
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

				<p>Welcome to the User Provisioning Tool. With this tool you may
				configure your application's authorization data in the common
				security module database. Modify, delete, or create Groups,
				Privileges, Protection Elements, Protection Groups, Roles, or Users.
				Begin by selecting a link below:</p>
				</td>
			</tr>
			<tr>

				<td>&nbsp;</td>
				<td width="60%">

				<p><a href="javascript: setTable('<%=DisplayConstants.APPLICATION_ID%>')">Application</a><br />
				Applications which uses the CSM for security.</p>
				<p><a href="javascript: setTable('<%=DisplayConstants.USER_ID%>')">Users</a><br />
				Users for an application or for a system or both.</p>
				<p>&nbsp;</p>
				</td>
				<td width="30%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</html:form>