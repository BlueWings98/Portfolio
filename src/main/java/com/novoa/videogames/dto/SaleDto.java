package com.novoa.videogames.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private String saleId;
    private String productName;
    private String price;
    private String discount;
}
