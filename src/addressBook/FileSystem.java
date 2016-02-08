package addressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSystem {

	public FileSystem() {
		
	}

	public AddressBook readFile(File file) throws FileNotFoundException, IOException {

		AddressBook addressBook = new AddressBook();


		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;

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

			// empty line for readability
			bufferedReader.readLine();
		}

		bufferedReader.close();
		
		return addressBook;
	}
	
	public void saveFile(AddressBook addressBook, File file) {
		
	}
}
