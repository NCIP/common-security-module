package gov.nih.nci.security.authorization.jaas;







/**
 * PermissionAdapterFactory.java
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapterFactory.java,v 1.1 2004-12-06 17:46:01 hustedb Exp $ $Log: not supported by cvs2svn $
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapterFactory.java,v 1.1 2004-12-06 17:46:01 hustedb Exp $ Revision 1.1  2004/12/03 19:05:51  hustedb
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapterFactory.java,v 1.1 2004-12-06 17:46:01 hustedb Exp $ initial release
 * @author ghuang  $Header: /share/content/cvsroot/security/api/src/gov/nih/nci/security/authorization/jaas/PermissionAdapterFactory.java,v 1.1 2004-12-06 17:46:01 hustedb Exp $
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class PermissionAdapterFactory {

	protected PermissionAdapter _adapter;
	protected boolean _bOK = false;
	protected boolean _bFirst = true;
	private static PermissionAdapterFactory _instance;



	public void finalize() throws Throwable {

	}

	public PermissionAdapterFactory(){

	}

	public static PermissionAdapterFactory getInstance(){
		return null;
	}

	public static PermissionAdapter getAdapter(){
		return null;
	}

	protected PermissionAdapter getHandlerInternal(){
		return null;
	}

	public boolean prepare(){
		return false;
	}

	public PermissionAdapter instantiateAdapter(){
		return null;
	}

}