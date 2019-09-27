package com.sept.javlets.chat;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.StudentAccountBean;
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
import com.sept.javlets.userauth.AccountBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		AccountBean alice = new AccountBean("Alice");
		AccountBean bob = new AccountBean("Bob");
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

        StudentAccountBean alice = new StudentAccountBean("aliceid");
        alice.setGivenName("Alice");

        StudentAccountBean bob = new StudentAccountBean("bobid");

        MessageBean message = new MessageBean("Hello, Bob!",
                "aliceid",
                "bobid");
        messageRepository.save(message);
        assertEquals(1, messageRepository.count());
        assertEquals("Hello, Bob!",
                messageRepository.findBySenderId("aliceid").get(0).getMsg());
    }

    @Test
    @DisplayName("Message Repository Size")
    void testNumMessages() {
        MessageBean message1 = new MessageBean("Hello, Bob!",
                "Alice",
                "Bob");

        MessageBean message2 = new MessageBean("Hello, Alice!",
                "Bob",
                "Alice");

        messageRepository.save(message1);
        messageRepository.save(message2);

        assertEquals(2, messageRepository.count());
    }

    @Test
    @DisplayName("Saving Message Details")
    void testMessageDetails() {
        MessageBean message = new MessageBean("Hello, Alice!",
                "Bob",
                "Alice");
        messageRepository.save(message);

        assertEquals("Hello, Alice!", message.getMsg());
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

//        messageController.add(sampleMessage);
//
//        assertEquals(1, messageController.getAllMessages().size());
    }

}
