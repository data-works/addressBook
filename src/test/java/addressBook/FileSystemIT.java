package test.java.addressBook;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.FileSystem;
import main.java.addressBook.Person;

public class FileSystemIT {

	FileSystem fileSystem;
	AddressBook addressBook;
	Person person, personWithNullValue;
	
	@Before
	public void setUp() throws Exception {
		fileSystem = new FileSystem();
		addressBook = new AddressBook();
		person = new Person("Josh", "Sampleman", "22 Big Road", "Miami", "FL", "12890", "123 234 3456");
		personWithNullValue = new Person(null, "Sampleman", "22 Big Road", "Miami", "FL", "12890", null);
	}

	@After
	public void tearDown() throws Exception {
		fileSystem = null;
		addressBook = null;
		person = null;
		personWithNullValue = null;
	}

	@Test
	public void testReadFile() throws FileNotFoundException, IOException {

		File file = new File("testBooks/sampleBook.txt");
		addressBook = fileSystem.readFile(file);
		
		assertEquals("Title should match", "A Sample Address Book", addressBook.getTitle());
		
		assertEquals("First name should match", "John", addressBook.getPersons().get(0).getFirstName());
		assertEquals("Last name should match", "Sample", addressBook.getPersons().get(0).getLastName());
		assertEquals("Address should match", "123 Sample Road", addressBook.getPersons().get(0).getAddress());
		assertEquals("City should match", "New York", addressBook.getPersons().get(0).getCity());
		assertEquals("State should match", "NY", addressBook.getPersons().get(0).getState());
		assertEquals("ZIP should match", "12345", addressBook.getPersons().get(0).getZip());
		assertEquals("Phone should match", "123 456 7890", addressBook.getPersons().get(0).getPhone());

		assertEquals("First name should match", "Jane", addressBook.getPersons().get(1).getFirstName());
		assertEquals("Last name should match", "Sample", addressBook.getPersons().get(1).getLastName());
		assertEquals("Address should match", "123 Main Street", addressBook.getPersons().get(1).getAddress());
		assertEquals("City should match", "San Francisco", addressBook.getPersons().get(1).getCity());
		assertEquals("State should match", "CA", addressBook.getPersons().get(1).getState());
		assertEquals("ZIP should match", "23456", addressBook.getPersons().get(1).getZip());
		assertEquals("Phone should match", "", addressBook.getPersons().get(1).getPhone());
	}

	@Test
	public void testSaveFile() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		addressBook.addPerson(person);
		addressBook.addPerson(person);
		addressBook.setTitle("A Sample Address Book");
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "A Sample Address Book", bufferedReader.readLine().trim());
		assertEquals("First name should match", "Josh", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "123 234 3456", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveFileWithEmptyTitle() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		addressBook.addPerson(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "", bufferedReader.readLine().trim());
		assertEquals("First name should match", "Josh", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "123 234 3456", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveFileWithNullValue() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		addressBook.addPerson(personWithNullValue);
		addressBook.addPerson(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "", bufferedReader.readLine().trim());
		assertEquals("First name should match", "", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveFileWithNullValueAtTheEnd() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		addressBook.addPerson(personWithNullValue);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "", bufferedReader.readLine().trim());
		assertEquals("First name should match", "", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
}
