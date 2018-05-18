package com.tp.server_back.controllers;

import com.tp.server_back.controllers.dtos.GraphDto;
import com.tp.server_back.controllers.dtos.LabelDto;
import com.tp.server_back.entities.Graph;
import com.tp.server_back.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ybunetel on 15/05/2018.
 */
@RestController
public class GraphController {

    @Autowired
    GraphService graphService;

    @GetMapping(value = "/graph")
    public List<GraphDto> getGraphs() throws IOException {
        return graphService.findAll().stream().map(GraphDto:: new).collect(Collectors.toList());
    }

    @GetMapping(value="/graph/{id}")
    public GraphDto getOneGraphById(@PathVariable("id") long id){
        return new GraphDto(graphService.findOne(id));
    }

    @PostMapping(value="/graph")
    public void persistGraph(@RequestBody GraphDto graphDto){

        Graph graph = new Graph();
        graph.setId(graphDto.getId());
        graph.setStartTime(graphDto.getStartTime());
        graph.setEndTime(graphDto.getEndTime());
        graphService.save(graph);
    }

}
