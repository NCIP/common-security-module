<%@ include file="/pages/imports.jsp"%>

<html:form method="post" action="createProject">

	<BR>

	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
				<td align="center"><h2>Create Project</h2>
				</td>
				</tr>
				<tr>
					<td>
					<table summary="" cellpadding="3" cellspacing="0" border="0"
						align="center">
						<tr>
							<td class="infoMessage" colspan="3">
			  				<html:messages id="message" message="true">
			  				<bean:write name="message"/>
			  				</html:messages>	
			  				</td>
						</tr>
						<tr>
							<td class="formMessage" colspan="3">* indicates a required field</td>
						</tr>
						<tr>
							<td class="formTitle" height="20" colspan="3">PROJECT</td>
						</tr>

						<tr>
							<td class="formRequiredNotice" width="5">*</td>
							<td class="formRequiredLabel">Project name:</td>
							<td class="dataCellText"><html:text property="name" size="20"
								maxlength="60" tabindex="12" /></td>
						</tr>
						<tr>
							<td class="formRequiredNotice" width="5">*</td>
							<td class="formRequiredLabel">Project description:</td>
							<td class="dataCellText"><html:textarea property="description"
								rows="2" cols="32" tabindex="13" /></td>
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
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="1" height="315"><tr><td></td></tr></table>
</html:form>
