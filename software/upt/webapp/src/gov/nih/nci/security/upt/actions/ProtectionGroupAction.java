/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jan 12, 2005
 *
 */
package gov.nih.nci.security.upt.actions;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module's User Provisioning Tool (UPT) Software License,
 *Version 3.0 Copyright 2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'UPT Software').  The UPT Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This UPT Software License (the 'License') is between NCI and You.  'You (or
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
 *rights in the UPT Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the UPT Software; (ii) distribute and have distributed to
 *and by third parties the UPT Software and any modifications and derivative works
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


import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.ProtectionGroupForm;
import gov.nih.nci.security.upt.forms.RoleForm;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 */
public class ProtectionGroupAction extends CommonAssociationAction 
{
	private static final Logger logProtectionGroup = Logger.getLogger(ProtectionGroupAction.class);
	ProtectionGroupForm protectionGroupForm;
	private String operation;
	private String[] associatedIds;
	private String[] parentAssociatedIds;
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public ProtectionGroupForm getProtectionGroupForm() {
		return protectionGroupForm;
	}

	public void setProtectionGroupForm(ProtectionGroupForm protectionGroupForm) {
		this.protectionGroupForm = protectionGroupForm;
	}

	public String[] getAssociatedIds() {
		return associatedIds;
	}

	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}
	
	
	public String[] getParentAssociatedIds() {
		return parentAssociatedIds;
	}

	public void setParentAssociatedIds(String[] parentAssociatedIds) {
		this.parentAssociatedIds = parentAssociatedIds;
	}

	public String execute() throws Exception
	{
		if(protectionGroupForm == null)
			protectionGroupForm = new ProtectionGroupForm();
		
		protectionGroupForm.setAssociatedIds(associatedIds);
		protectionGroupForm.setParentAssociatedIds(parentAssociatedIds);
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome(protectionGroupForm);
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd(protectionGroupForm);
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch(protectionGroupForm);
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult(protectionGroupForm);
		else if(operation.equalsIgnoreCase("create"))
				return create(protectionGroupForm);
		else if(operation.equalsIgnoreCase("read"))
				return read(protectionGroupForm);
		else if(operation.equalsIgnoreCase("update"))
			return update(protectionGroupForm);
		else if(operation.equalsIgnoreCase("delete"))
			return delete(protectionGroupForm);
		else if(operation.equalsIgnoreCase("search"))
			return search(protectionGroupForm);
		else if(operation.equalsIgnoreCase("loadAssociation"))
			return loadAssociation(protectionGroupForm);
		else if(operation.equalsIgnoreCase("setAssociation"))
			return setAssociation(protectionGroupForm);
		else if(operation.equalsIgnoreCase("loadParentAssociation"))
			return loadParentAssociation();
		else if(operation.equalsIgnoreCase("setParentAssociation"))
			return setParentAssociation();
		else
			return "input";
			
	}	
	public String loadParentAssociation() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug("||"+protectionGroupForm.getFormName()+"|loadParentAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		
		Collection associatedProtectionGroup = (Collection)new HashSet();
		protectionGroupForm.buildDisplayForm(userProvisioningManager);
		if (protectionGroupForm.getProtectionGroupParentProtectionGroup() != null)
			associatedProtectionGroup.add(protectionGroupForm.getProtectionGroupParentProtectionGroup());
		
		ProtectionGroup protectionGroup = new ProtectionGroup();
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroup);
		
		Collection protectionGroupList = (Collection)new HashSet();
		protectionGroupList.add(userProvisioningManager.getProtectionGroupById(protectionGroupForm.getPrimaryId()));
		availableProtectionGroups = ObjectSetUtil.minus(availableProtectionGroups,protectionGroupList);
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroup);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
		
		if (logProtectionGroup.isDebugEnabled())
			logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+protectionGroupForm.getFormName()+"|loadParentAssociation|Success|Success in Loading Parent Association for "+protectionGroupForm.getFormName()+" object|"
				+"|");
		return ForwardConstants.LOAD_PARENT_ASSOCIATION_SUCCESS;		
		
	}
	
	public String setParentAssociation() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug("||"+protectionGroupForm.getFormName()+"|setParentAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return ForwardConstants.LOGIN_PAGE;
		}
		UserInfoHelper.setUserInfo(((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), session.getId());
		try
		{
			// TO-DO replace assignProtectionGroups with setOwners or such method
			String parentProtectionGroupId = null;
			if (protectionGroupForm.getParentAssociatedIds() != null && (protectionGroupForm.getParentAssociatedIds().length == 1))
			{
				parentProtectionGroupId = protectionGroupForm.getParentAssociatedIds()[0];
			}
			else
			{
				parentProtectionGroupId = null;
			}
			userProvisioningManager.assignParentProtectionGroup(parentProtectionGroupId, protectionGroupForm.getProtectionGroupId());
			protectionGroupForm.setRequest(request);
			protectionGroupForm.buildDisplayForm(userProvisioningManager);
			addActionMessage("Association Update Successful");
		}
		catch (CSException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+protectionGroupForm.getFormName()+"|setParentAssociation|Failure|Error Loading Protection Group Association for the "+protectionGroupForm.getFormName()+" object|"
					+"|"+ cse.getMessage());			
		}
		if (logProtectionGroup.isDebugEnabled())
			logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+protectionGroupForm.getFormName()+"|setParentAssociation|Success|Success in Setting Parent Association for "+protectionGroupForm.getFormName()+" object|"
				+"|");
		return ForwardConstants.SET_PARENT_ASSOCIATION_SUCCESS;
		
	}



}
