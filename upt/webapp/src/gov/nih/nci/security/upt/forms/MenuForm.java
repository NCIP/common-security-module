/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import org.apache.struts.action.ActionForm;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuForm extends ActionForm 
{
	private String tableId;
	
	

	/**
	 * @return Returns the tableId.
	 */
	public String getTableId() {
		return tableId;
	}
	/**
	 * @param tableId The tableId to set.
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
}
