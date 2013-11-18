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
<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.RoleForm.operation.value=target;
    		document.RoleForm.submit();
    	}
    	
function skipNavigation()
{
	document.getElementById("roleHome").focus();
	window.location.hash="roleHome";
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


	<table summary="Role Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<s:form name="RoleForm" action="RoleDBOperation" theme="simple">
		<s:hidden name="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/RoleDBOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Role</h2>

					<h3><a id="roleHome"></a>Role Home</h3>

					<p>This is the Role section of the User Provisioning Tool. A Role
					is simply a collection of application Privileges. By combining
					Privileges into a Role, it becomes easier to associate Users and
					Groups with rights to a particular data set. <br>
					In the User or Group portion of the UPT, you may assign Roles to Users. In this section you may create new Roles, modify existing
					Role details, and assign or deassign Privileges to the Role. Please
					begin by selecting either <b>Create a New Role</b> or <b>Select an Existing
					Role</b>.</p>
					</td>
				</tr>
				<tr>
					<td valign="top" width="40%"><!-- sidebar begins -->
					<table cellpadding="0" cellspacing="0" border="0"
						height="100%">
						<tr><td><br></td></tr>
						<tr>
			  				<td class="infoMessage">
								<s:if test="hasActionMessages()">
								   <div class="welcome">
								      <s:actionmessage/>
								   </div>
								</s:if>			  
							</td>
						</tr>
						<tr><td><br></td></tr>
						<tr>
							<td valign="top">
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">ROLE LINKS</td>
								</tr>
								<s:if test="#session.CREATE_UPT_ROLE_OPERATION != null">
									<tr>
										<td class="sidebarContent"><a
											href="javascript: setAndSubmit('loadAdd')">Create a New Role</a><br>
										Click to add a new role.</td>
									</tr>
								</s:if>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Role</a><br>
									Enter search criteria to find the role you wish to operate on.</td>
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



