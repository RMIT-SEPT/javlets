package com.sept.javlets.userauth;

import java.util.ArrayList;
import java.util.List;

public class StudentAccountBean {

	private String username;
	private List<StudentAccountBean> connections;
	
	public StudentAccountBean(String username) {
		this.username = username;
		this.connections = new ArrayList<StudentAccountBean>();
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
