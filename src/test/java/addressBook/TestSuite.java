package test.java.addressBook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PersonTest.class,
	AddressBookTest.class, 
	FileSystemTest.class,
	AddressBookControllerTest.class,
	AddressBookGUITest.class,
	AddressBookIT.class, 
	AddressBookControllerIT.class,
	FileSystemIT.class,
	AddressBookGUIIT.class
})
public class TestSuite {

}