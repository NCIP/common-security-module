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
		document.UserForm.operation.value=target;
		document.UserForm.submit();
	}
	
	<s:if test="%{#session['ONLY_ROLES'] == null}">
	   	function setAndSubmitPG(target)
		{
			var len = document.UserForm.protectionGroupAssociatedIds.length;
			
			if (len == 0)
			{
				alert ("At least one Protection Group should be selected");
				return;
			}
			else if (len > 1)
			{
				alert ("Only a Single Protection Group can be selected");
				return;
			}
			else
			{
	   			document.UserForm.protectionGroupAssociatedIds[0].selected = true;
	
	    		var roleLen = document.UserForm.roleAssociatedIds.length;
				if (roleLen == 0)
				{
					alert ("At least one Role should be selected");
					return;
				}
		       	else
		       	{
			       	for (i=0 ; i < roleLen ; i++)
		    		{
		    			document.UserForm.roleAssociatedIds[i].selected = true;
		    		}
	
		    		document.UserForm.operation.value=target;
		    		document.UserForm.submit();
		    	}
		    }
		}
		
		function selSwitchPG(btn)
		{
		   var i= btnType = 0;
		   var isavailableProtectionGroupIds = doIt = false;
		
		   if (btn.value == "Assign" || btn.value == "Deassign") 
		      btnType = 1;
		   else if (btn.value == "Assign All" || btn.value == "Deassign All") 
		      btnType = 2;
		   else
		      btnType = 3;
		
	      isavailableProtectionGroupIds = (btn.value.indexOf('Assign') != -1) ? true : false;     
	
	      with ( ((isavailableProtectionGroupIds)? document.UserForm.availableProtectionGroupIds: document.UserForm.protectionGroupAssociatedIds) )
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
	                  if (isavailableProtectionGroupIds)
	                     document.UserForm.protectionGroupAssociatedIds.options[document.UserForm.protectionGroupAssociatedIds.length] = new Option( text, value );
	                  else
	                     document.UserForm.availableProtectionGroupIds.options[document.UserForm.availableProtectionGroupIds.length] = new Option( text, value );
	               } 
	               options[i] = null;
	               i--;
	            } 
	         } // end for loop
	         if (options[0] != null)
	            options[0].selected = true;
	      } // end with isavailableProtectionGroupIds
		} //-->    
	</s:if>

	function setAndSubmitRole(target)
	{
		var roleLen = document.UserForm.roleAssociatedIds.length;
		if (roleLen == 0)
		{
			alert ("At least one Role should be selected");
			return;
		}
	   	else
	   	{
	       	for (i=0 ; i < roleLen ; i++)
			{
				document.UserForm.roleAssociatedIds[i].selected = true;
			}
	
			document.UserForm.operation.value=target;
			document.UserForm.submit();
		}
	}

	function selSwitchRole(btn)
	{
	   var i= btnType = 0;
	   var isavailableRoleIds = doIt = false;
	
	   if (btn.value == "Assign" || btn.value == "Deassign") 
	      btnType = 1;
	   else if (btn.value == "Assign All" || btn.value == "Deassign All") 
	      btnType = 2;
	   else
	      btnType = 3;
	
      isavailableRoleIds = (btn.value.indexOf('Assign') != -1) ? true : false;     

      with ( ((isavailableRoleIds)? document.UserForm.availableRoleIds: document.UserForm.roleAssociatedIds) )
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
                  if (isavailableRoleIds)
                     document.UserForm.roleAssociatedIds.options[document.UserForm.roleAssociatedIds.length] = new Option( text, value );
                  else
                     document.UserForm.availableRoleIds.options[document.UserForm.availableRoleIds.length] = new Option( text, value );
               } 
               options[i] = null;
               i--;
            } 
         } // end for loop
         if (options[0] != null)
            options[0].selected = true;
      } // end with isavailableRoleIds
	}   

function skipNavigation()
{
	document.getElementById("usrAssoc").focus();
	window.location.hash="usrAssoc";
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
	
	//-->
    </script>

<table cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			class="contentBegins">
			<tr>
				<td>
				<h2><a id="usrAssoc"></a>User, Protection Group and Roles Association</h2>
				</td>
			</tr>
			<s:set var="userForm" value="#session.CURRENT_FORM"/>
			<s:if test='(#userForm.getUserLoginName() != "")'>
				<tr>
					<td>
						<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
							<tr>
								<td class="formTitle" height="20" colspan="2">SELECTED USER</td>
							</tr>
							<tr class="dataRowDark">
								<td class="formRequiredLabel" width="40%" scope="row"><label for="userLoginName">User Login Name</label></td>
								<td class="formField" width="60%"><s:property value="#userForm.userLoginName"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</s:if>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table cellpadding="3" cellspacing="10" border="0"
					height="100%" width="100%">
					<tr>
						<td class="infoMessage">
							<s:if test="hasActionMessages()">
							      <s:actionmessage/>
							</s:if>			  
		  				</td>
					</tr>
					<s:if test="#session.ONLY_ROLES == null">
						<tr>
							<td class="formMessage">Select a single <b>Protection Group</b> to associate with the selected <b>User</b>.</td>
						</tr>					
					</s:if>
					
					
					<s:form name="UserForm"
						action="UserDBOperation" theme="simple">
						<s:hidden name="operation" value="read" />
						<s:set var="userId" value="#userForm.getUserId()"/>
						<s:hidden name="userForm.userId" value="%{userId}"/>
						
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/UserDBOperation'/>"/>
						<s:if test="#session.ONLY_ROLES == null">
							<tr>
							<!-- protection group section -->
							
							<tr>
							<td width="100%">
							<table width="100%">
							
							
							<tr>  <!-- first row (available protection groups) -->
								<td width="100%" valign="top">
								<table cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSection">
									<tr>
										<td class="sidebarTitle" height="20">AVAILABLE PROTECTION
										GROUPS</td>
									</tr>
									<tr>
										<td class="formField" align="center">
										<select
											name="availableProtectionGroupIds" style="width:100%;"
											size="6">
											<s:iterator value="#request.AVAILABLE_PROTECTIONGROUP_SET" var="protectionGroup">
												<option
													value='<s:property value="#protectionGroup.getProtectionGroupId()"/>'>
													<s:property value="#protectionGroup.getProtectionGroupName()"/>
												</option>
											</s:iterator>
										</select></td>
									</tr>
								</table>
								</td>
								
								
								</tr>
								<!-- second row - buttons -->
								<tr>
								
								<!-- extra code -->
							<td align="center" width="100%">
							<table width="220">
							<tr>
							<!-- -->
								
								
								<td align="center"><input type="button" value="Assign"
									style="width:75px;" onclick="selSwitchPG(this);"> </td>
								<td align="center">
								<input type="button" value="Deassign" style="width:75px;"
									onclick="selSwitchPG(this);"> 
								</td>
								
								
								<!-- extra code -->
							</tr>
							</table>
							</td>
							<!-- -->								
								
								
								
								</tr>
								<!-- third row - assigned protection groups -->
								<tr>
								
								
								
								
								<td width="100%" valign="top">
								<table cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSection">
									<tr>

										<td class="sidebarTitle" height="20">ASSIGNED PROTECTION
										GROUP</td>
									</tr>
									<tr>
										<td class="formField" align="center"><select
											name="protectionGroupAssociatedIds" style="width:100%;"
											size="6">
										</select></td>
									</tr>
								</table>
								</td>
								
								
							</tr>
								<!-- end third row-->
								
								
								
							</table>
							</td>
							</tr>			
							<!-- end protection group section -->
							
							
							
							<!-- spacer -->							
							<tr>
								<br><br>
							</tr>
							
							
							
							
						</s:if>
						<!-- end Protection group section-->
						
						
						
						<s:if test="#session.ONLY_ROLES == null">
						<tr>
							<td class="formMessage">Select <b>Roles</b> which are to be associated with the selected <b>User</b>.</td>
						</tr>					
						</s:if>
						<s:if test="#session.ONLY_ROLES != null">
						<tr>
							<td class="formMessage">Assign or Deassign multiple <b>Roles</b> 
							for the selected <b>User</b> and <b>Protection Group</b>.</td>
						</tr>
						</s:if>
						
						<!-- role section -->
						    <tr>
							<td width="100%">
							<table width="100%">
						
						
						<!-- row 1 starts - available roles -->
						<tr>
							<td width="100%" valign="top">
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>
									<td class="sidebarTitle" height="20">AVAILABLE ROLES</td>
								</tr>
								<tr>
									<td class="formField" align="center"><select multiple
										name="availableRoleIds" style="width:100%;" size="6">
										<s:iterator value="#request.AVAILABLE_ROLE_SET" var="role">
											<option value='<s:property value="#role.id"/>'><s:property value="#role.name"/></option>
										</s:iterator>
									</select></td>
								</tr>
							</table>
							</td>
							
							
							</tr>
							<!-- transition to row 2 - buttons -->
							<tr>
							
							<!-- extra code -->
						<td align="center" width="100%">
							<table width="220">
							<tr>
							<!-- -->
							<s:if test="#session.UPDATE_UPT_USER_OPERATION != null">
								<td align="center"><input type="button" value="Assign"
									style="width:75px;" onclick="selSwitchRole(this);"> </td>
								<td align="center">
								<input type="button" value="Deassign" style="width:75px;"
									onclick="selSwitchRole(this);"> 
								</td>
							</s:if>
							<s:if test="#session.UPDATE_UPT_USER_OPERATION == null">
								<td align="center"><input type="button" value="Assign"	style="width:75px;" disabled="disabled"></td>
								<td align="center"><input type="button" value="Deassign" style="width:75px;" disabled="disabled"></td>
							</s:if>
							
							<!-- extra code -->
							</tr>
							</table>
							</td>
							<!-- -->
							
							</tr>
							<!-- transition to row 3 - assigned -->
							<tr>
							
							
							
							<td width="100%" valign="top">
							<table cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">ASSIGNED ROLES</td>
								</tr>
								<tr>
									<td class="formField" align="center"><select multiple
										name="roleAssociatedIds" style="width:100%;" size="6">
										<s:iterator value="#request.ASSIGNED_ROLE_SET" var="role">
											<option value='<s:property value="#role.id"/>'> <s:property value="#role.name"/></option>
										</s:iterator>
									</select></td>
								</tr>
							</table>
							</td>
							
							
							
							</tr>
							<!-- end 3rd row-->
							
							</table>
							</td>
							</tr>							
						<!-- end role section -->
							
							
							
							
						
				</table>
			</tr>
			<tr>
				<td align="right" class="actionSection"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
						<s:if test="#session.UPDATE_UPT_USER_OPERATION != null">
							<s:if test="#session.ONLY_ROLES == null">
								<td><input type="button" style="actionButton" onclick="setAndSubmitPG('setDoubleAssociation');" value="Update Association"></td>
							</s:if>	
							<s:if test="#session.ONLY_ROLES != null">
								<td><input type="button" style="actionButton" onclick="setAndSubmitRole('setRoleAssociation');" value="Update Association"></td>
							</s:if>
						</s:if>
						<s:if test="#session.UPDATE_UPT_USER_OPERATION == null">
							<td><input type="button" style="actionButton" disabled="disabled" value="Update Association"/></td>
						</s:if>
						<td><s:submit style="actionButton"
							onclick="setAndSubmit('loadProtectionGroupAssociation');" value="Back"/></td>
					</tr>
				</table>
				</td>
			</tr>
			</s:form>
		</table>
		</td>
	</tr>
</table>


