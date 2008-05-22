
<%@ include file="/pages/imports.jsp"%>

<html:form styleId="employeeForm" action="updateEmployee">

	<script> 
    	function setAndSubmit()
    	{
    		var len = document.employeeForm.associatedIds.length;
    		for (i=0 ; i < len ; i++)
    		{
    			document.employeeForm.associatedIds[i].selected = true;
    		}
    		//document.employeeForm.submit();
    	}
    	
    	// selSwitch functions

		function selSwitch(btn)
		{
		   var i= btnType = 0;
		   var isavailableIds = doIt = false;
		
		   if (btn.value == "Add" || btn.value == "Remove") 
		      btnType = 1;
		   else if (btn.value == "Add All" || btn.value == "Remove All") 
		      btnType = 2;
		   else
		      btnType = 3;
		
	      isavailableIds = (btn.value.indexOf('Add') != -1) ? true : false;     
	
	      with ( ((isavailableIds)? document.employeeForm.availableIds: document.employeeForm.associatedIds) )
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
	                     document.employeeForm.associatedIds.options[document.employeeForm.associatedIds.length] = new Option( text, value );
	                  else
	                     document.employeeForm.availableIds.options[document.employeeForm.availableIds.length] = new Option( text, value );
	               } 
	               options[i] = null;
	               i--;
	            } 
	         } // end for loop
	         if (options[0] != null)
	            options[0].selected = true;
	      } // end with isavailableIds
		}    
    </script>


<br>

<table summary="" cellpadding="0" cellspacing="0" border="0"
class="contentPage" width="100%" height="100%">
<tr>
<td valign="top">
<table cellpadding="0" cellspacing="0" border="0"
class="contentBegins">

<tr>
			<td align="center" colspan="2"><h2>Employee Information</h2>
			</td>
		</tr>


	
		
		<tr>
			<td valign="top"><jsp:include page="/pages/employeeRecord.jsp" /></td>

			<td valign="top" class="contentBegins" width="50%">

			<table summary="" cellpadding="3" cellspacing="0" border="0"
				align="left">

				<!--<tr>
					<td class="formMessage" colspan="3">&nbsp;</td>
				</tr>-->

				<tr>
					<td class="formTitle" height="20" colspan="3">EMPLOYEE PROJECTS</td>
				</tr>

				<tr>
					<td class="dataTableHeader" scope="col" colspan="3">Add or Remove
					Project Assignments</td>
				</tr>

				<tr>
					<td class="formRequiredLabel2">Assigned Projects</td>

					<td class="dataCellText" width="75px">&nbsp;</td>

					<td class="formRequiredLabel2">Available Projects</td>
				</tr>
				
				<bean:define name="<%=Constants.UNASSIGNED_PROJECTS%>" id="availableIds" type="java.util.List"/>
				
				<bean:define name="<%=Constants.ASSIGNED_PROJECTS%>" id="associatedIds" type="java.util.List"/>				
					

				<tr>
					<td class="dataCellText"><select name="associatedIds" multiple
						style="width:115px;" size="10">
						<logic:iterate name="associatedIds"
							id="project" type="Project">
							<option
								value='<bean:write name="project" property="projectId" />'><bean:write
								name="project" property="name" /></option>
						</logic:iterate>

					</select>
					<td class="formRequiredLabel2" align="center"><input type="button"
						value="Add" style="width:70px;font:8pt" onclick="selSwitch(this);">
					<br>
					<br>
					<input type="button" value="Remove" style="width:70px;font:8pt"
						onclick="selSwitch(this);"> <br>
					</td>

					<td class="dataCellText">
					  <select name="availableIds" multiple
						style="width:115px;" size="10">
						<logic:iterate name="availableIds"
							id="project" type="Project">
							<option
								value='<bean:write name="project" property="projectId" />'><bean:write
								name="project" property="name" /></option>
						</logic:iterate>
					   </select>
					</td>

				</tr>
				<tr>
					<td align="right" colspan="3"><!-- action buttons begins -->
					<table cellpadding="4" cellspacing="0" border="0">
						<tr>
							<td><html:submit style="actionButton" onclick="setAndSubmit();">Update</html:submit></td>
						</tr>
					</table>
					<!-- action buttons end --></td>
				</tr>
			</table>
			</html:form>