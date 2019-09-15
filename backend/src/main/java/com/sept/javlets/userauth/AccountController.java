package com.sept.javlets.userauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.mongo.UserRepository;

@RestController
@RequestMapping("/user")
public class AccountController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentAccountBean add(String username) {
		StudentAccountBean user = new StudentAccountBean(username);
		return userRepository.save(user);
	}
	
	@GetMapping
	public StudentAccountBean getUser(String username) {
		return userRepository.findByUsername(username);
	}

//	private Map<String, StudentAccountBean> accounts;
//	
//	public AccountController() {
//		this.accounts = new HashMap<String, StudentAccountBean>();
//	}
//	
//	public StudentAccountBean registerUser(String username) {
//		if (!accounts.containsKey(username)) {
//			accounts.put(username, new StudentAccountBean(username));
//		}
//		return accounts.get(username);
//	}
//	
//	public boolean removeUser(String username) {
//		boolean removed = false;
//		if (accounts.remove(username) != null) {
//			removed = true;
//		}
//		return removed;
//	}
//	
//	public StudentAccountBean getUser(String username) {
//		return accounts.get(username);
//	}
//	
//	public Map<String, StudentAccountBean> getAllAccounts() {
//		return accounts;
//	}
	
}
