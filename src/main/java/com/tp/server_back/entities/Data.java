package com.tp.server_back.entities;

import javax.persistence.*;

@Table
@Entity
public class Data {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name="value")
    private String value;
    @Column(name="time")
    private Double time;
}
