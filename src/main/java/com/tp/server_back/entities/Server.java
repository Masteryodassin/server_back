package com.tp.server_back.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class Server {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="memory_size")
	private Double memory_size;
	@Column(name="disk_size")
	private Double disk_size;
	@OneToMany(mappedBy="server")
	private List<ServerInfo> serverInfos;
	
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
	public double getMemory_size() {
		return memory_size;
	}
	public void setMemory_size(double memory_size) {
		this.memory_size = memory_size;
	}
	public double getDisk_size() {
		return disk_size;
	}
	public void setDisk_size(double disk_size) {
		this.disk_size = disk_size;
	}
	
	
}
