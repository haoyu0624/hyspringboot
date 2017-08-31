package com.hy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by haoy on 2017/8/2.
 */
public class Test1 {
    private static Logger logger = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) {
//        StringTokenizer exprsTok = new StringTokenizer("*/1 * * * *", " \t",
//                false);
//        System.out.println(exprsTok.nextToken().trim());
//        System.out.println(exprsTok.nextToken().trim());
        HashMap m = new HashMap();
        m.put("1","a");
        System.out.println(m);
        List list = new ArrayList();
        list.iterator();

//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            logger.info("12312313213123");
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
        boolean b = StringUtils.hasText(null);
        System.out.println(b);

        String simpleName = List.class.getSimpleName();
        String name = List.class.getName();
        System.out.println(name);
        System.out.println(simpleName);

    }
}
