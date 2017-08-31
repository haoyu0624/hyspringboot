package com.hy.Thread;

import com.hy.Test1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by haoy on 2017/8/23.
 */
public class ThreadTest {
    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(10);
        poolTaskExecutor.initialize();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            poolTaskExecutor.submit(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("===============>>"+j);

                }
            });
        }

    }
}
