package com.sept.javlets.userauth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountControllerTest {

	private static AccountController accountController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		accountController = new AccountController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		accountController.removeAllUsers();
	}

	@Test
	@DisplayName("Create/Remove Account Test")
	void testAccountCreationAndRemoval() {
		accountController.add("Alice");
		assertEquals(1, accountController.getAllUsers().size());
		accountController.removeUser("Alice");
		assertEquals(0, accountController.getAllUsers().size());
	}

	@Test
	@DisplayName("User Attributes Test")
	void testUserAttributes() {
		accountController.add("Alice");
		StudentAccountBean user = accountController.getUser("Alice");
		assertEquals("Alice", user.getUsername());
		assertEquals(0, user.getConnections().size());
		
		accountController.removeUser("Alice");
	}
	
	@Test
	@DisplayName("Add Connections Test")
	void testAddConnection() {
		StudentAccountBean alice = accountController.add("Alice");
		StudentAccountBean bob = accountController.add("Bob");
		alice.addConnection(bob);
		assertEquals(1, accountController.getUser("Alice").getConnections().size());
	}
	
	
}
