package com.sept.javlets.mongo;

import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.userauth.AccountBean;

@Repository
public interface UserRepository extends MongoRepository<AccountBean, String> {
	AccountBean findByUsername(String username);
}
