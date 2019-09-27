package com.sept.javlets.mongo;

import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.userauth.AccountBean;

@Repository
public interface MessageRepository extends MongoRepository<MessageBean, String> {
	public List<MessageBean> findBySender(AccountBean sender);
	public List<MessageBean> findByRecipient(AccountBean recipient);
	
}
