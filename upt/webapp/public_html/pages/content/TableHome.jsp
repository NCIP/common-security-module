<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
    <script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.tableHomeForm.operation.value=target;
    		document.tableHomeForm.submit();
    	}
    // -->
    </script>
<%
String TABLE_NAME = (String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID);
String TABLE_NAME_LOWER = ((String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID)).toLowerCase();
String TABLE_NAME_UPPER = ((String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID)).toUpperCase();
String TABLE_DESCRIPTION = ((String)session.getAttribute(DisplayConstants.CURRENT_TABLE_ID))+"_Description";
%>
<html:form styleId="tableHomeForm" action = "<%="/"+session.getAttribute(DisplayConstants.CURRENT_TABLE_ID)+"DBOperation"%>">
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:hidden property="operation" value="error"/>
	<tr>
		<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">

				<h2><bean:message key="<%=TABLE_NAME_UPPER%>"/></h2>

				<h3><bean:message key="<%=TABLE_NAME%>"/>&nbsp;Home</h3>

				<p><bean:message key="<%=TABLE_DESCRIPTION%>"/></p>
				</td>
			</tr>
			<tr>
				<td valign="top" width="40%"><!-- sidebar begins -->
				<table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
					<tr>
						<td>
		  				<html:messages id="message" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
		  				<li><bean:write name="message"/></li>
		  				</html:messages>				
		  				</td>
					</tr>					
					<tr>
					<td valign="top">
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20"><bean:message key="<%=TABLE_NAME_UPPER%>"/>&nbsp;LINKS</td>
						</tr>
						<tr>
							<td class="sidebarContent"><a href="javascript: setAndSubmit('loadAdd')">Create a New <bean:message key="<%=TABLE_NAME%>"/></a><br>
							Click to add a new <bean:message key="<%=TABLE_NAME_LOWER%>"/>.</td>
						</tr>
						<tr>
							<td class="sidebarContent"><a href="javascript: setAndSubmit('loadSearch')">Select an Existing <bean:message key="<%=TABLE_NAME%>"/></a><br>
							Enter search criteria to find the <bean:message key="<%=TABLE_NAME_LOWER%>"/> you wish to operate on.</td>
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
</table>
</html:form>


