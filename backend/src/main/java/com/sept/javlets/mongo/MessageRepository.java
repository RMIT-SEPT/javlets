package com.sept.javlets.mongo;

import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.userauth.AccountBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageBean, String> {
    List<MessageBean> findBySender(AccountBean sender);

    List<MessageBean> findByRecipient(AccountBean recipient);

}
