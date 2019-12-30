package com.thread;

/**
 * @author fourous
 * @date: 2019/12/30
 * @description: 采用丢弃策略
 */
public class DiscardRejectPolicy implements RejectPolicy {
    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        // doSomeThing
        System.out.println("discard one task");
    }
}
