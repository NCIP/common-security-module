
<%@ include file="/pages/imports.jsp"%>

<% request.getSession().setAttribute( Constants.EMPLOYEE_FORM, new gov.nih.nci.security.ri.valueObject.Employee()); %>

<html:form method="post" action="createEmployee">

	<table>
		<tr>
			<td align="center" colspan="2">
			<h2>Create an Employee Record</h2>
			</td>
		</tr>
		<tr>
			<td valign="top"><jsp:include page="/pages/employeeRecord.jsp" /></td>
		</tr>
		<tr>
			<td align="right" colspan="3"><!-- action buttons begins -->
			<table cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><html:submit /></td>
					<td><input class="actionButton" type="reset" value="Reset" /></td>
				</tr>
			</table>
			<!-- action buttons end --></td>
		</tr>
	</table>

</html:form>
