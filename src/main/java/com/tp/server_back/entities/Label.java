package com.tp.server_back.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name ="label")
@Entity
public class Label implements Serializable {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name= "data")
    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL)
    private List<Data> datas;
    @ManyToOne
    private Server server;


    @Transient
    private int indexColumn;


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


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public int getIndexColumn() {
        return indexColumn;
    }

    public void setIndexColumn(int indexColumn) {
        this.indexColumn = indexColumn;
    }
}
