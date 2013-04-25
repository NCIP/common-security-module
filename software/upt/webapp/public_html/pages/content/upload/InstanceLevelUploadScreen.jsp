<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<script>

   	function setAndSubmit(target)
   	{
   		document.InstanceLevelForm.operation.value=target;
   		document.InstanceLevelForm.submit();
 	}
 	
function skipNavigation()
{
	document.getElementById("uploadHome").focus();
	window.location.hash="uploadHome";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
		
	document.getElementById("menuHome").tabIndex = -1;
	document.getElementById("menuUser").tabIndex = -1;
	document.getElementById("menuPE").tabIndex = -1;
	document.getElementById("menuPrivilege").tabIndex = -1;
	document.getElementById("menuGroup").tabIndex = -1;
	document.getElementById("menuPG").tabIndex = -1;
	document.getElementById("menuRole").tabIndex = -1;
	document.getElementById("menuInstance").tabIndex = -1;
	document.getElementById("menulogout").tabIndex = -1;
}
 	

</script>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="InstanceLevelForm" action="/InstanceLevelOperation" enctype="multipart/form-data">
	<html:hidden property="operation" value="upload"/>
	<html:hidden property="userLoginName" value="upload"/>
		<tr>
			<td valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
					<tr>
						<td>
							<table summary="Upload jar for Instance level security" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
								<tr>
									<td class="infoMessage" colspan="3">
						  				<html:messages id="message" message="true">
						  				<bean:write name="message"/>
					  				</html:messages>	
					  				</td>
								</tr>
								<tr>
									<td colspan="3">
										<html:errors />
									</td>
								</tr>
								<logic:present name="<%=DisplayConstants.CURRENT_FORM%>">
								<tr>
									<tr>
										<td class="formMessage" colspan="3"><a id="uploadHome"></a>Enter the path's of the Application Jar files containing the Hibernate Files and the Domain Object. Also you provide 
										the name of the Hibernate configuration File to be used. 
										The <b>Application Jar File</b> is the path of the file containing the domain objects, hibernate mapping file and complete hibernate configuration file. 
										The uploaded file should be a valid Java Archive (jar) File. <b>NOTE:</b> For an SDK generated system, you can upload the second Application Jar File 
										using the second upload field. This is optional for applications which have their Hibernate Files and Domain Objects packaged into a single Jar File. 
										The <b>Hibernate Configuration File Name</b> is the fully qualified file name of the hibernate configuration file in the uploaded jar file.</td>
									</tr>
									<tr>
										<td class="formMessage" colspan="3">* indicates a required field</td>
									</tr>
								</tr>
								<tr>
									<td class="formTitle" height="20" colspan="3">UPLOAD THE APPLICATION JAR FILE</td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label for="uploadedFile1">Application Jar File</label></td>
									<td class="formField"><html:file style="formFieldSized" size="30" maxlength="100" styleId="uploadedFile1" property="uploadedFile1" value="" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label for="uploadedFile2">Application Jar File</label></td>
									<td class="formField"><html:file style="formFieldSized" size="30" maxlength="100" styleId="uploadedFile2" property="uploadedFile2" value="" /></td>
								</tr>
								<tr>									
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label for="hibernateFileName">Hibernate Configuration File Name</label></td>
									<td class="formField"><html:text style="formFieldSized" size="30" maxlength="100" styleId="hibernateFileName" property="hibernateFileName" value=""/></td>
								</tr>
								<tr>
									<td align="right" colspan="3"><!-- action buttons begins -->
										<table cellpadding="4" cellspacing="0" border="0">
											<tr>
												<td><html:submit style="actionButton" onclick="setAndSubmit('upload');">Upload</html:submit></td>
												<td><html:reset style="actionButton">Reset</html:reset></td>
												<td><html:submit style="actionButton" onclick="setAndSubmit('loadHome');">Back</html:submit></td>
											</tr>
										</table>
									</td><!-- action buttons end -->
								</tr>
								</logic:present>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</html:form>
	</table>

