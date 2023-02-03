package com.novoa.videogames.service;

import com.amazonaws.Response;
import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.repository.ConsoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsoleServiceTest {
    @Mock
    private ConsoleRepository consoleRepository;
    @InjectMocks
    @Autowired
    private ConsoleService consoleService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getConsoleIterableSuccess() {
        when(this.consoleRepository.findAll()).thenReturn(this.getDummyConsoleIterable());
        Iterable<Console> returnedValue = this.consoleService.getConsoleIterable();
        assertEquals(returnedValue, getDummyConsoleIterable());
    }
    @Test
    void getConsoleIterableFail()  {
        when(this.consoleRepository.findAll()).thenThrow(new IllegalArgumentException());
        assertThrows(Exception.class,()-> this.consoleService.getConsoleIterable());
    }
    @Test
    void getConsoleByIdSuccess() {
        when(this.consoleRepository.findById(anyString())).thenReturn(Optional.of(this.getDummyConsole()));
        when(this.consoleRepository.existsById(anyString())).thenReturn(true);
        ResponseEntity<Console> returnedValue = this.consoleService.getConsoleById("1");
        assertEquals(HttpStatus.OK,returnedValue.getStatusCode());
    }
    @Test
    void getConsoleByIdFail() {
        when(this.consoleRepository.findById(anyString())).thenThrow(new IllegalArgumentException());
        assertDoesNotThrow(()-> this.consoleService.getConsoleById("1"));
    }
    @Test
    void saveConsoleSuccess() {
        when(this.consoleRepository.save(any(Console.class))).thenReturn(this.getDummyConsole());
        Console returnedValue = this.consoleService.saveConsole(this.getDummyConsoleDto());
        assertEquals(getDummyConsole(),returnedValue);
    }
    @Test
    void saveConsoleFail() {
        when(this.consoleRepository.save(any(Console.class))).thenThrow(new IllegalArgumentException());
        assertDoesNotThrow(()-> this.consoleService.saveConsole(this.getDummyConsoleDto()));
    }
    @Test
    void updateConsoleSuccess() {
        when(this.consoleRepository.save(any(Console.class))).thenReturn(this.getDummyConsole());
        when(this.consoleRepository.findById(anyString())).thenReturn(Optional.of(this.getDummyConsole()));
        ResponseEntity<Console> returnedValue = this.consoleService.updateConsole(this.getDummyConsoleDto());
        assertEquals(HttpStatus.OK,returnedValue.getStatusCode());
    }
    @Test
    void updateConsoleFail() {
        when(this.consoleRepository.findById(anyString())).thenThrow(new IllegalArgumentException());
        assertDoesNotThrow(()-> this.consoleService.updateConsole(this.getDummyConsoleDto()));
    }
    @Test
    void deleteConsoleSuccess() {
        when(this.consoleRepository.findById(anyString())).thenReturn(Optional.of(this.getDummyConsole()));
        assertDoesNotThrow(()->this.consoleService.deleteConsole("1"));
    }
    @Test
    void deleteConsoleFail() {
        when(this.consoleRepository.findById(anyString())).thenThrow(new IllegalArgumentException());
        assertDoesNotThrow(()->this.consoleService.deleteConsole("1"));
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