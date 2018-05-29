package com.tp.server_back.controllers.dtos;

import com.tp.server_back.entities.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ServerDto {


    private long id;
    private String name;
    private List<Long> labelId = new ArrayList<Long>();

    public ServerDto(Server server){
        this.id = server.getId();
        this.name = server.getName();
        this.labelId = server.getLabels().stream()
                            .map(label -> label.getId())
                            .collect(Collectors.toList());

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

    public List<Long> getLabelId() {
        return labelId;
    }

    public void setLabelId(List<Long> labelId) {
        this.labelId = labelId;
    }
}
