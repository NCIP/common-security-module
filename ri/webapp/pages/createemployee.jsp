
<%@ include file="/pages/imports.jsp"%>

<% request.getSession().setAttribute( Constants.EMPLOYEE_FORM, new gov.nih.nci.security.ri.valueObject.Employee()); %>

<html:form method="post" action="createEmployee">

	<table>
		
		<tr>
			<td valign="top"><BR>

			<table summary="" cellpadding="0" cellspacing="0" border="0"
				class="contentPage" width="100%" height="100%">
				<tr>
					<td valign="top">
					<table cellpadding="0" cellspacing="0" border="0"
						class="contentBegins">
						
						<tr>
									<td align="center" colspan="2">
									<h2>Create an Employee Record</h2>
									</td>
		</tr>

						<tr>
							<td class="contentBegins" width="50%">


							<table cellpadding="3" cellspacing="0" border="0" align="center">

								<tr>
									<td class="formTitle" height="20" colspan="3">EMPLOYEE RECORD</td>
								</tr>


								<tr>
									<td class="dataTableHeader" scope="col" colspan="3">Demographic
									Information</td>
								</tr>


								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">User Name:</td>
									<td class="dataCellText"><html:text property="userName"
										size="20" maxlength="100" tabindex="12" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Password:</td>
									<td class="dataCellText"><html:text property="password"
										size="20" maxlength="100" tabindex="13" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Last name:</td>
									<td class="dataCellText"><html:text property="lastName"
										size="20" maxlength="100" tabindex="14" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">First name:</td>
									<td class="dataCellText"><html:text property="firstName"
										size="20" maxlength="100" tabindex="15" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formRequiredLabel">Middle Initial:</td>
									<td class="dataCellText"><html:text property="middleName"
										maxlength="1" size="1" tabindex="16" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Street address:</td>
									<td class="dataCellText"><html:text property="streetAddr"
										maxlength="45" size="20" tabindex="17" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">City:</td>
									<td class="dataCellText"><html:text property="city" size="20"
										maxlength="50" tabindex="18" /></td>
								</tr>


								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">State Code:</td>
									<td class="dataCellText"><html:text property="state" size="3"
										maxlength="3" tabindex="19" /></td>
								</tr>

								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Zip:</td>
									<td class="dataCellText"><html:text property="zip" size="20"
										maxlength="20" tabindex="20" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Phone number:</td>
									<td class="dataCellText"><html:text property="phoneNumber"
										size="20" maxlength="19" tabindex="21" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formRequiredLabel">Email address:</td>
									<td class="dataCellText"><html:text property="emailAddr"
										size="20" maxlength="100" tabindex="22" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formRequiredLabel">Social Security Number:</td>
									<td class="dataCellText"><html:text property="ssn" size="20"
										maxlength="100" tabindex="23" /></td>
								</tr>


								<tr>
									<td class="dataTableHeader" scope="col" colspan="3">Salary</td>
								</tr>


								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel">Salary:</td>
									<td class="dataCellText"><html:text property="salary" size="20"
										maxlength="14" tabindex="24" /></td>
								</tr>
								
								<tr>
									<td class="dataTableHeader" scope="col" colspan="3">Business Unit</td>
								</tr>
								
								<tr>
									<td class="formRequiredNotice" width="5" >*</td>
									
									<td class="formRequiredLabel">Business Unit:</td>
									<td class="dataCellText"><html:radio style="formFieldSized"
										property="businessUnit" value="<%=Constants.TECHNOLOGY_DIVISION%>" />&nbsp;Information Technology&nbsp;&nbsp;
										<html:radio
										style="formFieldSized" property="businessUnit"
										value="<%=Constants.BUSINESS_DIVISION%>" />
										&nbsp;Business Development&nbsp;&nbsp;<html:radio
										style="formFieldSized" property="businessUnit"
										value="<%=Constants.HR_DIVISION%>" />&nbsp;Human Resources
									</td>
								</tr>
								
								<tr>
									<td class="formRequiredNotice" width="5" >*</td>
									
									<td class="formRequiredLabel">Is the employee a Manager:</td>
									<td class="dataCellText"><html:radio style="formFieldSized"
										property="managerStatus" value="<%=Constants.YES%>" />&nbsp;<%=Constants.YES%>&nbsp;&nbsp;
										<html:radio
										style="formFieldSized" property="managerStatus"
										value="<%=Constants.NO%>" />
										&nbsp;<%=Constants.NO%>&nbsp;&nbsp;
									</td>
								</tr>
								
								<tr>
									<td class="formMessage" colspan="3">* indicates a required
									field</td>
								</tr>

							</table>

							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>




			</td>

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
