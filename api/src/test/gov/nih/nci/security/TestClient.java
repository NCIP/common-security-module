/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package test.gov.nih.nci.security;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 1.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */



import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.junk.RandomIntGenerator;
import gov.nih.nci.security.util.ObjectSetUtil;
import gov.nih.nci.security.dao.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;



/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class TestClient {
	static UserProvisioningManager upm = null;
	
	static{
		try{
		Properties p = System.getProperties();
		p.setProperty("gov.nih.nci.security.configFile","C:/securityConfig/ApplicationsecurityConfig.xml");
		upm = SecurityServiceProvider.getUserProvisioningManager("security");
		//upm = SecurityServiceProvider.getUserProvisioningManager("csmupt");
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void testPrivilegeCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		Privilege p1 = new Privilege();
		p1.setName("Update");
		p1.setDesc("Update Access");
		Privilege p2 = new Privilege();
		p2.setName("Read");
		p2.setDesc("Read Access");
		Privilege p3 = new Privilege();
		p3.setName("Create");
		p3.setDesc("Create Access");
		Privilege p4 = new Privilege();
		p4.setName("Delete");
		p4.setDesc("Update Access");
		try{
			upm.createPrivilege(p1);
			upm.createPrivilege(p2);
			upm.createPrivilege(p3);
			upm.createPrivilege(p4);
			//System.out.println("The returned id is:"+p.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testGetProtectionElements(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			java.util.Set pes = upm.getProtectionElements("54");
			System.out.println(pes.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void getProtectionGroups(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			java.util.Set pes = upm.getProtectionGroups("yuy");
			System.out.println(pes.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testPrivilegeDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removePrivilege("1");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void getProtectionGroupById(String id){
		
		
		try{
			ProtectionGroup child = upm.getProtectionGroupById(id);
//			ProtectionGroup parent = child.getParentProtectionGroup();
			System.out.println(" >>>>>>>>>>>>> " + child.getProtectionGroupId());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void testPrivilegeFind(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			Privilege p = upm.getPrivilege("Read");
			System.out.println("The returned Name:"+p.getName());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	
	public void testModifyCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			//Privilege p = upm.getPrivilege("2");
			Privilege p = new Privilege();
			p.setName("Create");
			p.setDesc("Create Access");
			upm.modifyPrivilege(p);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testRoleCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			//for(int i=1;i<11;i++){
				Role r = new Role();
				r.setName("Admin1234");
				r.setDesc("Admin role 1234 desc");
				Byte b = new Byte("1");
				r.setActive_flag(b.byteValue());
				upm.createRole(r);
			//}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testRoleDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removeRole("2");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void getPrincipals(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			am.getPrincipals("  ");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void secureCollection(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			am.secureCollection("  ",new ArrayList());
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void getPrivilegeMap(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			ArrayList al = new ArrayList();
			
			am.getPrivilegeMap("kumarvi",al);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testUserDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removeUser("1000");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testModifyRole(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			Role r = upm.getRoleById("3");
			r.setDesc("Updated Test Role Desc 2");
			upm.modifyRole(r);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void testGetProtectionGroupById(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			ProtectionElement pe = upm.getProtectionElementById("20044");
			System.out.println("Protection Element" + pe.getProtectionElementName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public void assignPrivilegeToRoles(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		//String[] privilegeIds = {"1", "2","3"};
		//String[] privilegeIds = {"1","2"};
		String[] privilegeIds = {"27","29"};
		//String[] privilegeIds = {};
		String roleId = "54";
		try{
			upm.assignPrivilegesToRole(roleId,privilegeIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignToProtectionGroups(){
		
		String[] protectionGroupIds = {"80","81","87","93"};
		
		String protectionElementId = "19";
		try{
			upm.assignToProtectionGroups(protectionElementId,protectionGroupIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getPrivileges(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		String roleId = "1";
		try{
			java.util.Collection result = upm.getPrivileges(roleId);
			Iterator it = result.iterator();
			while(it.hasNext()){
				Privilege p = (Privilege)it.next();
				System.out.println(p.getId().toString()+":"+p.getName()+":"+p.getDesc());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testGroupCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			for(int i=1;i<101;i++){
				Group grp = new Group();
				grp.setGroupName("Group_Name_"+i);
				grp.setGroupDesc("Group_Desc_"+i);
				upm.createGroup(grp);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testUserCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{

				User user = new User();
				user.setLoginName("vinaykumar");
				user.setFirstName("User_first_name_");
				user.setLastName("User_last_name_");
				user.setDepartment("NCI_");
				user.setEmailId(user.getLastName()+"@mail.nih.nci.gov");
				user.setOrganization("NIH");
				
				upm.createUser(user);
				System.out.println("The returned id is"+user.getUserId());
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	public void getUser(){
		
		String loginName = "   ";
		try{
			User user = upm.getUser(loginName);
			System.out.println(user.getFirstName()+":"+user.getEmailId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testProtectionGroupCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			for(int i=1;i<101;i++){
				ProtectionGroup pg = new ProtectionGroup();
				pg.setProtectionGroupName("protection_group_name_="+i);
				pg.setProtectionGroupDescription("PG_Desc_"+i);
				upm.createProtectionGroup(pg);
				System.out.println("The returned id is"+pg.getProtectionGroupId());
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void testProtectionGroupModify(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			ProtectionGroup pg = upm.getProtectionGroup(new Long("2"));
			ProtectionGroup pg1 = upm.getProtectionGroup(new Long("50"));
			pg1.setParentProtectionGroup(pg);
			upm.modifyProtectionGroup(pg1);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	public void testProtectionElementCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			/**
			for(int i=1;i<100000;i++){
				ProtectionElement pe = new ProtectionElement();
				pe.setProtectionElementName("PE_Name_"+i);
				pe.setObjectId("X_Y_Z_"+i);
				pe.setProtectionElementDescription("PE_Desc"+i);
				
				upm.createProtectionElement(pe);
				System.out.println("The returned id is"+pe.getProtectionElementId());
			}
			*/
			ProtectionElement pe = new ProtectionElement();
			pe.setProtectionElementId(new Long(20));
			upm.createProtectionElement(pe);
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error:"+ex.getMessage());
		}
	}
	
	public void getProtectionElement(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			ProtectionElement pe = upm.getProtectionElement("X_Y_Z_9");
			System.out.println("The name is"+pe.getProtectionElementName());
			//pe = upm.getProtectionElement(new Long("15"));
			System.out.println("The name is"+pe.getProtectionElementName());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void addUserToGroup(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			upm.addUserToGroup("2","15");
			upm.addUserToGroup("34","15");
			upm.addUserToGroup("33","15");
			upm.addUserToGroup("26","15");
			upm.addUserToGroup("2","16");
			upm.addUserToGroup("2","445");
			upm.addUserToGroup("5","45");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	public void removeUserFromGroup(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			//upm.addUserToGroup("2","15");
			upm.removeUserFromGroup("2","15");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignProtectioElement(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			String[] peIds = {"22","33","44","55"};
			String pgId = "3";
			//upm.assignProtectionElements(pgId,peIds);
			AuthorizationManager am = (AuthorizationManager)upm;
			am.assignProtectionElement("protection_group_name_=1","45 ","yrh");
			
		}catch(Exception ex){
			System.out.println("Error:"+ex.getMessage());
		}
	}
	
	public void assignUserRoleToProtectionGroup(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			//String[] peIds = {"10","12","13","15"};
			//String pgId = "10";
			//upm.assignProtectionElements(pgId,peIds);
			//Role r = upm.getRoleById("2");
			upm.assignUserRoleToProtectionGroup("700",new String[]{"54"},"35");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void priv_populatePgPe(){
		RandomIntGenerator rit = new RandomIntGenerator(19,20015);
		String[] peIds = new String[100];
		for(int i=0;i<100;i++){	    	 	
			int k = rit.draw();
			peIds[i]= String.valueOf(k);
		}
		rit = new RandomIntGenerator(35,132);
		String pg_id = String.valueOf(rit.draw());
		
		try{
			upm.assignProtectionElements(pg_id,peIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void priv_populateUgrpg(){
		RandomIntGenerator rit = new RandomIntGenerator(55,63);
		String[] roleIds = new String[2];
		for(int i=0;i<2;i++){	    	 	
			int k = rit.draw();
			roleIds[i]= String.valueOf(k);
		}
		rit = new RandomIntGenerator(1,5000);
		String user_id = String.valueOf(rit.draw());
		rit = new RandomIntGenerator(34,133);
		String pg_id = String.valueOf(rit.draw());
		
		try{
			upm.assignUserRoleToProtectionGroup(user_id,roleIds,pg_id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void populateUgrpg(){
		for(int i=1;i<10000;i++){	    	 	
			priv_populateUgrpg();
		}
	}
	
	public void populatePgPe(){
		for(int i=1;i<100;i++){	    	 	
			priv_populatePgPe();
		}
	}
	public void checkPermission(){
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			System.out.println(System.currentTimeMillis());
			//System.out.println(am.checkPermission("login_name_4322","x_y_z_11919","Delete"));
			//System.out.println(am.checkPermission("kumarvi","csmupt",null));
			//System.out.println(am.checkPermission("login_name_2121","x_y_z_17385","Delete"));
			//System.out.println(System.currentTimeMillis());
			boolean f = am.checkPermission("dighn","dgkhn","Jungle");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error:"+ex.getMessage());
		}
	}
	public void deAssignProtectionElement(){
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			
			am.deAssignProtectionElements("  rrrrr","X_Y_Z_2121");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error:"+ex.getMessage());
		}
	}
	public void testKunalCode(){
		try{
			Collection associatedGroups = (Collection)upm.getGroups("5020");
            Group group = new Group();
            group.setGroupName("Group_Name%");
            SearchCriteria searchCriteria = new GroupSearchCriteria(group);
            Collection totalGroups = (Collection)upm.getObjects(searchCriteria);
            Collection availableGroups = ObjectSetUtil.minus(totalGroups,associatedGroups);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getGroups(String id){
		try{
			Collection cal = upm.getGroups("5020");
			Iterator it = cal.iterator();
			while(it.hasNext()){
				Group gp = (Group)it.next();
				System.out.println(gp.getGroupName());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getProtectionGroupRoleContextForUser(String id){
		try{
			Collection cal = upm.getProtectionGroupRoleContextForUser(id);
			Iterator it = cal.iterator();
			while(it.hasNext()){
				ProtectionGroupRoleContext gp = (ProtectionGroupRoleContext)it.next();
				System.out.println(gp.getProtectionGroup().getProtectionGroupName());
				System.out.println(gp.getRoles().size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getProtectionGroupRoleContextForGroup(String id){
		try{
			Collection cal = upm.getProtectionGroupRoleContextForGroup(id);
			Iterator it = cal.iterator();
			while(it.hasNext()){
				ProtectionGroupRoleContext gp = (ProtectionGroupRoleContext)it.next();
				System.out.println(gp.getProtectionGroup().getProtectionGroupName());
				System.out.println(gp.getRoles().size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getObjects(){
		try{
			//Role role = new Role();
			//role.setName("role_name_1");
			//Group grp = new Group();
			//User user = new User();
			//user.setLoginName("login_name_1");
			//user.setFirstName("%");
			//user.setDepartment("security");
			ProtectionElement pe = new ProtectionElement();
			pe.setObjectId("%");
			//pe.setProtectionElementName("PE_name_1");
			//grp.setGroupName("g%");
			//SearchCriteria sc = new RoleSearchCriteria(role);
			SearchCriteria sc = new ProtectionElementSearchCriteria(pe);
			//SearchCriteria sc = new UserSearchCriteria(user);
			System.out.print(new java.util.Date());
			List result = upm.getObjects(sc);
			System.out.println(result.size());
			System.out.print(new java.util.Date());
			   Iterator it = result.iterator();
			   //while(it.hasNext()){
			   //	Role p = (Role)it.next();
			   	//User usr = (User)it.next();
			   	//System.out.println(usr.getFirstName()+":"+usr.getLastName());
			   //}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignOwners(){
		try{
			String[] uids = {"1","2"};
			String peIds = "3";
			upm.assignOwners(peIds,uids);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testSecureObject(){
		try{
			Role r = upm.getRoleById("55");
			Role r1 = (Role)upm.secureObject("kumarvi",r);
			System.out.println(r1.getDesc());
			System.out.println(r1.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		ts.secureCollection();

	}
}
