<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>

<script type='text/javascript' src='dwr/interface/InstanceLevelHelper.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script> 
 
<script>
<!--
 
      function displayErrorMessage(errorMessage)
      {
        alert(errorMessage);
      }
      
      function setAndSubmit(target)
      {
            if (target == "delete")
            {
                  if (confirm("Are you sure you want to delete the record?"))
                  {
                        document.InstanceLevelForm.operation.value=target;
                        document.InstanceLevelForm.submit();
                  }
            }
            if (target == "create")
            {
                  var tbl = document.getElementById('childClassTable');
                  var rowCount = tbl.rows.length;                 
                  var variableName;
                  var className;
                  for (var i = 1; i <= rowCount; i++)
                  {
                        var selectElement = document.getElementById('filterChainElement' + (i));
                        var vName = selectElement[selectElement.selectedIndex].value;
                        var cName = selectElement[selectElement.selectedIndex].innerHTML;
                        if (i==1)
                        {
                              variableName = vName;
                              className = cName;
                        }
                        else
                        {
                              variableName = variableName + ', ' + vName;
                              className = cName;
                        }
                  }
                  document.InstanceLevelForm.filterChain.value = variableName;
                  document.InstanceLevelForm.targetClassName.value = className;
                  
                  var attributeSelectElement = document.getElementById('targetClassAttributeNameList');
                  var attributeClass = attributeSelectElement[attributeSelectElement.selectedIndex].value;
                  var attributeName = attributeSelectElement[attributeSelectElement.selectedIndex].innerHTML;
                  document.InstanceLevelForm.targetClassAttributeName.value = attributeName;
                  document.InstanceLevelForm.targetClassAttributeType.value = attributeClass;
            }
            document.InstanceLevelForm.operation.value=target;
      }
      
      function addRow()
      {
            dwr.engine.setErrorHandler(displayErrorMessage);
            var tbl = document.getElementById('childClassTable');
            var lastRow = tbl.rows.length;
            var selectElement = document.getElementById('filterChainElement' + (lastRow));
            var className = selectElement[selectElement.selectedIndex].innerHTML;
            dwr.engine.setErrorHandler(displayErrorMessage);
            InstanceLevelHelper.getAssociatedClasses(className, addRowsData);
      }
      
      function addRowsData(map)
      {
            var tbl = document.getElementById('childClassTable');
            var lastRow = tbl.rows.length;
            var iteration = lastRow + 1;
            var row = tbl.insertRow(lastRow);
            var cell = row.insertCell(0);
            cell.marginTop
            var sel = document.createElement('select');
            sel.name = 'filterChainElement' + iteration;
            sel.id = 'filterChainElement' + iteration;
            dwr.util.addOptions (sel,map);
            cell.appendChild(sel);
      }
      
      
      function removeRow()
      {
            var tbl = document.getElementById('childClassTable');
            var lastRow = tbl.rows.length;
            if (lastRow > 1) tbl.deleteRow(lastRow - 1);
      }
      
      function loadFirstChildList()
      {
            var className = eval('document.InstanceLevelForm.className').value;
            dwr.engine.setErrorHandler(displayErrorMessage);
            InstanceLevelHelper.getAssociatedClasses(className, loadFirstChildListData);
      }
      
      function loadFirstChildListData(map)
      {
            dwr.util.useLoadingMessage();
            var tbl = document.getElementById('childClassTable');
            var lastRow = tbl.rows.length;
            for (var i = lastRow; i > 1; i--)
            {
                  tbl.deleteRow(i-1);
            }
            var sel = document.getElementById('filterChainElement1');
            dwr.util.removeAllOptions(sel);
            dwr.util.addOptions (sel,map);
      }
 
      function loadAssociatedAttribute()
      {
            var tbl = document.getElementById('childClassTable');
            var lastRow = tbl.rows.length;
            var selectElement = document.getElementById('filterChainElement' + (lastRow));
            var className = selectElement[selectElement.selectedIndex].innerHTML;
            dwr.engine.setErrorHandler(displayErrorMessage);
            InstanceLevelHelper.getAssociatedAttributes(className, loadAssociatedAttributeData);
      }
      
      function loadAssociatedAttributeData(map)
      {
            var sel = document.getElementById('targetClassAttributeNameList');
            dwr.util.removeAllOptions(sel);
            dwr.util.addOptions (sel,map);
      }
      
function skipNavigation()
{
	document.getElementById("ilDetail").focus();
	window.location.hash="ilDetail";
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



<bean:define id="submitValue" value="error" />
<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
		<bean:define id="submitValue" value="create" />
	</logic:equal>
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="search" />
	</logic:equal>
</logic:equal>
<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
		<bean:define id="submitValue" value="loadAdd" />
	</logic:equal>
	<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
		<bean:define id="submitValue" value="loadSearchResult" />
	</logic:equal>
</logic:notEqual>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:form styleId="InstanceLevelForm" action="/InstanceLevelOperation">
	<html:hidden property="operation" value="<%=submitValue%>"/>
	<html:hidden property="userLoginName" value="<%=submitValue%>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Instance level details" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td class="infoMessage" colspan="3">
			  				<html:messages id="message" message="true">
			  				<bean:write name="message"/>
			  				</html:messages>	
			  				</td>
						</tr>
						<tr>
							<td colspan="3">
							<html:errors />
							</td>
						</tr>
						<tr>
						<logic:present name="<%=DisplayConstants.CURRENT_FORM%>">
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="ilDetail"></a>Enter the details to add a new Filter Clause to be added. Note that you can
									add multiple filter clause for a given class. 
									The <b>Class Name</b> this the class for which you want to create a filter clause. 
									The <b>Filter Chain</b> is a chain of the associated objects on which the security of the class depends upon. In
									case of the inherited security you can follow the trail to the target class by selecting the associated class and 
									pressing the  <b>Add</b> button. You can remove the last associated class by pressing <b>Remove</b> button.
									If the security of the Class is dependant on it own self, then you can select the same Class (with the suffix self) 
									in the Filter Chain. Once you have done selecting the filter chain, you can press <b>Done</b> to indicate that. It populates the 
									<b>Target Class Attribute Name</b> field with the all the attributes of the Final Target Class. Alternatively if you want to provide
									an alias for the Target Class Name then you can do so by providing a value for the <b>Target Class Alias</b> field. Same way you
									can provide an alias for the Target Class Attribute Name by providing a value for the <b>Target Class Attribute Alias</b> field.
									</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">* indicates a required field</td>
								</tr>
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
								<tr>
									<td class="formMessage" colspan="3">Search for an existing Instance level filter by entering the <b>Filter Class Name</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<tr>
									<td class="formMessage" colspan="3"><a id="ilDetail"></a>Update the details of the Filter Clause. Note that all fields except the <b>Generated SQL</b> 
									are non editable.
									The <b>Class Name</b> is the class for which the filter clause is created. 
									The <b>Filter Chain</b> shows the chain of classes on which the security for the current class depends upon. 
									The <b>Target Class Name</b> is the final class in the chain on whose's attribute value the security for the current class depends. 
									The <b>Target Class Attribute Name</b> is an attribute of the target class on which the security for the current class depends. 
									The <b>Target Class Attribute Type</b> is the Java type of the attribute of the target class. 
									The <b>Target Class Alias</b> is the alias name to be used for the target class. 
									The <b>Target Class Attribute Alias</b> is the alias name to be used for the target class's attribute. 
									The <b>Generated SQL</b> is the only editable field on this page. It is the filter SQL that is generated by Hibernate based on filter 
									criteria selected above by the user. <b>NOTE:</b> Once you edit the SQL there is no way it can be regenerated without deleting and creating 
									the filter clause again. Also, make sure you follow the Hibernate Filter SQL specifications and have a valid working filtering SQL.								
									</td>
								</tr>							
							</logic:notEqual>
						</tr>
						<tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
									<td class="formTitle" height="20" colspan="3">ENTER THE NEW FILTER CLAUSE DETAILS</td>
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<td class="formTitle" height="20" colspan="3">ENTER THE FILTER CLAUSE SEARCH CRITERIA</td>
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
									<td class="formTitle" height="20" colspan="3">FILTER CLAUSE DETAILS</td>
							</logic:notEqual>
						</tr>
							<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
									<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="searchFormElements" id="formElements" />
								</logic:equal>
								<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
									<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="addFormElements" id="formElements" />
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
								<bean:define name="<%=DisplayConstants.CURRENT_FORM%>" property="displayFormElements" id="formElements" />
							</logic:notEqual>
							<logic:iterate name="formElements" id="formElement" type="FormElement">
								<tr>
									<logic:equal name="formElement" property="propertyRequired" value="<%=DisplayConstants.REQUIRED%>">
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyLabel" /></label></td>
									</logic:equal>
									<logic:notEqual name="formElement" property="propertyRequired" value="<%=DisplayConstants.REQUIRED%>">
										<td class="formRequiredNotice" width="5">&nbsp;</td>
										<td class="formLabel"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyLabel" /></label></td>
									</logic:notEqual>
									<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_BOX%>">
										<logic:equal name="formElement" property="propertyReadonly" value="<%=DisplayConstants.READONLY%>">
											<html:hidden property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>"/>
											<td class="formField"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />&nbsp;</label></td>
										</logic:equal>
										<logic:notEqual name="formElement" property="propertyReadonly"  value="<%=DisplayConstants.READONLY%>">
											<td class="formField"><html:text style="formFieldSized" size="60" maxlength="100" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/></td>
										</logic:notEqual>
									</logic:equal>
									<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_COMBOBOX%>">
										<logic:equal name="formElement" property="propertyReadonly" value="<%=DisplayConstants.READONLY%>">
											<html:hidden property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>"/>
											<td class="formField"><label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />&nbsp;</label></td>
										</logic:equal>
										<logic:notEqual name="formElement" property="propertyReadonly"  value="<%=DisplayConstants.READONLY%>">
											<logic:equal name="formElement" property="propertyName" value="className">
												<td class="formField">
													<html:select style="formFieldSized" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>" onclick="loadFirstChildList();" onchange="loadFirstChildList();" onfocus="loadFirstChildList();">
														<html:options collection="classNames" property="value" labelProperty="label" />
													</html:select>
												</td>
											</logic:equal>
											<logic:equal name="formElement" property="propertyName" value="filterChain">
												<input type="hidden" id="filterChain" name="filterChain" />
												<input type="hidden" id="targetClassName" name="targetClassName" />
												<td class="formField">
													<table id="childClassTable" name="childClassTable" cellpadding="0" cellspacing="0" border="0" width="100%" align="center" >
														<tr>
															<td>
															<html:select styleId="filterChainElement1" style="formFieldSized" property="filterChainElement1" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>">
															</html:select>
															</td>
														</tr>
													</table>
													<input type="button" value="Add" onclick="addRow();" />
													<input type="button" value="Remove" onclick="removeRow();" />
													<input type="button" value="Done" onclick="loadAssociatedAttribute();" />
												</td>
											</logic:equal>
											<logic:equal name="formElement" property="propertyName" value="targetClassAttributeName">
												<input type="hidden" id="targetClassAttributeName" name="targetClassAttributeName" />
												<input type="hidden" id="targetClassAttributeType" name="targetClassAttributeType" />
												<td class="formField">
													<html:select styleId='<%=formElement.getPropertyName() + "List"%>' style="formFieldSized" property='<%=formElement.getPropertyName() + "List"%>' value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>">
													</html:select>
												</td>
											</logic:equal>
										</logic:notEqual>
									</logic:equal>
									<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_DATE%>">
										<td class="formField">
										<logic:equal name="formElement" property="propertyReadonly" value="<%=DisplayConstants.READONLY%>">
											<label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />   <%=DisplayConstants.DISPLAY_DATE_FORMAT%></label>
										</logic:equal>
										<logic:notEqual name="formElement" property="propertyReadonly"  value="<%=DisplayConstants.READONLY%>">
											<% if(formElement.getPropertyDisabled()){ %>
												<label for="<%=formElement.getPropertyName()%>"><bean:write name="formElement" property="propertyValue" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DisplayConstants.DISPLAY_DATE_FORMAT%></label>
											<% }else{ %>
											<html:text  style="formFieldSized" size="10" maxlength="10" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>"/>  <%=DisplayConstants.DISPLAY_DATE_FORMAT%>
											<% } %>											
										</logic:notEqual>
										</td>
									</logic:equal>
									<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_TEXTAREA%>">
										<td class="formField"><html:textarea style="formFieldSized" cols="70" rows="5" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=formElement.getPropertyValue()%>" disabled="<%=formElement.getPropertyDisabled()%>" /></td>
									</logic:equal>
									<logic:equal name="formElement" property="propertyType" value="<%=DisplayConstants.INPUT_RADIO%>">
										<td class="formField"><html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.YES%>" />&nbsp;Yes&nbsp;&nbsp;<html:radio style="formFieldSized" styleId="<%=formElement.getPropertyName()%>" property="<%=formElement.getPropertyName()%>" value="<%=DisplayConstants.NO%>" />&nbsp;No</td>
									</logic:equal>
								</tr>
							</logic:iterate>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<logic:equal name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
										
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
										<td><html:submit style="actionButton" onclick="setAndSubmit('create');">Add</html:submit></td>
										</logic:equal>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('search');">Search</html:submit></td>
										</logic:equal>
										<td><html:submit style="actionButton" onclick="setAndSubmit('loadAdd');">Reset</html:submit></td>
										<td><html:submit style="actionButton" onclick="setAndSubmit('loadHome');">Back</html:submit></td>
									</logic:equal>
									<logic:notEqual name="<%=DisplayConstants.CURRENT_FORM%>" property="primaryId" value="<%=DisplayConstants.BLANK%>">
										
										<td><html:submit style="actionButton" onclick="setAndSubmit('update');">Update</html:submit></td>
										<td><button class="actionButton" onclick="setAndSubmit('delete');">Delete</button></td>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.ADD%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('loadAdd');">Back</html:submit></td>
										</logic:equal>
										<logic:equal name="<%=DisplayConstants.CURRENT_ACTION%>" value="<%=DisplayConstants.SEARCH%>">
											<td><html:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');">Back</html:submit></td>
										</logic:equal>
									</logic:notEqual>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</tr>
						</logic:present>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</html:form>
	</table>

