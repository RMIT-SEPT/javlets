package com.sept.javlets.wall;


import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.AccountController;
import com.sept.javlets.userauth.StudentAccountBean;

// @CrossOrigins(origins = "https://javlets-deployed.javets.appspot.com/")
// @CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {
	
	private final PostList postsList;
	private final AccountController accountController;

	public PostController() {
		this.postsList = new PostList();
		this.accountController = new AccountController();
	}
	
	public PostController(AccountController accountController) {
		this.postsList = new PostList();
		this.accountController = accountController;
	}
	

	@PostMapping(path="/wall/newPost")
	public void newPost(@RequestBody HashMap<String, String> postInfo) {
		StudentAccountBean author = accountController.registerUser(postInfo.get("author"));
		System.out.println("Received request");
		
		PostBean post = new PostBean(
				postInfo.get("postType"),
				postInfo.get("title"),
				postInfo.get("body"),
				author,
				Long.parseLong(postInfo.get("id"))
				);
		postsList.addPost(post);
	}
	
	@GetMapping(path="/wall/title")
	public String getPostTitle() {
		System.out.println("\nsending to front end:\n" + postsList.getAllPosts().get(1).getTitle());
		return postsList.getAllPosts().get(0).getTitle();
	}
	
	@GetMapping(path="/wall")
	public List<PostBean> getAllPosts() {
		return postsList.getAllPosts();
	}
	
	public void removeAllPosts() {
		postsList.removeAll();
	}

}
