
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>


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

				<p><a href="#">Groups</a><br />
				Collection of users that may be assigned protection grops and roles.
				</p>

				<p><a href="#">Privileges</a><br />
				Operations that can be performed on any data.</p>
				<p><a href="#">Protection Elements</a><br />
				data set that has a controlled access as per the roles and
				privileges. A protected element can be any object also. Basically it
				is a conceptual entity that an application will associate with any
				piece of data that it wants to protect.</p>
				<p><a href="#">Protection Groups</a><br />
				Collection of protected elements that are assigned to a Role or User
				at once. Also allows the Role/User to access future protected
				elements that do not yet exist.</p>
				<p><a href="#">Roles</a><br />
				Can be seen in the context of an application and /or protection
				group. A role is assigned to user to grant them privileges on
				particular protection group.</p>
				<p><a href="#">Users</a><br />
				Users for an application or for a system or both.</p>
				<p>&nbsp;</p>
				</td>
				<td width="30%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
