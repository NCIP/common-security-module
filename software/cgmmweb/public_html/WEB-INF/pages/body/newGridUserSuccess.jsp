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
		<table summary="" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td align=center>
					&nbsp;
					<table summary="" cellpadding="3" cellspacing="1" border="0"
						width="500">

						<tr>
							<td class="home" colspan="3">
								The new caGrid Account details are shown below.
								<br>
							</td>
						</tr>


						<tr>
							<td class="home" height="20" colspan="3">
								<h3>
									New caGrid Account Details
								</h3>
							</td>
						</tr>

						<!-- Input boxes begin.  -->

						<bean:define name="<%=DisplayConstants.CURRENT_FORM%>"
							id="newGridUserForm" type="NewGridUserForm" />

						<tr>


							<td>
								<LABEL for=firstName>
									<bean:message key="label.new_user_first_name" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getFirstName()%>
							</td>
						</tr>
						<tr>

							<td width="30%">
								<LABEL for=lastName>
									<bean:message key="label.new_user_last_name" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getLastName()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<h3>
									<LABEL for=userName>
										<bean:message key="label.new_user_name" />
									</LABEL>
								</h3>
							</td>
							<td>
								<h3>
									<%=newGridUserForm.getUserName()%>
								</h3>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=password>
									<bean:message key="label.new_user_password" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getPassword()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=email>
									<bean:message key="label.new_user_email" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getEmail()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=phone>
									<bean:message key="label.new_user_phone" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getPhone()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=organization>
									<bean:message key="label.new_user_organization" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getOrganization()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=address1>
									<bean:message key="label.new_user_address_1" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getAddress1()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=address2>
									<bean:message key="label.new_user_address_2" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getAddress2()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=city>
									<bean:message key="label.new_user_city" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getCity()%>
							</td>
						</tr>
						<bean:define name="<%=DisplayConstants.USA_STATES_MAP%>"
							id="statesMap" />
						<tr>
							<td>
								<LABEL for=field3>
									<bean:message key="label.new_user_state" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getState()%>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<LABEL for=postalCode>
									<bean:message key="label.new_user_postal_code" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getPostalCode()%>
							</td>
						</tr>
						<bean:define name="<%=DisplayConstants.COUNTRY_MAP%>"
							id="countryMap" />
						<tr>

							<td>
								<LABEL for=field3>
									<bean:message key="label.new_user_country" />
								</LABEL>
							</td>
							<td>
								<%=newGridUserForm.getCountry()%>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="3">
								<!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td>
											<html:form action="/NewGridUser">
												<html:submit style="actionButton" value="Confirm Migration" />
											</html:form>
										</td>
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
