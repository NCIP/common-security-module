
<%@ include file="/pages/imports.jsp"%>


<html:form method="post" action="updateEmployee">

<script> 
    <!--
    	function setAndSubmit(target)
    	{
    		document.form[0].operation.value=target;
    		var len = document.form[0].assignedprojects.length;
    		for (i=0 ; i < len ; i++)
    		{
    			document.form[0].assignedprojects[i].selected = true;
    		}
    		document.form[0].submit();
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
	
	      with ( ((isavailableIds)? document.dummyForm.availableprojects: document.form[0].assignedprojects) )
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
	                     document.form[0].assignedprojects.options[document.form[0].assignedprojects.length] = new Option( text, value );
	                  else
	                     document.dummyForm.availableprojects.options[document.dummyForm.availableprojects.length] = new Option( text, value );
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


	<table>
		<tr>
			<td align="center" colspan="2">
			<h2>Employee Information</h2>
			</td>
		</tr>
		<tr>
			<td valign="top"><jsp:include page="/pages/employeeRecord.jsp" /></td>

			<td valign="top" class="contentBegins" width="50%">

			<table summary="" cellpadding="3" cellspacing="0" border="0"
				align="left">

				<tr>
					<td class="formMessage" colspan="3">&nbsp;</td>
				</tr>

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

				<tr>
					<td class="dataCellText">
					<select name="assignedprojects" multiple="true"
						style="width:115px;" size="10" />
					<td class="formRequiredLabel2" align="center"><input type="button"
						value="Add" style="width:70px;font:8pt"> <br>
					<br>
					<input type="button" value="Remove" style="width:70px;font:8pt"> <br>
					</td>

					<td class="dataCellText"><html:select name="availableprojects" multiple="true"
						style="width:115px;" size="10" /></td>

				</tr>
				<tr>
					<td align="right" colspan="3"><!-- action buttons begins -->
					<table cellpadding="4" cellspacing="0" border="0">
						<tr>
							<td><html:submit>Update</html:submit></td>
						</tr>
					</table>
					<!-- action buttons end --></td>
				</tr>
			</table>
</html:form>

