package com.novoa.videogames.repository;

import com.novoa.videogames.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository <User,String> {
    boolean existsByUserName(String userName);
}
