/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Project;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import org.apache.log4j.Logger;

/**
 * DAO Class for the Project entity.
 * 
 * @author Brian Husted
 *  
 */
public class ProjectDAO extends SecurityRIDAO {

	static final Logger log = Logger.getLogger(ProjectDAO.class.getName());

	public static void saveProject(Project empl) throws HibernateException {
		saveObject(empl);
	}

	public static void removeProject(Project empl) throws HibernateException {
		deleteObject(empl);
	}

	public static List searchProject(Project project) throws HibernateException {

		Session s = null;

		try {

			s = getSessionFactory().openSession();
			Criteria criteria = s.createCriteria(Project.class);

			if (project.getName() != null
					&& project.getName().trim().length() > 0) {
				criteria.add(Expression.ilike("name", project.getName() + "%"));
			}

			List l = criteria.list();
			log
					.debug("The Project search returned " + l.size()
							+ " employees.");
			return l;

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}

	}

	public static Project searchProjectByPrimaryKey(Long id)
			throws HibernateException {
		return (Project) searchObjectByPrimaryKey(Project.class, id);
	}

}