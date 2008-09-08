package gov.nih.nci.security.cgmm.webapp.form;


import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
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
