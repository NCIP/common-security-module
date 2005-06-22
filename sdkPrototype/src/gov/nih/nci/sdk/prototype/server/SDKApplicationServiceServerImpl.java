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
		
	}
	public Object createObject(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		return hDAO.createObject(obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.sdk.prototype.service.SDKApplicationService#updateObject(java.lang.Object)
	 */
	public Object updateObject(Object obj) throws ApplicationException {
		// TODO Auto-generated method stub
		return hDAO.createObject(obj);
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
