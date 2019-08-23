package com.sept.javlets.wall;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sept.javlets.userauth.StudentAccountBean;

@Service
public class PostList {

	private static ArrayList<PostBean> posts = new ArrayList<>();
	 private static long idCounter = 0;

	static {
		posts.add(new PostBean("Student", "A Test", "Learn to Dance 2", "Williams", ++idCounter));
		posts.add(new PostBean("Mentor", "B Test", "Learn about Microservices 2", "Johnson", ++idCounter));
		posts.add(new PostBean("Sham", "C Test", "Learn about Angular", "Cello", ++idCounter));
	}
	// public PostList() {
	// 	this.posts = new ArrayList<PostBean>();
	// }
	
	public void addPost(PostBean post) {
		posts.add(post);
	}
	
	public boolean removePost(PostBean post) {
		return posts.remove(post);
	}
	
	// public List<PostBean> filterAuthor(StudentAccountBean author) {
	// 	List<PostBean> authorPosts = new ArrayList<PostBean>();
	// 	for (PostBean post : this.posts) {
	// 		if (post.getAuthor().equals(author))
	// 			authorPosts.add(post);
	// 	}
		
	// 	return authorPosts;
	// }
	

//	public List<PostBean> filterPublic() {
//		List<PostBean> publicPosts = new ArrayList<PostBean>();
//		for (PostBean post : this.posts) {
//			if (post.getPrivacy() == PrivacySetting.PUBLIC)
//				publicPosts.add(post);
//		}
//		
//		return publicPosts;
//	}

	
	public ArrayList<PostBean> getAllPosts() {
		System.out.println("Returning " + posts.size() + " posts");
		return posts;
	}
	
}
