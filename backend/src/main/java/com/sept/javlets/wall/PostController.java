package com.sept.javlets.wall;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.StudentAccountBean;

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
	@RequestMapping(path="/new-post/{author}/{postContent}")
	public void newPost(@PathVariable StudentAccountBean author, @PathVariable String postContent) {
		PostBean post = new PostBean(author,postContent);
		postsList.addPost(post);
	}
	
	@RequestMapping(path="/")
	public List<PostBean> allPosts(){
		return postsList.getAllPosts();
		// Return a list of all posted posts to frontend
	}

}
