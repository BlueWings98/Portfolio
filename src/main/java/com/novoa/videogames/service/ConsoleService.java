package com.novoa.videogames.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.dto.QuoteDto;
import com.novoa.videogames.dto.SaleDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.repository.ConsoleRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ConsoleService {
    @Autowired
    ConsoleRepository consoleRepository;
    @Autowired
    SaleService saleService;
    public Iterable<Console> getConsoleIterable(){
        return consoleRepository.findAll();
    }
    public Console getConsoleById(String consoleId){
        try{
            return consoleRepository.findById(consoleId).get();
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in Console Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id: "+ consoleId +" while trying to fetch by Id Console");
        }
        return new Console();
    }
    public Console getConsoleByName(String consoleName){
        try{
            return consoleRepository.getByConsoleName(consoleName);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in Console Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given name: "+ consoleName +" while trying to fetch by Id Console");
        }
        return new Console();
    }
    public Console saveConsole(ConsoleDto consoleDto){
        Console console = new Console(
                consoleDto.getConsoleId(),
                consoleDto.getConsoleName(),
                consoleDto.getMinPrice(),
                consoleDto.getMaxPrice(),
                consoleDto.getDiscount()
        );
        try {
            return consoleRepository.save(console);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Console Repository");
        }
        return new Console();
    }
    public Console updateConsole(ConsoleDto consoleDto){
        try {
            Console console = consoleRepository.findById(consoleDto.getConsoleId()).get();
            console.setConsoleId(consoleDto.getConsoleId());
            console.setConsoleName(consoleDto.getConsoleName());
            console.setMinPrice(consoleDto.getMinPrice());
            console.setMaxPrice(consoleDto.getMaxPrice());
            console.setDiscount(consoleDto.getDiscount());
            return consoleRepository.save(console);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in Console Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id, while trying to update Console");
        }
        return new Console();
    }
    public String deleteConsole(String consoleId){
        try{
            Console console = consoleRepository.findById(consoleId).get();
            if(!console.equals(Optional.empty())){
                consoleRepository.deleteById(consoleId);

                return "The console was deleted successfully";
            } else {
                System.out.println("There is no such element with Id: " + consoleId + " In Console Repository");
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such element to delete with id: "+ consoleId+ " in Console Repository.");
        } catch (IllegalArgumentException e ){
            System.out.println("The given argument was null while trying to delete, in Console Repository");
        }
        return "";
    }
    public JSONObject sellConsole(QuoteDto quoteDto){
        if(this.consoleExistsByName(quoteDto.getConsole())){
            Console console = this.consoleRepository.getByConsoleName(quoteDto.getConsole());
            JSONObject jsonObject = new JSONObject();
            SaleDto saleDto = new SaleDto();
            String cleanMinPrice = console.getMinPrice().split("\\.")[0];
            String cleanDiscount = console.getDiscount().split("\\.")[0];
            saleDto.setProductName(console.getConsoleName());
            int value = Integer.parseInt(cleanMinPrice);
            if(value<= quoteDto.getValue()){
                value = Integer.parseInt(cleanMinPrice)*(100-Integer.parseInt(cleanDiscount))/100;
                saleDto.setPrice(String.valueOf(value));
                saleDto.setDiscount(String.valueOf(Integer.parseInt(cleanMinPrice)-value));
            } else {
                value = Integer.parseInt(cleanMinPrice);
                saleDto.setPrice(String.valueOf(value));
            }
            String randomSaleId = String.valueOf(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
            saleDto.setSaleId(randomSaleId);
            this.saleService.saveSale(saleDto);
            jsonObject.put("ValueToChargeClient", value);
            return jsonObject;
        }
        return new JSONObject();
    }
    public boolean consoleExistsById(String consoleId){
        try{
            return consoleRepository.existsById(consoleId);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to check if that console existed by id.");
        }
        return false;
    }
    public boolean consoleExistsByName(String consoleName){
        try {
            return consoleRepository.existsByConsoleName(consoleName);
        } catch (IllegalArgumentException e){
        System.out.println("The given argument was null while trying to check if that console existed by name.");
    }
        return false;
    }
}
