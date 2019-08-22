package com.sept.javlets.wall;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sept.javlets.userauth.StudentAccountBean;

public class PostList {

	private List<PostBean> posts;
	
	public PostList() {
		this.posts = new ArrayList<PostBean>();
	}
	
	public void addPost(PostBean post) {
		posts.add(post);
	}
	
	public boolean removePost(PostBean post) {
		return posts.remove(post);
	}
	
	public List<PostBean> filterAuthor(StudentAccountBean author) {
		List<PostBean> authorPosts = new ArrayList<PostBean>();
		for (PostBean post : this.posts) {
			if (post.getAuthor().equals(author))
				authorPosts.add(post);
		}
		
		return authorPosts;
	}
	
//	public List<PostBean> filterPublic() {
//		List<PostBean> publicPosts = new ArrayList<PostBean>();
//		for (PostBean post : this.posts) {
//			if (post.getPrivacy() == PrivacySetting.PUBLIC)
//				publicPosts.add(post);
//		}
//		
//		return publicPosts;
//	}
	
	public List<PostBean> getAllPosts() {
		return posts;
	}
	
}
