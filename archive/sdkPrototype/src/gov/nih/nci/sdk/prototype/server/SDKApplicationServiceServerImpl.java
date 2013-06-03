/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.prototype.server;

import java.util.List;

import gov.nih.nci.sdk.common.ApplicationException;
import gov.nih.nci.sdk.prototype.service.SDKApplicationService;
import gov.nih.nci.sdk.server.management.HibernateDAO;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SDKApplicationServiceServerImpl implements SDKApplicationService{

	/* (non-Javadoc)
	 * @see gov.nih.nci.sdk.prototype.service.SDKApplicationService#createObject(java.lang.Object)
	 */
	private HibernateDAO hDAO;
	public SDKApplicationServiceServerImpl(){
		System.out.println("Message from SDKApplicationServiceServerImpl: Calling constructor !");
		hDAO = new HibernateDAO();
		if(hDAO!=null){
			System.out.println("Message from SDKApplicationServiceServerImpl: hDAO is there !");
		}else{
			System.out.println("Message from SDKApplicationServiceServerImpl: hDAO is not there !");
		}
	}
	public Object createObject(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		System.out.println("Message from SDKApplicationServiceServerImpl:Calling creatObject!");
		return hDAO.createObject(obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.sdk.prototype.service.SDKApplicationService#updateObject(java.lang.Object)
	 */
	public Object updateObject(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		return hDAO.updateObject(obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.sdk.prototype.service.SDKApplicationService#removeObject(java.lang.Object)
	 */
	public void removeObject(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		hDAO.removeObject(obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.sdk.prototype.service.SDKApplicationService#getObjects(java.lang.Object)
	 */
	public List getObjects(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		return hDAO.getObjects(obj);
	}

}
