/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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
 * Tag for dynamically displaying the server names by performing a select
 * distinct on the database.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */
public class ServerListOptionTag extends OptionsTag
{

	private static final String OPTIONS_KEY = "options";
	private static final String PROPERTY_VALUE = "hostName";
	private static final String LABEL_VALUE = "hostValue";

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
		l.add(new ServerOption("", "All Servers"));
		try
		{
			conn = JdbcConnectionHandler.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT SERVER FROM LOGTAB");
			while (rs.next())
			{
				String host = rs.getString(1);
				l.add(new ServerOption(host, host));
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

	public class ServerOption
	{
		private String hostName;
		private String hostValue;

		public ServerOption(String _hostName, String _hostValue)
		{
			setHostName(_hostName);
			setHostValue(_hostValue);
		}

		/**
		 * @return Returns the hostName.
		 */
		public String getHostName()
		{
			return hostName;
		}

		/**
		 * @param hostName
		 *            The hostName to set.
		 */
		public void setHostName(String hostName)
		{
			this.hostName = hostName;
		}

		/**
		 * @return Returns the hostValue.
		 */
		public String getHostValue()
		{
			return hostValue;
		}

		/**
		 * @param hostValue
		 *            The hostValue to set.
		 */
		public void setHostValue(String hostValue)
		{
			this.hostValue = hostValue;
		}
	}
}
