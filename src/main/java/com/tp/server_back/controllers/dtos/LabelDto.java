package com.tp.server_back.controllers.dtos;


import com.tp.server_back.entities.Label;

public class LabelDto {

    private long id;
    private String name;

    public LabelDto(Label label){
        this.id = label.getId();
        this.name = label.getName();
    }



    public long getId() {
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
