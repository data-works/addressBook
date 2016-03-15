package main.java.addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * The Class FileSystem.
 */
public class FileSystem {

	/**
	 * Read file.
	 *
	 * @param file the file
	 * @return the address book
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AddressBook readFile(File file) throws FileNotFoundException, IOException {

		AddressBook addressBook = new AddressBook();

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;

		addressBook.setTitle(bufferedReader.readLine().trim());
		
		while ((line = bufferedReader.readLine()) != null) {

			// create new person and read the data from the file
			Person person = new Person();
			person.setFirstName(line.trim());
			person.setLastName(bufferedReader.readLine().trim());
			person.setAddress(bufferedReader.readLine().trim());
			person.setCity(bufferedReader.readLine().trim());
			person.setState(bufferedReader.readLine().trim());
			person.setZip(bufferedReader.readLine().trim());
			person.setPhone(bufferedReader.readLine().trim());
			
			// add person to address book
			addressBook.addPerson(person);
		}

		bufferedReader.close();
		
		return addressBook;
	}
	
	/**
	 * Save file.
	 *
	 * @param addressBook the address book
	 * @param file the file
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveFile(AddressBook addressBook, File file)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {

		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
		
		// save the address book title
		if(addressBook.getTitle() != null) {
			writer.write(addressBook.getTitle().trim() + "\n");
		} else {
			writer.write("\n");
		}
		
		// save each person
		int i = 0;
		for(Person person : addressBook.getPersons()) {
			
			if(person.getFirstName() != null) {
				writer.write(person.getFirstName() + "\n");
			} else {
				writer.write("\n");
			}
			
			if(person.getLastName() != null) {
				writer.write(person.getLastName() + "\n");
			} else {
				writer.write("\n");
			}
			
			if(person.getAddress() != null) {
				writer.write(person.getAddress() + "\n");
			} else {
				writer.write("\n");
			}
			
			if(person.getCity() != null) {
				writer.write(person.getCity() + "\n");
			} else {
				writer.write("\n");
			}
			
			if(person.getState() != null) {
				writer.write(person.getState() + "\n");
			} else {
				writer.write("\n");
			}
			
			if(person.getZip() != null) {
				writer.write(person.getZip() + "\n");
			} else {
				writer.write("\n");
			}
			
			// don't add a new line if it is the last person in the address book
			if(person.getPhone() == null){
				writer.write("\n");	
			} else {
				if(addressBook.getPersons().size()-1 != i) {
					writer.write(person.getPhone() + "\n");
				} else {
					writer.write(person.getPhone());
				}
			}

			i++;
		}
		
		writer.close();
	}
}
