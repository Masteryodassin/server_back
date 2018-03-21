package com.tp.server_back.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class ServerInfo implements Serializable{
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="time")
	private Double time;
	@Column(name="humantime")
	private Date humantime;
	@Column(name="traffic_in")
	private Double traffic_in;
	@Column(name="traffic_out")
	private Double traffic_out;
	@Column(name="memory_used")
	private Double memory_used;
	@Column(name="security_error")
	private Float security_error;
	@Column(name="cpu0")
	private float cpu0;
	@Column(name="cpu1")
	private Float cpu1;
	@Column(name="cpu2")
	private Float cpu2;
	@Column(name="cpu3")
	private Float cpu3;
	@Column(name="cpu4")
	private Float cpu4;
	@Column(name="cpu5")
	private Float cpu5;
	@Column(name="cpu6")
	private Float cpu6;
	@Column(name="cpu7")
	private Float cpu7;
	@Column(name="cpu8")
	private Float cpu8;
	@Column(name="disk_used")
	private Double disk_used;
	@ManyToOne
	@JoinColumn(name = "id_Server")
	private Server server;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public Date getHumantme() {
		return humantime;
	}
	public void setHumantime(Date humantime) {
		this.humantime = humantime;
	}
	public double getTraffic_in() {
		return traffic_in;
	}
	public void setTraffic_in(double traffic_in) {
		this.traffic_in = traffic_in;
	}
	public double getTraffic_out() {
		return traffic_out;
	}
	public void setTraffic_out(double traffic_out) {
		this.traffic_out = traffic_out;
	}
	public double getMemory_used() {
		return memory_used;
	}
	public void setMemory_used(double memory_used) {
		this.memory_used = memory_used;
	}
	public float getSecurity_error() {
		return security_error;
	}
	public void setSecurity_error(float security_error) {
		this.security_error = security_error;
	}
	public float getCpu0() {
		return cpu0;
	}
	public void setCpu0(float cpu0) {
		this.cpu0 = cpu0;
	}
	public float getCpu1() {
		return cpu1;
	}
	public void setCpu1(float cpu1) {
		this.cpu1 = cpu1;
	}
	public float getCpu2() {
		return cpu2;
	}
	public void setCpu2(float cpu2) {
		this.cpu2 = cpu2;
	}
	public float getCpu3() {
		return cpu3;
	}
	public void setCpu3(float cpu3) {
		this.cpu3 = cpu3;
	}
	public float getCpu4() {
		return cpu4;
	}
	public void setCpu4(float cpu4) {
		this.cpu4 = cpu4;
	}
	public float getCpu5() {
		return cpu5;
	}
	public void setCpu5(float cpu5) {
		this.cpu5 = cpu5;
	}
	public float getCpu6() {
		return cpu6;
	}
	public void setCpu6(float cpu6) {
		this.cpu6 = cpu6;
	}
	public float getCpu7() {
		return cpu7;
	}
	public void setCpu7(float cpu7) {
		this.cpu7 = cpu7;
	}
	public float getCpu8() {
		return cpu8;
	}
	public void setCpu8(float cpu8) {
		this.cpu8 = cpu8;
	}
	public double getDisk_used() {
		return disk_used;
	}
	public void setDisk_used(double disk_used) {
		this.disk_used = disk_used;
	}
	
	
}
