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
	    		document.UserForm.operation.value=target;
    		}
    		else
    		{		
	    		var len = document.UserForm.associatedIds.length;
	    		for (i=0 ; i < len ; i++)
	    		{
	    			document.UserForm.associatedIds[i].selected = true;
	    		}
	    		document.UserForm.operation.value=target;
	    		document.UserForm.submit();
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
	
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.UserForm.associatedIds) )
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
	                     document.UserForm.associatedIds.options[document.UserForm.associatedIds.length] = new Option( text, value );
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
	document.getElementById("userAssoc").focus();
	window.location.hash="userAssoc";
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
					<h2><a id="userAssoc"></a>User and Groups Association</h2>
				</td>
			</tr>
			<logic:notEqual name="UserForm" property="userLoginName" value="<%=DisplayConstants.BLANK%>">
			<tr>
				<td>
					<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
						<tr>
							<td class="formTitle" height="20" colspan="2">SELECTED USER</td>
						</tr>
						<tr class="dataRowDark">
							<td class="formRequiredLabel" width="40%" scope="row"><label for="userLoginName">User Login Name</label></td>
							<td class="formField" width="60%"><bean:write name="UserForm" property="userLoginName" /></td>
						</tr>
					</table>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table cellpadding="3" cellspacing="10" border="0" height="100%" width="100%">
					<tr>
						<td class="infoMessage">
		  				<html:messages id="message" message="true">
		  				<bean:write name="message"/>
		  				</html:messages>				
		  				</td>
					</tr>
					<tr>
						<td class="formMessage" colspan="3">Assign or Deassign multiple <b>Groups</b> 
						for the selected <b>User</b>. To remove the complete association Deassign all the <b>Groups</b>.</td>
					</tr>
					
					<!-- large table starts -->
					
					<tr>
					<bean:define name="<%=DisplayConstants.AVAILABLE_SET%>" id="availableIds" type="java.util.Collection"/>
					<bean:define name="<%=DisplayConstants.ASSIGNED_SET%>" id="associatedIds" type="java.util.Collection"/>				
					
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

							<td class="sidebarTitle" height="20">AVAILABLE GROUPS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="availableIds" multiple style="width:100%;" size="6">
							<logic:iterate name="availableIds" id="group" type="Group">
								<option value="<bean:write name="group" property="groupId" />"><bean:write name="group" property="groupName" /></option>
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
					<html:form styleId="UserForm" action = '<%="/UserDBOperation"%>'>
					<html:hidden property="operation" value="read"/>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED GROUPS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="associatedIds" multiple style="width:100%;" size="6">
							<logic:iterate name="associatedIds" id="group" type="Group">
								<option value="<bean:write name="group" property="groupId" />"><bean:write name="group" property="groupName" /></option>
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

						<td><button class="actionButton" onclick="setAndSubmit('setAssociation');">Update Association</button></td>
						<td><html:submit style="actionButton" onclick="setAndSubmit('read');">Back</html:submit></td>						
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


