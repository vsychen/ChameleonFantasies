package test.data.database.employeeDatabase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ EmployeeDatabaseAddTest.class, EmployeeDatabaseSearchTest.class, EmployeeDatabaseEditTest.class,
		EmployeeDatabaseRemoveTest.class, EmployeeDatabaseListTest.class })

/***
 * Test suite for the employee database.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseSuiteTest {
}