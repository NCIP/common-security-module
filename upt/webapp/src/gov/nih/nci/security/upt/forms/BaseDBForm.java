/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.upt.viewobjects.SearchResult;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BaseDBForm
{
	public String getFormName();
	
	public ArrayList getAddFormElements();

	public ArrayList getDisplayFormElements();
	
	public ArrayList getSearchFormElements();

	public void buildDisplayForm(HttpServletRequest request) throws Exception;
	
	public void buildDBObject(HttpServletRequest request) throws Exception;
	
	public void removeDBObject(HttpServletRequest request) throws Exception;
	
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception;

	public String getPrimaryId();
	
	public void resetForm();

}
