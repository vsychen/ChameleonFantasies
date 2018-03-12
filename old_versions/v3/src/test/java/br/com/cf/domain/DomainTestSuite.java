package br.com.cf.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteSmokeTest.class, FantasiaSmokeTest.class, FuncionarioSmokeTest.class })
public class DomainTestSuite {
}