package test.business.control.accessControl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AccessControlLoginTest.class, AccessControlLogoutTest.class, AccessControlRemoveTest.class,
		AccessControlReportTest.class })

/***
 * Test suite for the access control methods.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class AccessControlSuiteTest {
}