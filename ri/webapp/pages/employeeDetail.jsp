
<%@ include file="/pages/imports.jsp"%>


<html:form method="post" action="updateEmployee">

	<table>
		<tr>
			<td align="center" colspan="2">
			<h2>Employee Information</h2>
			</td>
		</tr>
		<tr>
			<td valign="top"><jsp:include page="/pages/employeeRecord.jsp" /></td>

			<td valign="top" class="contentBegins" width="50%">

			<table summary="" cellpadding="3" cellspacing="0" border="0"
				align="left">

				<tr>
					<td class="formMessage" colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td class="formTitle" height="20" colspan="3">EMPLOYEE PROJECTS</td>
				</tr>

				<tr>
					<td class="dataTableHeader" scope="col" colspan="3">Add or Remove
					Project Assignments</td>
				</tr>

				<tr>
					<td class="formRequiredLabel2">Assigned Projects</td>

					<td class="dataCellText" width="75px">&nbsp;</td>

					<td class="formRequiredLabel2">Available Projects</td>
				</tr>

				<tr>
					<td class="dataCellText"><select name="availableprojects" multiple
						style="width:115px;" size="10" />
					<td class="formRequiredLabel2" align="center"><input type="button"
						value="Add" style="width:70px;font:8pt"> <br>
					<br>
					<input type="button" value="Remove" style="width:70px;font:8pt"> <br>
					</td>

					<td class="dataCellText"><select name="assignedprojects" multiple
						style="width:115px;" size="10" /></td>

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

