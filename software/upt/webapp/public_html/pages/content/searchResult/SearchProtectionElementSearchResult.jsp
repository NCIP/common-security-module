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
  		document.SearchProtectionElementForm.operation.value=target;
  		document.SearchProtectionElementForm.submit();
 	}
 	function keySearch(associatedIds, key)
 	{
 		for(var j=0; j<associatedIds.length; j++)
		{
			if(associatedIds[j].value == (key)){
				return true;
			}	
		}
		return false;
	}
 	
 	 function selSwitch(btn)
	{
  		var radioLen = document.SearchProtectionElementForm.protectionElementId.length;
  		if(radioLen == undefined)
  		{
  			if (document.SearchProtectionElementForm.protectionElementId.checked) 
			{
				if(!keySearch(window.opener.document.ProtectionGroupForm.associatedIds.options, document.SearchProtectionElementForm.protectionElementId.value))
				{
					var optLen = window.opener.document.ProtectionGroupForm.associatedIds.options.length++;
					
					window.opener.document.ProtectionGroupForm.associatedIds.options[optLen].text = document.SearchProtectionElementForm.protectionElementName.value;
					window.opener.document.ProtectionGroupForm.associatedIds.options[optLen].value = document.SearchProtectionElementForm.protectionElementId.value;

				}
				
			}
  		}

  	

		for (var i = 0; i <radioLen; i++) 
		{
			if (document.SearchProtectionElementForm.protectionElementId[i].checked) 
			{
				if(!keySearch(window.opener.document.ProtectionGroupForm.associatedIds.options, document.SearchProtectionElementForm.protectionElementId[i].value))
				{
					var optLen = window.opener.document.ProtectionGroupForm.associatedIds.options.length++;
					window.opener.document.ProtectionGroupForm.associatedIds.options[optLen].text = document.SearchProtectionElementForm.protectionElementName[i].value;
					window.opener.document.ProtectionGroupForm.associatedIds.options[optLen].value = document.SearchProtectionElementForm.protectionElementId[i].value;
				}
			}
		}
	    window.close();
	}  
	
function skipNavigation()
{
	document.getElementById("peResult").focus();
	window.location.hash="peResult";
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
	<s:form name="SearchProtectionElementForm"
		action="SearchProtectionElementDBOperation" theme="simple">
		<s:hidden name="operation" value="read" />
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SearchProtectionElementDBOperation'/>"/>
		<tr>
			<td>
			<h2><a id="peResult"></a>Protection Element</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%"
				class="contentBegins">
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
								<table summary="Search results for Protection Element search" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="10%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Protection Element Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Protection Element Description</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">Protection Element Type</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Object Id</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">Attribute</th>
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
											<s:set var="pId" value="#searchResultObject.getProtectionElementId().toString()"/>
											<s:set var="pName" value="#searchResultObject.getProtectionElementName().toString()"/>
											<s:checkbox name="protectionElementId" style="formFieldSized" fieldValue="%{pId}" value="false"/>
											</td>
											<td class="dataCellText" width="20%" ><s:hidden	name="protElementName" name="protectionElementName"
												value="%{pName}" />
												<s:property value="#searchResultObject.protectionElementName"/>&nbsp;</td>
											<td class="dataCellText" width="20%"><s:property value="#searchResultObject.protectionElementDescription"/>&nbsp;</td>
											<td class="dataCellText" width="15%"><s:property value="#searchResultObject.protectionElementType"/>&nbsp;</td>
											<td class="dataCellText" width="20%"><s:property value="#searchResultObject.objectId"/>&nbsp;</td>
											<td class="dataCellText" width="15%"><s:property value="#searchResultObject.attribute"/>&nbsp;</td>
										</tr>
									</s:iterator>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<script>
											if (window.opener)
											{
												var loc2 = "";
												loc2 = window.opener.location + "";
												var locIndex2 = loc2.indexOf("ProtectionGroupDBOperation");
												
												if ( locIndex2 != -1	)
												{
													document.write('<td><input type="button" value="Assign PE" style="width:92px;" onclick="selSwitch(this);"></td>');
												}
											}
										</script>

										<script>
						
											if (!window.opener)
											{
												var loc1 = window.opener.location;
												var locIndex1 = loc1.indexOf("ProtectionGroupDBOperation");
												if ( locIndex1 == -1 || locIndex1 =="" )
												{
													var read = 'read';
													document.write('<td><s:submit style="actionButton" onclick="setAndSubmit(read);" value="View Details"/></td>');
												}
											}
										</script>
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
