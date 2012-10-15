package gov.nih.nci.security.test;

import junit.framework.Test;
import junit.framework.TestSuite;

	public class ApiTestControlSuite {
	/**
	 * Create static runnable instance
	 * @return
	 */
	public  static Test suite(){
		TestSuite suite = new TestSuite();
		System.out.println("ApiTestControlSuite.suite()..test runnable");
		suite.addTestSuite(HelloWorldCase.class);
		return suite;
	}
}
