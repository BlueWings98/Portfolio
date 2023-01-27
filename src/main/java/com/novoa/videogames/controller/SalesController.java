package com.novoa.videogames.controller;

import com.novoa.videogames.entity.Sale;
import com.novoa.videogames.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
    @Autowired
    SaleService saleService;
    @GetMapping("/sales")
    public ResponseEntity<Iterable<Sale>> getAllSales(){
        return ResponseEntity.ok(saleService.getSaleIterable());
    }
    @GetMapping("/sales/{saleId}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("saleId") String saleId){
        if(!saleService.saleExistsById(saleId)){
            return new ResponseEntity("There is no sale with that Id: " + saleId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(saleService.getSaleById(saleId));
    }
}
