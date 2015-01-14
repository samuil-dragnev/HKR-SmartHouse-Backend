package com.hkr.smarthouse.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.models.Room;
import com.hkr.smarthouse.session.HUtil;

public class RoomDAO {

	public RoomDAO() {
		
	}
	
	public Room[] getRooms() {
		Room[] rooms;
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Room");
		
		@SuppressWarnings("unchecked")
		List<Room> resultList = q.list();
		rooms = new Room[resultList.size()];
		System.out.println("num of rooms:" + resultList.size());
		for (int i = 0; i < resultList.size(); i++) {
			rooms[i] = resultList.get(i);
		}
		session.getTransaction().commit();
		return rooms;
	}
	
	public Room addRoom(String name, boolean isIndoors) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Room temp = new Room();
		temp.setName(name);
		temp.setIndoors(isIndoors);
		session.save(temp);
        session.getTransaction().commit();
        return temp;
	}
	
	public Room getRoomByID(int id) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Room WHERE roomId=:id");
		q.setInteger("id", id);
		Room room = (Room) q.uniqueResult();
		session.getTransaction().commit();
		return room;
	}

}
