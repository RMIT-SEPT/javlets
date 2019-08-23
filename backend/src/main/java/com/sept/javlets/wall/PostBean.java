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

	private String postContent;
//	private StudentAccountBean author;
	private LocalDateTime date;
//	private PrivacySetting privacy; // To be implemented later
	private String type;
	private String title;
	private String body;
	private String author;
	private Date date2;
	private long id;
	
	public PostBean(StudentAccountBean author, String postContent) {
		this.postContent = postContent;
//		this.author = author;
		
		this.date = LocalDateTime.now();
//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?
	}

	public PostBean(String type, String title, String body, String author, long id) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.title = title;
		this.body = body;
		this.author = author;
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

	public Long getID() {
		return id;
	}

//	public String getPostContent() {
//		return postContent;
//	}
//
//	public void setPostContent(String postContent) {
//		this.postContent = postContent;
//	}
//
//	public StudentAccountBean getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(StudentAccountBean author) {
//		this.author = author;
//	}
//
//	public LocalDateTime getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDateTime date) {
//		this.date = date;
//	}
//
//	public PrivacySetting getPrivacy() {
//		return privacy;
//	}
//
//	public void setPrivacy(PrivacySetting privacy) {
//		this.privacy = privacy;
//	}
//	
//	
//	
}
