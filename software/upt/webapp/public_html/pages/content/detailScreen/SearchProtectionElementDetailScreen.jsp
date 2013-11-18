<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<script><!--

	function chkVal(){
	/*			if ( (document.ProtectionElementForm.protectionElementName.value == null || document.ProtectionElementForm.protectionElementName.value == "") &&
			   	 (document.ProtectionElementForm.protectionElementType.value == null || document.ProtectionElementForm.protectionElementType.value == "") &&
			   	 (document.ProtectionElementForm.protectionElementObjectId.value == null || document.ProtectionElementForm.protectionElementObjectId.value == "") &&
			   	 (document.ProtectionElementForm.protectionElementAttribute.value == null || document.ProtectionElementForm.protectionElementAttribute.value == "") )
		    {
		     	alert("Please enter some search criteria ");
		     	return false;

			}else{
			//	alert("Vijay Test");
			}
		*/
            return true;
	  }
	  	
   	function setAndSubmit(target)
   	{
   		if (target == "delete")
   		{
   			if (confirm("Are you sure you want to delete the record?"))
   			{
   				document.ProtectionElementForm.operation.value=target;
				document.ProtectionElementForm.submit();
			}
		}
		else
		{
	  		document.ProtectionElementForm.operation.value=target;
	  		document.ProtectionElementForm.submit();
	  	}
 	}
// 
--></script>
<s:set var="submitValue" value="error" />
<s:set var="currentForm" value="#session.CURRENT_FORM"/>
<s:if test='#currentForm.getPrimaryId().equals("")'>
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="create" />
	</s:if>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="search" />
	</s:if>
</s:if>
<s:else>
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="loadAdd" />
	</s:if>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="loadSearchResult" />
	</s:if>
</s:else>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="ProtectionElementForm" action="SearchProtectionElementDBOperation" focus="protectionElementName" theme="simple">
	<s:hidden name="operation" value="error"/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SearchProtectionElementDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Protection element detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td class="infoMessage" colspan="3">
							<s:if test="hasActionMessages()">
							      <s:actionmessage/>
							</s:if>			  
			  				</td>
						</tr>
						<tr>
							<td class="infoMessage" colspan="3">
							<s:if test="hasActionErrors()">
							      <s:actionerror/>
							</s:if>
							</td>
						</tr>
						<tr>
					<s:if test="#session.CURRENT_FORM != null">
						<s:if test='#currentForm.getPrimaryId().equals("")'>
							<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
								<tr>
									<td class="formMessage" colspan="3">Enter the details to add a new Protection Element. 
									The <b>Protection Element Name, Protection Element Type, Protection Element Object Id</b> and <b>Protection Element Attribute Name</b> uniquely identifies the Protection Element. 
									<b>Protection Element Name</b> and <b>Protection Element Object Id</b> are required fields. 
									The <b>Protection Element Description</b> is a brief summary about the Protection Element and the <b>Protection Element Type</b> describes the type of the Protection Element.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">* indicates a required field</td>
								</tr>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<tr>
									<td class="formMessage" colspan="3">Search for an existing Protection Element by entering the <b>Protection Element Name, Protection Element Type, Protection Element Object Id</b> or <b>Protection Element Attribute Name</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</s:if>
							</s:if>
							<s:else>
								<tr>
									<td class="formMessage" colspan="3">Update the details of the displayed Protection Element. 
									The <b>Protection Element Name, Protection Element Type, Protection Element Object Id</b> and <b>Protection Element Attribute Name</b> uniquely identifies the Protection Element. 
									<b>Protection Element Name</b> and <b>Protection Element Object Id</b> are required fields. 
									The <b>Protection Element Description</b> is a brief summary about the Protection Element and the <b>Protection Element Type</b> describes the type of the Protection Element. The <b>Update Date</b> indicates the date when this Privilege's Details were last updated.</td>
								</tr>							
							</s:else>
						</tr>
						<tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE NEW PROTECTION ELEMENT DETAILS</td>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
										<td class="formTitle" height="20" colspan="3">ENTER THE PROTECTION ELEMENT SEARCH CRITERIA</td>
								</s:if>
							</s:if>
							<s:else>
									<td class="formTitle" height="20" colspan="3">PROTECTION ELEMENT DETAILS</td>
							</s:else>
						</tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<s:set var="formElements" value="#currentForm.searchFormElements"/>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<s:set var="formElements" value="#currentForm.addFormElements"/>
								</s:if>
							</s:if>
							<s:else>
								<s:set var="formElements" value="#currentForm.displayFormElements"/>
								<s:set var="protectionElementId" value="#currentForm.getPrimaryId()"/>
								<s:hidden name="protectionElementForm.protectionElementId" value="%{protectionElementId}"/>
							</s:else>
							<s:iterator value="formElements" var="formElement">
								<tr>
									<s:if test='(#formElement.propertyRequired.equals("REQUIRED"))'>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
									</s:if>
									<s:else>
										<td class="formRequiredNotice" width="5">&nbsp;</td>
										<td class="formLabel"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
									</s:else>
									<s:if test='(#formElement.propertyType.equals("INPUT_BOX"))'>
										<td class="formField"><s:textfield style="formFieldSized" size="30" maxlength="100" name="protectionElementForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/></td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("PASSWORD"))'>
										<td class="formField"><s:password style="formFieldSized" size="30" maxlength="100" name="protectionElementForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" showPassword="true"/></td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_DATE"))'>
										<td class="formField">
										<s:if test='(#formElement.propertyReadonly.equals(DisplayConstants.READONLY))'>
											<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyValue"/>(MM/DD/YYYY)</label>
										</s:if>
										<s:else>
											<s:if test='#formElement.propertyDisabled'>
												<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MM/DD/YYYY)</label>
											</s:if>
											<s:else>
											<s:textfield size="10" maxlength="10"  name="protectionElementForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>  (MM/DD/YYYY)
											</s:else>										
										</s:else>
										</td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_TEXTAREA"))'>
										<td class="formField"><s:textarea style="formFieldSized" cols="32" rows="2" name="protectionElementForm.%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" /></td>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_RADIO"))'>
										<td class="formField"><s:radio name="protectionElementForm.%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="%{propertyValue}" /></td>
									</s:if>
								</tr>
							</s:iterator>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<s:if test='#currentForm.getPrimaryId().equals("")'>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td>
											<s:submit property="actionButton" onclick="if(chkVal()){setAndSubmit('search');}" value="Search"/></td>
											
										</s:if>
                                      
										<td><s:reset style="actionButton" value="Reset"/></td>
										<td><s:submit property="action" onclick="window.close();" value="Exit"/></td>										
									</s:if>
									<s:else>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');" value="Back"/></td>
										</s:if>
									</s:else>
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

