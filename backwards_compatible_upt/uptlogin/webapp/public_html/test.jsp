<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="gov.nih.nci.security.loginapp.constants.*"%>
<%@ page import="gov.nih.nci.security.loginapp.forms.*"%>

<html>
<body>
<bean:define name="<%=DisplayConstants.LOGIN_OBJECT%>" id="loginObject" type="LoginForm" />
<%
String contextName = "/"+((String)request.getAttribute("APPLICATION_CONTEXT")) + "/Login.do";

 %>
<form name="LoginForm" method="post" action="<%=contextName%>">
<%--<input type="hidden" name="loginId" size="14" value="admin" >
<input type="hidden" name="password" size="14" value="changeme" >
<input type="hidden" name="applicationContextName" size="14" value="sample41" >
--%>
<input type="hidden" name="loginId" size="14" value=<bean:write name="loginObject" property="loginId" /> >
<input type="hidden" name="password" size="14" value=<bean:write name="loginObject" property="password" /> >
<input type="hidden" name="applicationContextName" size="14" value=<bean:write name="loginObject" property="applicationContextName" /> >
</form>
<script language="Javascript">  
	document.LoginForm.submit();  
</script> 
</body>
</html>

