package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.UserGroupRoleProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.UserProtectionElement;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.hibernate.ProtectionGroupProtectionElement;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringUtilities;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;

import javax.security.auth.Subject;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Example;
import net.sf.hibernate.expression.Expression;

import org.apache.log4j.Logger;

/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public class AuthorizationDAOImpl implements AuthorizationDAO {

	static final Logger log = Logger.getLogger(AuthorizationDAOImpl.class
			.getName());

	private SessionFactory sf = null;

	private Application application = null;

	public AuthorizationDAOImpl(SessionFactory sf, String applicationContextName) {
		setHibernateSessionFactory(sf);
		try {
			log.debug("The context Name passed:" + applicationContextName);

			setApplication(getApplicationByName(applicationContextName));

			log.debug("The Application:" + application.getApplicationId() + ":"
					+ application.getApplicationDescription());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"Unable to Instantiate the AuthorizationDAOImpl"
							+ ex.getMessage());

		}
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void setHibernateSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}

	public void assignGroupsToUser(String userId, String[] groupIds)
			throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));
			//Set user_groups = user.getGroups();
			//Group group = getGroup(groupId);
			HashSet newGroups = new HashSet();
			for (int k = 0; k < groupIds.length; k++) {
				log.debug("The new list:" + groupIds[k]);
				Group group = (Group) this.getObjectByPrimaryKey(Group.class,
						groupIds[k]);
				if (group != null) {
					newGroups.add(group);
				}
			}

			user.setGroups(newGroups);
			s.update(user);

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to add a user id: "
							+ userId + " to group ids: "
							+ StringUtilities.stringArrayToString(groupIds), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignGroupRoleToProtectionGroup(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */

	public void assignGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup pgroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			Group group = (Group) this.getObjectByPrimaryKey(s, Group.class,
					new Long(groupId));

			for (int i = 0; i < rolesId.length; i++) {
				UserGroupRoleProtectionGroup intersection = new UserGroupRoleProtectionGroup();

				intersection.setGroup(group);
				intersection.setProtectionGroup(pgroup);
				Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
						new Long(rolesId[i]));

				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("protectionGroup", pgroup));
				criteria.add(Expression.eq("group", group));
				criteria.add(Expression.eq("role", role));

				List list = criteria.list();

				if (list.size() == 0) {
					intersection.setRole(role);
					intersection.setUpdateDate(new Date());
					s.save(intersection);
				}

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to assign group id: "
							+ groupId + " to protection group id: "
							+ protectionGroupId + "with role ids: "
							+ StringUtilities.stringArrayToString(rolesId), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignPrivilegesToRole(java.lang.String[],
	 *      java.lang.String)
	 */

	public void assignPrivilegesToRole(String roleId, String[] privilegeIds)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
					new Long(roleId));

			//Set currPriv = role.getPrivileges();
			Set newPrivs = new HashSet();

			for (int k = 0; k < privilegeIds.length; k++) {
				log.debug("The new list:" + privilegeIds[k]);
				Privilege pr = (Privilege) this.getObjectByPrimaryKey(
						Privilege.class, privilegeIds[k]);
				if (pr != null) {
					newPrivs.add(pr);
				}
			}
			role.setPrivileges(newPrivs);
			s.update(role);
			t.commit();

			//log.debug( "Privilege ID is: " +
			// privilege.getId().doubleValue() );
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to assign privilege ids: "
							+ StringUtilities.stringArrayToString(privilegeIds)
							+ " to role id: " + roleId, ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String,
	 *      java.lang.String[], java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String protectionElementObjectId,
			String protectionElementAttributeName)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup protectionGroup = getProtectionGroup(protectionGroupName);
			ProtectionElement protectionElement = getProtectionElement(
					protectionElementObjectId, protectionElementAttributeName);

			Criteria criteria = s
					.createCriteria(ProtectionGroupProtectionElement.class);
			criteria.add(Expression.eq("protectionGroup", protectionGroup));
			criteria.add(Expression.eq("protectionElement", protectionElement));

			List list = criteria.list();

			if (list.size() == 0) {
				ProtectionGroupProtectionElement pgpe = new ProtectionGroupProtectionElement();
				pgpe.setProtectionElement(protectionElement);
				pgpe.setProtectionGroup(protectionGroup);
				pgpe.setUpdateDate(new Date());

				s.save(pgpe);
			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to assign protection "
							+ "element object id: " + protectionElementObjectId
							+ " with protection element attribute "
							+ protectionElementAttributeName
							+ " to protection group name: "
							+ protectionGroupName, ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String,
	 *      java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException {
		// TODO Auto-generated method stub

		this.assignProtectionElements(protectionGroupName,
				protectionElementObjectId, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignUserRoleToProtectionGroup(java.lang.String,
	 *      java.lang.String[], java.lang.String)
	 */
	public void assignUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId)
			throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup pgroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));

			for (int i = 0; i < rolesId.length; i++) {
				UserGroupRoleProtectionGroup intersection = new UserGroupRoleProtectionGroup();

				intersection.setUser(user);
				intersection.setProtectionGroup(pgroup);
				Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
						new Long(rolesId[i]));

				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("protectionGroup", pgroup));
				criteria.add(Expression.eq("user", user));
				criteria.add(Expression.eq("role", role));

				List list = criteria.list();

				if (list.size() == 0) {
					intersection.setRole(role);
					intersection.setUpdateDate(new Date());
					s.save(intersection);
				}

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to a user id: "
							+ userId + " with role ids: "
							+ StringUtilities.stringArrayToString(rolesId)
							+ " to protection group id: " + protectionGroupId,
					ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
	 *      java.lang.String)
	 */
	public boolean checkPermission(AccessPermission permission, String userName) {
		// TODO Auto-generated method stub

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
	 *      javax.security.auth.Subject)
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
	 *      gov.nih.nci.security.authorization.domainobjects.User)
	 */
	public boolean checkPermission(AccessPermission permission, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeId, String privilegeName) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String privilegeName) throws CSTransactionException {
		// TODO Auto-generated method stub

		//log.debug("NOW____)))");
		boolean test = false;
		Session s = null;
		Transaction t = null;
		Connection cn = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();
			cn = s.connection();

			//User user = this.getUser(userName);
			//ProtectionElement pe = this.getProtectionElement(objectId);

			StringBuffer stbr = new StringBuffer();
			stbr.append("select 'X'");
			stbr.append("from protection_group pg,");
			stbr.append("protection_element pe,");
			stbr.append("protection_group_protection_element pgpe,");
			stbr.append("user_group_role_protection_group ugrpg,");
			stbr.append("user u,");
			stbr.append("role_privilege rp,");
			stbr.append("privilege p ");
			stbr
					.append("where pgpe.protection_group_id = pg.protection_group_id ");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id=?");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and ugrpg.user_id = u.user_id");
			stbr.append(" and u.login_name=?");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name=?");
			String sql = stbr.toString();
			PreparedStatement pstmt = cn.prepareStatement(sql);

			//Long priv_id = new Long(privilegeName);
			pstmt.setString(1, objectId);
			pstmt.setString(2, userName);
			pstmt.setString(3, privilegeName);
			//log.debug("NOW____");
			//log.debug(System.currentTimeMillis());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				test = true;
			}
			//log.debug(System.currentTimeMillis());
			rs.close();
			pstmt.close();
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to "
							+ " check permission with user name: " + userName
							+ " object id: " + objectId
							+ " and privilege name " + privilegeName, ex);
		} finally {
			try {
				cn.close();
				s.close();
			} catch (Exception ex2) {
			}
		}
		return test;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createGroup(gov.nih.nci.security.authorization.domainobjects.Group)
	 */
	/**
	 * public void createGroup(Group group) throws CSTransactionException { //
	 * TODO Auto-generated method stub Session s = null; Transaction t = null;
	 * try { s = sf.openSession(); t = s.beginTransaction();
	 * group.setApplication(application); group.setUpdateDate(new Date());
	 * s.save(group); t.commit(); log.debug("Group ID is: " +
	 * group.getGroupId()); } catch (Exception ex) { log.error(ex); try {
	 * t.rollback(); } catch (Exception ex3) { } throw new
	 * CSTransactionException("Bad", ex); } finally { try { s.close(); } catch
	 * (Exception ex2) { } } }
	 */
	/**
	 * public void createPrivilege(Privilege privilege) throws
	 * CSTransactionException { // TODO Auto-generated method stub Session s =
	 * null; Transaction t = null; try { s = sf.openSession(); t =
	 * s.beginTransaction(); privilege.setUpdateDate(new Date());
	 * s.save(privilege); t.commit();
	 * 
	 * log.debug("Privilege ID is: " + privilege.getId().doubleValue()); } catch
	 * (Exception ex) { log.error(ex); try { t.rollback(); } catch (Exception
	 * ex3) { } throw new CSTransactionException("Bad", ex); } finally { try {
	 * s.close(); } catch (Exception ex2) { } } }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
	 */
	/**
	 * public void createProtectionElement(ProtectionElement protectionElement)
	 * throws CSTransactionException { Session s = null; Transaction t = null;
	 * try { s = sf.openSession(); t = s.beginTransaction();
	 * protectionElement.setApplication(application);
	 * protectionElement.setUpdateDate(new Date()); s.save(protectionElement);
	 * t.commit(); log.debug("Protection element ID is: " +
	 * protectionElement.getProtectionElementId()); } catch (Exception ex) {
	 * ex.printStackTrace(); log.error(ex); try { t.rollback(); } catch
	 * (Exception ex3) { } throw new CSTransactionException( "Protection Element
	 * could not be created:", ex); } finally { try { s.close(); } catch
	 * (Exception ex2) { } } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
	 */
	/**
	 * public void createProtectionGroup(ProtectionGroup protectionGroup) throws
	 * CSTransactionException { Session s = null; Transaction t = null; try { s =
	 * sf.openSession(); t = s.beginTransaction();
	 * protectionGroup.setApplication(application);
	 * protectionGroup.setUpdateDate(new Date()); s.save(protectionGroup);
	 * t.commit(); log.debug("Protection group ID is: " +
	 * protectionGroup.getProtectionGroupId()); } catch (Exception ex) {
	 * log.error(ex); try { t.rollback(); } catch (Exception ex3) { } throw new
	 * CSTransactionException( "Protection group could not be created:", ex); }
	 * finally { try { s.close(); } catch (Exception ex2) { } } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createRole(gov.nih.nci.security.authorization.domainobjects.Role)
	 */
	/**
	 * public void createRole(Role role) throws CSTransactionException { // TODO
	 * Auto-generated method stub
	 * 
	 * Session s = null; Transaction t = null; try { s = sf.openSession(); t =
	 * s.beginTransaction(); role.setApplication(application);
	 * role.setUpdateDate(new Date()); s.save(role); t.commit(); log.debug("Role
	 * ID is: " + role.getId().doubleValue()); } catch (Exception ex) {
	 * log.error(ex); try { t.rollback(); } catch (Exception ex3) { } throw new
	 * CSTransactionException("Bad", ex); } finally { try { s.close(); } catch
	 * (Exception ex2) { } } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String,
	 *      java.lang.String[], java.lang.String[])
	 */
	/**
	 * Don't implement this method 'cause from authorization manager no body
	 * will pass an array
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectNames,
			String[] protectionElementAttributeNames)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String[],
	 *      java.lang.String)
	 */
	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 *  
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException {

		try {
			ProtectionGroup protectionGroup = this
					.getProtectionGroup(protectionGroupName);
			ProtectionElement protectionElement = this
					.getProtectionElement(protectionElementObjectId);

			String pgId = protectionGroup.getProtectionGroupId().toString();
			String[] peIds = { protectionElement.getProtectionElementId()
					.toString() };

			this.removeProtectionElementsFromProtectionGroup(pgId, peIds);
		} catch (Exception ex) {
			throw new CSTransactionException(
					"A fatal error occured while attempting to "
							+ "de-assign protectionElementObjectId: "
							+ protectionElementObjectId
							+ " from protection group name: "
							+ protectionGroupName, ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getObjects(gov.nih.nci.security.dao.SearchCriteria)
	 */
	public List getObjects(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Session s = null;
		List result = new ArrayList();
		try {

			s = sf.openSession();
			Criteria criteria = s
					.createCriteria(searchCriteria.getObjectType());
			Hashtable fieldValues = searchCriteria.getFieldAndValues();
			Enumeration en = fieldValues.keys();
			System.out.println("We here");
			while (en.hasMoreElements()) {
				String str = (String) en.nextElement();
				int i = ((String) fieldValues.get(str)).indexOf("%");
				if (i != -1) {
					criteria.add(Expression.like(str, fieldValues.get(str)));
				} else {
					criteria.add(Expression.eq(str, fieldValues.get(str)));
				}
			}
			if (fieldValues.size() == 0) {
				criteria.add(Expression.eqProperty("1", "1"));
			}
			result = criteria.list();

		} catch (Exception ex) {
			log.fatal("Unable to get objects", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrincipals(java.lang.String)
	 */
	public Principal[] getPrincipals(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrivilege(java.lang.String)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionElement(java.lang.String)
	 */
	public ProtectionElement getProtectionElement(String objectId,
			String attribute) throws CSObjectNotFoundException {
		Session s = null;
		ProtectionElement pe = null;
		try {
			ProtectionElement search = new ProtectionElement();
			search.setObjectId(objectId);
			search.setApplication(application);
			if (attribute != null) {
				search.setAttribute(attribute);
			}
			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(ProtectionElement.class).add(
					Example.create(search)).list();

			if (list.size() == 0) {
				throw new CSObjectNotFoundException(
						"Protection Element not found");
			}
			pe = (ProtectionElement) list.get(0);

		} catch (Exception ex) {
			log.fatal("Unable to find Protection Element", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return pe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionElement(java.lang.String)
	 */
	public ProtectionElement getProtectionElement(String objectId)
			throws CSObjectNotFoundException {
		return getProtectionElement(objectId, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionGroup(java.lang.String)
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		Session s = null;
		ProtectionGroup pgrp = null;
		try {
			ProtectionGroup search = new ProtectionGroup();
			search.setProtectionGroupName(protectionGroupName);
			search.setApplication(application);
			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(ProtectionGroup.class).add(
					Example.create(search)).list();

			if (list.size() == 0) {
				throw new CSObjectNotFoundException(
						"Protection Group not found");
			}
			pgrp = (ProtectionGroup) list.get(0);

		} catch (Exception ex) {
			log.fatal("Unable to find Protection group", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return pgrp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getRole(java.lang.String)
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		Session s = null;
		Role role = null;
		try {
			Role search = new Role();
			search.setName(roleName);
			search.setApplication(application);
			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(Role.class)
					.add(Example.create(search)).list();

			if (list.size() == 0) {
				throw new CSObjectNotFoundException("Role not found");
			}
			role = (Role) list.get(0);

		} catch (Exception ex) {
			log.fatal("Unable to find Protection Element", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getUser(java.lang.String)
	 */
	public User getUser(String loginName) {
		Session s = null;
		User user = null;
		try {
			User search = new User();
			search.setLoginName(loginName);

			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(User.class)
					.add(Example.create(search)).list();
			//p = (Privilege)s.load(Privilege.class,new Long(privilegeId));

			if (list.size() != 0) {
				user = (User) list.get(0);
			}

			Collection groups = user.getGroups();
			Iterator it = groups.iterator();
			while (it.hasNext()) {
				Group grp = (Group) it.next();
				log.debug("The user Id:" + grp.getGroupId());
			}

		} catch (Exception ex) {
			log.fatal("Unable to find Group", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupFromProtectionGroup(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId,
			String groupId) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		Connection cn = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();
			cn = s.connection();
			String sql = "delete from user_group_role_protection_group where protection_group_id=? and group_id=?";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			Long pg_id = new Long(protectionGroupId);
			Long g_id = new Long(groupId);
			pstmt.setLong(1, pg_id.longValue());
			pstmt.setLong(2, g_id.longValue());

			int i = pstmt.executeUpdate();

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				cn.close();
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupRoleFromProtectionGroup(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup pgroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			Group group = (Group) this.getObjectByPrimaryKey(s, Group.class,
					new Long(groupId));

			for (int i = 0; i < rolesId.length; i++) {
				UserGroupRoleProtectionGroup intersection = new UserGroupRoleProtectionGroup();

				intersection.setGroup(group);
				intersection.setProtectionGroup(pgroup);
				Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
						new Long(rolesId[i]));

				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("protectionGroup", pgroup));
				criteria.add(Expression.eq("group", group));
				criteria.add(Expression.eq("role", role));

				List list = criteria.list();

				if (list.size() == 0) {
					intersection.setRole(role);
					intersection.setUpdateDate(new Date());
					s.delete(intersection);
				}

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeRole(java.lang.String)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromGroup(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeUserFromGroup(String groupId, String userId)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));
			Group group = (Group) this.getObjectByPrimaryKey(s, Group.class,
					new Long(groupId));
			Set groups = user.getGroups();
			if (groups.contains(group)) {
				groups.remove(group);
				user.setGroups(groups);
				s.update(user);
			}

			t.commit();

			//log.debug( "Privilege ID is: " +
			// privilege.getId().doubleValue() );
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromProtectionGroup(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId,
			String userId) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		Connection cn = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();
			cn = s.connection();
			String sql = "delete from user_group_role_protection_group where protection_group_id=? and user_id=?";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			Long pg_id = new Long(protectionGroupId);
			Long u_id = new Long(userId);
			pstmt.setLong(1, pg_id.longValue());
			pstmt.setLong(2, u_id.longValue());

			int i = pstmt.executeUpdate();

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				cn.close();
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserRoleFromProtectionGroup(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId,
			String userId, String[] rolesId) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup pgroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));

			for (int i = 0; i < rolesId.length; i++) {
				UserGroupRoleProtectionGroup intersection = new UserGroupRoleProtectionGroup();

				intersection.setUser(user);
				intersection.setProtectionGroup(pgroup);
				Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
						new Long(rolesId[i]));

				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("protectionGroup", pgroup));
				criteria.add(Expression.eq("user", user));
				criteria.add(Expression.eq("role", role));

				List list = criteria.list();

				if (list.size() == 0) {
					intersection.setRole(role);
					intersection.setUpdateDate(new Date());
					s.delete(intersection);
				}

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/**
	 *  
	 */
	private User getLightWeightUser(String loginName) {
		Session s = null;
		User user = null;
		try {
			User search = new User();
			search.setLoginName(loginName);

			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(User.class)
					.add(Example.create(search)).list();
			//p = (Privilege)s.load(Privilege.class,new Long(privilegeId));

			if (list.size() != 0) {
				user = (User) list.get(0);
			}

		} catch (Exception ex) {
			log.fatal("Unable to find Group", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return user;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void setOwnerForProtectionElement(String loginName,
			String protectionElementObjectName,
			String protectionElementAttributeName)
			throws CSTransactionException {
		// TODO Auto-generated method stub

		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			User user = getLightWeightUser(loginName);
			ProtectionElement pe = getProtectionElement(
					protectionElementObjectName, protectionElementAttributeName);

			Criteria criteria = s.createCriteria(UserProtectionElement.class);
			criteria.add(Expression.eq("user", user));
			criteria.add(Expression.eq("protectionElement", pe));

			List list = criteria.list();
			if (list.size() <= 0) {
				UserProtectionElement intersection = new UserProtectionElement();

				intersection.setUser(user);
				intersection.setProtectionElement(pe);
				intersection.setUpdateDate(new Date());
				s.save(intersection);
			}
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String,
	 *      java.lang.String)
	 */
	public void setOwnerForProtectionElement(
			String protectionElementObjectName, String userName)
			throws CSTransactionException {
		// TODO Auto-generated method stub
		setOwnerForProtectionElement(userName, protectionElementObjectName,
				null);
	}

	public Set getPrivileges(String roleId) throws CSObjectNotFoundException {
		Session s = null;
		log.debug("The role: getting there");
		//ArrayList result = new ArrayList();
		Set result = new HashSet();
		try {
			s = sf.openSession();
			Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
					new Long(roleId));
			log.debug("The role:" + role.getName());
			result = role.getPrivileges();
			log.debug("The result size:" + result.size());

		} catch (Exception ex) {
			log.error(ex);
			throw new CSObjectNotFoundException("No Set found", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return result;
	}

	/**
	 * public void createUser(User user) throws CSTransactionException { Session
	 * s = null; Transaction t = null; try { s = sf.openSession(); t =
	 * s.beginTransaction(); user.setUpdateDate(new Date()); s.save(user);
	 * t.commit(); log.debug("User ID is: " + user.getUserId()); } catch
	 * (Exception ex) { log.error(ex); try { t.rollback(); } catch (Exception
	 * ex3) { } throw new CSTransactionException("Could not create the user",
	 * ex); } finally { try { s.close(); } catch (Exception ex2) { } } }
	 */

	public void assignProtectionElements(String protectionGroupId,
			String[] protectionElementIds) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			//log.debug("The original user Id:"+userId);

			ProtectionGroup protectionGroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			for (int i = 0; i < protectionElementIds.length; i++) {
				ProtectionGroupProtectionElement intersection = new ProtectionGroupProtectionElement();
				ProtectionElement protectionElement = (ProtectionElement) this
						.getObjectByPrimaryKey(s, ProtectionElement.class,
								new Long(protectionElementIds[i]));

				Criteria criteria = s
						.createCriteria(ProtectionGroupProtectionElement.class);
				criteria.add(Expression.eq("protectionGroup", protectionGroup));
				criteria.add(Expression.eq("protectionElement",
						protectionElement));
				List list = criteria.list();
				if (list.size() == 0) {
					intersection.setProtectionGroup(protectionGroup);
					intersection.setProtectionElement(protectionElement);
					intersection.setUpdateDate(new Date());
					s.save(intersection);
				}

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	public void removeProtectionElementsFromProtectionGroup(
			String protectionGroupId, String[] protectionElementIds)
			throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			//log.debug("The original user Id:"+userId);

			ProtectionGroup protectionGroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			for (int i = 0; i < protectionElementIds.length; i++) {
				ProtectionGroupProtectionElement intersection = new ProtectionGroupProtectionElement();
				ProtectionElement protectionElement = (ProtectionElement) this
						.getObjectByPrimaryKey(s, ProtectionElement.class,
								new Long(protectionElementIds[i]));

				intersection.setProtectionGroup(protectionGroup);
				intersection.setProtectionElement(protectionElement);
				intersection.setUpdateDate(new Date());
				this.removeObject(intersection);

			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
	}

	private Object getObjectByPrimaryKey(Session s, Class objectType,
			Long primaryKey) throws HibernateException,
			CSObjectNotFoundException {

		Object obj = s.load(objectType, primaryKey);

		if (obj == null) {
			throw new CSObjectNotFoundException(objectType.getName()
					+ " not found");
		}

		return obj;

	}

	public Object getObjectByPrimaryKey(Class objectType, String primaryKey)
			throws CSObjectNotFoundException {
		Object oj = null;

		Session s = null;

		try {

			s = sf.openSession();
			oj = getObjectByPrimaryKey(s, objectType, new Long(primaryKey));

		} catch (Exception ex) {
			log.error(ex);
			throw new CSObjectNotFoundException(objectType.getName()
					+ " not found", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

		return oj;
	}

	public void removeObject(Object oj) throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			s.delete(oj);

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}

	private Application getApplicationByName(String contextName)
			throws CSObjectNotFoundException {
		Session s = null;
		Application app = null;
		try {
			Application search = new Application();
			search.setApplicationName(contextName);
			//String query = "FROM
			// gov.nih.nci.security.authorization.domianobjects.Application";
			s = sf.openSession();
			List list = s.createCriteria(Application.class).add(
					Example.create(search)).list();
			//p = (Privilege)s.load(Privilege.class,new Long(privilegeId));
			log.debug("Somwthing");
			if (list.size() == 0) {
				log.debug("Could not find the Application");
				throw new CSObjectNotFoundException("Not found");
			}
			app = (Application) list.get(0);
			log.debug("Found the Application");

		} catch (Exception ex) {
			log.fatal("Unable to find application context", ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return app;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.AuthorizationManager#initialize(java.lang.String)
	 */
	public void initialize(String applicationContextName) {
		//do nothing...

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupRoleContext()
	 *      We might not implement this method
	 */
	public Set getProtectionGroupRoleContext(String userId)
			throws CSObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyObject(Object obj) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		try {
			log.debug("About to be Modified");
			s = sf.openSession();
			t = s.beginTransaction();

			s.update(obj);
			log.debug("Modified");
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Object could not be modified:",
					ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
	}

	public Application getApplication() {
		return this.application;
	}

	public void createObject(Object obj) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			s.save(obj);
			t.commit();
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException("Bad", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
	}

	/**
	 * @param application
	 *            The application to set.
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	
	public Set getGroups(String userId) throws CSObjectNotFoundException{
		Session s = null;
		Set groups = new HashSet();
		try {
			s = sf.openSession();
			
			User user = (User) this.getObjectByPrimaryKey(s, User.class,new Long(userId));
			groups = user.getGroups();
			log.debug("The result size:" + groups.size());
			

		} catch (Exception ex) {
			log.error(ex);
			
			throw new CSObjectNotFoundException(
					"A fatal error occurred while getting groups for a user id: "
							+ userId , ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		
		return groups;

	}
	
	public Set getProtectionElements(String protectionGroupId) throws CSObjectNotFoundException{
		Session s = null;
		log.debug("The role: getting there");
		//ArrayList result = new ArrayList();
		Set result = new HashSet();
		try {
			s = sf.openSession();
			ProtectionGroup protectionGroup = (ProtectionGroup) this.getObjectByPrimaryKey(s, ProtectionGroup.class,
					new Long(protectionGroupId));
			//log.debug("The role:" + role.getName());
			result = protectionGroup.getProtectionElements();
			log.debug("The result size:" + result.size());
            
		} catch (Exception ex) {
			log.error(ex);
			throw new CSObjectNotFoundException("No Set found", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return result;
	}
	
	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException{
		Session s = null;
		log.debug("The role: getting there");
		//ArrayList result = new ArrayList();
		Set result = new HashSet();
		try {
			s = sf.openSession();
			ProtectionElement protectionElement = (ProtectionElement) this.getObjectByPrimaryKey(s, ProtectionElement.class,
					new Long(protectionElementId));
			//log.debug("The role:" + role.getName());
			result = protectionElement.getProtectionGroups();
			log.debug("The result size:" + result.size());
            
		} catch (Exception ex) {
			log.error(ex);
			throw new CSObjectNotFoundException("No Set found", ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
		return result;
	}
	
	public void assignToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException{
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionElement protectionElement = (ProtectionElement) this.getObjectByPrimaryKey(s, ProtectionElement.class,
					new Long(protectionElementId));

			
			Set newSet = new HashSet();

			for (int k = 0; k < protectionGroupIds.length; k++) {
				log.debug("The new list:" + protectionGroupIds[k]);
				ProtectionGroup pg = (ProtectionGroup) this.getObjectByPrimaryKey(
						ProtectionGroup.class, protectionGroupIds[k]);
				if (pg != null) {
					newSet.add(pg);
				}
			}
			protectionElement.setProtectionGroups(newSet);
			s.update(protectionElement);
			t.commit();

			
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to assign group ids: "
							+ StringUtilities.stringArrayToString(protectionGroupIds)
							+ " to protection element id: " + protectionElementId, ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}

	}
	
	public void assignParentProtectionGroup(String parentProtectionGroupId,String childProtectionGroupId) throws CSTransactionException{
		Session s = null;
		Transaction t = null;
		//log.debug("Running create test...");
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			ProtectionGroup parent = null;
            if(parentProtectionGroupId!=null){
			parent = (ProtectionGroup) this.getObjectByPrimaryKey(s,
					ProtectionGroup.class, new Long(parentProtectionGroupId));
            }
            
			ProtectionGroup child = (ProtectionGroup) this.getObjectByPrimaryKey(s,
					ProtectionGroup.class, new Long(childProtectionGroupId));
			
			child.setParentProtectionGroup(parent);
			
			s.update(child);
			t.commit();

			
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
			throw new CSTransactionException(
					"A fatal error occurred while attempting to assign parent protection group: "
							, ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
			}
		}
	}
}