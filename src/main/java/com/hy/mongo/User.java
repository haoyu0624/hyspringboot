package com.hy.mongo;

import org.springframework.data.annotation.Id;

/**
 * Created by haoy on 2017/7/27.
 */
public class User {

        @Id
        private Long id;
        //@Id
        private String username;
        private Integer age;
        private Boolean order;

        public User(Long id, String username, Integer age,Boolean order) {
            this.id = id;
            this.username = username;
            this.age = age;
            this.order = order;
        }


}
