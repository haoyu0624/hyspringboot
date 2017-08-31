package com.hy.trigger;

import com.hy.trigger.BackOffByPoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haoy on 2017/6/30.
 */
//@Service
public class RetryTest {
    @Autowired
    RetryTemplate retryTemplate;
    @Value("${test.value}")
    private String dd;
    int i=0;
    //@Retryable(value= {RemoteAccessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000l,multiplier = 1))
    public String retryMethod(Map ss){

        System.out.println("i"+i++);
        //throw new Exception("remote access exception");
        throw new RuntimeException("Error");
        //return null;
    }

    @Recover
    public void recover(Exception e) {
        System.out.println("do recover operation1");
    }


    public void testRetry(){
        retryTemplate.setBackOffPolicy(new BackOffByPoll(60000L));
        retryTemplate.execute(new RetryCallback<Void, RuntimeException>() {
            @Override
            public Void doWithRetry(RetryContext arg0) {
                System.out.println("method========i"+i++);
                getResult(111);
                throw new RemoteAccessException("11111111");
            }

        }, new RecoveryCallback<Void>() {
            @Override
            public Void recover(RetryContext retryContext) throws Exception {
                System.out.println("fuck!!!!!!!!!!!!!!!!");
                return null;
            }
        });
        //getResult(111);
    }


    /**
     *
     * @param i
     */
    private void getResult(int i) {
    }

    public void handler() {
        System.out.println("=================================>>"+dd);
    }


//    @Recover
//    public void recover2(RuntimeException e) {
//        System.out.println("do recover operation1");
//    }
}
