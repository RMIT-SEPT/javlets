package com.sept.javlets.wall;

import com.sept.javlets.userauth.AccountBean;

public class LivestreamPostBean extends PostBean {
    
    // Scheduled date for the livestream -- TODO: Store as a proper date format (or long)
    private String selectDate;
	
	public LivestreamPostBean(String type, String title, String body, AccountBean author, String msgAuthor, long postId, String category,
			String selectDate) {
		super(type, title, body, author, msgAuthor, postId, category);
		
		this.selectDate = selectDate;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

}
