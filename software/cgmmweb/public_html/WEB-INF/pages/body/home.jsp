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
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

String serverPath=request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
String hostURL = serverPath+"/"+session.getAttribute("HOST_APPLICATION_NAME")+session.getAttribute("HOST_APPLICATION_PUBLIC_HOME_PAGE");

 %>
 <bean:define name="<%=DisplayConstants.HOST_APPLICATION_NAME%>"	id="hostApplicationName" />
<bean:define name="<%=DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE%>"	id="hostApplicationPublicHomePage" />
<tr>
	<td width="100%" valign="top">
		<!-- target of anchor to skip menus -->
		<a name="content" />
			<table summary="" cellpadding="0" cellspacing="0" border="0" width="600" height="100%">
				<!-- banner begins -->
				<tr>
					<td class="bannerHome">
						<img src="images/bannerHome.gif" height="140">
					</td>
				</tr>
				<!-- banner begins -->
				<tr>
					<td height="100%">
						<!-- target of anchor to skip menus -->
						<a name="content" />
							<table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
								<tr>
									<td valign="top">
										<!-- sidebar begins -->
										<table summary="" cellpadding="0" cellspacing="0" border="0"
											height="100">
											<!-- login ends -->
											<!-- what's new begins -->
											<tr>
												<td valign="top">
													<table cellpadding="0" cellspacing="0" border="0"
														class="contentBegins">
														<!--recommended flow-->
														<tr class="home">
															<td class="home" colspan="3">
																<h3>
																	Recommended Workflow
																</h3>
																To login to the application either of the following flows are
																recommended.
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="3" align="center">
																<div align="left">
																	<b> Login using local account (CSM) credentials.</b>
																</div>
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																&nbsp;
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<!-- Section A -->
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>1.</b> Login using local account credentials
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>2a.</b>Either Login using caGrid account credentials
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>2b.</b>or create new caGrid account
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="3">
																<b>Login using caGrid account credentials.</b>
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<!-- Step 2 A -->
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>1.</b> Login using caGrid account credentials.
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																&nbsp;
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>2a.</b> Login using local account credentials
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>

														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>2b.</b> Request creation of new local account
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
														
														<tr class="home">
															<td class="home" colspan="4">
																&nbsp;
															</td>
														</tr>
														<tr class="home">
															<td class="home" width="5%" align="center">
																&nbsp;
															</td>
															<td class="home" colspan="2">
																<b>Click here to go to back to <a href="<%=hostURL%>"><%=hostApplicationName%> Public Home page</a>.
																</b>
															</td>
															<td class="home" width="27%">
																&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td valign="top">
													<table summary="" cellpadding="0" cellspacing="0"
														border="0" width="100%" height="100"
														class="sidebarSection">
														<!-- csm login begins -->
														<html:form styleId="CsmLoginForm" action="/CsmLogin"
															focus="loginID">
															<tr>
																<td valign="top">
																	<table summary="" cellpadding="2" cellspacing="0"
																		border="0" width="100%" class="sidebarSectionLogin">
																		<tr>
																			<td class="sidebarTitle" height="20">
																				CSM LOGIN
																			</td>
																		</tr>
																		<tr>
																			<td class="sidebarContent"> 
																				<table cellpadding="2" cellspacing="0" border="0" width="100%">
																					<tr>
																						<td colspan="2">
																							<html:errors />
																						</td>
																					</tr>
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
																							<html:submit style="actionButton" value="Login" />
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
														<!-- grid login begins -->
														<html:form styleId="gridLoginForm" action="/GridLogin">
															<tr>
																<td valign="top">
																	<table summary="" cellpadding="2" cellspacing="0"
																		border="0" width="100%" class="sidebarSectionLogin">
																		<tr>
																			<td class="sidebarTitle" height="20">
																				GRID LOGIN
																			</td>
																		</tr>
																		<tr>
																			<td class="sidebarContent">
																				<table cellpadding="2" cellspacing="0" border="0" width="100%">
																					<tr>
																						<td colspan="2">
																							<html:errors />
																						</td>
																					</tr>
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
																							<html:select property="authenticationServiceURL">
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
																							<html:submit style="actionButton" value="Login" />
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</html:form>
														<!--  grid login ends -->
														<tr>
															<td class="sidebarTitle" height="20">
																DID YOU KNOW?
															</td>
														</tr>
														<tr>
															<td class="sidebarContent" valign="top">
																<li>
																	User can select the CSM or GRID login from the Menu
																	tabs.
																<li>
																	User can click on Home Menu tab to start over.
																<li>
																    Click here to go to back to <a href="<%=hostURL%>"><%=hostApplicationName%> Public Home page</a>.
																	
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
