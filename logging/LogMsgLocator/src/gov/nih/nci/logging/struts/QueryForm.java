package gov.nih.nci.logging.struts;

import org.apache.struts.validator.ValidatorForm;


/**
 * Provides a data transport bean from the jsp page
 * to the Struts Action.
 * @author Brian Husted
 */
public class QueryForm extends ValidatorForm {

       private String logLevel;
       private String application;
       private String server;
       private String startDate;
       private String startTime;
       private String endDate;
       private String endTime;
       private String msgBody;
       private String maxRecordCount;
       private String isXmlView;
       private String ndc;
       private String threadName;
       private String throwable;

    public QueryForm(){}


   
	/**
	 * @return Returns the application.
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * @param application The application to set.
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return Returns the isXmlView.
	 */
	public String getIsXmlView() {
		return isXmlView;
	}
	/**
	 * @param isXmlView The isXmlView to set.
	 */
	public void setIsXmlView(String isXmlView) {
		this.isXmlView = isXmlView;
	}
	/**
	 * @return Returns the logLevel.
	 */
	public String getLogLevel() {
		return logLevel;
	}
	/**
	 * @param logLevel The logLevel to set.
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	/**
	 * @return Returns the maxRecordCount.
	 */
	public String getMaxRecordCount() {
		return maxRecordCount;
	}
	/**
	 * @param maxRecordCount The maxRecordCount to set.
	 */
	public void setMaxRecordCount(String maxRecordCount) {
		this.maxRecordCount = maxRecordCount;
	}
	/**
	 * @return Returns the msgBody.
	 */
	public String getMsgBody() {
		return msgBody;
	}
	/**
	 * @param msgBody The msgBody to set.
	 */
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	/**
	 * @return Returns the ndc.
	 */
	public String getNdc() {
		return ndc;
	}
	/**
	 * @param ndc The ndc to set.
	 */
	public void setNdc(String ndc) {
		this.ndc = ndc;
	}
	/**
	 * @return Returns the server.
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server The server to set.
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return Returns the threadName.
	 */
	public String getThreadName() {
		return threadName;
	}
	/**
	 * @param threadName The threadName to set.
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	/**
	 * @return Returns the throwable.
	 */
	public String getThrowable() {
		return throwable;
	}
	/**
	 * @param throwable The throwable to set.
	 */
	public void setThrowable(String throwable) {
		this.throwable = throwable;
	}
}
