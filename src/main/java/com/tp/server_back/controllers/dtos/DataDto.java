package com.tp.server_back.controllers.dtos;

import com.tp.server_back.entities.Data;

import java.util.Map;

public class DataDto {

    private String name;
    private Map<String,Double> data;

    public DataDto (String name, long labelId){

        Data data;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setDatasMap(Map<String, Double> data) {
        this.data = data;
    }

}
