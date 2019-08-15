package com.sept.javlets.userauth;

import java.util.ArrayList;
import java.util.List;

public class StudentAccountBean {

	private String email;
	private List<StudentAccountBean> connections;
	
	public StudentAccountBean(String email) {
		this.email = email;
		this.connections = new ArrayList<StudentAccountBean>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
