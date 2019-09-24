package com.sept.javlets.wall;

import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostList {

    private ArrayList<PostBean> posts = new ArrayList<>();
    private long idCounter;

    public PostList() {
        idCounter = 0;

//		this.posts = new ArrayList<PostBean>();
//		posts.add(new PostBean("Student", "A Test", "Learn to Dance 2", new StudentAccountBean("Williams"), ++idCounter));
//		posts.add(new PostBean("Mentor", "B Test", "Learn about Microservices 2", new StudentAccountBean("Johnson"), ++idCounter));
//		posts.add(new PostBean("Sham", "C Test", "Learn about Angular", new StudentAccountBean("Cello"), ++idCounter));
    }

    public void addPost(PostBean post) {
        posts.add(post);
    }

    public boolean removePost(PostBean post) {
        return posts.remove(post);
    }

    public void removeAll() {
        posts.clear();
    }

    //if tis is method is to filter posts by author, would it be better to do in front end based on gAuther username?
    public List<PostBean> filterAuthor(String userName) {
        List<PostBean> authorPosts = new ArrayList<PostBean>();
        for (PostBean post : this.posts) {
            if (post.getUserId().equals(userName))
                authorPosts.add(post);
        }

        return authorPosts;
    }


    // TODO: Uncomment when post privacy is implemented
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
