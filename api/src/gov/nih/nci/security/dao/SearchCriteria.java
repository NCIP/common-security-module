package gov.nih.nci.security.dao;

import java.util.*;



/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:51 AM
 */
/**
 * This class is a common interface for all the search criteria. All the entities which needs to be 
 * have to implement this class. For eg. for <code>Role</code> a class {@link RoleSearchCriteria} would
 * be created which implements this interface. Such instances are used to stored the search criteria for 
 * various entities. This class is passed from the client applications to the backend to pass the search 
 * criterias for querying against the database
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public abstract class SearchCriteria {
	
	
	/**
	 * This method returns the Class which implements the {@link SearchCriteria}. This is used to determine which entity
	 * is being queried for.
	 * @return The Class which implments the {@link SearchCriteria}
	 */
	public abstract Class getObjectType();
	/**
	 * This method returns the collecition of field and corresponding value which constitute the search criteria for a 
	 * particular entity. 
	 * @return The collection of field and values which constitute the search criteria for a particular entity
	 * 
	 */
	public abstract Hashtable getFieldAndValues();
	/**
	 * @return The message which is returned from the back end back to the calling application. This message is used to
	 * indicate any special case for the search. E.g. if the search returned more than the preset number of results. This
	 * message is used to indicate the same.
	 */
	public abstract String getMessage();
}