package addressBook.test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	AddressBookTest.class, 
	FileSystemTest.class,
	AddressBookControllerTest.class,
	AddressBookGUITest.class
})
public class TestSuite {

}