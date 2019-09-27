package com.sept.javlets.userauth;

import com.sept.javlets.mongo.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountTest {

    @Autowired
    private UserRepository userRepository;

	@Test
	@DisplayName("Account Creation and Removal")
	void testAccountCreationAndRemoval() {
		AccountBean alice = new AccountBean("Alice");
		userRepository.save(alice);
		assertEquals(1, userRepository.count());
		
		userRepository.delete(alice);
		assertEquals(0, userRepository.count());
	}
	
	@Test
	@DisplayName("User Attributes Test")
	void testUserAttributes() {
		AccountBean alice = new AccountBean("Alice");
		userRepository.save(alice);
		
		assertEquals("Alice", alice.getUsername());
		assertEquals(0, alice.getConnections().size());
	}
	
	@Test
	@DisplayName("Add Connections Test")
	void testAddConnections() {
		AccountBean alice = new AccountBean("Alice");
		AccountBean bob = new AccountBean("Bob");
		alice.addConnection(bob);
		assertEquals(1, alice.getConnections().size());
	}

}
