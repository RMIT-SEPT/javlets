package com.sept.javlets.userauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class AccountBean {

	@Id
	private String id;
	
	private String email;
	private String givenName;
	private String familyName;
	private String googleID;
	private String imageUrl;
	private String username;
	private boolean isMentor;
	
	private List<AccountBean> connections;
	
	public AccountBean() {
		//initialise account
	}
	
	public AccountBean(String username) {
		this.username = username;
		this.connections = new ArrayList<AccountBean>();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGoogleID() {
		return googleID;
	}

	public void setGoogleID(String googleID) {
		this.googleID = googleID;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public List<AccountBean> getConnections() {
		return connections;
	}

	public void addConnection(AccountBean connection) {
		connections.add(connection);
	}
	
	public boolean removeConnection(AccountBean connection) {
		return connections.remove(connection);
	}

	public boolean isMentor() {
		return isMentor;
	}

	public void setMentor(boolean isMentor) {
		this.isMentor = isMentor;
	}
	
}
