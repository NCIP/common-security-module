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
	private String searchResultMessage;
	private List searchResultObjects;
	
	
	/**
	 * @return Returns the searchResultMessage.
	 */
	public String getSearchResultMessage() {
		return searchResultMessage;
	}
	/**
	 * @param searchResultMessage The searchResultMessage to set.
	 */
	public void setSearchResultMessage(String searchResultMessage) {
		this.searchResultMessage = searchResultMessage;
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
