package com.tp.server_back.controllers.dtos;

import com.tp.server_back.entities.Server;


public class ServerDto {

    private long id;
    private String name;

    public ServerDto(Server server){
        this.id = server.getId();
        this.name = server.getName();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
