package com.hkr.smarthouse.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Devices")
@XmlRootElement
public class Device implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deviceId", unique = true, nullable = false)
	private int id;
	@Column(name = "deviceName", nullable = false)
	private String name;
	@Column(name = "deviceState", nullable = false)
	private boolean state; 
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="room_id", nullable=false)
	private Room room;
	
	public Device(){}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public boolean isState() { return state; }
	public void setState(boolean state) { this.state = state; }

//	public Room getRoom() { return room; }
//	public void setRoom(Room room) { this.room = room; }
}