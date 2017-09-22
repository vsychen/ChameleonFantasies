package br.com.cf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.cf.domain.DomainTestSuite;
import br.com.cf.fachada.FachadaSmokeTest;
import br.com.cf.repository.RepositoryTestSuite;
import br.com.cf.service.ServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ DomainTestSuite.class, RepositoryTestSuite.class, ServiceTestSuite.class, FachadaSmokeTest.class })
public class ChameleonFantasiesModelTestSuite {
}