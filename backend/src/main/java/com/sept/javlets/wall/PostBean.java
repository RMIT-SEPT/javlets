package com.sept.javlets.wall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sept.javlets.userauth.StudentAccountBean;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PostBean {

	//TODO: Implement
//	private PrivacySetting privacy;
	
	private String type;
	private String title;
	private String body;
	private StudentAccountBean authorAccount;
	private long id;

	
	public PostBean() {
		
	}
	
	public PostBean(String type, String title, String body, StudentAccountBean authorAccount, long id) {
		this.type = type;
		this.title = title;
		this.body = body;
		this.authorAccount = authorAccount;
		this.id = id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
