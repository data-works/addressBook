package addressBook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private List<Person> persons;
	
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
}
