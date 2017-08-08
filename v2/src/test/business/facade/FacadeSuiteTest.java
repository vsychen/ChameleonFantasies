package test.business.facade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ FacadeAuxMethodsTest.class, FacadeFirstAccessTest.class, FacadeLoginTest.class,
		FacadeLogoutTest.class, FacadeAddEmployeeTest.class, FacadeAddCustomerTest.class, FacadeAddFantasyTest.class,
		FacadeEditEmployeeTest.class, FacadeBuySellFantasyTest.class, FacadeChangePriceTest.class,
		FacadeSeeCustomerTest.class, FacadeSeeEmployeeTest.class, FacadeSeeFantasyTest.class, FacadeRemoveTest.class,
		FacadeReportTest.class })

/***
 * Test suite for the facade methods.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeSuiteTest {
}