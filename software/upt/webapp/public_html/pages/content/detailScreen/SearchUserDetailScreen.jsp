<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<script>
<!--
// Anzen Comments(Added By Vijay)  -  Check Value Null values for all fileds Code Start
	function chkVal(){
	/*			if ( (document.UserForm.userLoginName.value == null || document.UserForm.userLoginName.value == "") &&
	   			 (document.UserForm.userFirstName.value == null || document.UserForm.userFirstName.value == "") &&
			   	 (document.UserForm.userLastName.value == null  || document.UserForm.userLastName.value == "") &&
			   	 (document.UserForm.userOrganization.value == null || document.UserForm.userOrganization.value == "") &&
			   	 (document.UserForm.userDepartment.value == null ||document.UserForm.userDepartment.value == "") &&
			   	 (document.UserForm.userEmailId.value == null || document.UserForm.userEmailId.value == "") )
		    {
		     	alert("Please enter some search criteria ");
		     	return false;

			}
		*/
            return true;
	  	}


   	function setAndSubmit(target)
   	{


   		if (target == "delete")
   		{
   			if (confirm("Are you sure you want to delete the record?"))
   			{
   				document.UserForm.operation.value=target;
				document.UserForm.submit();
			}
		}
		else
		{
			document.UserForm.operation.value=target;
			document.UserForm.submit();
		}

 	}

function skipNavigation()
{
	document.getElementById("userResult").focus();
	window.location.hash="userResult";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
		
	document.getElementById("menuHome").tabIndex = -1;
	document.getElementById("menuUser").tabIndex = -1;
	document.getElementById("menuPE").tabIndex = -1;
	document.getElementById("menuPrivilege").tabIndex = -1;
	document.getElementById("menuGroup").tabIndex = -1;
	document.getElementById("menuPG").tabIndex = -1;
	document.getElementById("menuRole").tabIndex = -1;
	document.getElementById("menuInstance").tabIndex = -1;
	document.getElementById("menulogout").tabIndex = -1;
} 	

// -->

</script>
<s:set var="submitValue" value="error"/>
<s:set var="currentForm" value="#session.CURRENT_FORM"/>
<s:if test='#currentForm.getPrimaryId().eqauls("")'>
	<s:if test='#session.CURRENT_ACTION.eqauls("SEARCH")'>
		<s:set var="submitValue" value="search"/>
	</s:if>	
</s:if>	
<s:else>
	<s:if test='#session.CURRENT_ACTION.eqauls("SEARCH")'>
		<s:set var="submitValue" value="loadSearchResult"/>
	</s:if>
</s:else>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="UserForm" action="/SearchUserDBOperation"  focus="userLoginName" theme="simple">
	<s:hidden name="operation" value="error"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SearchUserDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Search user detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
							<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<tr>
									<td class="formMessage" colspan="3"><a id="userResult"></a>Search for an existing User by entering the 
									<b>User Login Name, User First Name, User Last Name, User Organization, User Department</b> or <b>User Email Id</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</s:if>
							</s:if>
						<tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE USER SEARCH CRITERIA</td>
									<s:set var="formElements" value="#currentForm.searchFormElements"/>
								</s:if>
							</s:if>
							<s:else>
									<td class="formTitle" height="20" colspan="3">USER DETAILS</td>
									<s:set var="formElements" value="#currentForm.displayFormElements"/>
							</s:else>
						</tr>
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
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="if(chkVal()){setAndSubmit('search');}" value="Search"/></td>
										</s:if>
                                      
										<td><s:reset style="actionButton" value="Reset"/></td>
										<td><s:submit style="actionButton"  onclick="window.close();" value="Exit"/></td>
									</s:if>
									<s:else>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');" value="Back"/></td>
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


