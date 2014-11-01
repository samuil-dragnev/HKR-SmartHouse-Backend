package com.hkr.smarthouse.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.hkr.smarthouse.models.Device;
import com.hkr.smarthouse.models.Room;
@Path("/house")
public class HouseResources {

	@GET
	@Path("/rooms")
	@Produces("application/json")
	public Room getIt(){
		Room rm = new Room();
		ArrayList<Device> devices = new ArrayList<Device>();
		Device dev = new Device();
		devices.add(dev);
		devices.add(dev);
		rm.setDevices(devices);
		rm.setId(0);
		rm.setName("opas");
		return rm;
	}
}
