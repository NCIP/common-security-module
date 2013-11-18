<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>
<script>
<!--
	function setAndSubmit(target)
	{
		document.ApplicationForm.operation.value=target;
		document.ApplicationForm.submit();
	}
	
function skipNavigation()
{
	document.getElementById("appResult").focus();
	window.location.hash="appResult";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	if(document.getElementById("homeLink"))
		document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
		
	document.getElementById("saHome").tabIndex = -1;
	document.getElementById("saApp").tabIndex = -1;
	document.getElementById("saUser").tabIndex = -1;
	document.getElementById("saPriv").tabIndex = -1;
	document.getElementById("saLogout").tabIndex = -1;
}
	
// -->
</script>


	<table cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<s:form name="ApplicationForm"
	action="/ApplicationDBOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
	
		<tr>
			<td>
			<h2><a id="appResult"></a>Application</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				width="100%" class="contentBegins">
				<tr>
					<td>
					<table cellpadding="0" cellspacing="0" border="0"
						width="100%">
						<tr>
							<td class="dataTablePrimaryLabel" height="20">SEARCH RESULTS</td>
						</tr>
						<s:if test="#session.SEARCH_RESULT != null">
							<s:set var="searchResult" value="#session.SEARCH_RESULT"/>
							<s:set var="oddRow" value="true"/>
							<tr>
								<td>
								<table summary="Search results for Application search" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="9%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="33%">Application Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											colspan="3" width="58%">Application Description</th>
									</tr>
									<s:iterator value="#searchResult.searchResultObjects" var="searchResultObject">
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="9%"><s:radio name="applicationForm.applicationId" list="#{#searchResultObject.getApplicationId().toString():#searchResultObject.getApplicationId().toString()}" />
													</td>
												<td class="dataCellText" width="33%"><s:property value="#searchResultObject.applicationName"/>&nbsp;</td>
												<td class="dataCellText" colspan="3" width="58%"><s:property value="#searchResultObject.applicationDescription"/>&nbsp;</td>
											</tr>
										</s:if>
										<s:else>
										<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="9%"><s:radio
													name="applicationForm.applicationId"  list="#{#searchResultObject.getApplicationId().toString():#searchResultObject.getApplicationId().toString()}"/>
													</td>
												<td class="dataCellText" width="33%"><s:property value="#searchResultObject.applicationName"/>&nbsp;</td>
												<td class="dataCellText" colspan="3" width="58%"><s:property value="#searchResultObject.applicationDescription"/>&nbsp;</td>
											</tr>
										</s:else>
									</s:iterator>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										
										<td><s:submit style="actionButton"
											onclick="setAndSubmit('read');" value="View Details"/></td>
										<td><s:submit style="actionButton"
											onclick="setAndSubmit('loadSearch');" value="Back"/></td>
									</tr>
								</table>
								<!-- action buttons end --></td>
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











