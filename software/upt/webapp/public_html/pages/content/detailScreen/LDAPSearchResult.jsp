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

<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico" />

	<title>DataTables example</title>
	<style type="text/css" title="currentStyle">
		@import "styles/demo_page.css";
		@import "styles/demo_table.css";
	</style>
	<script type="text/javascript" language="javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="scripts/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf-8">
		var oTable;
		var oSubmit = true;
		$(document).ready(function() {
		    /* Add/remove class to a row when clicked on */
		    $('#users tr').click( function() {
			$(this).toggleClass('row_selected');
		    } );

		oTable = $('#users').dataTable({
			"aaSorting": [[ 4, "desc" ]],
			"sPaginationType": "full_numbers"
			} );
		} );
		

		function fnGetSelected( oTableLocal )
		{
			var aReturn = new Array();
			var aTrs = oTableLocal.fnGetNodes();

			for ( var i=0 ; i<aTrs.length ; i++ )
			{
				if ( $(aTrs[i]).hasClass('row_selected') )
				{
					var aData = oTableLocal.fnGetData( aTrs[i] );
					if(aData[0] == null || aData[0] == "")
					{
						alert("Invalid User selection to import with no LoginId");
						return null;
					}
					aReturn.push( aData[0] );
				}
			}
			return aReturn;
		}

		function submitForm()
		{
			if(oSubmit == true)
				document.ImportForm.submit();
			else
				return false;
		}

		function prepareSubmit(target)
		{
			if (target == "import")
			{
				var anSelected = fnGetSelected( oTable );
				if(anSelected == null || anSelected == "")
				{
						oSubmit = false;
						return;
				}
				else
				{
					if (confirm("Are you sure you want to import selected LDAP users into CSM?"))
					{
						oSubmit = true;
						document.ImportForm.operation.value=target;
						document.ImportForm.selectedLoginIds.value=anSelected;
					}
				}
			}
			else
			{
				document.ImportForm.operation.value=target;
				oSubmit = true;
			}
		}
	</script>
</head>

<script>
<!--
 	
function skipNavigation()
{
	document.getElementById("appDetail").focus();
	window.location.hash="appDetail";
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

<s:set var="submitValue" value="error" />

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="ImportForm" action="LDAPImport" theme="simple" onsubmit="return submitForm()">
	<s:hidden name="operation" value="error"/>
	<s:hidden name="selectedLoginIds" value=""/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Application detail" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td class="infoMessage" colspan="3">
								<s:if test="hasActionMessages()">
								   <div class="welcome">
								      <s:actionmessage/>
								   </div>
								</s:if>			  
			  				</td>
						</tr>
						<tr>
							<td colspan="3">
								<s:if test="hasActionErrors()">
								   <div class="errors">
								      <s:actionerror/>
								   </div>
								</s:if>
							</td>
						</tr>
						<tr>
						<s:set var="oddRow" value="true"/>
						
						<tr>
							<td align="right">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="users" width="100%">
							<thead>
								<tr align="left">
									<th>Login Id</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>
							<s:set var="searchResult" value="#request.LDAPImportUsers"/>
							<s:iterator value="searchResult" var="searchResultObject" end="1000">
							<s:if test='oddRow.equals("true")'>
								<s:set var="oddRow" value="false"/>
									<tr class="odd gradeX">
								</s:if>
								<s:else>
								<s:set var="oddRow" value="true"/>
									<tr class="even gradeC" align="left">
								</s:else>
										<td><s:property value="#searchResultObject.getUserLoginId()"/></td>
										<td><s:property value="#searchResultObject.getUserFirstName()"/></td>
										<td><s:property value="#searchResultObject.getUserLastName()"/></td>
										<td><s:property value="#searchResultObject.getUserEmailId()"/></td>
									</tr>
							</s:iterator>
							</tbody>
							</table>
						</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td><s:submit style="actionButton"
											onclick="prepareSubmit('import');" value="Import"/></td>
										<td><s:submit style="actionButton"
											onclick="prepareSubmit('loadSearch');" value="Back"/></td>
											
									</tr>
								</table>
								<!-- action buttons end --></td>
							</tr>
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:form>
	</table>

