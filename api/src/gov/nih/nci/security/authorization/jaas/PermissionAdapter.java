package gov.nih.nci.security.authorization.jaas;

import java.util.Hashtable;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.CodeSource;
import java.security.ProtectionDomain;






/**
 * PermissionAdapter.java
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapter.java,v 1.1 2004-12-06 17:46:00 hustedb Exp $ $Log: not supported by cvs2svn $
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapter.java,v 1.1 2004-12-06 17:46:00 hustedb Exp $ Revision 1.1  2004/12/03 19:05:51  hustedb
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapter.java,v 1.1 2004-12-06 17:46:00 hustedb Exp $ initial release
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapter.java,v 1.1 2004-12-06 17:46:00 hustedb Exp $
 * @version 1.0
 * @created 03-Dec-2004 1:17:49 AM
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

