<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<table cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top"><!-- target of anchor to skip menus --><a
			name="content" /></a>
		<table cellpadding="0" cellspacing="0" border="0"
			class="contentPage" width="100%" height="100%">

			<!-- banner begins -->
			<tr>
				<td class="bannerHome"><img src="images/bannerHome.gif" height="140" alt="CSM UPT Home"></td>
			</tr>
			<!-- banner begins -->

			<tr>
				<td height="100%"><!-- target of anchor to skip menus --><a
					name="content" /></a>

				<table cellpadding="0" cellspacing="0" border="0"
					height="100%">
					<tr>
						<td width="70%"><!-- welcome begins -->
						<table summary="Welcome" cellpadding="0" cellspacing="0" border="0"
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
								Users, Groups, Protection Groups, Protection Elements,
								Roles and Privileges. From these sections you may perform 
								basic functions such as modify,	delete, or create, 
								and you may also manage associations between
								the objects. For example you may assign Privileges to a Role.
								Please begin by logging in, then select one of the menu options
								or follow our Recommended Workflow.</td>
							</tr>
						</table>
						<!-- welcome ends --></td>
						<td valign="top" width="30%"><!-- sidebar begins -->
						<table cellpadding="0" cellspacing="0" border="0"
							height="100%">
							<s:form name="loginForm" action="Login.action"  focus="loginId" theme="simple">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/Login'/>"/>
								<!-- login begins -->
								<tr>
									<td valign="top">
									<table summary="Login" cellpadding="2" cellspacing="0" border="0"
										width="100%" class="sidebarSectionLogin">
										<tr>
											<td class="sidebarTitle" height="20">LOGIN TO U.P.T.</td>
										</tr>
										<tr>
											<td class="sidebarContent">
											<table cellpadding="2" cellspacing="0" border="0">
												<tr>
													<td class="infoMessage" colspan="2">
													<s:if test="hasActionMessages()">
													      <s:actionmessage/>
													</s:if>			  

													</td>
												</tr>
												<tr>
													<td class="errorMessage" colspan="2">
													<s:if test="hasActionErrors()">
													      <s:actionerror/>
													</s:if>			  

													</td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label for="loginId">LOGIN
													ID</label></td>
													<td class="formFieldLogin"><s:textfield
														size="14" name="loginForm.loginId" /></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="password">PASSWORD</label></td>
													<td class="formFieldLogin"><s:password
														size="14" name="loginForm.password" /></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="applicationContextName">APPLICATION NAME</label></td>
													<td class="formFieldLogin"><s:textfield
														size="14" name="loginForm.applicationContextName" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><s:submit style="actionButton" value="Login" /></td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<!-- login ends -->
								
							</s:form>
							<!-- what's new begins -->
							<tr>
								<td valign="top">
								<table summary="What is new" cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">WHAT'S NEW IN 5.0</td>
									</tr>
									<tr>
										<td class="sidebarContent">
											<li>NIH Password Policy
											<li>Configurable UPT
											<li>Several API enhancements and bug fixes
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- what's new ends -->

							<!-- did you know? begins -->
							<tr>
								<td valign="top">
								<table summary="Did you know?" cellpadding="0" cellspacing="0" border="0"
									width="100%" height="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">DID YOU KNOW?</td>
									</tr>
									<tr>
										<td class="sidebarContent" valign="top">
										<li>You can organize your Protection Elements with the new Protection Element Type field. 
										<li>A user's Login ID can no longer be edited once it has been created.
										
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- did you know? ends -->

							<!-- spacer cell begins (keep for dynamic expanding) -->
							<tr>
								<td valign="top" height="100%">
								<table cellpadding="0" cellspacing="0" border="0"
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
