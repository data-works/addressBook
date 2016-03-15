package main.java.addressBook;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The Class AddressBookGUI.
 */
public class AddressBookGUI {

	private AddressBookController controller;
	private AddressBook addressBook;
	private AddressBook storedAddressBook; // for clear search button
	private File file;
	private JFrame frame;
	public JMenuBar menuBar;
	public JMenu menu;
	public JLabel addressBookTitle;
	public JList nameList;
	public DefaultListModel<String> listModel;
	public JButton addButton;
	public JButton editButton;
	public JButton deleteButton;
	public JButton sortByNameButton;
	public JButton sortByZipButton;
	public JButton searchButton;
	public JButton clearSearchButton; // TODO
	public JMenuItem newItem;
	public JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	public JMenuItem editTitleItem;
	private JMenuItem printItem;
	public JMenuItem quitItem;
	private JScrollPane scrollPane;
	private JPanel info;
	private ListSelectionModel listSelection;
	private OptionPane optionPane;
	private JLabel labels[];
	public JTextField fname;
	public JTextField lname;
	public JTextField address;
	public JTextField city;
	public JTextField state;
	public JTextField zip;
	public JTextField phone;
	public boolean changesMade;
	private boolean hasSavedAs;
	private JFileChooser fileChooser;
	
	/**
	 * Instantiates a new address book GUI.
	 *
	 * @param controller the controller
	 * @param addressBook the address book
	 */
	public AddressBookGUI(AddressBookController controller) {
		this.controller = controller;
		fileChooser = new JFileChooser();
		hasSavedAs = false;
		changesMade = false;

		frame = new JFrame("");
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		sortByNameButton = new JButton("Sort by Name");
		sortByZipButton = new JButton("Sort by ZIP");
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		searchButton = new JButton("Search");
		clearSearchButton = new JButton("Clear Search");
		newItem = new JMenuItem("New", UIManager.getIcon("InternalFrame.icon"));
		openItem = new JMenuItem("Open", UIManager.getIcon("FileView.directoryIcon"));
		saveItem = new JMenuItem("Save", UIManager.getIcon("FileView.floppyDriveIcon"));
		saveAsItem = new JMenuItem("Save As...", UIManager.getIcon("FileView.floppyDriveIcon"));
		editTitleItem = new JMenuItem("Edit Title", UIManager.getIcon("FileChooser.detailsViewIcon"));
		printItem = new JMenuItem("Print", UIManager.getIcon("FileView.fileIcon"));
		quitItem = new JMenuItem("Quit", UIManager.getIcon("InternalFrame.paletteCloseIcon"));
		listModel = new DefaultListModel<>();
		optionPane = new ConfirmationOptionPane();
        fname = new JTextField();
        lname = new JTextField();
        address = new JTextField();
        city = new JTextField();
        state = new JTextField();
        zip = new JTextField();
        phone = new JTextField();
		
		//Keyboard shortcuts
		menu.setMnemonic(KeyEvent.VK_F);
		newItem.setMnemonic(KeyEvent.VK_N);
		openItem.setMnemonic(KeyEvent.VK_O);
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveAsItem.setMnemonic(KeyEvent.VK_A);
		editTitleItem.setMnemonic(KeyEvent.VK_E);
		printItem.setMnemonic(KeyEvent.VK_P);
		quitItem.setMnemonic(KeyEvent.VK_Q);
		
		menu.add(newItem);
		menu.add(openItem);
		menu.add(editTitleItem);
		menu.add(saveItem);
		menu.add(saveAsItem);
		menu.add(printItem);
		menu.add(quitItem);
		menuBar.add(menu);
		
		frame.setJMenuBar(menuBar);
		setMenuEnabled(false);
		
		/**
		 * Begin adding content to GUI
		 * NOTE: This will change. This is a temporary layout.
		 * I don't particularly like the Grid Bag Layout, so it is only temporary.
		 */
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		addressBookTitle = new JLabel();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(addressBookTitle, c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(addButton, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(deleteButton, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(sortByNameButton, c);
		
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(sortByZipButton, c);
		
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(editButton,c );
		
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(searchButton,c );
		
		c.gridx = 6;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		clearSearchButton.setEnabled(false);
		panel.add(clearSearchButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 10;
		c.gridwidth = 2;
		
		nameList = new JList<>(listModel);
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.setSelectedIndex(0);
		
		scrollPane = new JScrollPane(nameList);
		scrollPane.setPreferredSize(new Dimension(0, 400));
		panel.add(scrollPane, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 5;
		
		info = new JPanel(new GridLayout(0, 1));
		
		labels = new JLabel[7];
		for (int i = 0; i < 4; i++) {
			labels[i] = new JLabel();
			info.add(labels[i]);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setVerticalAlignment(JLabel.CENTER);
			labels[i].setFont(labels[i].getFont().deriveFont(14.0f));
		}
		
		labels[0].setText("Please open an existing address book or create a new one using the File menu.");
		
		panel.add(info, c);
		
		/**
		 * Setup some properties of the window
		 */
		frame.add(panel, BorderLayout.NORTH);
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
        frame.setVisible(true);
        
        // ActionListeners
        
		/**
		 * Displays the selected perons's info on the side
		 */
		nameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	if (!nameList.isSelectionEmpty()) {
                		Person person = addressBook.getPerson(nameList.getSelectedIndex());
                		setPerson(person);
                	}
                }
            }
        });
        
		/**
		 * Sort address book by Last Name
		 */
		sortByNameButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				addressBook.sortAddressBookByName();
				refreshAddressBook(addressBook);
			}
		});

		/**
		 * Sort address book by ZIP code
		 */
		sortByZipButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				addressBook.sortAddressBookByZip();
				refreshAddressBook(addressBook);
			}
		});
		
		/**
		 * Add a new person to the address book.
		 */
		addButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				Person newPerson = new Person();
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("First Name:"));
		        panel.add(fname);
		        panel.add(new JLabel("Last Name:"));
		        panel.add(lname);
		        panel.add(new JLabel("Street Address:"));
		        panel.add(address);
		        panel.add(new JLabel("City:"));
		        panel.add(city);
		        panel.add(new JLabel("State:"));
		        panel.add(state);
		        panel.add(new JLabel("ZIP Code:"));
		        panel.add(zip);
		        panel.add(new JLabel("Phone Number:"));
		        panel.add(phone);
		        int result = optionPane.showConfirmDialog(null, panel, "New Contact",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        
		        if(result == JOptionPane.OK_OPTION && !fname.getText().isEmpty() && !lname.getText().isEmpty()) {
		        	newPerson.setFirstName(fname.getText());
		        	newPerson.setLastName(lname.getText());
		        	newPerson.setAddress(address.getText());
		        	newPerson.setCity(city.getText());
		        	newPerson.setState(state.getText());
		        	newPerson.setZip(zip.getText());
		        	newPerson.setPhone(phone.getText());
		        	addressBook.addPerson(newPerson);
		        	refreshAddressBook(addressBook);
					deleteButton.setEnabled(true);
					sortByNameButton.setEnabled(true);
					sortByZipButton.setEnabled(true);
					editButton.setEnabled(true);
					searchButton.setEnabled(true);
					changesMade = true;
		        } else if(result == JOptionPane.OK_OPTION && (fname.getText().isEmpty() || lname.getText().isEmpty())) {
		        	displayPopup("First and last name are mandatory fields. New contact was not created.");
		        } else {
		        	displayPopup("Action cancelled. New contact was not created.");
		        }
		        fname.setText(null);
		        lname.setText(null);
		        address.setText(null);
		        city.setText(null);
		        state.setText(null);
		        zip.setText(null);
		        phone.setText(null);
			}
		});
		
		/**
		 * Deletes the selected person from the address book.
		 */
		deleteButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				if(!listModel.isEmpty() && !nameList.isSelectionEmpty()) {
					JPanel panel = new JPanel(new GridLayout(0, 1));
					JLabel warning = new JLabel("Are you sure you want to delete the selected contact?");
					panel.add(warning);
					int result = optionPane.showConfirmDialog(null, panel, "Delete Contact",
				            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						addressBook.removePersonByIndex(nameList.getSelectedIndex());
						listModel.removeElementAt(nameList.getSelectedIndex());
						for (int i = 0; i < 4; i++) {
							labels[i].setText("");
						}
						if(listModel.getSize() == 0) {
							deleteButton.setEnabled(false);
							sortByNameButton.setEnabled(false);
							sortByZipButton.setEnabled(false);
							editButton.setEnabled(false);
							searchButton.setEnabled(false);
							clearSearchButton.setEnabled(false);
						}
						changesMade = true;
			        } else {
			        	displayPopup("Deletion cancelled.");
			        }
				}
			}
		});
		
		/**
		 * Popup to allow user to change person info
		 */
		editButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				if(!listModel.isEmpty() && !nameList.isSelectionEmpty()) {
					Person person = addressBook.getPerson(nameList.getSelectedIndex());
	                
			        fname.setText(person.getFirstName());
			        lname.setText(person.getLastName());
			        address.setText(person.getAddress());
			        city.setText(person.getCity());
			        state.setText(person.getState());
			        zip.setText(person.getZip());
			        phone.setText(person.getPhone());
			        JPanel panel = new JPanel(new GridLayout(0, 1));
			        panel.add(new JLabel("First Name:"));
			        panel.add(fname);
			        panel.add(new JLabel("Last Name:"));
			        panel.add(lname);
			        panel.add(new JLabel("Street Address:"));
			        panel.add(address);
			        panel.add(new JLabel("City:"));
			        panel.add(city);
			        panel.add(new JLabel("State:"));
			        panel.add(state);
			        panel.add(new JLabel("ZIP Code:"));
			        panel.add(zip);
			        panel.add(new JLabel("Phone Number:"));
			        panel.add(phone);
			        int result = optionPane.showConfirmDialog(null, panel, "Edit Contact",
			            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			        if(result == JOptionPane.OK_OPTION && !fname.getText().isEmpty() && !lname.getText().isEmpty()) {
			        	person.setFirstName(fname.getText());
			        	person.setLastName(lname.getText());
			        	person.setAddress(address.getText());
			        	person.setCity(city.getText());
			        	person.setState(state.getText());
			        	person.setZip(zip.getText());
			        	person.setPhone(phone.getText());        	
			        	refreshWithSelection(addressBook, nameList.getSelectedIndex());
			        	setPerson(person);
			        	changesMade = true;
			        } else if(result == JOptionPane.OK_OPTION && (fname.getText().isEmpty() || lname.getText().isEmpty())) {
			        	displayPopup("First and last name are mandatory fields. Changes were not saved.");
			        } else {
			        	displayPopup("Action cancelled. Changes were not saved.");
			        }
			        fname.setText(null);
			        lname.setText(null);
			        address.setText(null);
			        city.setText(null);
			        state.setText(null);
			        zip.setText(null);
			        phone.setText(null);
				}
			}
		});
		
		// Search for specific contacts
		searchButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				
				Person person = new Person();
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("First Name:"));
		        panel.add(fname);
		        panel.add(new JLabel("Last Name:"));
		        panel.add(lname);
		        panel.add(new JLabel("Street Address:"));
		        panel.add(address);
		        panel.add(new JLabel("City:"));
		        panel.add(city);
		        panel.add(new JLabel("State:"));
		        panel.add(state);
		        panel.add(new JLabel("ZIP Code:"));
		        panel.add(zip);
		        panel.add(new JLabel("Phone Number:"));
		        panel.add(phone);
		        int result = optionPane.showConfirmDialog(null, panel, "Search Contact",
		        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        
		        if(result == JOptionPane.OK_OPTION && (!fname.getText().isEmpty() || !lname.getText().isEmpty() ||
		        		!address.getText().isEmpty() || !city.getText().isEmpty() || !state.getText().isEmpty() ||
		        		!zip.getText().isEmpty() || !phone.getText().isEmpty())) {
		        	person.setFirstName(fname.getText());
		        	person.setLastName(lname.getText());
		        	person.setAddress(address.getText());
		        	person.setCity(city.getText());
		        	person.setState(state.getText());
		        	person.setZip(zip.getText());
		        	person.setPhone(phone.getText());
		        	clearSearchButton.setEnabled(true);
			        searchButton.setEnabled(false);
			        addButton.setEnabled(false);
					deleteButton.setEnabled(false);
			        storedAddressBook = new AddressBook(addressBook);		  
			        // TODO: Add alert if no results?
			        addressBook.search(person);
			        refreshAddressBook(addressBook);
		        } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
		        	displayPopup("Search cancelled.");
		        } else {
		        	displayPopup("At least one field needs to be filled out to search for a contact.");	        		      
		        }
		        fname.setText(null);
		        lname.setText(null);
		        address.setText(null);
		        city.setText(null);
		        state.setText(null);
		        zip.setText(null);
		        phone.setText(null);
			}
		});
		
		// Clear the search
		clearSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = optionPane.showConfirmDialog(null, "Do you want to clear the search?", "Clear Search",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					addressBook = storedAddressBook;
					storedAddressBook = null;
					controller.setAddressBook(addressBook);
					refreshAddressBook(addressBook);
					searchButton.setEnabled(true);
					clearSearchButton.setEnabled(false);
					addButton.setEnabled(true);
					deleteButton.setEnabled(true);
				} else {
					displayPopup("The search has not been cleared.");
				}
			}
		});
		
		// Create a new addressBook
		newItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				String title = optionPane.showInputDialog(null, "Please enter the title of the new address book:",
						"Title", JOptionPane.PLAIN_MESSAGE);
				if(title == null) {
					JOptionPane.showMessageDialog(frame, "Creation of new address book was cancelled.");
				} else if(title.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "The title cannot be empty. The address book was not created.");
				} else {
					setAddressBook(new AddressBook());
					addressBook.setTitle(title);
					refreshAddressBook(addressBook);
					refreshGuiTitle();
					setMenuEnabled(true);
					displayMessageNoSelection();
					changesMade = true;
				}
			}
		});
		
		// Open an existing addressBook from a file
		openItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				//JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
				    file = fileChooser.getSelectedFile();
				    try {
				    	controller.loadAddressBook(file);
						setAddressBook(controller.getAddressBook());						
						refreshAddressBook(addressBook);		
						refreshGuiTitle();
						setMenuEnabled(true);
						displayMessageNoSelection();
						hasSavedAs = true;
					} catch (FileNotFoundException e1) {
						displayPopup("File not found");
					} catch (IOException e1) {
						displayPopup("Error: " + e1.getMessage());
					}
				}
			}
		});

		// Save the current addressBook under the same name
		saveItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				if (!hasSavedAs) {
					saveAsAddressBook();
				}
				else {
					saveAddressBook();
				}
			}
		});

		// Save the current addressBook under a different name
		saveAsItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				saveAsAddressBook();
			}
		});
		
		// Edit the title of the addressBook
		editTitleItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				String title = optionPane.showInputDialog(null, "Please edit the title of the address book:", 
						addressBook.getTitle(), JOptionPane.PLAIN_MESSAGE);
				if(title == null) {
					JOptionPane.showMessageDialog(frame, "Action cancelled. Title has not been changed.");
				} else if(title.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "The title cannot be empty. Title has not been changed.");
				} else {
					addressBook.setTitle(title);
					refreshGuiTitle();
					changesMade = true;
				}
			}
		});
		
		// Print the current selected contact in the address book
		printItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				controller.printContact(info);
			}
		});
		
		// Close the application
		quitItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				if(changesMade) {
					int result = optionPane.showConfirmDialog(null, "There are unsaved changes. Are you sure you want to exit the program?", "Exit",
				            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						System.exit(0);
			        }
				} else {
					int result = optionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit",
				            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						System.exit(0);
			        }
				}
			}
		});
	}
	
	/**
	 * Will keep the selected person's info in the side bar
	 * 
	 * @param p
	 */
    public void setPerson(Person p){
    	if(!nameList.isSelectionEmpty()) {
            labels[0].setText(p.getFirstName() + " " + p.getLastName());
            labels[1].setText(p.getAddress());
            labels[2].setText(p.getCity() + " " + p.getState() + " " + p.getZip());
            labels[3].setText(p.getPhone());
    	}
    }
	
	/**
	 * Refresh address book.
	 *
	 * @param ab the adressBook
	 */
	public void refreshAddressBook(AddressBook ab) {
		listModel.removeAllElements();
		for (Person p : ab.getPersons()) {
			listModel.addElement(p.getFirstName() + " " + p.getLastName());
		}
		nameList.clearSelection();
	}
	
	/**
	 * Refresh address book while keeping the currently selected item selected.
	 * 
	 * @param addressBook
	 * @param index
	 */
	public void refreshWithSelection(AddressBook ab, int index) {
		listModel.removeAllElements();
		for (Person p : ab.getPersons()) {
			listModel.addElement(p.getFirstName() + " " + p.getLastName());
		}
		nameList.setSelectedIndex(index);
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
	 * Refresh address book contents. Used for sorting and other things.
	 * @param addressBook
	 */
	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}
	
	/**
	 * Sets the stored address book.
	 *
	 * @param addressBook the new stored address book
	 */
	public void setStoredAddressBook(AddressBook addressBook) {
		this.storedAddressBook = addressBook;
	}
	
	/**
	 * Report error to user.
	 *
	 * @param message the message
	 */
	public void displayPopup(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	/**
	 * Sets the option pane. This option may be used for testing to use a mock OptionPane.
	 *
	 * @param o the new option pane
	 */
	public void setOptionPane(OptionPane o) {
		this.optionPane = o; 
	}
	
	/**
	 * Refresh gui title.
	 */
	public void refreshGuiTitle() {
		this.addressBookTitle.setText(addressBook.getTitle());
	}
	
	/**
	 * Save the address book.
	 */
	public void saveAddressBook() {
		try {
			controller.setAddressBook(addressBook);
			controller.saveAddressBook(file);
			changesMade = false;
			displayPopup("Address book has been saved.");
		} catch (FileNotFoundException e1) {
			displayPopup("File not found");
		} catch (IOException e1) {
			displayPopup(e1.getMessage());
		}
	}
	
	/**
	 * Save the address book asking the user for the path and name.
	 */
	public void saveAsAddressBook() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(addressBook.getTitle() + ".txt"));
		int option = fileChooser.showSaveDialog(frame);
		if (option == JFileChooser.APPROVE_OPTION) {
		    file = fileChooser.getSelectedFile();
		    saveAddressBook();
		    hasSavedAs = true;
		}
	}
	
	/**
	 * Displays a message when no contact has been selected.
	 */
	public void displayMessageNoSelection() {
		if(addressBook.getPersons().size() == 0) {
			labels[0].setText("Please create a new contact.");
		} else {
			labels[0].setText("Please select a contact from the left or create a new contact.");
		}
	}
	
	/**
	 * Enables or disables the menu.
	 *
	 * @param bool the enabled boolean
	 */
	public void setMenuEnabled(Boolean bool) {
		saveItem.setEnabled(bool);
		saveAsItem.setEnabled(bool);
		editTitleItem.setEnabled(bool);
		printItem.setEnabled(bool);
		addButton.setEnabled(bool);
		deleteButton.setEnabled(bool);
		sortByNameButton.setEnabled(bool);
		sortByZipButton.setEnabled(bool);
		editButton.setEnabled(bool);
		searchButton.setEnabled(bool);
	}
	
	/**
	 * Sets the file chooser.
	 *
	 * @param fileChooser the new file chooser
	 */
	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		AddressBookController controller = new AddressBookController();
		AddressBookGUI gui = new AddressBookGUI(controller);
	}
}
