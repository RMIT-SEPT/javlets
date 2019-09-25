package com.sept.javlets.wall;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.mongo.PostRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;
import com.sept.javlets.userauth.StudentAccountBean;

// @CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://javlets-deployed-dot-javets.appspot.com/")
// @CrossOrigin(origins = 'https://javlets-frontend-dot-javets.appspot.com/')
@RestController
@RequestMapping("/wall")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping(path="/newPost")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody HashMap<String, String> postInfo) {
		System.out.println("Received request");
		System.out.println("Author: " + postInfo.get("author"));
		AccountBean author = userRepository.findByUsername(postInfo.get("author"));
		if (author == null) {
			author = new StudentAccountBean(postInfo.get("author"));
			System.out.println("Before inserting");
			userRepository.insert(author);
		}
		
		System.out.println("Request 3453");
		
		PostBean post = new PostBean(
				postInfo.get("postType"),
				postInfo.get("title"),
				postInfo.get("body"),
				author
				);
		
		
		System.out.println("Request 3456");
		
		postRepository.save(post);
		System.out.println("Request 3457");
	}
	
	@GetMapping
	public List<PostBean> getAllPosts() {
		List<PostBean> posts = postRepository.findAll();
		System.out.println("Posts being returned:");
		for (PostBean p : posts) {
			System.out.println(p.toString());
		}
		System.out.println(" ");
		
		return posts;
	}
	
	@DeleteMapping
	public void removeAllPosts() {
		postRepository.deleteAll();
	}
	

}
