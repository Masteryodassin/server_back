package com.tp.server_back.entities;

import javax.persistence.*;

@Entity
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String selectedData;
}
