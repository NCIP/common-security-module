<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>


<table summary="" cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top"><!-- target of anchor to skip menus --><a
			name="content" /></a>
		<table summary="" cellpadding="0" cellspacing="0" border="0"
			class="contentPage" width="100%" height="100%">

			<!-- banner begins -->
			<tr>
				<td class="bannerHome"><img src="images/bannerHome.gif" height="140"></td>
			</tr>
			<!-- banner begins -->

			<tr>
				<td height="100%"><!-- target of anchor to skip menus --><a
					name="content" /></a>

				<table summary="" cellpadding="0" cellspacing="0" border="0"
					height="100%">
					<tr>
						<td width="70%"><!-- welcome begins -->
						<table summary="" cellpadding="0" cellspacing="0" border="0"
							height="100%">
							<tr>
								<td class="welcomeTitle" height="20">WELCOME TO THE USER
								PROVISIONING TOOL</td>
							</tr>
							<tr>
								<td class="welcomeContent" valign="top">Welcome to the User
								Provisioning Tool (UPT). This user interface tool is designed so
								that developers can easily configure an application's
								authorization data in the Common Security Module (CSM) database.
								With the click of a few buttons you may control which users can
								access protected elements or operations of your application.
								This tool combined with the CSM allows for fine-grain security
								control, and will eventually provide other features such as
								single sign-on. The UPT is divided into six major sections:
								Groups, Privileges, Protection Groups, Roles, and Users. From
								these sections you may perform basic functions such as modify,
								delete, or create, and you may also manage associations between
								the objects. For example you may assign Privileges to a Role.
								Please begin by logging in, then select one of the menu options
								or follow our Recommended Workflow. Enjoy.</td>
							</tr>
						</table>
						<!-- welcome ends --></td>
						<td valign="top" width="30%"><!-- sidebar begins -->
						<table summary="" cellpadding="0" cellspacing="0" border="0"
							height="100%">
							<html:form action="/Login">
								<!-- login begins -->
								<tr>
									<td valign="top">
									<table summary="" cellpadding="2" cellspacing="0" border="0"
										width="100%" class="sidebarSectionLogin">
										<tr>
											<td class="sidebarTitle" height="20">LOGIN TO U.P.T.</td>
										</tr>
										<tr>
											<td class="sidebarContent">
											<table cellpadding="2" cellspacing="0" border="0">
												<tr>
													<td colspan="2"><html:errors /></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label for="loginId">LOGIN
													ID</label></td>
													<td class="formFieldLogin"><html:text style="formField"
														size="14" property="loginId" value="" /></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="password">PASSWORD</label></td>
													<td class="formFieldLogin"><html:password style="formField"
														size="14" property="password" value="" /></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="applicationContextName">APPLICATION NAME</label></td>
													<td class="formFieldLogin"><html:text style="formField"
														size="14" property="applicationContextName" value="" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><html:submit style="actionButton" value="Login" /></td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<!-- login ends -->
							</html:form>
							<!-- what's new begins -->
							<tr>
								<td valign="top">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">WHAT'S NEW</td>
									</tr>
									<tr>
										<td class="sidebarContent">CSM is now available for the caCORE 3.0 release!</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- what's new ends -->

							<!-- did you know? begins -->
							<tr>
								<td valign="top">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									width="100%" height="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">DID YOU KNOW?</td>
									</tr>
									<tr>
										<td class="sidebarContent" valign="top">You can use CSM for all of your authentication and authorization needs.</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- did you know? ends -->

							<!-- spacer cell begins (keep for dynamic expanding) -->
							<tr>
								<td valign="top" height="100%">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									width="100%" height="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarContent" valign="top">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- spacer cell ends -->

						</table>
						<!-- sidebar ends --></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
