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
<% int cntResObj=1; // - Count the number of objects to display %>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.SearchProtectionElementForm.operation.value=target;
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
		// -->
</script>


<table summary="" cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<html:form styleId="ProtectionElementForm"
		action="<%="/SearchProtectionElementDBOperation"%>">
		<html:hidden property="operation" value="read" />
		<tr>
			<td>
			<h2>Protection Element</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%"
				class="contentBegins">
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
											width="20%">Protection Element Description</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">Protection Element Type</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="20%">Object Id</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="15%">Attribute</th>
									</tr>
									<logic:iterate name="searchResultObjects"
										id="searchResultObject" type="ProtectionElement" length="200">
										<%if (oddRow.equals("true"))
		{
			oddRow = "false";%>
										<tr class="dataRowLight">
											<td class="dataCellNumerical" width="10%">
											<html:checkbox property="protectionElementId" style="formFieldSized" value="<%=searchResultObject.getProtectionElementId().toString()%>"></html:checkbox>
											</td>
											<td class="dataCellText" width="20%" ><html:hidden	name="protElementName" property="protectionElementName"
												value="<%=searchResultObject.getProtectionElementName().toString()%>" /><bean:write
												name="searchResultObject" property="protectionElementName" />&nbsp;</td>
											<td class="dataCellText" width="20%"><bean:write
												name="searchResultObject"
												property="protectionElementDescription" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
												name="searchResultObject" property="protectionElementType" />&nbsp;</td>
											<td class="dataCellText" width="20%"><bean:write
												name="searchResultObject" property="objectId" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
												name="searchResultObject" property="attribute" />&nbsp;</td>
										</tr>
										<%}
		else
		{
			oddRow = "true";%>
										<tr class="dataRowDark">
											<td class="dataCellNumerical" width="10%">
											<html:checkbox property="protectionElementId" style="formFieldSized" value="<%=searchResultObject.getProtectionElementId().toString()%>"></html:checkbox>
											</td>
											<td class="dataCellText" width="20%"><html:hidden	name="protElementName" property="protectionElementName"
												value="<%=searchResultObject.getProtectionElementName().toString()%>" /><bean:write
												name="searchResultObject" property="protectionElementName" />&nbsp;</td>
											<td class="dataCellText" width="20%"><bean:write
												name="searchResultObject"
												property="protectionElementDescription" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
												name="searchResultObject" property="protectionElementType" />&nbsp;</td>
											<td class="dataCellText" width="20%"><bean:write
												name="searchResultObject" property="objectId" />&nbsp;</td>
											<td class="dataCellText" width="15%"><bean:write
												name="searchResultObject" property="attribute" />&nbsp;</td>
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