
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<script>
  <!--
    	function setTable(tableId)
    	{
    		document.homeForm.tableId.value=tableId;
    		document.homeForm.submit();
    	}
   // -->
</script>


<html:form styleId="homeForm" action="<%="/MenuSelection"%>">
	<html:hidden property="tableId" value="error" />
	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="2">

					<h2>User Provisioning Tool</h2>

					<h3>Welcome Super Admin!</h3>

					<p>Welcome to the User Provisioning Tool (UPT). This user interface
					tool is designed so that developers can easily configure an
					application's authorization data in the Common Security Module
					(CSM) database. As a Super Admin., you may add, remove, or modify
					Application details. You can also assign administrators (users) to these
					Applications. Please begin using this application by clicking on
					one of the menu subsections above or the links below. Enjoy.</p>
					</td>
				</tr>
				<tr>

					<td>&nbsp;</td>
					<td width="60%">

					<p><a
						href="javascript: setTable('<%=DisplayConstants.APPLICATION_ID%>')">Application</a><br />
					Applications which use the CSM for security.</p>
					<p><a href="javascript: setTable('<%=DisplayConstants.USER_ID%>')">Users</a><br />
					Those who can serve as UPT administrators for the application.</p>
					<p>&nbsp;</p>
					</td>
					<td width="30%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
