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
	
	/**
	 * Instantiates a new address book.
	 */
	public AddressBook() {
		persons = new ArrayList<Person>();
	}
	
	/**
	 * Instantiates a new address book.
	 *
	 * @param ab the addressbook
	 */
	public AddressBook(AddressBook ab) {
		persons = new ArrayList<Person>(ab.getPersons());
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
	 * Gets the person.
	 *
	 * @param index the index
	 * @return the person
	 */
	public Person getPerson(int index) {
		return persons.get(index);
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
	 * Removes the person by index.
	 *
	 * @param index the index
	 */
	public void removePersonByIndex(int index) {
		this.persons.remove(index);
	}
	
	/**
	 * Sort address book by last name, then first name.
	 */
	public void sortAddressBookByName() {
		Collections.sort(persons, new Comparator<Person>() {
			@Override
            public int compare(Person lhs, Person rhs) {
				
				if(lhs.getFirstName() != null && rhs.getFirstName() != null){
					return lhs.getLastName().concat(lhs.getFirstName()).compareTo(rhs.getLastName().concat(rhs.getFirstName()));
				} else {
					return lhs.getLastName().compareTo(rhs.getLastName());
				}
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
	 * Searches the address book based on given parameters.
	 *
	 * @param person the person
	 */
	public void search(Person person) {

		List<Person> searchResult = new ArrayList<Person>();
		
		int contentCount = person.contentCount();
		if(contentCount == 0) {
			persons = searchResult;
			return;
		}
		
		int itemCount = 0;
		for(Person p : persons) {
			if(person.getFirstName() != null && p.getFirstName() != null 
					&& p.getFirstName().toLowerCase().contains(person.getFirstName().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount 
					&& person.getLastName() != null	&& p.getLastName() != null 
					&& p.getLastName().toLowerCase().contains(person.getLastName().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount && person.getAddress() != null && p.getAddress() != null 
					&& p.getAddress().toLowerCase().contains(person.getAddress().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount && person.getCity() != null && p.getCity() != null 
					&& p.getCity().toLowerCase().contains(person.getCity().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount && person.getState() != null && p.getState() != null 
					&& p.getState().toLowerCase().contains(person.getState().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount && person.getZip() != null && p.getZip() != null 
					&& p.getZip().toLowerCase().contains(person.getZip().toLowerCase())) {
				itemCount++;
			}
			if(itemCount < contentCount && person.getPhone() != null && p.getPhone() != null 
					&& p.getPhone().toLowerCase().contains(person.getPhone().toLowerCase())) {
				itemCount++;
			}
			
			if(itemCount == contentCount) {
				searchResult.add(p);
			}
			itemCount = 0;
		}
		persons = searchResult;
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
