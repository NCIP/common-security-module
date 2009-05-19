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
			
String HOST_APPLICATION_LOGO_URL= (String)session.getAttribute("HOST_APPLICATION_LOGO_URL");
String HOST_APPLICATION_LOGO_ALT_TEXT= (String)session.getAttribute("HOST_APPLICATION_LOGO_ALT_TEXT");
%>
<tr>
	<td colspan="2" height="50">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="subhdrBG">
		<tr>
			<td height="50" align="left">
				<logic:present name="<%=DisplayConstants.HOST_APPLICATION_LOGO_URL%>">
				<a href="<%=hostURL%>">
					<img src="<%=HOST_APPLICATION_LOGO_URL%>" alt="<%=HOST_APPLICATION_LOGO_ALT_TEXT%>" height="35" hspace="10" border="0">
				</a>
				</logic:present>
				<logic:notPresent name="<%=DisplayConstants.HOST_APPLICATION_LOGO_URL%>">
					<a href="#">
						<img src="images/appLogo.gif" alt="Application Logo" height="35" hspace="10" border="0">
					</a>
				</logic:notPresent>
			</td>
		</tr>
	</table>
	</td>
</tr>
