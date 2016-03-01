package main.java.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Initialize {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		AddressBookController controller = new AddressBookController();
		AddressBook book = controller.getAddressBook();
		/**
		 * Adding a bunch of dummy persons for testing
		 */
		Person person = new Person();
		person.setFirstName("Matt");
		person.setLastName("Sinex");
		person.setPhone("(555) 555-5555");
		person.setAddress("1234 FGCU ln");
		person.setCity("Fort Myers");
		person.setState("FL");
		person.setZip("33911");
		book.addPerson(person);
		Person person2 = new Person();
		person2.setFirstName("Tomas");
		person2.setLastName("Hoelzel");
		person2.setPhone("(555) 555-5555");
		person2.setAddress("1234 FGCU ln");
		person2.setCity("Fort Myers");
		person2.setState("FL");
		person2.setZip("33912");
		book.addPerson(person2);
		Person person3 = new Person();
		person3.setFirstName("Bryce");
		person3.setLastName("Mueller");
		person3.setPhone("(555) 555-5555");
		person3.setAddress("1234 FGCU ln");
		person3.setCity("Fort Myers");
		person3.setState("FL");
		person3.setZip("33907");
		book.addPerson(person3);
		Person person4 = new Person();
		person4.setFirstName("Isaac");
		person4.setLastName("Gainey");
		person4.setPhone("(555) 555-5555");
		person4.setAddress("1234 FGCU ln");
		person4.setCity("Fort Myers");
		person4.setState("FL");
		person4.setZip("33908");
		book.addPerson(person4);
		
		// Start the app
		AddressBookGUI gui = new AddressBookGUI(controller, book);
	}

}
