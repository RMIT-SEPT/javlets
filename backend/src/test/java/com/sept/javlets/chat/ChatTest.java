package com.sept.javlets.chat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sept.javlets.userauth.StudentAccountBean;

class ChatTest {
	
	/* Test backend code
	 * 		Tests[1,2,3] will test whether backend handles its data correctly
	 * 		Test 4 will test whether backend handles data sent by request from frontend correctly
	 */
	
	private MessageController chatController;
	private StudentAccountBean alice;
	private StudentAccountBean bob;
	private MessageBean chatTest;
	private MessageBean chat1Test;
	private MessageBean chat2Test;
	private MessageList chatlist;
	
	@Test
	@DisplayName("Add new chat into MessageList Test")
	//test adding chat then return sender's name
	void addNewChatTest() {
		alice = new StudentAccountBean("Alice");
		bob = new StudentAccountBean("Bob");
		MessageBean chat = new MessageBean("hello Bob",alice,bob);
		MessageList chatlist = new MessageList();
		chatlist.addMessage(chat);
		assertEquals("Alice",alice.getUsername());
	}
	
	@Test
	@DisplayName("MessageList size Test")
	//test adding multiple chat and return correct size of message list
	void messageListSizeTest() {
		
		chat1Test = new MessageBean(
				"hello Bob",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		chat2Test = new MessageBean(
				"hello Alice",
				new StudentAccountBean("Bob"),
				new StudentAccountBean("Alice"));
		
		chatlist = new MessageList();
		
		chatlist.addMessage(chat1Test);
		chatlist.addMessage(chat2Test);
		
		//hardcoded 4 expected for milestone 1
		//two messages are already initially added
		assertEquals(4,chatlist.getAllMessages().size());
	}
	
	@Test
	@DisplayName("MessageList size Tests for adding two messages into message list")
	//test adding multiple chat and return correct size of message list
	void addMultipleMessagesSizeTest() {
		chatlist = new MessageList();
		
		chat1Test = new MessageBean(
				"hello Bob",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		chatlist.addMessage(chat1Test);
		
		//hardcoded 3 expected for milestone 1
		//two messages are already initially added
		assertEquals(3,chatlist.getAllMessages().size());

		chat2Test = new MessageBean(
				"hello Alice",
				new StudentAccountBean("Bob"),
				new StudentAccountBean("Alice"));
		
		chatlist.addMessage(chat2Test);		
		assertEquals(4,chatlist.getAllMessages().size());
	}
	
	@Test
	@DisplayName("Added correct chat content Test")
	//test adding a chat and return correct added message content to message list
	void addBodyTest() {
		
		chatTest = new MessageBean(
				"hello buddy, how are you?",
				new StudentAccountBean("Alice"),
				new StudentAccountBean("Bob"));
		
		chatlist = new MessageList();
		
		chatlist.addMessage(chatTest);
		
		assertEquals("hello buddy, how are you?",chatTest.getMessageContent());
	}

	@Test
	@DisplayName("Test Chat sent from frontend")
	//testing adding request sent from frontend
	// test adding content of chat components then size
	void testChatFromFrontend() {
		
		chatController = new MessageController();
		
		HashMap<String, String> sampleChat = new HashMap<String, String>();
		sampleChat.put("body", "Hello John!");
		sampleChat.put("from", "Alice");
		sampleChat.put("to", "Bob");
		
		chatController.newMessage(sampleChat);
		
		//hardcoded 3 expected for milestone 1
		//two messages are already initially added
		assertEquals(3, chatController.getAllMessages().size());
	}
	
}
