package main.java.addressBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;

/**
 * The Class AddressBookGUI.
 */
public class AddressBookGUI {

	private AddressBookController controller;
	private AddressBook addressBook;
	private JFrame frame;
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

		frame = new JFrame("");
		sortByNameButton = new JButton("");
		sortByZipButton = new JButton("");
		openItem = new JMenuItem("");

		sortByNameButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				addressBook.sortAddressBookByName();
			}
		});

		sortByZipButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				addressBook.sortAddressBookByZip();
			}
		});
		
		openItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
				    File file = fileChooser.getSelectedFile();
				    try {
				    	
						setAddressBook(controller.getAddressBook(file));
					} catch (FileNotFoundException e1) {
						reportError("File not found");
					} catch (IOException e1) {
						reportError(e1.getMessage());
					}
				}
			}
		});
	}

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}
	
	public void reportError(String message) {
		
		
	}
	
	public void update(Observable o, Object arg) {
		
	}
}
