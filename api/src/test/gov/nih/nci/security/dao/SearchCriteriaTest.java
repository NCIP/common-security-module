// SearchCriteriaTest.java

package test.gov.nih.nci.security.dao;

import gov.nih.nci.security.dao.SearchCriteria;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * SearchCriteriaTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.dao.SearchCriteria </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     SearchCriteria extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:35:10 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.dao.SearchCriteria
 * @see some.other.package
 */

public class SearchCriteriaTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public SearchCriteriaTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //searchcriteria = new SearchCriteria();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    searchcriteria = null;
  }

  /**
   * Test the constructor: SearchCriteria()
   */
  public void testSearchCriteria() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(SearchCriteriaTest.class) );
  }
  private SearchCriteria searchcriteria;
}
