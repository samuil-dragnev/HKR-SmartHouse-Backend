package com.hkr.smarthouse.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Rooms")
@XmlRootElement
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomId", unique = true, nullable = false)
	private int id;
	@Column(name = "roomName")
	private String name;
//	@OneToMany
//	private List<Device> devices = new ArrayList<Device>();
//	public List<Device> getDevices() { return devices; }
//	public void setDevices(List<Device> devices) { this.devices = devices; }
	public Room(){}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
}
