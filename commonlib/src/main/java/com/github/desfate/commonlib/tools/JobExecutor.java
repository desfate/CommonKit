package com.github.desfate.commonlib.tools;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池  用于处理线程调用
 * 这个理论上是不同时处理多任务 ！！！ 不然任务太多会卡顿
 */
public class JobExecutor {

    private final static int LINK_MAX_CAPACITY = 4; //     最大任务数
    private final static int CORE_SIZE = 1; //             只有在工作队列满了的情况下才会创建超出这个数量的线程
    private final static int MAX_CORE_SIZE = 4;//          线程池中允许的最大线程数
    private final static int KEEP_ALIVE_TIME = 10;//       线程空闲时间  当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量 = corePoolSize


    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(LINK_MAX_CAPACITY);
    private ThreadPoolExecutor threadPoolExecutor;  //线程池处理耗时任务
    private Handler handler; //                       handler保证一些任务可以在主线程上执行

    public JobExecutor(){
        //初始化
        init();
    }

    public void init(){
        if(threadPoolExecutor == null){
            // 1:corePoolSize: 线程池中核心线程的数量
            // 2:maximumPoolSize: 线程池中最大线程数量
            // 3:keepAliveTime: 非核心线程的超时时长
            // 4:unit: keepAliveTime这个参数的单位
            // 5:workQueue: 线程池中的任务队列
            // 6:threadFactory: 为线程池提供创建新线程的功能
            // 7:handler: 拒绝策略，当线程无法执行新任务时
            threadPoolExecutor = new ThreadPoolExecutor(
                    CORE_SIZE,
                    MAX_CORE_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    workQueue,
                    new ThreadPoolExecutor.DiscardOldestPolicy());
            handler = new Handler(Looper.getMainLooper());  //默认肯定是在主线程返回
        }
    }

    public <T> void execute(final Task<T> task){
        if (threadPoolExecutor != null) {
            threadPoolExecutor.execute(() -> {
                try {
                    T res = task.run(); // 前置任务 任务结果会返回给主线程（在子线程中执行）
                    JobExecutor.this.postOnMainThread(task, res); // 主线程任务
                    task.onJobThread(res); // 子线程处理最终任务 任务结果不会返回
                } catch (Exception e) {
                    e.printStackTrace();
                    task.onError(e.getMessage());
                }
            });
        }
    }

    /**
     * 发送到主线程处理任务
     * @param task 任务
     * @param res  返回的资源
     * @param <T>  泛型
     */
    public <T> void postOnMainThread(final Task<T> task, final T res){
        handler.post(() -> task.onMainThread(res));
    }


    public static abstract class Task<T> {
        public T run() {
            return null;
        }
        public void onMainThread(T result) {
            // default no implementation
        }

        public void onJobThread(T result) {
            // default no implementation
        }

        public void onError(String msg) {
            // default no implementation
        }
    }
}
