package main.java.addressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Class AddressBook.
 */
public class AddressBook {

	private List<Person> persons;
	private String title;
	
	public AddressBook() {
		persons = new ArrayList<Person>();
	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}
	
	/**
	 * Adds the person.
	 *
	 * @param person the person
	 */
	public void addPerson(Person person) {
		this.persons.add(person);
	}
	
	/**
	 * Removes the person.
	 *
	 * @param person the person
	 */
	public void removePerson(Person person) {
		this.persons.remove(person);
	}
	
	/**
	 * Sort address book by last name, then first name.
	 */
	public void sortAddressBookByName() {
		Collections.sort(persons, new Comparator<Person>() {
			@Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getLastName().concat(lhs.getFirstName()).compareTo(rhs.getLastName().concat(rhs.getFirstName()));
            }
		});
	}
	
	/**
	 * Sort address book by zip.
	 */
	public void sortAddressBookByZip() {
		Collections.sort(persons, new Comparator<Person>() {
			@Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getZip().compareTo(rhs.getZip());
            }
		});
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
