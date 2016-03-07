package test.java.addressBook;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.Person;

public class AddressBookControllerIT {

	private AddressBookController controller;
	private AddressBook addressBook;

	@Before
	public void setUp() throws Exception {
		controller = new AddressBookController();
		addressBook = new AddressBook();
		addressBook.setTitle("testing");
		Person person = new Person();
		person.setFirstName("John");
		addressBook.addPerson(person);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		addressBook = null;
	}

	@Test(timeout=100)
	public void testGetAddressBook() throws FileNotFoundException, IOException {
		File file = new File("testBooks/sampleBook.txt");
		controller.loadAddressBook(file);
		AddressBook addressBook = controller.getAddressBook();
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

	@Test(timeout=100)
	public void testSaveAddressBook() throws IOException {
		File file = new File("testBooks/tempBook.txt");
		file.delete();

		controller.setAddressBook(addressBook);
		controller.saveAddressBook(file);

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Address book title should match", "testing", bufferedReader.readLine().trim());
		assertEquals("First name should match", "John", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
}
