package test.data.database.fantasyDatabase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ FantasyDatabaseAddTest.class, FantasyDatabaseSearchTest.class, FantasyDatabaseEditTest.class,
		FantasyDatabaseRemoveTest.class, FantasyDatabaseListTest.class })

/***
 * Test suite for the fantasy database.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseSuiteTest {
}