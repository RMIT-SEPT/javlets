package com.sept.javlets.mongo;

import com.sept.javlets.userauth.AccountBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AccountBean, String> {
}
