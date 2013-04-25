<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<script> 
    <!--
    
   		
	    function closepopup()
		{
			newwin = window.open("about:blank", "UserSearchWin");
			if(false == newwin.closed)
			{
				newwin.close();
			}
		}
    
    	function opennewwin(appContext)
    	{
    	
    		newwin = window.open("about:blank", "UserSearchWin", "left=100,top=190,scrollbars=1,width=790,height=400");
    		newwin.document.open();
    		newwin.document.writeln('<form name="UserForm" method="post" action="/'+appContext+'/SearchUserDBOperation.do" id="UserForm">');
    		newwin.document.writeln('<input type="hidden" name="operation" value="error">');
    		newwin.document.writeln('<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>">');
    		newwin.document.writeln('</form>');
    		newwin.document.close();
   			newwin.document.UserForm.operation.value='loadSearch';
    		newwin.document.UserForm.submit();
    	}
    	
		//Anzen Comment(Added By Vijay) - Code End
    	
    	function setAndSubmit(target)
    	{
    			if (target == "read")
    			{
	    			document.GroupForm.operation.value=target;
	    			document.GroupForm.submit();
    			}
    			else
    			{		
	    			var len = document.GroupForm.associatedIds.length;
	    			for (i=0 ; i < len ; i++)
	    			{
	    				document.GroupForm.associatedIds[i].selected = true;
	    			}
	    			document.GroupForm.operation.value=target;
	    			document.GroupForm.submit();
				}
	    }

		function selSwitch(btn)
		{
		   var i= btnType = 0;
		   var isavailableIds = doIt = false;
		
		   if (btn.value == "Assign User" || btn.value == "Deassign User") 
		      btnType = 1;
		   else if (btn.value == "Assign All" || btn.value == "Deassign All") 
		      btnType = 2;
		   else
		      btnType = 3;
		
	      isavailableIds = (btn.value.indexOf('Assign') != -1) ? true : false;     
	
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.GroupForm.associatedIds) )
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
	                     document.GroupForm.associatedIds.options[document.GroupForm.associatedIds.length] = new Option( text, value );
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
	document.getElementById("grpAssoc").focus();
	window.location.hash="grpAssoc";
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
					<h2><a id="grpAssoc"></a>Group And User Association</h2>
				</td>
			</tr>
			<logic:notEqual name="GroupForm" property="groupName" value="<%=DisplayConstants.BLANK%>">
			<tr>
				<td>
					<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
						<tr>
							<td class="formTitle" height="20" colspan="2">SELECTED GROUP</td>
						</tr>
						<tr class="dataRowDark">
							<td class="formRequiredLabel" width="40%" scope="row"><label for="groupName">Group Name</label></td>
							<td class="formField" width="60%"><bean:write name="GroupForm" property="groupName" /></td>
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
						<td  align="center" class="formMessage" colspan="3">Assign or Deassign multiple <b>Users</b> 
						for the selected <b>Group</b>. To remove the complete association Deassign all the <b>Users</b>.</td>
					</tr>
					
					<tr>
					<bean:define name="<%=DisplayConstants.AVAILABLE_SET%>" id="availableIds" type="java.util.Collection"/>
					<bean:define name="<%=DisplayConstants.ASSIGNED_SET%>" id="associatedIds" type="java.util.Collection"/>				
					<td>
					<form name="dummyForm">
							<select name="availableIds"  style="width:0;" size="0">
							<logic:iterate name="availableIds" id="user" type="User">
							</logic:iterate>
	                    	</select>
					</form>
					</td>
					<!-- big table starts -->
					<td width="100%">
					<table width="100%">
					<!-- ROW 1 begins -->
					<tr>

					<td width="0%" valign="top">
					
					</td>
	
					<!-- transition to ROW 2 -->
					</tr>
					<tr>							
					
					<td align="center" width="100%">
					<table width="220">
					<tr>
					<td align="center">


		
					</table>	
					</td>
					
					<!-- transition to ROW 3 -->
					</tr>
					<tr>		
					
					<td width="100%" valign="top">
					<html:form styleId="GroupForm" action="/GroupDBOperation">
					<html:hidden property="operation" value="read"/>
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/GroupDBOperation'/>"/>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED ADMINISTRATORS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
							<select name="associatedIds" multiple style="width:100%;" size="6">
							<logic:iterate name="associatedIds" id="user" type="User">
								<option value="<bean:write name="user" property="userId" />"><bean:write name="user" property="loginName" /></option>
							</logic:iterate>
	                    	</select>
	                    </td>
						</tr>
					</table>
					</td>
					
					
					</tr>
					<!-- finish up changes -->
					<!-- add bottom row -->
					
					<tr>
				<td align="right" class="actionSection"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
						<logic:present name='<%=Constants.CSM_UPDATE_PRIVILEGE +"_"+Constants.UPT_GROUP_OPERATION%>'>						
							<td align="center">
						<script>
							var tempURL = window.location+"";			


							var url_array= tempURL.split("/");
							var contextTemp = url_array[3]+"";
							var temp = contextTemp.toLowerCase();

							document.write('<input type="button" value="Assign User" onclick="closepopup();opennewwin(contextTemp);">');					
						</script>
							</td>						
							
							<td align="center">	<input type="button" value="Deassign User" onclick="selSwitch(this);"></td>
							<td><button class="actionButton" onclick="setAndSubmit('setAssociation');">Update Association</button></td>
						</logic:present>
						<logic:notPresent name='<%=Constants.CSM_UPDATE_PRIVILEGE +"_"+Constants.UPT_GROUP_OPERATION%>'>
							<td align="center">	<input type="button" value="Assign User" disabled="disabled"/></td>
							<td align="center">	<input type="button" value="Deassign User" disabled="disabled"/></td>
							<td><html:submit disabled="true">Update Association</html:submit></td>
						</logic:notPresent>
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
</body>

