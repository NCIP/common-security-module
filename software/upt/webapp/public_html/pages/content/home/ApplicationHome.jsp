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
<script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.ApplicationForm.operation.value=target;
    		document.ApplicationForm.submit();
    	}
    	
function skipNavigation()
{
	document.getElementById("appHome").focus();
	window.location.hash="appHome";
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
    	
    // -->
    </script>


	
	<table summary="Application Home" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="ApplicationForm" action="/ApplicationDBOperation">
		<html:hidden property="operation" value="error" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>Application</h2>

					<h3><a id="appHome"></a>Application Home</h3>

					<p>This is the Application section of the UPT.  Here a Super Admin can add an application to the UPT.  Users can be assigned as UPT Administrators for their particular application(s). 
					They will have the right to create and modify Roles, Groups, etc. You may add or modify Application details by clicking on the links below.</p>
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

									<td class="sidebarTitle" height="20">APPLICATION LINKS</td>
								</tr>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadAdd')">Create a New
									Application</a><br>
									Click to add a new application.</td>
								</tr>
								<tr>
									<td class="sidebarContent"><a
										href="javascript: setAndSubmit('loadSearch')">Select an
									Existing Application</a><br>
							Enter search criteria to find the application you wish to operate on.</td>
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



