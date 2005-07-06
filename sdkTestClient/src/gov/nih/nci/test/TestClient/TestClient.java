/*
 * Created on Jun 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.test.TestClient;

import java.util.List;

import com.codegen.application.client.ApplicationServiceProvider;
import com.codegen.application.client.ClientSession;

import gov.nih.nci.sdk.prototype.domainobjects.Item;
import gov.nih.nci.sdk.prototype.service.SDKApplicationService;



/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestClient {
	/**
	 * Test retrieval of objects
	 *
	 */
	public void testGetObjects(){
		ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Item it = new Item();
		it.setName("rat");
		ApplicationServiceProvider asp = new ApplicationServiceProvider();
		SDKApplicationService appService = asp.getApplicationService();
		
		try{
			List l = appService.getObjects(it);
			System.out.println(l.size());
			
		}catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		cs.terminateSession();
	}

	/**
	 * Test creation of object
	 *
	 */
	public void testCreateObject(){
		ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Item it = new Item();
		it.setName("IceCream1234");
		ApplicationServiceProvider asp = new ApplicationServiceProvider();
		SDKApplicationService appService = asp.getApplicationService();
		
		try{
			Item it1 = (Item)appService.createObject(it);
			System.out.println(it1.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		cs.terminateSession();
	}
	/**
	 * Test updation of object
	 *
	 */
	
	public void testupdateObject(){
		ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Item it = new Item();
		it.setName("rat");
		ApplicationServiceProvider asp = new ApplicationServiceProvider();
		SDKApplicationService appService = asp.getApplicationService();
		
		try{
			List l = appService.getObjects(it);
			System.out.println(l.size());
			Item it1 = (Item)l.get(0);
			it1.setName("Rat Updated Name");
			Item it2 = (Item)appService.updateObject(it1);
			System.out.println(it2.getName());
		}catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		cs.terminateSession();
	}
	
	/**
	 * Test deletion of object
	 *
	 */
	
	public void testdeleteObject(){
		ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		Item it = new Item();
		it.setName("rat1");
		ApplicationServiceProvider asp = new ApplicationServiceProvider();
		SDKApplicationService appService = asp.getApplicationService();
		
		try{
			List l = appService.getObjects(it);
			Item it1 = (Item)l.get(0);
			appService.removeObject(it1);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		cs.terminateSession();
	}
	
	 public void testSessionTimeOut(){
	 	ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Item it = new Item();
		it.setName("rat");
		ApplicationServiceProvider asp = new ApplicationServiceProvider();
		SDKApplicationService appService = asp.getApplicationService();
		
		try{
			List l = appService.getObjects(it);
			System.out.println(l.size());
			
		}catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		/**
		 * Wait for some time for session to expire
		 */
		pause(5000);
		
		/**
		 * Then again call this method and it should throw an error
		 */
		try{
			List l = appService.getObjects(it);
			System.out.println(l.size());
			
		}catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		cs.terminateSession();
	 }
	 /**
	  * try wrong userId and password, it should throw an error.
	  * On the server console a sessionKey will be generated for this user
	  * if successfully logged in
	  *
	  */
	 public void testLogin(){
	 	ClientSession cs = ClientSession.getInstance();
		try{
			cs.startSession("kumarvi","password");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	 }
	 
	 public void testLogOut(){
	 	ClientSession cs = ClientSession.getInstance();
	 	
	 }
	 
	 private void pause(long time){
	 	try{
	 		Thread.sleep(time);
	 	}catch(Exception ex){
	 		ex.printStackTrace();
	 	}
	 }
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		//ts.testCreateObject();
		//ts.testupdateObject();
		ts.testdeleteObject();
	}
}
