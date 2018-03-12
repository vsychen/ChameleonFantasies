package test.data.database;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.data.database.customerDatabase.CustomerDatabaseSuiteTest;
import test.data.database.employeeDatabase.EmployeeDatabaseSuiteTest;
import test.data.database.fantasyDatabase.FantasyDatabaseSuiteTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ CustomerDatabaseSuiteTest.class, EmployeeDatabaseSuiteTest.class,
		FantasyDatabaseSuiteTest.class })

/***
 * Test suite for the databases.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class DatabaseSuiteTest {
}