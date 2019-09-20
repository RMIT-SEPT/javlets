package com.sept.javlets.chat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.StudentAccountBean;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatTest {
	
	/* Test backend code
	 * 		Tests[1,2,3] will test whether backend handles its data correctly
	 * 		Test 4 will test whether backend handles data sent by request from frontend correctly
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageController messageController;
	
	//private MessageController chatController;
	
	@BeforeEach
    void setUp() {
		StudentAccountBean alice = new StudentAccountBean("Alice");
		StudentAccountBean bob = new StudentAccountBean("Bob");
		userRepository.save(alice);
		userRepository.save(bob);
    }
	
	@AfterEach
	void tearDown() {
		messageRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Create Chat Message")
	void testChatMessage() {
		MessageBean message = new MessageBean("Hello, Bob!", 
											userRepository.findByUsername("Alice"), 
											userRepository.findByUsername("Bob"));
		messageRepository.save(message);
		assertEquals(1, messageRepository.count());
		assertEquals("Alice", 
						messageRepository.findBySender(
						userRepository.findByUsername(
						"Alice")).get(0).getSender().getUsername());
	}
	
	@Test
	@DisplayName("Message Repository Size")
	void testNumMessages() {
		MessageBean message1 = new MessageBean("Hello, Bob!",
												userRepository.findByUsername("Alice"),
												userRepository.findByUsername("Bob"));
		MessageBean message2 = new MessageBean("Hello, Alice!",
												userRepository.findByUsername("Bob"),
												userRepository.findByUsername("Alice"));
		messageRepository.save(message1);
		messageRepository.save(message2);
		
		assertEquals(2, messageRepository.count());
	}
	
	@Test
	@DisplayName("Saving Message Details")
	void testMessageDetails() {
		MessageBean message = new MessageBean("Hello, Alice!",
												userRepository.findByUsername("Bob"),
												userRepository.findByUsername("Alice"));
		messageRepository.save(message);
		
		assertEquals("Hello, Alice!", message.getMessageContent());
	}

	@Test
	@DisplayName("Test Chat sent from frontend")
	//testing adding request sent from frontend
	// test adding content of chat components then size
	void testMessageFromFrontend() {
		HashMap<String, String> sampleMessage = new HashMap<String, String>();
		sampleMessage.put("body", "Hello Bob!");
		sampleMessage.put("from", "Alice");
		sampleMessage.put("to", "Bob");
		
		messageController.add(sampleMessage);
		
		assertEquals(1, messageController.getAllMessages().size());
	}
	
}
