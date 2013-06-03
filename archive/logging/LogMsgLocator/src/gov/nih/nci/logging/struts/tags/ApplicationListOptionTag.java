/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.struts.tags;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import gov.nih.nci.logging.dao.JdbcConnectionHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import org.apache.struts.taglib.html.OptionsTag;

/**
 * Tag for dynamically displaying the application names by performing a select
 * distinct on the database.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */
public class ApplicationListOptionTag extends OptionsTag
{

	private static final String OPTIONS_KEY = "applications";
	private static final String PROPERTY_VALUE = "applicationName";
	private static final String LABEL_VALUE = "applicationValue";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException
	{
		prepareOptions();
		return super.doEndTag();
	}

	protected void prepareOptions()
	{
		storeOptions(retrieveOptions());
		configureProperties();
	}

	private void storeOptions(List options)
	{
		pageContext.setAttribute(OPTIONS_KEY, options);
	}

	private void configureProperties()
	{
		setCollection(OPTIONS_KEY);
		setProperty(PROPERTY_VALUE);
		setLabelProperty(LABEL_VALUE);

	}

	private List retrieveOptions()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List l = new ArrayList();
		l.add(new ApplicationOption("", "[Select Application]"));
		try
		{
			conn = JdbcConnectionHandler.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT APPLICATION FROM SIGNIN_USERS");
			while (rs.next())
			{
				String application = rs.getString(1);
				l.add(new ApplicationOption(application, application));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				stmt.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				conn.close();
			}
			catch (Exception e)
			{
			}
		}

		return l;

	}

	public class ApplicationOption
	{
		private String applicationName;
		private String applicationValue;

		public ApplicationOption(String _applicationName, String _applicationValue)
		{
			setapplicationName(_applicationName);
			setapplicationValue(_applicationValue);
		}

		/**
		 * @return Returns the applicationName.
		 */
		public String getapplicationName()
		{
			return applicationName;
		}

		/**
		 * @param applicationName
		 *            The applicationName to set.
		 */
		public void setapplicationName(String applicationName)
		{
			this.applicationName = applicationName;
		}

		/**
		 * @return Returns the applicationValue.
		 */
		public String getapplicationValue()
		{
			return applicationValue;
		}

		/**
		 * @param applicationValue
		 *            The applicationValue to set.
		 */
		public void setapplicationValue(String applicationValue)
		{
			this.applicationValue = applicationValue;
		}
	}

}
