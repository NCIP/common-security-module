/*
 * Created on Jan 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BaseAssociationForm extends BaseDBForm
{
	public String[] getAssociatedIds();
	
	public void buildAssociationObject(HttpServletRequest request)throws Exception;

	public void setAssociationObject(HttpServletRequest request)throws Exception;

}
