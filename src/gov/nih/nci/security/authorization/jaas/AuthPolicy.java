package gov.nih.nci.security.authorization.jaas;

import java.security.*;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import javax.security.auth.Subject;
import java.util.Enumeration;
import java.util.Set;






/**
 * GenericAuthPolicy.java  in java JRE /lib/security/java.security, set: policy.
 * provider=com.jdj.jaas.AuthPolicy
 * @author ghuang   $Header: /share/content/cvsroot/security/src/gov/nih/nci/security/authorization/jaas/AuthPolicy.java,v 1.1 2004-12-03 19:05:50 hustedb Exp $ $Log: not supported by cvs2svn $
 * @version 1.0
 * @created 03-Dec-2004 1:17:48 AM
 */
public class AuthPolicy extends sun.security.provider.PolicyFile {

	protected PermissionAdapter _handler;



	public void finalize() throws Throwable {
		super.finalize();
	}

	public AuthPolicy(){

	}

	/**
	 * @param subject
	 * @param cs
	 * 
	 */
	public PermissionCollection getPermissions(Subject subject, CodeSource cs){
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see java.security.Policy#getPermissions(java.security.CodeSource)
	 * @param codesource
	 * 
	 */
	public PermissionCollection getPermissions(CodeSource codesource){
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
	 * @see java.security.Policy#refresh()
	 */
	public void refresh(){

	}

	/**
	 * @param perms
	 * @param newPerms
	 * 
	 */
	private PermissionCollection addToCollection(PermissionCollection perms, PermissionCollection newPerms){
		return null;
	}

}