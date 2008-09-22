package gov.nih.nci.security.dao;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
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


import gov.nih.nci.security.authorization.domainobjects.*;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;

/**
 * This interface is used for persistence to the appropriate application. The
 * interface will persist to and retrive the data from the database.
 * 
 * @version 1.0
 * created 03-Dec-2004 1:17:47 AM
 */
public interface AuthorizationDAO {

	/**
	 * @param protectionGroup
	 *  
	 */
	/**
	public void createProtectionGroup(ProtectionGroup protectionGroup)
			throws CSTransactionException;
     */
	/**
	 * @param loginName
	 *  
	 */
	public User getUser(String loginName);
	
	public Set getUsers(String groupId) throws CSObjectNotFoundException;

	public ApplicationContext getApplicationContext();
	
	public Application getApplication();

	
	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 *  
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId,
			String protectionElementAttributeName)
			throws CSTransactionException;

	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.UserProvisioningManager#assignUserToGroup(java.lang.String, java.lang.String)
	 */
	public void assignUserToGroup(String userId,String groupName)throws CSTransactionException;
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.UserProvisioningManager#assignUsersToGroup(java.lang.String, java.lang.String[])
	 */
	public void assignUsersToGroup(String groupId, String[] userIds) throws CSTransactionException;

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.UserProvisioningManager#assignUsersToGroup(java.lang.String, java.lang.String[])
	 */
	public void addUsersToGroup(String groupId, String[] userIds) throws CSTransactionException;

	
	/**
	 * @param protectionElementObjectId
	 * @param userNames
	 *  
	 */
	public void setOwnerForProtectionElement(
			String protectionElementObjectId, String[] userNames)
			throws CSTransactionException;

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 *  
	 */
	public void addUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId)
			throws CSTransactionException;
	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 *  
	 */
	public void assignUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId)
			throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 *  
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException;

	
	/**
	 * @param protectionGroupId
	 * @param userId
	 * @param rolesId 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId, String userId, String[] rolesId) throws CSTransactionException;

	
	
	/**
	 * @param permission
	 * @param subject
	 * @throws CSException
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) throws CSException;

	
	/**
	 * @param permission
	 * @param userName
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, String userName) throws CSException;

	

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 *  
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeId, String privilegeName) throws CSException;

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeName
	 * @param attributeValue
	 * @param privilegeName
	 * @return
	 */
	public boolean checkPermission(String userName, String objectId, String attributeName, String attributeValue, String privilegeName) throws CSException;
	
	/**
	 * @param privilege
	 *  
	 */
	/**
	public void createPrivilege(Privilege privilege)
			throws CSTransactionException;
    */
	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 *  
	 */
	public boolean checkPermission(String userName, String objectId,
			String privilegeName) throws CSException;

	public boolean checkOwnership(String userName,
			String protectionElementObjectId);

	/**
	 * @param userName
	 *  
	 */
	public Principal[] getPrincipals(String userName);

	
	/**
	 * @param roleId
	 * @param privilegeIds
	 *  
	 */
	public void addPrivilegesToRole(String roleId, String[] privilegeIds)
			throws CSTransactionException;

	/**
	 * @param roleId
	 * @param privilegeIds
	 *  
	 */
	public void assignPrivilegesToRole(String roleId, String[] privilegeIds)
			throws CSTransactionException;

	/**
	 * Returns the protection element for the passed object id
	 * 
	 * @param objectId
	 *  
	 */
	public ProtectionElement getProtectionElement(String objectId)
			throws CSObjectNotFoundException;

	

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 *  
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException;

	

	
	/**
	 * @param userName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 * @throws CSTransactionException
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)
			throws CSTransactionException;
	
	
	/**
	 * @param loginName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 * @throws CSTransactionException
	 */
	public void removeOwnerForProtectionElement(String loginName, String protectionElementObjectId, String protectionElementAttributeName)
			throws CSTransactionException;
			
			
	/**
	 * @param protectionElementObjectId
	 * @param userNames
	 * @throws CSTransactionException
	 */
	public void removeOwnerForProtectionElement(String protectionElementObjectId,
					String[] userNames) throws CSTransactionException;
	
	
	
	/**
	 * @param userId
	 * @param groupIds
	 */
	public void addGroupsToUser(String userId,String[] groupIds)
			throws CSTransactionException;
	
	/**
	 * @param userId
	 * @param groupIds
	 */
	public void assignGroupsToUser(String userId,String[] groupIds)
			throws CSTransactionException;

	/**
	 * @param groupId
	 * @param userId
	 *  
	 */
	public void removeUserFromGroup(String groupId, String userId)
			throws CSTransactionException;

	
	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 *  
	 */
	public void addGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException ;
	
	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 *  
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException;

	
	
	/**
	 * This method removes the user from a protection group irrespective of all
	 * the roles
	 * 
	 * @param protectionGroupId
	 * @param userId
	 *  
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId,
			String userId) throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 *  
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId,
			String groupId, String[] roleId) throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 *  
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId,
			String groupId) throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 *  
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)
			throws CSObjectNotFoundException;

	

	/**
	 * @param roleName
	 *  
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException;

	

	

	

	public Set getPrivileges(String roleId) throws CSObjectNotFoundException;

	/**
	 * @param searchCriteria
	 *  
	 */
	public java.util.List getObjects(SearchCriteria searchCriteria);

	//public void createUser(User user) throws CSTransactionException;

	public void assignProtectionElements(String protectionGroupId,
			String[] protectionElementIds) throws CSTransactionException;

	public void addProtectionElements(String protectionGroupId,
			String[] protectionElementIds) throws CSTransactionException;
	
	public void removeProtectionElementsFromProtectionGroup(
			String protectionGroupId, String[] protectionLementIds)
			throws CSTransactionException;
	
	public Set getProtectionGroupRoleContextForUser(String userId) throws CSObjectNotFoundException;
	
	public Set getProtectionGroupRoleContextForGroup(String groupId) throws CSObjectNotFoundException;

	public Set getProtectionElementPrivilegeContextForUser(String userId) throws CSObjectNotFoundException;
	
	public Set getProtectionElementPrivilegeContextForGroup(String groupId) throws CSObjectNotFoundException;	
	
	public void removeObject(Object obj) throws CSTransactionException;
	
	public void modifyObject(Object obj) throws CSTransactionException;
	
	public Object getObjectByPrimaryKey(Class objectType, String primaryKey)throws CSObjectNotFoundException;
	
	public void createObject(Object obj) throws CSTransactionException;
	
	public Set getGroups(String userId) throws CSObjectNotFoundException;
	
	public Set getProtectionElements(String protectionGroupId) throws CSObjectNotFoundException;

	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException;
	
	public void addToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException;
	
	public void assignToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException;

	public void assignParentProtectionGroup(String parentProtectionGroupId,String childProtectionGroupId) throws CSTransactionException;
	
	public void addOwners(String protectionElementId,String[] userIds) throws CSTransactionException;
	
	public void assignOwners(String protectionElementId,String[] userIds) throws CSTransactionException;
	
	public Set getOwners(String protectionElementId) throws CSObjectNotFoundException;

	public Object secureObject(String userName, Object obj) throws CSException;
	
	public Collection secureCollection(String userName,Collection objects) throws CSException;
	
	public Collection getPrivilegeMap(String userName,Collection pEs) throws CSException;
	
	public Object secureUpdate(String userName, Object originalObject,Object mutatedObject) throws CSException;

	public boolean checkPermissionForGroup(String groupName, String objectId, String attributeName, String privilegeName) throws CSException;
	
	public boolean checkPermissionForGroup(String groupName, String objectId, String attributeName, String attributeValue, String privilegeName) throws CSException;

	public boolean checkPermissionForGroup(String groupName, String objectId, String privilegeName) throws CSException;

	public List getAccessibleGroups(String objectId, String privilegeName) throws CSException;

	public List getAccessibleGroups(String objectId, String attributeName, String privilegeName) throws CSException;

	public Application getApplication(String applicationContextName) throws CSObjectNotFoundException;

	public List getAttributeMap(String userName, String className, String privilegeName);

	public List getAttributeMapForGroup(String groupsName, String className, String privilegeName);

	
}


