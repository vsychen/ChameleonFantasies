package test.data.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AddressTest.class, ClothTest.class, CustomerTest.class, EmployeeTest.class, FantasyTest.class })

/***
 * Test suite for entities. Each entity test will only test the constructor and
 * toString() method. The remaining methods of each class are the GETTER/SETTER
 * methods.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EntitiesSuiteTest {
}