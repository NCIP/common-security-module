<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:notPresent name="loginForm"><jsp:forward page="sign-on.jsp"/></logic:notPresent>
<HTML>
	<HEAD>
		<TITLE><tiles:getAsString name="title"/></TITLE>

        <link rel="stylesheet" href="style/translocator.css">
	</HEAD>
	<BODY>
	<TABLE border="0" width="100%" cell spacing="5">
		<TR>
			<TD colspan="2"><tiles:insert attribute="header"/></TD>
		</TR>
		<TR>
			<TD bgcolor="#0033CC" height="100%" width="25%" valign="top" align="left" >
				<tiles:insert attribute="menu"/>
			</TD>
			<TD width="75%" valign="top" align="left">
				<tiles:insert attribute="body"/>
			</TD>
		</TR>

	</TABLE>
	</BODY>
</HTML>