package com.sin.model;

import java.util.HashSet;
import java.util.Set;

public class Message {
	
	private int id;
	
	private String title;
	
	private String content;
	
	private Set<Comment> Comments;

	
	
	public Message() {
		Comments = new HashSet<Comment>();
	}
	
	public void addComment(Comment comment) {
		Comments.add(comment);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return Comments;
	}

	public void setComments(Set<Comment> comments) {
		Comments = comments;
	}
}
