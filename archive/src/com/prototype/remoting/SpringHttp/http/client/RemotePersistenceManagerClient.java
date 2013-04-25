/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on May 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.remoting.SpringHttp.http.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.prototype.application.dataobjects.Person;
import com.prototype.application.persistence.PersistenceException;
import com.prototype.application.persistence.PersistenceManager;
import com.prototype.application.remote.RemoteObject;
import com.prototype.application.remote.RemoteService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemotePersistenceManagerClient implements PersistenceManager{

	
	public RemotePersistenceManagerClient(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "src/com/prototype/remoting/SpringHttp/conf/remoteService.xml");

        rs = (RemoteService)ctx.getBean("remoteService");
	}
	/* (non-Javadoc)
	 * @see com.prototype.application.persistence.PersistenceManager#getPersonById(java.lang.String)
	 */
	public Person getPersonById(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		//RemoteObject ro = new RemoteObject();
		//ro.setBusinessObject(id);
		//ro.setSessionKey("1234");
		RemoteObject ro = ServiceBroker.getRemoteObject(id);
		return rs.getPersonById(ro);
	}

	/* (non-Javadoc)
	 * @see com.prototype.application.persistence.PersistenceManager#createPerson(com.prototype.application.dataobjects.Person)
	 */
	public Person createPerson(Person p) throws PersistenceException {
		// TODO Auto-generated method stub
		RemoteObject ro = ServiceBroker.getRemoteObject(p);
		return rs.createPerson(ro);
	}

	/* (non-Javadoc)
	 * @see com.prototype.application.persistence.PersistenceManager#deletePersonById(java.lang.String)
	 */
	public boolean deletePersonById(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		RemoteObject ro = ServiceBroker.getRemoteObject(id);
		return rs.deletePersonById(ro);
	}
	
	private RemoteService rs;

}
