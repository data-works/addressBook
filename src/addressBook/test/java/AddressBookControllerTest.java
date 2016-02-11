package addressBook.test.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addressBook.main.java.AddressBook;
import addressBook.main.java.AddressBookController;

public class AddressBookControllerTest {

	private AddressBookController controller1, controller2;
	
	@Before
	public void setUp() throws Exception {
		controller1 = new AddressBookController("testBooks/collectionOfBooks");
		controller2 = new AddressBookController();
	}

	@After
	public void tearDown() throws Exception {
		controller1 = null;
		controller2 = null;
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
}
