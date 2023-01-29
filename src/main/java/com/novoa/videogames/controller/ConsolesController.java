package com.novoa.videogames.controller;

import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsolesController {
    @Autowired
    ConsoleService consoleService;
    @GetMapping("/consoles")
    public ResponseEntity<Iterable<Console>> getAllConsoles(){
        return ResponseEntity.ok(consoleService.getConsoleIterable());
    }
    @GetMapping("/consoles/{consoleId}")
    public ResponseEntity<Console> getConsoleById(@PathVariable("consoleId") String consoleId){
        if(!consoleService.consoleExistsById(consoleId)){
            return new ResponseEntity("There is no console with that Id: " + consoleId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(consoleService.getConsoleById(consoleId));
    }
    @PostMapping("/consoles")
    public ResponseEntity<Console> createConsole(@RequestBody ConsoleDto consoleDto){
        if(consoleService.consoleExistsById(consoleDto.getConsoleId())){
            return new ResponseEntity("The console Id already exists", HttpStatus.BAD_REQUEST);
        }
        if (consoleService.consoleExistsByName(consoleDto.getConsoleName())){
           return new ResponseEntity("That console name already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(consoleService.saveConsole(consoleDto));
    }
    @PutMapping("/consoles")
    public ResponseEntity<Console> updateConsole(@RequestBody ConsoleDto consoleDto){
        if(!consoleService.consoleExistsById(consoleDto.getConsoleId())){
            return new ResponseEntity("The console with that Id does not exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(consoleService.updateConsole(consoleDto));
    }
    @DeleteMapping("/consoles/{consoleId}")
    public ResponseEntity<?> deleteConsoleById(@PathVariable("consoleId") String consoleId){
        if(!consoleService.consoleExistsById(consoleId)){
            return new ResponseEntity<>("There is no console with that Id: " + consoleId, HttpStatus.NOT_FOUND);
        }
        String message = consoleService.deleteConsole(consoleId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
