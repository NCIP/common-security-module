<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import='gov.nih.nci.security.upt.viewobjects.*'%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<%@page import="gov.nih.nci.security.authentication.LockoutManager"%><script>
<!--
   	function setAndSubmit(target)
   	{
		if(target == "update" || target == "create")
		{
			document.UserForm.operation.value=target;
			return false;
		}
   		if (target == "delete")
   		{
   			if (confirm("Are you sure you want to delete the record?"))
   			{
   				document.UserForm.operation.value=target;
				document.UserForm.submit();
				return false;
			}
		}
		else
		{
	  		document.UserForm.operation.value=target;
	  		document.UserForm.submit();
	  		return false;
	  	}
 	}
 	
function skipNavigation()
{
	document.getElementById("userDetail").focus();
	window.location.hash="userDetail";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	if(document.getElementById("homeLink"))
		document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
	if(document.getElementById("menuHome"))
		document.getElementById("menuHome").tabIndex = -1;
	if(document.getElementById("menuUser"))
		document.getElementById("menuUser").tabIndex = -1;
	if(document.getElementById("menuPE"))	
		document.getElementById("menuPE").tabIndex = -1;
	if(document.getElementById("menuPrivilege"))	
		document.getElementById("menuPrivilege").tabIndex = -1;
	if(document.getElementById("menuGroup"))
		document.getElementById("menuGroup").tabIndex = -1;
	if(document.getElementById("menuPG"))
		document.getElementById("menuPG").tabIndex = -1;
	if(document.getElementById("menuRole"))
		document.getElementById("menuRole").tabIndex = -1;
	if(document.getElementById("menuInstance"))
		document.getElementById("menuInstance").tabIndex = -1;
	if(document.getElementById("menulogout"))
		document.getElementById("menulogout").tabIndex = -1;

	if(document.getElementById("saHome"))
		document.getElementById("saHome").tabIndex = -1;
	if(document.getElementById("saApp"))	
		document.getElementById("saApp").tabIndex = -1;
	if(document.getElementById("saUser"))
		document.getElementById("saUser").tabIndex = -1;
	if(document.getElementById("saPriv"))
		document.getElementById("saPriv").tabIndex = -1;
	if(document.getElementById("saLogout"))
		document.getElementById("saLogout").tabIndex = -1;

}
 	
// -->
</script>
<s:set var="submitValue" value="error"/>
<s:set var="currentForm" value="#session.CURRENT_FORM"/>
<s:if test='#currentForm.getPrimaryId().eqauls("")'> 
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="create"/>
	</s:if>	
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="search"/>
	</s:if>	
</s:if>	
<s:else>
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="loadAdd"/>
	</s:if>	
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="loadSearchResult"/>
	</s:if>	
</s:else>	

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="UserForm" action="UserDBOperation" theme="simple">
	<s:hidden name="operation" value="%{submitValue}"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/UserDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="User detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td class="infoMessage" colspan="3">
								<s:if test="hasActionMessages()">
								      <s:actionmessage/>
								</s:if>			  
			  				</td>
						</tr>
						<tr>
							<td class="errorMessage" colspan="3">
								<s:if test="hasActionErrors()">
								      <s:actionerror/>
								</s:if>
							</td>
						</tr>
						<s:if test="#session.CURRENT_FORM != null">
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
								<tr>
										<td class="formMessage" colspan="3"><a id="userDetail"></a>Enter the details to add a new User. 
										The <b>User Login Name</b> uniquely identifies the User and is a required field. 
										The <b>User First Name</b> and <b>User Last Name</b> identifies the User. The <b>User Organization, User Department</b>
										 and <b>User Title</b> provides his work details. The <b>User Phone Number</b> and <b>User Email Id</b> provides the contact details for the User.
										The <b>User Password</b> can be entered if the same schema is also going to be used for Authentication.
										The <b>User Start Date</b> and <b>User End Date</b> determine the period for which the User is a valid User.</td>
									</tr>
									<tr>
										<td class="formMessage" colspan="3">* indicates a required field</td>
									</tr>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<tr>
										<td class="formMessage" colspan="3"><a id="userDetail"></a>Search for an existing User by entering the 
										<b>User Login Name, User First Name, User Last Name, User Organization, User Department</b> or <b>User Email Id</b>.</td>
									</tr>
									<tr>
										<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
									</tr>
								</s:if>
							</s:if>
							<s:else>
								<tr>
									<td class="formMessage" colspan="3"><a id="userDetail"></a>Update the details of the displayed User. 
									The <b>User Login Name</b> uniquely identifies the User and is a required field. 
									The <b>User First Name</b> and <b>User Last Name</b> identifies the User. The <b>User Organization, User Department</b>
									 and <b>User Title</b> provides his work details. The <b>User Phone Number</b> and <b>User Email Id</b> provides the contact details for the User.
									The <b>User Password</b> can be entered if the same schema is also going to be used for Authentication.
									The <b>User Start Date</b> and <b>User End Date</b> determine the period for which the User is a valid User.
									The <b>Update Date</b> indicates the date when this User's Details were last updated.</td>
								</tr>							
							</s:else>
						<tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE NEW USER DETAILS</td>	
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE USER SEARCH CRITERIA</td>
								</s:if>
							</s:if>
							<s:else>
								<td class="formTitle" height="20" colspan="3">USER DETAILS</td>
								<s:set var="userId" value="#currentForm.getUserId()"/>
								<s:hidden name="userForm.userId" value="%{userId}"/>
									
							</s:else>
						</tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>  
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<s:set var="formElements" value="#currentForm.searchFormElements"/>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<s:set var="formElements" value="#currentForm.addFormElements"/>
								</s:if>
							</s:if>
							<s:else>
								<s:set var="formElements" value="#currentForm.displayFormElements"/>
							</s:else>
							<s:iterator value="formElements" var="formElement">
							<s:set var="propertyValue" value="#formElement.propertyValue"/>
							<s:set var="propertyName" value="#formElement.propertyName"/>
							<s:set var="propertyDisabled" value="#formElement.propertyDisabled"/>
							<tr>
								<s:if test='(#formElement.propertyRequired.equals("REQUIRED"))'>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
								</s:if>
								<s:else>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
								</s:else>
								<s:if test='(#formElement.propertyType.equals("INPUT_BOX"))'>
									<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="userForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/></td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("PASSWORD"))'>
									<td class="formField"><s:password style="formFieldSized" size="30" maxlength="100" name="userForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" showPassword="true"/></td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_DATE"))'>
									<td class="formField">
									<s:if test='(#formElement.propertyReadonly.equals(DisplayConstants.READONLY))'>
										<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyValue"/>(MM/DD/YYYY)</label>
									</s:if>
									<s:else>
										<s:if test='#formElement.propertyDisabled'>
											<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MM/DD/YYYY)</label>
										</s:if>
										<s:else>
										<s:textfield size="10" maxlength="10"  name="userForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>  (MM/DD/YYYY)
										</s:else>										
									</s:else>
									</td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_TEXTAREA"))'>
									<td class="formField"><s:textarea style="formFieldSized" cols="32" rows="2" name="userForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" /></td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_RADIO"))'>
									<td class="formField"><s:radio name="userForm.%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="%{propertyValue}" /></td>
								</s:if>
							</tr>
							</s:iterator>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<s:if test='#currentForm.getPrimaryId().equals("")'> 
										<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('create');" value="Add"/></td>
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('search');" value="Search"/></td>
										</s:if>
										<td><s:reset style="actionButton" value="Reset"/></td>
										<td><s:submit style="actionButton" onclick="setAndSubmit('loadHome');" value="Back"/></td>
									</s:if>
									<s:else>
										<%
										  UserForm userForm = (UserForm)session.getAttribute("CURRENT_FORM");
										  boolean isUserLockOut = LockoutManager.getInstance().isUserLockedOut((String)userForm.getUserLoginName());
										%>
										<s:if test="isUserLockOut == true">
										 <td><s:submit style="actionButton" onclick="setAndSubmit('unlock');" value="Unlock"/></td>
										</s:if>
										<s:if test='#session.UPDATE_UPT_USER_OPERATION != null'>
										<td><s:submit style="actionButton" onclick="setAndSubmit('update');" value="Update"/></td>										
										</s:if>
										<s:else>
										<td><s:submit disabled="true">Update</s:submit></td>
										</s:else>
										<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadAdd');" value="Back"/></td>
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');" value="Back"/></td>
										</s:if>
										<s:if test='#session.ADMIN_USER == null'>
										</tr>
									</table>
									<table cellpadding="4" cellspacing="0" border="0">
										<tr>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadAssociation');" value="Associated Groups"/></td>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadProtectionElementPrivilegesAssociation');" value="Associated PE & Privileges"/></td>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadProtectionGroupAssociation');" value="Associated PG & Roles"/></td>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadDoubleAssociation');" value="Assign PG & Roles"/></td>
										</s:if>
									</s:else>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</s:if>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:form>
	</table>

