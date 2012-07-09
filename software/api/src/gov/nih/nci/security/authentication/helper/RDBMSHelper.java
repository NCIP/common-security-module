package gov.nih.nci.security.authentication.helper;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */


import gov.nih.nci.security.authentication.principal.EmailIdPrincipal;
import gov.nih.nci.security.authentication.principal.FirstNamePrincipal;
import gov.nih.nci.security.authentication.principal.LastNamePrincipal;
import gov.nih.nci.security.authentication.principal.LoginIdPrincipal;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.exceptions.internal.CSInternalConfigurationException;
import gov.nih.nci.security.exceptions.internal.CSInternalInsufficientAttributesException;
import gov.nih.nci.security.util.StringEncrypter;
import gov.nih.nci.security.util.StringUtilities;
import gov.nih.nci.security.util.StringEncrypter.EncryptionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import javax.security.auth.Subject;

import org.apache.log4j.Logger;

/**
 *
 * This is a helper class which is used to perform all JDBC operations like
 * connecting to the database, executing queries etc. This is a static class and
 * provides a single helper method.
 *
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class RDBMSHelper {

	private static final Logger log = Logger.getLogger(RDBMSHelper.class);

	/**
	 * Default Private Class Constructor
	 *
	 */
	private RDBMSHelper()
	{
	}

	/**
	 * Accepts the connection properties as well as the user id and password.
	 * It opens a connection to the database and fires a the query to find. If
	 * the query was successful then it returns TRUE else it returns FALSE
	 * @param connectionProperties table containing details for establishing
	 * 			connection like the driver, the url, the user name and the
	 * 			password for the establishing the database connection. It also
	 * 			contains the actual query statement to retrieve the user record
	 * @param userID the user entered user name provided by the calling
	 * 			application
	 * @param password the user entered password provided by the calling
	 * 			application
	 * @param subject
	 * @return TRUE if the authentication was sucessful using the provided user
	 * 		   	credentials and FALSE if the authentication fails
	 * @throws CSInternalConfigurationException
	 * @throws CSInternalInsufficientAttributesException
	 */
	public static boolean authenticate (Hashtable connectionProperties, String userID, char[] password, Subject subject) throws CSInternalConfigurationException, CSInternalInsufficientAttributesException
	{
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		String encryptedPassword= new String(password);
		String encryptionEnabled = (String)connectionProperties.get(Constants.ENCRYPTION_ENABLED);
		
		encryptedPassword = StringUtilities.initTrimmedString(encryptPassword(encryptedPassword,encryptionEnabled ));

		String query = (String)connectionProperties.get("query");
		if (!StringUtilities.isBlank(query))
		{
			return executeQuery(connection, query, userID,encryptedPassword);
		}
		else
		{
			return authenticateAndObtainSubject(connection, connectionProperties, userID, encryptedPassword, subject);
		}

	}


	private static String encryptPassword(String encryptedPassword,
			String encryptionEnabled) {
		if (!StringUtilities.isBlank(encryptionEnabled) && encryptionEnabled.equalsIgnoreCase(Constants.YES)){
			StringEncrypter se;
			try {
				se = new StringEncrypter();
				encryptedPassword = se.encrypt(new String(encryptedPassword));
			} catch (EncryptionException e) {				
				e.printStackTrace();
			}
		}
		return encryptedPassword;
	}

	private static boolean authenticateAndObtainSubject(Connection connection, Hashtable connectionProperties, String userID, String password, Subject subject) throws CSInternalInsufficientAttributesException, CSInternalConfigurationException
	{

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean loginOK = false;

		String tableName = (String)connectionProperties.get(Constants.TABLE_NAME);

		String userNameColumn = (String)connectionProperties.get(Constants.USER_LOGIN_ID);
		String passwordColumn = (String)connectionProperties.get(Constants.USER_PASSWORD);
		String lastNameColumn = (String)connectionProperties.get(Constants.USER_LAST_NAME);
		String firstNameColumn = (String)connectionProperties.get(Constants.USER_FIRST_NAME);
		String emailIdColumn = (String)connectionProperties.get(Constants.USER_EMAIL_ID);

		String query = new String();

		query = "SELECT " + userNameColumn + ", " + firstNameColumn + ", " + lastNameColumn + ", " + emailIdColumn + " FROM " + tableName + " WHERE " + userNameColumn + " = ? " + "AND " + passwordColumn + " = ?";

		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
			statement.setString(2,password);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to validate user credentials");
		}

		try
		{
			resultSet = statement.executeQuery();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to validate user credentials");
		}
		if (resultSet != null)
		{
			try
			{
				while(resultSet.next())
				{
					String firstName = resultSet.getString(firstNameColumn);
					if (!StringUtilities.isBlank(firstName))
						subject.getPrincipals().add(new FirstNamePrincipal(firstName));
					else
						throw new CSInternalInsufficientAttributesException("User Attribute First Name not found");
					String lastName = resultSet.getString(lastNameColumn);
					if (!StringUtilities.isBlank(lastName))
						subject.getPrincipals().add(new LastNamePrincipal(lastName));
					else
						throw new CSInternalInsufficientAttributesException("User Attribute Last Name not found");
					String emailId = resultSet.getString(emailIdColumn);
					if (!StringUtilities.isBlank(emailId))
						subject.getPrincipals().add(new EmailIdPrincipal(emailId));
					else
						throw new CSInternalInsufficientAttributesException("User Attribute Email Id not found");

					subject.getPrincipals().add(new LoginIdPrincipal(userID));

					loginOK = true;
					break;
				}
			}
			catch (SQLException e)
			{
				throw new CSInternalConfigurationException("Unable to execute the query to validate user credentials");
			}
		}
		try
		{
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| Login is "+loginOK+" for the user "+userID+"and password "+password+"|");
		return loginOK;
	}

	/**
	 * Accepts the connection object, the query string and the user credentials
	 * and queries the database to retrieve the user record. It first prepares a
	 * statement from the connection and the query passed as parameter. It
	 * replaces the user id and password in the statement and executes the same.
	 * If a record it retrieved from the database it indicates that the user
	 * credentail provided are correct and a TRUE is returned. Finally the
	 * database resources like connection, statement etc. are freed and released
	 * @param connection the connection obtained using the connection properties
	 * 			provided in the configuration
	 * @param query the actual query statement which is used to retrieve the
	 * 			user record from the database
	 * @param userID the user entered user name provided by the calling
	 * 			application
	 * @param password the user entered password provided by the calling
	 * 			application
	 * @return TRUE if the querying is successful and the user record is
	 * 			retrieved using the provided credentials
	 * @throws CSInternalConfigurationException
	 *
	 */
	private static boolean executeQuery(Connection connection, String query, String userID, String password) throws CSInternalConfigurationException
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean loginOK = false;
		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
			statement.setString(2,password);
			resultSet = statement.executeQuery();
			if (resultSet != null)
			{
				while(resultSet.next())
				{
					loginOK = true;
					break;
				}
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
			throw new CSInternalConfigurationException("Unable to execute the query to validate user credentials");
		}
		finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException sqe)
			{
				if (log.isDebugEnabled())
					log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| Login is "+loginOK+" for the user "+userID+"and password "+password+"|");
		return loginOK;
	}

	/**
	 * Accepts the connection properties which are used to connect to the
	 * database. The established connection is then returned to the calling
	 * function. The properties should have the following items - "driver",
	 * "url", "user" and "passwd".
	 * @param connectionProperties contains the properties needed to connect to
	 * 			the database. It contains the values for the following items
	 * 			the driver class to be used, the url of the database to connect
	 * 			the user name and the password to be used to connect to the
	 * 			database
	 * @return a connection to the database obtained using the properties
	 * 			provided
	 * @throws CSInternalConfigurationException
	 *
	 */
	private static Connection getConnection (Hashtable connectionProperties) throws CSInternalConfigurationException
	{
		if (connectionProperties == null)
			return null;

		String driver 	= (String)connectionProperties.get("driver");
		String url 		= (String)connectionProperties.get("url");
		String user 	= (String)connectionProperties.get("user");
		String passwd 	= (String)connectionProperties.get("passwd");

		Connection connection = null;

		// load the driver, if it is not loaded
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			if (log.isDebugEnabled())
				log.debug("Authentication|||getConnection|Failure| Error Loading Driver for Authentication Database|"+ e.getMessage());
			throw new CSInternalConfigurationException ("Unable to Load Driver for Authentication Database");
		}

		// Get the connection to the database
		try
		{
			connection = DriverManager.getConnection(url, user, passwd);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			if (log.isDebugEnabled())
				log.debug("Authentication|||getConnection|Failure| Error Obtaining Connection for Authentication Database|"+ e.getMessage());
			throw new CSInternalConfigurationException ("Unable to obtain connection for Authentication Database");
		}
		if (log.isDebugEnabled())
			log.debug("Authentication|||getConnection|Success| Success in Obtaining Connection for Authentication Database|");
		return connection;
	}

	public static boolean isPasswordExpired(Hashtable connectionProperties, String userID) throws CSInternalConfigurationException {
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean passwordExpired = false;
	
		String query = new String();

		query = "SELECT PASSWORD_EXPIRED FROM CSM_USER WHERE LOGIN_NAME = ? ";

		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to check if the password is expired ");
		}

		try
		{
			resultSet = statement.executeQuery();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to check if the password is expired ");
		}
		if (resultSet != null)
		{
			try
			{
				while(resultSet.next())
				{
					 passwordExpired = resultSet.getBoolean("PASSWORD_EXPIRED");
					
				}
			}
			catch (SQLException e)
			{
				throw new CSInternalConfigurationException("Unable to execute the query to validate user credentials");
			}
		}
		try
		{
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| Password expired is "+passwordExpired+" for the user");
		return passwordExpired;				
	}


	public static boolean changePassword(Hashtable connectionProperties, String userID, String password) throws CSInternalConfigurationException {
		
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		PreparedStatement statement = null;
		boolean passwordExpired = false;
		String encryptionEnabled = (String)connectionProperties.get(Constants.ENCRYPTION_ENABLED);
		
		String query = new String();
		query = "UPDATE CSM_USER SET PASSWORD = ?,PASSWORD_EXPIRED = FALSE WHERE LOGIN_NAME = ? ";
		
		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, encryptPassword(password,encryptionEnabled ));
			statement.setString(2, userID);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to check if the password is expired ");
		}

		try
		{
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to check if the password is expired ");
		}
		try
		{
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| Password expired is "+passwordExpired+" for the user");
	
		return true;				
	}

	public static boolean insertIntoPasswordHistory(Hashtable connectionProperties, String userID, char[] password) throws CSInternalConfigurationException {
		
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		PreparedStatement statement = null;
		boolean passwordExpired = false;
		String encryptionEnabled = (String)connectionProperties.get(Constants.ENCRYPTION_ENABLED);
		
		String query = new String();
		query = "INSERT INTO CSM_PASSWORD_HISTORY (LOGIN_NAME, PASSWORD) VALUES (?, ?) ";
		
		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
			statement.setString(2, encryptPassword(new String(password),encryptionEnabled ));			
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to check if the password is expired ");
		}

		try
		{
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to check if the password is expired ");
		}
		try
		{
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| Password expired is "+passwordExpired+" for the user");
	
		return true;				
	}

	
	public static boolean isFirstTimeLogin(Hashtable connectionProperties, String userID) throws CSInternalConfigurationException {
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean firstTimeLogin = false;
	
		String query = new String();

		query = "SELECT FIRST_TIME_LOGIN FROM CSM_USER WHERE LOGIN_NAME = ? ";

		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to check if the password is expired ");
		}

		try
		{
			resultSet = statement.executeQuery();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to check if the password is expired ");
		}
		if (resultSet != null)
		{
			try
			{
				while(resultSet.next())
				{
					 firstTimeLogin = resultSet.getBoolean("FIRST_TIME_LOGIN");
					
				}
			}
			catch (SQLException e)
			{
				throw new CSInternalConfigurationException("Unable to execute the query to validate user credentials");
			}
		}
		try
		{
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| is Login First Time"+firstTimeLogin+" for the user");
		return firstTimeLogin;				
	}

	public static boolean resetFirstTimeLogin(Hashtable connectionProperties, String userID) throws CSInternalConfigurationException {
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}

		PreparedStatement statement = null;
		boolean resetPassword = false;
	
		String query = new String();

		query = "UPDATE CSM_USER SET FIRST_TIME_LOGIN = FALSE WHERE LOGIN_NAME = ? ";

		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to reset the first time login flag ");
		}

		try
		{
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to reset the first time login flag ");
		}
		try
		{
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| First time login flag is "+resetPassword+" for the user");
		return resetPassword;				

	}

	public static boolean passwordMatchs(Hashtable connectionProperties,
			String userID, String newPassword, int passwordNum) throws CSInternalConfigurationException {
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			return false;
		}
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean passwordMatch = false;
		
		String encryptPassword = encryptPassword(newPassword,(String)connectionProperties.get(Constants.ENCRYPTION_ENABLED));
		String query = new String();

		query = "SELECT PASSWORD FROM CSM_PASSWORD_HISTORY WHERE LOGIN_NAME = ? ORDER BY ID DESC";

		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to generate query statement to check if the passwords are matched ");
		}

		try
		{
			resultSet = statement.executeQuery();
		}
		catch (SQLException e)
		{
			throw new CSInternalConfigurationException("Unable to execute the query to check if the passwords are matched ");
		}
		if (resultSet != null)
		{
			try
			{
				for(int i = 0; i < passwordNum;i++)
				{
					 resultSet.next();
					 String prevPassword = resultSet.getString("PASSWORD");
					 if (encryptPassword != null && prevPassword.equals(encryptPassword))
						 passwordMatch = true;					 
					
				}
			}
			catch (SQLException e)
			{
				throw new CSInternalConfigurationException("Unable to execute the query to check if the passwords are matched");
			}
		}
		try
		{
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		catch (SQLException sqe)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication||"+userID+"|executeQuery|Failure| Error in closing connections |"+ sqe.getMessage());
		}
		if (log.isDebugEnabled())
			log.debug("Authentication||"+userID+"|executeQuery|Success| is Login First Time"+passwordMatch+" for the user");
		return passwordMatch;				

	}
}
