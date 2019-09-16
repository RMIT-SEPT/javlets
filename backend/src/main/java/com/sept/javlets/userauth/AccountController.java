package com.sept.javlets.userauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AccountController {

	private StudentAccountBean studentAccount;
	private HashMap<String, StudentAccountBean> accounts;
	
	
	public AccountController() {
		this.accounts = new HashMap<String, StudentAccountBean>();
	}
	
	public StudentAccountBean registerUser(HashMap<String,String> loginInfo) {
		if (!accounts.containsKey(loginInfo.get("name"))) {
			
			studentAccount = new StudentAccountBean(loginInfo.get("name"));
			studentAccount.setEmail(loginInfo.get("email"));
			studentAccount.setImageUrl(loginInfo.get("imageUrl"));
			
			accounts.put(loginInfo.get("name"), studentAccount);
		}
		
		return accounts.get(loginInfo.get("name"));
	}
	
	public StudentAccountBean registerUser(String username) {
		if (!accounts.containsKey(username)) {
			studentAccount = new StudentAccountBean(username);
			accounts.put(username, studentAccount);
		}
		return accounts.get(username);
	}
	
	public boolean removeUser(String username) {
		boolean removed = false;
		if (accounts.remove(username) != null) {
			removed = true;
		}
		return removed;
	}
	
	public StudentAccountBean getUser(String username) {
		return accounts.get(username);
	}
	
	public Map<String, StudentAccountBean> getAllAccounts() {
		return accounts;
	}
	
	public boolean validateID(String ID) {
		if(!ID.matches("(s|e)\\d{7}"))
			return false;
		return true;
	}
	
	@PostMapping(path="/login")
    public void login(@RequestBody HashMap<String,String> loginInfo) {
		String[] arrOfStr = loginInfo.get("email").split("@");
		String studentID = null;
		
		if(validateID(arrOfStr[0])) {
			studentID = arrOfStr[0];
			registerUser(loginInfo);
			System.out.println(studentID);
			
			if(!studentID.equals(null)) {
				System.out.printf("Log in with: %s", studentID);
			}
		} else {
			System.out.println("Incorrect student email/ID");
		}
		
	}
}
