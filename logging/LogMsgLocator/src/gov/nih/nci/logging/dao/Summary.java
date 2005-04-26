package gov.nih.nci.logging.dao;

import java.util.*;

/**
 * Bean class for storing log message attributes.
 * 
 * @author Brian Husted
 *  
 */
public class Summary {
	private String oid;

	private String priority;

	private String application;

	private String server;

	private String createdOn;

	private String thread;

	private String msgBody;

	private String ndc;

	private String throwable;

	private boolean viewDetailsInd;

	public static final String DISPLAY_DATE_FORMAT = "MM/dd/yy HH:mm:ssS";

	public static final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			DISPLAY_DATE_FORMAT);

	public Summary() {

	}

	public String getOid() {
		return oid;
	}

	public void setOid(String value) {
		oid = value;
	}

	public String getApplication() {
		if (application == null) {
			return "";
		}
		return application;
	}

	public void setApplication(String value) {
		application = value;
	}

	public String getServer() {
		if (server == null) {
			return "";
		}
		return server;
	}

	public void setServer(String value) {
		server = value;
	}

	public String getPriority() {
		if (priority == null) {
			return "";
		}
		return priority;
	}

	public void setPriority(String value) {
		priority = value;
	}

	public String getThread() {
		if (thread == null) {
			return "";
		}
		return thread;
	}

	public void setThread(String value) {
		thread = value;
	}

	public String getCreatedOn() {
		if (createdOn == null) {
			return "";
		}
		try {
			java.util.Date d = new java.util.Date();
			d.setTime(Long.parseLong(createdOn));

			return sdf.format(d);
		} catch (Exception ex) {
			return "";
		}
	}

	public void setCreatedOn(String value) {
		createdOn = value;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String value) {
		ndc = value;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String value) {
		msgBody = value;
	}

	public String getThrowable() {
		return throwable;
	}

	public void setThrowable(String value) {
		throwable = value;
	}

	public boolean getViewDetailsInd() {
		return viewDetailsInd;
	}

	public void setViewDetailsInd(boolean value) {
		viewDetailsInd = value;
	}
}

