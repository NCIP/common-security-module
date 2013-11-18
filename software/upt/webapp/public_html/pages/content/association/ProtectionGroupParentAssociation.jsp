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
    	function setAndSubmit(target)
    	{
    		if (target == "read")
    		{
	    		document.ProtectionGroupForm.operation.value=target;
	    		document.ProtectionGroupForm.submit();
    		}
    		else
    		{		
	    		var len = document.ProtectionGroupForm.parentAssociatedIds.length;
    			if (len > 1)
	    		{
	    			alert ("Only single Parent Protection Group can be associated")
	    		}
	    		else
	    		{
		    		for (i=0 ; i < len ; i++)
		    		{
		    			document.ProtectionGroupForm.parentAssociatedIds[i].selected = true;
		    		}
		    		document.ProtectionGroupForm.operation.value=target;
		    		document.ProtectionGroupForm.submit();
				}
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
	
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.ProtectionGroupForm.parentAssociatedIds) )
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
	                     document.ProtectionGroupForm.parentAssociatedIds.options[document.ProtectionGroupForm.parentAssociatedIds.length] = new Option( text, value );
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

<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">
					<h2><a id="pgAssoc"></a>Protection Group and Parent Protection Group Association</h2>
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
						<td class="formMessage" colspan="3">Assign or Deassign single <b>Parent Protection Group</b> 
						for the selected <b>Protection Group</b>. To remove the complete association Deassign the assigned <b>Parent Protection Group</b>.</td>
					</tr>
					
					<!-- large table starts -->
					
					
					<tr>
					
					<!-- cell begins-->
					<td width="100%">
					<table width="100%">
					<!-- ROW 1 begins -->
					<tr>	
					
					<!-- first section -->
					
					
					
					
					<td width="100%" valign="top">
					<form name="dummyForm">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">AVAILABLE PGs</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="availableIds" style="width:100%;" size="6">
							<s:iterator value="#request.AVAILABLE_SET" var="protectionGroup">
								<option value='<s:property value="#protectionGroup.protectionGroupId"/>'><s:property value="#protectionGroup.protectionGroupName"/></option>
							</s:iterator>
	                    	</select>
	                    </td>
						</tr>
					</table>
					</form>
					</td>
					
					
					</tr>
					<!-- end first, start second -->
					<tr>
					
					<!-- extra code -->
							<td align="center" width="100%">
							<table width="220">
							<tr>
							<!-- -->
								<s:if test='#session.UPDATE_UPT_PROTECTION_GROUP_OPERATION != null'>
									<td align="center">
										<input type="button" value="Assign" style="width:75px;" onclick="selSwitch(this);">
										</td>
									<td align="center">	
										<input type="button" value="Deassign" style="width:75px;" onclick="selSwitch(this);">
									</td>
								</s:if>
								<s:else>
									<td align="center">
										<input type="button" value="Assign" style="width:75px;" disabled="disabled"/>
									</td>
									<td align="center">	
										<input type="button" value="Deassign" style="width:75px;" disabled="disabled"/>
									</td>
								</s:else>
					<!-- extra code -->
							</tr>
							</table>
							</td>
							<!-- -->	
					</tr>
					<!-- end second, start third -->
					<tr>
					<td width="100%" valign="top">
					<s:form name="ProtectionGroupForm" action="ProtectionGroupDBOperation" theme="simple">
					<s:hidden name="operation" value="read"/>
					<s:set var="protectionGroupId" value="#protectionGroupForm.getProtectionGroupId()"/>
					<s:hidden name="protectionGroupForm.protectionGroupId" value="%{protectionGroupId}"/>
					
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ProtectionGroupDBOperation'/>"/>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED PARENT PG</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="parentAssociatedIds" style="width:100%;" size="6">
							<s:iterator value="#request.ASSIGNED_SET" var="ProtectionGroup">
							1
								<option value='<s:property value="#ProtectionGroup.protectionGroupId"/>'><s:property value="#ProtectionGroup.protectionGroupName"/></option>
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
							<td><s:submit class="actionButton" onclick="setAndSubmit('setParentAssociation');" value="Update Association"/></td>
						</s:if>
						<s:else>
							<td><s:submit class="actionButton" disabled="disabled" value="Update Association"/></td>
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


