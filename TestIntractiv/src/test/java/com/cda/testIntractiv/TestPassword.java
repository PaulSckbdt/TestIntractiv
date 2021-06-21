package com.cda.testIntractiv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cda.testIntractiv.model.Compliance;
import com.cda.testIntractiv.model.Password;

@SpringBootTest
class TestPassword {

	@Test
	void testPassword() {
		Password pwd = new Password();

		// test length
		Compliance compliance = pwd.testCompliance("aaaaaa");
		System.out.println(compliance);
		assertFalse(compliance.isValid());
		assertEquals(compliance.getReason(), "at least 8 characters");

		// test digit
		compliance = pwd.testCompliance("aaafezfezaaa");

		System.out.println(compliance);
		assertFalse(compliance.isValid());
		assertEquals(compliance.getReason(), "at least one digit");

		// test caractere special
		compliance = pwd.testCompliance("aaaf123ezf1ezaaa");

		System.out.println(compliance);
		assertFalse(compliance.isValid());
		assertEquals(compliance.getReason(), "at least one special character (!,#,$,%,&,@)");

		// test majuscule
		compliance = pwd.testCompliance("aaaf123e@zfa");

		System.out.println(compliance);
		assertFalse(compliance.isValid());
		assertEquals(compliance.getReason(), "at least one uppercase letter");

		compliance = pwd.testCompliance("aAaf123e@zfa");

		System.out.println(compliance);
		assertTrue(compliance.isValid());
		assertEquals(compliance.getReason(), "password is compliant");

	}

}
