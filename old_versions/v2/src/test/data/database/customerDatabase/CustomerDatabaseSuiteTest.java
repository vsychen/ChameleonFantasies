package test.data.database.customerDatabase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ CustomerDatabaseAddTest.class, CustomerDatabaseSearchTest.class, CustomerDatabaseEditTest.class,
		CustomerDatabaseRemoveTest.class, CustomerDatabaseListTest.class })

/***
 * Test suite for the customer database.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseSuiteTest {
}