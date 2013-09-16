/**
 *
 */
package test.gov.nih.nci.security.dao;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.nih.nci.security.dao.Queries;


/**
 * @author Murty Sanku
 *
 */
public class QueriesTest extends Queries{

	// Change the values of the variables per the testing need
	// Variables for the local/test database connection
	static final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/caarray3";
	static final String USER = "sysadmin1";
	static final String PASS = "sysadmin1";
	// All the values below are input parameters and available in caArray database
	// Note that some of these variables are declared of different types by the queries
	static final String uid = "3";
	static final long lUID = 3;
	static final String grpid = "17";
	static final int appid = 2;
	static final long lAppID = 2;
	static final int peid = 2;
	static final String prgrName = "PE(8989) group";
	static final String uName = "collaborator";
	static final String grName = "Biostatistician";
	static final String objid = "gov.nih.nci.caarray.domain.project.Project";
	static final String privName = "READ";
	static final String attrName = "id";
	static final String attrValue = "9998";
	// Variable for data file creation
	// This directory should pre-exist before running the test
	static final String dataDir = "C:\\Temp\\QueriesJUnit\\Modified\\";
	static final String fldX = "X";

	// These are used internally by the code
	static Connection conn = null;
	static StringBuffer strb = new StringBuffer();
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static long timeBefore;
	static long timeAfter;
	static long timeTaken;
	static String dbFld1;
	static String dbFld2;

	
	
	// Utility method to create the data file, note the three parameters.
	public static void writeDataToFile(ResultSet dataRS, String fld1, String fileName) throws SQLException {
		
        try {
            
            FileWriter fileWriter = new FileWriter(dataDir+fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while(dataRS.next()) {
            	dbFld1 = dataRS.getString(fld1);
            	bufferedWriter.write(dbFld1);
            	bufferedWriter.newLine();
            	
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}

	// Utility method to create the data file, note the four parameters.
	public static void writeDataToFile(ResultSet dataRS, String fld1, String fld2, String fileName) throws SQLException {
		
        try {
            
            FileWriter fileWriter = new FileWriter(dataDir+fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while(dataRS.next()) {
            	dbFld1 = dataRS.getString(fld1);
            	dbFld2 = dataRS.getString(fld2);
            	bufferedWriter.write(dbFld1+", "+dbFld2);
            	bufferedWriter.newLine();
            	
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}

	// Utility method to create the data file, note the two parameters.
	public static void writeDataToFile(StringBuffer sb, String fileName) {
		
        try {
            
            FileWriter fileWriter = new FileWriter(dataDir+fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sb.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		strb.append("***************************************************");
		strb.append("\nThe following results are for the MODIFIED queries.");
		strb.append("\n***************************************************");
		strb.append("\n\nConnected to "+ DB_URL+"\n");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		strb.append("\n\nConnection to the database closed.");
		System.out.println(strb.toString());
		String dataFileName = "SummaryTimeResultsForAllModifiedQueries.txt";
		writeDataToFile(strb, dataFileName);
		conn.close();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		strb.append("\n Now testing the method ... ");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		strb.append("  ...  " + timeTaken + " millisec.");
		//strb.append("\n Method done.");
		rs.close();
		rs = null;
		ps.close();
		ps = null;
		dbFld1 = null;
		dbFld2 = null;
		timeBefore = 0;
		timeAfter = 0;
		timeTaken = 0;

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForUserAndGroupForAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForUserAndGroupForAttribute() throws SQLException {

		strb.append("getQueryForUserAndGroupForAttribute");
		ps = Queries.getQueryForUserAndGroupForAttribute(uName, objid, attrName, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForUserAndGroupForAttribute_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForUserAndGroupForAttributeValue(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForUserAndGroupForAttributeValue() throws SQLException {

		strb.append("getQueryForUserAndGroupForAttributeValue");
		ps = Queries.getQueryForUserAndGroupForAttributeValue(uName, objid, attrName, attrValue, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForUserAndGroupForAttributeValue_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);
		
	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForUserAndGroup(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForUserAndGroup() throws SQLException {

		strb.append("getQueryForCheckPermissionForUserAndGroup");
		ps = Queries.getQueryForCheckPermissionForUserAndGroup(uName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForUserAndGroup_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForGroup(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForGroup() throws SQLException {

		strb.append("getQueryForCheckPermissionForGroup");
		ps = Queries.getQueryForCheckPermissionForGroup(uName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForGroup_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForOnlyGroup(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForOnlyGroup3() throws SQLException {

		strb.append("getQueryForCheckPermissionForOnlyGroup5param");
		// Note the parameters being passed
		ps = Queries.getQueryForCheckPermissionForOnlyGroup(grName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForOnlyGroup5param_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForAccessibleGroups(java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForAccessibleGroups() throws SQLException {

		strb.append("getQueryForAccessibleGroups");
		ps = Queries.getQueryForAccessibleGroups(objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForAccessibleGroups_Results.txt";
		writeDataToFile(rs, "group_id", dataFileName);
		
	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForAccessibleGroupsWithAttribute(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForAccessibleGroupsWithAttribute() throws SQLException {

		strb.append("getQueryForAccessibleGroupsWithAttribute");
		ps = Queries.getQueryForAccessibleGroupsWithAttribute(objid, attrName, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForAccessibleGroupsWithAttribute_Results.txt";
		writeDataToFile(rs, "group_id", dataFileName);
		
	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForOnlyGroup(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForOnlyGroup2() throws SQLException {

		strb.append("getQueryForCheckPermissionForOnlyGroup6param");
		// Note the parameters being passed
		ps = Queries.getQueryForCheckPermissionForOnlyGroup(grName, objid, attrName, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForOnlyGroup6param_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForOnlyGroup(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForOnlyGroup() throws SQLException {

		strb.append("getQueryForCheckPermissionForOnlyGroup7param");
		// Note the parameters being passed
		ps = Queries.getQueryForCheckPermissionForOnlyGroup(grName, objid, attrName, attrValue, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForOnlyGroup7param_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForCheckPermissionForUser(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForCheckPermissionForUser() throws SQLException {

		strb.append("getQueryForCheckPermissionForUser");
		ps = Queries.getQueryForCheckPermissionForUser(uName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForCheckPermissionForUser_Results.txt";
		writeDataToFile(rs, fldX, dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryForObjectMap(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryForObjectMap() throws SQLException {

		strb.append("getQueryForObjectMap");
		ps = Queries.getQueryForObjectMap(uName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryForObjectMap_Results.txt";
		writeDataToFile(rs, "attribute", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforUserPEPrivilegeMap(java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforUserPEPrivilegeMap() throws SQLException {

		strb.append("getQueryforUserPEPrivilegeMap");
		ps = Queries.getQueryforUserPEPrivilegeMap(uid, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforUserPEPrivilegeMap_Results.txt";
		writeDataToFile(rs, "pe_id", "p_id", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforGroupPEPrivilegeMap(java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforGroupPEPrivilegeMap() throws SQLException {

		strb.append("getQueryforGroupPEPrivilegeMap");
		ps = Queries.getQueryforGroupPEPrivilegeMap(grpid, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforGroupPEPrivilegeMap_Results.txt";
		writeDataToFile(rs, "pe_id", "p_id", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforUserAttributeMap(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforUserAttributeMap() throws SQLException {

		strb.append("getQueryforUserAttributeMap");
		ps = Queries.getQueryforUserAttributeMap(uName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforUserAttributeMap_Results.txt";
		writeDataToFile(rs, "attribute", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforGroupAttributeMap(java.lang.String, java.lang.String, java.lang.String, int, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforGroupAttributeMap() throws SQLException {

		strb.append("getQueryforGroupAttributeMap");
		ps = Queries.getQueryforGroupAttributeMap(grName, objid, privName, appid, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforGroupAttributeMap_Results.txt";
		writeDataToFile(rs, "attribute", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforUptOperationPE(java.lang.String, java.lang.String, java.lang.Long, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforUptOperationPE() throws SQLException {

		strb.append("getQueryforUptOperationPE");
		ps = Queries.getQueryforUptOperationPE(objid, uName, lAppID, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforUptOperationPE_Results.txt";
		writeDataToFile(rs, "protection_element_id", dataFileName);

	}

	/**
	 * Test method for {@link gov.nih.nci.security.dao.Queries#getQueryforLinkPEUser(java.lang.String, int, java.lang.Long, java.lang.Long, java.sql.Connection)}.
	 */
	@Test
	public void testGetQueryforLinkPEUser() throws SQLException {

		strb.append("getQueryforLinkPEUser");
		ps = Queries.getQueryforLinkPEUser(prgrName, peid, lUID, lAppID, conn);
		timeBefore = System.currentTimeMillis();
		rs = ps.executeQuery();
		timeAfter = System.currentTimeMillis();
		timeTaken = timeAfter - timeBefore;

		String dataFileName = "queryforLinkPEUser_Results.txt";
		writeDataToFile(rs, "protection_element_id", dataFileName);

	}

}
