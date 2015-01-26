package com.sin.model;

import java.util.HashSet;
import java.util.Set;

public class Classroom {
	private int id;
	private String name;
	private int grade;
	private Set<Student> stus = new HashSet<Student>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", grade=" + grade
				+ ", stus=" + stus + "]";
	}
	
	
}
