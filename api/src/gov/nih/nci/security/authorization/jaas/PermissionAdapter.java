package gov.nih.nci.security.authorization.jaas;

import java.util.Hashtable;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.CodeSource;
import java.security.ProtectionDomain;






/**
 * PermissionAdapter.java
 * created 03-Dec-2004 1:17:49 AM
 */
public interface PermissionAdapter {

	/**
	 * @param initParams
	 * 
	 */
	public void initialize(Hashtable initParams);

	/**
	 * @param principals
	 * @param codeSource
	 * 
	 */
	public PermissionCollection getPermissions(Principal[] principals, CodeSource codeSource);

	/**
	 * @param codeSource
	 * 
	 */
	public PermissionCollection getPermissions(CodeSource codeSource);

	/**
	 * @param domain
	 * 
	 */
	public PermissionCollection getPermissions(ProtectionDomain domain);

	public void terminate();

}

