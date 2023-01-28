package com.novoa.videogames.service;

import com.novoa.videogames.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SaleServiceTest {
    SaleRepository saleRepository;
    SaleService saleService;
    @BeforeEach
    void setUp() {
        this.saleRepository = mock(SaleRepository.class);
        this.saleService = new SaleService();
    }
    @Test
    void getSaleIterableSuccess() {
    }
    @Test
    void getSaleIterableFail() {
    }
    @Test
    void getSaleByIdSuccess() {
    }
    @Test
    void getSaleByIdFail() {
    }
    @Test
    void saveSaleSuccess() {
    }
    @Test
    void saveSaleFail() {
    }
    @Test
    void updateSaleSuccess() {
    }
    @Test
    void updateSaleFail() {
    }
    @Test
    void deleteSaleSuccess() {
    }
    @Test
    void deleteSaleFail() {
    }
    @Test
    void saleExistsByIdSuccess() {
    }
    @Test
    void saleExistsByIdFail() {
    }
    @Test
    void saleExistsByNameSuccess() {
    }
    @Test
    void saleExistsByNameFail() {
    }
}