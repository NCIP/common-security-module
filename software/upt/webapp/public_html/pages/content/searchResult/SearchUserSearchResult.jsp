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
<% int cntResObj=1; // - Count the number of objects to display %>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.SearchUserForm.operation.value=target;
  		document.SearchUserForm.submit();
 	}
 	
 	function keySearch(associatedIds, key)
 	{
 		for(var j=0; j<associatedIds.length; j++)
		{
		
			if(associatedIds[j].value == (key))
			{
				return true;
			}
		}
		return false;
	}
 	
 	 function selSwitch(btn)
	{

  		var radioLen = document.SearchUserForm.userId.length;
  		
  		 var isgroupform = false;
  		 var isapplicationform = false;
  		 var ischecked = false;
  		for(var looop = 0; looop < window.opener.document.forms.length; looop++)
  		{
  			if(window.opener.document.forms[looop].name == "applicationForm")	isapplicationform = true;
  			
  			if(window.opener.document.forms[looop].name == "GroupForm")
  			{ isgroupform = true;

  			}
  			
  		}
		if(isapplicationform)
		{
	  		if(radioLen == undefined)
	  		{
	 			if (document.SearchUserForm.userId.checked) 
				{	
					if(!keySearch(window.opener.document.associatedIds.options, document.SearchUserForm.userId.value))
					{
						var optLen = window.opener.document.applicationForm.associatedIds.options.length++;	
						window.opener.document.applicationForm.associatedIds.options[optLen].text = document.SearchUserForm.lgName.value;
						window.opener.document.applicationForm.associatedIds.options[optLen].value = document.SearchUserForm.userId.value;
						ischecked = true;
					}		
				}
	  		}
			for (var i = 0; i <radioLen; i++) 
			{
				if (document.SearchUserForm.userId[i].checked) 
				{
					if(!keySearch(window.opener.document.applicationForm.associatedIds.options, document.SearchUserForm.userId[i].value))
					{
						var optLen = window.opener.document.applicationForm.associatedIds.options.length++;

						window.opener.document.applicationForm.associatedIds.options[optLen].text = document.SearchUserForm.lgName[i].value;
						window.opener.document.applicationForm.associatedIds.options[optLen].value = document.SearchUserForm.userId[i].value;
					}
					ischecked = true;
				}
				
			}
		}		
		if(isgroupform)
		{
			if(radioLen == undefined)
	  		{
	 			if (document.SearchUserForm.userId.checked) 
				{		
					if(!keySearch(window.opener.document.GroupForm.associatedIds.options, document.SearchUserForm.userId.value))
					{
						var optLen = window.opener.document.GroupForm.associatedIds.options.length++;	
						window.opener.document.GroupForm.associatedIds.options[optLen].text = document.SearchUserForm.lgName.value;
						window.opener.document.GroupForm.associatedIds.options[optLen].value = document.SearchUserForm.userId.value;
						ischecked = true;
					}		
				}
	  		}
			for (var i = 0; i <radioLen; i++) 
			{
				if (document.SearchUserForm.userId[i].checked) 
				{
					if(!keySearch(window.opener.document.GroupForm.associatedIds.options, document.SearchUserForm.userId[i].value))
					{
						var optLen = window.opener.document.GroupForm.associatedIds.options.length++;

						window.opener.document.GroupForm.associatedIds.options[optLen].text = document.SearchUserForm.lgName[i].value;
						window.opener.document.GroupForm.associatedIds.options[optLen].value = document.SearchUserForm.userId[i].value;

					}
					ischecked = true;
				}
			}
		}		
		if(ischecked)
		      window.close();
		else
			alert("Atleast one check box should be checked.");
	}  
	
function skipNavigation()
{
	document.getElementById("userResult").focus();
	window.location.hash="userResult";
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
		<s:form name="SearchUserForm"
	action="SearchUserDBOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/SearchUserDBOperation'/>"/>
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
							<s:set var="searchResults" value="#searchResult.searchResultObjects"/>
							<s:set var="oddRow" value="true"/>
							<tr>
								<td>
								<table summary="Search results for User" cellpadding="3"
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
									<s:iterator value="searchResults" var="searchResultObject" end="1000">
									<s:set var="loginName" value="#searchResultObject.getLoginName().toString()"/>
									<s:set var="userId" value="#searchResultObject.getUserId().toString()"/>
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="10%">

												<s:checkbox name="userId" style="formFieldSized" fieldValue="%{userId}" value="false"></s:checkbox></td>
											<td class="dataCellText" width="15%"><s:hidden
												name="lgName"
												value="%{loginName}" /><s:property value="#searchResultObject.loginName"/>&nbsp;</td>
											<td class="dataCellText" width="15%"><s:property value="#searchResultObject.firstName"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.lastName"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.organization"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.department"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.emailId"/>&nbsp;</td>
											</tr>
										</s:if>
										<s:else>
										<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
											<td class="dataCellNumerical" width="10%">
											<s:checkbox name="userId" style="formFieldSized" fieldValue="%{userId}" value="false"></s:checkbox>
											</td>
											<td class="dataCellText" width="15%"><s:hidden
												name="lgName"
												value="%{loginName}" /><s:property value="#searchResultObject.loginName"/>&nbsp;</td>
											<td class="dataCellText" width="15%"><s:property value="#searchResultObject.firstName"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.lastName"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.organization"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.department"/>&nbsp;</td>
												<td class="dataCellText" width="15%"><s:property value="#searchResultObject.emailId"/>&nbsp;</td>
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
										<script>
											if (window.opener)
											{
												var loc2 = "";
												loc2 = window.opener.location + "";
												var locIndex2 = loc2.indexOf("ApplicationDBOperation");
												if ( locIndex2 != -1	)
												{
													document.write('<td><input type="button" value="Assign Admin" onclick="selSwitch(this);"></td>');
												}
												var locIndex2 = loc2.indexOf("GroupDBOperation");
												if ( locIndex2 != -1	)
												{
													document.write('<td><input type="button" value="Assign User"  onclick="selSwitch(this);"></td>');
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
