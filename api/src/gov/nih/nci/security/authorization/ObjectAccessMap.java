/*
 * Created on Jan 13, 2005
 *
 */
package gov.nih.nci.security.authorization;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class repesents a structure conating the access of
 * a user on the attributes of an object
 * 
 * @author kumarvi
 * 
 */
public class ObjectAccessMap {
	
	private Hashtable accessMap=new Hashtable();
	private String objectName;
	public ObjectAccessMap(String objectName,Hashtable accessMap){
		this.objectName=objectName;
		this.accessMap=accessMap;
	}
	
	/**
	 * The methods checks the permission of a user on the attribute
	 * of an object.
	 * @param attributeName
	 * @return boolean
	 */
	public boolean hasAccess(String attributeName){
		boolean hasAccess = false;
		Boolean value = (Boolean)accessMap.get(attributeName.toLowerCase());
		if(value!=null){
			hasAccess = true;
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
			//stbr.append("Field:"+key+" has access:"+val);
			stbr.append("Field:").append(key).append(" has access:").append(val);
			stbr.append("\n");
		}
		
		return stbr.toString();
	}

}
