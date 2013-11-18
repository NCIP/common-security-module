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
		document.SystemConfigurationForm.operation.value=target;
		//document.SystemConfigurationForm.submit();
 	}
 	<!--
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
<s:set var="submitValue" value="error" />
	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="SystemConfigurationForm" action="SystemConfiguration" theme="simple">
	<s:hidden name="operation" id="operation" value="error"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SystemConfiguration'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>System Configuration</h2>

					<p>This is the System Configuration section of the UPT.  Here a Super Admin can edit configuration of the application to the UPT.</p>
					</td>
				</tr>
				<tr>
					<td>
					<table summary="System Configuration" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
							<td class="formTitle" height="20" colspan="3">SYSTEM CONFIGURATION</td>
						</tr>	
						<s:set var="configForm" value="#session.CURRENT_FORM"/>
						
						<s:iterator value="#configForm.formElementList" var="formElement" status="rowstatus">
							<tr>
								<s:set var="propertyValue" value="#formElement.propertyValue"/>
								<s:set var="propertyName" value="#formElement.propertyName"/>
								<s:set var="propertyDisabled" value="#formElement.propertyDisabled"/>
								<s:if test='#formElement.propertyRequired.equals("REQUIRED")'>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2">
										<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label>
									</td>
								</s:if>
								<s:else>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label for="<s:property value="#formElement.propertyName}"/>"><s:property value="#formElement.propertyLabel"/></label></td>
								</s:else>
								<s:if test='(#formElement.propertyType.equals("INPUT_BOX"))'>
									<td class="formField">
									<s:if test='(#formElement.propertyReadonly.equals("READONLY"))'>
										<s:hidden name="%{propertyName}" value="%{propertyValue}"/>
										<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label>
									</s:if>
									<s:else>
										<s:textfield size="30" maxlength="3000" name="%{propertyName}" value="%{propertyValue}" disabled="%{formElement.propertyDisabled}"/>
									</s:else>
									</td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("PASSWORD"))'>
									<td class="formField"><s:password style="formFieldSized" size="30" maxlength="100" name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/></td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_DATE"))'>
									<td class="formField">
									<s:if test='(#formElement.propertyReadonly.equals("READONLY"))'>
										<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/>(MM/DD/YYYY)</label>
									</s:if>
									<s:else>
										<s:if test='#formElement.propertyDisabled'>
											<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MM/DD/YYYY)</label>
										</s:if>
										<s:else>
										<s:textfield size="10" maxlength="10"  name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>  (MM/DD/YYYY)
										</s:else>										
									</s:else>
									</td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_TEXTAREA"))'>
									<td class="formField"><s:textarea style="formFieldSized" cols="32" rows="2" name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" /></td>
								</s:if>
								<s:if test='(#formElement.propertyType.equals("INPUT_RADIO"))'>
									<td class="formField"><s:radio style="formFieldSized" name="%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="YES" />&nbsp;Yes&nbsp;&nbsp;<s:radio style="formFieldSized" name="%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="NO" />&nbsp;No</td>
								</s:if>
							</tr>
						</s:iterator>
												
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td><s:submit style="actionButton" onclick="setAndSubmit('update');" value="Update" name="Back" /></td>									
									<td><s:submit style="actionButton" onclick="setAndSubmit('loadHome');" value="Back" name="Back" /></td>
								</tr>
							</table>
							</td><!-- action buttons end -->
					</table>
					</td>
				</tr>
				</s:form>
			</table>
			</td>
		</tr>
	</table>

