package gov.nih.nci.security.authorization.jaas;

import java.security.Permission;
import java.util.*;
import java.io.Serializable;
import java.io.File;







/**
 * AccessPermission.java
 * @author ghuang  Create:5-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AccessPermission.java,v 1.2 2004-12-03 20:09:00 hustedb Exp $ $Log: not supported by cvs2svn $
 * @author ghuang  Create:5-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AccessPermission.java,v 1.2 2004-12-03 20:09:00 hustedb Exp $ Revision 1.1  2004/12/03 19:05:50  hustedb
 * @author ghuang  Create:5-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AccessPermission.java,v 1.2 2004-12-03 20:09:00 hustedb Exp $ initial release
 * @author ghuang  Create:5-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AccessPermission.java,v 1.2 2004-12-03 20:09:00 hustedb Exp $
 * @version 1.0
 * @created 03-Dec-2004 1:17:46 AM
 */
public class AccessPermission extends Permission implements Serializable {

	protected static final String ACTION_DELIM = ",";
	private static final String WILD = "*";
	private static final String SEP_WILD = File.separator+WILD;
	private String _action;
	private ArrayList _listActions = new ArrayList();
	private String _path;
	private boolean _bTail = false;

	public AccessPermission( String name ){
		super(name);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}



	/**
	 * (non-Javadoc)
	 * @see java.security.Permission#implies(java.security.Permission)
	 * @param permission
	 * 
	 */
	public boolean implies(Permission permission){
		return false;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj
	 * 
	 */
	public boolean equals(Object obj){
		return false;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * @see java.security.Permission#getActions()
	 */
	public String getActions(){
		return "";
	}

	/**
	 * @param name
	 * 
	 */
	protected void getPath(String name){

	}

	/**
	 * @param action
	 * 
	 */
	protected void parseAction(String action){

	}

	/**
	 * @param listActions
	 * 
	 */
	protected boolean compareAction(ArrayList listActions){
		return false;
	}

	/**
	 * @param listActions
	 * 
	 */
	protected boolean containAction(ArrayList listActions){
		return false;
	}

	/**
	 * @param path
	 * 
	 */
	protected boolean containWith(String path){
		return false;
	}

}