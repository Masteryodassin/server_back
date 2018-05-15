package com.tp.server_back.controllers;

import com.tp.server_back.entities.Graph;
import com.tp.server_back.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by ybunetel on 15/05/2018.
 */
@Controller
public class GraphController {

    @Autowired
    GraphService graphService;

    @GetMapping(value = "/graph")
    public List<Graph> getGraphs() throws IOException {
        return graphService.findAll();
    }

    @GetMapping(value="/graph/{id}")
    public Graph getOneGraphById(@PathVariable("id") long id){
        return (graphService.findOne(id));
    }

    @PostMapping(value="/graph")
    public void persistGraph(@RequestBody Graph graph){
        graphService.save(graph);
    }

}
