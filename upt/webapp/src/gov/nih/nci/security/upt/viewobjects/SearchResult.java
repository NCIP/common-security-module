/*
 * Created on Dec 5, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.viewobjects;
 
import java.util.List;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchResult 
{
	private String fieldName;
	private String primaryIdName;
	private List searchResultObjects;
	
	
	/**
	 * @return Returns the primaryIdName.
	 */
	public String getPrimaryIdName() {
		return primaryIdName;
	}
	/**
	 * @param primaryIdName The primaryIdName to set.
	 */
	public void setPrimaryIdName(String primaryIdName) {
		this.primaryIdName = primaryIdName;
	}
	/**
	 * @return Returns the fieldName.
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName The fieldName to set.
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return Returns the searchResultObjects.
	 */
	public List getSearchResultObjects() {
		return searchResultObjects;
	}
	/**
	 * @param searchResultObjects The searchResultObjects to set.
	 */
	public void setSearchResultObjects(List searchResultObjects) {
		this.searchResultObjects = searchResultObjects;
	}
}
