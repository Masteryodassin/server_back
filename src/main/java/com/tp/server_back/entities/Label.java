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
    @ManyToOne
    @JoinColumn(name = "id_Server")
    private Server server;



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

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }


}
