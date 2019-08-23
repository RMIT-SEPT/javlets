package com.sept.javlets.wall;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sept.javlets.userauth.AccountController;

class PostTest {

	private static PostController postController;
	private static AccountController accountController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		accountController = new AccountController();
		postController = new PostController(accountController);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		accountController.registerUser("TestUser1");
	}

	@AfterEach
	void tearDown() throws Exception {
		accountController.removeUser("TestUser1");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
