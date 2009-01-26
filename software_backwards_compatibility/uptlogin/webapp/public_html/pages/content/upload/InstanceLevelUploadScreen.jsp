<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.loginapp.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.loginapp.constants.*"%>
<%@ page import="gov.nih.nci.security.loginapp.forms.*"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
   		document.InstanceLevelForm.operation.value=target;
 	}
// -->
</script>

	<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="InstanceLevelForm" action="/InstanceLevelOperation"  focus="uploadedFile1" enctype="multipart/form-data">
	<html:hidden property="operation" value="upload"/>
	<html:hidden property="userLoginName" value="upload"/>
		<tr>
			<td valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
					<tr>
						<td>
							<table summary="" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
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
										<td class="formMessage" colspan="3">Enter the path's of the Application Jar files containing the Hibernate Files and the Domain Object. Also you provide 
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
									<td class="formRequiredLabel2"><label>Application Jar File</label></td>
									<td class="formField"><html:file style="formFieldSized" size="30" maxlength="100" property="uploadedFile1" value="" /></td>
								</tr>
								<tr>
									<td class="formRequiredNotice" width="5">&nbsp;</td>
									<td class="formLabel"><label>Application Jar File</label></td>
									<td class="formField"><html:file style="formFieldSized" size="30" maxlength="100" property="uploadedFile2" value="" /></td>
								</tr>
								<tr>									
									<td class="formRequiredNotice" width="5">*</td>
									<td class="formRequiredLabel2"><label>Hibernate Configuration File Name</label></td>
									<td class="formField"><html:text style="formFieldSized" size="30" maxlength="100" property="hibernateFileName" value=""/></td>
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

