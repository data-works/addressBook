package main.java.addressBook;

import java.util.Observable;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;

/**
 * The Class AddressBookGUI.
 */
public class AddressBookGUI {

	private AddressBookController controller;
	private AddressBook addressBook;
	private JLabel addressBookTitle;
	private AbstractListModel nameListModel;
	private JList nameList;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton sortByNameButton;
	private JButton sortByZipButton;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem printItem;
	private JMenuItem quitItem;
	
	/**
	 * Instantiates a new address book GUI.
	 *
	 * @param controller the controller
	 * @param addressBook the address book
	 */
	public AddressBookGUI(AddressBookController controller, AddressBook addressBook) {
		this.controller = controller;
		this.addressBook = addressBook;
		
		addressBookTitle = new JLabel(addressBook.getTitle());
	}

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}
	
	/*public void reportError(String message) {
		
	}*/
	
	/*public void update(Observable o, Object arg) {
		
	}*/
}
