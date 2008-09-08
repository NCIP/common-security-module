<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ page import="gov.nih.nci.security.cgmm.webapp.DisplayConstants"%>



<tr>
	<td valign="top" class="contentPage">
		<table summary="" cellpadding="3" cellspacing="0" border="0"
			width="500">

			<tr>
				<td class="formMessage" colspan="1">
				</td>
				<td class="formMessage" colspan="3"></td>
			</tr>
			<tr>
				<td class="formMessage" colspan="1"></td>
				<td class="formMessage" colspan="3">
					<table summary="" cellpadding="3" cellspacing="0" border="0"
						width="500">
						<html:form styleId="CsmLoginForm" action="/CsmLogin"
							focus="loginID">
							<%--<html:hidden property="activity" value="submitQuery" />--%>

							<tr>
								<td class="formMessage" colspan="3">
									Have a local CSM account to migrate?. Provide local CSM
									credentials to login and migrate account information.
								</td>
							</tr>
							<tr>

								<td class="formTitle" height="20" colspan="3">
									<bean:message key="label.csm_login" />
								</td>
							</tr>

							<tr>
								<td colspan="3">
									<font color="red""> <html:errors /> </font>
								</td>
							</tr>
							<tr>

								<td class=formLabel width="30%">
									<LABEL for=loginID>
										<bean:message key="label.login_id" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=loginID size=40 name=loginID>
								</td>
							</tr>
							<tr>

								<td class=formLabel width="30%">
									<LABEL for=password>
										<bean:message key="label.login_password" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=password size=40 type=password
										name=password>
								</td>
							</tr>
							<tr>
								<td align="right" colspan="3">
									<!-- action buttons begins -->
									<table cellpadding="4" cellspacing="0" border="0">
										<tr>
											<td>
												<input class="actionButton" type="submit"
													value='<bean:message key="label.submit_button"/>' />
											</td>
											</html:form>
											<html:form action="/CsmLogin">
												<td>
													<input class="actionButton" type="submit"
														value='<bean:message key="label.reset_button"/>' />
												</td>
											</html:form>
										</tr>
									</table>
									<!-- action buttons end -->
								</td>
							</tr>
					</table>

				</td>
			</tr>
		<logic:present name="<%=DisplayConstants.LOGIN_WORKFLOW%>">
			<%
						if (DisplayConstants.GRID_WORKFLOW.equalsIgnoreCase((String) request
						.getSession().getAttribute(DisplayConstants.LOGIN_WORKFLOW))) {
			%>
			
			<tr>
				<td class="formMessage" colspan="1">
				</td>
				<td class="formMessage" colspan="3">
					Dont have a local (CSM) Account?. Click New User Creation button to proceed. 
					<html:form action="/NewCsmUser">
						<input class="actionButton" type="submit" value='<bean:message key="label.new_grid_user"/>' />
					</html:form>
				</td>
			</tr>
			<%
			}
			%>
		</logic:present>
		
		</table>
		
		
	</td>
</tr>
