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

<bean:define name="<%=DisplayConstants.HOST_APPLICATION_NAME%>"
	id="hostApplicationName" />
<logic:present name="<%=DisplayConstants.STANDALONE_MODE%>">
	<bean:define name="<%=DisplayConstants.STANDALONE_MODE%>" id="STANDALONE_MODE" />
</logic:present>
<bean:define name="<%=DisplayConstants.GRID_PROXY_ID%>"
	id="gridID" />
	
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
																<h3>
																	CSM to GAARDS Account Migration (CGMM)
																</h3>
																You have successfully migrated your <%=hostApplicationName1%> Local (CSM) account to GRID account. <br>
																Remember, you can no longer login using your <%=hostApplicationName1%> Local (CSM) account and shall now use your GRID account to login to <%=hostApplicationName1%>.
																<br>
																<br>
																Login using this GRID ID for further login attempts.
																<br>
																<h3>
																	<%=gridID%>
																</h3>
																<br>
																
																<logic:equal name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" value="true"> 
																	The account migration was successful. Please log in to <%=hostApplicationName1%> with your Grid account.
																</logic:equal>
																<br>
															</td>
														</tr>

														<logic:equal name="<%=DisplayConstants.STANDALONE_MODE%>" value="false"> 
															<tr>
																<td class="home" colspan="3" width="100%">
																	<logic:equal name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" value="true"> 
																		<html:form action="/ConfirmMigration">
																			<html:submit style="actionButton">Click to go to <bean:write name="hostApplicationName"/> Login page</html:submit>
																		</html:form>
																	</logic:equal>
																	<logic:equal name="<%=DisplayConstants.ALTERNATE_BEHAVIOR%>" value="false"> 
																		<html:form action="/ConfirmMigration">
																			<html:submit style="actionButton">Log in to <bean:write name="hostApplicationName"/></html:submit>
																		</html:form>
	
																	</logic:equal>
	
																	
																</td>
																
															</tr>
														</logic:equal>
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
