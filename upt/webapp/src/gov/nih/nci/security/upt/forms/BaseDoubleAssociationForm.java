/*
 * Created on Jan 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BaseDoubleAssociationForm extends BaseAssociationForm
{
	
	public String[] getProtectionGroupAssociatedIds();
	
	public String[] getRoleAssociatedIds();
	
	public String getProtectionGroupAssociatedId();
	
	public void buildDoubleAssociationObject(HttpServletRequest request)throws Exception;

	public void setDoubleAssociationObject(HttpServletRequest request)throws Exception;
	
	public Collection buildProtectionGroupAssociationObject(HttpServletRequest request)throws Exception;
	
	public void buildRoleAssociationObject(HttpServletRequest request)throws Exception;
	
	public void removeProtectionGroupAssociation(HttpServletRequest request)throws Exception;
	
	public void updateRoleAssociation(HttpServletRequest request)throws Exception;

}
