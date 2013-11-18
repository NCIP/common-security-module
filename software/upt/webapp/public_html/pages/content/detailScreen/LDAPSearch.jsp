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
	function enableAuth()
	{
		if(document.LDAPSearchForm.securityAuthentication.value == "Simple")
		{
			document.LDAPSearchForm.securityPrincipal.disabled = false;
			document.LDAPSearchForm.securityCredentials.disabled = false;
		}
		else
		{
			document.LDAPSearchForm.securityPrincipal.value = "";
			document.LDAPSearchForm.securityCredentials.value = "";
			document.LDAPSearchForm.securityPrincipal.disabled = true;
			document.LDAPSearchForm.securityCredentials.disabled = true;
		}
	}
	
   	function setAndSubmit(target)
   	{
		if(document.LDAPSearchForm.securityAuthentication.value == "-1")
		{
			alert("Please select Security Authentication Type");
			return false;
		}
		document.LDAPSearchForm.operation.value=target;
		document.LDAPSearchForm.submit();
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

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="LDAPSearchForm" action="LDAPImport" theme="simple">
	<s:hidden name="operation" value="error"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Application detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
								<tr>
									<td class="formMessage" colspan="3"><a id="appDetail"></a>Search for LDAP users by entering the following criteria.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Search filter example: ou=NCI,o=NIH</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Mapping with CSM User example &lt;ldap attr:csm attr&gt;: uid=UserId,sn=LastName,givenname=FirstName</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Valid CSM User attribute for mapping are: UserId, FirstName, LastName, Org, Dept, Title, Phone, Email</td>
								</tr>
						</tr>
						<tr>
							<td class="formTitle" height="20" colspan="3">ENTER THE LDAP SERVER CONNECTION AND SEARCH DETAILS</td>
						</tr>
								<tr>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="providerURL">Provider URL:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="providerURL"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Security Authentication Type:</label></td>
										<td class="formField">
										<s:select label="Security Authentication Type:" headerKey="-1" headerValue="Select Type" onchange="enableAuth()" list="#{'None':'None', 'Simple':'Simple'}" name="securityAuthentication"/>
										</td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Security Principal:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="securityPrincipal" disabled="true"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Security Password:</label></td>
										<td class="formField"><s:password style="formFieldSized" size="30" maxlength="100" name="securityCredentials" disabled="true"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Login Id:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="loginId"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">First Name:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="firstName"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">last Name:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="lastName"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Email:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="email"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Additional Search Filter:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="searchFilter"/></td>
								</tr>
								<tr>
										<td class="formRequiredNotice" width="5"></td>
										<td class="formRequiredLabel2"><label for="InitialContextFactory">Mapping with CSM User:</label></td>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="mappingWithCSM"/></td>
								</tr>
						<tr>
						</tr>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td><s:submit style="actionButton" onclick="setAndSubmit('search');" value="Search"/></td>
									<td><s:reset style="actionButton" value="Reset"/></td>
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

