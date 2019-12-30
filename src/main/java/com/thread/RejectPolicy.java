package com.thread;
/**
* @author fourous
* @date: 2019/12/30
* @description: RejectPolicy 拒绝策略接口
 * Java中有四种拒绝策略分别是
 *
 *     rejected = new ThreadPoolExecutor.AbortPolicy();//默认，队列满了丢任务抛出异常
 *     rejected = new ThreadPoolExecutor.DiscardPolicy();//队列满了丢任务不异常
 *     rejected = new ThreadPoolExecutor.DiscardOldestPolicy();//将最早进入队列的任务删，之后再尝试加入队列
 *     rejected = new ThreadPoolExecutor.CallerRunsPolicy();//如果添加到线程池失败，那么主线程会自己去执行该任务
 *
*/
public interface RejectPolicy {
    /**
     * 策略方法
     * @param task
     * @param myThreadPoolExecutor
     */
    void reject(Runnable task,MyThreadPoolExecutor myThreadPoolExecutor);
}
