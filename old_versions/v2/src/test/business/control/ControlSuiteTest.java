package test.business.control;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.business.control.accessControl.AccessControlSuiteTest;
import test.business.control.customerControl.CustomerControlAddSearchTest;
import test.business.control.employeeControl.EmployeeControlSuiteTest;
import test.business.control.fantasyControl.FantasyControlSuiteTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AccessControlSuiteTest.class, CustomerControlAddSearchTest.class, EmployeeControlSuiteTest.class,
		FantasyControlSuiteTest.class })

/***
 * Test suite for the business control.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class ControlSuiteTest {
}