package addressBook.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	AddressBookTest.class, 
	FileSystemTest.class 
})
public class TestSuite {

}