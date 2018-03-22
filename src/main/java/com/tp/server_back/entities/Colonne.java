package com.tp.server_back.entities;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
public class Colonne implements Serializable {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="label")
    private String label;
    @Column(name="data")
    private String[] datas;

}
