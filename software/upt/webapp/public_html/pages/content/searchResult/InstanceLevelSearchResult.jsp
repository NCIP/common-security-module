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

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<% int cntResObj=1; // - Count the number of objects to display %>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.InstanceLevelForm.operation.value=target;
  		document.InstanceLevelForm.submit();
 	}
 	
function skipNavigation()
{
	document.getElementById("ilResult").focus();
	window.location.hash="ilResult";
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
		<s:form name="InstanceLevelForm" action="InstanceLevelOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/InstanceLevelOperation'/>"/>
		<tr height="80">
			<td>
			<h2><a id="ilResult"></a>Filter Clause</h2>
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
								<table summary="Search results for Instance level search" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="10%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="30%">Class Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="30%">Filter Chain</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Target Attribute Name</th>
									</tr>
									
									<s:iterator value="#searchResult.searchResultObjects" var="searchResultObject" end="1000">
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<tr class="dataRowLight">
										</s:if>
										<s:else>
											<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
										</s:else>	
												<td class="dataCellNumerical" width="10%">
												<s:radio
													name="instanceLevelForm.id"  list="#{#searchResultObject.getId().toString():#searchResultObject.getId().toString()}"/>
												</td>
												<td class="dataCellText" width="30%"><s:property value="#searchResultObject.getClassName()"/>&nbsp;</td>
												<td class="dataCellText" width="30%"><s:property value="#searchResultObject.getFilterChain()"/>&nbsp;</td>
												<td class="dataCellText" width="20%"><s:property value="#searchResultObject.getTargetClassAttributeName()"/>&nbsp;</td>
											</tr>
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
