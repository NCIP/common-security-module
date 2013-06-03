<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

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

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.ProtectionElementForm.operation.value=target;
 	}
// -->
</script>


	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="ProtectionElementForm"
	action="<%="/ProtectionElementDBOperation"%>">
	<html:hidden property="operation" value="read" />
		<tr>
			<td>
			<h2>Protection Element</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="" cellpadding="0" cellspacing="0" border="0"
						width="100%">
						<tr>
							<td class="dataTablePrimaryLabel" height="20">SEARCH RESULTS</td>
						</tr>
						<logic:present name="<%=DisplayConstants.SEARCH_RESULT%>">
							<bean:define name="<%=DisplayConstants.SEARCH_RESULT%>"
								property="searchResultObjects" id="searchResultObjects" />
							<bean:define id="oddRow" value="true" />
							<tr>
								<td>
								<table summary="Enter summary of data here" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="10%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Protection Element Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="30%">Protection Element Description</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Object Id</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Attribute</th>
									</tr>
									<logic:iterate name="searchResultObjects"
										id="searchResultObject" type="ProtectionElement">
										<%if (oddRow.equals("true")) { oddRow ="false";%>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="10%"><html:radio
													style="formFieldSized" property="protectionElementId"
													value="<%=searchResultObject.getProtectionElementId().toString()%>" /></td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="protectionElementName" />&nbsp;</td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="protectionElementDescription" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="objectId" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="attribute" />&nbsp;</td>
											</tr>
										<%}else{ oddRow = "true";%>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="10%"><html:radio
													style="formFieldSized" property="protectionElementId"
													value="<%=searchResultObject.getProtectionElementId().toString()%>" /></td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="protectionElementName" />&nbsp;</td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="protectionElementDescription" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="objectId" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="attribute" />&nbsp;</td>
											</tr>
										<%}%>
									</logic:iterate>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('loadSearch');">Back</html:submit></td>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('read');">View Details</html:submit></td>
									</tr>
								</table>
								<!-- action buttons end --></td>
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
