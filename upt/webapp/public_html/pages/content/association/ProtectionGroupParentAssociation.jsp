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
    		document.associationForm.operation.value=target;
    		var len = document.associationForm.associatedIds.length;
    		for (i=0 ; i < len ; i++)
    		{
    			document.associationForm.associatedIds[i].selected = true;
    		}
    		document.associationForm.submit();
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
	
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.associationForm.associatedIds) )
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
	                     document.associationForm.associatedIds.options[document.associationForm.associatedIds.length] = new Option( text, value );
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
		<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">
					<h2>Protection Group and Parent Protection Group Association</h2>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table summary="" cellpadding="3" cellspacing="10" border="0" height="100%">
					<tr>
						<td>
		  				<html:messages id="message" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
		  				<li><bean:write name="message"/></li>
		  				</html:messages>				
		  				</td>
					</tr>					
					<tr>
					<bean:define name="<%=DisplayConstants.AVAILABLE_SET%>" id="availableIds" type="java.util.Collection"/>
					<bean:define name="<%=DisplayConstants.ASSIGNED_SET%>" id="associatedIds" type="java.util.Collection"/>				
					<td width="35%" valign="top">
					<form name="dummyForm">
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">AVAILABLE PROTECTION GROUPS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="availableIds" multiple style="width:200px;" size="6">
							<logic:iterate name="availableIds" id="protectionGroup" type="ProtectionGroup">
								<option value="<bean:write name="protectionGroup" property="protectionGroupId" />"><bean:write name="protectionGroup" property="protectionGroupName" /></option>
							</logic:iterate>
	                    	</select>
	                    </td>
						</tr>
					</table>
					</form>
					</td>
					<td align="center">
						<input type="button" value="Assign" style="width:75px;" onclick="selSwitch(this);">
						<br><br>
						<input type="button" value="Deassign" style="width:75px;" onclick="selSwitch(this);">
						<br>
					</td>
					<td width="35%" valign="top">
					<html:form styleId="associationForm" action = "<%="/ProtectionGroupDBOperation"%>">
					<html:hidden property="operation" value="error"/>
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED PARENT PROTECTION GROUPS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="parentAssociatedIds" multiple style="width:200px;" size="6">
							<logic:iterate name="associatedIds" id="protectionGroup" type="ProtectionGroup">
								<option value="<bean:write name="protectionGroup" property="protectionGroupId" />"><bean:write name="protectionGroup" property="protectionGroupName" /></option>
							</logic:iterate>
	                    	</select>
	                    </td>
						</tr>
					</table>
					</td>
					</tr>
				</table>
			</tr>
			<tr>
				<td align="right" class="actionSection"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
						<td><html:submit style="actionButton" onclick="setAndSubmit('read');">Back</html:submit></td>
						<td><html:submit style="actionButton" onclick="setAndSubmit('setParentAssociation');">Update Association</html:submit></td>
					</tr>
				</table>
				</td>				
			</tr>
			</html:form>
		</table>
		</td>
	</tr>
</table>


