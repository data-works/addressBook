package addressBook.test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addressBook.main.java.AddressBook;
import addressBook.main.java.AddressBookController;
import addressBook.main.java.AddressBookGUI;

public class AddressBookGUITest {

	AddressBookGUI gui;
	AddressBook addressBook1, addressBook2;
	AddressBookController controller;
	
	@Before
	public void setUp() throws Exception {
		addressBook1 = new AddressBook();
		addressBook2 = new AddressBook();
		addressBook1.setTitle("Address Book 1");
		addressBook2.setTitle("Address Book 2");
		controller = new AddressBookController();
		gui = new AddressBookGUI(controller, addressBook2);
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
	}

	@Test
	public void testSetAddressBook() {
		gui.setAddressBook(addressBook2);
		assertEquals("Address Book should have been selected", addressBook2.getTitle(), gui.getAddressBook().getTitle());
	}

}