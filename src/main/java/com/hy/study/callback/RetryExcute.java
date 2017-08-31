package com.hy.study.callback;

/**
 * Created by haoy on 2017/8/4.
 */
public class RetryExcute {

    public void start(ReTryCallback reTryCallback){
        System.out.println("=============================RetryExcute");
        reTryCallback.reTryBussiness();
    }
}
