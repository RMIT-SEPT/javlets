package com.sept.javlets.chat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.userauth.StudentAccountBean;

class ChatTest {
	
	/* Test backend code
	 * 		Tests[1,2,3] will test whether backend handles its data correctly
	 * 		Test 4 will test whether backend handles data sent by request from frontend correctly
	 */
	@Autowired
	private MessageRepository messageRepository;
	private MessageController chatController;
	
	private StudentAccountBean alice;
	private StudentAccountBean bob;
	
	@BeforeEach
    void setUp() {
		chatController = new MessageController();
    }
	
	@AfterEach
	void tearDown() {
		messageRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Add new chat into MessageList Test")
	//test adding chat then return sender's name
	void addNewChatTest() {
		alice = new StudentAccountBean("Alice");
		bob = new StudentAccountBean("Bob");
		
		MessageBean chat = new MessageBean("hello Bob",alice,bob);
		
		messageRepository.save(chat);
		assertEquals("Alice", alice.getUsername());
	}
	
	@Test
	@DisplayName("Message repository size Test")
	//test adding multiple chat and return correct size of message list
	void messageListSizeTest() {
		
		MessageBean chat1Test = new MessageBean(
				"hello Bob",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		MessageBean chat2Test = new MessageBean(
				"hello Alice",
				new StudentAccountBean("Bob"),
				new StudentAccountBean("Alice"));
		
		messageRepository.save(chat1Test);
		messageRepository.save(chat2Test);
		
		assertEquals(2, messageRepository.count());
	}
	
	@Test
	@DisplayName("MessageList size Tests for adding two messages into message list")
	//test adding multiple chat and return correct size of message list
	void addMultipleMessagesSizeTest() {
		MessageBean chat1Test = new MessageBean(
				"hello Bob",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		messageRepository.save(chat1Test);
		
		assertEquals(1, messageRepository.count());

		MessageBean chat2Test = new MessageBean(
				"hello Alice",
				new StudentAccountBean("Bob"),
				new StudentAccountBean("Alice"));
		
		messageRepository.save(chat2Test);		
		assertEquals(2, messageRepository.count());
	}
	
	@Test
	@DisplayName("Added correct chat content Test")
	//test adding a chat and return correct added message content to message list
	void addBodyTest() {
		
		MessageBean chatTest = new MessageBean(
				"hello buddy, how are you?",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		messageRepository.save(chatTest);
		
		assertEquals("hello buddy, how are you?", chatTest.getMessageContent());
	}

	@Test
	@DisplayName("Test Chat sent from frontend")
	//testing adding request sent from frontend
	// test adding content of chat components then size
	void testChatFromFrontend() {
		
		HashMap<String, String> sampleChat = new HashMap<String, String>();
		sampleChat.put("body", "Hello John!");
		sampleChat.put("from", "Alice");
		sampleChat.put("to", "Bob");
		
		chatController.add(sampleChat);
		
		assertEquals(1, chatController.getAllMessages().size());
	}
	
}
