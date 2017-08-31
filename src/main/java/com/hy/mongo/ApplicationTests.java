package com.hy.mongo;

import com.hy.trigger.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;


/**
 * Created by haoy on 2017/7/27.
 */
@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SampleController.class)
///@WebAppConfiguration
//@SpringBootApplication
public class ApplicationTests {

    @Autowired
    private UserRepository userRepository;
//    @Before
//    public void setUp() {
//        userRepository.deleteAll();
//    }

    @Test
    public void fuck() throws Exception {
        // 创建三个User，并验证User总数
        userRepository.save(new User(1L, "didi", 30,true));
        userRepository.save(new User(1L, "dbdb", 40,true));
        userRepository.save(new User(3L, "kaka", 50,false));
//        userRepository.findAll();
//
//        // 删除一个User，再验证User总数
//        User u = userRepository.findOne(1L);
//        userRepository.delete(u);
////        Assert.assertEquals(2, userRepository.findAll().size());
//
//        // 删除一个User，再验证User总数
//        u = userRepository.findByUsername("mama");
//        userRepository.delete(u);
//        Assert.assertEquals(1, userRepository.findAll().size());

    }

}