package com.tp.server_back.controllers;

import com.tp.server_back.controllers.dtos.LabelDto;

import com.tp.server_back.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LabelController {

    @Autowired
    LabelService labelService;

    @GetMapping(value = "label")
    public List<LabelDto> getLabels() throws IOException {
        return labelService.findAll().stream().map(LabelDto:: new).collect(Collectors.toList());
    }

    @GetMapping(value="label/{id}")
    public LabelDto getOneLabel(@PathVariable("id") long id){
        return new LabelDto(labelService.findOne(id));
    }
}
