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
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<% int cntResObj=1; // - Count the number of objects to display %>
<script>
<!--
	cntResObj=1; 
   	function setAndSubmit(target)
   	{
  		document.UserForm.operation.value=target;
  		document.UserForm.submit();
 	}

 	function keySearch(associatedIds, key)
 	{
 		//alert ("I am here");
 		for(var j=0; j<associatedIds.length; j++)
		{
			if(associatedIds[j].value == (key))
				return true;
		}
		return false;
	}

 	 function selSwitch(btn)
	{

  		var radioLen = document.UserForm.userId.length

  		if(radioLen == undefined)
  		{
  			if (document.UserForm.userId.checked)
			{
				if(!keySearch(window.opener.document.ApplicationForm.associatedIds.options, document.UserForm.userId.value))
				{
					var optLen = window.opener.document.ApplicationForm.associatedIds.options.length++;

					window.opener.document.ApplicationForm.associatedIds.options[optLen].text = document.UserForm.lgName.value;
					window.opener.document.ApplicationForm.associatedIds.options[optLen].value = document.UserForm.userId.value;
				}
				else
				{
					alert("User already exists");
				}
			}
  		}


		for (var i = 0; i <radioLen; i++)
		{
			if (document.UserForm.userId[i].checked)
			{
				if(!keySearch(window.opener.document.ApplicationForm.associatedIds.options, document.UserForm.userId[i].value))
				{
					var optLen = window.opener.document.ApplicationForm.associatedIds.options.length++;

					window.opener.document.ApplicationForm.associatedIds.options[optLen].text = document.UserForm.lgName[i].value;
					window.opener.document.ApplicationForm.associatedIds.options[optLen].value = document.UserForm.userId[i].value;
				}
				else
				{
					alert("User already exists");
					break;
				}
			}
		}

	}
	
function skipNavigation()
{
	document.getElementById("userResult").focus();
	window.location.hash="userResult";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	if(document.getElementById("homeLink"))
		document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
	if(document.getElementById("menuHome"))
		document.getElementById("menuHome").tabIndex = -1;
	if(document.getElementById("menuUser"))
		document.getElementById("menuUser").tabIndex = -1;
	if(document.getElementById("menuPE"))	
		document.getElementById("menuPE").tabIndex = -1;
	if(document.getElementById("menuPrivilege"))	
		document.getElementById("menuPrivilege").tabIndex = -1;
	if(document.getElementById("menuGroup"))
		document.getElementById("menuGroup").tabIndex = -1;
	if(document.getElementById("menuPG"))
		document.getElementById("menuPG").tabIndex = -1;
	if(document.getElementById("menuRole"))
		document.getElementById("menuRole").tabIndex = -1;
	if(document.getElementById("menuInstance"))
		document.getElementById("menuInstance").tabIndex = -1;
	if(document.getElementById("menulogout"))
		document.getElementById("menulogout").tabIndex = -1;

	if(document.getElementById("saHome"))
		document.getElementById("saHome").tabIndex = -1;
	if(document.getElementById("saApp"))	
		document.getElementById("saApp").tabIndex = -1;
	if(document.getElementById("saUser"))
		document.getElementById("saUser").tabIndex = -1;
	if(document.getElementById("saPriv"))
		document.getElementById("saPriv").tabIndex = -1;
	if(document.getElementById("saLogout"))
		document.getElementById("saLogout").tabIndex = -1;

}
    
	
		// -->

</script>


	<table cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<s:form name="UserForm"
	action="/UserDBOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/UserDBOperation'/>"/>
		<tr>
			<td>
			<h2><a id="userResult"></a>User</h2>
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
								<table summary="Search results for User search"  cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="10%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User Login Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User First Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User Last Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User Organization</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User Department</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">User Email Id</th>
									</tr>
									<s:iterator value="#searchResult.searchResultObjects" var="searchResultObject" end="1000">
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<s:set var="loginName" value="#searchResultObject.getLoginName().toString()"/>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="10%">
												<s:radio
													name="userForm.userId"  list="#{#searchResultObject.getUserId().toString():#searchResultObject.getUserId().toString()}"/>
												</td>
											<td class="dataCellText" width="15%"><s:hidden
												name="logName" property="lgName"
												value="%{loginName}" /><s:property value="#searchResultObject.loginName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.firstName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.lastName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.organization"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.department"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.emailId"/>&nbsp;</td>
											</tr>
										</s:if>	
										<s:else>
											<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="10%">
												<s:radio
													name="userForm.userId"  list="#{#searchResultObject.getUserId().toString():#searchResultObject.getUserId().toString()}"/>
												</td>
											<td class="dataCellText" width="15%"><s:hidden
												name="logName" property="lgName"
												value="%{loginName}" />
												<s:property value="#searchResultObject.loginName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.firstName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.lastName"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.organization"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.department"/>&nbsp;</td>
											<td class="dataCellText" width="15%">
												<s:property value="#searchResultObject.emailId"/>&nbsp;</td>
											</tr>
										</s:else>
										<% cntResObj=cntResObj+1; %>
									</s:iterator>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										
										<td><s:submit style="actionButton" onclick="setAndSubmit('read');" value="View Details"/></td>
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
