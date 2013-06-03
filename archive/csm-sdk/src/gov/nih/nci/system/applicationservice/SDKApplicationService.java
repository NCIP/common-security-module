/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jul 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.applicationservice;

import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.sdk.common.ApplicationException;

import java.util.List;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SDKApplicationService {

	public Object createObject(Object obj) throws ApplicationException;
	
	public Object updateObject(Object obj) throws ApplicationException;
	
	public void   removeObject(Object obj) throws ApplicationException;
	
	public List getObjects(Object obj) throws ApplicationException;
	
	/**
	 * @param criteria
	 * @return total count for the query
	 * @throws ApplicationException
	 * 
	 */
	public abstract int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException;
	
	/**
	 * Gets the result list for the specified Hibernate Criteria from the HTTPClient.
	 * @param criteria  Specified Hibernate criteria
	 * @return  gets the result list
	 * @throws ApplicationException
	 */
	public abstract List query(Object criteria, String targetClassName)	throws ApplicationException;

	/**
	 * @param criteria
	 * @param firstRow
	 * @param resultsPerQuery
	 * @param targetClassName
	 * @return List
	 * @throws ApplicationException
	 */
	public abstract List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;

	/**
	 * Returns true if the specified object is of type <code>java.lang.Array</code> or <code>java.util.Collection </code> type
	 * @param criterion  specified objected
	 * @return  true or false
	 */
	public abstract List evsSearch(EVSQuery evsCriterion) throws ApplicationException;

	
	/****************************************************   HQL Search *************************************************/
	
	public abstract List search(Class targetClass, Object obj) throws ApplicationException;
	
	public abstract List search(Class targetClass, List objList) throws ApplicationException;

	public abstract List search(String path, Object obj) throws ApplicationException;
	
	public abstract List search(String path, List objList) throws ApplicationException;
	
	public String getTimeStamp() throws ApplicationException;
}