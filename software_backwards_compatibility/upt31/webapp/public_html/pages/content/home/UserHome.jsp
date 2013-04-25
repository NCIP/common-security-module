<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.UserForm.operation.value=target;
    		document.UserForm.submit();
    	}
    // -->
    </script>


<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">

<html:form styleId="UserForm" action="/UserDBOperation">
	
		<html:hidden property="operation" value="error" />
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>User</h2>

					<h3>User Home</h3>
					<logic:notPresent name="<%=DisplayConstants.ADMIN_USER%>">
					<p>This is the User section of the User Provisioning Tool. A User
					is simply someone that requires access to your application. Users
					can have an associated Protection Group and Roles. <br>
					In this section you may create new Users, modify existing User
					details, and associate or deassociate Users with Protection Groups
					and Roles. Please begin by selecting either <b>Create a New User</b> or
					<b>Select an Existing User</b>.</p>
					</logic:notPresent>
					<logic:present name="<%=DisplayConstants.ADMIN_USER%>">
					<p>This is the User section of the Super Admin Mode of the User Provisioning Tool. In this section you may create new Users or modify exiting User details. 
					Then in the Application section you may assign that User to any Application. 
					Please begin by selecting either <b>Create a New User</b> or <b>Select an Existing User</b>.</p>
					</logic:present>
					</td>
				</tr>
				<tr>
					<td valign="top" width="40%"><!-- sidebar begins -->
					<table summary="" cellpadding="0" cellspacing="0" border="0"
						height="100%">
						<tr><td><br></td></tr>
						<tr>
			  				<td class="infoMessage"><html:messages id="message" message="true">
								<bean:write name="message" />
							</html:messages></td>
						</tr>
						<tr><td><br></td></tr>
						<tr>
							<td valign="top">
							<table summary="" cellpadding="0" cellspacing="0" border="0"
								width="100%" height="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">USER LINKS</td>
								</tr>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadAdd')">Create a New User</a><br>
									Click to add a new user.</td>
								</tr>
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

	
</html:form>

	</table>
