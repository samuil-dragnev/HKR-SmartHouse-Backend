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
	@Column(name = "roomName", nullable = false)
	private String name;
	@Column(name= "isIndoors", nullable = false)
	private boolean isIndoors;
	
	@OneToMany(mappedBy = "room", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Device> devices = new ArrayList<Device>();
	
	public Room(){}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public List<Device> getDevices() { return devices; }
	public void setDevices(List<Device> devices) { this.devices = devices; }
	public boolean isIndoors() { return isIndoors; }
	public void setIndoors(boolean isIndoors) { this.isIndoors = isIndoors; }
}