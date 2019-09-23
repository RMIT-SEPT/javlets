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
        StudentAccountBean alice = new StudentAccountBean("Alice");
        userRepository.save(alice);
        assertEquals(1, userRepository.count());

        userRepository.delete(alice);
        assertEquals(0, userRepository.count());
    }

    @Test
    @DisplayName("User Attributes Test")
    void testUserAttributes() {
        StudentAccountBean alice = new StudentAccountBean("21312123312312321");
        userRepository.save(alice);

        assertEquals("21312123312312321", alice.getId());
        assertEquals(0, alice.getConnections().size());
    }

    @Test
    @DisplayName("Add Connections Test")
    void testAddConnections() {
        StudentAccountBean alice = new StudentAccountBean("Alice");
        StudentAccountBean bob = new StudentAccountBean("Bob");
        alice.addConnection(bob);
        assertEquals(1, alice.getConnections().size());
    }

}
