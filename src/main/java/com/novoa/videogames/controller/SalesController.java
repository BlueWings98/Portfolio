package com.novoa.videogames.controller;

import com.novoa.videogames.dto.SaleDto;
import com.novoa.videogames.entity.Sale;
import com.novoa.videogames.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return this.saleService.getSaleById(saleId);
    }
    @PostMapping("/sales")
    public ResponseEntity<Sale> createSale(@RequestBody SaleDto saleDto){
        return this.saleService.saveSale(saleDto);
    }
    @PutMapping("/sales")
    public ResponseEntity<Sale> updateSale(@RequestBody SaleDto saleDto){
        return this.saleService.updateSale(saleDto);
    }
    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<String> deleteSaleById(@PathVariable("saleId") String saleId){
        return this.saleService.deleteSale(saleId);
    }
    @GetMapping("/sales/discount/report")
    public ResponseEntity<String> getDiscountReport(){
        return this.saleService.getDiscountReport();
    }
}
