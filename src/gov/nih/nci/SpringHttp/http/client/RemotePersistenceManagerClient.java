/*
 * Created on May 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.SpringHttp.http.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ekagrasoft.dataobjects.Person;
import com.ekagrasoft.persistence.PersistenceException;
import com.ekagrasoft.persistence.PersistenceManager;
import com.ekagrasoft.remote.RemoteObject;
import com.ekagrasoft.remote.RemoteService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemotePersistenceManagerClient implements PersistenceManager{

	
	public RemotePersistenceManagerClient(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "src/gov/nih/nci/SpringHttp/conf/remoteService.xml");

        rs = (RemoteService)ctx.getBean("remoteService");
	}
	/* (non-Javadoc)
	 * @see com.ekagrasoft.persistence.PersistenceManager#getPersonById(java.lang.String)
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
	 * @see com.ekagrasoft.persistence.PersistenceManager#createPerson(com.ekagrasoft.dataobjects.Person)
	 */
	public Person createPerson(Person p) throws PersistenceException {
		// TODO Auto-generated method stub
		RemoteObject ro = ServiceBroker.getRemoteObject(p);
		return rs.createPerson(ro);
	}

	/* (non-Javadoc)
	 * @see com.ekagrasoft.persistence.PersistenceManager#deletePersonById(java.lang.String)
	 */
	public boolean deletePersonById(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		RemoteObject ro = ServiceBroker.getRemoteObject(id);
		return rs.deletePersonById(ro);
	}
	
	private RemoteService rs;

}
