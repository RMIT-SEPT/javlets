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
	}

	@Test
	@DisplayName("Create Account Test")
	void testAccountCreation() {
		accountController.registerUser("Test User 1");
		assertEquals(1, accountController.getAllAccounts().size());
		accountController.removeUser("Test User 1");
	}

	@Test
	@DisplayName("")
	void test() {
		
	}
	
}
