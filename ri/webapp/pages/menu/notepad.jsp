<%@ include file="/pages/imports.jsp"%>

<%@ page import="gov.nih.nci.security.ri.valueObject.Employee"%>

<!--<table cellpadding="0" cellspacing="0" border="0" width="175"
	height="100%">-->

<!-- notepad row
	<tr>
		<td valign="top" class="leftMenu" width="175" height="150"> -->
			<table width="100%">
			<tr>
				<td align="center" height="20" class="mainMenuItem"><html:link
					action="Logout" styleClass="mainMenuLink">LOGOUT</html:link></td>
			</tr>
			</table>
			<table background="<html:rewrite href="pages/images/notepad.gif"/>"
				width="175" height="150" border="0" cellspacing=0 cellpadding=0>
				<tr>
					<td align="center"><br><br>Your Name:<br>
						<bean:define name="USER" id="user" type="Employee" />
						<bean:write name="user" property="firstName" />
						<br>
						<bean:write name="user" property="lastName" />
						<br>
					</td>
				</tr>
			</table>
		<!--</td>
	</tr>	-->
		<!-- notepad ends   		
</table>  -->
