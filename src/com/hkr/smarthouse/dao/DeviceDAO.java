package com.hkr.smarthouse.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.models.Device;
import com.hkr.smarthouse.models.Room;
import com.hkr.smarthouse.session.HUtil;

public class DeviceDAO {

	public DeviceDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Device addDevice(int roomId, String deviceName) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Room roomTemp = (Room) session.load(Room.class, roomId);
		Device temp = new Device();
		temp.setName(deviceName);
		temp.setState(false);
		if (roomTemp.getDevices() != null) {
			roomTemp.getDevices().add(temp);
		} else {
			List<Device> devices = new ArrayList<Device>();
			roomTemp.setDevices(devices);
			roomTemp.getDevices().add(temp);
		}
		
		session.save(temp);
		session.save(roomTemp);
        session.getTransaction().commit();
        return temp;
	}
	
	public Room getRoomByID(int id) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Room room = (Room) session.createQuery("FROM Room WHERE id IN (SELECT room.id FROM Device WHERE id = :id)")
				.setInteger("id", id)
		 		.uniqueResult();
		session.getTransaction().commit();
		return room;
	}

	public Device getDeviceByID(int id) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Device device = (Device) session.createQuery("FROM Device WHERE id = :id")
				.setInteger("id", id)
				.uniqueResult();
		session.getTransaction().commit();
		return device;
	}
	
	public Device changeDeviceState(int id, boolean state) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("UPDATE Device SET state = :state WHERE id = :id");
		q.setInteger("id", id);
		System.out.println(state);
		q.setBoolean("state", state);
		int result = q.executeUpdate();
		System.out.println(result);
		session.getTransaction().commit();
		if (result == 1) {
			return getDeviceByID(id);
		} else {
			return getDeviceByID(id);
		}
	}
}
