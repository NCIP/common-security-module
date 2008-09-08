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
<%@ page import="gov.nih.nci.security.cgmm.webapp.form.NewGridUserForm"%>

<tr>

	<td valign="top" class="contentPage">
		&nbsp;<table summary="" cellpadding="3" cellspacing="0" border="0"
			width="500">
			
				<tr>
					<td class="formMessage" colspan="3">
						Please provide details to create a new Dorian account. Click submit to attempt creation of new account in Dorian.
						<br>
					</td>
				</tr>

				<tr>
					<td class="formMessage" colspan="3">
						
						<bean:message key="label.required_indicator" />
					</td>
				</tr>
				<tr>
					<td class="formTitle" height="20" colspan="3">
						<bean:message key="label.new_grid_user" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<font color="red""> <html:errors />
						</font>
					</td>
				</tr>
				<!-- Input boxes begin.  -->

				<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" id="newGridUserForm" type="NewGridUserForm"  />

				<tr>
					
					
					<td class=formLabel >
						<LABEL for=firstName>
							<bean:message key="label.new_user_first_name" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getFirstName()%>
					</td>
				</tr>
				<tr>
					
					<td class=formLabel width="30%">
						<LABEL for=lastName>
							<bean:message key="label.new_user_last_name" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getLastName()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=userName>
							<bean:message key="label.new_user_name" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getUserName()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=password>
							<bean:message key="label.new_user_password" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getPassword()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=email>
							<bean:message key="label.new_user_email" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getEmail()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=phone>
							<bean:message key="label.new_user_phone" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getPhone()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=organization>
							<bean:message key="label.new_user_organization" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getOrganization()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=address1>
							<bean:message key="label.new_user_address_1" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getAddress1()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=address2>
							<bean:message key="label.new_user_address_2" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getAddress2()%>
					</td>
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=city>
							<bean:message key="label.new_user_city" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getCity()%>
					</td>
				</tr>
				<bean:define name="<%=DisplayConstants.USA_STATES_MAP%>" id="statesMap" />
				<tr>
					<td class=formLabel><LABEL for=field3><bean:message key="label.new_user_state" /></LABEL></td>
					<td class=formField>
						<%=newGridUserForm.getState()%>
					</td>					
				</tr>
				<tr>
					<td class=formLabel width="30%">
						<LABEL for=postalCode>
							<bean:message key="label.new_user_postal_code" />
						</LABEL>
					</td>
					<td class=formField>
						<%=newGridUserForm.getPostalCode()%>
					</td>
				</tr>
				<bean:define name="<%=DisplayConstants.COUNTRY_MAP%>" id="countryMap" />
				<tr>

						<td class=formLabel><LABEL for=field3><bean:message key="label.new_user_country" /></LABEL></td>
						<td class=formField>
							<%=newGridUserForm.getCountry()%>
						</td>					
				</tr>
				<tr>
					<td align="right" colspan="3">
						<!-- action buttons begins -->
						<table cellpadding="4" cellspacing="0" border="0">
							<tr>
								
								<html:form action="/NewGridUser">
								<html:hidden property="activity" value="ok"/>
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

