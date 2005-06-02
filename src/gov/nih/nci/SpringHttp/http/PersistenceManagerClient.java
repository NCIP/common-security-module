/*
 * Created on May 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.SpringHttp.http;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ekagrasoft.persistence.PersistenceManager;
/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PersistenceManagerClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
        "src/gov/nih/nci/SpringHttp/conf/persistenceManager.xml");

        PersistenceManager pm = (PersistenceManager)ctx.getBean("persistenceManagerService");
        //System.out.println(helloWorld.getMessage());
        try{
        pm.getPersonById("333");
        }catch(Exception pex){
        	//pex.printStackTrace();
        	System.out.println("Something happened"+pex.getMessage());
        }
        /**
        Person person = new Person();
		person.setName("Spring_xyz_12993");
		try{
		person = pm.createPerson(person);
		}catch(Exception ex){
			System.out.println("The Message is:"+ex.getMessage());
		}
		System.out.println("Person's Id is : " + person.getId());
		*/
	}
}
