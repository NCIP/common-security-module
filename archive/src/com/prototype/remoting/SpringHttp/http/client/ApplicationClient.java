/*
 * Created on May 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.remoting.SpringHttp.http.client;

import com.prototype.application.dataobjects.Person;
import com.prototype.application.persistence.PersistenceManager;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationClient {
    
  private PersistenceManager pm;
  
  public ApplicationClient(){
  	pm = (PersistenceManager)new RemotePersistenceManagerClient();
  }
  
	public void callServiceWithoutLogin(){
		
		try{
	 		Person p = pm.getPersonById("3");
	 		System.out.println("Name is:"+p.getName());
	 			 		
	 	}catch(Exception ex){
	 		System.out.println(ex.getMessage());
	 	}
	}
	public void callServiceWithLogin(){
		
		try{
	 		
	 		
	 		AuthenticationManager am = new AuthenticationManager();
	 		am.authenticate("kumarvi","1234");
	 		
	 		Person p = pm.getPersonById("3");
	 		if(p!=null){
	 		System.out.println("Name is:"+p.getName());
	 		}else{
	 			System.out.println("could not find person with id =3");
	 		}
	 		
	 		p = pm.getPersonById("12");
	 		if(p!=null){
		 		System.out.println("Name is:"+p.getName());
		 		}else{
		 			System.out.println("could not find person with id =12");
		 		}
	 	}catch(Exception ex){
	 		System.out.println(ex.getMessage());
	 	}
		
	}
	
	public void callServiceLoginLogout(){
		try{
	 		
	 		
	 		AuthenticationManager am = new AuthenticationManager();
	 		am.authenticate("kumarvi","1234");
	 		
	 		Person p = pm.getPersonById("3");
	 		if(p!=null){
	 		System.out.println("Name is:"+p.getName());
	 		}else{
	 			System.out.println("could not find person with id =3");
	 		}
	 		am.logOut();
	 		p = pm.getPersonById("12");
	 		if(p!=null){
		 		System.out.println("Name is:"+p.getName());
		 		}else{
		 			System.out.println("could not find person with id =12");
		 		}
	 	}catch(Exception ex){
	 		System.out.println(ex.getMessage());
	 	}
	}
	
	public void callLoginLogOut(){
		AuthenticationManager am = new AuthenticationManager();
 		am.authenticate("kumarvi","1234");
 		
 		am.logOut();
 		
 		Person p = null;
 		try{
 		p=pm.getPersonById("3");
 		}catch(Exception ex){
 			System.out.println("Error:"+ex.getMessage());
 		}
 		if(p!=null){
 		System.out.println("Name is:"+p.getName());
 		}else{
 			System.out.println("could not find person with id =3");
 		}
	}
	 public static void main(String[] args){
	 	ApplicationClient ac = new ApplicationClient();
	 	
	 	//ac.callServiceWithoutLogin();
	 	//ac.callServiceWithLogin();
	 	ac.callServiceLoginLogout();
	 	//ac.callLoginLogOut();
	 	
	 }
	 
	 
	 
	 
}
