package com.sept.javlets.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.wall.PostBean;

@Repository
public interface PostRepository extends MongoRepository<PostBean, String> {
	// This probably needs to be changed to StudentAccountBean instead of String
		List<PostBean> findByAuthor(String author);
		
		PostBean findByTitle(String title);
}
