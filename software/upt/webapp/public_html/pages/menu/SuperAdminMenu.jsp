<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<script>
  <!--
    	function set(id)
    	{
    		document.MenuForm.tableId.value=id;
    		document.MenuForm.submit();
    	}
   // -->
</script>
	<td class="mainMenu" height="5" valign="top">
<s:form name="MenuForm" id="MenuForm" action="MenuSelection.action" theme="simple">

	<%
	String tableId;
	try
	{
		tableId = (String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID);
		if (tableId.equalsIgnoreCase(DisplayConstants.BLANK))
		{
			tableId = DisplayConstants.ADMIN_HOME_ID;
			session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.ADMIN_HOME_ID);
		}
	}
	catch (Exception e)
	{
		tableId = DisplayConstants.ADMIN_HOME_ID;
		session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.ADMIN_HOME_ID);
	}
	System.out.println(" session1 " + session.getAttribute(DisplayConstants.PRIVILEGE_ID));
	System.out.println(" session2 " + session.getAttribute(DisplayConstants.ADMIN_HOME_ID));
	
	%>
	<s:hidden name="tableId" id="tableId" value="error" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='MenuSelection.action'/>"/>

	<table cellpadding="0" cellspacing="0" border="0" height="5">
		<%
		System.out.println("session.LOGIN_OBJECT...");
		%>

		<s:if test="#session['LOGIN_OBJECT'] != null">
			<tr>
			<!-- link 1 begins -->
			
			<%if (tableId.equalsIgnoreCase(DisplayConstants.ADMIN_HOME_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('AdminHome')"><a class="mainMenuLink" href="javascript: set('AdminHome')" id="saHome">HOME</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.ADMIN_HOME_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('AdminHome')"><a class="mainMenuLink" href="javascript: set('AdminHome')" id="saHome">HOME</a></td>
			<%}%>
			<!-- link 1 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>			
			<!-- link 2 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.APPLICATION_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('Application')"><a class="mainMenuLink" href="javascript: set('Application')" id="saApp">APPLICATION</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.APPLICATION_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('Application')"><a class="mainMenuLink" href="javascript: set('Application')" id="saApp">APPLICATION</a></td>
			<%}%>
			<!-- link 2 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 3 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.USER_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('User')"><a class="mainMenuLink" href="javascript: set('User')" id="saUser">USER</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.USER_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('User')"><a class="mainMenuLink" href="javascript: set('User')" id="saUser">USER</a></td>			
			<%}%>
			<!-- link 3 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16"  alt="MainMenu Items Separator" /></td>
			
			<!-- link priv 3.0.1 begins -->
				<%if (tableId.equalsIgnoreCase(DisplayConstants.PRIVILEGE_ID)){%>
				<td height="16" class="mainMenuItemOver"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()"
					onclick="javascript: set('Privilege')"><a
					class="mainMenuLink"
					href="javascript: set('Privilege')" id="saPriv">PRIVILEGE</a>
				<%}if (!tableId.equalsIgnoreCase(DisplayConstants.PRIVILEGE_ID)){%>
				<td height="16" class="mainMenuItem"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"
					onclick="javascript: set('Privilege')"><a
					class="mainMenuLink"
					href="javascript: set('Privilege')" id="saPriv">PRIVILEGE</a>
				<%}%>
				<!-- link priv 3.0.1 ends -->
			
			<!-- link 3 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16"  alt="MainMenu Items Separator" /></td>
			
			<!-- link 4 begins -->			
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"><a class="mainMenuLink" href="javascript: set('SystemConfiguration')" id="saSystemConfiguration">SYSTEM CONFIGURATION</a></td>
			<!-- link 4 ends -->			
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16"  alt="MainMenu Items Separator" /></td>
			
			<!-- link 5 begins -->			
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"><a class="mainMenuLink" href="javascript: set('ImportLDAPUser')" id="saImport">IMPORT LDAP USERS</a></td>
			<!-- link 5 ends -->			
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16"  alt="MainMenu Items Separator" /></td>
			
			<!-- link 6 begins -->			
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"><a class="mainMenuLink" href="javascript: set('Logout')" id="saLogout">LOG OUT</a></td>
			<!-- link 6 ends -->			
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16"  alt="MainMenu Items Separator" /></td>
			</tr>
		</s:if>
	</table>
	</td>
</s:form>
