package addressBook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private List<Person> persons;
	
	public AddressBook() {
		persons = new ArrayList<Person>();
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	public void addPerson(Person person) {
		this.persons.add(person);
	}
}
