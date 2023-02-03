package com.novoa.videogames.controller;

import com.novoa.videogames.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SalesControllerTest {
    SaleService saleService;
    SalesController salesController;
    @BeforeEach
    void setUp() {
        this.saleService = mock(SaleService.class);
        this.salesController = new SalesController();
    }
    @Test
    void getAllSalesSuccess() {
    }
    @Test
    void getSaleByIdSuccess() {
    }
    @Test
    void createSaleSuccess() {
    }
    @Test
    void updateSaleSuccess() {
    }
    @Test
    void deleteSaleByIdSuccess() {
    }
}