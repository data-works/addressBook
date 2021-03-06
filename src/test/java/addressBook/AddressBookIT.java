package test.java.addressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.Person;

public class AddressBookIT {

	AddressBook addressBook;
	Person person1, person2, person3, person4, person5;

	@Before
	public void setUp() throws Exception {
		person1 = new Person("Josh", "Aaa", "22 Big Road", "Miami", "FL", "12893", "123 234 3456");	
		person2 = new Person("Jane", "Ccc", "22 Big Road", "Miami", "FL", "12899", "123 234 3456");	
		person3 = new Person("John", "Bbb", "22 Big Road", "Miami", "FL", "12850", "123 234 3456");	
		person4 = new Person("Jane", "Bbb", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");	
		person5 = new Person(null, null, "Main Street", "Atlanta", "GA", "12850", "123 234 8970");	

		addressBook = new AddressBook();
		addressBook.addPerson(person1);
		addressBook.addPerson(person2);
		addressBook.addPerson(person3);
		addressBook.addPerson(person4);
	}

	@After
	public void tearDown() throws Exception {
		addressBook = null;
		person1 = null;
		person2 = null;
		person3 = null;
		person4 = null;
	}

	@Test
	public void testSortAddressBookByName() {
		addressBook.sortAddressBookByName();
		assertEquals("First person sorted correctly", person1, addressBook.getPersons().get(0));
		assertEquals("Second person sorted correctly", person4, addressBook.getPersons().get(1));
		assertEquals("Third person sorted correctly", person3, addressBook.getPersons().get(2));
		assertEquals("Fourth person sorted correctly", person2, addressBook.getPersons().get(3));
	}

	@Test
	public void testSortAddressBookByZip() {
		addressBook.sortAddressBookByZip();
		assertEquals("First person sorted correctly", person3, addressBook.getPersons().get(0));
		assertEquals("Second person sorted correctly", person4, addressBook.getPersons().get(1));
		assertEquals("Third person sorted correctly", person1, addressBook.getPersons().get(2));
		assertEquals("Fourth person sorted correctly", person2, addressBook.getPersons().get(3));
	}

	@Test
	public void testSearch() {
		Person person = new Person("John", null, null, null, null, null, null);
		addressBook.search(person);

		assertEquals("Should find one person", 1, addressBook.getPersons().size());
		assertEquals("Should find correct person", "John", addressBook.getPerson(0).getFirstName());
	}
	
	@Test
	public void testSearch2() {
		addressBook.addPerson(person5);
		Person person = new Person("John", "Bbb", "22 Big Road", "Miami", "FL", "12850", "123 234 3456");
		addressBook.search(person);

		assertEquals("Should find one person", 1, addressBook.getPersons().size());
		assertEquals("Should find correct person", "John", addressBook.getPerson(0).getFirstName());
	}


	@Test
	public void testSearchWithMultipleResults() {
		Person person = new Person(null, "Bbb", null, null, null, null, null);
		addressBook.search(person);

		assertEquals("Should find two people", 2, addressBook.getPersons().size());
		assertEquals("Should find the correct first person", "John", addressBook.getPerson(0).getFirstName());
		assertEquals("Should find the correct first person", "Jane", addressBook.getPerson(1).getFirstName());
	}

	@Test
	public void testSearchWithNoResults() {
		Person person = new Person(null, null, null, null, null, null, null);
		addressBook.search(person);

		assertEquals("Should find no results", 0, addressBook.getPersons().size());
	}
}
