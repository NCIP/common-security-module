<%@ include file="/pages/imports.jsp"%>

<html:form method="post" action="createEmployee">

<BR>

	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
				<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				
				<tr>
					<td align="center" colspan="2"><h2>Create an Employee Record</h2>
					</td>
				</tr>
				
				
				<tr>
					<td class="contentBegins" width="50%">
					
					
					<table cellpadding="3" cellspacing="0" border="0" align="center">
					<tr>
						<td class="formMessage" colspan="3">* indicates a required field</td>
					</tr>
					<tr>
						<td class="formTitle" height="20" colspan="3">EMPLOYEE RECORD</td>
					</tr>


					<tr>
						<td class="dataTableHeader" scope="col" colspan="3">Demographic
						Information</td>
					</tr>


					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">Last name:</td>
						<td class="dataCellText"><html:text property="lastName" size="20"
							maxlength="100" tabindex="12" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">First name:</td>
						<td class="dataCellText"><html:text property="firstName" size="20"
							maxlength="100" tabindex="13" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formRequiredLabel">Middle Name</td>
						<td class="dataCellText"><html:text property="middleName"
							maxlength="1" size="1" tabindex="14" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">Street address:</td>
						<td class="dataCellText"><html:text property="streetAddr"
							maxlength="45" size="20" tabindex="15" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">City:</td>
						<td class="dataCellText"><html:text property="city" size="20"
							maxlength="50" tabindex="16" /></td>
					</tr>


					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">State Code:</td>
						<td class="dataCellText"><html:text property="state" size="3"
							maxlength="3" tabindex="17" /></td>
					</tr>

					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">Zip:</td>
						<td class="dataCellText"><html:text property="zip" size="20"
							maxlength="20" tabindex="18" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">Phone number:</td>
						<td class="dataCellText"><html:text property="phoneNumber"
							size="20" maxlength="19" tabindex="19" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formRequiredLabel">Email address:</td>
						<td class="dataCellText"><html:text property="emailAddr" size="20"
							maxlength="100" tabindex="20" /></td>
					</tr>
					<tr>
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formRequiredLabel">Social Security Number:</td>
						<td class="dataCellText"><html:text property="ssn" size="20"
							maxlength="100" tabindex="21" /></td>
					</tr>


					<tr>
						<td class="dataTableHeader" scope="col" colspan="3">Salary</td>
					</tr>

					
					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">Salary:</td>
						<td class="dataCellText"><html:text property="salary" size="20"
							maxlength="14" tabindex="22" /></td>
					</tr>

					</table>
					
					
					
					</td>
					
					<td valign="top" class="contentBegins" width="50%">
					
					<table summary="" cellpadding="3" cellspacing="0" border="0" align="left">
					
					<tr>
						<td class="formMessage" colspan="3">&nbsp;</td>
					</tr>
					
					<tr>
						<td class="formTitle" height="20" colspan="3">EMPLOYEE PROJECTS</td>
					</tr>

					<tr>
						<td class="dataTableHeader" scope="col" colspan="3">Add or Remove Project Assignments</td>
					</tr>
					
					<tr>
					<td class="formRequiredLabel2">Available Projects</td>
					
					<td class="dataCellText" width="75px">&nbsp;</td>
					
					<td class="formRequiredLabel2">Assigned Projects</td>
					</tr>
					
					<tr>
					<td class="dataCellText"><select name="availableprojects" multiple style="width:115px;" size="10"/>
					
					<td class="formRequiredLabel2" align="center">
						<input type="button" value="Add" style="width:70px;font:8pt">
						<br>
						<br>
						<input type="button" value="Remove" style="width:70px;font:8pt">
						<br>
					</td>
					
					<td class="dataCellText"><select name="assignedprojects" multiple style="width:115px;" size="10"/>
					
					
					
					<td>
				
		
	
				
				
				
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</html:form>
