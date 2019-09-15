package com.sept.javlets.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.userauth.StudentAccountBean;

public interface MessageRepository extends MongoRepository<MessageBean, String> {
	public List<MessageBean> findBySender(StudentAccountBean sender);
	public List<MessageBean> findByRecipient(StudentAccountBean recipient);
	
}
