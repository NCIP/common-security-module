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

<%@ page import="java.util.*"%>
<%@ page import="gov.nih.nci.security.cgmm.webapp.DisplayConstants"%>


<tr>
	<td valign="top" class="contentPage">
		&nbsp;&nbsp;&nbsp;
		<br>
		<table summary="" cellpadding="3" cellspacing="0" border="0"
			width="500">
			<html:form styleId="gridLoginForm" action="/GridLogin"
				focus="loginID">


				<tr>
					<td class="formMessage" colspan="3">
						Provide your credentials for authentication and select
						Authentication Source. If you do not have GAARDS based credentials
						for any of the available Authentication Source then select the
						Home Menu tab to start over.

					</td>
				</tr>
				<tr>
					<td class="formTitle" height="20" colspan="3">
						<bean:message key="label.grid_login" />
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<font color="red""> <html:errors />
						</font>
					</td>
				</tr>
				<tr>

					<td class=formLabel width="30%">
						<LABEL for=recordCount>
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
				<bean:define name="<%=DisplayConstants.AUTHENTICATION_SERVICE_MAP%>"
					id="authenticationServiceMap" />
				<tr>

					<td class=formLabel>
						<LABEL for="field3">
							<bean:message key="label.authentication_service" />
						</LABEL>
					</td>
					<td class=formField>
						<html:select property="authenticationServiceURL">
							<html:options collection="authenticationServiceMap"
								property="value" labelProperty="key" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td align="right" colspan="3">
						<!-- action buttons begins -->
						<table cellpadding="4" cellspacing="0" border="0" align="right">
							<tr>
								<td align="right">
									<input class="actionButton" type="submit"
										value='<bean:message key="label.submit_button"/>' />
								</td>
								</html:form>
								<html:form action="/GridLogin">
									<td align="right">
										<input class="actionButton" type="submit"
											value='<bean:message key="label.reset_button"/>' />
									</td>
								</html:form>
							</tr>
						</table>
						<!-- action buttons end -->
					</td>
				</tr>
				<tr>
					<td class="formMessage" colspan="2" align="left">
						<logic:present name="<%=DisplayConstants.LOGIN_WORKFLOW%>">
							<%
										if (DisplayConstants.CSM_WORKFLOW
										.equalsIgnoreCase((String) request.getSession()
										.getAttribute(DisplayConstants.LOGIN_WORKFLOW))) {
							%>
			OR Proceed to New Dorian Account creation. Click the New User Creation button below.
			<html:form action="/NewGridUser">
								<input class="actionButton" type="submit"
									value='<bean:message key="label.new_grid_user"/>' />
							</html:form>
							<%
							}
							%>
						</logic:present>
					</td>


				</tr>
		</table>

	</td>
</tr>

