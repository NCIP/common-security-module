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
		document.associationForm.operation.value=target;
	}
    	
	<logic:notPresent name="<%=DisplayConstants.ONLY_ROLES%>">

	   	function setAndSubmitPG(target)
		{
			var len = document.associationForm.protectionGroupAssociatedIds.length;
			
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
	   			document.associationForm.protectionGroupAssociatedIds[0].selected = true;
	
	    		var roleLen = document.associationForm.roleAssociatedIds.length;
				if (roleLen == 0)
				{
					alert ("At least one Role should be selected");
					return;
				}
		       	else
		       	{
			       	for (i=0 ; i < roleLen ; i++)
		    		{
		    			document.associationForm.roleAssociatedIds[i].selected = true;
		    		}
	
		    		document.associationForm.operation.value=target;
		    		document.associationForm.submit();
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
	
	      with ( ((isavailableProtectionGroupIds)? document.associationForm.availableProtectionGroupIds: document.associationForm.protectionGroupAssociatedIds) )
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
	                     document.associationForm.protectionGroupAssociatedIds.options[document.associationForm.protectionGroupAssociatedIds.length] = new Option( text, value );
	                  else
	                     document.associationForm.availableProtectionGroupIds.options[document.associationForm.availableProtectionGroupIds.length] = new Option( text, value );
	               } 
	               options[i] = null;
	               i--;
	            } 
	         } // end for loop
	         if (options[0] != null)
	            options[0].selected = true;
	      } // end with isavailableProtectionGroupIds
		}   
	</logic:notPresent>

	function setAndSubmitRole(target)
	{
		var roleLen = document.associationForm.roleAssociatedIds.length;
		if (roleLen == 0)
		{
			alert ("At least one Role should be selected");
			return;
		}
	   	else
	   	{
	       	for (i=0 ; i < roleLen ; i++)
			{
				document.associationForm.roleAssociatedIds[i].selected = true;
			}
	
			document.associationForm.operation.value=target;
			document.associationForm.submit();
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

      with ( ((isavailableRoleIds)? document.associationForm.availableRoleIds: document.associationForm.roleAssociatedIds) )
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
                     document.associationForm.roleAssociatedIds.options[document.associationForm.roleAssociatedIds.length] = new Option( text, value );
                  else
                     document.associationForm.availableRoleIds.options[document.associationForm.availableRoleIds.length] = new Option( text, value );
               } 
               options[i] = null;
               i--;
            } 
         } // end for loop
         if (options[0] != null)
            options[0].selected = true;
      } // end with isavailableRoleIds
	}    //-->
    </script>

<table summary="" cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0"
			class="contentBegins">
			<tr>
				<td colspan="3">
				<h2>Group, Protection Group and Roles Association</h2>
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
								<td class="formRequiredLabel" width="40%" scope="row"><label>Group Name</label></td>
								<td class="formField" width="60%"><bean:write name="GroupForm" property="groupName" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table summary="" cellpadding="3" cellspacing="10" border="0"
					height="100%">
					<tr>
						<td class="infoMessage">
		  				<html:messages id="message" message="true">
		  				<bean:write name="message"/>
		  				</html:messages>				
		  				</td>
					</tr>
					<logic:notPresent name="<%=DisplayConstants.ONLY_ROLES%>">
						<tr>
							<td class="formMessage" colspan="3">Select a single <b>Protection Group</b> and 
							Multiple <b>Roles</b> which are to  be associated with the selected <b>Group</b></td>
						</tr>					
					</logic:notPresent>
					<logic:present name="<%=DisplayConstants.ONLY_ROLES%>">
						<tr>
							<td class="formMessage" colspan="3">Assign or Deassign multiple <b>Roles</b> 
							for the selected <b>Group</b> and <b>Protection Group</b>. To remove the complete association Deassign all the <b>Roles</b>.</td>
						</tr>
					</logic:present>
					<html:form styleId="associationForm"
						action="<%="/GroupDBOperation"%>">
						<html:hidden property="operation" value="read" />
						<logic:notPresent name="<%=DisplayConstants.ONLY_ROLES%>">
							<tr>
								<bean:define
									name="<%=DisplayConstants.AVAILABLE_PROTECTIONGROUP_SET%>"
									id="availableProtectionGroupIds" type="java.util.Collection" />
								<td width="35%" valign="top">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSection">
									<tr>
										<td class="sidebarTitle" height="20">AVAILABLE PROTECTION
										GROUPS</td>
									</tr>
									<tr>
										<td class="formField" align="center"><select
											name="availableProtectionGroupIds" style="width:200px;"
											size="6">
											<logic:iterate name="availableProtectionGroupIds"
												id="protectionGroup" type="ProtectionGroup">
												<option
													value="<bean:write name="protectionGroup" property="protectionGroupId" />"><bean:write
													name="protectionGroup" property="protectionGroupName" /></option>
											</logic:iterate>
										</select></td>
									</tr>
								</table>
								</td>
								<td align="center"><input type="button" value="Assign"
									style="width:75px;" onclick="selSwitchPG(this);"> <br>
								<br>
								<input type="button" value="Deassign" style="width:75px;"
									onclick="selSwitchPG(this);"> <br>
								</td>
								<td width="35%" valign="top">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSection">
									<tr>

										<td class="sidebarTitle" height="20">ASSIGNED PROTECTION
										GROUPS</td>
									</tr>
									<tr>
										<td class="formField" align="center"><select
											name="protectionGroupAssociatedIds" style="width:200px;"
											size="6">
										</select></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<br><br><br>
							</tr>
						</logic:notPresent>
						<tr>
							<bean:define name="<%=DisplayConstants.AVAILABLE_ROLE_SET%>"
								id="availableRoleIds" type="java.util.Collection" />
							<bean:define name="<%=DisplayConstants.ASSIGNED_ROLE_SET%>"
								id="roleAssociatedIds" type="java.util.Collection" />
							<td width="35%" valign="top">
							<table summary="" cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>
									<td class="sidebarTitle" height="20">AVAILABLE ROLES</td>
								</tr>
								<tr>
									<td class="formField" align="center"><select multiple
										name="availableRoleIds" style="width:200px;" size="6">
										<logic:iterate name="availableRoleIds" id="role" type="Role">
											<option value="<bean:write name="role" property="id" />"><bean:write
												name="role" property="name" /></option>
										</logic:iterate>
									</select></td>
								</tr>
							</table>
							</td>
							<td align="center"><input type="button" value="Assign"
								style="width:75px;" onclick="selSwitchRole(this);"> <br>
							<br>
							<input type="button" value="Deassign" style="width:75px;"
								onclick="selSwitchRole(this);"> <br>
							</td>
							<td width="35%" valign="top">
							<table summary="" cellpadding="0" cellspacing="0" border="0"
								width="100%" class="sidebarSection">
								<tr>

									<td class="sidebarTitle" height="20">ASSIGNED ROLES</td>
								</tr>
								<tr>
									<td class="formField" align="center"><select multiple
										name="roleAssociatedIds" style="width:200px;" size="6">
										<logic:iterate name="roleAssociatedIds" id="role" type="Role">
											<option value="<bean:write name="role" property="id" />"><bean:write
												name="role" property="name" /></option>
										</logic:iterate>
									</select></td>
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
						<td><html:submit style="actionButton"
							onclick="setAndSubmit('read');">Back</html:submit></td>
						<logic:notPresent name="<%=DisplayConstants.ONLY_ROLES%>">
						<td><input type="button" style="actionButton" onclick="setAndSubmitPG('setDoubleAssociation');" value="Update Association"></td>
						</logic:notPresent>
						<logic:present name="<%=DisplayConstants.ONLY_ROLES%>">
						<td><input type="button" style="actionButton" onclick="setAndSubmitRole('setRoleAssociation');" value="Update Association"></td>
						</logic:present>
					</tr>
				</table>
				</td>
			</tr>
			</html:form>
		</table>
		</td>
	</tr>
</table>


