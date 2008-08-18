//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl

package gov.nih.nci.security.migrate.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-18-2006
 * 
 * XDoclet definition:
 * @struts.form name="QueryForm"
 */
public class QueryForm extends ActionForm {

	

	
	private String logLevel;
	private String application;
	private String server;
	private String user;
	private String organization;
	private String sessionID;
	private String message;
	private String ndc;
	private String thread;
	private String objectID;
	private String objectName;
	private String operation;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String throwable;
	private String recordCount;
	private String loginId;
	private String password;
	
	private String activity;
	

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	
	/**
	 * @return Returns the application.
	 */
	public String getApplication()
	{
		return application;
	}

	
	/**
	 * @param application The application to set.
	 */
	public void setApplication(String application)
	{
		this.application = application;
	}

	
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate()
	{
		return endDate;
	}

	
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	
	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime()
	{
		return endTime;
	}

	
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	
	/**
	 * @return Returns the logLevel.
	 */
	public String getLogLevel()
	{
		return logLevel;
	}

	
	/**
	 * @param logLevel The logLevel to set.
	 */
	public void setLogLevel(String logLevel)
	{
		this.logLevel = logLevel;
	}

	
	/**
	 * @return Returns the message.
	 */
	public String getMessage()
	{
		return message;
	}

	
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	
	/**
	 * @return Returns the ndc.
	 */
	public String getNdc()
	{
		return ndc;
	}

	
	/**
	 * @param ndc The ndc to set.
	 */
	public void setNdc(String ndc)
	{
		this.ndc = ndc;
	}

	
	
	
	/**
	 * @return Returns the objectID.
	 */
	public String getObjectID()
	{
		return objectID;
	}

	
	/**
	 * @param objectID The objectID to set.
	 */
	public void setObjectID(String objectID)
	{
		this.objectID = objectID;
	}

	
	/**
	 * @return Returns the objectName.
	 */
	public String getObjectName()
	{
		return objectName;
	}

	
	/**
	 * @param objectName The objectName to set.
	 */
	public void setObjectName(String objectName)
	{
		this.objectName = objectName;
	}

	
	/**
	 * @return Returns the organization.
	 */
	public String getOrganization()
	{
		return organization;
	}

	
	/**
	 * @param organization The organization to set.
	 */
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	
	/**
	 * @return Returns the server.
	 */
	public String getServer()
	{
		return server;
	}

	
	/**
	 * @param server The server to set.
	 */
	public void setServer(String server)
	{
		this.server = server;
	}

	
	/**
	 * @return Returns the sessionID.
	 */
	public String getSessionID()
	{
		return sessionID;
	}

	
	/**
	 * @param sessionID The sessionID to set.
	 */
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}

	
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate()
	{
		return startDate;
	}

	
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	
	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime()
	{
		return startTime;
	}

	
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	
	/**
	 * @return Returns the thread.
	 */
	public String getThread()
	{
		return thread;
	}

	
	/**
	 * @param thread The thread to set.
	 */
	public void setThread(String thread)
	{
		this.thread = thread;
	}

	
	/**
	 * @return Returns the user.
	 */
	public String getUser()
	{
		return user;
	}

	
	/**
	 * @param user The user to set.
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	
	/**
	 * @return Returns the recordCount.
	 */
	public String getRecordCount()
	{
		return recordCount;
	}

	
	/**
	 * @param recordCount The recordCount to set.
	 */
	public void setRecordCount(String recordCount)
	{
		this.recordCount = recordCount;
	}

	
	/**
	 * @return Returns the operation.
	 */
	public String getOperation()
	{
		return operation;
	}

	
	/**
	 * @param operation The operation to set.
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	
	/**
	 * @return Returns the activity.
	 */
	public String getActivity()
	{
		return activity;
	}

	
	/**
	 * @param activity The activity to set.
	 */
	public void setActivity(String activity)
	{
		this.activity = activity;
	}

	
	/**
	 * @return Returns the throwable.
	 */
	public String getThrowable()
	{
		return throwable;
	}

	
	/**
	 * @param throwable The throwable to set.
	 */
	public void setThrowable(String throwable)
	{
		this.throwable = throwable;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}


