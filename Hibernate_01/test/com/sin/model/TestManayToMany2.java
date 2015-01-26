package com.sin.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.sin.util.HibernateUtil;

public class TestManayToMany2 {
	
	@Test
	public void testAdd() {
		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Course course = new Course();
			course.setName("政治");
			session.save(course);
			
			Course course2 = new Course();
			course2.setName("数学");
			session.save(course2);
			
			Course course3 = new Course();
			course3.setName("生物");
			session.save(course3);
			
			
			Teacher teacher = new Teacher();
			teacher.setName("zhangsan");
			session.save(teacher);
			
			Teacher teacher2 = new Teacher();
			teacher2.setName("lisi");
			session.save(teacher2);
			
			//张三 政治 50
			TeacherCourse tc = new TeacherCourse();
			tc.setAch(50);
			tc.setCourse(course);
			tc.setTeacher(teacher);
			
			//张三数学70
			TeacherCourse tc2 = new TeacherCourse();
			tc2.setAch(70);
			tc2.setCourse(course2);
			tc2.setTeacher(teacher);
			
			Set<TeacherCourse> tcs = new HashSet<TeacherCourse>();
			tcs.add(tc);
			tcs.add(tc2);
			teacher.setTcs(tcs);
			session.save(teacher);
			
			
			
			//李四 政治 90
			TeacherCourse tc3 = new TeacherCourse();
			tc3.setAch(90);
			tc3.setCourse(course);
			tc3.setTeacher(teacher2);
			//李四 生物 100
			TeacherCourse tc4 = new TeacherCourse();
			tc4.setAch(100);
			tc4.setCourse(course3);
			tc4.setTeacher(teacher2);
			
			Set<TeacherCourse> tcs2 = new HashSet<TeacherCourse>();
			tcs2.add(tc3);
			tcs2.add(tc4);
			teacher2.setTcs(tcs);
			session.save(teacher2);
			
			
			
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
