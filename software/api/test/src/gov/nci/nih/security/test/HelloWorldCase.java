package gov.nih.nci.security.test;

import junit.framework.TestCase;

public class HelloWorldCase extends TestCase {

public HelloWorldCase(String text)
{
	super (text);
}

public void testprint1()
{
	System.out.println("Hello World1");
	assertTrue( "TestExample", true );
}

public void testprint2()
{
	System.out.println("Hello World2");	
	assertTrue( "TestExample", true );
}
}
