package gov.nih.nci.logging.struts;

import gov.nih.nci.logging.Password;
import gov.nih.nci.logging.dao.*;

/**
 * 
 * Class verifies a user name and password.
 * SHA-1 Encoding is used to verify the password.
 * 
 * @author Brian Husted
 */
public class LoginValidator {

	/**
	 * Returns true if the user name and password
	 * are validated. SHA-1 encoding is used to verify
	 * the password.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean validate(String username, String password) throws Exception {
		// variables defined
		boolean grantAccess = false;
		String dbPassword;
		LoginDaoJdbc connector = new LoginDaoJdbc();

		// One-way hash of the password
		password = Password.shaEncode(password);
		dbPassword = connector.retrieveLoginInformation(username);
				
		//One-way hash of the password from the database
		if (password != null && dbPassword != null
				&& password.equals(dbPassword)) {
			grantAccess = true;			
		}
		
		return grantAccess;
		
	}

}

