/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.clientlayer;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClientLayerGenerator {
	
	public String basePackage;
	public Class applicationServiceInterface;
	
	public ClientLayerGenerator(Class applicationServiceInterface){
		this.applicationServiceInterface= applicationServiceInterface;
	}
	
	public void createApplicationServiceProvider(){
		
	}
	public void createGetApplicationServiceMethod(){
		
	}
	/**
	 * This method will create the spring xml file for dependency injection
	 */
	public void createXMLGlue_forApplicationService_injection(){
		
	}
	/**
	 * Make ClientManagement.jar containing AuthenticationManager and ClientSessionHolder
	 * 
	 * Make a remotelayer.jar containing only RemoteService class
	 * 
	 * Make a applicationServer.jar contaiaing RemoteSe
	 * @param args
	 */

	public static void main(String[] args) {
	}
}
