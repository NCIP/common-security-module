
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

  <!--
    	
//-->

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
	UserProvisioningManager upm = (UserProvisioningManager)session.getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

	Boolean isUserEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_USERS_LINK) == null)
	{
		isUserEnabled = upm.checkLinkAccessible(Constants.UPT_UI_USERS_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_USERS_LINK, isUserEnabled);
	}
	else
		isUserEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_USERS_LINK);
		

	Boolean isPEEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_PROTECTION_ELEMENTS_LINK) == null)
	{
		isPEEnabled = upm.checkLinkAccessible(Constants.UPT_UI_PROTECTION_ELEMENTS_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_PROTECTION_ELEMENTS_LINK, isPEEnabled);
	}
	else
		isPEEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_PROTECTION_ELEMENTS_LINK);

	Boolean isPrivEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_PRIVILEGES_LINK) == null)
	{
		isPrivEnabled = upm.checkLinkAccessible(Constants.UPT_UI_PRIVILEGES_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_PRIVILEGES_LINK, isPrivEnabled);
	}
	else
		isPrivEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_PRIVILEGES_LINK);
		
	Boolean isPGEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_PROTECTION_GROUPS_LINK) == null)
	{
		isPGEnabled = upm.checkLinkAccessible(Constants.UPT_UI_PROTECTION_GROUPS_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_PROTECTION_GROUPS_LINK, isPGEnabled);
	}
	else
		isPGEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_PROTECTION_GROUPS_LINK);
		
	Boolean isGroupEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_GROUPS_LINK) == null)
	{
		isGroupEnabled = upm.checkLinkAccessible(Constants.UPT_UI_GROUPS_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_GROUPS_LINK, isGroupEnabled);
	}
	else
		isGroupEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_GROUPS_LINK);
		
	Boolean isRoleEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_ROLE_LINK) == null)
	{
		isRoleEnabled = upm.checkLinkAccessible(Constants.UPT_UI_ROLE_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_ROLE_LINK, isRoleEnabled);
	}
	else
		isRoleEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_ROLE_LINK);
		
	Boolean isILSEnabled = Boolean.TRUE;
	if(session.getAttribute(Constants.UPT_UI_INSTANCE_LEVEL_LINK) == null)
	{
		isILSEnabled = upm.checkLinkAccessible(Constants.UPT_UI_INSTANCE_LEVEL_LINK, form.getLoginId(), form.getApplicationContextName());
		session.setAttribute(Constants.UPT_UI_INSTANCE_LEVEL_LINK, isILSEnabled);
	}
	else
		isILSEnabled = (Boolean)session.getAttribute(Constants.UPT_UI_INSTANCE_LEVEL_LINK);
	

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
								<%if(isUserEnabled.booleanValue())
								{%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.USER_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('user','','images/Users.gif',1)" id="userA" tabindex=1><img
										name="user" border="0" src="images/Users2.gif" width="98"
										alt="User"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Application User"></a></td>
								<%
								} else {
								%>
									<td width="100%" height="50"><img
										name="user" border="0" src="images/Users2.gif" width="98"
										alt="User"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Application User"></td>
								
								<%}%>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<%if(isPEEnabled.booleanValue())
								{%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('PEs','','images/protectionelements.gif',1)" tabindex=2><img
										name="PEs" border="0" src="images/protectionelements2.gif"
										alt="Protection Element"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Protection Element"></a></td>
								<%} else {
								%>
									<td width="100%" height="50"><img
										name="PEs" border="0" src="images/protectionelements2.gif"
										alt="Protection Element"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Protection Element"></td>

								<%}%>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<%if(isPrivEnabled.booleanValue())
								{%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('priv','','images/privileges.gif',1)" tabindex=3><img
										name="priv" border="0" src="images/privileges2.gif" width="98"
										alt="Privilege"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Privilege"></a></td>
								<%} else {
								%>
									<td width="100%" height="50"><img
										name="priv" border="0" src="images/privileges2.gif" width="98"
										alt="Privilege"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										Title="Privilege"></td>
								<%}%>
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
								<%
								if(isGroupEnabled.booleanValue())
								{
								%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('group','','images/groups.gif',1)" tabindex=4><img
										name="group" border="0" src="images/groups2.gif" width="98"
										alt="Group"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Group"></a></td>
								<%
								}
								else
								{
								%>
									<td width="100%" height="50"><img name="group" border="0" src="images/groups2.gif" width="98"
										alt="Group" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Group"></td>

								<%}%>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50" alt=""></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<%
								if(isPGEnabled.booleanValue())
								{
								%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('pgs','','images/protectiongroups.gif',1)" tabindex=5><img
										name="pgs" border="0" src="images/protectiongroups2.gif"
										alt="Protectoin Group"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Protection Group"></a></td>
								<%} else {%>
									<td width="100%" height="50"><img
										name="pgs" border="0" src="images/protectiongroups2.gif"
										alt="Protectoin Group"
										width="98" height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Protection Group"></td>
								<%}%>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
								<%
								if(isRoleEnabled.booleanValue())
								{
								%>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.ROLE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('roles','','images/roles.gif',1)" tabindex=6><img
										name="roles" border="0" src="images/roles2.gif" width="98"
										alt="Role"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Role"></a></td>
								<%} else{ %>
									<td width="100%" height="50"><img
										name="roles" border="0" src="images/roles2.gif" width="98"
										alt="Role"
										height="50" longdesc="<%=urlStr%>/content/home/longdescription.html"
										title="Role"></td>
								<%}%>
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

