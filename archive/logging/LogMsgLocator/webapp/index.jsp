<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="layout.jsp" flush="true" >
	<tiles:put name="title"  value="Log Tracker" />
	<tiles:put name="header" value="header.htm"/>
	<tiles:put name="menu"   value="queryMenu.jsp"/>
	<tiles:put name="body"   value="welcomeBody.htm"/>
</tiles:insert>