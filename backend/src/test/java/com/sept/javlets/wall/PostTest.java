package com.sept.javlets.wall;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

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
		postController = new PostController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		accountController.add("TestUser1");
	}

	@AfterEach
	void tearDown() throws Exception {
		postController.removeAllPosts();
		accountController.removeAllUsers();
	}

	@Test
	void testPost() {
		HashMap<String, String> samplePost = new HashMap<String, String>();
		samplePost.put("postType", "Student");
		samplePost.put("title", "Test Title 1");
		samplePost.put("body", "Test Post Body 1");
		samplePost.put("author", "TestUser1");
		
		postController.add(samplePost);
		
		// Includes hardcoded test posts in the PostList class, when they get removed then 
		//   the assert statement should change
		assertEquals(4, postController.getAllPosts().size());
	}
	
	@Test
	void test() {
		
	}

}
