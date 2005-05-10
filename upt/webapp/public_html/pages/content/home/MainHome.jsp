
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<script>
  <!--
    	
//-->

</script>


	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td colspan="3">

					<h2>User Provisioning Tool</h2>

					<h3>Welcome!</h3>

					<p>Welcome to the User Provisioning Tool. You may begin by clicking
					on any menu option above, or you can follow our Recommended
					Workflow outlined below.<br>This diagram shows how UPT security
					objects are related.  Roll over objects to learn more. </p>
					</td>
					<td width="30%">&nbsp;</td>
					<tr><td colspan="4">&nbsp;</td></tr>
				</tr>




				<!--
				<!-- diagram -->

				<tr>
					<td colspan="2" width="50%">

					<table cellspacing="0" cellpadding="0">
						<tr>
							<td height="50%">
							<table width="50%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.USER_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('user','','images/Users.gif',1)"><img
										name="user" border="0" src="images/Users2.gif" width="98"
										height="50" alt="A User is someone that requires access to your application. Users can become part of a Group, and can have an associated Protection Group and Roles."></a></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('PEs','','images/protectionelements.gif',1)"><img
										name="PEs" border="0" src="images/protectionelements2.gif"
										width="98" height="50" alt="A Protection Element is any entity (typically data) that has controlled access. Examples include Social Security Number, City, and Salary."></a></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('priv','','images/privileges.gif',1)"><img
										name="priv" border="0" src="images/privileges2.gif" width="98"
										height="50" alt="A Privilege refers to any operation performed upon data. Examples include Delete Record or Modify Record."></a></td>
								</tr>

								<tr>
									<td width="100%" height="50"><img src="images/create.gif"
										width="98" height="50"></td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table
								background="<html:rewrite href="images/dotted_line1.gif"/>"
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="100"><img src="images/elbow.gif" width="98"
										height="100"></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>

								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('group','','images/groups.gif',1)"><img
										name="group" border="0" src="images/groups2.gif" width="98"
										height="50" alt="A Group is a collection of application users. By combining users into a Group, it becomes easier to manage their collective roles and access rights in your application."></a></td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('pgs','','images/protectiongroups.gif',1)"><img
										name="pgs" border="0" src="images/protectiongroups2.gif"
										width="98" height="50" alt="A Protection Group is a collection of application Protection Elements. By combining Protection Elements into a Protection Group, it becomes easier to associate Users and Groups with rights to a particular data set. Examples include Address and Personal Information."></a></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><a
										href="javascript: set('<%=DisplayConstants.ROLE_ID%>')"
										onMouseOut="MM_swapImgRestore()"
										onMouseOver="MM_swapImage('roles','','images/roles.gif',1)"><img
										name="roles" border="0" src="images/roles2.gif" width="98"
										height="50" alt="A Role is a collection of application Privileges. Examples include Record Admin. and EmployeeModify."></a></td>
								</tr>

								<tr>
									<td width="100%" height="50"><img src="images/assign.gif"
										width="98" height="50"></td>
								</tr>
							</table>
							</td>
							<td height="100%">
							<table
								background="<html:rewrite href="images/dotted_line2.gif"/>"
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>
								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" height="50"><img
										src="images/horizontal_line.gif" width="98" height="50"></td>
								</tr>

								<tr>
									<td width="100%" height="50">&nbsp;</td>
								</tr>
							</table>
							</td>
							<td valign="top" height="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td valign="top" height="100%"><img src="images/assoc_block2.gif" width="98" height="300" alt="Each User assumes Roles for Protection Groups.  For example, User John has a Role EmployeeModify for all elements in the Address Protection Group. Assign PGs and Roles from the User or Group sections of the UPT." /></td>
								</tr>

								<tr>
									<td width="100%" height="50"><img src="images/associate.gif"
										width="98" height="50"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					</td>
					
					<td colspan="2" width="50%">&nbsp;</td>
				</tr>






				<tr class="home">
					<td class="home" colspan="4">&nbsp;</td>
				</tr>




				<!-- workflow begins-->
				<tr>

					<td colspan="4">
					<table cellpadding="0" cellspacing="0" border="0"
						class="contentBegins">
						<!--recommended flow-->
						<tr class="home">
							<td class="home" colspan="3">
							<h3>Recommended Workflow</h3>
							<p>When initially adding an application, we recommend the
							following steps:</p>

							</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>

						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>

						<tr class="home">
							<td class="home" colspan="3">1. Create base objects - <b>Users</b>,
							<b>Protection</b> <b>Elements</b>, and <b>Privileges</b></td>
							<td class="home" width="25%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="3" align="center">
							<div align="left">2. Create collections of these objects</div>
							</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">&nbsp;</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!-- Section A -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Groups</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
				
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Users</b> to <b>Groups</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">A collection of Privileges. </td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">A Role called "account admin" might include Privileges "create account" and "modify account".
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">How:</td>
					<td class="home" width="60%">Go to the Role section to create a Role, then assign Privileges to that Role.	
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<!-- end section A -->
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Section B -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Protection Groups</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Any entity that can be protected.
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">Protection Elements might include "name" or "telephone number"
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				-->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Protection Elements</b> to
							<b>Protection Groups</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">A collection of Protection Elements.</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">A Protection Group called "telephone directory" may include the Protection Elements "name" and "phone number".
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">How:</td>
					<td class="home" width="60%">Go to the Protection Group section to create a Protection Group, then assign Protection Elements to that Protection Group.	
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<!-- end section B -->
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Section C -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Create <b>Roles</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Users for an application or for a system or both.</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				-->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign <b>Privileges</b> to <b>Roles</b></td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">A collection of Users. </td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">A Group called "administrators" might include those that need to modify a telephone directory information.
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">How:</td>
					<td class="home" width="60%">Go to the Group section to create a Group, then assign Users to that Group.	
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<!-- end section C -->
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="3">3. Associate rights with <b>Users</b>
							and <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
						<!-- Step 2 A -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign a <b>Protection Group</b> and
							<b>Roles</b> to <b>Users</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">&nbsp;</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Each User assumes certain Roles (set of operation rights) for a Protection Group (set of protected entities). </td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">User John has a Role "account admin" for all elements in the "telephone directory" Protection Group. 

					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">How:</td>
					<td class="home" width="60%">Go to the User section to assign a Protection Group and Roles.	
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<!-- Step 2 B -->
						<tr class="home">
							<td class="home" width="5%" align="center">&nbsp;</td>
							<td class="home" colspan="2">Assign a <b>Protection Group</b> and
							<b>Roles</b> to <b>Groups</b>.</td>
							<td class="home" width="27%">&nbsp;</td>
						</tr>
						<!--
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Each Group assumes certain Roles (set of operation rights) for a Protection Group (set of protected entities). </td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">The Group "administrators" has a Role "account admin" for all elements in the "telephone directory" Protection Group. 

					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">How:</td>
					<td class="home" width="60%">Go to the Group section to assign a Protection Group and Roles.	
</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				-->
						<tr class="home">
							<td class="home" colspan="4">&nbsp;</td>
						</tr>
					</table>
				</td>
				</tr>
				<!-- workflow ends, association begins -->


				<!--association diagram
				<tr class="home">
					<td class="home" colspan="3">
					<h3>Association Diagram</h3>
					
					<p>This diagram shows how the security objects are related.  CSM works by making sure a User has the appropriate Privilege for a particular Protected Element.</p>
					</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>	
				<tr>
				<td colspan="2" class="home"><img src="images/assoc.gif"/></td>
				</tr>
	
	
	
	
	


				
				<!--
				<tr class="home">

					<td>&nbsp;</td>
					<td class="home" width="60%">

					<p><a href="javascript: set('<%=DisplayConstants.GROUP_ID%>')">Groups</a><br />
					Collection of Users that may be assigned Protection Groups and
					Roles.</p>
					<p><a
						href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')">Privileges</a><br />
					Operations that can be performed on any data.</p>
					<p><a
						href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')">Protection
					Elements</a><br />
					data set that has a controlled access as per the Roles and
					Privileges. A Protected Element can be any object also. Basically
					it is a conceptual entity that an application will associate with
					any piece of data that it wants to protect.</p>
					<p><a
						href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')">Protection
					Groups</a><br />
					Collection of Protected Elements that are assigned to a Role or
					User at once. Also allows the Role/User to access future Protected
					Elements that do not yet exist.</p>
					<p><a href="javascript: set('<%=DisplayConstants.ROLE_ID%>')">Roles</a><br />
					Can be seen in the context of an application and /or Protection
					Group. A role is assigned to a User to grant them Privileges on
					particular Protection Proup.</p>
					<p><a href="javascript: set('<%=DisplayConstants.USER_ID%>')">Users</a><br />
					Users for an application or for a system or both.</p>
					<p>&nbsp;</p>
					</td>
					<td class="home" width="30%">&nbsp;</td>
				</tr>  -->
			</table>
			</td>
		</tr>
	</table>

