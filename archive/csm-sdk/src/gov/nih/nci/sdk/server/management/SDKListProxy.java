package gov.nih.nci.sdk.server.management;

import java.lang.reflect.Field;
import java.util.*;

import gov.nih.nci.common.util.ListProxy;
import gov.nih.nci.csm.sdk.application.client.ApplicationServiceProvider;
import gov.nih.nci.system.applicationservice.*;

import org.apache.log4j.*;


public class SDKListProxy extends ArrayList
{
    // the real size of actual list if competely materialized, the record count from hibernate query
    private int realSize_=-1;
    // the real position in the list, as user call get(9037)
    private int currentRealPosition;
    // a switch that allows shortcutting if we have all the records
    private boolean hasAllRecords_;

    // the chunk of the list we are working with
    private SDKListChunk listChunk_ = new SDKListChunk();

    // to track what has been added and removed from the list
    // the hash consists of the object and it's position in the whole list
    private HashMap addedObjects_;
    private HashMap removedObjects_;
    private HashMap alteredObjects_;


    // some stuff to tell us about the orginal query
    private Object originalCrit_;
    private String serverAddress_;
    private int originalStart_;
    private int maxRecordsPerQuery_;
    private String targetClassName;
    private static Logger log= Logger.getLogger(SDKListProxy.class.getName());

// ==================================================================================
    // make a inner class, there is no telling what we need to do    
    private class SDKListChunk extends ArrayList
    {
    }

    public SDKListProxy(List list)
    {
    	try {
			if (Class.forName("gov.nih.nci.common.util.ListProxy").isInstance(list))
			{
				ListProxy listProxy = (ListProxy)list;
				this.setOriginalStart(listProxy.getOriginalStart());
				this.setServerAddress(listProxy.getServerAddress());
				this.setOriginalCriteria(listProxy.getOriginalCriteria());
				this.setMaxRecordsPerQuery(listProxy.getMaxRecordsPerQuery());
				this.setTargetClassName(listProxy.getTargetClassName());
				try {
					Field field = listProxy.getClass().getDeclaredField("listChunk_");
					field.setAccessible(true);
					ArrayList arrayList = (ArrayList)field.get(listProxy);
			    	this.listChunk_.addAll(arrayList);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
		    	this.listChunk_.addAll(list);				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

/**
	 * 
	 */
	public SDKListProxy() {
		
		// TODO Auto-generated constructor stub
	}

// end of inner class ListChunk ==============================================================

    /**
     * @return size of the List
     */
    public int size()
    {
		int rowCount=0;
        if (realSize_==-1)
        {
           if (hasAllRecords_) 
           {
               realSize_=listChunk_.size();
           } else
           {
           		ApplicationServiceProvider asp = new ApplicationServiceProvider();
           		SDKApplicationService appService = asp.getApplicationService();

				try{				  
				    rowCount = appService.getQueryRowCount(originalCrit_, targetClassName);
				}catch(Exception ex)
				{
				    log.error("Exception: " + ex.getMessage());
					ex.printStackTrace();
					System.err.println("Exception in SDKListProxy -- size " + ex.getMessage());
				} 	
				//System.out.println("size = " + rowCount);
				realSize_ = rowCount;
				if(rowCount < maxRecordsPerQuery_)
				    hasAllRecords_ = true;
				else
				    hasAllRecords_ = false;
			}
        }
        return realSize_;
    }

    /** 
     * @see java.util.ArrayList#isEmpty()
     */
    public boolean isEmpty()
    {
        return listChunk_.isEmpty();
    }


    /** 
     * @see java.util.ArrayList#contains(java.lang.Object)
     */
    public boolean contains(Object o)
    {
        if (hasAllRecords_)
        {
            return listChunk_.contains(o);
        } else
        {
            // step through the entire set of list chunks from
            // the appservice looking for the result.
            boolean computedResult=false;
            computedResult = listChunk_.contains(o);            
            if (computedResult)
                return computedResult;
            else
            {
                int firstResult = 0;
           		ApplicationServiceProvider asp = new ApplicationServiceProvider();
           		SDKApplicationService appService = asp.getApplicationService();
                for(;;)
                {
                    List ls = new ArrayList();
    			    try
    			    {
    			        ls = appService.query(originalCrit_, firstResult, maxRecordsPerQuery_, targetClassName);
    			        if(ls.size() <= 0) // there is no more records in database
    			            break; 
    			        computedResult = ls.contains(o) ;
    			        if(computedResult)
    			            break;
    			        else
    			            firstResult += maxRecordsPerQuery_;
    			    }catch(Exception ex)
    			    {
    			        log.error("Exception: " + ex.getMessage());
    			        System.err.println("Exception in SDKListProxy boolean contains(Object o): " + ex.getMessage());
    			    }	
                }
            }             
            return computedResult;
        }
    }

    
    /**
     * @see java.util.ArrayList#toArray()
     * 
     */
    public Object[] toArray()
    {
/*        if (hasAllRecords_)
        {
            return listChunk_.toArray();
        } else
        {
            ArrayList wholeList = new ArrayList();
            try{
             throw new Exception("Object[] toArray(): This feature is not yet implemented in this version.");             
            }
            catch(Exception ex)
            {
                log.error("Exception: " + ex.getMessage());
                System.err.println(ex.getMessage());
            }
            return wholeList.toArray();            
        }
*/
        return listChunk_.toArray();    	
    }

    /** 
     * @see java.util.ArrayList#toArray(java.lang.Object[])
     */
    public Object[] toArray(Object a[])
    {
        if (hasAllRecords_)
        {
             return listChunk_.toArray();
        } else
        {            
            ArrayList wholeList = new ArrayList();
            try{
             throw new Exception("Object[] toArray(Object a[]): This feature is not yet implemented in this version.");
             
            }
            catch(Exception ex)
            {
                log.error("Exception: " + ex.getMessage());
                System.err.println(ex.getMessage());
            }
            return wholeList.toArray();           
         }
    }


    /**
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    public boolean add(Object o)
    {
        if (hasAllRecords_)
        {
          return listChunk_.add(o);
        } else
        {
            try{
                throw new Exception("boolean add(Object o): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }
            return false;
        }
    }


    /**
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(Object obj)
    {
        if (hasAllRecords_)
        {
           return listChunk_.remove(obj);
        } else
        {
            try{
                throw new Exception("boolean remove(Object obj): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }            
           return false;
        }

    }


    /**
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection c)
    {
        if (hasAllRecords_)
        {
            return listChunk_.containsAll(c);
        }
        else
        {
            // find if the entire collection is there via appservice and then
            boolean computedResult=false;
            int recordsCount=0;
            computedResult = listChunk_.containsAll(c);        
            
                
            if (computedResult)
                return computedResult;
            else
            {
                int collectionSize = c.size();
                if( collectionSize > maxRecordsPerQuery_)            
                   recordsCount = collectionSize;            
                else
                    recordsCount = maxRecordsPerQuery_;
                int firstResult = 0;

                ApplicationServiceProvider asp = new ApplicationServiceProvider();
           		SDKApplicationService appService = asp.getApplicationService();
                
           		for(;;)
                {
                    List ls = new ArrayList();
    			    try
    			    {
    			        ls = appService.query(originalCrit_, firstResult, recordsCount, targetClassName);
    			        if(ls.size() <= 0) // there is no more records in database
    			            break; 
    			        computedResult = ls.contains(c) ;
    			        if(computedResult)
    			            break;
    			        else
    			            firstResult += recordsCount;
    			    }catch(Exception ex)
    			    {
    			        log.error("Exception: " + ex.getMessage());
    			        System.err.println("Exception in SDKListProxy boolean contains(Object o): " + ex.getMessage());
    			    }	
                }
            }             
            return computedResult;            
        }
    }


    /**
     * @see java.util.ArrayList#addAll(java.util.Collection)
     */
    public boolean addAll(Collection c)
    {
       	if (hasAllRecords_)
       	{
       	    return listChunk_.addAll(c);
       	} else
       	{
           if(listChunk_.size()== 0)
       	    	return listChunk_.addAll(c);
           else
           {
               try{
                   throw new Exception("boolean addAll(Collection c): This feature is not yet implemented in this version.");             
              }
              catch(Exception ex)
              {
                  log.error("Exception: " + ex.getMessage());
                  System.err.println(ex.getMessage());
              }
           }       	    
           return false;          
       	}
    }


    /**
     * @see java.util.ArrayList#addAll(int, java.util.Collection)
     */
    public boolean addAll(int index, Collection c)
    {
        if (hasAllRecords_)
        {
            return listChunk_.addAll(index, c);
        }
        else
        {
           try{
                throw new Exception("boolean addAll(int index, Collection c): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }         
            
        }
        return false;
    }

    /**
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection c)
    {
        if (hasAllRecords_)
        {
            return listChunk_.removeAll(c);
        }
        else
        {
           try{
                throw new Exception("boolean removeAll(Collection c): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }
        }
        return false;
    }

    /**
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection c)
    {
        if (hasAllRecords_)
        {
            return listChunk_.retainAll(c);
        }
        else
        {
           try{
                throw new Exception("boolean retainAll(Collection c): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }
           return false;
        }
    }

    
    /** 
     * @see java.util.ArrayList#clear()
     */
    
    public void clear()
    {
        listChunk_.clear();
        hasAllRecords_ = false;
    }


	/**
	 * @param index
	 * @return Object at this index
	 * 
	 */
    public Object get(int index)
    {
        if(realSize_ == -1)
        {                
            size();
        }
        if (hasAllRecords_)
        {
            return listChunk_.get(index);
        }
        else
        {
            // go through entire list from appservice taking into account
            // removed objects and added objects and get that object
           
           
			int currentSize = listChunk_.size();
			//System.out.println("listChunk_ size is " + currentSize);
			int firstRow = originalStart_;
			int maxRow = maxRecordsPerQuery_;

			ApplicationServiceProvider asp = new ApplicationServiceProvider();
       		SDKApplicationService appService = asp.getApplicationService();
			
			if((index >= (firstRow + maxRecordsPerQuery_))  && (index < realSize_))
			{
				originalStart_ = index;	
				try
				{
				    List ls = new SDKListProxy();				    
					ls = appService.query(originalCrit_, originalStart_, maxRecordsPerQuery_, targetClassName);
					listChunk_.clear();
					
					listChunk_.addAll(ls);
					currentRealPosition = index;					
					return listChunk_.get(index - originalStart_);					

				}catch(Exception ex)
				{
				    log.error("Exception: " + ex.getMessage());
					System.err.println("Exception in SDKListProxy --- get() " + ex.getMessage());
				}
			}
			else if(index < firstRow) 
			{// first row is at 2003, index is 4
			   originalStart_ = index;
			   try
				{
				    List ls1 = new SDKListProxy();				    
					ls1 = appService.query(originalCrit_, originalStart_, maxRecordsPerQuery_, targetClassName);
					listChunk_.clear();
					listChunk_.addAll(ls1);
					currentRealPosition = index;
					return listChunk_.get(index - originalStart_);					

				}catch(Exception ex)
				{
					System.err.println("Exception in SDKListProxy --- get() " + ex.getMessage());
				}			 
			}
			else // within the currentwindow
			{				
				currentRealPosition = index;
            	return listChunk_.get(index - originalStart_);
			}
            return new Object();
        }
    }

    /**
     * @see java.util.ArrayList#set(int, java.lang.Object)
     */
    public Object set(int index, Object element)
    {
        RangeCheck(index);
        Object oldValue=null;
        if (hasAllRecords_)
        {
            return listChunk_.set(index, element);
        }
        else
        {
           try{
                throw new Exception("Object set(int index, Object element): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }            
        }
        return new Object();
    }



    /** 
     * @see java.util.ArrayList#add(int, java.lang.Object)
     */
    public void add(int index, Object element)
    {
        if (hasAllRecords_)
        {
          listChunk_.add(index, element);
        } else
        {
           try{
                throw new Exception("void add(int index, Object element): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }
            // note it added in the added objects hashmap
        }

    }


    public Object remove(int index)
    {
        if (hasAllRecords_)
        {
           return listChunk_.remove(index);
        } else
        {
           try{
                throw new Exception("Object remove(int index): This feature is not yet implemented in this version.");             
           }
           catch(Exception ex)
           {
               log.error("Exception: " + ex.getMessage());
               System.err.println(ex.getMessage());
           }
            // find if it exists anywhere via appservice and then
            // track it in removed objects then return it            
        }
        return new Object();
    }


    /**
     * @see java.util.ArrayList#indexOf(java.lang.Object)
     */
    public int indexOf(Object o)
    {
        if (hasAllRecords_)
        {
           return listChunk_.indexOf(o);
        } else
        {
            if( o == null)
            {
                for (int i=0; i< realSize_; i++)
                {
                    Object obj = get(i);
                	if(obj == null)
                	    return i;
                }
            }else
            {
                for (int i=0; i< realSize_; i++)
                {
                    Object obj = get(i);
                	if(o.equals(obj))
                	    return i;
                }
                
            }           
            return -1;
        }
    }

    /**
     * @see java.util.ArrayList#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object o)
    {
        if (hasAllRecords_)
        {
           return listChunk_.lastIndexOf(o);
        } else
        {
            if( o == null)
            {
                for (int i=realSize_ - 1; i>=0; i--)
                {
                    Object obj = get(i);
                	if(obj == null)
                	    return i;
                }
            }else
            {
                for (int i=realSize_ - 1; i>=0; i--)
                {
                    Object obj = get(i);
                	if(o.equals(obj))
                	    return i;
                }                
            }  
            return -1;
        }
    }


    /**
     * @see java.util.List#subList(int, int)
     */
    public List subList(int fromIndex, int toIndex)
    {
        if (hasAllRecords_)
        {
           return listChunk_.subList(fromIndex, toIndex);
        } else
        {
            if(fromIndex < 0)
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            if(toIndex > realSize_)
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            if(fromIndex > toIndex)
                throw new IllegalArgumentException("fromIndex(" + fromIndex + 
                        						   ") > toIndex(" + toIndex + ")");
                
       		ApplicationServiceProvider asp = new ApplicationServiceProvider();
       		SDKApplicationService appService = asp.getApplicationService();
           
            List ls = new ArrayList();
		    try
		    {
		        ls = appService.query(originalCrit_, fromIndex, toIndex - fromIndex, targetClassName);
		        
		    }catch(Exception ex)
		    {
		        log.error("Exception: " + ex.getMessage());
		        System.err.println("Exception in SDKListProxy subList(int fromIndex, int toIndex): " + ex.getMessage());
		    }	            
            return ls;
        }
    }
    
    /**
     * @return Returns the hasAllRecords.
     */
    public boolean isHasAllRecords()
    {
        return hasAllRecords_;
    }
    /**
     * @param hasAllRecords The hasAllRecords to set.
     */
    public void setHasAllRecords(boolean hasAllRecords)
    {
        hasAllRecords_ = hasAllRecords;
    }
    /**
     * @return Returns the maxRecordsPerQuery.
     */
    public int getMaxRecordsPerQuery()
    {
        return maxRecordsPerQuery_;
    }
    /**
     * @param maxRecordsPerQuery The maxRecordsPerQuery to set.
     */
    public void setMaxRecordsPerQuery(int maxRecordsPerQuery)
    {
        maxRecordsPerQuery_ = maxRecordsPerQuery;
    }
    /**
     * @return Returns the orginalCriteria.
     */
    public Object getOriginalCriteria()
    {
        return originalCrit_;
    }
    /**
     * @param originalCriteria The orginalCriteria to set.
     */
    public void setOriginalCriteria(Object originalCriteria)
    {
        originalCrit_ = originalCriteria;
    }
    /**
     * @return Returns the orginalStart.
     */
    public int getOriginalStart()
    {
        return originalStart_;
    }
    /**
     * @param orginalStart The orginalStart to set.
     */
    public void setOriginalStart(int orginalStart)
    {
        originalStart_ = orginalStart;
    }
    /**
     * @return Returns the realSize.
     */
    public int getRealSize()
    {
        return realSize_;
    }
    /**
     * @param realSize The realSize to set.
     */
    public void setRealSize(int realSize)
    {
        realSize_ = realSize;
    }
    /**
     * @return Returns the serverAddress.
     */
    public String getServerAddress()
    {
        return serverAddress_;
    }
    /**
     * @param serverAddress The serverAddress to set.
     */
    public void setServerAddress(String serverAddress)
    {
        serverAddress_ = serverAddress;
    }



    private void RangeCheck(int index)
    {
		if(index >= realSize_)
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + realSize_);
	}
    
    /**
     * @param className
     */
    public void setTargetClassName(String className){
    	targetClassName = className;
    	}
    
    /**
     * @return String targetClassName
     */
    public String getTargetClassName(){
    	return targetClassName;
    	}

}
