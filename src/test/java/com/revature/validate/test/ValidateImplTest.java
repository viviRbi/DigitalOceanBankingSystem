package com.revature.validate.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.validate.ValidateImpl;

class ValidateImplTest {
	private static ValidateImpl v;
	
	@BeforeAll
	public static void setUpValidationImpl() {
		v = new ValidateImpl();
	}
	
	@Test
	void testValidateUsernameLessThanFiveWords() {
		String username = v.validateUsername("abc");
		assertEquals( "\n \u001B[31m USERNAME: Username cannot be less than 5 characters. \u001B[0m",username);
	}
	
	@Test
	void testValidateUsernameMoreThanFiveWords() {
		String username = v.validateUsername("abcdef");
		assertEquals( "", username);
	}

	@Test
	void testValidatePasswordLessThanFiveWords() {
		String pass = v.validateUsername("abc");
		assertEquals( "\n \u001B[31m USERNAME: Username cannot be less than 5 characters. \u001B[0m", pass);
	}
	
	@Test
	void testValidatePasswordMoreThanFiveWords() {
		String pass = v.validateUsername("abcdef");
		assertEquals( "", pass);
	}

	@Test
	void testValidatePhoneWrongTemplate() {
		String phone = v.validatePhone("123  456 7897");
		assertEquals( "\n \u001B[31m PHONE: Please type your phone number correctly \u001B[0m", phone);
		phone = v.validatePhone("123 456 7897666");
		assertEquals( "\n \u001B[31m PHONE: Please type your phone number correctly \u001B[0m", phone);
		phone = v.validatePhone("23 456 789766");
		assertEquals( "\n \u001B[31m PHONE: Please type your phone number correctly \u001B[0m", phone);
	}
	
	@Test
	void testValidatePhoneRightTemplate() {
		String phone = v.validatePhone("123 456 7897");
		assertEquals( "", phone);
		phone = v.validatePhone("1234567897");
		assertEquals( "", phone);
		phone = v.validatePhone("123-456-7897");
		assertEquals( "", phone);
		phone = v.validatePhone("(123) 456-7897");
		assertEquals( "", phone);
	}
	
	@Test
	void testValidateBirthdayWrongTemplate() {
		// Dead people
		String b = v.validateBirthday("01/01/1111");
		LocalDate birthday=LocalDate.parse("01/01/1111", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate curr=LocalDate.now();
		Period p=Period.between(birthday, curr);
		int age = p.getYears();
		assertEquals( "\n \u001B[31m BIRTHDAY: Incorrect birthday. You cannot be " + age+" years old \u001B[0m", b);
		//Young
		b = v.validateBirthday("01/01/2020");
		assertEquals( "\n \u001B[31m BIRTHDAY: You are not old enough to register \u001B[0m", b);
		//Wrong template
		b = v.validateBirthday("0/01/2020");
		assertEquals("\n \u001B[31m BIRTHDAY: Please type your birthday correctly \u001B[0m", b);
	}
	
	@Test
	void testValidateBirthdayRightTemplate() {
		String b = v.validateBirthday("01/01/1955");
		assertEquals( "", b);
	}

	void testValidateAddressWrongTemplate() {
		String b = v.validateAddress("1234 ");
		assertEquals( "\n \u001B[31m ADDRESS: Please type your address correctly (ex: 12394 Abc lane) \u001B[0m", b);
		b = v.validateAddress("12 Abc");
		assertEquals( "\n \u001B[31m ADDRESS: Please type your address correctly (ex: 12394 Abc lane) \u001B[0m", b);
	}
	
	void testValidateAddressRightTemplate() {
		String b = v.validateAddress("1234 Scb");
		assertEquals( "", b);
		b = v.validateAddress("1234 Abd ln");
		assertEquals( "", b);
	}

	@Test
	void testValidateZipCodeWrongTemplate() {
		String b = v.validateZipCode("1234");
		String c = "1234";
		assertEquals( "\n \u001B[31m ZIPCODE: Please enter 5 digit number for Zipcode \u001B[0m", b);
		b = v.validateZipCode("1234567");
		assertEquals( "\n \u001B[31m ZIPCODE: Please enter 5 digit number for Zipcode \u001B[0m", b);
	}
	
	@Test
	void testValidateZipCodeRightTemplate() {
		String b = v.validateAddress("12345");
		assertEquals("",b);
	}

	@Test
	void testValidateStateWrongTemplate() {
		String s = v.validateState("abc");
		assertEquals("\n \u001B[31m STATE: Please type the State 2-letter code \u001B[0m",s);
		s = v.validateState("rx");
		assertEquals("\n \u001B[31m STATE: Incorrect State \u001B[0m",s);
	}
	
	@Test
	void testValidateStateRightTemplate() {
		String s = v.validateState("al");
		assertEquals("",s);
		s = v.validateState("AR");
		assertEquals("",s);
		s = v.validateState("Oh");
		assertEquals("",s);
	}

}
