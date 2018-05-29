package com.tp.server_back.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "graph")
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="start")
    private String startTime;
    @Column(name="end")
    private String endTime;

    @ManyToMany
    private List<Label> labels;

    public Graph(){}

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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

}
