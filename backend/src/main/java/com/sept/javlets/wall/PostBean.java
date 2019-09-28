package com.sept.javlets.wall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sept.javlets.userauth.AccountBean;

@JsonIgnoreProperties(ignoreUnknown=true)
@Document(collection="posts")
public class PostBean {
	
	@Id
	private String mongoId;
	
	// DateTime information stored as a long
	private long postId;
	
	// Type of post (Student or Mentor)
	private String type;
	
	// Post title and body
	private String title;
	private String body;
	
	// Account associated with the post -- **contains all user information**
    private AccountBean author;
    
    // Post category - either wallpost or livestream
    private String category;
    
    //TODO: Implement
//	private PrivacySetting privacy;

    public PostBean() {}
    
    public PostBean(String type, String title, String body, AccountBean author, long postId, String category) {
		this.type = type;
		this.title = title;
		this.body = body;
        this.author = author;
        this.postId = postId;
        this.category = category;

//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?
	}

	public String getType() {
        return type;
    }
	
	public void setType(String type) {
		this.type = type;
	}

	public AccountBean getAuthor() {
		return author;
	}

	public void setAuthor(AccountBean authorAccount) {
		this.author = authorAccount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return postId;
    }

    public void setId(Long id) {
        this.postId = id;
    }
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public String toString() {
        return String.format("postID: %s, Author: %s, Title: %s, Body: %s, Type: %s%n", postId, author.getUsername(), title, body, type);
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
