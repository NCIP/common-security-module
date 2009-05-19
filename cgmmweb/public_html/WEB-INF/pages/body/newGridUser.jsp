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
<%@ page import="gov.nih.nci.security.cgmm.webapp.form.NewGridUserForm"%>
<logic:present name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>">
	<bean:define name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" id="ALTERNATE_BEHAVIOR" />
</logic:present>
<tr>
	<td valign="top" class="contentPage">
		<table summary="" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td align=center>
					&nbsp;
					<table summary="" cellpadding="3" cellspacing="0" border="0"
						width="500">
						<html:form styleId="newGridUserForm" action="/NewGridUser">
							<tr>
								<td class="home" colspan="3">
									Please provide details to create a new caGRID account. Click
									submit to attempt request/creation of new account.
									<br>
								</td>
							</tr>

							<tr>
								<td class="formMessage" colspan="3">

									<bean:message key="label.required_indicator" />
								</td>
							</tr>
							<tr>
								<td colspan="3" class="txtHighlight">
									<html:errors />
								</td>
							</tr>

							<tr>
								<td class="formTitle" height="20" colspan="3">
									<bean:message key="label.new_grid_user" />
								</td>
							</tr>
							<!-- Input boxes begin.  -->

							<bean:define name="<%=DisplayConstants.CURRENT_FORM%>"
								id="newGridUserForm" type="NewGridUserForm" />

							<tr>


								<td class=formLabel>
									<LABEL for=firstName>
										<bean:message key="label.new_user_first_name" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=firstName size=40 name=firstName
										value="<%=newGridUserForm.getFirstName()%>" />
								</td>
							</tr>
							<tr>

								<td class=formLabel width="30%">
									<LABEL for=lastName>
										<bean:message key="label.new_user_last_name" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=lastName size=40 name=lastName
										value="<%=newGridUserForm.getLastName()%>" />
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=userName>
										<bean:message key="label.new_user_name" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=userName size=40 name=userName
										value="<%=newGridUserForm.getUserName()%>" />
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=password>
										<bean:message key="label.new_user_password" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=password size=40 type=password
										name=password value="<%=newGridUserForm.getPassword()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=email>
										<bean:message key="label.new_user_email" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=email size=40 name=email
										value="<%=newGridUserForm.getEmail()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=phone>
										<bean:message key="label.new_user_phone" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=phone size=12 name=phone
										value="<%=newGridUserForm.getPhone()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=organization>
										<bean:message key="label.new_user_organization" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=organization size=40
										name=organization
										value="<%=newGridUserForm.getOrganization()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=address1>
										<bean:message key="label.new_user_address_1" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=address1 size=40 name=address1
										value="<%=newGridUserForm.getAddress1()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=address2>
										<bean:message key="label.new_user_address_2" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=address2 size=40 name=address2
										value="<%=newGridUserForm.getAddress2()%>">
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=city>
										<bean:message key="label.new_user_city" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=city size=40 name=city
										value="<%=newGridUserForm.getCity()%>">
								</td>
							</tr>
							<bean:define name="<%=DisplayConstants.USA_STATES_MAP%>"
								id="statesMap" />
							<tr>
								<td class=formLabel>
									<LABEL for=field3>
										<bean:message key="label.new_user_state" />
									</LABEL>
								</td>
								<td class=formField>
									<html:select property="state">
										<html:options collection="statesMap" property="value"
											labelProperty="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class=formLabel width="30%">
									<LABEL for=postalCode>
										<bean:message key="label.new_user_postal_code" />
									</LABEL>
								</td>
								<td class=formField>
									<INPUT class=formField id=postalCode size=40 name=postalCode
										value="<%=newGridUserForm.getPostalCode()%>">
								</td>
							</tr>
							<bean:define name="<%=DisplayConstants.COUNTRY_MAP%>"
								id="countryMap" />
							<tr>

								<td class=formLabel>
									<LABEL for=field3>
										<bean:message key="label.new_user_country" />
									</LABEL>
								</td>
								<td class=formField>
									<html:select property="country">
										<html:options collection="countryMap" property="value"
											labelProperty="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right" colspan="3">
									<!-- action buttons begins -->
									<table cellpadding="4" cellspacing="0" border="0">
										<tr>
											<td colspan=2>
												<html:submit style="actionButton" value="Request New Account" />
												<%--
									<input class="actionButton" type="submit"
										value='<bean:message key="label.submit_button"/>' />
								--%>
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
		</table>
	</td>
</tr>
