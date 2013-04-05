<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
   		if (target == "delete")
   		{
   			if (confirm("Are you sure you want to delete the record?"))
   			{
   				document.ApplicationForm.operation.value=target;
				document.ApplicationForm.submit();
				return false;
			}
		}
		else
		{
	  		document.ApplicationForm.operation.value=target;
	  		document.ApplicationForm.submit();
	  		return false;
	  	}
 	}
 	
function skipNavigation()
{
	document.getElementById("appDetail").focus();
	window.location.hash="appDetail";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	if(document.getElementById("homeLink"))
		document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
		
	document.getElementById("saHome").tabIndex = -1;
	document.getElementById("saApp").tabIndex = -1;
	document.getElementById("saUser").tabIndex = -1;
	document.getElementById("saPriv").tabIndex = -1;
	document.getElementById("saLogout").tabIndex = -1;
}
 	
// -->
</script>
<bean:define id="submitValue" value="error" />
<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
		<bean:define id="submitValue" value="create" />
	</logic:equal>
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="search" />
	</logic:equal>
</logic:equal>
<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
		<bean:define id="submitValue" value="loadAdd" />
	</logic:equal>
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="loadSearchResult" />
	</logic:equal>
</logic:notEqual>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="ApplicationForm" action="/ApplicationDBOperation">
	<html:hidden property="operation" value="<%=submitValue%>"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Application detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
						<logic:present name="<%=DisplayConstants.CURRENT_FORM%>">
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="appDetail"></a>Enter the details to add a new Application. 
									The <b>Application Name</b> uniquely identifies	the Application and is a required field. 
									The <b>Application Description</b> is a brief summary about the application. The <b>Application Declarative Flag</b> 
									indicates whether or not the application uses Declarative security. The <b>Application Active Flag</b> indicates if the
									 Application is currently active.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">* indicates a required field</td>
								</tr>
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="appDetail"></a>Search for an existing application by entering the <b>Application Name</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="appDetail"></a>Update the details of the displayed Application. 
									The <b>Application Name</b> uniquely identifies	the Application and is a required field. 
									The <b>Application Description</b> is a brief summary about the application. The <b>Application Declarative Flag</b> 
									indicates whether application uses Declarative Security or not. The <b>Application Active Flag</b> indicates if the
									 Application is currently active or not. The <b>Update Date</b> indicates the date when this Application's Details were last updated.</td>
								</tr>							
							</logic:notEqual>
						</tr>
						<tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
									<td class="formTitle" height="20" colspan="3">ENTER THE NEW APPLICATION DETAILS</td>
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<td class="formTitle" height="20" colspan="3">ENTER THE APPLICATION SEARCH CRITERIA</td>
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									<td class="formTitle" height="20" colspan="3">APPLICATION DETAILS</td>
							</logic:notEqual>
						</tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="searchFormElements" id="formElements" />
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
									<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="addFormElements" id="formElements" />
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="displayFormElements" id="formElements" />
							</logic:notEqual>
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
										<td class="formField"><html:text style="formFieldSized" size="30" maxlength="100" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/></td>
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
						<tr>
							<td class="formMessage" colspan="3">
								<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
											# Required to fill out either all or none of the database related fields.
									</logic:equal>
								</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									# Required to fill out either all or none of the database related fields.
							</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
										
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('create');">Add</html:submit></td>
										</logic:equal>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('search');">Search</html:submit></td>
										</logic:equal>
										<td><html:reset style="actionButton">Reset</html:reset></td>
										<td><html:submit style="actionButton" onclick="setAndSubmit('loadHome');">Back</html:submit></td>
									</logic:equal>
									<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
										<td><html:submit style="actionButton" onclick="setAndSubmit('testConnection');">Test Connection</html:submit></td>
										<td><html:submit style="actionButton" onclick="setAndSubmit('update');">Update</html:submit></td>
										<td><button class="actionButton" onclick="setAndSubmit('delete');">Delete</button></td>
										<td><html:submit style="actionButton" onclick="setAndSubmit('loadAssociation');">Associated Admins</html:submit></td>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('loadAdd');">Back</html:submit></td>
										</logic:equal>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');">Back</html:submit></td>
										</logic:equal>
										
									</logic:notEqual>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</logic:present>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</html:form>
	</table>

