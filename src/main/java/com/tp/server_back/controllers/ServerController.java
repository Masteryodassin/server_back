package com.tp.server_back.controllers;

import com.tp.server_back.entities.Server;
import com.tp.server_back.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController(value = "/server")
public class ServerController {

    @Autowired
    ServerService serverService;

    /**
     * Controller
     * @return List<Server>
     * @throws IOException
     */
    @GetMapping
    public List<Server> getServers() throws IOException{
        return serverService.findAll();
    }

    @GetMapping(value="/{id}")
    public Server getOneServer(){
        return  null;
    }
}
