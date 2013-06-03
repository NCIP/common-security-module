/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Dec 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

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


import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.util.StringUtils;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchProtectionElementForm extends ValidatorForm implements BaseAssociationForm{

	private String protectionElementId;
	private String protectionElementName;
	private String protectionElementDescription;
	private String protectionElementObjectId;
	private String protectionElementAttribute;
	private String protectionElementType;
	private String protectionElementUpdateDate;
	
	private String[] associatedIds;
	private String[] ownershipAssociatedIds;
	
	
	/**
	 * @return Returns the protectionElementAttribute.
	 */
	public String getProtectionElementAttribute() {
		return protectionElementAttribute;
	}
	/**
	 * @param protectionElementAttribute The protectionElementAttribute to set.
	 */
	public void setProtectionElementAttribute(String protectionElementAttribute) {
		this.protectionElementAttribute = protectionElementAttribute;
	}
	/**
	 * @return Returns the protectionElementDescription.
	 */
	public String getProtectionElementDescription() {
		return protectionElementDescription;
	}
	/**
	 * @param protectionElementDescription The protectionElementDescription to set.
	 */
	public void setProtectionElementDescription(
			String protectionElementDescription) {
		this.protectionElementDescription = protectionElementDescription;
	}
	/**
	 * @return Returns the protectionElementId.
	 */
	public String getProtectionElementId() {
		return protectionElementId;
	}
	/**
	 * @param protectionElementId The protectionElementId to set.
	 */
	public void setProtectionElementId(String protectionElementId) {
		this.protectionElementId = protectionElementId;
	}
	/**
	 * @return Returns the protectionElementName.
	 */
	public String getProtectionElementName() {
		return protectionElementName;
	}
	/**
	 * @param protectionElementName The protectionElementName to set.
	 */
	public void setProtectionElementName(String protectionElementName) {
		this.protectionElementName = protectionElementName;
	}
	/**
	 * @return Returns the protectionElementObjectId.
	 */
	public String getProtectionElementObjectId() {
		return protectionElementObjectId;
	}
	/**
	 * @param protectionElementObjectId The protectionElementObjectId to set.
	 */
	public void setProtectionElementObjectId(String protectionElementObjectId) {
		this.protectionElementObjectId = protectionElementObjectId;
	}
	/**
	 * @return Returns the protectionElementType.
	 */
	public String getProtectionElementType() {
		return protectionElementType;
	}
	/**
	 * @param protectionElementTypeId The protectionElementTypeId to set.
	 */
	public void setProtectionElementType(String protectionElementType) {
		this.protectionElementType = protectionElementType;
	}
	
	/**
	 * @return Returns the protectionElementUpdateDate.
	 */
	public String getProtectionElementUpdateDate() {
		return protectionElementUpdateDate;
	}
	/**
	 * @param protectionElementUpdateDate The protectionElementUpdateDate to set.
	 */
	public void setProtectionElementUpdateDate(
			String protectionElementUpdateDate) {
		this.protectionElementUpdateDate = protectionElementUpdateDate;
	}
	/**
	 * @return Returns the associatedIds.
	 */
	public String[] getAssociatedIds() {
		return associatedIds;
	}
	/**
	 * @param associatedIds The associatedIds to set.
	 */
	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}
	/**
	 * @return Returns the ownershipAssociatedIds.
	 */
	public String[] getOwnershipAssociatedIds() {
		return ownershipAssociatedIds;
	}
	/**
	 * @param ownershipAssociatedIds The ownershipAssociatedIds to set.
	 */
	public void setOwnershipAssociatedIds(String[] ownershipAssociatedIds) {
		this.ownershipAssociatedIds = ownershipAssociatedIds;
	}
	
	
	public void resetForm()
	{
		this.protectionElementId = "";
		this.protectionElementName = "";
		this.protectionElementDescription = "";
		this.protectionElementType = "";
		this.protectionElementObjectId = "";
		this.protectionElementAttribute = "";
		this.protectionElementUpdateDate = "";
		this.associatedIds = null;
		this.ownershipAssociatedIds = null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.protectionElementName = "";
		this.protectionElementDescription = "";
		this.protectionElementType = "";
		this.protectionElementObjectId = "";
		this.protectionElementAttribute = "";
		this.associatedIds = null;
		this.ownershipAssociatedIds = null;
	}
	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", getProtectionElementName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Description", "protectionElementDescription", getProtectionElementDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Type", "protectionElementType", getProtectionElementType(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId", getProtectionElementObjectId(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", getProtectionElementAttribute(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", StringUtils.initString(getProtectionElementName()), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Description", "protectionElementDescription", StringUtils.initString(getProtectionElementDescription()), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Type", "protectionElementType", StringUtils.initString(getProtectionElementType()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId",StringUtils.initString(getProtectionElementObjectId()), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", StringUtils.initString(getProtectionElementAttribute()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Update Date", "protectionElementUpdateDate", StringUtils.initString(getProtectionElementUpdateDate()), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));

		return formElementList;
	}

	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", getProtectionElementName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Type", "protectionElementType", getProtectionElementType(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId", getProtectionElementObjectId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", getProtectionElementAttribute(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#getId()
	 */
	public String getPrimaryId()
	{
		if (this.protectionElementId == null)
		{
			return new String("");
		}
		else
		{
			return protectionElementId;
		}
	}
	
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = userProvisioningManager.getProtectionElementById(this.protectionElementId);

		this.protectionElementName = protectionElement.getProtectionElementName();
		this.protectionElementDescription = protectionElement.getProtectionElementDescription();
		this.protectionElementType = protectionElement.getProtectionElementType();
		this.protectionElementObjectId = protectionElement.getObjectId();
		this.protectionElementAttribute = protectionElement.getAttribute();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = null;
		
		if ((this.protectionElementId == null) || ((this.protectionElementId).equalsIgnoreCase("")))
		{
			protectionElement = new ProtectionElement();
		}
		else
		{
			protectionElement = userProvisioningManager.getProtectionElementById(this.protectionElementId);
		}

		protectionElement.setProtectionElementName(this.protectionElementName);
		protectionElement.setProtectionElementDescription(this.protectionElementDescription);
		protectionElement.setProtectionElementType(this.protectionElementType);
		protectionElement.setObjectId(this.protectionElementObjectId);
		protectionElement.setAttribute(this.protectionElementAttribute);
		
		
		if ((this.protectionElementId == null) || ((this.protectionElementId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createProtectionElement(protectionElement);
			this.protectionElementId = protectionElement.getProtectionElementId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		}
		else
		{
			protectionElement.setProtectionElementId(new Long(this.protectionElementId));
			userProvisioningManager.modifyProtectionElement(protectionElement);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeProtectionElement(this.protectionElementId);
		this.resetForm();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = new ProtectionElement();
		
		if (this.protectionElementName != null && !(this.protectionElementName.trim().equalsIgnoreCase("")))
			protectionElement.setProtectionElementName(this.protectionElementName);
		if (this.protectionElementType != null && !(this.protectionElementType.trim().equalsIgnoreCase("")))
			protectionElement.setProtectionElementType(this.protectionElementType);
		if (this.protectionElementObjectId != null && !(this.protectionElementObjectId.trim().equalsIgnoreCase("")))
			protectionElement.setObjectId(this.protectionElementObjectId);
		if (this.protectionElementAttribute != null && !(this.protectionElementAttribute.trim().equalsIgnoreCase("")))
			protectionElement.setAttribute(this.protectionElementAttribute);
		
		SearchCriteria searchCriteria = new ProtectionElementSearchCriteria(protectionElement);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildAssociationObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection associatedProtectionGroups = userProvisioningManager.getProtectionGroups(this.protectionElementId);
		
		ProtectionGroup protectionGroup = new ProtectionGroup();
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroups);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroups);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		if (this.associatedIds == null)
			this.associatedIds = new String[0];
		userProvisioningManager.assignToProtectionGroups(this.protectionElementId, this.associatedIds);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return DisplayConstants.PROTECTION_ELEMENT_ID;
	}	
	
}
