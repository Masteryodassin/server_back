package com.tp.server_back.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table
@Entity
public class Label implements Serializable {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name= "data")
    @OneToMany(mappedBy = "label")
    private List<Data> datas;


}
