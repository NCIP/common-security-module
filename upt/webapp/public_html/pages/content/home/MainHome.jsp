
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
					<td colspan="3">

					<h2>User Provisioning Tool</h2>

					<h3>Welcome!</h3>

					<p>Welcome to the User Provisioning Tool. You may begin by clicking on any menu option above, or you can follow our Recommended Workflow outlined below.  This page also includes an association diagram to help show how UPT security objects are related.</p>
					</td>
					<td width="30%">&nbsp;</td>
				</tr>
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<!--recommended flow-->
				<tr class="home">
					<td class="home" colspan="3">
					<h3>Recommended Workflow</h3>
					
					<p>When initially adding an application, we recommend the following steps:</p>
					</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>	
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
	
	<!-- workflow begins-->
	<tr>
	<td colspan="4">
	<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">			
				
				<tr class="home">
					<td class="home" width="7%">Step 1</td>
					<td class="home" colspan="2">Perform steps A, B, and C (in any order).</td>
									
					<td class="home" width="25%">&nbsp;</td>
				</tr>
				
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				
				<!-- Section A -->
				<tr class="home">
					<td class="home" width="5%" align="center">A</td>
					<td class="home" colspan="2">Create <a
						href="javascript: setTable('<%=DisplayConstants.PRIVILEGE_ID%>')">Privileges</a>.
					</td>
					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Any operation that can be performed.</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">Example:</td>
					<td class="home" width="60%">An example of a Privilege is the right to create an account - or "create account"</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<tr class="home">
					<td class="home" width="5%" align="center">&nbsp;</td>
					<td class="home" colspan="2">Create <a href="javascript: setTable('<%=DisplayConstants.ROLE_ID%>')">Roles</a>.
					</td>					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<!-- Section B -->
				<tr class="home">
					<td class="home" width="5%" align="center">B</td>
					<td class="home" colspan="2">Create <a
						href="javascript: setTable('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')">Protection
					Elements</a>.
					</td>
					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<tr class="home">
					<td class="home" width="5%" align="center">&nbsp;</td>
					<td class="home" colspan="2">Create <a
						href="javascript: setTable('<%=DisplayConstants.PROTECTION_GROUP_ID%>')">Protection
					Groups</a>.
					</td>					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				
				<!-- Section C -->
				<tr class="home">
					<td class="home" width="5%" align="center">C</td>
					<td class="home" colspan="2">Create <a
						href="javascript: setTable('<%=DisplayConstants.USER_ID%>')">Users</a>.
					</td>
					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				<tr class="home">
					<td class="home" width="5%">&nbsp;</td>
					<td class="home" width="7%">What:</td>
					<td class="home" width="60%">Users for an application or for a system or both.</td>
					<td class="home" width="27%">&nbsp;</td>
				</tr>
				
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<tr class="home">
					<td class="home" width="5%" align="center">&nbsp;</td>
					<td class="home" colspan="2">Create <a href="javascript: setTable('<%=DisplayConstants.GROUP_ID%>')">Groups</a>.
					</td>					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
				
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<tr class="home">
					<td class="home" width="7%">Step 2</td>
					<td class="home" colspan="2">Once you have created the objects in Step 1, you can now begin associating rights with Users and Groups.  Perform A and B in any order. </td>
									
					<td class="home" width="25%">&nbsp;</td>
				</tr>
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				<!-- Step 2 A -->
				<tr class="home">
					<td class="home" width="5%" align="center">A</td>
					<td class="home" colspan="2">Assign a Protection Group and Roles to Users.
					</td>					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
					<td class="home" width="5%" align="center">B</td>
					<td class="home" colspan="2">Assign a Protection Group and Roles to a Group.
					</td>					
					<td class="home" width="27%">&nbsp;</td>
				</tr>
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
				
				<tr class="home"><td class="home" colspan="4">&nbsp;</td></tr>
				
	
	</table>
	</td>
	</tr>	
<!-- workflow ends, association begins -->


<!--association diagram-->
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
	
	<tr class="home">
	<td colspan="3" class="home"><h3>Association Diagram</h3>
	</td>
	<td class="home" width="27%">&nbsp;</td>
	</tr>
	
	<tr class="home">
	<td colspan="3" class="home">This diagram shows how the security objects are related.  CSM works by making sure a User has the appropriate Privilege for a particular Protected Element.
	</td>
	<td class="home" width="27%">&nbsp;</td>
	</tr>
	
	<tr class="home">
	<td colspan="3" class="home"><img src="images/association.gif"/></td>
	</tr>
	
	-->			
				
				<!--
				<tr class="home">

					<td>&nbsp;</td>
					<td class="home" width="60%">

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
					<td class="home" width="30%">&nbsp;</td>
				</tr>  -->
			</table>
			</td>
		</tr>
	</table>
</html:form>
