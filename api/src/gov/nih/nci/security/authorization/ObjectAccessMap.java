/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authorization;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObjectAccessMap {
	
	private Hashtable accessMap=new Hashtable();
	private String objectName;
	public ObjectAccessMap(String objectName,Hashtable accessMap){
		this.objectName=objectName;
		this.accessMap=accessMap;
	}
	public boolean hasAccess(String attributeName){
		boolean hasAccess = true;
		Boolean value = (Boolean)accessMap.get(attributeName.toLowerCase());
		if(value!=null){
			hasAccess = false;
		}
		return hasAccess;
	}
	
	public String toString(){
		StringBuffer stbr = new StringBuffer();
		stbr.append("Object Name:"+objectName);
		stbr.append("\n");
		stbr.append("****************************************");
		stbr.append("\n");
		Enumeration en = accessMap.keys();
		while(en.hasMoreElements()){
			String key = (String)en.nextElement();
			Boolean val = (Boolean)accessMap.get(key);
			stbr.append("Field:"+key+" has access:"+val);
			stbr.append("\n");
		}
		
		return stbr.toString();
	}

}
