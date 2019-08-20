package com.sept.javlets.userauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	private Map<String, StudentAccountBean> accounts;
	
	public AccountController() {
		this.accounts = new HashMap<String, StudentAccountBean>();
	}
	
	public StudentAccountBean registerUser(String username) {
		if (!accounts.containsKey(username)) {
			accounts.put(username, new StudentAccountBean(username));
		}
		return accounts.get(username);
	}
	
	public StudentAccountBean getUser(String username) {
		return accounts.get(username);
	}
	
}
