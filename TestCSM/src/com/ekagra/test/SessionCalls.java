/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.test;

import java.util.Properties;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.Privilege;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SessionCalls {
	private String name = null;
	LogWriter logger = LogWriter.getInstance();
	UserProvisioningManager upm = null;
	AuthorizationManager am = null;
	RandomIntGenerator randomUser = new RandomIntGenerator(3,5044);
	RandomIntGenerator randomProtectionElement = new RandomIntGenerator(20,20051);
	RandomIntGenerator randomPrivilege = new RandomIntGenerator(26,60);
	
	public SessionCalls(String name){
		this.name = name;
	}
	
	public void startSession(){
	try{
		//Properties p = System.getProperties();
		//p.setProperty("gov.nih.nci.security.configFile","C:/securityConfig/ApplicationsecurityConfig.xml");
		logger.log(LogWriter.INFO,name+" :Sesion is starting");
		upm = SecurityServiceProvider.getUserProvisioningManager("security");
		if(upm==null) throw new Exception();
		am = (AuthorizationManager)upm;
		logger.log(LogWriter.INFO,name+" :Got Managers");
		}catch(Exception ex){
			logger.log(LogWriter.SEVERE,name+" :Could not initilaise the managers");
			//ex.printStackTrace();
		}
	}
	
	public void doWork(){
		startSession();
		int randomUserId  = randomUser.draw();
		String userId = String.valueOf(randomUserId);
		int randomProtectionElementId = randomProtectionElement.draw();
		String peId = String.valueOf(randomProtectionElementId);
		int randomPrivilegeId = randomPrivilege.draw();
		String privilegeId = String.valueOf(randomPrivilegeId);
		User user = null;
		ProtectionElement protectionElement = null;
		Privilege privilege = null;
		try{
			logger.log(LogWriter.INFO,name+" Getting user with userId="+userId);
			user = upm.getUserById(userId);
			logger.log(LogWriter.INFO,name+" Got user with userId="+userId);
		}catch(Exception ex1){
			logger.log(LogWriter.SEVERE,name+" :Could not get User with userId="+userId);
		}
		
		try{
			logger.log(LogWriter.INFO,name+" Getting ProtectionElement with protectionElementId="+peId);
			protectionElement = upm.getProtectionElementById(peId);
			
			logger.log(LogWriter.INFO,name+" Got ProtectionElement with protectionElementId="+peId);
			
		}catch(Exception ex1){
			logger.log(LogWriter.SEVERE,name+" :Could not get protection Element with protectionElementId="+peId);
		}
		
		try{
			logger.log(LogWriter.INFO,name+" Getting Privilege with privilegeId="+privilegeId);
			privilege = upm.getPrivilegeById(privilegeId);
			logger.log(LogWriter.INFO,name+" Got Privilege with privilegeId="+privilegeId);
			
		}catch(Exception ex1){
			logger.log(LogWriter.SEVERE,name+" :Could Privilege with privilegeId="+privilegeId);
		}
		
		 /**
		  * Now we will call the real method
		  */
		String userName = null;
		String objectId = null;
		String privilegeName = null;
		if(user!=null){
			userName = user.getLoginName();
		}
		if(protectionElement!=null){
			objectId = protectionElement.getObjectId();
		}
		
		if(privilege!=null){
			privilegeName =privilege.getName();
		}
		    try{
		    	logger.log(LogWriter.INFO,name+" :calling checkPermission with arguments userName="+userName+" objectId="+objectId+" privlegeName="+privilegeName);
		    	boolean result = am.checkPermission(userName,objectId,privilegeName);
		    	logger.log(LogWriter.INFO,name+" :The result of checkPermission =" +result);
		    	logger.log(LogWriter.INFO,name+" :called checkPermission successfully");
		    	
		    }catch(Exception ex2){
		    	ex2.printStackTrace();
		    	logger.log(LogWriter.SEVERE,name+" :Failed checkPermission with arguments userName="+userName+" objectId="+objectId+" privlegeName="+privilegeName);
		    }
	}
	
	public static void main(String[] args){
		SessionCalls sc = new SessionCalls("Vinay");
		sc.doWork();
	}

}
