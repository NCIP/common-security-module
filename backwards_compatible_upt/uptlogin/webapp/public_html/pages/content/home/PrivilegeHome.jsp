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

<%@ page import="gov.nih.nci.security.loginapp.constants.*"%>
<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.PrivilegeForm.operation.value=target;
    		document.PrivilegeForm.submit();
    	}
    // -->
    </script>


	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="PrivilegeForm" action="/PrivilegeDBOperation">
		<html:hidden property="operation" value="error" />
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Privilege</h2>

					<h3>Privilege Home</h3>
					<logic:notPresent name="<%=DisplayConstants.ADMIN_USER%>">
					<p>This is the Privilege section of the User Provisioning Tool. A
					Privilege refers to any operation performed upon data.   
					Assigning privileges helps control access to important components of an application (Protection Elements).  
					<br>
					<!--In this section you may create new Privileges or modify existing
					Privilege details. Please begin by selecting either <b>Create a New
					Privilege</b> or <b>Select an Existing Privilege</b>.-->
					This application has a standard set of privileges that you may search for and view in this section.  Please begin by selecting <b>Select an Existing Privilege</b>. </p>
					</logic:notPresent>
					<logic:present name="<%=DisplayConstants.ADMIN_USER%>">
					<p>This is the Privilege section for UPT Super Admins. The UPT installs with CSM Standard Privileges that were agreed upon by the Security Working Group.  If necessary in this section you may create new application-specific Privileges or modify existing
					Privilege details. 
					<br>
					A Privilege refers to any operation performed upon data. Assigning privileges helps control access to important components of an application (Protection Elements).  
					<br>
					Please begin by selecting either <b>Create a New
					Privilege</b> or <b>Select an Existing Privilege</b>. </p>
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
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">PRIVILEGE LINKS</td>
								</tr>
								<logic:present name="<%=DisplayConstants.ADMIN_USER%>">
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadAdd')">Create a New
									Privilege</a><br>
									Click to add a new privilege.</td>
								</tr>
								
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Privilege</a><br>
									Enter search criteria to find the privilege you wish to operate
									on.</td>
								</tr>
								</logic:present>
								<logic:notPresent name="<%=DisplayConstants.ADMIN_USER%>">
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Privilege</a><br>
									Enter search criteria to find the privilege you wish to view.</td>
								</tr>
								</logic:notPresent>
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



