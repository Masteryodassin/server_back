package com.tp.server_back.controllers;

import com.tp.server_back.services.utils.FileParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class FileUploadController {

    @Autowired
    FileParsingService fileParsingService;

    @GetMapping(value= "/file")
    public String uploadFile(){
        fileParsingService.loadAll();
        return "Fichier correctement chargé";
    }

}
