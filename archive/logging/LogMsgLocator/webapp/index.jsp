<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="layout.jsp" flush="true" >
	<tiles:put name="title"  value="Log Tracker" />
	<tiles:put name="header" value="header.htm"/>
	<tiles:put name="menu"   value="queryMenu.jsp"/>
	<tiles:put name="body"   value="welcomeBody.htm"/>
</tiles:insert>