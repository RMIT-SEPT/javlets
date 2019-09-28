package com.sept.javlets.mongo;

import com.sept.javlets.wall.PostBean;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.userauth.AccountBean;

@Repository
public interface PostRepository extends MongoRepository<PostBean, String> {
	List<PostBean> findByAuthor(AccountBean author);
	List<PostBean> findByTitle(String title);
}
