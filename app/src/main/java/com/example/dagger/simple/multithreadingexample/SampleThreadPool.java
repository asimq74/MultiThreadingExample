package com.example.dagger.simple.multithreadingexample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;

public class SampleThreadPool {

	public static final SampleThreadPool DEFAULT = new SampleThreadPool();
	private static final int KEEP_ALIVE = 10;
	private static int MAX_POOL_SIZE;
	private static SampleThreadPool mInstance;

	public static void finish() {
		mInstance.mThreadPoolExec.shutdown();
	}

	public static synchronized void post(AsyncTask<String, String, Void> asyncTask) {
		if (mInstance == null) {
			mInstance = new SampleThreadPool();
		}
		asyncTask.executeOnExecutor(mInstance.mThreadPoolExec);
	}
	private ThreadPoolExecutor mThreadPoolExec;
	BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

	private SampleThreadPool() {
//		ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
//				.setNamePrefix("DemoPool-Thread")
//				.setDaemon(false).setPriority(Thread.MAX_PRIORITY).build();
		int coreNum = Runtime.getRuntime().availableProcessors();
		MAX_POOL_SIZE = coreNum * 2;
		mThreadPoolExec = new ThreadPoolExecutor(
				coreNum,
				MAX_POOL_SIZE,
				KEEP_ALIVE,
				TimeUnit.SECONDS,
				workQueue
//				, customThreadfactory
		);
	}
}
