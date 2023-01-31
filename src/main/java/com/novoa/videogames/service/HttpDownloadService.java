package com.novoa.videogames.service;

import com.novoa.videogames.dto.UtilitiesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author www.codejava.net
 * Edited for the purpose of this code by Sebastian Novoa
 */
@Service
public class HttpDownloadService {
    @Autowired
    HttpDownloadService httpDownloadService;
    private final int BUFFER_SIZE = 4096;
    private String downloadFile(String fileURL, String saveDir) {
        try {
            String message = "";
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                if (disposition != null) {
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
                }
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
                message = "File downloaded";
            } else {
                message= "No file to download. Server replied HTTP code: " + responseCode;
            }
            httpConn.disconnect();

            return message;
        }catch(IOException e ){

            return ("There was an IOException: " + e.getMessage());
        }
    }
    public ResponseEntity<String> downloadFile(UtilitiesDto utilitiesDto){
        String fileURL = utilitiesDto.getFileURL();
        File tmpDir = new File("src/main/resources/files/Descuentos.xlsx");
        if (!tmpDir.exists()) {
            String message = this.httpDownloadService.downloadFile(fileURL, "src/main/resources/files");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The file already exists", HttpStatus.BAD_REQUEST);
        }
    }
}
