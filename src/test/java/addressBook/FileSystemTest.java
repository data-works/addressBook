package test.java.addressBook;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.FileSystem;
import main.java.addressBook.Person;

public class FileSystemTest {

	FileSystem fileSystem;
	AddressBook addressBook;
	Person person;
	List<Person> persons;
	
	@Before
	public void setUp() throws Exception {
		fileSystem = new FileSystem();
		addressBook = EasyMock.createMock("AddressBook", AddressBook.class);
		person = EasyMock.createMock("Person", Person.class);
		persons = new ArrayList<Person>();
	}

	@After
	public void tearDown() throws Exception {
		fileSystem = null;
		addressBook = null;
		person = null;
		persons = null;
	}
	
	@Test(expected=FileNotFoundException.class)
	public void readNonExistentFile() throws FileNotFoundException, IOException {
		File file = new File("123456/abcde12345.txt");
		addressBook = fileSystem.readFile(file);
	}
	
	@Test(expected=NullPointerException.class)
	public void readBrokenFile() throws IOException {
		File file = new File("testBooks/brokenBook.txt");
		addressBook = fileSystem.readFile(file);
	}

	@Test
	public void testSaveFile() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		persons.add(person);
		EasyMock.expect(addressBook.getTitle()).andReturn("A Sample Address Book").times(2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).times(2);
		EasyMock.expect(person.getFirstName()).andReturn("Josh").times(2);
		EasyMock.expect(person.getLastName()).andReturn("Sampleman").times(2);
		EasyMock.expect(person.getAddress()).andReturn("22 Big Road").times(2);
		EasyMock.expect(person.getCity()).andReturn("Miami").times(2);
		EasyMock.expect(person.getState()).andReturn("FL").times(2);
		EasyMock.expect(person.getZip()).andReturn("12890").times(2);
		EasyMock.expect(person.getPhone()).andReturn("123 234 3456").times(2);
		EasyMock.replay(addressBook);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person);
		
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
		
		persons.add(person);
		EasyMock.expect(addressBook.getTitle()).andReturn(null);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).times(2);
		EasyMock.expect(person.getFirstName()).andReturn("Josh").times(2);
		EasyMock.expect(person.getLastName()).andReturn("Sampleman").times(2);
		EasyMock.expect(person.getAddress()).andReturn("22 Big Road").times(2);
		EasyMock.expect(person.getCity()).andReturn("Miami").times(2);
		EasyMock.expect(person.getState()).andReturn("FL").times(2);
		EasyMock.expect(person.getZip()).andReturn(null);
		EasyMock.expect(person.getPhone()).andReturn("123 234 3456").times(2);
		EasyMock.replay(addressBook);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "", bufferedReader.readLine().trim());
		assertEquals("First name should match", "Josh", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "123 234 3456", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveFileWithNullValues() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		persons.add(person);
		EasyMock.expect(addressBook.getTitle()).andReturn("A Sample Address Book").times(2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).times(1);
		EasyMock.expect(person.getFirstName()).andReturn(null);
		EasyMock.expect(person.getLastName()).andReturn(null);
		EasyMock.expect(person.getAddress()).andReturn(null);
		EasyMock.expect(person.getCity()).andReturn(null);
		EasyMock.expect(person.getState()).andReturn(null);
		EasyMock.expect(person.getZip()).andReturn("12890").times(2);
		EasyMock.expect(person.getPhone()).andReturn(null);
		EasyMock.replay(addressBook);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Title should match", "A Sample Address Book", bufferedReader.readLine().trim());
		assertEquals("First name should match", "", bufferedReader.readLine().trim());
		assertEquals("Last name should match", "", bufferedReader.readLine().trim());
		assertEquals("Address should match", "", bufferedReader.readLine().trim());
		assertEquals("City should match", "", bufferedReader.readLine().trim());
		assertEquals("State should match", "", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveFileWithNullValueAtTheEnd() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		persons.add(person);
		persons.add(person);
		EasyMock.expect(addressBook.getTitle()).andReturn("A Sample Address Book").times(2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).times(2);
		EasyMock.expect(person.getFirstName()).andReturn("Josh").times(4);
		EasyMock.expect(person.getLastName()).andReturn("Sampleman").times(4);
		EasyMock.expect(person.getAddress()).andReturn("22 Big Road").times(4);
		EasyMock.expect(person.getCity()).andReturn("Miami").times(4);
		EasyMock.expect(person.getState()).andReturn("FL").times(4);
		EasyMock.expect(person.getZip()).andReturn("12890").times(4);
		EasyMock.expect(person.getPhone()).andReturn("123 234 3456").times(2);
		EasyMock.expect(person.getPhone()).andReturn(null);
		EasyMock.replay(addressBook);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempBook.txt");
		file.delete();
		fileSystem.saveFile(addressBook, file);
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person);
		
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
		assertEquals("First name should match", "Josh", bufferedReader.readLine().trim());
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
	public void testSaveContact() throws IOException {
		EasyMock.expect(person.getFirstName()).andReturn("Josh").times(2);
		EasyMock.expect(person.getLastName()).andReturn("Sampleman").times(2);
		EasyMock.expect(person.getAddress()).andReturn("22 Big Road").times(2);
		EasyMock.expect(person.getCity()).andReturn("Miami").times(2);
		EasyMock.expect(person.getState()).andReturn("FL").times(2);
		EasyMock.expect(person.getZip()).andReturn(null);
		EasyMock.expect(person.getPhone()).andReturn("123 234 3456").times(2);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempPrint.txt");
		file.delete();
		fileSystem.saveContact(person, file);
		
		EasyMock.verify(person);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Name should match", "Josh Sampleman", bufferedReader.readLine().trim());
		assertEquals("Address should match", "22 Big Road", bufferedReader.readLine().trim());
		assertEquals("City should match", "Miami", bufferedReader.readLine().trim());
		assertEquals("State should match", "FL", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "123 234 3456", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
	
	@Test
	public void testSaveContactWillNull() throws IOException {
		EasyMock.expect(person.getFirstName()).andReturn(null);
		EasyMock.expect(person.getLastName()).andReturn(null);
		EasyMock.expect(person.getAddress()).andReturn(null);
		EasyMock.expect(person.getCity()).andReturn(null);
		EasyMock.expect(person.getState()).andReturn(null);
		EasyMock.expect(person.getZip()).andReturn("12890").times(2);
		EasyMock.expect(person.getPhone()).andReturn(null);
		EasyMock.replay(person);
		
		File file = new File("testBooks/tempPrint.txt");
		file.delete();
		fileSystem.saveContact(person, file);
		
		EasyMock.verify(person);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Name should match", "", bufferedReader.readLine().trim());
		assertEquals("Address should match", "", bufferedReader.readLine().trim());
		assertEquals("City should match", "", bufferedReader.readLine().trim());
		assertEquals("State should match", "", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12890", bufferedReader.readLine().trim());
		assertEquals("Phone should match", "", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
	}
}
