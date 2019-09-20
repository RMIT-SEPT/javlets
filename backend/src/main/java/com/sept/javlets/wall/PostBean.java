package com.sept.javlets.wall;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sept.javlets.userauth.StudentAccountBean;

@JsonIgnoreProperties(ignoreUnknown=true)
@Document(collection="posts")
public class PostBean {

	//TODO: Implement
//	private PrivacySetting privacy;
	
	@Id
	private String id;
	
	private String type;
	private String title;
	private String body;
	private StudentAccountBean authorAccount;
	
	public PostBean() {
		
	}
	
	public PostBean(String type, String title, String body, StudentAccountBean authorAccount) {
		this.type = type;
		this.title = title;
		this.body = body;
		this.authorAccount = authorAccount;

//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?
	}

	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StudentAccountBean getAuthorAccount() {
		return authorAccount;
	}

	public void setAuthor(StudentAccountBean authorAccount) {
		this.authorAccount = authorAccount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return String.format("ID: %s, Author: %s, Title: %s, Body: %s, Type: %s%n", id, authorAccount.getUsername(), title, body, type);
	}

	//TODO: Implement
//	public PrivacySetting getPrivacy() {
//		return privacy;
//	}
//
//	public void setPrivacy(PrivacySetting privacy) {
//		this.privacy = privacy;
//	}
	
	
	
}
