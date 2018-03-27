package com.tp.server_back.entities;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Table
@Entity
public class Server implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@OneToMany(mappedBy="server",cascade = CascadeType.ALL)
	private List<Label> labels;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
}
