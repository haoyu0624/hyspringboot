package com.hy.trigger;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
//@EnableAutoConfiguration
@EnableRetry
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RequestMapping("/samplecontroller")
public class SampleController {
    int i=0;
    @Autowired
//    private RetryTest retryTest;

    @Value("${test.value}")
    private String dd;
    @RequestMapping("/test")
    @ResponseBody
    String home() throws Exception {
//        retryTest.retryMethod(dd);
        RetryTest retryTest = new RetryTest();
        retryTest.handler();
        System.out.println("=================NB================="+dd);
        return "Hello World!";
    }

    @RequestMapping("/test1")
    @ResponseBody
    String homeTest(@RequestBody String ss, @RequestParam String salary,@RequestParam String name, @RequestHeader String adminId) {
        System.out.println(ss);
//        String user = ss.get("user").toString();
////        HashMap jsonObject = JSONObject.parseObject(ss,HashMap.class);
////        String user = jsonObject.get("user").toString();
////        Integer age = Integer.valueOf(ss.get("age").toString());
////        System.out.println("======name======"+name);
////        System.out.println("======user======"+user);
//        System.out.println("======ss======"+ss);
//        System.out.println("======user======"+user);
////        System.out.println("======salary======"+salary);
////        System.out.println("======deleted======"+deleted);
        System.out.println("======adminId======"+adminId);
//        //retryTest.testRetry();
//        System.out.println("=================NB=================");
        return "Hello World!";
    }

    @RequestMapping("/gzyinterface")
    @ResponseBody
    String homeTest1(@RequestBody Map ss, @RequestParam Integer salary, @RequestHeader String adminId) {
        String user = ss.get("user").toString();
        String age = ss.get("age").toString();
        System.out.println("======user======"+user);
        System.out.println("======age======"+age);
        System.out.println("======salary======"+salary);
        System.out.println("======adminId======"+adminId);
        //retryTest.testRetry();
        System.out.println("=================NB=================");
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}