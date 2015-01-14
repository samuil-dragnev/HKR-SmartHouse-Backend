package com.hkr.smarthouse.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.hkr.smarthouse.dao.DeviceDAO;
import com.hkr.smarthouse.dao.RoomDAO;
import com.hkr.smarthouse.dao.UnauthUserDAO;
import com.hkr.smarthouse.dao.UserDAO;
import com.hkr.smarthouse.json.AddDevice;
import com.hkr.smarthouse.json.AddRoom;
import com.hkr.smarthouse.json.Authentication;
import com.hkr.smarthouse.json.AuthorizeUser;
import com.hkr.smarthouse.json.RegisterUser;
import com.hkr.smarthouse.json.ToggleDevice;
import com.hkr.smarthouse.models.Device;
import com.hkr.smarthouse.models.Room;
import com.hkr.smarthouse.models.UnauthorizedUser;
import com.hkr.smarthouse.models.User;

@Path("/house")
public class HouseResources {

	private RoomDAO roomDAO = new RoomDAO();
	private DeviceDAO deviceDAO = new DeviceDAO();
	private UserDAO userDAO = new UserDAO();
	private UnauthUserDAO unAuthUser = new UnauthUserDAO();

	@POST
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String authenticateUser(Authentication auth ) {
		return userDAO.login(auth.getSsn(), auth.getPassword());
	}

	@GET
	@Path("/rooms")
	@Produces(MediaType.APPLICATION_JSON)
	public Room[] getRoomsAndDevices(@QueryParam("houseId") int houseId) {
		return roomDAO.getRooms();
	}

	@POST
	@Path("/device")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Device changeDeviceState(ToggleDevice toggle) {
		return deviceDAO.changeDeviceState(toggle.getId(), toggle.isState());
	}

	@POST
	@Path("/rooms/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Room addRoom(AddRoom room) {
		return roomDAO.addRoom(room.getName(), room.isIndoors());
	}
	
	@POST
	@Path("/rooms/devices/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Device addDevice(AddDevice device) {
		return deviceDAO.addDevice(device.getRoomId(), device.getName());
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(RegisterUser newUser) {
		return unAuthUser.addUnauthUser(newUser);
	}
	
	@GET
	@Path("/user/{ssn}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("ssn") String ssn) {
		return userDAO.getUser(ssn);
	}
	
	@GET
	@Path("/unauth")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UnauthorizedUser[] getUnAuthUser() {
		return unAuthUser.getAllUnAuthUsers();
	}
	
	@POST
	@Path("/authorize")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String authorizeUnauthUser(AuthorizeUser user) {
		return userDAO.authorizaUser(user.getSsn(), user.getIds());
	}

	@POST
	@Path("/authorize/{unauthUserSSN}")
	public String authorizeUser(@PathParam("unauthUserSSN") String ssn) {
		return null;
	}
}
