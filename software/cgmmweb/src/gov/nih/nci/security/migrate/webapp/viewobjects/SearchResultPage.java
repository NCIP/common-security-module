package gov.nih.nci.security.migrate.webapp.viewobjects;



 
import java.util.List;

/**
 * @author (Ekagra Software Technologies Ltd.)
 */
public class SearchResultPage implements Page
{
	private String searchResultMessage;
	
	private int totalResultSize;
	private int pageSize; 
	private int currentPageNumber;
	
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
	
	public boolean isFirstPage()
	{
		return getPageNumber() == 1;
		
	}
	public boolean isLastPage()
	{
		return getPageNumber() >= getLastPageNumber();	
	}
	
	public String getIsFirstPage()
	{
		if(getPageNumber() == 1){
			return "TRUE";
			
		}else{
			return "FALSE";
		}
	}
	public String getIsLastPage()
	{
		if(getPageNumber() >= getLastPageNumber()){
			return "TRUE";
		}else{
			return "FALSE";
		}
		
	}
	public boolean hasNextPage()
	{
		return ((getPageNumber() + 1) * getPageSize()) <
        (getTotalNumberOfElements() + 1);
	}
	public boolean hasPreviousPage()
	{
		 return getPageNumber() > 1;
	}
	public int getLastPageNumber()
	{
		/*
		* the Math.floor() method because page numbers are zero-based
		* (i.e. the first page is page 0).
		*/
		double totalResults = new Integer(getTotalNumberOfElements()).doubleValue();
	    return new Double(Math.ceil(totalResults / getPageSize())).intValue();
	}
	public List getThisPageElements()
	{
		return searchResultObjects; 
	}
	public int getTotalNumberOfElements()
	{
		 return totalResultSize ;
	}
	public int getThisPageFirstElementNumber()
	{
		 return (getPageNumber() * getPageSize()-1) + 1;
	}
	public int getThisPageLastElementNumber()
	{
		 int fullPage = getThisPageFirstElementNumber() + (getPageSize()+1) - 1;
		    return getTotalNumberOfElements() < fullPage ?
		            getTotalNumberOfElements() :
		            fullPage;
	}
	public int getNextPageNumber()
	{
		return getPageNumber() + 1;
	}
	public int getPreviousPageNumber()
	{
		 return getPageNumber() - 1;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public int getPageNumber()
	{
		return currentPageNumber;
	}
	
	/**
	 * @return Returns the currentPageNumber.
	 */
	public int getCurrentPageNumber()
	{
		return currentPageNumber;
	}
	
	/**
	 * @param currentPageNumber The currentPageNumber to set.
	 */
	public void setCurrentPageNumber(int currentPageNumber)
	{
		this.currentPageNumber = currentPageNumber;
	}
	
	/**
	 * @return Returns the totalResultSize.
	 */
	public int getTotalResultSize()
	{
		return totalResultSize;
	}
	
	/**
	 * @param totalResultSize The totalResultSize to set.
	 */
	public void setTotalResultSize(int totalResultSize)
	{
		this.totalResultSize = totalResultSize;
	}
	
	/**
	 * @param pageSize The pageSize to set.
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
}
