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
		document.GroupForm.operation.value=target;
		document.GroupForm.submit();
	}
	
function skipNavigation()
{
	document.getElementById("groupResult").focus();
	window.location.hash="groupResult";
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
	
// -->
</script>


	<table cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
	<s:form name="GroupForm" action="GroupDBOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/GroupDBOperation'/>"/>
		<tr>
			<td height="80">
			<h2><a id="groupResult"></a>Group</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				width="100%" class="contentBegins">
				<tr>
					<td class="errorMessage" colspan="3">
						<s:if test="hasActionErrors()">
						      <s:actionerror/>
						</s:if>
					</td>
				<tr>
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
								<table summary="Search results for Group search"  cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="9%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="33%">Group Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											colspan="3" width="58%">Group Description</th>
									</tr>
									<s:iterator value="#searchResult.searchResultObjects" var="searchResultObject">
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="9%">
												<s:radio name="groupForm.groupId" list="#{#searchResultObject.getGroupId().toString():#searchResultObject.getGroupId().toString()}" />
												</td>
												<td class="dataCellText" width="33%"><s:property value="#searchResultObject.getGroupName()"/>&nbsp;</td>
												<td class="dataCellText" colspan="3" width="58%"><s:property value="#searchResultObject.getGroupDesc()"/>&nbsp;</td>
											</tr>
										</s:if>
										<s:else>
										<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="9%">
												<s:radio name="groupForm.groupId" list="#{#searchResultObject.getGroupId().toString():#searchResultObject.getGroupId().toString()}" />
												</td>
												<td class="dataCellText" width="33%"><s:property value="#searchResultObject.getGroupName()"/>&nbsp;</td>
												<td class="dataCellText" colspan="3" width="58%"><s:property value="#searchResultObject.getGroupDesc()"/>&nbsp;</td>
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











