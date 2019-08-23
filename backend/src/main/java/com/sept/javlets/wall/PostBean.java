package com.sept.javlets.wall;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sept.javlets.userauth.StudentAccountBean;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PostBean {
	
//	ObjectMapper mapper = new ObjectMapper();
//	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	private String postType;
//	private String title;
	private String postContent;
	private StudentAccountBean authorAccount;
	private LocalDateTime date;

//	private PrivacySetting privacy; // To be implemented later
	private String type;
	private String title;
	private String body;
	private String author;
	private long id;

	
	public PostBean() {
		
	}
	
	public PostBean(String postType, String title, String postContent, StudentAccountBean authorAccount, long id) {
		this.postType = postType;
		this.title = title;
		this.postContent = postContent;
		this.authorAccount = authorAccount;
		
		this.id = id;

//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?

	}

	public PostBean(String type, String title, String body, String author, long id) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.title = title;
		this.body = body;
		this.author = author;
//		this.id = Long.parseLong(id);
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public String getTitle() {
		return title;
	}
	public String getBody() {
		return body;
	}
	public String getAuthor() {
		return author;
	}
	
	
	

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
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


//	public PrivacySetting getPrivacy() {
//		return privacy;
//	}
//
//	public void setPrivacy(PrivacySetting privacy) {
//		this.privacy = privacy;
//	}
	
	
	
}
