package com.hy.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoy on 2017/8/16.
 */
public class Log4j2Test {
    private static Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    private static Logger loggerl = LoggerFactory.getLogger("Log4j2Test");


    public static void main(String[] args) {
        logger.info("1111111111111");
        loggerl.info("22222222222222");
        Map map = new HashMap();
        map.put("msgKey","receiveActionKafkaStart");
        String replace = map.get("msgKey").toString().replace("Start", "End");
    }
}
