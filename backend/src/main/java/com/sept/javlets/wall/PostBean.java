package com.sept.javlets.wall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sept.javlets.userauth.AccountBean;

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
	private AccountBean authorAccount;
	
	public PostBean() {
		
	}
	
	public PostBean(String type, String title, String body, AccountBean author) {
		this.type = type;
		this.title = title;
		this.body = body;
		this.authorAccount = author;

//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?

	}


    public String getType() {
        return type;
    }

	public AccountBean getAuthorAccount() {
		return authorAccount;
	}

	public void setAuthor(AccountBean authorAccount) {
		this.authorAccount = authorAccount;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return postId;
    }

    public void setId(Long id) {
        this.postId = id;
    }

    public String getUserId() {
        return userId;
    }

    public String toString() {
        return String.format("postID: %s, Author: %s, Title: %s, Body: %s, Type: %s%n, UserID: %s", postId, author, title, body, type, userId);
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
