package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.ObjectAccessMap;
import gov.nih.nci.security.authorization.ObjectPrivilegeMap;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.UserGroupRoleProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.UserProtectionElement;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.hibernate.ProtectionGroupProtectionElement;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringUtilities;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

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
 * @version 1.0 created 03-Dec-2004 1:17:47 AM
 */
public class AuthorizationDAOImpl implements AuthorizationDAO {

	static final Logger log = Logger.getLogger(AuthorizationDAOImpl.class
			.getName());

	private SessionFactory sf = null;

	private Application application = null;

	private String typeOfAccess = "MIXED";

	public AuthorizationDAOImpl(SessionFactory sf, String applicationContextName) {
		setHibernateSessionFactory(sf);
		try {
			Application app = this.getApplicationByName(applicationContextName);
			if (app == null) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|"
									+ applicationContextName
									+ "||AuthorizationDAOImpl|Failure|No Application found for the Context Name|");
				throw new Exception(
						"Unable to retrieve Application with this Context Name");
			}
			this.setApplication(app);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|"
								+ applicationContextName
								+ "||AuthorizationDAOImpl|Failure|Cannot instantiate AuthorizationDAOImpl|"
								+ ex.getMessage());
			throw new RuntimeException(
					"Unable to Instantiate the AuthorizationDAOImpl");
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|"
							+ applicationContextName
							+ "||AuthorizationDAOImpl|Success|Instantiated AuthorizationDAOImpl|");
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
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));
			HashSet newGroups = new HashSet();
			for (int k = 0; k < groupIds.length; k++) {
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupsToUser|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignGroupsToUser|Failure|Error occurred in assigning Groups "
								+ StringUtilities.stringArrayToString(groupIds)
								+ " to User " + userId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Groups to User\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupsToUser|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignGroupsToUser|Success|Successful in assigning Groups "
							+ StringUtilities.stringArrayToString(groupIds)
							+ " to User " + userId + "|");
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupsToUser|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error Occured in assigning Roles "
								+ StringUtilities.stringArrayToString(rolesId)
								+ " to Group "
								+ groupId
								+ " and Protection Group"
								+ protectionGroupId
								+ "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Protection Group and Roles to a Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignGroupRoleToProtectionGroup|Success|Successful in assigning Roles "
							+ StringUtilities.stringArrayToString(rolesId)
							+ " to Group "
							+ groupId
							+ " and Protection Group"
							+ protectionGroupId + "|");
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

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignPrivilegesToRole|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignPrivilegesToRole|Failure|Error Occured in assigning Privilege "
								+ StringUtilities
										.stringArrayToString(privilegeIds)
								+ " to Role " + roleId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Privileges to Role\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignPrivilegesToRole|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignPrivilegesToRole|Success|Success in assigning Privilege "
							+ StringUtilities.stringArrayToString(privilegeIds)
							+ " to Role " + roleId + "|");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String,
	 *      java.lang.String[], java.lang.String[])
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId,
			String protectionElementAttributeName)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		try {

			s = sf.openSession();
			t = s.beginTransaction();

			if (StringUtilities.isBlank(protectionGroupName)) {
				throw new CSTransactionException(
						"The protectionGroupName can't be null");
			}
			if (StringUtilities.isBlank(protectionElementObjectId)) {
				throw new CSTransactionException(
						"The protectionElementObjectId can't be null");
			}

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
			} else {
				throw new CSTransactionException(
						"This association already exist!");
			}

			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignProtectionElements|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignProtectionElements|Failure|Error Occured in assigning Protection Element with Object Id "
								+ protectionElementObjectId
								+ " with protection element attribute "
								+ protectionElementAttributeName
								+ " to protection group name: "
								+ protectionGroupName + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Protection Element to Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignProtectionElements|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignProtectionElements|Success|Successful in assigning Protection Element with Object Id "
							+ protectionElementObjectId
							+ " with protection element attribute "
							+ protectionElementAttributeName
							+ " to protection group name: "
							+ protectionGroupName + "|");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String,
	 *      java.lang.String[])
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException {

		this.assignProtectionElement(protectionGroupName,
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignUserRoleToProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error Occured in assigning Roles "
								+ StringUtilities.stringArrayToString(rolesId)
								+ " to User "
								+ userId
								+ " and Protection Group"
								+ protectionGroupId
								+ "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Protection Group and Roles to a User\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignUserRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignGroupRoleToProtectionGroup|Success|Successful in assigning Roles "
							+ StringUtilities.stringArrayToString(rolesId)
							+ " to User "
							+ userId
							+ " and Protection Group"
							+ protectionGroupId + "|");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
	 *      java.lang.String)
	 */
	public boolean checkPermission(AccessPermission permission, String userName)
			throws CSException {
		if (permission == null) {
			throw new CSException("permission can't be null !");
		}
		String objectId = permission.getName();
		String privilege = permission.getActions();

		return checkPermission(userName, objectId, privilege);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
	 *      javax.security.auth.Subject)
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject)
			throws CSException {

		boolean test = false;
		if (permission == null) {
			throw new CSException("permission can't be null!");
		}
		String objectId = permission.getName();
		String privilege = permission.getActions();
		if (subject == null) {
			throw new CSException("subject can't be null!");
		}
		Set ps = subject.getPrincipals();
		if (ps.size() == 0) {
			throw new CSException("The subject has no principals!");
		}
		Iterator it = ps.iterator();

		while (it.hasNext()) {
			Principal p = (Principal) it.next();
			String userName = p.getName();
			test = this.checkPermission(userName, objectId, privilege);
			if (test)
				break;
		}

		return test;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeName, String privilegeName) throws CSException {

		ResultSet rs = null;
		Statement stmt = null;
		boolean test = false;
		Session s = null;

		Connection cn = null;
		if (StringUtilities.isBlank(userName)) {
			throw new CSException("user name can't be null!");
		}
		if (StringUtilities.isBlank(objectId)) {
			throw new CSException("objectId can't be null!");
		}
		test = this.checkOwnership(userName, objectId);
		if (test)
			return true;

		if (attributeName == null || privilegeName == null) {
			return false;
		}

		try {

			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("select 'X'");
			stbr.append(" from protection_group pg,");
			stbr.append(" protection_element pe,");
			stbr.append(" protection_group_protection_element pgpe,");
			stbr.append(" user_group_role_protection_group ugrpg,");
			stbr.append(" user u,");
			stbr.append(" groups g,");
			stbr.append(" user_group ug,");
			stbr.append(" role_privilege rp,");
			stbr.append(" privilege p ");
			stbr
					.append(" where pgpe.protection_group_id = pg.protection_group_id");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id='" + objectId + "'");
			stbr.append(" and pe.attribute='" + attributeName + "'");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and (( ugrpg.group_id = g.group_id");
			stbr.append("       and ug.user_id = u.user_id)");
			stbr.append("       or ");
			stbr.append("     (ugrpg.user_id = u.user_id))");
			stbr.append(" and u.login_name='" + userName + "'");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name='" + privilegeName + "'");

			String sql = stbr.toString();
			log.debug( "checkPermission with attr sql: " + sql );
			stmt = cn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				test = true;
			}
			rs.close();

			stmt.close();

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Failed to get privileges for " + userName + "|"
						+ ex.getMessage());
			throw new CSException("Failed to get privileges for " + userName
					+ "|" + ex.getMessage(), ex);
		} finally {
			try {

				s.close();
				rs.close();
				stmt.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getPrivilegeMap|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}

		return test;
	}

	public boolean checkPermission(String userName, String objectId,
			String privilegeName) throws CSException {
		boolean test = false;

		if (StringUtilities.isBlank(userName)) {
			throw new CSException("user name can't be null!");
		}
		if (StringUtilities.isBlank(objectId)) {
			throw new CSException("objectId can't be null!");
		}
		test = this.checkOwnership(userName, objectId);
		if (test)
			return true;

		if (typeOfAccess.equalsIgnoreCase("MIXED")) {
			test = this.checkPermissionForUserAndGroup(userName, objectId,
					privilegeName);

			return test;
		}

		if (typeOfAccess.equalsIgnoreCase("GROUP_ONLY")) {
			test = this.checkPermissionForGroup(userName, objectId,
					privilegeName);

			return test;
		}

		if (typeOfAccess.equalsIgnoreCase("USER_ONLY")) {
			test = this.checkPermissionForUser(userName, objectId,
					privilegeName);

			return test;
		}

		return test;
	}

	private boolean checkPermissionForUser(String userName, String objectId,
			String privilegeName) throws CSException {
		boolean test = false;
		Session s = null;
		Statement stmt = null;
		ResultSet rs = null;
		Connection cn = null;
		if (userName == null || objectId == null || privilegeName == null) {
			return false;
		}
		try {

			s = sf.openSession();

			cn = s.connection();

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
			stbr.append(" and pe.object_id='" + objectId + "'");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and ugrpg.user_id = u.user_id");
			stbr.append(" and u.login_name='" + userName + "'");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name='" + privilegeName + "'");
			String sql = stbr.toString();
			stmt = cn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				test = true;
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			log.error(ex);

			if (log.isDebugEnabled())
				log
						.debug("Authorization||"
								+ userName
								+ "|checkPermission|Failure|Error Occured in checking permissions with user id "
								+ userName + " object id: " + objectId
								+ " and privilege name " + privilegeName + "|"
								+ ex.getMessage());
			throw new CSException(
					"An error occurred while checking permissions\n"
							+ ex.getMessage(), ex);
		} finally {
			try {

				s.close();
				rs.close();
				stmt.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||checkPermission|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization||"
							+ userName
							+ "|checkPermission|Success|Successful in checking permissions with user id "
							+ userName + " object id: " + objectId
							+ " and privilege name " + privilegeName
							+ " and the result is " + test + "|");
		return test;
	}

	private boolean checkPermissionForUserAndGroup(String userName,
			String objectId, String privilegeName) throws CSException {
		boolean test = false;
		Session s = null;
		Statement stmt = null;
		ResultSet rs = null;
		Connection cn = null;

		if (userName == null || objectId == null || privilegeName == null) {
			return false;
		}

		try {

			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("select 'X'");
			stbr.append(" from protection_group pg,");
			stbr.append(" protection_element pe,");
			stbr.append(" protection_group_protection_element pgpe,");
			stbr.append(" user_group_role_protection_group ugrpg,");
			stbr.append(" user u,");
			stbr.append(" groups g,");
			stbr.append(" user_group ug,");
			stbr.append(" role_privilege rp,");
			stbr.append(" privilege p ");
			stbr
					.append(" where pgpe.protection_group_id = pg.protection_group_id");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id='" + objectId + "'");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and (( ugrpg.group_id = g.group_id");
			stbr.append("       and ug.user_id = u.user_id)");
			stbr.append("       or ");
			stbr.append("     (ugrpg.user_id = u.user_id))");
			stbr.append(" and u.login_name='" + userName + "'");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name='" + privilegeName + "'");
			String sql = stbr.toString();
			stmt = cn.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				test = true;
			}
			rs.close();
			stmt.close();

		} catch (Exception ex) {
			log.error(ex);

			if (log.isDebugEnabled())
				log
						.debug("Authorization||"
								+ userName
								+ "|checkPermission|Failure|Error Occured in checking permissions with user id "
								+ userName + " object id: " + objectId
								+ " and privilege name " + privilegeName + "|"
								+ ex.getMessage());
			throw new CSException(
					"An error occurred while checking permissions\n"
							+ ex.getMessage(), ex);
		} finally {
			try {

				s.close();
				rs.close();
				stmt.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||checkPermission|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization||"
							+ userName
							+ "|checkPermission|Success|Successful in checking permissions with user id "
							+ userName + " object id: " + objectId
							+ " and privilege name " + privilegeName
							+ " and the result is " + test + "|");
		return test;
	}

	private boolean checkPermissionForGroup(String userName, String objectId,
			String privilegeName) throws CSException {
		boolean test = false;
		Session s = null;
		Statement stmt = null;
		ResultSet rs = null;
		Connection cn = null;
		try {

			if (privilegeName == null) {
				return false;
			}
			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("select 'X'");
			stbr.append("from protection_group pg,");
			stbr.append("protection_element pe,");
			stbr.append("protection_group_protection_element pgpe,");
			stbr.append("user_group_role_protection_group ugrpg,");
			stbr.append("user u,");
			stbr.append("groups g,");
			stbr.append("user_group ug,");
			stbr.append("role_privilege rp,");
			stbr.append("privilege p ");
			stbr
					.append("where pgpe.protection_group_id = pg.protection_group_id ");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id='" + objectId + "'");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and ugrpg.group_id = g.group_id ");
			stbr.append(" and ug.user_id = u.user_id");
			stbr.append(" and u.login_name='" + userName + "'");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name='" + privilegeName + "'");
			String sql = stbr.toString();
			stmt = cn.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				test = true;
			}
			rs.close();
			stmt.close();
			//t.commit();

		} catch (Exception ex) {
			log.error(ex);

			if (log.isDebugEnabled())
				log
						.debug("Authorization||"
								+ userName
								+ "|checkPermission|Failure|Error Occured in checking permissions with user id "
								+ userName + " object id: " + objectId
								+ " and privilege name " + privilegeName + "|"
								+ ex.getMessage());
			throw new CSException(
					"An error occurred while checking permissions\n"
							+ ex.getMessage(), ex);
		} finally {
			try {

				s.close();
				rs.close();
				stmt.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||checkPermission|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization||"
							+ userName
							+ "|checkPermission|Success|Successful in checking permissions with user id "
							+ userName + " object id: " + objectId
							+ " and privilege name " + privilegeName
							+ " and the result is " + test + "|");
		return test;
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
			if (log.isDebugEnabled()) {
				log
						.debug("Authorization|||deAssignProtectionElements|Failure|Error Occured in deassigning Protection Group "
								+ protectionGroupName
								+ " and Protection Element "
								+ protectionElementObjectId
								+ "|"
								+ ex.getMessage());
			}
			throw new CSTransactionException(
					"An error occured in deassigning Protection Element from Protection Group\n"
							+ ex.getMessage(), ex);
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||deAssignProtectionElements|Success|Successful in deassigning Protection Group "
							+ protectionGroupName
							+ " and Protection Element "
							+ protectionElementObjectId + "|");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {

		ApplicationContext applicationContext = this.getApplication();

		return applicationContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getObjects(gov.nih.nci.security.dao.SearchCriteria)
	 */
	public List getObjects(SearchCriteria searchCriteria) {
		Session s = null;
		List result = new ArrayList();
		try {

			s = sf.openSession();
			Criteria criteria = s
					.createCriteria(searchCriteria.getObjectType());
			Hashtable fieldValues = searchCriteria.getFieldAndValues();
			Enumeration en = fieldValues.keys();
			while (en.hasMoreElements()) {
				String str = (String) en.nextElement();
				String fieldValue = (String) fieldValues.get(str);
				String fieldValue_ = StringUtilities.replaceInString(
						fieldValue, "*", "%");
				//int i = ((String) fieldValues.get(str)).indexOf("%");
				int i = fieldValue_.indexOf("%");
				if (i != -1) {
					//criteria.add(Expression.like(str, fieldValues.get(str)));
					criteria.add(Expression.like(str, fieldValue_));
				} else {
					//criteria.add(Expression.eq(str, fieldValues.get(str)));
					criteria.add(Expression.eq(str, fieldValue_));
				}
			}
			if (fieldValues.size() == 0) {
				criteria.add(Expression.eqProperty("1", "1"));
			}
			System.out.println("Message from debug: ObjectType="+searchCriteria.getObjectType().getName());
			
			//boolean t = searchCriteria.getObjectType().getName().equalsIgnoreCase("gov.nih.nci.security.authorization.domainobjects.User")||searchCriteria.getObjectType().getName().equalsIgnoreCase("gov.nih.nci.security.authorization.domainobjects.Privilege");
			
			//System.out.println("Test:"+t);
			
			//if(!t){
			//	criteria.add(Expression.eq("application", this.application));
			//}
			
			if (!(searchCriteria.getObjectType().getName().equalsIgnoreCase("gov.nih.nci.security.authorization.domainobjects.User")
					||searchCriteria.getObjectType().getName().equalsIgnoreCase("gov.nih.nci.security.authorization.domainobjects.Privilege"))) {
				criteria.add(Expression.eq("application", this.application));
			}
			

			result = criteria.list();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getObjects|Failure|Error in Obtaining Search Objects from Database |"
								+ ex.getMessage());
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getObjects|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getObjects|Success|Successful in Searching objects from the database |");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrincipals(java.lang.String)
	 */
	public Principal[] getPrincipals(String userName) {
		ArrayList al = new ArrayList();
		Set groups = new HashSet();
		Principal[] ps = null;
		if (StringUtilities.isBlank(userName)) {
			return null;
		}

		try {
			User user = this.getUser(userName);
			if (user == null) {
				return null;
			}
			al.add((Principal) user);
			groups = this.getGroups(user.getUserId().toString());
			Iterator it = groups.iterator();
			while (it.hasNext()) {
				Group grp = (Group) it.next();
				al.add((Principal) grp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//TypeuWant[] a = (TypeuWant [] ) arraylist.toArray(new
		// TypeUWant[arraylist.size()])
		ps = (Principal[]) al.toArray(new Principal[al.size()]);

		return ps;
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
		if (StringUtilities.isBlank(objectId)) {
			throw new CSObjectNotFoundException(
					"The protection element can't be searched with null objectId");
		}
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionElement|Failure|Protection Element not found for object id "
									+ objectId
									+ " and attribute "
									+ attribute
									+ "|");
				throw new CSObjectNotFoundException(
						"Protection Element not found with these attributes");
			}
			pe = (ProtectionElement) list.get(0);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getProtectionElement|Failure|Error in obtaining Protection Element for object id "
								+ objectId
								+ " and attribute "
								+ attribute
								+ "|");
			throw new CSObjectNotFoundException(
					"Protection Element is not found with object id= "
							+ objectId + " and attributeName= " + attribute);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionElement|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionElement|Success|Successful in obtaining Protection Element for object id "
							+ objectId + " and attribute " + attribute + "|");
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
		Session s = null;
		ProtectionGroup pgrp = null;
		if (StringUtilities.isBlank(protectionGroupName)) {
			throw new CSObjectNotFoundException(
					"The protection group can't searched with null name");
		}
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionGroup|Failure|Protection Group not found for name "
									+ protectionGroupName + "|");
				throw new CSObjectNotFoundException(
						"Protection Group not found");
			}
			pgrp = (ProtectionGroup) list.get(0);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log
						.debug("Authorization|||getProtectionGroup|Failure|Protection Group not found for name "
								+ protectionGroupName + "|" + ex.getMessage());
			}
			throw new CSObjectNotFoundException(
					"Protection Group not found for name "
							+ protectionGroupName);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionGroup|Success|Protection Group found for name "
							+ protectionGroupName + "|");
		return pgrp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getRole(java.lang.String)
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException {
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getRole|Failure|Role not found for name "
									+ roleName + "|");
				throw new CSObjectNotFoundException("Role not found");
			}
			role = (Role) list.get(0);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getRole|Failure|Error in obtaining the Role for name "
								+ roleName + "|" + ex.getMessage());
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getRole|Success|Successful in obtaining the Role for name "
							+ roleName + "|");
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

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getUser|Failure|Error Occured in Getting User for Name "
								+ loginName + "|" + ex.getMessage());

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getUser|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getUser|Success|Success in Getting User for Name "
							+ loginName + "|");
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeGroupFromProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error Occured in deassigning Group "
								+ groupId
								+ " and Protection Group"
								+ protectionGroupId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in deassigning Group and Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {

				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeGroupFromProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignGroupRoleToProtectionGroup|Success|Success in deassigning Group "
							+ groupId
							+ " and Protection Group"
							+ protectionGroupId + "|");
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeGroupRoleFromProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||removeGroupRoleFromProtectionGroup|Failure|Error Occured in assigning Roles "
								+ StringUtilities.stringArrayToString(rolesId)
								+ " to Group "
								+ groupId
								+ " and Protection Group"
								+ protectionGroupId
								+ "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in assigning Roles and Protection Group to a Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||removeGroupRoleFromProtectionGroup|Success|Successful in assigning Roles "
							+ StringUtilities.stringArrayToString(rolesId)
							+ " to Group "
							+ groupId
							+ " and Protection Group"
							+ protectionGroupId + "|");
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
		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserFromGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||removeUserFromGroup|Failure|Error Occured in deassigning User "
								+ userId
								+ " from Group "
								+ groupId
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in deassigning User from a Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserFromGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||removeUserFromGroup|Success|Successful in deassigning User "
							+ userId + " from Group " + groupId + "|");
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
			pstmt.close();
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserFromProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||removeUserFromProtectionGroup|Failure|Error Occured in deassigning User "
								+ userId
								+ " from Protection Group "
								+ protectionGroupId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in deassigning User from Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {

				s.close();

			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserFromProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||removeUserFromProtectionGroup|Success|Successful in deassigning User "
							+ userId
							+ " from Protection Group "
							+ protectionGroupId + "|");
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserRoleFromProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||removeUserRoleFromProtectionGroup|Failure|Error Occured in deassigning Roles "
								+ StringUtilities.stringArrayToString(rolesId)
								+ " and Protection Group "
								+ protectionGroupId
								+ " for user " + userId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in deassigning Roles and Protection Group for the User\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeUserRoleFromProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||removeUserRoleFromProtectionGroup|Success|Successful in deassigning Roles "
							+ StringUtilities.stringArrayToString(rolesId)
							+ " and Protection Group "
							+ protectionGroupId
							+ " for user " + userId + "|");
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

			s = sf.openSession();
			List list = s.createCriteria(User.class)
					.add(Example.create(search)).list();

			if (list.size() != 0) {
				user = (User) list.get(0);
			}

		} catch (Exception ex) {
			log.fatal("Unable to find Group\n" + ex.getMessage(), ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
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
			String protectionElementObjectId,
			String protectionElementAttributeName)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		if (StringUtilities.isBlank(loginName)) {
			throw new CSTransactionException("login name can't be null");
		}

		try {

			s = sf.openSession();
			t = s.beginTransaction();

			User user = getLightWeightUser(loginName);
			if (user == null) {
				throw new CSTransactionException(
						"No user found for this login name");
			}
			ProtectionElement pe = getProtectionElement(
					protectionElementObjectId, protectionElementAttributeName);

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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwnerForProtectionElement|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||setOwnerForProtectionElement|Failure|Error Setting owner for Protection Element object Name"
								+ protectionElementObjectId
								+ " and Attribute Id "
								+ protectionElementAttributeName
								+ " for user "
								+ loginName + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in setting owner for the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwnerForProtectionElement|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||setOwnerForProtectionElement|Success|Success in Setting owner for Protection Element object Name"
							+ protectionElementObjectId
							+ " and Attribute Id "
							+ protectionElementAttributeName
							+ " for user "
							+ loginName + "|");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String,
	 *      java.lang.String)
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectId,
			String[] userNames) throws CSTransactionException {

		Session s = null;
		Transaction t = null;
		if (StringUtilities.isBlank(protectionElementObjectId)) {
			throw new CSTransactionException("object Id can't be null!");
		}
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			Set users = new HashSet();

			for (int i = 0; i < userNames.length; i++) {
				User user = this.getUser(userNames[i]);
				if (user != null) {
					users.add(user);
				}
			}
			ProtectionElement pe = new ProtectionElement();
			pe.setObjectId(protectionElementObjectId);
			SearchCriteria sc = new ProtectionElementSearchCriteria(pe);
			List l = this.getObjects(sc);

			ProtectionElement protectionElement = (ProtectionElement) l.get(0);

			protectionElement.setOwners(users);

			s.update(protectionElement);
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwnerForProtectionElement|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||setOwnerForProtectionElement|Failure|Error Setting owner for Protection Element object Name"
								+ protectionElementObjectId
								+ " for users "
								+ StringUtilities
										.stringArrayToString(userNames)
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in setting multiple owners for the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwnerForProtectionElement|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||setOwnerForProtectionElement|Success|Successful in Setting owner for Protection Element object Name"
							+ protectionElementObjectId
							+ " for users "
							+ StringUtilities.stringArrayToString(userNames)
							+ "|");
	}

	public Set getPrivileges(String roleId) throws CSObjectNotFoundException {
		Session s = null;
		Set result = new HashSet();
		try {
			s = sf.openSession();
			Role role = (Role) this.getObjectByPrimaryKey(s, Role.class,
					new Long(roleId));
			result = role.getPrivileges();
			log.debug("The result size is: " + result.size());
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getPrivileges|Failure|Error obtaining Associated Privileges for Role id "
								+ roleId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occured in obtaining associated Privileges for the given Role\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignGroupRoleToProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getPrivileges|Success|Successful in obtaining Associated Privileges for Role id "
							+ roleId + "|");
		return result;
	}

	/**
	 * public void createUser(User user) throws CSTransactionException { Session
	 * s = null; Transaction t = null; try { s = sf.openSession(); t =
	 * s.beginTransaction(); user.setUpdateDate(new Date()); s.save(user);
	 * t.commit(); log.debug("User ID is: " + user.getUserId()); } catch
	 * (Exception ex) { log.error(ex); try { t.rollback(); } catch (Exception
	 * ex3) { } throw new CSTransactionException("Could not create the user",
	 * ex); } finally { try { s.close(); } catch { } } }
	 */

	public void assignProtectionElements(String protectionGroupId,
			String[] protectionElementIds) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		Set pes = new HashSet();
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionGroup protectionGroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));

			for (int i = 0; i < protectionElementIds.length; i++) {

				ProtectionElement protectionElement = (ProtectionElement) this
						.getObjectByPrimaryKey(ProtectionElement.class,
								protectionElementIds[i]);

				pes.add(protectionElement);

			}
			protectionGroup.setProtectionElements(pes);
			s.update(protectionGroup);
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignProtectionElements|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignProtectionElements|Failure|Error Occured in assigning Protection Elements "
								+ StringUtilities
										.stringArrayToString(protectionElementIds)
								+ " to Protection Group"
								+ protectionGroupId
								+ "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occured in assigning Protection Elements to the Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignProtectionElements|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignProtectionElements|Success|Successful in assigning Protection Elements "
							+ StringUtilities
									.stringArrayToString(protectionElementIds)
							+ " to Protection Group" + protectionGroupId + "|");
	}

	public void removeProtectionElementsFromProtectionGroup(
			String protectionGroupId, String[] protectionElementIds)
			throws CSTransactionException {
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();

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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeProtectionElementsFromProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			log
					.debug("Authorization|||removeProtectionElementsFromProtectionGroup|Failure|Error Occured in deassigning Protection Elements "
							+ StringUtilities
									.stringArrayToString(protectionElementIds)
							+ " to Protection Group"
							+ protectionGroupId
							+ "|"
							+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in deassigning Protection Elements from Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeProtectionElementsFromProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		log
				.debug("Authorization|||removeProtectionElementsFromProtectionGroup|Success|Success in deassigning Protection Elements "
						+ StringUtilities
								.stringArrayToString(protectionElementIds)
						+ " to Protection Group" + protectionGroupId + "|");
	}

	private Object getObjectByPrimaryKey(Session s, Class objectType,
			Long primaryKey) throws HibernateException,
			CSObjectNotFoundException {

		if (primaryKey == null) {
			throw new CSObjectNotFoundException("The primary key can't be null");
		}
		Object obj = s.load(objectType, primaryKey);

		if (obj == null) {
			log
					.debug("Authorization|||getObjectByPrimaryKey|Failure|Not found object of type "
							+ objectType.getName() + "|");
			throw new CSObjectNotFoundException(objectType.getName()
					+ " not found");
		}
		log
				.debug("Authorization|||getObjectByPrimaryKey|Success|Success in retrieving object of type "
						+ objectType.getName() + "|");
		return obj;
	}

	public Object getObjectByPrimaryKey(Class objectType, String primaryKey)
			throws CSObjectNotFoundException {
		Object oj = null;

		Session s = null;
		if (StringUtilities.isBlank(primaryKey)) {
			throw new CSObjectNotFoundException("The primary key can't be null");
		}
		try {

			s = sf.openSession();
			oj = getObjectByPrimaryKey(s, objectType, new Long(primaryKey));

		} catch (Exception ex) {
			log
					.debug("Authorization|||getObjectByPrimaryKey|Failure|Error in retrieving object of type "
							+ objectType.getName() + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(objectType.getName()
					+ " not found\n" + ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getObjectByPrimaryKey|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		log
				.debug("Authorization|||getObjectByPrimaryKey|Success|Success in retrieving object of type "
						+ objectType.getName() + "|");
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeObject|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||removeObject|Failure|Error in removing object of type "
								+ oj.getClass().getName()
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in removing object of type "
							+ oj.getClass().getName() + "\n" + ex.getMessage(),
					ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||removeObject|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||removeObject|Success|Success in removing object of type "
							+ oj.getClass().getName() + "|");
	}

	private Application getApplicationByName(String contextName)
			throws CSObjectNotFoundException {
		Session s = null;
		Application app = null;
		try {
			Application search = new Application();
			search.setApplicationName(contextName);
			s = sf.openSession();
			List list = s.createCriteria(Application.class).add(
					Example.create(search)).list();
			if (list.size() == 0) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|"
									+ contextName
									+ "||getApplicationByName|Failure|No Application Found for the Context Name "
									+ contextName + "|");
				throw new CSObjectNotFoundException(
						"No Application Found for the given Context Name");
			}
			app = (Application) list.get(0);
			log.debug("Found the Application");

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|"
								+ contextName
								+ "||getApplicationByName|Failure|Error in obtaining application "
								+ contextName + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occured in retrieving Application for the given Context Name\n"
							+ ex.getMessage(), ex);

		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getApplicationByName|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|"
							+ contextName
							+ "||getApplicationByName|Success|Application Found for the Context Name "
							+ contextName + "|");
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
	public Set getProtectionGroupRoleContextForUser(String userId)
			throws CSObjectNotFoundException {
		Set result = new HashSet();
		Session s = null;

		Connection cn = null;

		try {
			ArrayList pgIds = new ArrayList();
			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("SELECT distinct protection_group_id ");
			stbr.append("FROM user_group_role_protection_group ");
			stbr.append("where user_id = " + userId);

			String sql = stbr.toString();
			Statement stmt = cn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String pg_id = rs.getString(1);
				pgIds.add(pg_id);
			}
			rs.close();
			stmt.close();

			User user = (User) this.getObjectByPrimaryKey(User.class, userId);
			for (int i = 0; i < pgIds.size(); i++) {

				ProtectionGroup pg = (ProtectionGroup) this
						.getObjectByPrimaryKey(ProtectionGroup.class, pgIds
								.get(i).toString());
				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("user", user));
				criteria.add(Expression.eq("protectionGroup", pg));
				List list = criteria.list();

				Iterator it = list.iterator();
				Set roles = new HashSet();
				while (it.hasNext()) {
					UserGroupRoleProtectionGroup ugrpg = (UserGroupRoleProtectionGroup) it
							.next();
					roles.add(ugrpg.getRole());
				}

				ProtectionGroupRoleContext pgrc = new ProtectionGroupRoleContext();
				pgrc.setProtectionGroup(pg);
				pgrc.setRoles(roles);
				result.add(pgrc);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getProtectionGroupRoleContextForUser|Failure|Error in obtaining the Protection Group - Role Context for the User Id "
								+ userId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occured in obtaining the Protection Group - Role Context for the User\n"
							+ ex.getMessage(), ex);
		}

		finally {
			try {

				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionGroupRoleContextForUser|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionGroupRoleContextForUser|Success|Successful in obtaining the Protection Group - Role Context for the User Id "
							+ userId + "|");
		return result;
	}

	public Set getProtectionGroupRoleContextForGroup(String groupId)
			throws CSObjectNotFoundException {
		Set result = new HashSet();
		Session s = null;

		Connection cn = null;

		try {
			ArrayList pgIds = new ArrayList();
			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("SELECT distinct protection_group_id ");
			stbr.append("FROM user_group_role_protection_group ");
			stbr.append("where group_id = " + groupId);

			String sql = stbr.toString();
			Statement stmt = cn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String pg_id = rs.getString(1);
				pgIds.add(pg_id);
			}
			rs.close();
			stmt.close();

			Group group = (Group) this.getObjectByPrimaryKey(Group.class,
					groupId);
			for (int i = 0; i < pgIds.size(); i++) {

				ProtectionGroup pg = (ProtectionGroup) this
						.getObjectByPrimaryKey(ProtectionGroup.class, pgIds
								.get(i).toString());
				Criteria criteria = s
						.createCriteria(UserGroupRoleProtectionGroup.class);
				criteria.add(Expression.eq("group", group));
				criteria.add(Expression.eq("protectionGroup", pg));
				List list = criteria.list();

				Iterator it = list.iterator();
				Set roles = new HashSet();
				while (it.hasNext()) {
					UserGroupRoleProtectionGroup ugrpg = (UserGroupRoleProtectionGroup) it
							.next();
					roles.add(ugrpg.getRole());
				}

				ProtectionGroupRoleContext pgrc = new ProtectionGroupRoleContext();
				pgrc.setProtectionGroup(pg);
				pgrc.setRoles(roles);
				result.add(pgrc);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getProtectionGroupRoleContextForUser|Failure|Error in obtaining the Protection Group - Role Context for the Group Id "
								+ groupId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occured in obtaining the Protection Group - Role Context for the Group\n"
							+ ex.getMessage(), ex);
		}

		finally {
			try {

				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionGroupRoleContextForUser|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionGroupRoleContextForUser|Success|Successful in obtaining the Protection Group - Role Context for the Group Id "
							+ groupId + "|");
		return result;
	}

	public void modifyObject(Object obj) throws CSTransactionException {
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();

			s.update(obj);
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||modifyObject|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||modifyObject|Failure|Error in modifying the "
								+ obj.getClass().getName()
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in modifying the "
							+ StringUtilities.getClassName(obj.getClass()
									.getName()) + "\n" + ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||modifyObject|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||modifyObject|Success|Successful in modifying the "
							+ obj.getClass().getName() + "|");

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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||createObject|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||createObject|Failure|Error in creating the "
								+ obj.getClass().getName()
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occured in creating the "
							+ StringUtilities.getClassName(obj.getClass()
									.getName()) + "\n" + ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||createObject|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||createObject|Success|Successful in creating the "
							+ obj.getClass().getName() + "|");
	}

	/**
	 * @param application
	 *            The application to set.
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	public Set getGroups(String userId) throws CSObjectNotFoundException {
		Session s = null;
		Set groups = new HashSet();
		try {
			s = sf.openSession();

			User user = (User) this.getObjectByPrimaryKey(s, User.class,
					new Long(userId));
			groups = user.getGroups();
			log.debug("The result size:" + groups.size());

		} catch (Exception ex) {
			log.error(ex);
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getGroups|Failure|Error in obtaining Groups for User Id "
								+ userId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occurred while obtaining Associated Groups for the User\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getGroups|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getGroups|Success|Successful in obtaining Groups for User Id "
							+ userId + "|");
		return groups;

	}

	public Set getProtectionElements(String protectionGroupId)
			throws CSObjectNotFoundException {
		Session s = null;
		Set result = new HashSet();
		try {
			s = sf.openSession();
			ProtectionGroup protectionGroup = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							protectionGroupId));
			result = protectionGroup.getProtectionElements();
			log.debug("The result size is: " + result.size());

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getProtectionElements|Failure|Error in obtaining Protection Elements for Protection Group Id "
								+ protectionGroupId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occurred while obtaining Associated Protection Elements for the Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionElements|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionElements|Success|Succesful in obtaining Protection Elements for Protection Group Id "
							+ protectionGroupId + "|");
		return result;
	}

	public Set getProtectionGroups(String protectionElementId)
			throws CSObjectNotFoundException {
		Session s = null;
		Set result = new HashSet();
		try {
			s = sf.openSession();
			if (StringUtilities.isBlank(protectionElementId)) {
				throw new CSObjectNotFoundException("Primary key can't be null");
			}
			ProtectionElement protectionElement = (ProtectionElement) this
					.getObjectByPrimaryKey(s, ProtectionElement.class,
							new Long(protectionElementId));
			result = protectionElement.getProtectionGroups();
			log.debug("The result size:" + result.size());

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getProtectionGroups|Failure|Error in obtaining Protection Groups for Protection Element Id "
								+ protectionElementId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occurred while obtaining Associated Protection Groups for the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getProtectionGroups|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getProtectionGroups|Success|Successful in obtaining Protection Groups for Protection Element Id "
							+ protectionElementId + "|");
		return result;
	}

	public void assignToProtectionGroups(String protectionElementId,
			String[] protectionGroupIds) throws CSTransactionException {
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();

			ProtectionElement protectionElement = (ProtectionElement) this
					.getObjectByPrimaryKey(s, ProtectionElement.class,
							new Long(protectionElementId));

			Set newSet = new HashSet();

			for (int k = 0; k < protectionGroupIds.length; k++) {
				log.debug("The new list:" + protectionGroupIds[k]);
				ProtectionGroup pg = (ProtectionGroup) this
						.getObjectByPrimaryKey(ProtectionGroup.class,
								protectionGroupIds[k]);
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
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignToProtectionGroups|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignToProtectionGroups|Failure|Error in assigning Protection Groups "
								+ StringUtilities
										.stringArrayToString(protectionGroupIds)
								+ " to protection element id "
								+ protectionElementId + "|" + ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Protection Groups to the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignToProtectionGroups|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignToProtectionGroups|Success|Successful in assigning Protection Groups Back Transaction"
							+ StringUtilities
									.stringArrayToString(protectionGroupIds)
							+ " to protection element id "
							+ protectionElementId + "|");
	}

	public void assignParentProtectionGroup(String parentProtectionGroupId,
			String childProtectionGroupId) throws CSTransactionException {
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();
			ProtectionGroup parent = null;

			ProtectionGroup child = (ProtectionGroup) this
					.getObjectByPrimaryKey(s, ProtectionGroup.class, new Long(
							childProtectionGroupId));

			if (parentProtectionGroupId != null) {
				parent = (ProtectionGroup) this.getObjectByPrimaryKey(s,
						ProtectionGroup.class,
						new Long(parentProtectionGroupId));
			} else {
				parent = null;
			}

			child.setParentProtectionGroup(parent);

			s.update(child);
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignParentProtectionGroup|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||assignParentProtectionGroup|Failure|Error in assigning Parent Protection Groups"
								+ parentProtectionGroupId
								+ " to protection group id "
								+ childProtectionGroupId
								+ "|"
								+ ex.getMessage());
			throw new CSTransactionException(
					"An error occurred in assigning Parent Protection Group to the Protection Group\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||assignParentProtectionGroup|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||assignParentProtectionGroup|Success|Successful in assigning Parent Protection Groups"
							+ parentProtectionGroupId
							+ " to protection group id "
							+ childProtectionGroupId + "|");
	}

	private ObjectAccessMap getObjectAccessMap(String objectTypeName,
			String loginName, String privilegeName) {
		Hashtable accessMap = new Hashtable();
		Session s = null;

		Connection cn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("select pe.attribute");
			stbr.append(" from protection_group pg,");
			stbr.append("protection_element pe,");
			stbr.append("protection_group_protection_element pgpe,");
			stbr.append("user_group_role_protection_group ugrpg,");
			stbr.append("user u,");
			stbr.append("role_privilege rp,");
			stbr.append("privilege p");
			stbr
					.append(" where pgpe.protection_group_id = pg.protection_group_id");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id='" + objectTypeName + "'");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id");
			stbr.append(" and ugrpg.user_id = u.user_id");
			stbr.append(" and u.login_name='" + loginName + "'");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");
			stbr.append(" and p.privilege_name='" + privilegeName + "'");
			String sql = stbr.toString();
			log.debug("SQL is : " + sql);
			stmt = cn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String att = rs.getString("attribute");
				log.debug("The attribute is: " + att);
				Boolean b = new Boolean(true);
				accessMap.put(att.toLowerCase(), b);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getObjectAccessMap|Failure|Error in Obtaining the Object Access Map|"
								+ ex.getMessage());
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception ex2) {
			}

			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getObjectAccessMap|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getObjectAccessMap|Success|Successful in Obtaining the Object Access Map|");
		return new ObjectAccessMap(objectTypeName, accessMap);
	}

	public Object secureObject(String userName, Object obj) throws CSException {
		Object o = null;
		if (StringUtilities.isBlank(userName)) {
			throw new CSException("No user name have been supplied!");
		}
		if (obj == null) {
			return obj;
		}
		try {

			Class cl = obj.getClass();
			log.debug(cl.getName());
			ObjectAccessMap accessMap = this.getObjectAccessMap(cl.getName(),
					userName, "READ");

			log.debug(accessMap.toString());

			o = cl.newInstance();
			Method methods[] = cl.getDeclaredMethods();

			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];

				String name = m.getName();
				//log.debug("Name from outer block"+name);
				//log.debug("Para type"+m.getParameterTypes());
				if (name.startsWith("set")
						&& (m.getModifiers() == Modifier.PUBLIC)) {
					String att = name.substring(3, name.length());
					String methodName = "get" + att;
					//log.debug(methodName);
					Method m2 = cl.getMethod(methodName, null);
					//log.debug("Method Name m2"+m2.getName());
					//log.debug(m2.invoke(obj,null));
					if (!accessMap.hasAccess(att)) {
						m.invoke(o, new Object[] { null });
					} else {
						m.invoke(o, new Object[] { m2.invoke(obj, null) });
					}
				}
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Authorization||" + userName
						+ "|secureObject|Failure|Error in Secure Object|"
						+ ex.getMessage());

			throw new CSException("Failed to secure the object:"
					+ ex.getMessage(), ex);
		}

		return o;

	}

	public Collection secureCollection(String userName, Collection collection)
			throws CSException {
		ArrayList result = new ArrayList();
		if (collection.size() == 0) {
			return collection;
		}
		if (StringUtilities.isBlank(userName)) {
			throw new CSException("No userName have been supplied!");
		}
		try {
			Iterator it = collection.iterator();
			List l = (List) collection;
			Object obj_ = (Object) l.get(0);

			Class cl = obj_.getClass();
			log.debug(cl.getName());
			ObjectAccessMap accessMap = this.getObjectAccessMap(cl.getName(),
					userName, "READ");
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				Object o = cl.newInstance();
				Method methods[] = cl.getDeclaredMethods();

				for (int i = 0; i < methods.length; i++) {
					Method m = methods[i];

					String name = m.getName();
					//log.debug("Name from outer block"+name);
					//log.debug("Para type"+m.getParameterTypes());
					if (name.startsWith("set")
							&& (m.getModifiers() == Modifier.PUBLIC)) {
						String att = name.substring(3, name.length());
						String methodName = "get" + att;
						//log.debug(methodName);
						Method m2 = cl.getMethod(methodName, null);
						//log.debug("Method Name m2"+m2.getName());
						//log.debug(m2.invoke(obj,null));
						if (!accessMap.hasAccess(att)) {
							m.invoke(o, new Object[] { null });
						} else {
							m.invoke(o, new Object[] { m2.invoke(obj, null) });
						}
					}
				}
				result.add(o);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization||"
								+ userName
								+ "|secureCollection|Failure|Error in Secure Collection|"
								+ ex.getMessage());

			throw new CSException("Failed to secure Collection:"
					+ ex.getMessage(), ex);
		}

		return result;

	}

	public Object secureUpdate(String userName, Object originalObject,
			Object mutatedObject) throws CSException {
		//Object o = null;
		if (StringUtilities.isBlank(userName)) {
			throw new CSException("No user name have been supplied!");
		}
		if (originalObject == null || mutatedObject == null) {
			return originalObject;
		}
		try {

			Class cl = originalObject.getClass();
			log.debug(cl.getName());
			ObjectAccessMap accessMap = this.getObjectAccessMap(cl.getName(),
					userName, "UPDATE_DENIED");

			//o = cl.newInstance();
			Method methods[] = cl.getDeclaredMethods();

			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];

				String name = m.getName();
				log.debug("Method is: " + name);
				//log.debug("Name from outer block"+name);
				//log.debug("Para type"+m.getParameterTypes());
				if (name.startsWith("set")
						&& (m.getModifiers() == Modifier.PUBLIC)) {
					String att = name.substring(3, name.length());
					log.debug("Attribute is: " + att);
					String methodName = "get" + att;
					//log.debug(methodName);
					Method m2 = cl.getMethod(methodName, null);
					//log.debug("Method Name m2"+m2.getName());
					//log.debug(m2.invoke(obj,null));
					if (accessMap.hasAccess(att)) {
						log.debug("No Access to update attribute: " + att);
						Object origValue = m2.invoke(originalObject, null);
						if (origValue != null) {
							log.debug("Original value is: "
									+ origValue.toString());
						}
						m.invoke(mutatedObject, new Object[] { origValue });
					} else {
						log.debug("Access permitted to update attribute: "
								+ att);
					}
				}
			}

		} catch (Exception ex) {
			log.error("Error Securing object", ex);
			if (log.isDebugEnabled())
				log.debug("Authorization||" + userName
						+ "|secureUpdate|Failure|Error in Secure Update|"
						+ ex.getMessage());

			throw new CSException("Failed to secure update the object:"
					+ ex.getMessage(), ex);
		}

		return mutatedObject;

	}

	public Set getOwners(String protectionElementId)
			throws CSObjectNotFoundException {

		Session s = null;

		Set result = new HashSet();
		try {
			s = sf.openSession();
			ProtectionElement protectionElement = (ProtectionElement) this
					.getObjectByPrimaryKey(s, ProtectionElement.class,
							new Long(protectionElementId));

			result = protectionElement.getOwners();
			log.debug("The result size is: " + result.size());

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||getOwners|Failure|An Error occured in retrieving the Owners for the Protection Element Id "
								+ protectionElementId + "|" + ex.getMessage());
			throw new CSObjectNotFoundException(
					"An error occured in retrieving the Owners for the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getOwners|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||getOwners|Success|Successful in retrieving the Owners for the Protection Element Id "
							+ protectionElementId + "|");
		return result;
	}

	public void assignOwners(String protectionElementId, String[] userIds)
			throws CSTransactionException {

		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();

			Set users = new HashSet();

			for (int i = 0; i < userIds.length; i++) {
				User user = (User) this.getObjectByPrimaryKey(User.class,
						userIds[i]);
				users.add(user);
			}
			ProtectionElement pe = (ProtectionElement) this
					.getObjectByPrimaryKey(ProtectionElement.class,
							protectionElementId);

			pe.setOwners(users);

			s.update(pe);
			t.commit();

		} catch (Exception ex) {
			log.error(ex);
			try {
				t.rollback();
			} catch (Exception ex3) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwners|Failure|Error in Rolling Back Transaction|"
									+ ex3.getMessage());
			}
			if (log.isDebugEnabled())
				log
						.debug("Authorization|||setOwners|Failure|Error in assigning the Owners "
								+ StringUtilities.stringArrayToString(userIds)
								+ "for the Protection Element Id "
								+ protectionElementId + "|");
			throw new CSTransactionException(
					"An error occured in assigning Owners to the Protection Element\n"
							+ ex.getMessage(), ex);
		} finally {
			try {
				s.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||setOwners|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization|||setOwners|Success|Successful in assigning the Owners "
							+ StringUtilities.stringArrayToString(userIds)
							+ "for the Protection Element Id "
							+ protectionElementId + "|");
	}

	public boolean checkOwnership(String userName,
			String protectionElementObjectId) {
		boolean test = false;
		Session s = null;
		Statement stmt = null;
		Connection cn = null;
		ResultSet rs = null;

		try {

			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr
					.append("Select  user_protection_element_id from"
							+ " user_protection_element upe, user u, protection_element pe"
							+ " where pe.object_id = '"
							+ protectionElementObjectId
							+ "' and u.login_name ='" + userName
							+ "' and upe.protection_element_id=pe.protection_element_id"
							+ " and upe.user_id = u.user_id");

			String sql = stbr.toString();
			stmt = cn.createStatement();
			log.debug("The owner sql: " + sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				test = true;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log
						.debug("Authorization||"
								+ userName
								+ "|checkOwnerShip|Failure|Error in checking ownership for user "
								+ userName + " and Protection Element "
								+ protectionElementObjectId + "|"
								+ ex.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();

			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||checkOwnerShip|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}

			try {
				s.close();
			} catch (Exception ex) {
			}
		}
		if (log.isDebugEnabled())
			log
					.debug("Authorization||"
							+ userName
							+ "|checkOwnerShip|Success|Successful in checking ownership for user "
							+ userName + " and Protection Element "
							+ protectionElementObjectId + "|");
		return test;
	}

	public Collection getPrivilegeMap(String userName, Collection pEs)
			throws CSException {
		ArrayList result = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean test = false;
		Session s = null;

		Connection cn = null;

		if (StringUtilities.isBlank(userName)) {
			throw new CSException("userName can't be null!");
		}
		if (pEs == null) {
			throw new CSException(
					"protection elements collection can't be null!");
		}
		if (pEs.size() == 0) {
			return result;
		}

		try {

			s = sf.openSession();

			cn = s.connection();

			StringBuffer stbr = new StringBuffer();
			stbr.append("select distinct(p.privilege_name)");
			stbr.append(" from protection_group pg,");
			stbr.append(" protection_element pe,");
			stbr.append(" protection_group_protection_element pgpe,");
			stbr.append(" user_group_role_protection_group ugrpg,");
			stbr.append(" user u,");
			stbr.append(" groups g,");
			stbr.append(" user_group ug,");
			stbr.append(" role_privilege rp,");
			stbr.append(" privilege p ");
			stbr
					.append(" where pgpe.protection_group_id = pg.protection_group_id");
			stbr
					.append(" and pgpe.protection_element_id = pe.protection_element_id");
			stbr.append(" and pe.object_id= ?");
			stbr.append(" and pe.attribute=?");
			stbr
					.append(" and pg.protection_group_id = ugrpg.protection_group_id ");
			stbr.append(" and (( ugrpg.group_id = g.group_id");
			stbr.append("       and ug.user_id = u.user_id)");
			stbr.append("       or ");
			stbr.append("     (ugrpg.user_id = u.user_id))");
			stbr.append(" and u.login_name=?");
			stbr.append(" and ugrpg.role_id = rp.role_id ");
			stbr.append(" and rp.privilege_id = p.privilege_id");

			String sql = stbr.toString();
			pstmt = cn.prepareStatement(sql);

			Iterator it = pEs.iterator();
			while (it.hasNext()) {
				ProtectionElement pe = (ProtectionElement) it.next();
				ArrayList privs = new ArrayList();
				if (pe.getObjectId() != null) {
					pstmt.setString(1, pe.getObjectId());
					if (pe.getAttribute() != null) {
						pstmt.setString(2, pe.getAttribute());
					} else {
						pstmt.setNull(2, Types.LONGVARCHAR);
					}
					pstmt.setString(3, userName);
				}

				rs = pstmt.executeQuery();

				while (rs.next()) {
					String priv = rs.getString(1);
					Privilege p = new Privilege();
					p.setName(priv);
					privs.add(p);
				}
				rs.close();
				ObjectPrivilegeMap opm = new ObjectPrivilegeMap(pe, privs);
				result.add(opm);
			}

			pstmt.close();

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Failed to get privileges for " + userName + "|"
						+ ex.getMessage());
			throw new CSException("Failed to get privileges for " + userName
					+ "|" + ex.getMessage(), ex);
		} finally {
			try {

				s.close();
				rs.close();
				pstmt.close();
			} catch (Exception ex2) {
				if (log.isDebugEnabled())
					log
							.debug("Authorization|||getPrivilegeMap|Failure|Error in Closing Session |"
									+ ex2.getMessage());
			}
		}

		return result;
	}
}