package gov.nih.nci.security.dao;

import java.util.*;



/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:51 AM
 */
public abstract class SearchCriteria {
	
	public abstract Class getObjectType();
	public abstract Hashtable getFieldAndValues();
	public abstract String getMessage();
}