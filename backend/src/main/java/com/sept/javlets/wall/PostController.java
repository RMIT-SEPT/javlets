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
import com.sept.javlets.userauth.AccountController;
import com.sept.javlets.userauth.StudentAccountBean;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/wall")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PostBean add(@RequestBody HashMap<String, String> postInfo) {
		StudentAccountBean author = userRepository.findByUsername(postInfo.get("author"));
		System.out.println("Received request");
		
		PostBean post = new PostBean(
				postInfo.get("postType"),
				postInfo.get("title"),
				postInfo.get("body"),
				author
				);
		return postRepository.save(post);
	}
	
	@GetMapping
	public List<PostBean> getAllPosts() { 
		return postRepository.findAll();
	}
	
	@DeleteMapping
	public void removeAllPosts() {
		postRepository.deleteAll();
	}
	
//	private final PostList postsList;
//	private final AccountController accountController;

//	public PostController() {
//		this.postsList = new PostList();
//		this.accountController = new AccountController();
//	}
//	
//	public PostController(AccountController accountController) {
//		this.postsList = new PostList();
//		this.accountController = accountController;
//	}
	

//	@PostMapping(path="/wall/newPost")
//	public void newPost(@RequestBody HashMap<String, String> postInfo) {
//		StudentAccountBean author = accountController.registerUser(postInfo.get("author"));
//		System.out.println("Received request");
//		
//		PostBean post = new PostBean(
//				postInfo.get("postType"),
//				postInfo.get("title"),
//				postInfo.get("body"),
//				author,
//				Long.parseLong(postInfo.get("id"))
//				);
//		postsList.addPost(post);
//	}
	
//	@GetMapping(path="/wall/title")
//	public String getPostTitle() {
//		System.out.println("\nsending to front end:\n" + postsList.getAllPosts().get(1).getTitle());
//		return postsList.getAllPosts().get(0).getTitle();
//	}
//	
////	@GetMapping(path="/wall")
////	public List<PostBean> getAllPosts() {
////		return postsList.getAllPosts();
////	}
//	
//	public void removeAllPosts() {
//		postsList.removeAll();
//	}

}
