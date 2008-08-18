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
<%@ page
	import="gov.nih.nci.security.migrate.constants.CGMMConstants"%>
<%@ page import="gov.nih.nci.security.migrate.webapp.viewobjects.*"%>

<tr>
	<td valign="top" class="contentPage">&nbsp;&nbsp;&nbsp; 
		<br><table summary="" cellpadding="3" cellspacing="0" border="0" width="500">
			<html:form styleId="gridLoginForm" action="/GridLogin" focus="loginID">


				<tr>
					<td class="formMessage" colspan="3">
						GRID LOGIN
						<bean:message key="label.required_indicator" />
					</td>
				</tr>
				<tr>
					<td class="formTitle" height="20" colspan="3">
						<bean:message key="label.grid_login" />
					</td>
				</tr>
				

 

				

				 <tr>
						<td colspan="3"><font color="red"">
							<html:errors/></font>
						</td>
			 	</tr>
				<tr>
					
					<td class=formLabel width="30%" >
						<LABEL for=recordCount>
							<bean:message key="label.login_id" />
						</LABEL>
					</td>
					<td class=formField >
							<INPUT class=formField id=loginID size=40 name=loginID >
					</td>
				</tr>
				<tr>
					
					<td class=formLabel width="30%" >
						<LABEL for=password>
							<bean:message key="label.login_password" />
						</LABEL>
					</td>
					<td class=formField >
							<INPUT class=formField id=password size=40  type=password name=password>
					</td>
				</tr>
				<bean:define name="<%=CGMMConstants.AUTHENTICATION_SERVICE_MAP%>" id="authenticationServiceMap" />
				<tr>

						<td class=formLabel><LABEL for="field3"><bean:message
							key="label.authentication_service" /></LABEL></td>
						<td class=formField>
							<html:select property="authenticationServiceURL">
								<html:options collection="authenticationServiceMap" property="value" labelProperty="key"/>
							</html:select>
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
								<html:form action="/GridLogin">
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
		<logic:present name="<%=CGMMConstants.LOGIN_WORKFLOW%>">
		<%
			if(CGMMConstants.CSM_WORKFLOW.equalsIgnoreCase((String)request.getSession().getAttribute(CGMMConstants.LOGIN_WORKFLOW))){
		 %>
			<br>OR <br>Proceed to New Dorian Account creation.
			<html:form action="/NewGridUser">
				<input class="actionButton" type="submit" value='<bean:message key="label.new_grid_user"/>' />
			</html:form>
		<%} %>
		</logic:present>
	</td>
</tr>

