package com.sept.javlets.mongo;

import com.sept.javlets.chat.MessageBean;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sept.javlets.userauth.AccountBean;

@Repository
public interface MessageRepository extends MongoRepository<MessageBean, String> {
	public List<MessageBean> findBySender(AccountBean sender);
	public List<MessageBean> findByRecipient(AccountBean recipient);
	
}
