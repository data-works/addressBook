package main.java.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AddressBookController.
 */
public class AddressBookController {
	
	private List<AddressBook> addressBooks;
	private String addressBookDirectory = "books";
	
	/**
	 * Instantiates a new address book controller.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AddressBookController() throws FileNotFoundException, IOException {
		addressBooks = getAddressBooksFromFiles(getAddressBookFiles());
	}
	
	/**
	 * Instantiates a new address book controller.
	 *
	 * @param addressBookDirectory the address book directory
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AddressBookController(String addressBookDirectory) throws FileNotFoundException, IOException {
		this.addressBookDirectory = addressBookDirectory;
		addressBooks = getAddressBooksFromFiles(getAddressBookFiles());
	}
	
	/**
	 * Gets the address book files.
	 *
	 * @return the address book files
	 */
	private File[] getAddressBookFiles() {
		return new File(addressBookDirectory).listFiles();
	}
	
	/**
	 * Gets the address books from files.
	 *
	 * @param files the files
	 * @return the address books from files
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<AddressBook> getAddressBooksFromFiles(File[] files) throws FileNotFoundException, IOException {
		
		List<AddressBook> addressBooks = new ArrayList<AddressBook>();
		
		FileSystem fileSystem = new FileSystem();
		
		for(File file : files) {
			AddressBook addressBook = fileSystem.readFile(file);
			addressBooks.add(addressBook);
		}
		
		return addressBooks;
	}
	
	/**
	 * List all address books.
	 *
	 * @return the list
	 */
	public List<AddressBook> listAllAddressBooks() {
		
		return addressBooks;
	}
}
