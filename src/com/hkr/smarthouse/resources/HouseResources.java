package com.hkr.smarthouse.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.db.HUtil;
import com.hkr.smarthouse.models.Room;

@Path("/house")
public class HouseResources {

	@GET
	@Path("/rooms")
	@Produces("application/json")
	public Room[] getIt() {
		Room[] rooms;
		Session session = HUtil.getSessionFactory().openSession();

		Query q = session.createQuery("FROM Room");
		
		@SuppressWarnings("unchecked")
		List<Room> resultList = q.list();
		rooms = new Room[resultList.size()];
		System.out.println("num of employess:" + resultList.size());
		for (int i = 0; i < resultList.size(); i++) {
			rooms[i] = resultList.get(i);
		}

		return rooms;
	}
}
