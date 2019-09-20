package com.sept.javlets.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.userauth.StudentAccountBean;

@Repository
public interface UserRepository extends MongoRepository<StudentAccountBean, String> {
	StudentAccountBean findByUsername(String username);
}
