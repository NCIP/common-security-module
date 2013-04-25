<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.LoginForm"%>
<%@ page import="gov.nih.nci.security.UserProvisioningManager"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>

<script>
function skipNavigation()
{
	//window.location.hash.refresh();
	document.getElementById("uptHeader").focus();
	window.location.hash="uptHeader";
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
</script>


	<table summary="User Provisioning Tool Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2  id="UPT"><a id="uptHeader"></a>User Provisioning Tool</h2>

					<h3>Welcome!</h3>

					<p>Welcome to the User Provisioning Tool. You may begin by clicking
					on any menu option above, or you can follow our Recommended
					Workflow outlined below.<br>This diagram shows how UPT security
					objects are related.  Roll over objects to learn more. </p>
					</td>
					<td width="30%">&nbsp;</td>
					<tr><td colspan="4">&nbsp;</td></tr>
				</tr>


<%
	String urlStr = request.getRequestURL().toString();
	urlStr = urlStr.substring(0, urlStr.lastIndexOf("/"));
	urlStr = urlStr.substring(0, urlStr.lastIndexOf("/"));
	LoginForm form = (LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT);
%>

				<!--
				<!-- diagram -->

				<tr>
					<td colspan="2" width="50%">

					<table cellspacing="0" cellpadding="0">
						<tr>
							<td height="50%">
							<table width="50%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_USER_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.USER_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('user','','images/Users.gif',1)" id="userA" tabindex=1><img
										name="user" border="0" src="images/Users2.gif" width="98"
										alt="User"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Application User"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_USER_OPERATION%>'>
									<td width="100%" height="50"><img
										name="user" border="0" src="images/Users2.gif" width="98"
										alt="User"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Application User"></td>
								</logic:notPresent>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PROTECTION_ELEMENT_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('PEs','','images/protectionelements.gif',1)" tabindex=2><img
										name="PEs" border="0" src="images/protectionelements2.gif"
										alt="Protection Element"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Protection Element"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PROTECTION_ELEMENT_OPERATION%>'>
									<td width="100%" height="50"><img
										name="PEs" border="0" src="images/protectionelements2.gif"
										alt="Protection Element"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Protection Element"></td>
								</logic:notPresent>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PRIVILEGE_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('priv','','images/privileges.gif',1)" tabindex=3><img
										name="priv" border="0" src="images/privileges2.gif" width="98"
										alt="Privilege"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Privilege"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PRIVILEGE_OPERATION%>'>
									<td width="100%" height="50"><img
										name="priv" border="0" src="images/privileges2.gif" width="98"
										alt="Privilege"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Privilege"></td>
								</logic:notPresent>
								</tr>
								<tr>
									<td width="100%" height="50"><img src="images/create.gif" alt="Create" 
										width="98" height="50"></td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table
								background="<html:rewrite href="images/dotted_line1.gif"/>"
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="100"><img src="images/elbow.gif" width="98"
										height="100" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>

								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_GROUP_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('group','','images/groups.gif',1)" tabindex=4><img
										name="group" border="0" src="images/groups2.gif" width="98"
										alt="Group"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Group"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_GROUP_OPERATION%>'>
									<td width="100%" height="50"><img name="group" border="0" src="images/groups2.gif" width="98"
										alt="Group" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Group"></td>
								</logic:notPresent>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PROTECTION_GROUP_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('pgs','','images/protectiongroups.gif',1)" tabindex=5><img
										name="pgs" border="0" src="images/protectiongroups2.gif"
										alt="Protectoin Group"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Protection Group"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_PROTECTION_GROUP_OPERATION%>'>
									<td width="100%" height="50"><img
										name="pgs" border="0" src="images/protectiongroups2.gif"
										alt="Protectoin Group"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Protection Group"></td>
								</logic:notPresent>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<logic:present name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_ROLE_OPERATION%>'>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.ROLE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('roles','','images/roles.gif',1)" tabindex=6><img
										name="roles" border="0" src="images/roles2.gif" width="98"
										alt="Role"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Role"></a></td>
								</logic:present>
								<logic:notPresent name='<%=Constants.CSM_ACCESS_PRIVILEGE +"_"+Constants.UPT_ROLE_OPERATION%>'>
									<td width="100%" height="50"><img
										name="roles" border="0" src="images/roles2.gif" width="98"
										alt="Role"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Role"></td>
								</logic:notPresent>
								</tr>

								<tr>
									<td width="100%" height="50"><img src="images/assign.gif"
										width="98" height="50" alt=""></td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table
								background="<html:rewrite href="images/dotted_line2.gif"/>"
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>

								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
							</table>
							</td>
							<td valign="top" height="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td valign="top" height="100%"><img src="images/assoc_block2.gif" width="98" height="300" alt="Final Association" longdesc="<%=urlStr%>/content/home/longdescription.html" /></td>
								</tr>

								<tr>
									<td width="100%" height="50"><img src="images/associate.gif"
										width="98" height="50" alt="Associate"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					</td>
					
					<td colspan="2" width="50%">&nbsp;</td>
				</tr>

				<tr class="home">
					<td class="home" colspan="4">&nbsp;</td>
				</tr>




				<!-- workflow begins-->
				<tr>

					<td colspan="4">
					<table cellpadding="0" cellspacing="0" border="0"
						class="contentBegins">
						<!--recommended flow-->
						<tr class="home">
							<td class="home" colspan="3">
							<h3>Recommended Workflow</h3>
							<p>When initially adding an application, we recommend the
							following steps:</p>

							</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>

						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>

						<tr class="home">
							<td class="home" colspan="3">1. Create base objects - <b>Users</b> and
							<b>Protection</b> <b>Elements</b>. <b>CSM Standard Privileges</b> are provided.</td>
							<td class="home" width="25%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="3" align="center">
							<div align="left">2. Create collections of these objects.</div>
							</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">&nbsp;</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!-- Section A -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
				
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Users</b> to <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Section B -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Protection Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Protection Elements</b> to
							<b>Protection Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Section C -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Roles</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Privileges</b> to <b>Roles</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
			
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="3">3. Associate rights with <b>Users</b>
							and <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Step 2 A -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign a <b>Protection Group</b> and
							<b>Roles</b> to <b>Users</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">&nbsp;</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign a <b>Protection Group</b> and
							<b>Roles</b> to <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
					</table>
				</td>
				</tr>
				<!-- workflow ends, association begins -->
			</table>
			</td>
		</tr>
	</table>

