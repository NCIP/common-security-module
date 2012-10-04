<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
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
		document.SystemConfigurationForm.submit();
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
<bean:define id="submitValue" value="error" />
	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="SystemConfigurationForm" action="/SystemConfiguration">
	<html:hidden property="operation" value="<%=submitValue%>"/>
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
			  				<html:messages id="message" message="true">
			  				<bean:write name="message"/>
			  				</html:messages>	
			  				</td>
						</tr>
						<tr>
							<td colspan="3">
							<html:errors />
							</td>
						</tr>
						<tr>
							<td class="formTitle" height="20" colspan="3">SYSTEM CONFIGURATION</td>
						</tr>							
						<bean:define name="SystemConfigurationForm" property="displayFormElements" id="formElements" />

						<logic:iterate name="formElements" id="formElement" type="FormElement">
							<tr>
								<logic:equal name="formElement" property="propertyRequired" value="<%=DisplayConstants.REQUIRED%>">
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyLabel" /></label></td>
								</logic:equal>
								<logic:notEqual name="formElement" property="propertyRequired" value="<%=DisplayConstants.REQUIRED%>">
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyLabel" /></label></td>
								</logic:notEqual>
								<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_BOX%>">
									<td class="formField">
									<logic:equal name="formElement" property="propertyReadonly" value="<%=DisplayConstants.READONLY%>">
										<html:hidden property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>"/>
										<label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" /></label>
									</logic:equal>
									<logic:notEqual name="formElement" property="propertyReadonly"  value="<%=DisplayConstants.READONLY%>">
										<html:text  style="formFieldSized" size="30" maxlength="100" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/>
									</logic:notEqual>
									</td>
								</logic:equal>
								<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.PASSWORD%>">
									<td class="formField"><html:password style="formFieldSized" size="30" maxlength="100" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/></td>
								</logic:equal>
								<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_DATE%>">
									<td class="formField">
									<logic:equal name="formElement" property="propertyReadonly" value="<%=DisplayConstants.READONLY%>">
										<label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />   <%=DisplayConstants.DISPLAY_DATE_FORMAT%></label>
									</logic:equal>
									<logic:notEqual name="formElement" property="propertyReadonly"  value="<%=DisplayConstants.READONLY%>">
										<% if(formElement.getPropertyDisabled()){ %>
											<label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DisplayConstants.DISPLAY_DATE_FORMAT%></label>
										<% }else{ %>
										<html:text  style="formFieldSized" size="10" maxlength="10" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/>  <%=DisplayConstants.DISPLAY_DATE_FORMAT%>
										<% } %>											
									</logic:notEqual>
									</td>
								</logic:equal>
								<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_TEXTAREA%>">
									<td class="formField"><html:textarea style="formFieldSized" cols="32" rows="2" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>" /></td>
								</logic:equal>
								<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_RADIO%>">
									<td class="formField"><html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.YES%>" />&nbsp;Yes&nbsp;&nbsp;<html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.NO%>" />&nbsp;No</td>
								</logic:equal>
							</tr>
						</logic:iterate>
												
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td><html:submit style="actionButton" onclick="setAndSubmit('update');">Update</html:submit></td>									
									<td><html:submit style="actionButton" onclick="setAndSubmit('loadHome');">Back</html:submit></td>
								</tr>
							</table>
							</td><!-- action buttons end -->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</html:form>
	</table>

