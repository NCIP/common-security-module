/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import gov.nih.nci.logging.utils.*;

/**
 * A custom Apache Log4J Appender will be responsible for formatting and
 * inserting Log4J messages into the configurable RDBMS.
 * 
 * Features include: --Inserts the SQL statements into the database in near real
 * time --Uses a configurable buffer to perform batch processing --Spawns
 * threads to execute the batch inserts to maximize performance --Prepares all
 * data for RDBMS by escaping quotes
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JDBCAppender extends AppenderSkeleton implements Constants
{

	private String application = null;
	private static String server = null;
	private String dbUrl = null;
	private String dbDriverClass = null;
	private String dbUser = null;
	private String dbPwd = null;
	private String useFilter = null;
	private int recordCtr = 0;
	private int maxBufferSize = 0;
	private List buff = new ArrayList();

	static
	{
		// set the server by looking up the host name from the system
		try
		{
			setServer(InetAddress.getLocalHost().getHostName());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public JDBCAppender()
	{
	}

	/**
	 * @param layout
	 */
	public JDBCAppender(Layout layout)
	{
		super();
		setLayout(layout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.log4j.Appender#setLayout(org.apache.log4j.Layout)
	 */
	public void setLayout(Layout layout)
	{
		super.setLayout(layout);
	}

	private String clean(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return "";
		}
		if (str.indexOf("'") < 0 && str.indexOf(",") < 0)
		{
			return str;
		}
		str = replace(str, "'", "''");
		return replace(str, ",", " ");

	}

	protected String replace(String source, String find, String replacement)
	{
		int i = 0;
		int j;
		int k = find.length();
		int m = replacement.length();

		while (i < source.length())
		{
			j = source.indexOf(find, i);

			if (j == -1)
			{
				break;
			}

			source = replace(source, j, j + k, replacement);

			i = j + m;
		}

		return source;
	}

	protected String replace(String source, int start, int end, String replacement)
	{
		if (start == 0)
		{
			source = replacement + source.substring(end);
		}
		else if (end == source.length())
		{
			source = source.substring(0, start) + replacement;
		}
		else
		{
			source = source.substring(0, start) + replacement + source.substring(end);
		}

		return source;
	}

	public void append(LoggingEvent le)
	{

		if (useFilter() && JDBCAppenderFilter.isMatch(le))
		{
			// The event was filtered out
			return;
		}
		//
		UserInfo user = (UserInfo) ThreadVariable.get();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO LOGTAB (");
		sql.append(APPLICATION);
		sql.append(",");
		sql.append(SERVER);
		sql.append(",");
		sql.append(PRIORITY);
		sql.append(",");
		sql.append(CATEGORY);
		sql.append(",");
		sql.append(THREAD);
		sql.append(",");
		sql.append(USER);
		sql.append(",");
		sql.append(SESSIONID);
		sql.append(",");
		sql.append(MSG);
		sql.append(",");
		sql.append(THROWABLE);
		sql.append(",");
		sql.append(NDC);
		sql.append(",");
		sql.append(CREATED_ON);
		sql.append(") VALUES ('");
		sql.append(clean(getApplication()));
		sql.append("','");
		sql.append(clean(getServer()));
		sql.append("','");
		String level = "";
		if (le.getLevel() != null)
		{
			level = le.getLevel().toString();
		}
		sql.append(clean(level));
		sql.append("','");
		sql.append(clean(le.getLoggerName()));
		sql.append("','");
		sql.append(clean(le.getThreadName()));
		sql.append("','");
		sql.append(clean(user.getUsername()));
		sql.append("','");
		sql.append(clean(user.getSessionID()));
		sql.append("','");
		String msg = "";
		if (le.getMessage() != null)
		{
			msg = le.getMessage().toString();
		}
		// sql.append( clean( msg ) ); sql.append("','");
		sql.append(clean(msg));
		sql.append("','");
		sql.append(clean(getThrowable(le)));
		sql.append("','");
		sql.append(clean(le.getNDC()));
		sql.append("',");
		sql.append(System.currentTimeMillis());
		sql.append(")");
		addRowToBuffer(sql.toString());

	}

	private void addRowToBuffer(String sql)
	{
		getBuff().add(sql);
		setRecordCtr(getRecordCtr() + 1);
		if (getRecordCtr() >= getMaxBufferSize())
		{
			execute();
		}

	}

	private boolean useFilter()
	{
		return Boolean.valueOf(getUseFilter()).booleanValue();
	}

	private void execute()
	{

		List rows = getBuff();
		setBuff(new ArrayList());
		setRecordCtr(0);
		JDBCExecutor exe = new JDBCExecutor(rows);
		exe.setDbDriverClass(getDbDriverClass());
		exe.setDbPwd(getDbPwd());
		exe.setDbUrl(getDbUrl());
		exe.setDbUser(getDbUser());

		// execute the batch insert
		new Thread(exe).start();

	}

	protected static String getThrowable(LoggingEvent le)
	{
		return Utils.getThrowable(le);
	}

	public boolean requiresLayout()
	{
		return true;
	}

	public void close()
	{
		//
	}

	public String getApplication()
	{
		return application;
	}

	public void setApplication(String value)
	{
		application = value;
	}

	/**
	 * @return Returns the server.
	 */
	public static String getServer()
	{
		return server;
	}

	/**
	 * @param server
	 * The server to set.
	 */
	public static void setServer(String server)
	{
		JDBCAppender.server = server;
	}

	/**
	 * @return Returns the buff.
	 */
	public List getBuff()
	{
		return buff;
	}

	/**
	 * @param buff
	 * The buff to set.
	 */
	public void setBuff(List buff)
	{
		this.buff = buff;
	}

	/**
	 * @return Returns the dbDriverClass.
	 */
	public String getDbDriverClass()
	{
		return dbDriverClass;
	}

	/**
	 * @param dbDriverClass
	 * The dbDriverClass to set.
	 */
	public void setDbDriverClass(String dbDriverClass)
	{
		this.dbDriverClass = dbDriverClass;
	}

	/**
	 * @return Returns the dbPwd.
	 */
	public String getDbPwd()
	{
		return dbPwd;
	}

	/**
	 * @param dbPwd
	 * The dbPwd to set.
	 */
	public void setDbPwd(String dbPwd)
	{
		this.dbPwd = dbPwd;
	}

	/**
	 * @return Returns the dbUrl.
	 */
	public String getDbUrl()
	{
		return dbUrl;
	}

	/**
	 * @param dbUrl
	 * The dbUrl to set.
	 */
	public void setDbUrl(String dbUrl)
	{
		this.dbUrl = dbUrl;
	}

	/**
	 * @return Returns the dbUser.
	 */
	public String getDbUser()
	{
		return dbUser;
	}

	/**
	 * @param dbUser
	 * The dbUser to set.
	 */
	public void setDbUser(String dbUser)
	{
		this.dbUser = dbUser;
	}

	/**
	 * @return Returns the maxBufferSize.
	 */
	public int getMaxBufferSize()
	{
		return maxBufferSize;
	}

	/**
	 * @param maxBufferSize
	 * The maxBufferSize to set.
	 */
	public void setMaxBufferSize(int maxBufferSize)
	{
		this.maxBufferSize = maxBufferSize;
	}

	/**
	 * @return Returns the recordCtr.
	 */
	public int getRecordCtr()
	{
		return recordCtr;
	}

	/**
	 * @param recordCtr
	 * The recordCtr to set.
	 */
	public void setRecordCtr(int recordCtr)
	{
		this.recordCtr = recordCtr;
	}

	/**
	 * @return Returns the useFilter.
	 */
	public String getUseFilter()
	{
		return useFilter;
	}

	/**
	 * @param useFilter
	 * The useFilter to set.
	 */
	public void setUseFilter(String useFilter)
	{
		this.useFilter = useFilter;
	}
}