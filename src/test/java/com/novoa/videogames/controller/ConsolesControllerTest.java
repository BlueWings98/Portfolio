package com.novoa.videogames.controller;

import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.service.ConsoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsolesControllerTest {
    @Mock
    ConsoleService consoleService;
    @InjectMocks
    @Autowired
    ConsolesController consolesController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getAllConsolesSuccess() {
        when(consoleService.getConsoleIterable()).thenReturn(this.getDummyConsoleIterable());
        ResponseEntity<Iterable<Console>> response = consolesController.getAllConsoles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getAllConsolesFail() {
        when(consoleService.getConsoleIterable()).thenThrow(IllegalArgumentException.class);
        assertThrows(Exception.class,()->this.consolesController.getAllConsoles());
    }
    @Test
    void getConsoleByIdSuccess() {
        when(consoleService.getConsoleById(anyString())).thenReturn(new ResponseEntity<Console>(HttpStatus.OK));
        when(consoleService.consoleExistsById(anyString())).thenReturn(true);
        ResponseEntity<Console> response = consolesController.getConsoleById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getConsoleByIdFail() {
        when(consoleService.getConsoleById(anyString())).thenReturn(new ResponseEntity<Console>(HttpStatus.NOT_FOUND));
        ResponseEntity<Console> response = consolesController.getConsoleById("1");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void createConsoleSuccess() {
        when(consoleService.createConsole(any(ConsoleDto.class))).thenReturn(new ResponseEntity<Console>(HttpStatus.OK));
        ResponseEntity<Console> response = consolesController.createConsole(this.getDummyConsoleDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void createConsoleFail() {
        when(consoleService.createConsole(any(ConsoleDto.class))).thenReturn(new ResponseEntity<Console>(HttpStatus.BAD_REQUEST));
        ResponseEntity<Console> response = consolesController.createConsole(this.getDummyConsoleDto());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void updateConsoleSuccess() {
        when(consoleService.consoleExistsById(anyString())).thenReturn(true);
        when(consoleService.updateConsole(any(ConsoleDto.class))).thenReturn(new ResponseEntity<Console>(HttpStatus.OK));
        ResponseEntity<Console> response = consolesController.updateConsole(this.getDummyConsoleDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void updateConsoleFail() {
        when(consoleService.consoleExistsById(anyString())).thenReturn(false);
        when(consoleService.updateConsole(any(ConsoleDto.class))).thenReturn(new ResponseEntity<Console>(HttpStatus.BAD_REQUEST));
        ResponseEntity<Console> response = consolesController.updateConsole(this.getDummyConsoleDto());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void deleteConsoleByIdSuccess() {
        when(consoleService.consoleExistsById(anyString())).thenReturn(true);
        when(consoleService.deleteConsole(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = consolesController.deleteConsoleById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void deleteConsoleByIdFail() {
        when(consoleService.consoleExistsById(anyString())).thenReturn(false);
        when(consoleService.deleteConsole(anyString())).thenReturn(new ResponseEntity<String>(HttpStatus.NOT_FOUND));
        ResponseEntity<?> response = consolesController.deleteConsoleById("1");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    private Iterable<Console> getDummyConsoleIterable(){
        ArrayList<Console> dummyConsoleArrayList = new ArrayList<Console>();
        dummyConsoleArrayList.add(this.getDummyConsole());

        return dummyConsoleArrayList;
    }
    private Console getDummyConsole(){
        return new Console("1",
                "PlayStation4",
                "500",
                "10000",
                "12");
    }
    private ConsoleDto getDummyConsoleDto(){
        return new ConsoleDto("1",
                "PlayStation4",
                "500",
                "10000",
                "12");
    }
}