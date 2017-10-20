package com.merak.lzpt.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Async
	public Future<String> doTask1() {
		logger.info("Task1 started.");
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("Task1 finished, time elapsed: {} ms.", end - start);
		return new AsyncResult<>("Task1 accomplished!");
	}
}
