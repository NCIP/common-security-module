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
import gov.nih.nci.sdk.server.management.SDKListProxy;
import gov.nih.nci.sdk.server.management.SdkDAO;

import java.util.Date;
import java.util.List;


/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SDKApplicationServiceImpl implements SDKApplicationService{

	private ApplicationService applicationService = null;
	private SdkDAO sdkDAO = null;
	
	public SDKApplicationServiceImpl()
	{
		this.applicationService = ApplicationService.getLocalInstance();
		this.sdkDAO = new SdkDAO();
	}
	
	public Object createObject(Object obj) throws ApplicationException {
		return sdkDAO.createObject(obj);
	}
	public Object updateObject(Object obj) throws ApplicationException {
		return sdkDAO.updateObject(obj);
	}
	public void removeObject(Object obj) throws ApplicationException {
		sdkDAO.removeObject(obj);
	}
	public List getObjects(Object obj) throws ApplicationException {
		return sdkDAO.getObjects(obj);
	}	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#getQueryRowCount(java.lang.Object, java.lang.String)
	 */
	public int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException {
		int returnValue = -1;
		try {
			returnValue = this.applicationService.getQueryRowCount(criteria, targetClassName);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#query(java.lang.Object, java.lang.String)
	 */
	public List query(Object criteria, String targetClassName) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.query(criteria, targetClassName));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#query(java.lang.Object, int, int, java.lang.String)
	 */
	public List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.query(criteria, firstRow, resultsPerQuery, targetClassName));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#evsSearch(gov.nih.nci.evs.query.EVSQuery)
	 */
	public List evsSearch(EVSQuery evsCriterion) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.evsSearch(evsCriterion));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#search(java.lang.Class, java.lang.Object)
	 */
	public List search(Class targetClass, Object obj) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.search(targetClass, obj));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#search(java.lang.Class, java.util.List)
	 */
	public List search(Class targetClass, List objList) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.search(targetClass, objList));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#search(java.lang.String, java.lang.Object)
	 */
	public List search(String path, Object obj) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.search(path, obj));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.SDKApplicationService#search(java.lang.String, java.util.List)
	 */
	public List search(String path, List objList) throws ApplicationException {
		List list = null;
		try {
			list = new SDKListProxy(this.applicationService.search(path, objList));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}
	
	public String getTimeStamp()
	{
		return new Date().toString();
	}

}
