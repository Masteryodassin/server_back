package com.tp.server_back.controllers.dtos;

import com.tp.server_back.entities.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerDto {

    private int id;
    private String name;

    public ServerDto(Server server){
        this.id = server.getId();
        this.name = server.getName();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
