package gov.nih.nci.logging.dao;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.util.*;

import junit.framework.TestCase;
import gov.nih.nci.logging.struts.QueryForm;

/**
 * Created On: April 29, 2004
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */
public class SummaryDaoJdbcTest extends TestCase
{

	/**
	 * Constructor for TransactionSummaryDaoJdbcImplTest.
	 * 
	 * @param arg0
	 */
	public SummaryDaoJdbcTest(String arg0)
	{
		super(arg0);
	}

	/**
	 * @throws Exception
	 */
	public void testValidSummaryRetrievalRequest() throws Exception
	{
		QueryForm query = new QueryForm();
		query.setStartDate("05/03/2004");
		query.setStartTime("10:00 AM");
		query.setMaxRecordCount("50");
		SummaryDaoJdbc sum = new SummaryDaoJdbc();
		// sum.setDbDriverClass( "com.mysql.jdbc.Driver" );
		// sum.setDbPwd( "candleSt1ck" );
		// sum.setDbUrl( "jdbc:mysql://localhost:3306/ggdev" );
		// sum.setDbUser( "bhusted" );

		List l = sum.findSummaries(query);
		System.out.println(l.size() + " Rows returned");
		if (l != null && l.size() > 0)
		{
			Iterator i = l.iterator();
			while (i.hasNext())
			{
				System.out.println(i.next());
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		new SummaryDaoJdbcTest("test").testValidSummaryRetrievalRequest();
	}

}