package test.java.addressBook;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.Person;

public class AddressBookTest {

	AddressBook addressBook, addressBookWithMock;
	Person person1, person2, person3, person4, personMock1, personMock2;
	
	@Before
	public void setUp() throws Exception {
		
		// Objects for tests that require mocking
		personMock1 = EasyMock.createMock("Person1", Person.class);
		personMock2 = EasyMock.createMock("Person2", Person.class); 
		
		addressBookWithMock = new AddressBook();
		addressBookWithMock.addPerson(personMock1);
		addressBookWithMock.addPerson(personMock2);
		
		// Objects for tests that do not require mocking
		person1 = new Person("Josh", "Aaa", "22 Big Road", "Miami", "FL", "12893", "123 234 3456");	
		person2 = new Person("Jane", "Ccc", "22 Big Road", "Miami", "FL", "12899", "123 234 3456");	
		person3 = new Person("John", "Bbb", "22 Big Road", "Miami", "FL", "12850", "123 234 3456");	
		person4 = new Person("Jane", "Bbb", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");	
		
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
		personMock1 = null;
		personMock2 = null;
	}

	@Test
	public void testRemovePerson() {
		addressBook.removePerson(person2);
		assertFalse("Address book should no longer contain removed person", addressBook.getPersons().contains(person2));
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
		
		Person searchedPerson = EasyMock.createMock("searchedPerson", Person.class); 
		
		EasyMock.expect(searchedPerson.getFirstName()).andReturn("John").times(4);
		EasyMock.expect(searchedPerson.getLastName()).andReturn(null);
		EasyMock.expect(searchedPerson.getAddress()).andReturn(null);
		EasyMock.expect(searchedPerson.getCity()).andReturn(null);
		EasyMock.expect(searchedPerson.getState()).andReturn(null);
		EasyMock.expect(searchedPerson.getZip()).andReturn(null);
		EasyMock.expect(searchedPerson.getPhone()).andReturn(null);
		EasyMock.expect(searchedPerson.contentCount()).andReturn(1);
		EasyMock.replay(searchedPerson);
		
		EasyMock.expect(personMock1.getFirstName()).andReturn("John").times(3);
/*		EasyMock.expect(personMock1.getLastName()).andReturn("Bbb");
		EasyMock.expect(personMock1.getAddress()).andReturn("22 Big Road");
		EasyMock.expect(personMock1.getCity()).andReturn("Miami");
		EasyMock.expect(personMock1.getState()).andReturn("FL");
		EasyMock.expect(personMock1.getZip()).andReturn("12850");
		EasyMock.expect(personMock1.getPhone()).andReturn("123 234 3456");*/
		EasyMock.replay(personMock1);
		
		EasyMock.expect(personMock2.getFirstName()).andReturn("Jane").times(2);
/*		EasyMock.expect(personMock2.getLastName()).andReturn("Bbb");
		EasyMock.expect(personMock2.getAddress()).andReturn("22 Big Road");
		EasyMock.expect(personMock2.getCity()).andReturn("Miami");
		EasyMock.expect(personMock2.getState()).andReturn("FL");
		EasyMock.expect(personMock2.getZip()).andReturn("12890");
		EasyMock.expect(personMock2.getPhone()).andReturn("123 234 3456");*/
		EasyMock.replay(personMock2);

		//Person person = new Person("John", null, null, null, null, null, null);
		addressBookWithMock.search(searchedPerson);
		
		assertEquals("Should find one person", 1, addressBookWithMock.getPersons().size());
		assertEquals("Should find correct person", "John", addressBookWithMock.getPerson(0).getFirstName());
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
