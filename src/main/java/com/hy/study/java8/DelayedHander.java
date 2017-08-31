package com.hy.study.java8;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by haoy on 2017/8/1.
 */
public class DelayedHander {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

        executor.schedule(new Runnable() {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Work start, thread id:" + Thread.currentThread().getId() + " " + sdf.format(new Date()));
            }

        }, 2, TimeUnit.SECONDS);

        System.out.println("=-==========================");
    }
}
