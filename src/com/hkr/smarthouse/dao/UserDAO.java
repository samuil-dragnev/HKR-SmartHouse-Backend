package com.hkr.smarthouse.dao;

import org.hibernate.Session;

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
			System.out.println("ofasp");
			return "{\"authenticate\": true}"; 
		} else {
			return "{\"authenticate\": false}";
		}
	}
	
	public String getUser(String ssn) {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object[] result = (Object[]) session.createQuery("SELECT first_name, last_name FROM User WHERE ssn=:ssn")
				.setString("ssn", ssn)
				.uniqueResult();
		session.getTransaction().commit();
		return "{ \"first_name\": \"" + (String) result[0] + "\"," +
			"	\"last_name\": \"" + (String) result[1] + "\" }";
	}

}
