package com.sin.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.sin.util.HibernateUtil;

public class TestHibernate {
	

	@Test
	public void test() {
		
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
				applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		
		Session session = null;
		try{
			session = factory.openSession();
			session.beginTransaction();
			User user = new User("zhangsan","123321", "的", new Date());
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(null != session) session.getTransaction().rollback();
		}finally {
			if(null != session) session.close();
		}
		
	}
	
	@Test
	public void update() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = (User) session.load(User.class, 2);
			user.setUsername("lisi");
			session.update(user);
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
	
	
	@Test
	public void testManyToOne() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Classroom classroom = new Classroom();
			classroom.setName("class_01");
			classroom.setGrade(1);
			session.save(classroom);
			Student student = new Student();
			student.setName("zhangsan");
			student.setNo("001");
			student.setClassroom(classroom);
			
			Student student2 = new Student();
			student2.setName("lisi");
			student2.setNo("002");
			student2.setClassroom(classroom);
			session.save(student);
			session.save(student2);
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
	
	
	
	
	@Test
	public void testOneToMany() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Comment comment = new Comment();
			comment.setContent("ccc");
			Comment comment2 = new Comment();
			comment2.setContent("ccc2");
			session.save(comment);
			session.save(comment2);
			
			Message message  = new Message();
			message.setTitle("tt");
			message.setContent("ccccc");
			message.addComment(comment);
			message.addComment(comment2);
			
			session.save(message);
			
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

	
	
	
	
	@Test
	public void testOneToManyShuang() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			
			Student student = new Student();
			student.setName("zhangsan");
			student.setNo("001");
			
			
			Student student2 = new Student();
			student2.setName("lisi");
			student2.setNo("002");
			
			
			
			Classroom classroom = new Classroom();
			classroom.setName("一班");
			classroom.getStus().add(student);
			classroom.getStus().add(student2);
			student.setClassroom(classroom);
			student2.setClassroom(classroom);
			session.save(classroom);
			
			
			
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
	
	
	
	@Test
	public void testManyToOneLoad() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Classroom classroom =  (Classroom) session.load(Classroom.class,1);
			System.out.println(classroom);
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
