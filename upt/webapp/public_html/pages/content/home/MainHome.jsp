
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

					<h3>Welcome!</h3>

					<p>Welcome to the User Provisioning Tool (UPT). This user interface
					tool is designed so that developers can easily configure an
					application's authorization data in the Common Security Module
					(CSM) database. With the click of a few buttons you may control
					which users can access protected elements or operations of your application. This
					tool combined with the CSM allows for fine-grain security control,
					and will eventually provide other features such as single sign-on.
					The UPT is divided into six major sections: Groups, Privileges,
					Protection Groups, Roles, and Users. From these sections you may
					perform basic functions such as modify, delete, or create, and you
					may also manage associations between the objects. For example you
					may assign Privileges to a Role. Please begin using this
					application by clicking on one of the menu subsections above or the
					links below. Enjoy.</p>
					</td>
				</tr>
				<tr>

					<td>&nbsp;</td>
					<td width="60%">

					<p><a href="javascript: setTable('<%=DisplayConstants.GROUP_ID%>')">Groups</a><br />
					Collection of Users that may be assigned Protection Groups and
					Roles.</p>
					<p><a
						href="javascript: setTable('<%=DisplayConstants.PRIVILEGE_ID%>')">Privileges</a><br />
					Operations that can be performed on any data.</p>
					<p><a
						href="javascript: setTable('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')">Protection
					Elements</a><br />
					data set that has a controlled access as per the Roles and
					Privileges. A Protected Element can be any object also. Basically
					it is a conceptual entity that an application will associate with
					any piece of data that it wants to protect.</p>
					<p><a
						href="javascript: setTable('<%=DisplayConstants.PROTECTION_GROUP_ID%>')">Protection
					Groups</a><br />
					Collection of Protected Elements that are assigned to a Role or
					User at once. Also allows the Role/User to access future Protected
					Elements that do not yet exist.</p>
					<p><a href="javascript: setTable('<%=DisplayConstants.ROLE_ID%>')">Roles</a><br />
					Can be seen in the context of an application and /or Protection
					Group. A role is assigned to a User to grant them Privileges on
					particular Protection Proup.</p>
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
