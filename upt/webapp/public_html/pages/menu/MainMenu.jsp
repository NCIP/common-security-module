<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<script>
  <!--
    	function set(tableId)
    	{
    		document.menuForm.tableId.value=tableId;
    		document.menuForm.submit();
    	}
   // -->
</script>

<html:form styleId="menuForm" action="<%="/MenuSelection"%>">
	<%
	String tableId;
	try
	{
		tableId = (String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID);
		if (tableId.equalsIgnoreCase(DisplayConstants.BLANK))
		{
			tableId = DisplayConstants.HOME_ID;
			session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.HOME_ID);
		}
	}
	catch (Exception e)
	{
		tableId = DisplayConstants.HOME_ID;
		session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.HOME_ID);
	}
	%>
	<html:hidden property="tableId" value="error" />
	<table summary="" cellpadding="0" cellspacing="0" border="0" height="20">
		<logic:present name="<%=DisplayConstants.LOGIN_OBJECT%>">
			<td width="1"><!-- anchor to skip main menu --><a href="#content"><img src="images/shim.gif" alt="Skip Menu" width="1" height="1" border="0" /></a></td>
			<!-- link 1 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.HOME_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.HOME_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.HOME_ID%>')">HOME</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.HOME_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.HOME_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.HOME_ID%>')">HOME</a></td>
			<%}%>
			<!-- link 1 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 2 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.GROUP_ID)){%>			
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.GROUP_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.GROUP_ID%>')">GROUP</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.GROUP_ID)){%>			
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.GROUP_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.GROUP_ID%>')">GROUP</a></td>
			<%}%>
			<!-- link 2 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 3 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.PRIVILEGE_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')">PRIVILEGE</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.PRIVILEGE_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PRIVILEGE_ID%>')">PRIVILEGE</a></td>			
			<%}%>
			<!-- link 3 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 4 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.PROTECTION_ELEMENT_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')">PROTECTION ELEMENT</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.PROTECTION_ELEMENT_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PROTECTION_ELEMENT_ID%>')">PROTECTION ELEMENT</a></td>
			<%}%>
			<!-- link 4 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 5 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.PROTECTION_GROUP_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')">PROTECTION GROUP</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.PROTECTION_GROUP_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.PROTECTION_GROUP_ID%>')">PROTECTION GROUP</a></td>
			<%}%>
			<!-- link 5 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 6 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.ROLE_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.ROLE_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.ROLE_ID%>')">ROLE</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.ROLE_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.ROLE_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.ROLE_ID%>')">ROLE</a></td>
			<%}%>
			<!-- link 6 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 7 begins -->
			<%if (tableId.equalsIgnoreCase(DisplayConstants.USER_ID)){%>
			<td height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.USER_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.USER_ID%>')">USER</a></td>
			<%}if (!tableId.equalsIgnoreCase(DisplayConstants.USER_ID)){%>
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.USER_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.USER_ID%>')">USER</a></td>			
			<%}%>
			<!-- link 7 ends -->
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
			<!-- link 8 begins -->			
			<td height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="javascript: set('<%=DisplayConstants.LOGOUT_ID%>')"><a class="mainMenuLink" href="javascript: set('<%=DisplayConstants.LOGOUT_ID%>')">LOG OUT</a></td>
			<!-- link 8 ends -->			
			<td><img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" /></td>
		</logic:present>
	</table>
</html:form>
