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
    		document.GroupForm.operation.value=target;
    		document.GroupForm.submit();
    	}
    	
function skipNavigation()
{
	document.getElementById("groupHome").focus();
	window.location.hash="groupHome";
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


	<table summary="Group Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<s:form name="GroupForm" action="GroupDBOperation" theme="simple">
		<s:hidden name="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/GroupDBOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Group</h2>

					<h3><a id="groupHome"></a>Group Home</h3>

					<p>This is the Group section of the User Provisioning Tool. A Group
					is simply a collection of application users. By combining users
					into a Group, it becomes easier to manage their collective roles
					and access rights in your application. Simply select an existing
					group, and associate a new Protection Group and Roles. Upon doing
					so, everyone in that particular Group has the same rights. <br>
					Under the User portion of UPT you may assign users to Groups. In
					this section you may create new Groups, modify existing Group
					details, and associate or deassociate Groups' Protection Groups and
					Roles. Please begin by selecting either <b>Create a New Group</b> or
					<b>Select an Existing Group</b>.</p>
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

									<td class="sidebarTitle" height="20">GROUP LINKS</td>
								</tr>
								<s:if test="#session.CREATE_UPT_GROUP_OPERATION != null">
									<tr>
										<td class="sidebarContent"><a
											href="javascript: setAndSubmit('loadAdd')">Create a New Group</a><br>
										Click to add a new group.</td>
									</tr>
								</s:if>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Group</a><br>
									Enter search criteria to find the group you wish to operate on.</td>
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



