
<%@ include file="/pages/imports.jsp"%>


<html:form method="post" action="updateEmployee" >

	<table>
		<tr>
			<td valign="top"><jsp:include page="/pages/employeeRecord.jsp" /></td>
		</tr>
		<tr>
			<td align="right" colspan="3"><!-- action buttons begins -->
			<table cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><html:submit>Update</html:submit></td>
				</tr>
			</table>
			<!-- action buttons end --></td>
		</tr>
	</table>
	
</html:form>
	
