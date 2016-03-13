package main.java.addressBook;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JPanel;

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
	public AddressBookController() {
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
		} else {
			throw new NullPointerException("No file specified.");
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
		} else if (file == null) {
			throw new NullPointerException("No file specified.");
		} else {
			throw new NullPointerException("No address book specified.");
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
	

	/**
	 * Prints the contact.
	 *
	 * @param info the info JPanel
	 */
	public void printContact(JPanel info) {
		PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable(new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum) {
		      if (pageNum > 0) {
		    	  return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      info.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if(pj.printDialog() == false) {
			  return;
		  }
		  
		  try {
		        pj.print();
		  } catch(PrinterException ex) {
		        // handle exception
		  }
	}
}
