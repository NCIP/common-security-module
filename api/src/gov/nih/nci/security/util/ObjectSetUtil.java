/*
 * Created on Dec 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.util;

import java.util.*;

/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class ObjectSetUtil {
	
	public static Collection intersect(Collection firstSet,Collection secondSet){
		ArrayList result = new ArrayList();
		Iterator it = firstSet.iterator();
		while(it.hasNext()){
			Object oj = it.next();
			if(secondSet.contains(oj)){
				result.add(oj);
			}
		}
		return result;
	}
	public static Collection minus(Collection toBeDeletedFrom,Collection tobeDeleted){
		ArrayList result = new ArrayList(toBeDeletedFrom);
		
		Iterator it = tobeDeleted.iterator();
		while(it.hasNext()){
			Object oj = it.next();
			if(toBeDeletedFrom.contains(oj)){
				result.remove(oj);
			}
		}
		
		return result;
	}
    public static Collection union(Collection firstSet,Collection secondSet){
    	return null;
    }
}
