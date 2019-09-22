package com.sept.javlets.mongo;

import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageBean, String> {
    List<MessageBean> findBySender(StudentAccountBean sender);

    List<MessageBean> findByRecipient(StudentAccountBean recipient);

}
