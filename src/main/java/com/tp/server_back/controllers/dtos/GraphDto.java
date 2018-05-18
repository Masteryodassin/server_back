package com.tp.server_back.controllers.dtos;

import com.tp.server_back.entities.Graph;
import com.tp.server_back.entities.Label;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ybunetel on 17/05/2018.
 */
public class GraphDto {


    private long id;

    private String startTime;

    private String endTime;

    private List<LabelDto> labelDtos;


    public GraphDto(Graph graph){
        this.id = graph.getId();
        this.startTime = graph.getStartTime();
        this.endTime = graph.getEndTime();
        this.labelDtos = graph.getLabels().stream().map(LabelDto::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<LabelDto> getLabelDtos() {
        return labelDtos;
    }

    public void setLabelDtos(List<LabelDto> labels) {
        this.labelDtos = labels;
    }
}
