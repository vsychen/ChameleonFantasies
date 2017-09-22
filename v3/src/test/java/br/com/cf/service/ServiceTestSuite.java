package br.com.cf.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteServiceUnitTest.class, FantasiaServiceUnitTest.class, FuncionarioServiceUnitTest.class })
public class ServiceTestSuite {
}