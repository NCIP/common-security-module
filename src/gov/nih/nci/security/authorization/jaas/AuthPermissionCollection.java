package gov.nih.nci.security.authorization.jaas;

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Iterator;


import java.security.PermissionCollection;





/**
 * AuthPermissionCollection.java
 * @author ghuang  Create:5-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AuthPermissionCollection.java,v 1.1 2004-12-03 19:05:50 hustedb Exp $ $Log: not supported by cvs2svn $
 * @version 1.0
 * @created 03-Dec-2004 1:17:48 AM
 */
public class AuthPermissionCollection extends PermissionCollection {

	private Vector _perms = new Vector ();

	public AuthPermissionCollection(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * (non-Javadoc)
	 * @see java.security.PermissionCollection#add(java.security.Permission)
	 * @param permission
	 * 
	 */
	public void add(Permission permission){

	}

	/**
	 * (non-Javadoc)
	 * @see java.security.PermissionCollection#implies(java.security.Permission)
	 * @param permission
	 * 
	 */
	public boolean implies(Permission permission){
		return false;
	}

	/**
	 * (non-Javadoc)
	 * @see java.security.PermissionCollection#elements()
	 */
	public Enumeration elements(){
		return null;
	}

}