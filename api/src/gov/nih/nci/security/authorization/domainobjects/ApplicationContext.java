package gov.nih.nci.security.authorization.domainobjects;





/**
 * Application Context is the represntative of the application. It holds method to
 * retrieve all the object like protection groups, protection elements, roles etc
 * that belong to that application.
 * @version 1.0
 * created 03-Dec-2004 1:17:47 AM
 */
public abstract class ApplicationContext {

	/**
	 * It is the unique id for the application in the database.
	 */
	private Long applicationId;
	/**
	 * The name of the application. The saem name is used for applicationContextName.
	 */
	private String applicationName;
	/**
	 * This attributes indicates if the application is active or not.
	 */
	private byte activeFlag;
	/**
	 * This is the name of the data source for authorization data. This is the JNDI
	 * name for the data source for this application.
	 */
	private String dataSourceName;
	/**
	 * This name represent the the name that should be used to get all the
	 * configuration for authentication for this application.
	 */
	private String authenticationPolicyConfigName;
	/**
	 * Indicate whether the application uses declarative security or not
	 */
	private byte declarativeFlag;

	public ApplicationContext(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * This attributes indicates if the application is active or not.
	 */
	public byte getActiveFlag(){
		return activeFlag;
	}

	/**
	 * It is the unique id for the application in the database.
	 */
	public Long getApplicationId(){
		return applicationId;
	}

	/**
	 * The name of the application. The saem name is used for applicationContextName.
	 */
	public String getApplicationName(){
		return applicationName;
	}

	/**
	 * This name represent the the name that should be used to get all the
	 * configuration for authentication for this application.
	 */
	public String getAuthenticationPolicyConfigName(){
		return authenticationPolicyConfigName;
	}

	/**
	 * This is the name of the data source for authorization data. This is the JNDI
	 * name for the data source for this application.
	 */
	public String getDataSourceName(){
		return dataSourceName;
	}

	/**
	 * Indicate whether the application uses declarative security or not
	 */
	public byte getDeclarativeFlag(){
		return declarativeFlag;
	}

	/**
	 * This attributes indicates if the application is active or not.
	 * @param newVal
	 * 
	 */
	public void setActiveFlag(byte newVal){
		activeFlag = newVal;
	}

	/**
	 * It is the unique id for the application in the database.
	 * @param newVal
	 * 
	 */
	public void setApplicationId(Long newVal){
		applicationId = newVal;
	}

	/**
	 * The name of the application. The saem name is used for applicationContextName.
	 * @param newVal
	 * 
	 */
	public void setApplicationName(String newVal){
		applicationName = newVal;
	}

	/**
	 * This name represent the the name that should be used to get all the
	 * configuration for authentication for this application.
	 * @param newVal
	 * 
	 */
	public void setAuthenticationPolicyConfigName(String newVal){
		authenticationPolicyConfigName = newVal;
	}

	/**
	 * This is the name of the data source for authorization data. This is the JNDI
	 * name for the data source for this application.
	 * @param newVal
	 * 
	 */
	public void setDataSourceName(String newVal){
		dataSourceName = newVal;
	}

	/**
	 * Indicate whether the application uses declarative security or not
	 * @param newVal
	 * 
	 */
	public void setDeclarativeFlag(byte newVal){
		declarativeFlag = newVal;
	}

}