package com.hkr.smarthouse.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.models.Device;
import com.hkr.smarthouse.models.Room;
import com.hkr.smarthouse.session.HUtil;

public class DeviceDAO {

	public DeviceDAO() {
		// TODO Auto-generated constructor stub
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
	
	public boolean changeDeviceState(int id, boolean state) {
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
			return true;
		} else {
			return false;
		}
	}
}
