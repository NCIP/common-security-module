<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%
String path = request.getContextPath();
String appProfileAction = path + "/AppUserLogin";
%>

<table width="100%"  valign="top" border="0" cellspacing="0" cellpadding="0" class="subhdrBG">
							<tr>
							
							<!-- news3 separate links depending on admin or super admin -->
							
							 <s:if test="#session['ADMIN_USER'] != null">
								 <td width="400" align="left"><a href="<s:url value="AdminHome.action"/>"><img src="images/appLogo.gif" alt="UPT Home" hspace="10" border="0"/></a></td>
							 </s:if>
							<s:else>	
								<td width="400" align="left"><a href="<s:url value="Home.action"/>"><img src="images/appLogo.gif" alt="UPT Home" hspace="10" border="0"/></a></td>
	 						 </s:else>								
							<!-- end home links -->	
							<s:if test="#session['LOGIN_OBJECT'] != null">
							<s:set var="loginObject" value="#session.LOGIN_OBJECT"/>
								<td width="200" align="right">
									<table>
									<!--
									<tr><td class="appMenu" width="200" align="right">&nbsp;</td><td class="appMenu" width="50" align="center">&nbsp;</td></tr>
									-->
									<tr><td class="appMenu" width="60%" align="right">Login ID :</td><td class="appMenu2" width="40%" align="left"><a class="mainMenuLink" href="<%=appProfileAction%>" id="appUser"><s:property value="#loginObject.loginId"/></a></td></tr>
									<tr><td class="appMenu" width="60%" align="right">Application :</td><td class="appMenu2" width="40%" align="left"><s:property value="#loginObject.applicationContextName"/></td></tr>
							 	<s:if test="#session['ADMIN_USER'] != null">
									<tr><td class="appMenu" width="60%" align="right">Role :</td><td class="appMenu2" width="40%" align="left">Super&nbsp;Admin</td></tr>
							 	</s:if>
							 	<s:else>
									<tr><td class="appMenu" width="60%" align="right">Role :</td><td class="appMenu2" width="40%" align="left">Admin</td></tr>
							 	</s:else>
								</table>
								</td>
								
							 </s:if>
							</tr>							
</table>
