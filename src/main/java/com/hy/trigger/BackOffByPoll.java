package com.hy.trigger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.*;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by haoy on 2017/7/3.
 */
public class BackOffByPoll extends StatelessBackOffPolicy {
    private Sleeper sleeper = new ThreadWaitSleeper();
    //时间
    private volatile long hour;
    //次数
    private volatile int time=0;

    public BackOffByPoll(long hour) {
        this.hour = hour;
    }
//    public static void retryTemplate(FixedBackOffPolicy BackOff,RetryCallback retrycallback,RecoveryCallback recoveryCallback) throws Throwable {
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setBackOffPolicy(BackOff);
//        retryTemplate.execute(retrycallback,recoveryCallback);
//        retryTemplate.execute(new RetryCallback<Void, RuntimeException>() {
//            @Override
//            public Void doWithRetry(RetryContext arg0) {
//                System.out.println("method========i");
//                //getResult(111);
//                throw new RemoteAccessException("11111111");
//            }
//
//        }, new RecoveryCallback<Void>() {
//            @Override
//            public Void recover(RetryContext retryContext) throws Exception {
//                System.out.println("fuck!!!!!!!!!!!!!!!!");
//                return null;
//            }
//        });
//    }

    @Override
    protected void doBackOff() throws BackOffInterruptedException {
        long result;
        //long s = 5000l;
        //long currentTime = this.time;
        //long result = time - s;
        if(time==0){
            result = Math.round(hour * 0.1);
        }else if(time==1){
            result = Math.round(hour * 0.3);
        }else{
            result = Math.round(hour * 0.5);
        }
        time++;
        try {
            this.sleeper.sleep(result);
        } catch (InterruptedException var2) {
            throw new BackOffInterruptedException("Thread interrupted while sleeping", var2);
        }
    }

}
