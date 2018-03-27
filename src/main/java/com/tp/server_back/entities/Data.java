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
    private String time;

    @ManyToOne
    private Label label;



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Label getServer() {
        return label;
    }

    public void setServer(Label label) {
        this.label = label;
    }
}
