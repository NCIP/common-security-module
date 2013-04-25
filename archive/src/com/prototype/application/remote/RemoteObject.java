/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.application.remote;

import java.io.Serializable;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoteObject implements Serializable {
	
	private Object businessObject;
	private String sessionKey;
	
	public void setBusinessObject(Object val){
		businessObject = val;
	}
	public Object getBusinessObject(){
		return businessObject;
	}
	
	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}
	public String getSessionKey(){
		return sessionKey;
	}

}
