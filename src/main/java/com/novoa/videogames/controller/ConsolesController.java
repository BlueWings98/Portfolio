package com.novoa.videogames.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.dto.QuoteDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.service.ConsoleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        return consoleService.getConsoleById(consoleId);
    }
    @PostMapping("/consoles")
    public ResponseEntity<Console> createConsole(@RequestBody ConsoleDto consoleDto){
        return consoleService.createConsole(consoleDto);
    }
    @PutMapping("/consoles")
    public ResponseEntity<Console> updateConsole(@RequestBody ConsoleDto consoleDto){
        return consoleService.updateConsole(consoleDto);
    }
    @DeleteMapping("/consoles/{consoleId}")
    public ResponseEntity<?> deleteConsoleById(@PathVariable("consoleId") String consoleId){
        return consoleService.deleteConsole(consoleId);
    }
    @PutMapping("/consoles/sale")
    public Map sellConsole(@RequestBody QuoteDto quoteDto){
        JSONObject quote = this.consoleService.sellConsole(quoteDto);
        return quote.toMap();
    }
}
