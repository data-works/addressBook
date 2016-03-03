package test.java.addressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.AddressBookGUI;
import main.java.addressBook.Person;

public class AddressBookGUITest {

	AddressBookGUI gui, guiEmptyList;
	AddressBook addressBook1, addressBook2, addressBook3;
	AddressBookController controller;
	
	@Before
	public void setUp() throws Exception {
		addressBook1 = new AddressBook();
		addressBook2 = new AddressBook();
		addressBook3 = new AddressBook();
		addressBook1.setTitle("Address Book 1");
		addressBook2.setTitle("Address Book 2");
		
		Person person = new Person();
		person.setLastName("B");
		person.setZip("12346");
		addressBook1.addPerson(person);
		addressBook2.addPerson(person);
		
		person = new Person();
		person.setZip("12345");
		person.setLastName("A");
		addressBook1.addPerson(person);
		addressBook2.addPerson(person);
		
		controller = new AddressBookController();
		gui = new AddressBookGUI(controller, addressBook2);
		guiEmptyList = new AddressBookGUI(controller, addressBook3);
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
	
	@Test
	public void testSortName() {
		gui.sortByNameButton.doClick();
		assertEquals("Address Book should be sorted by name", addressBook1.getPersons().get(1).getLastName(),
				gui.getAddressBook().getPersons().get(0).getLastName());
	}
	
	@Test
	public void testSortZip() {
		gui.sortByZipButton.doClick();
		assertEquals("Address Book should be sorted by ZIP", "12345", gui.getAddressBook().getPersons().get(0).getZip());
	}
	
	@Test
	public void testDeleteButton() {
		gui.nameList.setSelectedIndex(0);
		int previousLength = gui.getAddressBook().getPersons().size();
		gui.deleteButton.doClick();
		assertEquals("Address Book should have one less person in it", previousLength-1, gui.getAddressBook().getPersons().size());
		assertNotEquals("Address Book should not contain deleted person", addressBook1.getPersons().get(0).getLastName(), 
				gui.getAddressBook().getPersons().get(0).getLastName());
	}
	
	@Test
	public void testDeleteButtonNoSelection() {
		gui.nameList.clearSelection();
		gui.deleteButton.doClick();
		assertEquals("Nobody should be deleted from address book", addressBook1.getPersons().size(),
				gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testDeleteButtonEmptyList() {
		guiEmptyList.deleteButton.doClick();
		assertEquals("Address Book should remain empty and no exceptions being thrown", 0,
				guiEmptyList.getAddressBook().getPersons().size());
	}
}
