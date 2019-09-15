package com.sept.javlets.userauth;


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
	public List<StudentAccountBean> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/${username}")
	public StudentAccountBean getUser(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}
	
	@DeleteMapping
	public void removeAllUsers() {
		userRepository.deleteAll();
	}
	
	@DeleteMapping(path="/${username}")
	public void removeUser(@PathVariable String username) {
		userRepository.delete(userRepository.findByUsername(username));
	}
	
}
