package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.*;

import gov.nih.nci.security.authorization.jaas.*;
import gov.nih.nci.security.dao.hibernate.HibernateSessionFactory;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;

import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.SessionFactory;
import java.util.List;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.expression.*;



/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public class AuthorizationDAOImpl implements AuthorizationDAO {
	
	private SessionFactory sf = null;
	private Application application = null;

	public AuthorizationDAOImpl(SessionFactory sf,String applicationContextName) {
       this.sf=sf;
       try{
       this.application = this.getApplicationByName(applicationContextName);
       }catch(Exception ex){
       	ex.printStackTrace();
       }
	}

	public void finalize() throws Throwable {

	}
	public void setHibernateSessionFactory(SessionFactory sf){
		this.sf=sf;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#addUserToGroup(java.lang.String, java.lang.String)
	 */
	public void addUserToGroup(String groupId, String user)
			throws CSTransactionException {
		// TODO Auto-generated method stub
		/**
		 *  insert into usergroup table (groupid,userid);
		 */

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignGroupRoleToProtectionGroup(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignPrivilegesToRole(java.lang.String[], java.lang.String)
	 */
	
	public void assignPrivilegesToRole(String roleId,String[] privilegeIds)throws CSTransactionException{
		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectName,
			String[] protectionElementAttributeName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String, java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectNames)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignUserRoleToProtectionGroup(java.lang.String, java.lang.String[], java.lang.String)
	 */
	public void assignUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission, java.lang.String)
	 */
	public boolean checkPermission(AccessPermission permission, String userName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission, javax.security.auth.Subject)
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission, gov.nih.nci.security.authorization.domainobjects.User)
	 */
	public boolean checkPermission(AccessPermission permission, User user) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeId, String privilegeName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String privilegeName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createGroup(gov.nih.nci.security.authorization.domainobjects.Group)
	 */
	public void createGroup(Group group) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
	 */
	public void createPrivilegeXXX(Privilege privilege)
			throws CSTransactionException {
		// TODO Auto-generated method stub
		Session s = null;
		Transaction t = null;
		System.out.println("Running create test...");
		try {
			s = HibernateSessionFactory.currentSession();

			t = s.beginTransaction();
			//Privilege p = new Privilege();
			
			//p.setDesc("TestDesc");
			//p.setName("TestName");
			s.save(privilege);
			t.commit();
			s.close();
			System.out.println( "Privilege ID is: " + privilege.getId().doubleValue() );
		} catch (Exception ex) {
			try {
				s.close();
			} catch (Exception ex2) {
			}
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad",ex);
		}


	}
	
	public void createPrivilege(Privilege privilege)
				throws CSTransactionException {
			// TODO Auto-generated method stub
			Session s = null;
			Transaction t = null;
			try {
				s = sf.openSession();	
				t = s.beginTransaction();
				s.save(privilege);
				t.commit();
				s.close();
				System.out.println( "Privilege ID is: " + privilege.getId().doubleValue() );
			} catch (Exception ex) {
				try {
					s.close();
				} catch (Exception ex2) {
				}
				try {
					t.rollback();
				} catch (Exception ex3) {
				}
				throw new CSTransactionException("Bad",ex);
			}
			
			
			}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
	 */
	public void createProtectionElement(ProtectionElement protectionElement)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createRole(gov.nih.nci.security.authorization.domainobjects.Role)
	 */
	public void createRole(Role role) throws CSTransactionException {
		// TODO Auto-generated method stub
		
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();	
			t = s.beginTransaction();
			role.setApplication(application);
			s.save(role);
			t.commit();
			s.close();
			System.out.println( "Role ID is: " + role.getId().doubleValue() );
		} catch (Exception ex) {
			try {
				s.close();
			} catch (Exception ex2) {
			}
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad",ex);
		}

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectNames,
			String[] protectionElementAttributeNames)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String[], java.lang.String)
	 */
	public void deAssignProtectionElements(
			String[] protectionElementObjectNames, String protectionGroupName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getGroup(java.lang.Long)
	 */
	public Group getGroup(Long groupId) throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getGroup(java.lang.String)
	 */
	public Group getGroup(String groupName) throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getObjects(gov.nih.nci.security.dao.SearchCriteria)
	 */
	public Set getObjects(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrincipals(java.lang.String)
	 */
	public Principal[] getPrincipals(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrivilege(java.lang.String)
	 */
	public Privilege getPrivilege(String privilegeId)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		/**
		Session s = null;
		Privilege p = null;
		try {
			//String query = "SELECT privilege FROM privilege IN CLASS gov.nih.nci.security.authorization.domianobjects.Privilege where privilege.privilege_id=:id";
			s = sf.openSession();	
			//List list = s.find(query,new Long(privilegeId),Hibernate.LONG);
			p = (Privilege)s.load(Privilege.class,new Long(privilegeId));
			System.out.println("Somwthing");
			if(p==null){
				throw new CSObjectNotFoundException("Not found");
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				s.close();
			} catch (Exception ex2) {
			}
			
			
		}
		return p;
		*/
		Application app = this.getApplicationByName("Security");
		System.out.println(app.getApplicationDescription());
		
		return (Privilege)this.getObjectByPrimaryKey(Privilege.class,new Long(privilegeId));
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionElement(java.lang.Long)
	 */
	public ProtectionElement getProtectionElement(Long protectionElementId)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionElement(java.lang.String)
	 */
	public ProtectionElement getProtectionElement(String objectId)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionGroup(java.lang.Long)
	 */
	public ProtectionGroup getProtectionGroup(Long protectionGroupId)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionGroup(java.lang.String)
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getRole(java.lang.Long)
	 */
	public Role getRole(Long roleId) throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return (Role)this.getObjectByPrimaryKey(Role.class,roleId);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getRole(java.lang.String)
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getUser(java.lang.String)
	 */
	public User getUser(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyGroup(gov.nih.nci.security.authorization.domainobjects.Group)
	 */
	public void modifyGroup(Group group) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
	 */
	public void modifyPrivilege(Privilege privilege)
			throws CSTransactionException {
		// TODO Auto-generated method stub
		Session s = null;
		Transaction t = null;
		try {
			System.out.println( "About to be Modified");
			s = sf.openSession();	
			t = s.beginTransaction();
			s.saveOrUpdate(privilege);
			System.out.println( "Modified");
			t.commit();
			s.close();
			//System.out.println( "Privilege ID is: " + privilege.getId().doubleValue() );
		} catch (Exception ex) {
			try {
				s.close();
			} catch (Exception ex2) {
			}
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad",ex);
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyRole(gov.nih.nci.security.authorization.domainobjects.Role)
	 */
	public void modifyRole(Role role) throws CSTransactionException {
		// TODO Auto-generated method stub
		Session s = null;
		Transaction t = null;
		try {
			
			s = sf.openSession();	
			t = s.beginTransaction();
			s.update(role);
			System.out.println( "Modified");
			t.commit();
			s.close();
			
		} catch (Exception ex) {
			try {
				s.close();
			} catch (Exception ex2) {
			}
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad",ex);
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroup(java.lang.String)
	 */
	public void removeGroup(String groupId) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupFromProtectionGroup(java.lang.String, java.lang.String)
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId,
			String groupId) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupRoleFromProtectionGroup(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId,
			String groupId, String[] roleId) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removePrivilege(java.lang.String)
	 */
	public void removePrivilege(String privilegeId)
			throws CSTransactionException {
		Privilege p = new Privilege();
		p.setId(new Long(privilegeId));
		this.removeObject(p);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removePrivilegesFromRole(java.lang.String, java.lang.String[])
	 */
	public void removePrivilegesFromRole(String role, String[] privilegeName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
	 */
	public void removeProtectionElement(ProtectionElement element)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeProtectionGroup(java.lang.String)
	 */
	public void removeProtectionGroup(String protectionGroupName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeRole(java.lang.String)
	 */
	public void removeRole(String roleId) throws CSTransactionException {
		// TODO Auto-generated method stub
		     Role r = new Role();
		     r.setId(new Long(roleId));
            this.removeObject(r);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromGroup(java.lang.String, java.lang.String)
	 */
	public void removeUserFromGroup(String groupId, String userId)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromProtectionGroup(java.lang.String, java.lang.String)
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId,
			String userId) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserRoleFromProtectionGroup(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName,
			String userName, String[] roles) throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setOwnerForProtectionElement(String userName,
			String protectionElementObjectName,
			String protectionElementAttributeName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String, java.lang.String)
	 */
	public void setOwnerForProtectionElement(
			String protectionElementObjectName, String userName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}
	private Object getObjectByPrimaryKey(Class objectType,Long primaryKey)throws CSObjectNotFoundException{
		Object oj = null;
		
		Session s = null;
		
		try {
			
			s = sf.openSession();	
			
			oj = s.load(objectType,primaryKey);
			
			if(oj==null){
				throw new CSObjectNotFoundException(objectType.getName()+" not found");
			}
			
			
		} catch (Exception ex) {
			
			//ex.printStackTrace();
			try {
				s.close();
						} catch (Exception ex2) {
						}
			
						throw new CSObjectNotFoundException(objectType.getName()+" not found",ex);
		}
		
		return oj;
	}
	private void removeObject(Object oj) throws CSTransactionException{
		
		Session s = null;
		Transaction t = null;
		try {
			
			s = sf.openSession();	
			t = s.beginTransaction();
			
			s.delete(oj);
			
			t.commit();
			s.close();
			 
		} catch (Exception ex) {
			try {
				s.close();
			} catch (Exception ex2) {
			}
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad",ex);
		}
		
	}
	private Application getApplicationByName(String contextName) throws CSObjectNotFoundException{
		Session s = null;
		Application app = null;
		try {
			Application search = new Application();
			search.setApplicationName(contextName);
			//String query = "FROM gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();	
			List list = s.createCriteria(Application.class).add(Example.create(search)).list();
			//p = (Privilege)s.load(Privilege.class,new Long(privilegeId));
			System.out.println("Somwthing");
			if(list.size()==0){
				throw new CSObjectNotFoundException("Not found");
			}
			app = (Application)list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				s.close();
			} catch (Exception ex2) {
			}
			
			
		}
		return app;
	}
}