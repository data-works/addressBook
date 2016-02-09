package addressBook.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addressBook.AddressBook;
import addressBook.Person;

public class AddressBookTest {

	AddressBook addressBook;
	Person person1, person2, person3;
	
	@Before
	public void setUp() throws Exception {
		person1 = new Person("Josh", "Sampleman", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");	
		person2 = new Person("Jane", "Sampleman", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");	
		person3 = new Person("John", "Sampleman", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");	
		
		addressBook = new AddressBook();
		addressBook.addPerson(person1);
		addressBook.addPerson(person2);
		addressBook.addPerson(person3);
	}

	@After
	public void tearDown() throws Exception {
		addressBook = null;
	}

	@Test
	public void testRemovePerson() {
		addressBook.removePerson(person2);
		assertFalse("Address book should no longer contain removed person", addressBook.getPersons().contains(person2));
	}

}
