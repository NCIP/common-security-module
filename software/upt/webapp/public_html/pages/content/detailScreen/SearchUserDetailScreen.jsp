<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

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
<bean:define id="submitValue" value="error" />
<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="search" />
	</logic:equal>
</logic:equal>
<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="loadSearchResult" />
	</logic:equal>
</logic:notEqual>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="UserForm" action="/SearchUserDBOperation"  focus="userLoginName">
	<html:hidden property="operation" value="<%=submitValue%>"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SearchUserDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Search user detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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

						<logic:present name="<%=DisplayConstants.CURRENT_FORM%>">

							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="userResult"></a>Search for an existing User by entering the 
									<b>User Login Name, User First Name, User Last Name, User Organization, User Department</b> or <b>User Email Id</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</logic:equal>
							</logic:equal>
						<tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<td class="formTitle" height="20" colspan="3">ENTER THE USER SEARCH CRITERIA</td>
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									<td class="formTitle" height="20" colspan="3">USER DETAILS</td>
							</logic:notEqual>
						</tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="searchFormElements" id="formElements" />
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
										<td class="formField"><html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>"  property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.YES%>" />&nbsp;Yes&nbsp;&nbsp;<html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.NO%>" />&nbsp;No</td>
									</logic:equal>
								</tr>
							</logic:iterate>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									
									<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									
                                      
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="if(chkVal()){setAndSubmit('search');}">Search</html:submit></td>
										</logic:equal>
                                      
										<td><html:reset style="actionButton">Reset</html:reset></td>
										<td><html:button property="action" onclick="window.close();">Exit</html:button></td>
									</logic:equal>
									<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');">Back</html:submit></td>
										</logic:equal>
										
									</logic:notEqual>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</logic:present>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</html:form>
	</table>


