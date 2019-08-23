package com.sept.javlets.wall;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.StudentAccountBean;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {
	
	private PostList postsList;

	public PostController() {
		postsList = new PostList();
	}
	
	/*
	 * Make new post
	 * 		ADD the post into the list
	 * 		REFRESH the page (frontend)
	 * FRONTEND to send author and postContent via path-link
	*/
//	@RequestMapping(path="/new-post/{author}/{postContent}")
//	public void newPost(@PathVariable StudentAccountBean author, @PathVariable String postContent) {
//		PostBean post = new PostBean(author,postContent);
//		postsList.addPost(post);
//	}
	
	@GetMapping("/wall/all")
	public ArrayList<PostBean> getAllWallPosts() {
		System.out.println("\nsending to front end:\n" + postsList.getAllPosts());
		return postsList.getAllPosts();
	}
	@GetMapping("/wall/title")
	public String getPostTitle() {
		System.out.println("\nsending to front end:\n" + postsList.getAllPosts().get(1).getTitle());
		return postsList.getAllPosts().get(0).getTitle();
	}

}
