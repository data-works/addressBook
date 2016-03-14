package test.java.addressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.AddressBookGUI;
import main.java.addressBook.OkMockOptionPane;
import main.java.addressBook.Person;

public class AddressBookGUITest {

	AddressBookGUI gui;
	AddressBook addressBook;
	AddressBookController controller;
	Person person1, person2;
	List<Person> persons, onePerson;

	@Before
	public void setUp() throws Exception {
		addressBook = EasyMock.createMock("addressBook", AddressBook.class);
		controller = EasyMock.createMock("addressBookController", AddressBookController.class);
		gui = new AddressBookGUI(controller);
		gui.setAddressBook(addressBook);
		
		person1 = EasyMock.createMock("Person1", Person.class);
		person2 = EasyMock.createMock("Person2", Person.class);
		persons = new ArrayList<Person>();
		persons.add(person1);
		persons.add(person2);
		onePerson = new ArrayList<Person>();
		onePerson.add(person1);
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
		addressBook = null;
		controller = null;
		person1 = null;
		person2 = null;
		persons = null;
	}

	@Test
	public void testSetAddressBook() {
		gui.setAddressBook(addressBook);
		assertEquals("Address Book should have been selected", addressBook, gui.getAddressBook());
	}

	@Test
	public void testSortName() {		
		EasyMock.expect(person1.getFirstName()).andReturn("John");
		EasyMock.expect(person1.getLastName()).andReturn("Example");
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Sample");
		EasyMock.replay(person1);
		EasyMock.replay(person2);
		
		addressBook.sortAddressBookByName();
		EasyMock.expectLastCall().once();
		EasyMock.expect(addressBook.getPersons()).andReturn(persons);
		
		EasyMock.replay(addressBook);
		
		gui.sortByNameButton.setEnabled(true);
		gui.sortByNameButton.doClick();
		
		EasyMock.verify(addressBook);
		
		assertEquals("No item should be selected", -1, gui.nameList.getSelectedIndex());
		assertEquals("First list item should be correct", "John Example", gui.listModel.getElementAt(0));
		assertEquals("Second list item should be correct", "Jane Sample", gui.listModel.getElementAt(1));
	}

	@Test
	public void testSortZip() {
		EasyMock.expect(person1.getFirstName()).andReturn("John");
		EasyMock.expect(person1.getLastName()).andReturn("Example");
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Sample");
		EasyMock.replay(person1);
		EasyMock.replay(person2);
		
		addressBook.sortAddressBookByZip();
		EasyMock.expectLastCall().once();
		EasyMock.expect(addressBook.getPersons()).andReturn(persons);
		
		EasyMock.replay(addressBook);
		
		gui.sortByZipButton.setEnabled(true);
		gui.sortByZipButton.doClick();
		
		EasyMock.verify(addressBook);
		
		assertEquals("No item should be selected", -1, gui.nameList.getSelectedIndex());
		assertEquals("First list item should be correct", "John Example", gui.listModel.getElementAt(0));
		assertEquals("Second list item should be correct", "Jane Sample", gui.listModel.getElementAt(1));
	}

	@Test
	public void testDeleteButton() {
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(2);
		EasyMock.expect(person1.getLastName()).andReturn("Example").times(2);
		EasyMock.expect(person1.getAddress()).andReturn("123 Street");
		EasyMock.expect(person1.getCity()).andReturn("Boston");
		EasyMock.expect(person1.getState()).andReturn("MA");
		EasyMock.expect(person1.getZip()).andReturn("11111");
		EasyMock.expect(person1.getPhone()).andReturn("123 456");
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Sample");
		EasyMock.replay(person1);
		EasyMock.replay(person2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons);
		EasyMock.expect(addressBook.getPerson(0)).andReturn(person1);
		addressBook.removePersonByIndex(0);
		EasyMock.expectLastCall().once();
		EasyMock.replay(addressBook);
		
		gui.refreshAddressBook(addressBook);
		gui.nameList.setSelectedIndex(0);
		gui.setOptionPane(new OkMockOptionPane());
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person1);
		EasyMock.verify(person2);
		
		assertEquals("Address Book should have one less person in it", 1, gui.listModel.size());
		assertEquals("Address Book should not contain deleted person", "Jane Sample", 
				gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testDeleteButtonLastEntry() {
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(2);
		EasyMock.expect(person1.getLastName()).andReturn("Example").times(2);
		EasyMock.expect(person1.getAddress()).andReturn("123 Street");
		EasyMock.expect(person1.getCity()).andReturn("Boston");
		EasyMock.expect(person1.getState()).andReturn("MA");
		EasyMock.expect(person1.getZip()).andReturn("11111");
		EasyMock.expect(person1.getPhone()).andReturn("123 456");
		EasyMock.replay(person1);
		EasyMock.expect(addressBook.getPersons()).andReturn(onePerson);
		EasyMock.expect(addressBook.getPerson(0)).andReturn(person1);
		addressBook.removePersonByIndex(0);
		EasyMock.expectLastCall().once();
		EasyMock.replay(addressBook);
		
		gui.refreshAddressBook(addressBook);
		gui.nameList.setSelectedIndex(0);
		gui.setOptionPane(new OkMockOptionPane());
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person1);
		
		assertEquals("Address Book should have nobody in it", 0, gui.listModel.size());
		assertFalse("Delete button should be disabled", gui.deleteButton.isEnabled());
		assertFalse("Sort by name button should be disabled", gui.sortByNameButton.isEnabled());
		assertFalse("Sort by zip button should be disabled", gui.sortByZipButton.isEnabled());
		assertFalse("Edit button should be disabled", gui.editButton.isEnabled());
		assertFalse("Search button should be disabled", gui.searchButton.isEnabled());
	}

	@Test
	public void testDeleteButtonNoSelection() {
		EasyMock.expect(person1.getFirstName()).andReturn("John");
		EasyMock.expect(person1.getLastName()).andReturn("Example");
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Sample");
		EasyMock.replay(person1);
		EasyMock.replay(person2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons);
		EasyMock.replay(addressBook);
		
		gui.refreshAddressBook(addressBook);
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		
		// EasyMock will throw an AssertionError if removePersonByIndex() is called
		EasyMock.verify(addressBook);
		EasyMock.verify(person1);
		EasyMock.verify(person2);
		
		assertEquals("Address Book should not have deleted anybody", 2, gui.listModel.size());
	}

	@Test
	public void testDeleteButtonEmptyList() {
		
		EasyMock.replay(addressBook);
		
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		
		// EasyMock will throw an AssertionError if removePersonByIndex() is called
		EasyMock.verify(addressBook);
		
		assertEquals("Address Book should not have anybody in the list", 0, gui.listModel.size());
	}
	
	@Test
	public void testAddButton() {
		EasyMock.expect(addressBook.getPersons()).andReturn(persons);
		addressBook.addPerson(EasyMock.isA(Person.class));
		EasyMock.expectLastCall().once();
		EasyMock.replay(addressBook);
		
		EasyMock.expect(person1.getFirstName()).andReturn("Josh");
		EasyMock.expect(person1.getLastName()).andReturn("Example");
		EasyMock.expect(person2.getFirstName()).andReturn("Jane");
		EasyMock.expect(person2.getLastName()).andReturn("Sample");
		EasyMock.replay(person1);
		EasyMock.replay(person2);
		
		gui.addButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.fname.setText("John");
		gui.lname.setText("Sample");
		gui.addButton.doClick();
		
		EasyMock.verify(addressBook);
	}

	@Test
	public void testEditButton() {
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(6);
		EasyMock.expect(person1.getLastName()).andReturn("Example").times(6);
		EasyMock.expect(person1.getAddress()).andReturn("123 Street").times(4);
		EasyMock.expect(person1.getCity()).andReturn("Boston").times(4);
		EasyMock.expect(person1.getState()).andReturn("MA").times(4);
		EasyMock.expect(person1.getZip()).andReturn("11111").times(4);
		EasyMock.expect(person1.getPhone()).andReturn("123 456").times(4);
		person1.setFirstName("John");
		EasyMock.expectLastCall().once();
		person1.setLastName("Example");
		EasyMock.expectLastCall().once();
		person1.setAddress("123 Street");
		EasyMock.expectLastCall().once();
		person1.setCity("Boston");
		EasyMock.expectLastCall().once();
		person1.setState("MA");
		EasyMock.expectLastCall().once();
		person1.setZip("11111");
		EasyMock.expectLastCall().once();
		person1.setPhone("123 456");
		EasyMock.expectLastCall().once();
		EasyMock.replay(person1);
		
		EasyMock.expect(person2.getFirstName()).andReturn("Jane").times(2);
		EasyMock.expect(person2.getLastName()).andReturn("Sample").times(2);
		EasyMock.replay(person2);
		
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).times(2);
		EasyMock.expect(addressBook.getPerson(0)).andReturn(person1).times(3);
		EasyMock.replay(addressBook);
		gui.refreshAddressBook(addressBook);
		gui.nameList.setSelectedIndex(0);
		gui.editButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.editButton.doClick();
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person1);
		EasyMock.verify(person2);
		
		assertEquals("Entry should still be in the list", "John Example", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testSearchButton() {
		EasyMock.expect(person1.getFirstName()).andReturn("John").times(2);
		EasyMock.expect(person1.getLastName()).andReturn("Example").times(2);
		EasyMock.replay(person1);
		EasyMock.expect(person2.getFirstName()).andReturn("Jane").once();
		EasyMock.expect(person2.getLastName()).andReturn("Sample").once();
		EasyMock.replay(person2);
		EasyMock.expect(addressBook.getPersons()).andReturn(persons).once();
		EasyMock.expect(addressBook.getPersons()).andReturn(onePerson).once();
		addressBook.search(EasyMock.isA(Person.class));
		EasyMock.expectLastCall().once();
		EasyMock.replay(addressBook);
		
		gui.refreshAddressBook(addressBook);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("John");
		gui.searchButton.doClick();
		
		EasyMock.verify(addressBook);
		EasyMock.verify(person1);
		EasyMock.verify(person2);
		
		assertEquals("List should contain only one result", 1, gui.listModel.size());
		assertEquals("List should show the correct result", "John Example", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testEditTitle() {
		EasyMock.expect(addressBook.getTitle()).andReturn("Old Sample Title").once();
		addressBook.setTitle("New Sample Title");
		EasyMock.expectLastCall().once();
		EasyMock.expect(addressBook.getTitle()).andReturn("New Sample Title").once();
		EasyMock.replay(addressBook);
		gui.setOptionPane(new OkMockOptionPane());
		gui.editTitleItem.setEnabled(true);
		gui.editTitleItem.doClick();
		
		assertEquals("Title should have been changed", "New Sample Title", gui.addressBookTitle.getText());
	}
}
