package com.hkr.smarthouse.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.models.Room;
import com.hkr.smarthouse.models.UnauthorizedUser;
import com.hkr.smarthouse.models.User;
import com.hkr.smarthouse.session.HUtil;

public class UserDAO {

	public UserDAO() {}
	
	public String login(String ssn, String password) {
		
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.createQuery("FROM User WHERE ssn=:ssn AND password = :password")
				.setString("ssn", ssn)
				.setString("password", password)
				.uniqueResult();
		session.getTransaction().commit();
		if (user != null) {
			return "{\"authenticate\": true }"; 
		} else {
			return "{\"authenticate\": false}";
		}
	}
	
	public User getUser(String ssn) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM User WHERE ssn=:ssn");
		q.setString("ssn", ssn);
		User user = (User) q.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	public String authorizaUser(String ssn, int[] ids) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		UnauthorizedUser temp = (UnauthorizedUser) session.load(UnauthorizedUser.class, ssn);
		User user = new User();
		user.setAdmin(false);
		user.setEmail(temp.getEmail());
		user.setFirst_name(temp.getFirst_name());
		user.setLast_name(temp.getLast_name());
		user.setPassword(temp.getPassword());
		user.setSsn(temp.getSsn());
		session.save(user);
        session.getTransaction().commit();
        return "{ \"authorized\": true }";
	}
}
