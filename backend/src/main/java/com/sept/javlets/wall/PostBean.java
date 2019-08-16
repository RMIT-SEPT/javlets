package com.sept.javlets.wall;

import java.time.LocalDateTime;
import com.sept.javlets.userauth.StudentAccountBean;

public class PostBean {

	private String postContent;
	private StudentAccountBean author;
	private LocalDateTime date;
	private PrivacySetting privacy; // To be implemented later
	
	public PostBean(StudentAccountBean author, String postContent) {
		this.postContent = postContent;
		this.author = author;
		
		this.date = LocalDateTime.now();
		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?
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

	public PrivacySetting getPrivacy() {
		return privacy;
	}

	public void setPrivacy(PrivacySetting privacy) {
		this.privacy = privacy;
	}
	
	
	
}
