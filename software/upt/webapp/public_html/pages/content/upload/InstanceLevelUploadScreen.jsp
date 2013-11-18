<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<style type="text/css">
.errors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:400px;
	margin-bottom:8px;
}
.errors li{ 
	list-style: none; 
}
.welcome {
	background-color:#DDFFDD;
	border:1px solid #009900;
	width:200px;
}
.welcome li{ 
	list-style: none; 
}
</style>

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
	<s:form name="InstanceLevelForm" action="InstanceLevelOperation" enctype="multipart/form-data" theme="simple">
	<s:hidden name="operation" value="upload"/>
	<s:hidden name="userLoginName" value="upload"/>
		<tr>
			<td valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
					<tr>
						<td>
							<table summary="Upload jar for Instance level security" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
								<tr>
									<td class="infoMessage" colspan="3">
										<s:if test="hasActionMessages()">
										      <s:actionmessage/>
										</s:if>
					  				</td>
								</tr>
								<tr>
									<td class="errorMessage" colspan="3">
										<s:if test="hasActionErrors()">
										      <s:actionerror/>
										</s:if>
									</td>
								</tr>
								<s:set var="currentForm" value="#session.CURRENT_FORM"/>
								<s:if test="#currentForm != null">
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
									<td class="formField"><s:file style="formFieldSized" size="30" maxlength="100" styleId="uploadedFile1" name="instanceLevelForm.uploadedFile" value="" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label for="uploadedFile2">Application Jar File</label></td>
									<td class="formField"><s:file style="formFieldSized" size="30" maxlength="100" styleId="uploadedFile2" name="instanceLevelForm.uploadedFile" value="" /></td>
								</tr>
								<tr>									
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label for="hibernateFileName">Hibernate Configuration File Name</label></td>
									<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" styleId="hibernateFileName" name="instanceLevelForm.hibernateFileName" value=""/></td>
								</tr>
								<tr>
									<td align="right" colspan="3"><!-- action buttons begins -->
										<table cellpadding="4" cellspacing="0" border="0">
											<tr>
												<s:if test="#session.UPDATE_UPT_INSTANCE_LEVEL_OPERATION != null">
													<td><s:submit style="actionButton" onclick="setAndSubmit('upload');" value="Upload"/></td>
												</s:if>
												<s:else>
													<td><s:submit style="actionButton" disabled="true" value="Upload"/></td>
												</s:else>
												<td><s:reset style="actionButton" value="Reset"/></td>
												<td><s:submit style="actionButton" onclick="setAndSubmit('loadHome');" value="Back"/></td>
											</tr>
										</table>
									</td><!-- action buttons end -->
								</tr>
								</s:if>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</s:form>
	</table>

