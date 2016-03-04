package test.java.addressBook;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.Person;

public class AddressBookControllerTest {

	private AddressBookController controller1, controller2;
	private AddressBook addressBook, addressBook2;
	
	@Before
	public void setUp() throws Exception {
		controller1 = new AddressBookController("testBooks/collectionOfBooks");
		controller2 = new AddressBookController();
		addressBook = new AddressBook();
		addressBook2 = new AddressBook();
		addressBook.setTitle("testing");
		addressBook2.setTitle("testing");
		Person person = new Person();
		person.setFirstName("John");
		addressBook2.addPerson(person);
	}

	@After
	public void tearDown() throws Exception {
		controller1 = null;
		controller2 = null;
		addressBook = null;
		addressBook2 = null;
	}

	@Test
	public void testListAllAddressBooks() throws FileNotFoundException, IOException {
		
		List<AddressBook> addressBooks = controller1.listAllAddressBooks();
		assertEquals("Should have the correct number of address books", 3, addressBooks.size());
		assertEquals("Title should match", "A Sample Address Book", addressBooks.get(0).getTitle());
		assertEquals("First name should match", "John", addressBooks.get(0).getPersons().get(0).getFirstName());
		assertEquals("Last name should match", "Sample", addressBooks.get(0).getPersons().get(0).getLastName());
		assertEquals("Address should match", "123 Sample Road", addressBooks.get(0).getPersons().get(0).getAddress());
		assertEquals("City should match", "New York", addressBooks.get(0).getPersons().get(0).getCity());
		assertEquals("State should match", "NY", addressBooks.get(0).getPersons().get(0).getState());
		assertEquals("ZIP should match", "12345", addressBooks.get(0).getPersons().get(0).getZip());
		assertEquals("Phone should match", "123 456 7890", addressBooks.get(0).getPersons().get(0).getPhone());
	}
	
	@Test
	public void testGetAddressBook() throws FileNotFoundException, IOException {
		
		File file = new File("testBooks/sampleBook.txt");
		AddressBook addressBook = controller2.getAddressBook(file);
		assertEquals("Should read a file correctly and return an address book of the correct size", 2, 
				addressBook.getPersons().size());
		assertEquals("Title should match", "A Sample Address Book", addressBook.getTitle());
		assertEquals("First name should match", "John",addressBook.getPersons().get(0).getFirstName());
		assertEquals("Last name should match", "Sample", addressBook.getPersons().get(0).getLastName());
		assertEquals("Address should match", "123 Sample Road", addressBook.getPersons().get(0).getAddress());
		assertEquals("City should match", "New York", addressBook.getPersons().get(0).getCity());
		assertEquals("State should match", "NY", addressBook.getPersons().get(0).getState());
		assertEquals("ZIP should match", "12345", addressBook.getPersons().get(0).getZip());
		assertEquals("Phone should match", "123 456 7890", addressBook.getPersons().get(0).getPhone());
	}
	
	@Test
	public void testSetAddressBook() throws FileNotFoundException, IOException {
		
		controller2.setAddressBook(addressBook);
		assertEquals("Should have added the address book", "testing", controller2.getAddressBook().getTitle());
	}
	
	@Test
	public void testSaveAddressBook() throws IOException {
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		
		controller2.setAddressBook(addressBook2);
		controller2.saveAddressBook(file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		
		assertEquals("Address book title should match", "testing", bufferedReader.readLine().trim());
		assertEquals("First name should match", "John", bufferedReader.readLine().trim());
		
		bufferedReader.close();
		file.delete();
	}
}
