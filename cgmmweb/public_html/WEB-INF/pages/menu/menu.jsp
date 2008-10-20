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
<%@ page
	import="gov.nih.nci.security.cgmm.webapp.ForwardConstants"%>

<script>
  <!--
    	function set(tableId)
    	{
	    	window.location = "index.jsp";
    		document.menuForm.tableId.value=tableId;
    		document.menuForm.submit();
    	}
    	 	
   // -->
</script>
<table summary="" cellpadding="0" cellspacing="0" border="0" height="20">
	<tr>
<html:form styleId="menuForm" action="<%="/MenuSelection"%>">
<%
	String tableId;
	try {
		tableId = (String) session
		.getAttribute(ForwardConstants.CURRENT_TABLE_ID);
		if (tableId.equalsIgnoreCase(ForwardConstants.BLANK)) {
	tableId = ForwardConstants.HOME_ID;
	session.setAttribute(ForwardConstants.CURRENT_TABLE_ID,
	ForwardConstants.HOME_ID);
		}
	} catch (Exception e) {
		tableId = ForwardConstants.HOME_ID;
		session.setAttribute(ForwardConstants.CURRENT_TABLE_ID,
		ForwardConstants.HOME_ID);
	}
%>
<html:hidden property="tableId" value="error" />

		<td width="1">
			<a href="#content"><img src="images/shim.gif" alt="Skip Menu"
					width="1" height="1" border="0" /> </a>
		</td>
		<td>
			<img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" />
		</td>
		<td height="16" class="mainMenuItemOver"
			onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
			onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()">
			<a  class="mainMenuLink" href="javascript: set('<%=ForwardConstants.HOME_ID%>')">
				<bean:message key="label.menu_home" />
			</a>
		</td>
		<td>
			<img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" />
		</td>
		<%--
		<bean:define name="<%=DisplayConstants.HOST_APPLICATION_NAME%>"
															id="hostApplicationName" />
		<td height="16" class="mainMenuItemOver"
			onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
			onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()">
			<a  class="mainMenuLink" href="javascript: set('<%=ForwardConstants.HOST_HOME_ID%>')">
				BACK TO <%=hostApplicationName%>
			</a>
		</td>
		<td>
			<img src="images/mainMenuSeparator.gif" width="1" height="16" alt="" />
		</td>
		<td height="16" class="mainMenuItemOver"
			onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
			onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()">
			<a  class="mainMenuLink" href="javascript: set('<%=ForwardConstants.GRID_HOME_ID%>')">
				<bean:message key="label.menu_grid" />
			</a>
		</td>

--%></html:form>

	</tr>
</table>
