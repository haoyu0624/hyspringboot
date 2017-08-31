package com.hy.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by haoy on 2017/7/27.
 */
@Component
public interface UserRepository extends MongoRepository<User, Long> {

     User findByUsername(String username);

}