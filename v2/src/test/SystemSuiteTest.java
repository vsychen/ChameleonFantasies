package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.business.BusinessSuiteTest;
import test.data.DataSuiteTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ DataSuiteTest.class, BusinessSuiteTest.class })

/***
 * Test suite for data layer and business layer.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class SystemSuiteTest {
}