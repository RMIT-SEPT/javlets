package com.sept.javlets.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageBeanTest {

	@Test
	void test() {
		
		
	}
	
	@Test
	  @DisplayName("Dummy test")
	  void dummyTest() {
	    int expected = 4;
	    int actual = 2 + 2;
	    assertEquals(expected, actual, "INCONCEIVABLE!");
	}
	
	@Test
	  @DisplayName("Dummy testing")
	  void aDummyTesting() {
	    boolean shouldKnowThis = true;
	    boolean doesKnowthis = false;
	    assertEquals(shouldKnowThis, doesKnowthis, "INCONCEIVABLE!");
	}


}
