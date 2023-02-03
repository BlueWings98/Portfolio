package com.novoa.videogames.controller;

import com.novoa.videogames.dto.UtilitiesDto;
import com.novoa.videogames.entity.Console;
import com.novoa.videogames.service.ExcelReadService;
import com.novoa.videogames.service.HttpDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilitiesController {
    @Autowired
    ExcelReadService excelReadService;
    @Autowired
    HttpDownloadService httpDownloadService;
    @PostMapping("/utilities/download")
    public ResponseEntity<String> downloadFile(@RequestBody UtilitiesDto utilitiesDto) {
        return httpDownloadService.downloadFile(utilitiesDto);
    }
    @PostMapping("/utilities/read")
    public ResponseEntity<Iterable<Console>> readExcelAndUpload(@RequestBody UtilitiesDto utilitiesDto) {
        return excelReadService.readExcelAndUpload(utilitiesDto);
    }
}
