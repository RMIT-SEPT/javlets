package com.sept.javlets.userauth;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GAuthController {
	private StudentAccountBean studentAccount;
	
	public GAuthController() {
		this.studentAccount = new StudentAccountBean();
	}
	
	@PostMapping(path="/login")
    public void login(@RequestBody HashMap<String, String> loginInfo) {
		 studentAccount.setEmail(loginInfo.get("email"));
		 studentAccount.setUsername(loginInfo.get("name"));
		 studentAccount.setImageUrl(loginInfo.get("imageUrl"));
    }
}
