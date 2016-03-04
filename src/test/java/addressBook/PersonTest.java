package test.java.addressBook;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.Person;

public class PersonTest {

	Person person1, person2, person3;
	
	@Before
	public void setUp() throws Exception {
		person1 = new Person("John", "Sample", "123 Sample Road", "New York", "NY", "12345", "123 456 7890");
		person2 = new Person("John", "Sample", null, "New York", "NY", "12345", null);	
		person3 = new Person();	
	}

	@After
	public void tearDown() throws Exception {
		person1 = null;
		person2 = null;
		person3 = null;
	}

	@Test
	public void testPerson() {
		assertEquals("First name should match", "John", person1.getFirstName());
		assertEquals("Last name should match", "Sample", person1.getLastName());
		assertEquals("Address should match", "123 Sample Road", person1.getAddress());
		assertEquals("City should match", "New York", person1.getCity());
		assertEquals("State should match", "NY", person1.getState());
		assertEquals("ZIP should match", "12345", person1.getZip());
		assertEquals("Phone should match", "123 456 7890", person1.getPhone());
	}
	
	@Test
	public void testContentCount() {
		assertEquals("The content count should match", 7, person1.contentCount());
	}
	
	@Test
	public void testContentCountWithNullValues() {
		assertEquals("The content count should match", 5, person2.contentCount());
	}
	
	@Test
	public void testContentCountWithNullOnly() {
		assertEquals("The content count should match", 0, person3.contentCount());
	}
}
