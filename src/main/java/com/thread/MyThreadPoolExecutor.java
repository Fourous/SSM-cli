package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fourous
 * @date: 2019/12/30
 * @description: 线程池执行类
 * 线程池主要考虑因素有核心线程数，最大线程数，任务队列，拒绝策略
 * 其中JKD中的线程池还有两个参数KeepAliveTime,unit 用来控制何时销毁核心和非核心线程的
 *
 *
 * **************************   Java中5种线程池创建方式   *****************
 *     threadPool = Executors.newCachedThreadPool();//有缓冲的线程池，线程数 JVM 控制
 *     threadPool = Executors.newFixedThreadPool(3);//固定大小的线程池
 *     threadPool = Executors.newScheduledThreadPool(2);
 *     threadPool = Executors.newSingleThreadExecutor();//单线程的线程池，只有一个线程在工作
 *     threadPool = new ThreadPoolExecutor();//默认线程池，可控制参数比较多 
 *
 *
 * **************************   Java中3中阻塞队列        ****************
 *     workQueue = new ArrayBlockingQueue<>(5);//基于数组的先进先出队列，有界
 *     workQueue = new LinkedBlockingQueue<>();//基于链表的先进先出队列，无界
 *     workQueue = new SynchronousQueue<>();//无缓冲的等待队列，无界
 */
public class MyThreadPoolExecutor implements Executor {
    /**
     * 线程池名称
     */
    private String name;
    /**
     * 线程序列号
     */
    private AtomicInteger sequence = new AtomicInteger(0);
    /**
     * 核心线程数
     */
    private int coreSize;
    /**
     * 最大线程数
     */
    private int maxSize;
    /**
     * 任务队列
     */
    private BlockingQueue<Runnable> taskQueue;
    /**
     * 拒绝策略
     */
    private RejectPolicy rejectPolicy;
    /**
     * 当前正在运行的线程数
     * 由于需要可见行，需要使用原子操作，也可以使用volatile并结合Unsafe做CAS操作
     */
    private AtomicInteger runningCount = new AtomicInteger(0);

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable task) {
        //正在运行的线程数
        int count = runningCount.get();
        // 如果正在运行的线程数小于核心线程数，直接加一个线程
        if (count < coreSize) {
            if (addWorker(task, true)) {
                return;
            }
            //如果添加核心线程失败，进入下面逻辑
        }
        if (taskQueue.offer(task)) {
            // TODO doThing
        } else {
            //如果入队失败
            if (addWorker(task, false)) {
                //如果添加非核心线程失败，执行拒绝策略
                rejectPolicy.reject(task, this);
            }
        }
    }

    /**
     * 创建线程要时刻警惕并发陷阱
     *
     * @param newTask
     * @param core
     * @return
     */
    private boolean addWorker(Runnable newTask, boolean core) {
        /**
         * 自旋判断是不是真的可以创建一个线程
         */
        for (; ; ) {
            // 正在运行的线程数
            int count = runningCount.get();
            //核心还是非核心线程
            int max = core ? coreSize : maxSize;
            //不满足创建线程条件，直接返回false
            if (count >= max) {
                return false;
            }
            // 修改runningCount成功，可以创建线程
            if (runningCount.weakCompareAndSet(count, count + 1)) {
                //线程名字
                String threadName = (core ? "core_" : "") + name + sequence.incrementAndGet();
                new Thread(() -> {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    Runnable task = newTask;
                    //不断从任务队列中取出任务执行，如果取到NULL就跳出循环，线程结束
                    while (task != null || (task = getTask()) != null) {
                        try {
                            //执行任务
                            task.run();
                        } finally {
                            //任务执行完成，置为空
                            task = null;
                        }
                    }
                }, threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try {
            return taskQueue.take();
        } catch (InterruptedException e) {
            runningCount.decrementAndGet();
            return null;
        }
    }

    /**
     * 测试结果可见创建了5个核心线程，执行了25个任务
     *
     * @param args
     */
    public static void main(String[] args) {
        Executor threadPool = new MyThreadPoolExecutor("test", 5, 10, new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("running: " + System.currentTimeMillis() + ": " + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
