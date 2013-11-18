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
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<script> 
    <!--
    
    
    	//Anzen Comment(Added By Vijay) - Popup window for ProtectionElement Search and close the popup Windw (Code Start)
   		
	    function closepopup()
		{
			newwin = window.open("about:blank", "PESearchWindow");
			if(false == newwin.closed)
			{
				newwin.close();
			}
		}
    
    	function opennewwin(appContext)
    	{
    	
    		newwin = window.open("about:blank", "PESearchWindow", "left=100,top=190,scrollbars=1,width=790,height=400");
    		newwin.document.open();
    		newwin.document.writeln('<form name="ProtectionElementForm" method="post" action="/'+appContext+'/SearchProtectionElementDBOperation.action" id="ProtectionElementForm">');
    		newwin.document.writeln('<input type="hidden" name="operation" value="error">');
    		newwin.document.writeln('<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>">');
    		newwin.document.writeln('</form>');
    		newwin.document.close();
   			newwin.document.ProtectionElementForm.operation.value='loadSearch';
    		newwin.document.ProtectionElementForm.submit();
    	}
    	
		//Anzen Comment(Added By Vijay) - Code End
		
    	function setAndSubmit(target)
    	{
    		if (target == "read")
    		{
	    		document.ProtectionGroupForm.operation.value=target;
	    		document.ProtectionGroupForm.submti();
    		}
    		else
    		{		
	    		var len = document.ProtectionGroupForm.associatedIds.length;
	    		for (i=0 ; i < len ; i++)
	    		{
	    			document.ProtectionGroupForm.associatedIds[i].selected = true;
	    		}
	    		document.ProtectionGroupForm.operation.value=target;
	    		document.ProtectionGroupForm.submit();
			}
	    }    	
    	// selSwitch functions

		function selSwitch(btn)
		{
		   var i= btnType = 0;
		   var isavailableIds = doIt = false;
		
		   if (btn.value == "Assign" || btn.value == "Deassign") 
		      btnType = 1;
		   else if (btn.value == "Assign All" || btn.value == "Deassign All") 
		      btnType = 2;
		   else
		      btnType = 3;
		
	      isavailableIds = (btn.value.indexOf('Assign') != -1) ? true : false;     
	
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.ProtectionGroupForm.associatedIds) )
	      {
	         for (i = 0; i < length; i++)
	         {
	            doIt = false;
	            if (btnType == 1)
	            { 
	               if(options[i].selected) doIt = true;
	            }
	            else if (btnType == 2)
	            {
	               doIt = true;
	            } 
	            else 
	               if (!options[i].selected) doIt = true;
	             
	            if (doIt)
	            {
	               with (options[i])
	               {
	                  if (isavailableIds)
	                     document.ProtectionGroupForm.associatedIds.options[document.ProtectionGroupForm.associatedIds.length] = new Option( text, value );
	                  else
	                     document.dummyForm.availableIds.options[document.dummyForm.availableIds.length] = new Option( text, value );
	               } 
	               options[i] = null;
	               i--;
	            } 
	         } // end for loop
	         if (options[0] != null)
	            options[0].selected = true;
	      } // end with isavailableIds
		}    
		
function skipNavigation()
{
	document.getElementById("pgAssoc").focus();
	window.location.hash="pgAssoc";
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

<body onUnload="closepopup();">
<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">
					<h2><a id="pgAssoc"></a>Protection Group and Protection Elements Association</h2>
				</td>
			</tr>
			<s:set var="protectionGroupForm" value="#session.CURRENT_FORM"/>
			<s:if test='!protectionGroupForm.protectionGroupName.equals("")'>
			<tr>
				<td>
					<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
						<tr>
							<td class="formTitle" height="20" colspan="2">SELECTED PROTECTION GROUP</td>
						</tr>
						<tr class="dataRowDark">
							<td class="formRequiredLabel" width="40%" scope="row"><label for="protectionGroupName">Protection Group Name</label></td>
							<td class="formField" width="60%"><s:property value="#protectionGroupForm.protectionGroupName"/></td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table cellpadding="3" cellspacing="10" border="0" height="100%">
					<tr>
						<td class="infoMessage">
						<s:if test="hasActionMessages()">
						      <s:actionmessage/>
						</s:if>			  
		  				</td>
					</tr>
					<tr>
						<td class="formMessage" colspan="3">Assign or Deassign multiple <b>Protection Elements</b> 
						for the selected <b>Protection Group</b>. To remove the complete association Deassign all the <b>Protection Elements</b>.</td>
					</tr>
					
					<!-- large table starts -->
					
					<tr>
					<td>
					<form name="dummyForm">
							<select name="availableIds"  style="width:0;" size="0">
							<s:iterator value="#request.AVAILABLE_SET" var="protectionElement">
							</s:iterator>
				                    	</select>
					</form>
					</td>
					
					<!-- cell begins-->
					<td width="100%">
					<table width="100%">
					<!-- ROW 1 begins -->
					<tr>	
					
					<!-- first section -->
					
					
					
					
					
					</tr>
					<!-- end first, start second -->
					
					<!-- end second, start third -->
					<tr>
					
					
					<td width="35%" valign="top">
					<s:form name="ProtectionGroupForm" action="ProtectionGroupDBOperation" theme="simple">
					<s:hidden name="operation" value="read"/>
					<s:set var="protectionGroupId" value="#protectionGroupForm.getProtectionGroupId()"/>
					<s:hidden name="protectionGroupForm.protectionGroupId" value="%{protectionGroupId}"/>
					
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ProtectionGroupDBOperation'/>"/>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>
							<td class="sidebarTitle" height="20">ASSIGNED PEs</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="associatedIds" multiple style="width:100%;" size="6">
							<s:iterator value="#request.ASSIGNED_SET" var="protectionElement">
								<option value='<s:property value="#protectionElement.protectionElementId"/>'><s:property value="#protectionElement.protectionElementName"/></option>
							</s:iterator>
	                    	</select>
	                    </td>
						</tr>
					</table>
					</td>
					
					</tr>
					<!-- end third section -->
					
					<tr>
				<td align="right" class="actionSection"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
						<s:if test='#session.UPDATE_UPT_PROTECTION_GROUP_OPERATION != null'>
							<td>
							<script>
								var tempURL = window.location+"";			
								var url_array= tempURL.split("/");
								var contextTemp = url_array[3]+"";
								var temp = contextTemp.toLowerCase();
								document.write('<input type="button" value="Assign PE" style="width:75px;" onclick="closepopup();opennewwin(contextTemp);">');					
							</script>
							</td>
							<td><input type="button" value="Deassign" style="width:92px;" onclick="selSwitch(this);"></td>
							<td><button class="actionButton" onclick="setAndSubmit('setAssociation');">Update Association</button></td>
						</s:if>
						<s:else>
							<td align="center"><input type="button" value="Assign" style="width:75px;" disabled="disabled"/></td>
							<td align="center"><input type="button" value="Deassign" style="width:95px;" disabled="disabled"></td>
							<td align="center"><input type="button" value="Update Association" style="width:125px;" disabled="disabled"></td>
						</s:else>
						<td><s:submit style="actionButton" onclick="setAndSubmit('read');" value="Back"/></td>						
					</tr>
				</table>
				</td>				
			</tr>
			
			<!--close up big table-->
					</table>
					</td>
					
					
					
					</tr>
				</table>
			</tr>
			
			</s:form>
		</table>
		</td>
	</tr>
</table>


