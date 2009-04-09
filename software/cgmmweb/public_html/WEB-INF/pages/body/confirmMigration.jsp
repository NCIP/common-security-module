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

	String serverPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	String hostURL = serverPath + "/"
			+ session.getAttribute("HOST_APPLICATION_NAME")
			+ session.getAttribute("HOST_APPLICATION_PUBLIC_HOME_PAGE");
			

	String hostApplicationName1 = (String)session.getAttribute("HOST_APPLICATION_NAME") ;
	hostApplicationName1= hostApplicationName1.toUpperCase();			
%>

<bean:define name="<%=DisplayConstants.LOGIN_OBJECT%>" id="csmID" />
<bean:define name="<%=DisplayConstants.GRID_PROXY_ID%>" id="gridID" />
<tr>
	<td width="100%" valign="top">
		<!-- target of anchor to skip menus -->

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
															CSM to GAARDS Account Migration Module (CGMM)
														</h3>
														You have requested to migrate your <%=hostApplicationName1%> Local (CSM) account to GRID
														account.
														<br>
														<br>
													</td>
												</tr>
												<tr>
													<td colspan="3" class="txtHighlight">
														<html:errors />
													</td>
												</tr>

												<tr>
													<td class="home" colspan="3" align="center">
														<h3>
															Local (CSM) Account:
															<%=csmID%>
														</h3>
													</td>
												</tr>
												<tr>
													<td class="home" align="center" colspan="3">
														TO
													</td>
												<tr>
													<td class="home" colspan="3" align="center">
														&nbsp;
														<br>
														<h3>
															GRID Account:
															<%=gridID%>
														</h3>
													</td>
												</tr>

												<tr>
													<td class="home" colspan="1" width="45%" align="center">
														<html:form action="/MenuSelection">
															<html:submit style="actionButton"
																value="Cancel Migration" />
												
														</html:form>
													</td>
													<td class="home" colspan="1" width="10%">
													</td>
													<td class="home" colspan="1" width="45%" align="center">
														<html:form styleId="menuForm" action="/ConfirmMigration">
															<html:hidden property="tableId" value="error" />
															<html:submit style="actionButton"
																value="Yes, Migrate my CSM Account" />
															
														</html:form>
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
