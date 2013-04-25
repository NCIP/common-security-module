/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on May 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.remoting.SpringHttp.http.client;

import com.prototype.application.remote.RemoteObject;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceBroker {
  public static RemoteObject getRemoteObject(Object businessObject){
  	ClientSessionHolder csh = ClientSessionHolder.getInstance();
  	String sessionKey = csh.getSessionKey();
  	RemoteObject ro = new RemoteObject();
  	ro.setBusinessObject(businessObject);
  	ro.setSessionKey(sessionKey);
  	System.out.println("Sending request with sessionKey:"+sessionKey);
  	return ro;
  }
}
