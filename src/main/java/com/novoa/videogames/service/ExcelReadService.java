package com.novoa.videogames.service;

import com.novoa.videogames.dto.ConsoleDto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelReadService {
    public ArrayList<ConsoleDto> ReadExcel(String filePath) {
        Map<Integer, List<String>> data = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(i).add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            data.get(i).add(cell.getNumericCellValue() + "");
                            break;
                        default:
                            data.get(i).add("placeholder");
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found while trying to read the excel");
        } catch (IOException e) {
            System.out.println("There was a Input-Output exception while trying to read the excel");
        }
        return this.excelRowsToConsoleDto(data);
    }

    private ArrayList<ConsoleDto> excelRowsToConsoleDto(Map<Integer, List<String>> data) {
        int key;
        int i;
        List<String> rows;
        ArrayList<ConsoleDto> consolesDtoToLoad = new ArrayList<>();
        ConsoleDto consoleDto = new ConsoleDto();
        Iterator iterator;
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            i = 0;
            key = entry.getKey();
            rows = entry.getValue();
            iterator = rows.listIterator();
            while (iterator.hasNext()) {
                switch (i) {
                    case 0:

                        consoleDto.setConsoleName(rows.get(i));
                        break;
                    case 1:
                        consoleDto.setMinPrice(rows.get(i));
                        break;
                    case 2:
                        consoleDto.setMaxPrice(rows.get(i));
                        break;
                    case 3:
                        consoleDto.setDiscount(rows.get(i));
                        break;
                    default:
                        break;
                }
                consoleDto.setConsoleId(String.valueOf(key));
                iterator.next();
                i++;
            }
            if(consoleDto.getDiscount()==null){
                consoleDto.setDiscount(consoleDto.getMaxPrice());
                consoleDto.setMaxPrice("");
            }
            consolesDtoToLoad.add(consoleDto);
            consoleDto = new ConsoleDto();
        }
        consolesDtoToLoad.remove(0);
        return consolesDtoToLoad;
    }
}
