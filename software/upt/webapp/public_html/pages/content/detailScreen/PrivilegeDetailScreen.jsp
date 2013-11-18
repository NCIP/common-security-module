<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@taglib uri="/struts-tags" prefix="s"%>

<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
   		if (target == "delete")
   		{
   			if (confirm("Are you sure you want to delete the record?"))
   			{
   				document.PrivilegeForm.operation.value=target;
				document.PrivilegeForm.submit();
				return false;
			}
		}
		else
		{
	  		document.PrivilegeForm.operation.value=target;
	  		document.PrivilegeForm.submit();
	  		return false;
	  	}
 	}
 	
function skipNavigation()
{
	document.getElementById("privDetail").focus();
	window.location.hash="privDetail";
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

<s:set var="submitValue" value="error" />
<s:set var="currentForm" value="#session.CURRENT_FORM"/>
<s:if test='#currentForm.getPrimaryId().equals("")'>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="search" />
	</s:if>
</s:if>
<s:else>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="loadSearchResult" />
	</s:if>
</s:else>


	<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="PrivilegeForm" action="PrivilegeDBOperation"  theme="simple">
	<s:hidden name="operation" value="error"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/PrivilegeDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Privilege Details" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
						<tr>
						
					<s:if test="#session.CURRENT_FORM != null">
						<s:if test='#currentForm.getPrimaryId().equals("")'>
							<s:if test="#session.ADMIN_USER != null">
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
										<tr>
											<td class="formMessage" colspan="3"><a id="privDetail"></a>Enter the details to add a new Privilege. 
											The <b>Privilege Name</b> uniquely identifies the Privilege and is a required field. 
											The <b>Privilege Description</b> is a brief summary about the Privilege.</td>
										</tr>
										<tr>
											<td class="formMessage" colspan="3">* indicates a required field</td>
										</tr>
								</s:if>
								<!--3.0.1-->
							</s:if>
								<!--3.0.1-->
							<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<tr>
									<td class="formMessage" colspan="3"><a id="privDetail"></a>Search for an existing Privilege by entering the <b>Privilege Name</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
							</s:if>
						</s:if>
						<s:else>
								<tr>
									<td class="formMessage" colspan="3"> <a id="privDetail"></a>
									The <b>Privilege Name</b> uniquely identifies the Privilege. 
									The <b>Privilege Description</b> is a brief summary about the Privilege. The <b>Update Date</b> indicates the date when this Privilege's Details were last updated.</td>
								</tr>							
						</s:else>
						</tr>
						<tr>
						<s:if test='#currentForm.getPrimaryId().equals("")'>
							<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<!--3.0.1-->
								<s:if test="#session.ADMIN_USER != null">
									  <td class="formTitle" height="20" colspan="3">ENTER THE NEW PRIVILEGE DETAILS</td>
								</s:if><!--3.0.1-->
							</s:if>
							<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<td class="formTitle" height="20" colspan="3">ENTER THE PRIVILEGE SEARCH CRITERIA</td>
							</s:if>
						</s:if>
						<s:else>
							<td class="formTitle" height="20" colspan="3">PRIVILEGE DETAILS</td>
						</s:else>
						</tr>
						<s:if test='#currentForm.getPrimaryId().equals("")'>
							<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<s:set var="formElements" value="#currentForm.searchFormElements"/>
							</s:if>
								<!--3.0.1-->
							<s:if test="#session.ADMIN_USER != null">
								<!--3.0.1-->
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<s:set var="formElements" value="#currentForm.addFormElements"/>
							</s:if>
								<!--3.0.1-->
						</s:if>
								<!--3.0.1-->
						</s:if>
						<s:else>
							<s:set var="formElements" value="#currentForm.displayFormElements"/>
							<s:set var="privilegeId" value="#currentForm.getPrimaryId()"/>
							<s:hidden name="privilegeForm.privilegeId" value="%{privilegeId}"/>
							
						</s:else>
						<s:iterator value="formElements" var="formElement">
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
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="privilegeForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/></td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("PASSWORD"))'>
										<td class="formField"><s:password style="formFieldSized" size="30" maxlength="100" name="privilegeForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" showPassword="true"/></td>
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
											<s:textfield size="10" maxlength="10"  name="privilegeForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>  (MM/DD/YYYY)
											</s:else>										
										</s:else>
										</td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_TEXTAREA"))'>
										<td class="formField"><s:textarea style="formFieldSized" cols="32" rows="2" name="privilegeForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" /></td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_RADIO"))'>
										<td class="formField"><s:radio name="privilegeForm.%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="%{propertyValue}" /></td>
									</s:if>
								</tr>
							</s:iterator>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<s:if test='#currentForm.getPrimaryId().equals("")'>
										<!--3.0.1-->
										<s:if test="#session.ADMIN_USER != null">
										<!--3.0.1-->
											<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
												<td><s:submit style="actionButton" onclick="setAndSubmit('create');" value="Add"/></td>
											</s:if>
										<!--3.0.1-->
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('search');" value="Search"/></td>
										</s:if>
										
										<!--3.0.1-->
										<s:if test="#session.ADMIN_USER != null">
										<!--3.0.1-->
											<td><s:reset style="actionButton" value="Reset"/></td>
										<!--3.0.1-->
										</s:if>										
										<td><s:submit style="actionButton" onclick="setAndSubmit('loadHome');" value="Back"/></td>
									</s:if>
									<s:else>
										<!--3.0.1-->
										<s:if test="#session.ADMIN_USER != null">
										<!--3.0.1-->
											<s:if test="#session.UPDATE_UPT_PRIVILEGE_OPERATION != null">
												<td><s:submit style="actionButton" onclick="setAndSubmit('update');" value="Update"/></td>
											</s:if>
											<s:else>
												<td><s:submit disabled="true" value="Update"/></td>
											</s:else>
											<s:if test="#session.DELETE_UPT_PRIVILEGE_OPERATION != null">
												<td><s:submit class="actionButton" onclick="setAndSubmit('delete');" value="Delete"/></td>
											</s:if>
											<s:else>
												<td><s:submit disabled="true" value="Delete"/></td>
											</s:else>
											<!--3.0.1-->
										</s:if>

										<!--3.0.1-->
										<s:if test="#session.ADMIN_USER != null">
										<!--3.0.1-->
											<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
												<td><s:submit style="actionButton" onclick="setAndSubmit('loadAdd');" value="Back"/></td>
											</s:if>
										<!--3.0.1-->
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');" value="Back"/></td>
										</s:if>
									</s:else>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</tr>
						</s:if>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:form>
	</table>

