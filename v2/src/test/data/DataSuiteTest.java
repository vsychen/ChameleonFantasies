package test.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.data.database.DatabaseSuiteTest;
import test.data.entities.EntitiesSuiteTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ EntitiesSuiteTest.class, DatabaseSuiteTest.class })

/***
 * Test suite for entities and database.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class DataSuiteTest {
}