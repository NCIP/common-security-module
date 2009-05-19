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

<%

	String hostApplicationName1 = (String)session.getAttribute("HOST_APPLICATION_NAME") ;
	hostApplicationName1= hostApplicationName1.toUpperCase();
%>
<logic:present name="<%=DisplayConstants.HOST_APPLICATION_NAME%>">
	<bean:define name="<%=DisplayConstants.HOST_APPLICATION_NAME%>" id="hostApplicationName" />
</logic:present>
<logic:present name="<%=DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE%>">
<bean:define
	name="<%=DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE%>" id="hostApplicationPublicHomePage" />
</logic:present>
<logic:present name="<%=DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE%>">
<bean:define
	name="<%=DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE%>" id="hostApplicationPublicHomePage" />
</logic:present>
<logic:present name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>">
	<bean:define name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" id="ALTERNATE_BEHAVIOR" />
</logic:present>
<logic:present name="<%=DisplayConstants.STANDALONE_MODE%>">
	<bean:define name="<%=DisplayConstants.STANDALONE_MODE%>" id="STANDALONE_MODE" />
</logic:present>


<tr>
	<td width="100%" valign="top">
		<!-- target of anchor to skip menus -->
		<a name="content" />
			<table summary="" cellpadding="0" cellspacing="0" border="0"
				width="600" height="100%">
				<!-- banner begins -->
				<tr>
					<td class="bannerHome">
						<img src="images/bannerHome.gif" height="140">
					</td>
				</tr>
				<!-- banner ends -->
				<tr>
					<td height="100%">
						<!-- target of anchor to skip menus -->
						<a name="content" />
							<table summary="" cellpadding="0" cellspacing="0" border="0"
								height="100%">
								<tr>
									<td class="home" width="5%">
										&nbsp;
									</td>
									<td valign="top">
										<table summary="" cellpadding="0" cellspacing="0" border="0"
											height="100">
											<tr>
												<td valign="top">
													<table cellpadding="0" cellspacing="0" border="0"
														class="contentBegins">

														<tr class="home">

															<td class="home" colspan="3">
																<br>
																<h3 >
																	Local (CSM) Account To Grid Account Migration
																</h3>
																Welcome to the CSM GAARDS Account Migration Module. The CGMMWeb is a web interface that allows automatic
																migration <%=hostApplicationName1%> user's Local (CSM) account to a GAARDS (caGrid) account. 
																<br><br>
																<logic:equal name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" value="false"> 
																	
																	If you have a local <%=hostApplicationName1%> (CSM) account then proceed to migrate it to a new or existing GAARDS (caGrid) account.
																	<br><br>
																	Alternatively, if you have a GAARDS (caGrid) account then proceed to migrate it with an existing <%=hostApplicationName1%> local (CSM) account.
																	<br><br>
																	If you have already completed the migration process then proceed to Log in to the <%=hostApplicationName1%> application using GAARDS (caGrid) account information.
																	<br><br>
																</logic:equal>
															</td>
														</tr>
														<tr>
															<td colspan="3" class="txtHighlight">
																<html:errors />
															</td>
														</tr>

														<tr>

															<td class="home" colspan="3" width="90%">
																<table cellpadding="0" cellspacing="0" border="0"
																	class="contentBegins">
																	<tr>
																		<td valign="top">
																			<table summary="" cellpadding="0" cellspacing="0"
																				border="0" width="300" height="100%">

																				<!-- csm login begins -->
																				<html:form styleId="CsmLoginForm" action="/CsmLogin"
																					focus="loginID">
																					<tr>
																						<td valign="top">
																							<table summary="" cellpadding="2" cellspacing="0"
																								border="0" width="100%">
																								<tr>
																									<td align="center" height="20">
																										<h3>
																											Migrate this local <%=hostApplicationName1%> account
																										</h3>
																									</td>
																								</tr>
																								<tr>
																									<td>
																										<table cellpadding="2" cellspacing="0"
																											border="0" width="100%">
																											<%--
																											<tr>
																												<td colspan="2">
																													<html:errors />
																												</td>
																											</tr>
																											--%>
																											<tr>
																												<td class="sidebarLogin" align="right">
																													<label for="loginId">
																														LOGIN ID
																													</label>
																												</td>
																												<td class="formFieldLogin">
																													<html:text style="formField" size="14"
																														property="loginID" value="" />
																												</td>
																											</tr>
																											<tr>
																												<td class="sidebarLogin" align="right">
																													<label for="password">
																														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PASSWORD
																													</label>
																												</td>
																												<td class="formFieldLogin">
																													<html:password style="formField" size="14"
																														property="password" value="" />
																												</td>
																											</tr>
																											<tr>
																												<td>
																													&nbsp;
																												</td>
																												<td>
																													<html:submit style="actionButton"
																														value="Login" />
																														
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>
																							</table>
																						</td>
																					</tr>

																				</html:form>
																				<!--  csm login ends -->
																			</table>
																		</td>
																		<logic:equal name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" value="false"> 
																		<td valign="top" width="2%"></td>
																		<td valign="top" align="center">
																			OR
																			<br>
																			<table summary="" cellpadding="0" cellspacing="0"
																				border="1" width="1" height="100">
																				<tr>
																					<td>
																						<!-- Empty spacer to show vertical line -->
																					</td>
																				</tr>
																			</table>
																		</td>
																		<!-- OR Spacers -->
																		<td valign="top" width="2%"></td>
																		<td valign="top">
																			<table summary="" cellpadding="0" cellspacing="0"
																				border="0" width="300" height="100%">

																				<!-- grid login begins -->
																				<html:form styleId="gridLoginForm"
																					action="/GridLogin">
																					<tr>
																						<td valign="top">
																							<table summary="" cellpadding="2" cellspacing="0"
																								border="0" width="100%">
																								<tr>
																									<td align="center" height="20">
																										<h3>
																											Login to / Migrate a Grid Account
																										</h3>
																									</td>
																								</tr>
																								<tr>
																									<td>
																										<table cellpadding="2" cellspacing="0"
																											border="0" width="100%">
																											<%--<tr>
																												<td colspan="2">
																													<html:errors />
																												</td>
																											</tr>
																											--%>
																											<tr>
																												<td class="sidebarLogin" align="right">
																													<label for="loginId">
																														LOGIN ID
																													</label>
																												</td>
																												<td class="formFieldLogin">
																													<html:text style="formField" size="14"
																														property="loginID" value="" />
																												</td>
																											</tr>
																											<tr>
																												<td class="sidebarLogin" align="right">
																													<label for="password">
																														PASSWORD
																													</label>
																												</td>
																												<td class="formFieldLogin">
																													<html:password style="formField" size="14"
																														property="password" value="" />
																												</td>
																											</tr>
																											<bean:define
																												name="<%=DisplayConstants.AUTHENTICATION_SERVICE_MAP%>"
																												id="authenticationServiceMap" />
																											<tr>
																												<td class="sidebarLogin" align="right">
																													<label for="authenticationServiceURL">
																														AUTHENTICATION
																														<br>
																														SOURCE
																													</label>
																												</td>
																												<td class="formFieldLogin">
																													<html:select
																														property="authenticationServiceURL">
																														<html:options
																															collection="authenticationServiceMap"
																															property="value" labelProperty="key" />
																													</html:select>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													&nbsp;
																												</td>
																												<td>
																													<html:submit style="actionButton"
																														value="Login" />
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>
																							</table>
																						</td>
																					</tr>
																				</html:form>

																			</table>
																		</td>
																		</logic:equal>

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
							</table>
					</td>
				</tr>
			</table>
	</td>
</tr>
