package gov.nih.nci.security.authorization.jaas;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.sql.*;






/**
 * AuthznDBHanlder.java
 * @author ghuang  Create:4-Mar-2003  $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/PermissionRoleDBAdapter.java,v 1.1 2004-12-03 19:05:52 hustedb Exp $ $Log: not supported by cvs2svn $
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class PermissionRoleDBAdapter implements PermissionAdapter {

	private static final String SQL_GETALLPERM = "SELECT name, task, action, parent FROM role";
	private Hashtable _htConnProp;
	private Connection _connection;
	private Hashtable _htPermissions;



	public void finalize() throws Throwable {

	}

	public PermissionRoleDBAdapter(){

	}

	/**
	 * (non-Javadoc)
	 * @see sample.auth.AuthznHandler#initialize(java.util.Hashtable)
	 * @param initParams
	 * 
	 */
	public void initialize(Hashtable initParams){

	}

	/**
	 * @param codeSource
	 * 
	 */
	public PermissionCollection getPermissions(CodeSource codeSource){
		return null;
	}

	/**
	 * @param domain
	 * 
	 */
	public PermissionCollection getPermissions(ProtectionDomain domain){
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see sample.auth.AuthznHandler#getPermissions(java.security.Principal[], java.
	 * security.CodeSource)
	 * @param principals
	 * @param codeSource
	 * 
	 */
	public PermissionCollection getPermissions(Principal[] principals, CodeSource codeSource){
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see sample.auth.AuthznHandler#terminate()
	 */
	public void terminate(){

	}

	private Connection getConnection(){
		return null;
	}

	/**
	 * @param name
	 * @param principals
	 * 
	 */
	private boolean isTitleTo(String name, Principal[] principals){
		return false;
	}

	protected Hashtable getAllPermissions(){
		return null;
	}

}