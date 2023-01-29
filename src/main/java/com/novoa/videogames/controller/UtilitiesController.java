package com.novoa.videogames.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.novoa.videogames.dto.ConsoleDto;
import com.novoa.videogames.dto.UtilitiesDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.repository.ConsoleRepository;
import com.novoa.videogames.service.ConsoleService;
import com.novoa.videogames.service.ExcelReadService;
import com.novoa.videogames.service.HttpDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;

@RestController
public class UtilitiesController {
    @Autowired
    ExcelReadService excelReadService;
    @Autowired
    HttpDownloadService httpDownloadService;
    @Autowired
    ConsoleService consoleService;
    @PostMapping("/utilities/download")
    public ResponseEntity<String> downloadFile(@RequestBody UtilitiesDto utilitiesDto){
        String fileURL = utilitiesDto.getFileURL();
        File tmpDir = new File("src/main/resources/files/Descuentos.xlsx");
        if(!tmpDir.exists()){
            String message = this.httpDownloadService.downloadFile(fileURL,"src/main/resources/files");
            return new ResponseEntity<>(message,HttpStatus.OK);
        } else{
            return new ResponseEntity<>("The file already exists", HttpStatus.BAD_REQUEST);
        }
    }
    //"src/main/resources/files/Descuentos.xlsx"
    @PostMapping("/utilities/read")
    public ResponseEntity<Iterable<Console>> readExcelAndUpload(@RequestBody UtilitiesDto utilitiesDto){
        String inputFilePath = utilitiesDto.getInputFilePath();
        File tmpDir = new File(inputFilePath);
        if(tmpDir.exists()){
            ArrayList<Console> consoleArrayList = new ArrayList<>();
            ArrayList<ConsoleDto> consoleDtoArrayList = this.excelReadService.ReadExcel(inputFilePath);
            for (ConsoleDto consoleDto:consoleDtoArrayList) {
                consoleArrayList.add(this.consoleService.updateConsole(consoleDto));
            }
            return ResponseEntity.ok(consoleArrayList);
        } else{
            return new ResponseEntity("The file already exists", HttpStatus.BAD_REQUEST);
        }
    }
}
