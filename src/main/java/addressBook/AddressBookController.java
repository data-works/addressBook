package main.java.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * The Class AddressBookController.
 */
public class AddressBookController {
	
	private AddressBook addressBook;
	private FileSystem fileSystem;
	
	/**
	 * Instantiates a new address book controller.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AddressBookController() throws FileNotFoundException, IOException {
		addressBook = new AddressBook();
		fileSystem = new FileSystem();
	}
	
	/**
	 * Gets the address book.
	 *
	 * @param file the file
	 * @return the address book
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadAddressBook(File file) throws FileNotFoundException, IOException {
		if(file != null) {
			addressBook = fileSystem.readFile(file);
		}
	}
	
	/**
	 * Save address book.
	 *
	 * @param file the file
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveAddressBook(File file) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		if(file != null && addressBook != null) {
			fileSystem.saveFile(addressBook, file);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Gets the address book.
	 *
	 * @return the address book
	 */
	public AddressBook getAddressBook() {
		return addressBook;
	}

	/**
	 * Sets the address book.
	 *
	 * @param addressBook the new address book
	 */
	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}
	
	/**
	 * Sets the file system.
	 *
	 * @param fileSystem the new file system
	 */
	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}
	
	/**
	 * Gets the file system.
	 *
	 * @return the file system
	 */
	public FileSystem getFileSystem() {
		return fileSystem;
	}
}
