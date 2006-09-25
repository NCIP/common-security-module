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
  		document.UserForm.operation.value=target;
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

  		var radioLen = document.UserForm.userId.length;

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
				
			}
  		}
  	

		for (var i = 0; i <radioLen; i++) 
		{
			if (document.UserForm.userId[i].checked) 
			{
				if(!keySearch(window.opener.document.ApplicationForm.associatedIds.options, document.UserForm.userId[i].value))
				{
					var optLen = window.opener.document.ApplicationForm.associatedIds.options.length++;
					//alert(document.UserForm.userId[i].lgName.value);
					window.opener.document.ApplicationForm.associatedIds.options[optLen].text = document.UserForm.lgName[i].value;
					window.opener.document.ApplicationForm.associatedIds.options[optLen].value = document.UserForm.userId[i].value;
				}
				
			}
		}
		

	      window.close();
	}  
		// -->
</script>


	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<html:form styleId="UserForm"
	action='<%="/SearchUserDBOperation"%>'>
	<html:hidden property="operation" value="read" />
		<tr>
			<td>
			<h2>User</h2>
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
									<logic:iterate name="searchResultObjects"
										id="searchResultObject" type="User" length="100">
										<%if (oddRow.equals("true")) {oddRow = "false";%>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="10%">

												<html:checkbox property="userId" style="formFieldSized" value="<%=searchResultObject.getUserId().toString()%>"></html:checkbox></td>
											<td class="dataCellText" width="15%"><html:hidden
												name="logName" property="lgName"
												value="<%=searchResultObject.getLoginName().toString()%>" /><bean:write
												name="searchResultObject" property="loginName" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="firstName" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="lastName" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="organization" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="department" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="emailId" />&nbsp;</td>
											</tr>
										<%} else {oddRow = "true";%>
											<tr class="dataRowDark">
											<td class="dataCellNumerical" width="10%">
											<html:checkbox property="userId" style="formFieldSized" value="<%=searchResultObject.getUserId().toString()%>"></html:checkbox>
											</td>
											<td class="dataCellText" width="15%"><html:hidden
												name="logName" property="lgName"
												value="<%=searchResultObject.getLoginName().toString()%>" /><bean:write
												name="searchResultObject" property="loginName" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="firstName" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="lastName" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="organization" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="department" />&nbsp;</td>
												<td class="dataCellText" width="15%"><bean:write
													name="searchResultObject" property="emailId" />&nbsp;</td>
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
										<script>
											if (window.opener)
											{
												var loc2 = "";
												loc2 = window.opener.location + "";
												var locIndex2 = loc2.indexOf("ApplicationDBOperation");
												
												if ( locIndex2 != -1	)
												{
													document.write('<td><input type="button" value="Assign Admin" style="width:92px;" onclick="selSwitch(this);"></td>');
												}
											}
										</script>
										
										<script>
						
											if (!window.opener)
											{
												var loc1 = window.opener.location;
												var locIndex1 = loc1.indexOf("ApplicationDBOperation");
												if ( locIndex1 == -1 || locIndex1 =="" )
												{
													var read = 'read';
													document.write('<td><html:submit style="actionButton" onclick="setAndSubmit(read);">View Details</html:submit></td>');
												}
											}
										</script>
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
