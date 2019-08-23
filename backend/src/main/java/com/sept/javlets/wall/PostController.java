package com.sept.javlets.wall;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.AccountController;
import com.sept.javlets.userauth.StudentAccountBean;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {
	
	private PostList postsList;
	
	@Autowired
	private AccountController accountController;

	public PostController() {
		postsList = new PostList();
	}
	
	/*
	 * Make new post
	 * 		ADD the post into the list
	 * 		REFRESH the page (frontend)
	 * FRONTEND to send author and postContent via path-link
	*/

	@PostMapping(path="/wall/newPost")
	public void newPost(@RequestBody HashMap<String, String> postInfo) {
		StudentAccountBean author = accountController.getUser(postInfo.get("author"));
		System.out.println("Received request");
		
		PostBean post = new PostBean(
				postInfo.get("postType"),
				postInfo.get("title"),
				postInfo.get("body"),
				author
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

}
