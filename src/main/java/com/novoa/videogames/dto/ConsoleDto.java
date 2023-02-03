package com.novoa.videogames.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsoleDto {
    private String consoleId;
    private String consoleName;
    private String minPrice;
    private String maxPrice;
    private String discount;
}
