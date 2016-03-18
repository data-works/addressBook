package test.java.addressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.addressBook.AddressBook;
import main.java.addressBook.AddressBookController;
import main.java.addressBook.AddressBookGUI;
import main.java.addressBook.OkMockOptionPane;
import main.java.addressBook.Person;

public class AddressBookGUIIT {

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
		person.setFirstName("John");
		person.setLastName("B");
		person.setZip("12346");
		addressBook1.addPerson(person);
		addressBook2.addPerson(person);
		
		person = new Person();
		person.setFirstName("Jane");
		person.setLastName("A");
		person.setZip("12345");
		addressBook1.addPerson(person);
		addressBook2.addPerson(person);
		
		controller = new AddressBookController();
		
		controller.setAddressBook(addressBook2);
		gui = new AddressBookGUI(controller);
		gui.setAddressBook(addressBook1);
		
		controller.setAddressBook(addressBook3);
		guiEmptyList = new AddressBookGUI(controller);
	}

	@After
	public void tearDown() throws Exception {
		gui = null;
		guiEmptyList = null;
		addressBook1 = null;
		addressBook2 = null;
		addressBook3 = null;
		controller = null;
	}
	
	@Test
	public void testSortName() {
		gui.sortByNameButton.setEnabled(true);
		gui.sortByNameButton.doClick();
		assertEquals("Address Book should be sorted by name", addressBook1.getPersons().get(0).getLastName(),
				gui.getAddressBook().getPersons().get(0).getLastName());
		assertEquals("No item should be selected", -1, gui.nameList.getSelectedIndex());
	}
	
	@Test
	public void testSortZip() {
		gui.sortByZipButton.setEnabled(true);
		gui.sortByZipButton.doClick();
		assertEquals("Address Book should be sorted by ZIP", "12345", gui.getAddressBook().getPersons().get(0).getZip());
	}
	
	@Test
	public void testDeleteButton() {
		Person tmpPerson = addressBook1.getPerson(0);
		gui.refreshAddressBook(addressBook1);
		gui.nameList.setSelectedIndex(0);
		int previousLength = gui.getAddressBook().getPersons().size();
		gui.setOptionPane(new OkMockOptionPane());
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		
		assertEquals("Address Book should have one less person in it", previousLength-1,
				gui.getAddressBook().getPersons().size());
		assertNotEquals("Address Book should not contain deleted person", tmpPerson, 
				gui.getAddressBook().getPersons().get(0));
	}
	
	@Test
	public void testDeleteButtonNoSelection() {
		gui.refreshAddressBook(addressBook1);
		gui.nameList.clearSelection();
		gui.deleteButton.setEnabled(true);
		gui.deleteButton.doClick();
		assertEquals("Nobody should be deleted from address book", addressBook1.getPersons().size(),
				gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testDeleteButtonEmptyList() {
		guiEmptyList.setAddressBook(addressBook3);
		guiEmptyList.refreshAddressBook(addressBook3);
		guiEmptyList.deleteButton.setEnabled(true);
		guiEmptyList.deleteButton.doClick();
		assertEquals("Address Book should remain empty and no exceptions being thrown", 0,
				guiEmptyList.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testAddButton() {	
		gui.refreshAddressBook(addressBook1);
		gui.addButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.fname.setText("John");
		gui.lname.setText("Sample");
		gui.addButton.doClick();
		
		assertEquals("First name should match", "John",	gui.getAddressBook().getPerson(2).getFirstName());
		assertEquals("Last name should match", "Sample", gui.getAddressBook().getPerson(2).getLastName());
		assertEquals("List item should match", "John Sample", gui.listModel.getElementAt(2));
	}
	
	@Test
	public void testAddFirstEmpty() {	
		gui.refreshAddressBook(addressBook1);
		gui.addButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.fname.setText("");
		gui.lname.setText("Sample");
		gui.addButton.doClick();
		
		assertEquals("No item should have been added", 2, gui.listModel.size());
	}
	
	@Test
	public void testAddLastEmpty() {	
		gui.refreshAddressBook(addressBook1);
		gui.addButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.fname.setText("John");
		gui.lname.setText("");
		gui.addButton.doClick();
		
		assertEquals("No item should have been added", 2, gui.listModel.size());
	}
	
	@Test
	public void testEditButton() {
		gui.refreshAddressBook(addressBook1);
		gui.nameList.setSelectedIndex(0);
		gui.editButton.setEnabled(true);
		gui.setOptionPane(new OkMockOptionPane());
		gui.editButton.doClick();
		
		assertEquals("Entry should still be in the list", "John B", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testSearchButton() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("Jane");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain one result", 1, gui.getAddressBook().getPersons().size());
		assertEquals("List should show the correct result", "Jane A", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testEditTitle() {
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.editTitleItem.setEnabled(true);
		gui.editTitleItem.doClick();
		
		assertEquals("Title should have been changed", "New Sample Text", gui.addressBookTitle.getText());
	}
	
	@Test
	public void testClearSearch() {
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("Jane");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain one result", 1, gui.getAddressBook().getPersons().size());
		assertEquals("List should show the correct result", "Jane A", gui.listModel.getElementAt(0));
		
		gui.clearSearchButton.setEnabled(true);
		gui.clearSearchButton.doClick();
		
		assertEquals("Search should have been cleared, full list displayed", 2, gui.getAddressBook().getPersons().size());
		assertEquals("Search should have been cleared, correct first person displayed", 
				"John B", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testOpenItem() throws FileNotFoundException, IOException {
		File file = new File("testBooks/sampleBook.txt");
		JFileChooser fileChooser = EasyMock.createMock("fileChooser", JFileChooser.class);
		gui.setFileChooser(fileChooser);
		
		EasyMock.expect(fileChooser.showOpenDialog(EasyMock.isA(Component.class))).andReturn(JFileChooser.APPROVE_OPTION).once();
		EasyMock.expect(fileChooser.getSelectedFile()).andReturn(file).once();
		EasyMock.replay(fileChooser);
	
		gui.openItem.doClick();
		
		assertEquals("New address book title should have been loaded", "A Sample Address Book", gui.addressBookTitle.getText());
		assertEquals("New address book people should have been loaded", 2, gui.getAddressBook().getPersons().size());
		assertEquals("Correct first person should have been loaded", "123 Sample Road", 
				gui.getAddressBook().getPerson(0).getAddress());
		assertEquals("Correct second person should have been loaded", "123 Main Street", 
				gui.getAddressBook().getPerson(1).getAddress());
		
		EasyMock.verify(fileChooser);
	}
	
	@Test
	public void testPrintItem() throws IOException {
		File file = new File("testBooks/tempPrint.txt");
		file.delete();
		
		gui.refreshAddressBook(addressBook1);
		gui.nameList.setSelectedIndex(0);
		
		JFileChooser fileChooser = EasyMock.createMock("fileChooser", JFileChooser.class);
		gui.setFileChooser(fileChooser);
		fileChooser.setSelectedFile(EasyMock.isA(File.class));
		EasyMock.expectLastCall().once();
		EasyMock.expect(fileChooser.showSaveDialog(EasyMock.isA(Component.class))).andReturn(JFileChooser.APPROVE_OPTION).once();
		EasyMock.expect(fileChooser.getSelectedFile()).andReturn(file).once();
		EasyMock.replay(fileChooser);
		gui.printItem.setEnabled(true);
		gui.printItem.doClick();
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		assertEquals("Name should match", "John B", bufferedReader.readLine().trim());
		assertEquals("Address should be blank", "", bufferedReader.readLine().trim());
		assertEquals("City should be blank", "", bufferedReader.readLine().trim());
		assertEquals("State should be blank", "", bufferedReader.readLine().trim());
		assertEquals("ZIP should match", "12346", bufferedReader.readLine().trim());
		assertEquals("Phone should be blank", "", bufferedReader.readLine().trim());

		bufferedReader.close();
		file.delete();
		
		EasyMock.verify(fileChooser);
	}
	
	@Test
	public void testSearch1() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("");
		gui.lname.setText("A");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain one result", 1, gui.getAddressBook().getPersons().size());
		assertEquals("List should show the correct result", "Jane A", gui.listModel.getElementAt(0));
	}
	
	@Test
	public void testSearch2() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("");
		gui.lname.setText("");
		gui.address.setText("Sample");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain no result", 0, gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testSearch3() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("");
		gui.lname.setText("");
		gui.address.setText("");
		gui.city.setText("Sample");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain no result", 0, gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testSearch4() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("");
		gui.lname.setText("");
		gui.address.setText("");
		gui.city.setText("");
		gui.zip.setText("Sample");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain no result", 0, gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testSearch5() {		
		gui.refreshAddressBook(addressBook1);
		gui.setOptionPane(new OkMockOptionPane());
		gui.searchButton.setEnabled(true);
		gui.fname.setText("");
		gui.lname.setText("");
		gui.address.setText("");
		gui.city.setText("");
		gui.zip.setText("");
		gui.phone.setText("Sample");
		gui.searchButton.doClick();
		
		assertEquals("Address book should contain no result", 0, gui.getAddressBook().getPersons().size());
	}
	
	@Test
	public void testSetPerson() {
		Person person = new Person();
		gui.nameList.clearSelection();
		gui.setPerson(person);
		
		assertEquals("Displayed text should not have changes", 
				"Please open an existing address book or create a new one using the File menu.", gui.labels[0].getText());
	}
}
