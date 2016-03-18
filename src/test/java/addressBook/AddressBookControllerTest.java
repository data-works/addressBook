package test.java.addressBook;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.FileSystem;
import main.java.addressBook.Person;

public class AddressBookControllerTest {

	private AddressBookController controller;
	private AddressBook addressBook;
	private FileSystem fileSystem;
	private File file;
	private Person person;

	@Before
	public void setUp() throws Exception {
		file = new File("testBooks/tempBook.txt");
		fileSystem = EasyMock.createMock("FileSystem", FileSystem.class);
		controller = new AddressBookController();
		addressBook = EasyMock.createMock("addressBook", AddressBook.class);
		person = EasyMock.createMock("Person", Person.class);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		addressBook = null;
		fileSystem = null;
		file = null;
		person = null;
	}

	@Test
	public void testLoadAddressBook() throws FileNotFoundException, IOException {
		EasyMock.expect(fileSystem.readFile(file)).andReturn(addressBook);
		EasyMock.replay(fileSystem);

		controller.setFileSystem(fileSystem);
		controller.loadAddressBook(file);

		assertEquals("Should have loaded the correct address book", addressBook, controller.getAddressBook());
	}
	
	@Test(expected=NullPointerException.class)
	public void testLoadAddressBookNull() throws FileNotFoundException, IOException {
		controller.loadAddressBook(null);
	}

	@Test
	public void testSetAddressBook() throws FileNotFoundException, IOException {
		controller.setAddressBook(addressBook);
		assertEquals("Should have added the address book", addressBook, controller.getAddressBook());
	}

	@Test
	public void testSaveAddressBook() throws IOException {
		controller.setFileSystem(fileSystem);
		fileSystem.saveFile(addressBook, file);

		EasyMock.expectLastCall().once();
		EasyMock.replay(fileSystem);

		controller.setAddressBook(addressBook);
		controller.saveAddressBook(file);

		EasyMock.verify(fileSystem);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSaveAddressBookNull() throws IOException {
		controller.setAddressBook(null);
		controller.saveAddressBook(file);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSaveFileNull() throws IOException {
		controller.setAddressBook(addressBook);
		controller.saveAddressBook(null);
	}
	
	@Test
	public void testSetFileSystem() {
		controller.setFileSystem(fileSystem);
		assertEquals("Should have set the correct FileSystem", fileSystem, controller.getFileSystem());
	}
	
	@Test
	public void testPrintContact() throws IOException {
		controller.setFileSystem(fileSystem);
		fileSystem.saveContact(person, file);
		EasyMock.expectLastCall().once();
		EasyMock.replay(fileSystem);
		
		controller.printContact(person, file);

		EasyMock.verify(fileSystem);
	}
}
