<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<script>
    	
function skipNavigation()
{
	document.getElementById("saHomeHeader").focus();
	window.location.hash="saHomeHeader";
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
    	
  
</script>



	<table summary="SuperAdmin Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="2">

					<h2>User Provisioning Tool</h2>

					<h3><a id="saHomeHeader"></a>Welcome Super Admin!</h3>

					<p>Welcome to the User Provisioning Tool (UPT). This user interface
					tool is designed so that developers can easily configure an
					application's authorization data in the Common Security Module
					(CSM) database. As a Super Admin, you may add, remove, or modify
					Application details. You can assign Administrators (Users) to these
					Applications. Also, you may create or modify the Privileges that an Admin may assign to application users. Please begin by clicking on
					one of the menu subsections above or the links below.</p>
					</td>
				</tr>
				<!-- new box for menu items -->
				<tr>
<td valign="top" width="40%"><!-- sidebar begins -->
<table cellpadding="0" cellspacing="0" border="0"
						height="100%">
<tr><td><br></td></tr>
<tr>

						</tr>
						<tr><td><br></td></tr>
						<tr>
							<td valign="top">


<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">MENU LINKS</td>
								</tr>
								<tr><td class="sidebarContent">
								<a href="javascript: set('<%=DisplayConstants.APPLICATION_ID%>')">Application</a><br />
					Applications that use CSM for security.
					</td></tr>
					<tr><td class="sidebarContent">
					<a href="javascript: set('<%=DisplayConstants.USER_ID%>')">User</a><br />
					Users who serve as UPT Administrators.
					</td></tr>
					<tr><td class="sidebarContent">
					<a href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')">Privilege</a><br />
					Standard privileges that may be assigned to application users.
					</td></tr>
								
								
								
</table>
</td>
</tr>
</table>
</td>
</tr>
<tr><td>&nbsp;</td></tr>
				
				<!-- end new box for menu items -->
				
				
				<!-- old links 
				<tr><td colspan="2"><br></td></tr>
				<tr>

					<td>&nbsp;</td>
					<td width="60%">

					<p><a
						href="javascript: set('<%=DisplayConstants.APPLICATION_ID%>')">Application</a><br />
					Applications that use CSM for security.</p>
					<p><a href="javascript: set('<%=DisplayConstants.USER_ID%>')">User</a><br />
					Users can serve as UPT Administrators.</p>
					<p><a href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')">Privilege</a><br />
					Standard privileges made available to applications.</p>
					<p>&nbsp;</p>
					</td>
					<td width="30%">&nbsp;</td>
				</tr>-->
				
			</table>
			</td>
		</tr>
	</table>

