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
        if(!saleService.saleExistsById(saleId)){
            return new ResponseEntity("There is no sale with that Id: " + saleId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(saleService.getSaleById(saleId));
    }
    @PostMapping("/sales")
    public ResponseEntity<Sale> createSale(@RequestBody SaleDto saleDto){
        if(saleService.saleExistsById(saleDto.getSaleId())){
            return new ResponseEntity("The sale Id already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(saleService.saveSale(saleDto));
    }
    @PutMapping("/sales")
    public ResponseEntity<Sale> updateSale(@RequestBody SaleDto saleDto){
        if(!saleService.saleExistsById(saleDto.getSaleId())){
            return new ResponseEntity("The sale with that Id does not exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(saleService.updateSale(saleDto));
    }
    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<?> deleteSaleById(@PathVariable("saleId") String saleId){
        if(!saleService.saleExistsById(saleId)){
            return new ResponseEntity("There is no sale with that Id: " + saleId, HttpStatus.NOT_FOUND);
        }
        saleService.deleteSale(saleId);
        return new ResponseEntity("Sale deleted successfully", HttpStatus.OK);
    }
}
