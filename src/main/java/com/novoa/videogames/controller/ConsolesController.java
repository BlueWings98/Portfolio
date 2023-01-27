package com.novoa.videogames.controller;

import com.novoa.videogames.entity.Console;
import com.novoa.videogames.entity.User;
import com.novoa.videogames.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsolesController {
    @Autowired
    ConsoleService consoleService;
    @GetMapping("/consoles")
    public ResponseEntity<Iterable<Console>> getAllConsoles(){
        return ResponseEntity.ok(consoleService.getConsoleIterable());
    }
    @GetMapping("/users/{consoleId}")
    public ResponseEntity<Console> getConsoleById(@PathVariable("consoleId") String consoleId){
        if(!consoleService.consoleExistsById(consoleId)){
            return new ResponseEntity("There is no console with that Id: " + consoleId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(consoleService.getConsoleById(consoleId));
    }
}
