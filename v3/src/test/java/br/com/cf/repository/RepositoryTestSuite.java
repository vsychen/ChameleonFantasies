package br.com.cf.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteDAOUnitTest.class, FantasiaDAOUnitTest.class, FuncionarioDAOUnitTest.class })
public class RepositoryTestSuite {
}