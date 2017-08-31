package com.hy.study.callback;

/**
 * Created by haoy on 2017/8/4.
 */
public class HanderMain {
    public static void main(String[] args) {
        final int itt = 1;
        RetryExcute re = new RetryExcute();
        re.start(new ReTryCallback(){
            @Override
            public void reTryBussiness() {
                System.out.println(itt);
                System.out.println("retry================bussiness");
            }
        });
    }
}
