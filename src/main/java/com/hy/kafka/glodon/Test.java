package com.hy.kafka.glodon;

/**
 * Created by haoy on 2017/7/24.
 */
public class Test {

    private static volatile int c = 0;

    public static void main(String[] args) throws InterruptedException {
        //定时抓取
       new Thread(new Runnable()
       {
            @Override
            public void run()
            {
                while (true)
                {
                    if (c == 1)
                    {
                        //远程broker max poll
                        System.out.println("1111111111111111");
                        throw new RuntimeException();
                    }else
                        System.out.println("1111111111111111");
                }

            }
        }).start();
        Thread.sleep(100l);
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                System.out.println();
                c++;
            }
        }).start();
    }
}
