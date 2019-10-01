package com.sept.javlets.mongo;

import com.sept.javlets.userauth.AccountBean;
import com.sept.javlets.wall.PostBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostBean, String> {
    List<PostBean> findByAuthor(AccountBean author);

    List<PostBean> findByTitle(String title);
}
