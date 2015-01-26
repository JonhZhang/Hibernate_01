package com.sin.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.sin.util.HibernateUtil;

public class TestManayToMany {
	
	@Test
	public void testAdd() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Admin admin = new Admin();
			admin.setName("zhangsan");
			session.save(admin);
			
			Admin admin2 = new Admin();
			admin2.setName("lisi");
			session.save(admin2);
			
			Role role = new Role();
			role.setName("管理员");
			Set<Admin> admins = new HashSet<Admin>();
			admins.add(admin);
			role.setAdmins(admins);
			session.save(role);
			
			Role role2 = new Role();
			role2.setName("保管员");
			Set<Admin> admins2 = new HashSet<Admin>();
			admins2.add(admin);
			admins2.add(admin2);
			role2.setAdmins(admins2);
			session.save(role2);
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(null != session) {
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
		}
		
		
		
	}
	
}
