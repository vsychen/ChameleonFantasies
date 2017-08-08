package test.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.business.control.ControlSuiteTest;
import test.business.entities.SessionTest;
import test.business.facade.FacadeSuiteTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ SessionTest.class, ControlSuiteTest.class, FacadeSuiteTest.class })

/***
 * Test suite for business entities, business control and facade.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class BusinessSuiteTest {
}