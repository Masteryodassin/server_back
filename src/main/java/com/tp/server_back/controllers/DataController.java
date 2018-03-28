package com.tp.server_back.controllers;

import com.tp.server_back.entities.Data;
import com.tp.server_back.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/data")
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping(value="/{serverId}{labelId}")
    public List<Data> dataList(@PathVariable("serverId") long serverId, @PathVariable("labelId") long labelId) {
        return dataService.getDatasByLabelandServerId(serverId, labelId);

    }

}
