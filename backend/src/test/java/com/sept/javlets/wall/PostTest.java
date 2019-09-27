package com.sept.javlets.wall;

import com.sept.javlets.mongo.PostRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostTest {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

	@BeforeEach
	void setUp() throws Exception {
		AccountBean user = new AccountBean("Test User");
		userRepository.save(user);
	}

    @AfterEach
    void tearDown() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Making a Post")
    void testPost() {
        postRepository.save(
            // new PostBean("Student", "A Tragedy", "Did you ever hear the story of Darth Plagueis the Wise?",
            //                     userRepository.findByUsername("Test User")
            new PostBean("Student", "A Tragedy", "Did you ever hear the story of Darth Plagueis the Wise?",
                                userRepository.findByUsername("Test User"), 123));

        assertEquals(1, postRepository.count());
    }

}
