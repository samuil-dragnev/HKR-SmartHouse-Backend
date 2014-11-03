package com.hkr.smarthouse.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hkr.smarthouse.dao.*;
import com.hkr.smarthouse.models.*;

@Path("/house")
public class HouseResources {

	private RoomDAO roomDAO = new RoomDAO();
	private DeviceDAO deviceDAO = new DeviceDAO();

	@GET
	@Path("/rooms")
	@Produces(MediaType.APPLICATION_JSON)
	public Room[] getRooms() {
		return roomDAO.getRooms();
	}

	@GET
	@Path("/rooms/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Room getRoom(@PathParam("id") int id) {
		return roomDAO.getRoomByID(id);
	}
	
	@GET
	@Path("/devices/{id}/room")
	@Produces(MediaType.APPLICATION_JSON)
	public Room getDeviceRoom(@PathParam("id") int id) {
		return deviceDAO.getRoomByID(id);
	}
	
	@GET
	@Path("/devices/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Device getDivice(@PathParam("id") int id) {
		return deviceDAO.getDeviceByID(id);
	}
	
	@GET
	@Path("/devices")
	@Produces(MediaType.APPLICATION_JSON)
	public String changeDeviceStatus(
			@QueryParam("id") int id,
			@QueryParam("state") boolean state) {
		return "{ \"updateSuccess\":"+deviceDAO.changeDeviceState(id, state)+"}";
	}
}
