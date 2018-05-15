package com.tp.server_back.controllers;

import com.tp.server_back.controllers.dtos.ServerDto;
import com.tp.server_back.entities.Server;
import com.tp.server_back.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServerController {

    @Autowired
    ServerService serverService;

    /**
     * Controller
     * @return List<Server>
     * @throws IOException
     */
    @GetMapping(value= "/server")
    public List<ServerDto> getServers() throws IOException{
        return serverService.findAll().stream().map(ServerDto:: new).collect(Collectors.toList());
    }

    @GetMapping(value="/server/{id}")
    public ServerDto getOneServer(@PathVariable("id") long id){
        return  new ServerDto(serverService.findOne(id));
    }
}
