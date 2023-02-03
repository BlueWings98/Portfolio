package com.novoa.videogames.repository;

import com.novoa.videogames.entity.Console;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ConsoleRepository extends CrudRepository <Console, String> {
    boolean existsByConsoleName(String consoleName);
    Console getByConsoleName(String consoleName);
}
