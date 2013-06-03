<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

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
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.ProtectionGroupForm.operation.value=target;
    		document.ProtectionGroupForm.submit();
    	}
    	
function skipNavigation()
{
	document.getElementById("pgHome").focus();
	window.location.hash="pgHome";
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


	<table summary="Protection Group Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="ProtectionGroupForm" action="/ProtectionGroupDBOperation">
		<html:hidden property="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ProtectionGroupDBOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Protection Group</h2>

					<h3><a id="pgHome"></a>Protection Group Home</h3>

					<p>This is the Protection Group section of the User Provisioning
					Tool. A Protection Group is simply a collection of application
					Protection Elements. By combining Protection Elements into a
					Protection Group, it becomes easier to associate Users and Groups
					with rights to a particular data set. <br>
					In the User or Group portion of the UPT, you may assign Protection Groups to
					Users. In this section you may create new Protection
					Groups, modify existing Protection Group details, assign or
					deassign a parent for a Protection Group, and assign or deassign Protection Elements to a Protection Group. Please begin by selecting
					either <b>Create a New Protection Group</b> or <b>Select an Existing
					Protection Group</b>.</p>
					</td>
				</tr>
				<tr>
					<td valign="top" width="40%"><!-- sidebar begins -->
					<table cellpadding="0" cellspacing="0" border="0"
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
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">PROTECTION GROUP LINKS</td>
								</tr>
								<logic:present name='<%=Constants.CSM_CREATE_PRIVILEGE +"_"+Constants.UPT_PROTECTION_GROUP_OPERATION%>'>
									<tr>
										<td class="sidebarContent"><a
											href="javascript: setAndSubmit('loadAdd')">Create a New
										Protection Group</a><br>
										Click to add a new protection group.</td>
									</tr>
								</logic:present>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Protection Group</a><br>
									Enter search criteria to find the protection group you wish to
									operate on.</td>
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

