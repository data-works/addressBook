package addressBook.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addressBook.AddressBook;
import addressBook.FileSystem;

public class FileSystemTest {

	FileSystem fileSystem;
	
	@Before
	public void setUp() throws Exception {
		fileSystem = new FileSystem();
	}

	@After
	public void tearDown() throws Exception {
		fileSystem = null;
	}

	@Test
	public void testFileSystem() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadFile() throws FileNotFoundException, IOException {

		File file = new File("sampleBook.txt");
		AddressBook addressBook = fileSystem.readFile(file);
		
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
		assertEquals("Phone should match", "234 567 8901", addressBook.getPersons().get(1).getPhone());
	}

	@Test
	public void testSaveFile() {
		fail("Not yet implemented");
	}

}
