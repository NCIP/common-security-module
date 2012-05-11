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
		<html:form styleId="InstanceLevelForm"
	action="/InstanceLevelOperation">
	<html:hidden property="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/InstanceLevelOperation'/>"/>
		<tr>
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
						<logic:present name="<%=DisplayConstants.SEARCH_RESULT%>">
							<bean:define name="<%=DisplayConstants.SEARCH_RESULT%>"
								property="searchResultObjects" id="searchResultObjects" />
							<bean:define id="oddRow" value="true" />
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
									<logic:iterate name="searchResultObjects"
										id="searchResultObject" type="FilterClause" length="1000">
										<%if (oddRow.equals("true")) { oddRow ="false";%>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="10%"><html:radio
													style="formFieldSized" property="id"
													value="<%=searchResultObject.getId().toString()%>" /></td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="className" />&nbsp;</td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="filterChain" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="targetClassAttributeName" />&nbsp;</td>
											</tr>
										<%}else{ oddRow = "true";%>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="10%"><html:radio
													style="formFieldSized" property="id"
													value="<%=searchResultObject.getId().toString()%>" /></td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="className" />&nbsp;</td>
												<td class="dataCellText" width="30%"><bean:write
													name="searchResultObject" property="filterChain" />&nbsp;</td>
												<td class="dataCellText" width="20%"><bean:write
													name="searchResultObject" property="targetClassAttributeName" />&nbsp;</td>
											</tr>
										<%}%>
										<% cntResObj=cntResObj+1; %>
									</logic:iterate>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('read');">View Details</html:submit></td>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('loadSearch');">Back</html:submit></td>
										
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
