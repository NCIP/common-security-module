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

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<%@page import="gov.nih.nci.security.authentication.LockoutManager"%><script>

   	function setAndSubmit(target)
   	{
		document.AppUserForm.operation.value=target;
		document.AppUserForm.submit();
 	}
	
</script>
<s:set var="submitValue" value="error"/>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="AppUserForm" action="AppUserLogin" theme="simple">	
	<s:hidden name="userForm.operation" value="update"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/AppUserLogin'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>User Details</h2>

					<p>This is the User Detail section of the UPT.  Here a User can edit his personal information or change the password.</p>
					</td>
				</tr>
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
							<td class="infoMessage" colspan="3">
								<s:if test="hasActionErrors()">
								      <s:actionerror/>
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="formTitle" height="20" colspan="3">USER DETAILS</td>
						</tr>
							
						<s:set var="formElements" value="#session.CURRENT_FORM"/>
						<s:set var="userId" value="#formElements.getPrimaryId()"/>
						<s:hidden name="userForm.userId" value="%{userId}"/>
						<s:iterator value="%{#formElements.displayFormElements}" var="formElement">
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
								<td><s:submit style="actionButton" onclick="setAndSubmit('update');" value="Update"/></td>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</tr>	
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:form>
	</table>

