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
<bean:define name="<%=DisplayConstants.HOST_APPLICATION_NAME%>"
	id="hostApplicationName" />


<tr>
	<td valign="top" class="contentPage">
		<table summary="" cellpadding="3" cellspacing="0" border="0"
			width="500">

			<tr>
				<td class="formMessage" colspan="1">
				</td>
				<td class="formMessage" colspan="3"></td>
			</tr>
			<tr>
				<td class="formMessage" colspan="1"></td>
				<td class="formMessage" colspan="3">
					<table summary="" cellpadding="3" cellspacing="0" border="0"
						width="500">
						<html:form action="/CsmLogin">
							<%--<html:hidden property="activity" value="submitQuery" />--%>

							<tr>
								<td class="formMessage" colspan="3">
									You have successfully migrated your <%=hostApplicationName%> Local (CSM) account to a caGrid User account. Going forward, use your caGrid User account login credentials. Your <%=hostApplicationName%> Local (CSM) account login credentials are no longer applicable for login to <%=hostApplicationName%>.
									<br><br>Select OK to proceed.
								</td>
							</tr>
							<tr>

								<td colspan="3">
									<input class="actionButton" type="submit" value='OK' />
					
								</td>
							</tr>
							</html:form>
							
					</table>

				</td>
			</tr>
		
		
		</table>
		
		
	</td>
</tr>
