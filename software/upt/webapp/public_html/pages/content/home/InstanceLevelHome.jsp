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
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<script>
    	function setAndSubmit(target)
    	{
    		document.InstanceLevelForm.operation.value=target;
    		document.InstanceLevelForm.submit();
    	}
    	
function skipNavigation()
{
	document.getElementById("ilHome").focus();
	window.location.hash="ilHome";
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


	<table summary="Instance level Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="InstanceLevelForm" action="/InstanceLevelOperation">
		<html:hidden property="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/InstanceLevelOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Instance Level</h2>

					<h3><a id="ilHome"></a>Instance Level Home</h3>

					<p>This is the InstanceLevel section of the User Provisioning Tool. Instance Level
					is a feature provided by CSM to allow filtering of the instance of data directly at the database level by creating filter criteria's and
					linking them with allowed values from CSM tables.<br>
					In this section you may create upload an application jar file containing the Hibernate file and the Domain Objects, Create a new
					Filter Clause or Search for exisiting filter clauses. Please
					begin by selecting either <b>Upload the Jar File</b>, <b>Add New Security Filter</b> or <b>Select an Existing Security Filter</b>.</p>
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
						<tr>
							<td colspan="3">
							<html:errors />
							</td>
						</tr>
						
						<tr><td><br></td></tr>
						<tr>
							<td valign="top">
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">INSTANCE LEVEL LINKS</td>
								</tr>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadUpload');">Upload the Jar File</a><br>
									Click to upload a Jar File.</td>
								</tr>
								<logic:present name='<%=Constants.CSM_CREATE_PRIVILEGE +"_"+Constants.UPT_INSTANCE_LEVEL_OPERATION%>'>
									<tr>
										<td class="sidebarContent"><a
											href="javascript: setAndSubmit('loadAdd');">Add New Security Filter</a><br>
										Click to add a new Instance Level Security Setting.</td>
									</tr>
								</logic:present>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch');">Select an
									Existing Security Filter</a><br>
									Enter the Class Name to find the Instance Level Security Filter for it.</td>
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



