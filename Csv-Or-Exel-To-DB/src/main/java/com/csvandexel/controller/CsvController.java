package com.csvandexel.controller;

import com.csvandexel.entity.CsvEntity;
import com.csvandexel.service.CsvService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CsvController {
    private final CsvService csvService;

    @Autowired
    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/uploadcsv")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        csvService.processFile(multipartFile);
        String message = "File uploaded successfully!";
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadexcel")
    public ResponseEntity<Map<String,HttpStatus>> uploadExcel(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        csvService.processFileExcel(multipartFile);
        String ret = (multipartFile != null) ? "Uploaded" : "Not Uploaded";
        Map<String,HttpStatus> map = new HashMap<>();
        map.put(ret,HttpStatus.ACCEPTED);
        return  ResponseEntity.ok(map);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @JsonIgnore
    @GetMapping("/all")
    public ResponseEntity<List<CsvEntity>> all(){
        List<CsvEntity> csvEntities = csvService.list();
        return new ResponseEntity<>(csvEntities, HttpStatus.OK);
    }

}
