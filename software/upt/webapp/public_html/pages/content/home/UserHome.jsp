<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.UserForm.operation.value=target;
    		document.UserForm.submit();
    	}
    // -->
    
function skipNavigation()
{
	document.getElementById("userHome").focus();
	window.location.hash="userHome";
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
    
    </script>


<table summary="User Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">

<s:form name="UserForm" id="UserForm" action="/UserDBOperation" theme="simple">
	
		<s:hidden name="operation" id="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/UserDBOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>User</h2>

					<h3><a id="userHome"></a>User Home</h3>
					<s:if test="#session[DisplayConstants.ADMIN_USER] != null">
					<p>This is the User section of the Super Admin Mode of the User Provisioning Tool. In this section you may create new Users or modify exiting User details. 
					Then in the Application section you may assign that User to any Application. 
					Please begin by selecting either <b>Create a New User</b> or <b>Select an Existing User</b>.</p>
					</s:if>
					<s:else>
					<p>This is the User section of the User Provisioning Tool. A User
					is simply someone that requires access to your application. Users
					can have an associated Protection Group and Roles. <br>
					In this section you may create new Users, modify existing User
					details, and associate or deassociate Users with Protection Groups
					and Roles. Please begin by selecting either <b>Create a New User</b> or
					<b>Select an Existing User</b>.</p>
					</s:else>
					</td>
				</tr>
				<tr>
					<td valign="top" width="40%"><!-- sidebar begins -->
					<table cellpadding="0" cellspacing="0" border="0"
						height="100%">
						<tr><td><br></td></tr>
						<tr>
			  				<td class="infoMessage" colspan="3">
								<s:if test="hasActionMessages()">
								      <s:actionmessage/>
								</s:if>			  				
							</td>
						</tr>
						<tr><td><br></td></tr>
						<tr>
							<td valign="top">
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" height="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">USER LINKS</td>
								</tr>
								<s:if test="#session['CREATE_UPT_USER_OPERATION'] != null">
									<tr>
										<td class="sidebarContent"><a
											href="javascript: setAndSubmit('loadAdd')">Create a New User</a><br>
										Click to add a new user.</td>
									</tr>
								</s:if>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing User</a><br>
									Enter search criteria to find the user you wish to operate on.</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<td width="60%"></td>
				</tr>										
			</table>
			</td>
		</tr>

	
</s:form>

	</table>
