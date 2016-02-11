package test.java.addressBook;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.Person;

public class PersonTest {

	Person person;
	
	@Before
	public void setUp() throws Exception {
		person = new Person("John", "Sample", "123 Sample Road", "New York", "NY", "12345", "123 456 7890");	
	}

	@After
	public void tearDown() throws Exception {
		person = null;
	}

	@Test
	public void testPerson() {
		assertEquals("First name should match", "John", person.getFirstName());
		assertEquals("Last name should match", "Sample", person.getLastName());
		assertEquals("Address should match", "123 Sample Road", person.getAddress());
		assertEquals("City should match", "New York", person.getCity());
		assertEquals("State should match", "NY", person.getState());
		assertEquals("ZIP should match", "12345", person.getZip());
		assertEquals("Phone should match", "123 456 7890", person.getPhone());
	}
}
