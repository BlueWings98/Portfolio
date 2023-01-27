package com.novoa.videogames.service;

import com.novoa.videogames.dto.SaleDto;
import com.novoa.videogames.entity.Sale;
import com.novoa.videogames.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
    public Sale getSaleById(String saleId){
        try{
            return saleRepository.findById(saleId).get();
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in Sale Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id: "+ saleId +" while trying to fetch by Id Sale");
        }
        return new Sale();
    }
    public Sale saveSale(SaleDto saleDto){
        Sale sale = new Sale(
                saleDto.getSaleId(),
                saleDto.getProductName(),
                saleDto.getPrice(),
                saleDto.getDiscount()
        );
        try {
            return saleRepository.save(sale);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Sale Repository");
        }
        return new Sale();
    }
    public Sale updateSale(SaleDto saleDto){
        try {
            Sale sale = saleRepository.findById(saleDto.getSaleId()).get();
            sale.setSaleId(saleDto.getSaleId());
            sale.setProductName(saleDto.getProductName());
            sale.setPrice(saleDto.getPrice());
            sale.setDiscount(saleDto.getDiscount());
            return saleRepository.save(sale);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Sale Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id, while trying to update Sale");
        }
        return new Sale();
    }
    public void deleteSale(String saleId){
        try{
            Sale sale = saleRepository.findById(saleId).get();
            if(!sale.equals(Optional.empty())){
                saleRepository.deleteById(saleId);
            } else {
                System.out.println("There is no such element with Id: " + saleId + " In Sale Repository");
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such element to delete with id: "+ saleId+ " in Sale Repository.");
        } catch (IllegalArgumentException e ){
            System.out.println("The given argument was null while trying to delete, in Sale Repository");
        }
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
}
