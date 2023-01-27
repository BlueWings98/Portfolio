package com.novoa.videogames.repository;

import com.novoa.videogames.entity.Sale;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface SaleRepository extends CrudRepository <Sale, String> {
    boolean existsByProductName(String productName);
}
