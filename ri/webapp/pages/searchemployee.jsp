<%@ include file= "/pages/imports.jsp" %>

<% request.getSession().setAttribute( Constants.EMPLOYEE_FORM, new gov.nih.nci.security.ri.valueObject.Employee()); %>

<html:form method="post" action="searchEmployee">

<BR>
<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
				<td align="center"><h2>Search for an Employee</h2>
				</td>
				</tr>
				<tr>
					<td>
					<table summary="" cellpadding="3" cellspacing="0" border="0"
						align="center">
						<tr>
						<td class="formTitle" height="20" colspan="3">SEARCH</td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">&nbsp;</td>

						<td class="formRequiredLabel"><label for="field1">Search Records (enter last name) :  </label></td>

						<td class="formField"><html:text property="lastName"/></td>					
					</tr>


					<tr>
						<td align="right" colspan="3">
							<!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td><html:submit/></td>
									<td><input class="actionButton" type="reset" value="Reset" /></td>
								</tr>
							</table>
							<!-- action buttons end -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</html:form>