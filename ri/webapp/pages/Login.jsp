<%@ include file= "/pages/imports.jsp" %>
<html>
<head>
<title>Employee Records Management System</title>
<link rel="stylesheet" type="text/css" href="pages/styleSheet.css" />
<script src="script.js" type="text/javascript"></script>
</head>
<body>

<html:form method="post" action="login" >
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
										<tr>
                <td valign="top">
                  <!-- target of anchor to skip menus --><a name="content" /></a>
                  <table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">

                    <!-- banner begins -->
                    <tr>
                      <td class="bannerHome"><img src="pages/images/bannerHome.gif" height="140"></td>
                    </tr>
                    <!-- banner begins -->

                    <tr>
                      <td height="100%">

												<!-- target of anchor to skip menus --><a name="content" /></a>

                        <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
                          <tr>
                            <td width="70%">

                              <!-- welcome begins -->
                              <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
                                <tr>
                                  <td class="welcomeTitle" colspan="3" height="20">WELCOME
                                    TO THE EMPLOYEE RECORDS MANAGEMENT SYSTEM</td>
                                </tr>
                                <tr>
								                                  <td colspan="3" class="welcomeContent" valign="top" height="100"> Welcome
								                                    to the Employee Records Management System (ERMS) for ABC Company - your ONE STOP solution for all your employee record needs.  Employee records hold all the vital information a company needs to manage each employee.  It's important that this information is up to date and complete.  ERMS provides personalized access to records so that you get the right amount of access based on your needs. The system is designed for <b>Human Resources, Management, and Employees:
                                </tr>




                                <tr>
                                <td valign="top">
																                                      <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
																                                      <tr>
																                                        <td class="sidebarTitle" height="20">HUMAN RESOURCES</td>
																                                      </tr>
																                                      <tr>
																                                      								<td class="sidebarContent" align="center" height="91"><img src="pages/images/catbert_use.gif"></td>
																                                      	</tr>

																                                      <tr>
																                                        <td class="sidebarContent" valign="top">Manage all employee records from ERMS.  Modify salaries at will.  Remove the obsolete records of terminated employees and create new profiles for their cheaper replacements.</td>
																                                      </tr>
																                                      </table>
                                  </td>
                                  <td valign="top">
								                                      <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
								                                      <tr>
								                                        <td class="sidebarTitle" height="20">MANAGEMENT</td>
								                                      </tr>
								                                       <tr>
																	  																                                      								<td class="sidebarContent" align="center" height="91"><img src="pages/images/boss_use.gif"></td>
																                                      	</tr>
								                                      <tr>
								                                        <td class="sidebarContent" valign="top">Assign projects and track assignments.  View records of your entire team, and gather the vital contact information you need to call your people to action at night and on the weekends.</td>
								                                      </tr>
								                                      </table>
                                  </td>
                                  <td valign="top">
								  								                                      <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
								  								                                      <tr>
								  								                                        <td class="sidebarTitle" height="20">EMPLOYEES</td>
								  								                                      </tr>
								  								                                       <tr>
																									  																                                      								<td class="sidebarContent" align="center" height="91"><img src="pages/images/dilbert_use.gif"></td>
																                                      	</tr>
								  								                                      <tr>
								  								                                        <td class="sidebarContent" valign="top">View and modify your contact information when your address and phone number changes.  Following each annual review, watch your salary daily to confirm that it doesn't increase.</td>
								  								                                      </tr>
								  								                                      </table>
                                  </td>



                                </tr>
                              </table>
                              <!-- welcome ends -->

                            </td>
                            <td valign="top" width="30%">

                              <!-- sidebar begins -->
                              <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">

                                <!-- login begins -->
                                <tr>
                                  <td valign="top">
                                    <table summary="" cellpadding="2" cellspacing="0" border="0" width="100%" class="sidebarSection">
                                      <tr>
                                        <td class="sidebarTitle" height="20">LOGIN TO SITE</td>
                                      </tr>
                                      <tr>
                                        <td class="sidebarContent">
                                          <table cellpadding="2" cellspacing="0" border="0">
                                            <tr>
                                              <td class="sidebarLogin" align="right"><label for="loginID">LOGIN ID</label></td>
                                              <td class="formFieldLogin"><html:text property="loginID" size="14"/></td>
                                            </tr>
                                            <tr>
                                              <td class="sidebarLogin" align="right"><label for="password">PASSWORD</label></td>
                                              <td class="formFieldLogin"><html:text property="password" size="14"/></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td> <html:submit><bean:message key="button.login"/></html:submit></td>
                                            </tr>
                                          </table>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <!-- login ends -->



                                <!-- spacer cell begins (keep for dynamic expanding) -->
                                <tr><td valign="top" height="100%">
                                    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
                                    <tr>
                                      <td class="sidebarContent" valign="top">&nbsp;</td>
                                    </tr>
                                    </table>
																</td></tr>
                                <!-- spacer cell ends -->

                              </table>
                              <!-- sidebar ends -->

                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
									</table>
									
 </html:form>
</html>