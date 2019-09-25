package com.sept.javlets.userauth;


import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.mongo.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentAccountBean add(@RequestParam String username) {
		StudentAccountBean user = new StudentAccountBean(username);
		return userRepository.save(user);
	}
	
	@GetMapping
	public List<AccountBean> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/{username}")
	public AccountBean getUser(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}
	
	@DeleteMapping
	public void removeAllUsers() {
		userRepository.deleteAll();
	}
	
	@DeleteMapping(path="/{username}")
	public void removeUser(@PathVariable String username) {
		userRepository.delete(userRepository.findByUsername(username));
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
		/////////////////////////////////////////////////////////////////////
		// TODO: Create StudentAccountBean object properly with all fields //
		/////////////////////////////////////////////////////////////////////
		if(validateID(arrOfStr[0])) {
			studentID = arrOfStr[0];
			add(studentID);
			System.out.println(studentID);
			
			if(!studentID.equals(null)) {
				System.out.printf("Log in with: %s", studentID);
			}
		} else {
			System.out.println("Incorrect student email/ID");
		}
		
	}
}
