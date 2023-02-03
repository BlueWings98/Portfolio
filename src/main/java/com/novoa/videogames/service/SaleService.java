package com.novoa.videogames.service;

import com.novoa.videogames.dto.SaleDto;
import com.novoa.videogames.entity.Sale;
import com.novoa.videogames.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    public Iterable<Sale> getSaleIterable(){
        return saleRepository.findAll();
    }
    public ResponseEntity<Sale> getSaleById(String saleId){
        if(saleExistsById(saleId)){
            return new ResponseEntity("There is no sale with that Id: " + saleId, HttpStatus.NOT_FOUND);
        }
        try{
            return ResponseEntity.ok(saleRepository.findById(saleId).get());
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in Sale Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id: "+ saleId +" while trying to fetch by Id Sale");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Sale> saveSale(SaleDto saleDto){
        if(saleExistsById(saleDto.getSaleId())){
            return new ResponseEntity("The sale Id already exists", HttpStatus.BAD_REQUEST);
        }
        Sale sale = new Sale(
                saleDto.getSaleId(),
                saleDto.getProductName(),
                saleDto.getPrice(),
                saleDto.getDiscount()
        );
        try {
            return ResponseEntity.ok(saleRepository.save(sale));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Sale Repository");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Sale> updateSale(SaleDto saleDto){
        if(!saleExistsById(saleDto.getSaleId())){
            return new ResponseEntity("The sale with that Id does not exist", HttpStatus.BAD_REQUEST);
        }
        try {
            Sale sale = saleRepository.findById(saleDto.getSaleId()).get();
            sale.setSaleId(saleDto.getSaleId());
            sale.setProductName(saleDto.getProductName());
            sale.setPrice(saleDto.getPrice());
            sale.setDiscount(saleDto.getDiscount());

            return ResponseEntity.ok(saleRepository.save(sale));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Sale Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id, while trying to update Sale");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteSale(String saleId){
        if(!saleExistsById(saleId)){
            return new ResponseEntity("There is no sale with that Id: " + saleId, HttpStatus.NOT_FOUND);
        }
        try{
            Sale sale = saleRepository.findById(saleId).get();
            if(!sale.equals(Optional.empty())){
                saleRepository.deleteById(saleId);
                return new ResponseEntity("Sale deleted successfully", HttpStatus.OK);
            } else {
                System.out.println("There is no such element with Id: " + saleId + " In Sale Repository");
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such element to delete with id: "+ saleId+ " in Sale Repository.");
        } catch (IllegalArgumentException e ){
            System.out.println("The given argument was null while trying to delete, in Sale Repository");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public boolean saleExistsById(String saleId){
        try{
            return saleRepository.existsById(saleId);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to check if that sale existed by id.");
        }
        return false;
    }
    public boolean saleExistsByName(String productName){
        try {
            return saleRepository.existsByProductName(productName);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to check if that sale existed by product name.");
        }
        return false;
    }
    public ResponseEntity<String> getDiscountReport(){
        int totalDiscount = 0;
        for (Sale sale: this.getSaleIterable()) {
            if (sale.getDiscount()!=null){
                totalDiscount = totalDiscount + Integer.parseInt(sale.getDiscount());
            }
        }
        return new ResponseEntity<>("The total given by discounts is: " + totalDiscount , HttpStatus.OK);
    }
}
