package com.sept.javlets.wall;


import com.sept.javlets.mongo.PostRepository;
import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wall")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/newPost")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody HashMap<String, String> postInfo) {
        System.out.println("Received request");
        System.out.println("Author: " + postInfo.get("author"));
        PostBean post;
        if (postInfo.get("category").equals("livestream")) {
            post = new LivestreamPostBean(
                    postInfo.get("postType"),
                    postInfo.get("title"),
                    postInfo.get("body"),
                    userRepository.findById(postInfo.get("userId")).get(),
                    postInfo.get("author"),
                    Long.parseLong(postInfo.get("postId")),
                    postInfo.get("category"),
                    postInfo.get("selectDate"));
            postRepository.save(post);
            System.out.println("Live post saved");

        } else if (postInfo.get("category").equals("wallpost")) {

            post = new PostBean(
                    postInfo.get("postType"),
                    postInfo.get("title"),
                    postInfo.get("body"),
                    userRepository.findById(postInfo.get("userId")).get(),
                    postInfo.get("author"),
                    Long.parseLong(postInfo.get("postId")),
                    postInfo.get("category"));
            postRepository.save(post);
            System.out.println("Wall post saved");

        } else {
            System.err.println("Error: Invalid post category");
        }

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
