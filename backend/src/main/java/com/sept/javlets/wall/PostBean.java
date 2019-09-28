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
	
	private long postId;
	private String type;
	private String title;
	private String body;
    private AccountBean author;
    private String msgAuthor;
    private String userId;
    private String category;
    private String selectDate;
    //TODO: Implement
//	private PrivacySetting privacy;

    public PostBean(String type, String title, String body, AccountBean author, String msgAuthor, long postId, String userId, String category, String selectDate) {
		this.type = type;
		this.title = title;
		this.body = body;
        this.author = author;
        this.msgAuthor = msgAuthor;
        this.postId = postId;
        this.userId = userId;
        this.category = category;
        this.selectDate = selectDate;

//		this.privacy = PrivacySetting.PUBLIC; // Default privacy setting?

	}

    public String getType() {
        return type;
    }

	public AccountBean getAuthor() {
		return author;
	}

	public void setAuthor(AccountBean authorAccount) {
		this.author = authorAccount;
    }
    
    public String getMsgAuthor(){
        return msgAuthor;
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

    public String getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public String toString() {
        return String.format("postID: %s, Author: %s, Title: %s, Body: %s, Type: %s%n", postId, msgAuthor, title, body, type);
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
