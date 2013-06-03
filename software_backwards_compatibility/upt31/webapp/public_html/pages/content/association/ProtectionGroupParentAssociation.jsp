<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>
    <script> 
    <!--
    	function setAndSubmit(target)
    	{
    		if (target == "read")
    		{
	    		document.ProtectionGroupForm.operation.value=target;
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
		}    // -->
    </script>

<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">
					<h2>Protection Group and Parent Protection Group Association</h2>
				</td>
			</tr>
			<logic:notEqual name="ProtectionGroupForm" property="protectionGroupName" value="<%=DisplayConstants.BLANK%>">
			<tr>
				<td>
					<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
						<tr>
							<td class="formTitle" height="20" colspan="2">SELECTED PROTECTION GROUP</td>
						</tr>
						<tr class="dataRowDark">
							<td class="formRequiredLabel" width="40%" scope="row"><label>Protection Group Name</label></td>
							<td class="formField" width="60%"><bean:write name="ProtectionGroupForm" property="protectionGroupName" /></td>
						</tr>
					</table>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table summary="" cellpadding="3" cellspacing="10" border="0" height="100%">
					<tr>
						<td class="infoMessage">
		  				<html:messages id="message" message="true">
		  				<bean:write name="message"/>
		  				</html:messages>				
		  				</td>
					</tr>
					<tr>
						<td class="formMessage" colspan="3">Assign or Deassign single <b>Parent Protection Group</b> 
						for the selected <b>Protection Group</b>. To remove the complete association Deassign the assigned <b>Parent Protection Group</b>.</td>
					</tr>
					
					<!-- large table starts -->
					
					
					<tr>
					<bean:define name="<%=DisplayConstants.AVAILABLE_SET%>" id="availableIds" type="java.util.Collection"/>
					<bean:define name="<%=DisplayConstants.ASSIGNED_SET%>" id="parentAssociatedIds" type="java.util.Collection"/>				
					
					
					<!-- cell begins-->
					<td width="100%">
					<table width="100%">
					<!-- ROW 1 begins -->
					<tr>	
					
					<!-- first section -->
					
					
					
					
					<td width="100%" valign="top">
					<form name="dummyForm">
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">AVAILABLE PGs</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="availableIds" style="width:100%;" size="6">
							<logic:iterate name="availableIds" id="protectionGroup" type="ProtectionGroup">
								<option value="<bean:write name="protectionGroup" property="protectionGroupId" />"><bean:write name="protectionGroup" property="protectionGroupName" /></option>
							</logic:iterate>
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
					
					<td align="center">
						<input type="button" value="Assign" style="width:75px;" onclick="selSwitch(this);">
						</td>
						
					<td align="center">	
						<input type="button" value="Deassign" style="width:75px;" onclick="selSwitch(this);">
					</td>
					
					
					<!-- extra code -->
							</tr>
							</table>
							</td>
							<!-- -->	
					
					
					</tr>
					<!-- end second, start third -->
					<tr>
					
					
					<td width="100%" valign="top">
					<html:form styleId="ProtectionGroupForm" action = "<%="/ProtectionGroupDBOperation"%>">
					<html:hidden property="operation" value="read"/>
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED PARENT PG</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="parentAssociatedIds" style="width:100%;" size="6">
							<logic:iterate name="parentAssociatedIds" id="protectionGroup" type="ProtectionGroup">
								<option value="<bean:write name="protectionGroup" property="protectionGroupId" />"><bean:write name="protectionGroup" property="protectionGroupName" /></option>
							</logic:iterate>
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
						<td><html:submit style="actionButton" onclick="setAndSubmit('read');">Back</html:submit></td>
						<td><button class="actionButton" onclick="setAndSubmit('setParentAssociation');">Update Association</button></td>
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
			
			</html:form>
		</table>
		</td>
	</tr>
</table>


