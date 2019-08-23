package com.sept.javlets.wall;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.sept.javlets.userauth.StudentAccountBean;

public class PostBean {

	private String postType;
	private String title;
	private String postContent;
	private StudentAccountBean author;
	private LocalDateTime date;
	// private PrivacySetting privacy; // To be implemented later
	
	public PostBean() {
		
	}
	
	public PostBean(String postType, String title, String postContent, StudentAccountBean author) {
		this.postType = postType;
		this.title = title;
		this.postContent = postContent;
		this.author = author;
		
		this.date = LocalDateTime.now();
		// this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public StudentAccountBean getAuthor() {
		return author;
	}

	public void setAuthor(StudentAccountBean author) {
		this.author = author;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

//	public PrivacySetting getPrivacy() {
//		return privacy;
//	}

//	public void setPrivacy(PrivacySetting privacy) {
//		this.privacy = privacy;
//	}
	
	
	
}
