/*
 * Created on Dec 5, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.viewobjects;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchResultObject 
{
	private String primaryId;
	private String fieldValue;
	
	/**
	 * @param primaryId
	 * @param fieldNameValue
	 */
	public SearchResultObject(String primaryId, String fieldValue) {
		super();
		this.primaryId = primaryId;
		this.fieldValue = fieldValue;
	}
	/**
	 * @return Returns the fieldNameValue.
	 */
	public String getFieldValue() {
		return fieldValue;
	}
	/**
	 * @return Returns the primaryId.
	 */
	public String getPrimaryId() {
		return primaryId;
	}
}
