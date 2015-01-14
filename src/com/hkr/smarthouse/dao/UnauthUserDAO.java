package com.hkr.smarthouse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hkr.smarthouse.json.RegisterUser;
import com.hkr.smarthouse.models.UnauthorizedUser;
import com.hkr.smarthouse.session.HUtil;

public class UnauthUserDAO {

	public UnauthUserDAO() {
		// TODO Auto-generated constructor stub
	}

	public String addUnauthUser(RegisterUser newUser) {		
		boolean success = false;
		UnauthorizedUser temp;
		try {
			Session session = HUtil.getSessionFactory().openSession();
			session.beginTransaction();
			temp = new UnauthorizedUser();
			temp.setAdmin(false);
			temp.setEmail(newUser.getEmail());
			temp.setFirst_name(newUser.getFirst_name());
			temp.setLast_name(newUser.getLast_name());
			temp.setPassword(newUser.getPassword());
			temp.setSsn(newUser.getSsn());
			session.save(temp);
			session.getTransaction().commit();
			success = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
        return success ? "{ \"registered\": true }" : "{ \"registered\": false }";
	}
	
	public UnauthorizedUser[] getAllUnAuthUsers() {
		Session session = HUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM UnauthorizedUser");
		UnauthorizedUser[] rooms;
		@SuppressWarnings("unchecked")
		List<UnauthorizedUser> resultList = q.list();
		rooms = new UnauthorizedUser[resultList.size()];
		System.out.println("num of rooms:" + resultList.size());
		for (int i = 0; i < resultList.size(); i++) {
			rooms[i] = resultList.get(i);
		}
		session.getTransaction().commit();
		return rooms;
	}
}
