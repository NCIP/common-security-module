
<%@ include file="/pages/imports.jsp"%>

<br>

<table summary="" cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0"
			class="contentBegins">
			<tr>
							<td align="center"><h2>Search Results</h2>
							</td>
				</tr>
			<tr>
				<td>

				<table summary="" cellpadding="0" cellspacing="0" border="0"
					width="456">
					<tr>

						<td class="dataTablePrimaryLabel" height="20">EMPLOYEE SEARCH
						RESULTS</td>
					</tr>



					<tr>
						<td>
						<table summary="Enter summary of data here" cellpadding="3"
							cellspacing="0" border="0" class="dataTable" width="100%">
							<tr>
								<th class="dataTableHeader" scope="col">Employee Name</th>
							</tr>


						</table>
						</td>
					</tr>

					
					<logic:present name='<%= Constants.EMPLOYEE_LIST %>'>
					 <% int index = 0; %>
						<logic:iterate id="next" name='<%= Constants.EMPLOYEE_LIST %>'
							type="gov.nih.nci.security.ri.valueObject.Employee">
							<% String rowClass = "dataRowLight"; 
							   if ( (index++ % 2 ) == 0 ){
								  rowClass = "dataRowDark";
							   }
							%>
							<tr class='<%= rowClass %> '>
								<td class="dataCellText" width="33%"><html:link
									action="/viewEmployee" paramId='<%= Constants.EMPLOYEE_ID %>'
									paramName="next" paramProperty="employeeId">
								<bean:write name="next" property="lastName" />
								,&nbsp;
								<bean:write name="next" property="firstName" />
								</html:link></td>
							</tr>

						</logic:iterate>
					</logic:present>
				</table>
				</td>
			</tr>


		</table>
		</td>
	</tr>
</table>
<table width="1" height="315"><tr><td></td></tr></table>









