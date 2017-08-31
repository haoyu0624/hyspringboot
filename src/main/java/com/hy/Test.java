package com.hy;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by haoy on 2017/6/22.
 */
public class Test {
    private  static boolean bbb = false;
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bbb = true;
                //System.out.println("==================>>"+bbb);
                //i = i+1;
                System.out.println("flag:"+bbb);
            }
        }).start();

        while(true){
            if(bbb){
                System.out.println("---------------------------");
            }
        }


    }

}
class Th implements Runnable{
    private boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag:"+isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}