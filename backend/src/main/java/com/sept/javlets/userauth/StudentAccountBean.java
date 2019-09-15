package com.sept.javlets.userauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class StudentAccountBean {
	
	@Id
	private String id;
	
	private String username;
	private List<StudentAccountBean> connections;
	
	public StudentAccountBean(String username) {
		this.username = username;
		this.connections = new ArrayList<StudentAccountBean>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<StudentAccountBean> getConnections() {
		return connections;
	}

	public void addConnection(StudentAccountBean connection) {
		connections.add(connection);
	}
	
	public boolean removeConnection(StudentAccountBean connection) {
		return connections.remove(connection);
	}

	
}
