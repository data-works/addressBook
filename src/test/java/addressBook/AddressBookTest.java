package test.java.addressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.Person;

public class AddressBookTest {

	AddressBook addressBook;
	Person person1, person2, person3, person4;
	
	@Before
	public void setUp() throws Exception {
		person1 = EasyMock.createMock("Person1", Person.class);
		person2 = EasyMock.createMock("Person2", Person.class);
		person3 = EasyMock.createMock("Person3", Person.class); 
		person4 = EasyMock.createMock("Person4", Person.class); 
		
		addressBook = new AddressBook();
		addressBook.addPerson(person1);
		addressBook.addPerson(person2);
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
	public void testCreateAddressBookFromExisting() {	
		AddressBook newAddressBook = new AddressBook(addressBook);
		assertEquals("Address book should have been copied", addressBook.getPersons(), newAddressBook.getPersons());
	}
	
	@Test
	public void testRemovePerson() {
		addressBook.removePerson(person2);
		assertFalse("Address book should no longer contain removed person", addressBook.getPersons().contains(person2));
	}
	
	@Test
	public void testRemovePersonByIndex() {
		addressBook.removePersonByIndex(1);
		assertFalse("Address book should no longer contain removed person", addressBook.getPersons().contains(person2));
	}
	
	@Test
	public void testSortAddressBookByName() {
		addressBook.addPerson(person3);
		addressBook.addPerson(person4);
		
		EasyMock.expect(person1.getFirstName()).andReturn("Ddd").times(3);
		EasyMock.expect(person1.getLastName()).andReturn("Ddd").times(2);
		EasyMock.replay(person1);
		
		EasyMock.expect(person2.getFirstName()).andReturn(null).times(3);
		EasyMock.expect(person2.getLastName()).andReturn("Ccc").times(2);
		EasyMock.replay(person2);
		
		EasyMock.expect(person3.getFirstName()).andReturn("Aab").times(3);
		EasyMock.expect(person3.getLastName()).andReturn("Aaa").times(2);
		EasyMock.replay(person3);
		
		EasyMock.expect(person4.getFirstName()).andReturn("Aaa").times(3);
		EasyMock.expect(person4.getLastName()).andReturn("Aaa").times(2);
		EasyMock.replay(person4);
		
		addressBook.sortAddressBookByName();
		assertEquals("First person sorted correctly", person4, addressBook.getPerson(0));
		assertEquals("Second person sorted correctly", person3, addressBook.getPerson(1));
		assertEquals("Third person sorted correctly", person2, addressBook.getPerson(2));
		assertEquals("Fourth person sorted correctly", person1, addressBook.getPerson(3));
	}
	
	@Test(expected=NullPointerException.class)
	public void testSortByNameNull(){
		EasyMock.expect(person1.getFirstName()).andReturn(null);
		EasyMock.expect(person1.getLastName()).andReturn(null);
		EasyMock.replay(person1);
		
		EasyMock.expect(person2.getFirstName()).andReturn(null);
		EasyMock.expect(person2.getLastName()).andReturn(null);
		EasyMock.replay(person2);
		
		addressBook.sortAddressBookByName();		
	}
	
	@Test
	public void testSortAddressBookByZip() {
		addressBook.addPerson(person3);
		addressBook.addPerson(person4);
		
		EasyMock.expect(person1.getZip()).andReturn("22360").times(2);
		EasyMock.replay(person1);
		
		EasyMock.expect(person2.getZip()).andReturn("12345").times(3);
		EasyMock.replay(person2);
		
		EasyMock.expect(person3.getZip()).andReturn("12340").times(2);
		EasyMock.replay(person3);
		
		EasyMock.expect(person4.getZip()).andReturn("12346").times(3);
		EasyMock.replay(person4);
		
		addressBook.sortAddressBookByZip();
		assertEquals("First person sorted correctly", person3, addressBook.getPersons().get(0));
		assertEquals("Second person sorted correctly", person2, addressBook.getPersons().get(1));
		assertEquals("Third person sorted correctly", person4, addressBook.getPersons().get(2));
		assertEquals("Fourth person sorted correctly", person1, addressBook.getPersons().get(3));
	}
	
	@Test
	public void testSearch() {
		
		Person searchedPerson = EasyMock.createMock("searchedPerson", Person.class); 
		
		EasyMock.expect(searchedPerson.getFirstName()).andReturn("John").times(6);
		EasyMock.expect(searchedPerson.getLastName()).andReturn(null);
		EasyMock.expect(searchedPerson.getAddress()).andReturn(null);
		EasyMock.expect(searchedPerson.getCity()).andReturn(null);
		EasyMock.expect(searchedPerson.getState()).andReturn(null);
		EasyMock.expect(searchedPerson.getZip()).andReturn(null);
		EasyMock.expect(searchedPerson.getPhone()).andReturn(null);
		EasyMock.expect(searchedPerson.contentCount()).andReturn(1);
		EasyMock.replay(searchedPerson);
		
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(3);
		EasyMock.replay(person1);
		
		EasyMock.expect(person2.getFirstName()).andReturn("Jane").times(2);
		EasyMock.replay(person2);

		addressBook.search(searchedPerson);
		
		assertEquals("Should find one person", 1, addressBook.getPersons().size());
		assertEquals("Should find correct person", "John", addressBook.getPerson(0).getFirstName());
	}
	
	@Test
	public void testSearchWithFullContents() {
		
		Person searchedPerson = EasyMock.createMock("searchedPerson", Person.class); 
		
		EasyMock.expect(searchedPerson.getFirstName()).andReturn("John").times(6);
		EasyMock.expect(searchedPerson.getLastName()).andReturn("Smith").times(6);
		EasyMock.expect(searchedPerson.getAddress()).andReturn("Main Road").times(5);
		EasyMock.expect(searchedPerson.getCity()).andReturn("York").times(5);
		EasyMock.expect(searchedPerson.getState()).andReturn("New York").times(5);
		EasyMock.expect(searchedPerson.getZip()).andReturn("123").times(5);
		EasyMock.expect(searchedPerson.getPhone()).andReturn("123456").times(5);
		EasyMock.expect(searchedPerson.contentCount()).andReturn(7);
		EasyMock.replay(searchedPerson);
		
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(3);
		EasyMock.expect(person1.getLastName()).andReturn("Smith").times(2);
		EasyMock.expect(person1.getAddress()).andReturn("Main Road").times(2);
		EasyMock.expect(person1.getCity()).andReturn("New York").times(2);
		EasyMock.expect(person1.getState()).andReturn("New York").times(2);
		EasyMock.expect(person1.getZip()).andReturn("123456").times(2);
		EasyMock.expect(person1.getPhone()).andReturn("123456").times(2);
		EasyMock.replay(person1);
		EasyMock.expect(person2.getFirstName()).andReturn("Jane").times(2);
		EasyMock.expect(person2.getLastName()).andReturn("Smith").times(2);
		EasyMock.expect(person2.getAddress()).andReturn(null);
		EasyMock.expect(person2.getCity()).andReturn(null);
		EasyMock.expect(person2.getState()).andReturn(null);
		EasyMock.expect(person2.getZip()).andReturn(null);
		EasyMock.expect(person2.getPhone()).andReturn(null);
		EasyMock.replay(person2);
		
		addressBook.search(searchedPerson);
		
		assertEquals("Should find one person", 1, addressBook.getPersons().size());
		assertEquals("Should find correct person", "John", addressBook.getPerson(0).getFirstName());
		
		EasyMock.verify(searchedPerson);
		EasyMock.verify(person1);
		EasyMock.verify(person2);
	}
	
	@Test
	public void testSearchWithMultipleResults() {
		
		Person searchedPerson = EasyMock.createMock("searchedPerson", Person.class); 
		
		EasyMock.expect(searchedPerson.getFirstName()).andReturn(null).times(2);
		EasyMock.expect(searchedPerson.getLastName()).andReturn("Smith").times(6);
		EasyMock.expect(searchedPerson.getAddress()).andReturn(null);
		EasyMock.expect(searchedPerson.getCity()).andReturn(null);
		EasyMock.expect(searchedPerson.getState()).andReturn(null);
		EasyMock.expect(searchedPerson.getZip()).andReturn(null);
		EasyMock.expect(searchedPerson.getPhone()).andReturn(null);
		EasyMock.expect(searchedPerson.contentCount()).andReturn(1);
		EasyMock.replay(searchedPerson);
		
		EasyMock.expect(person1.getFirstName()).andReturn("John");
		EasyMock.expect(person1.getLastName()).andReturn("Smith").times(2);
		EasyMock.replay(person1);
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Smith").times(2);
		EasyMock.replay(person2);
		
		addressBook.search(searchedPerson);
		
		assertEquals("Should find two people", 2, addressBook.getPersons().size());
		assertEquals("Should find the correct first person", "John", addressBook.getPerson(0).getFirstName());
		assertEquals("Should find the correct first person", "Jane", addressBook.getPerson(1).getFirstName());
	}
	
	@Test
	public void testSearchWithNoResults() {
		
		Person searchedPerson = EasyMock.createMock("searchedPerson", Person.class); 
		EasyMock.expect(searchedPerson.contentCount()).andReturn(0);
		EasyMock.replay(searchedPerson);

		addressBook.search(searchedPerson);

		assertEquals("Should find no results", 0, addressBook.getPersons().size());
	}
}
