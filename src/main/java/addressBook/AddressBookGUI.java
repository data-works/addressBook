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
import java.util.Observable;

import javax.swing.AbstractListModel;
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
	private File file;
	private JFrame frame;
	public JMenuBar menuBar;
	public JMenu menu;
	private JLabel addressBookTitle;
	private AbstractListModel nameListModel;
	private JLabel personInfo;
	public JList nameList;
	private DefaultListModel<String> listModel;
	private JButton addButton;
	private JButton editButton;
	public JButton deleteButton;
	public JButton sortByNameButton;
	public JButton sortByZipButton;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem printItem;
	private JMenuItem quitItem;
	private JScrollPane scrollPane;
	
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
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		sortByNameButton = new JButton("Sort by Name");
		sortByZipButton = new JButton("Sort by ZIP");
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		newItem = new JMenuItem("New", UIManager.getIcon("InternalFrame.icon"));
		openItem = new JMenuItem("Open", UIManager.getIcon("FileView.directoryIcon"));
		saveItem = new JMenuItem("Save", UIManager.getIcon("FileView.floppyDriveIcon"));
		saveAsItem = new JMenuItem("Save As...", UIManager.getIcon("FileView.floppyDriveIcon"));
		printItem = new JMenuItem("Print", UIManager.getIcon("FileView.fileIcon"));
		quitItem = new JMenuItem("Quit", UIManager.getIcon("InternalFrame.paletteCloseIcon"));
		listModel = new DefaultListModel<>();
		
		menu.setMnemonic(KeyEvent.VK_F);
		newItem.setMnemonic(KeyEvent.VK_N);
		openItem.setMnemonic(KeyEvent.VK_O);
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveAsItem.setMnemonic(KeyEvent.VK_A);
		printItem.setMnemonic(KeyEvent.VK_P);
		quitItem.setMnemonic(KeyEvent.VK_Q);
		menu.add(newItem);
		menu.add(openItem);
		menu.add(saveItem);
		menu.add(saveAsItem);
		menu.add(printItem);
		menu.add(quitItem);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		/**
		 * Begin adding content to GUI
		 * NOTE: This will change. This is a temporary layout.
		 * I don't particularly like the Grid Bag Layout, so it is only temporary.
		 */
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		addressBookTitle = new JLabel(addressBook.getTitle());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
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
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 10;
		c.gridwidth = 2;
		
		/**
		 * Add all people to list
		 */
		for (Person p : addressBook.getPersons()) {
			listModel.addElement(p.getFirstName() + " " + p.getLastName());
		}
		nameList = new JList<>(listModel);
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.setSelectedIndex(0);
		
		scrollPane = new JScrollPane(nameList);
		scrollPane.setPreferredSize(new Dimension(0, 400));
		panel.add(scrollPane, c);
		
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		personInfo = new JLabel("INFO HERE");
		panel.add(personInfo, c);
		
		/**
		 * Setup some properties of the window
		 */
		frame.add(panel, BorderLayout.NORTH);
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		//frame.pack();
		frame.setResizable(false);
        frame.setVisible(true);
        
		/**
		 * Eventually, this will display the selected perons's info on the side
		 */
		nameList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  personInfo.setText("INFO");
                }
            }
        });
        
        // ActionListeners
        
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
		 * NOTE: Currently does not work. More time will be spent
		 * correcting this soon.
		 */
		addButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				Person newPerson = new Person();
		        JTextField fname = new JTextField("");
		        JTextField lname = new JTextField("");
		        JTextField address = new JTextField("");
		        JTextField city = new JTextField("");
		        JTextField state = new JTextField("");
		        JTextField zip = new JTextField("");
		        JTextField phone = new JTextField("");
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
		        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
		        	newPerson.setFirstName(fname.getText());
		        	newPerson.setLastName(lname.getText());
		        	newPerson.setAddress(address.getText());
		        	newPerson.setCity(city.getText());
		        	newPerson.setState(state.getText());
		        	newPerson.setZip(zip.getText());
		        	newPerson.setPhone(phone.getText());
		        	addressBook.addPerson(newPerson);
		        	refreshAddressBook(addressBook);
		        } else {
		        	
		        }
			}
		});
		
		deleteButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				if (!listModel.isEmpty() && !nameList.isSelectionEmpty()) {
					JPanel panel = new JPanel(new GridLayout(0, 1));
					JLabel warning = new JLabel("Are you sure you want to delete the selected contact?");
					panel.add(warning);
					int result = JOptionPane.showConfirmDialog(null, panel, "Delete",
				            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						addressBook.removePersonByIndex(nameList.getSelectedIndex());
						listModel.removeElementAt(nameList.getSelectedIndex());
			        } else {
			        	
			        }
				}
			}
		});
		
		/*
		editButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				
				// TODO: Popup to allow user to change person info
				
			}
		});
		*/
		
		/*
		newItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {

				// TODO: Popup to ask user for name of new address book
				
			}
		});
		*/
		
		openItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
				    file = fileChooser.getSelectedFile();
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
		
		saveItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				try {
					controller.saveAddressBook(file);
				} catch (FileNotFoundException e1) {
					reportError("File not found");
				} catch (IOException e1) {
					reportError(e1.getMessage());
				}
			}
		});
		
		saveAsItem.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showSaveDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
				    file = fileChooser.getSelectedFile();
				    try {
				    	controller.saveAddressBook(file);
					} catch (FileNotFoundException e1) {
						reportError("File not found");
					} catch (IOException e1) {
						reportError(e1.getMessage());
					}
				}
			}
		});
	}
	
	public void refreshAddressBook(AddressBook ab) {
		listModel.removeAllElements();
		for (Person p : ab.getPersons()) {
			listModel.addElement(p.getFirstName() + " " + p.getLastName());
		}
	}

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
	 * Report error to user.
	 *
	 * @param message the message
	 */
	public void reportError(String message) {
		
		// TODO: Popup to display error message
	}
	
	public void update(Observable o, Object arg) {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		File file = new File("books/sampleBookLong.txt");
		
		AddressBookController controller = new AddressBookController();
		AddressBook book = controller.getAddressBook(file);
		
		AddressBookGUI gui = new AddressBookGUI(controller, book);
	}
}
