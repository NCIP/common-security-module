<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.viewobjects.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

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
            document.InstanceLevelForm.submit();
      }
      
      function addRow()
      {
      alert("test");
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
            alert("sel "+sel);
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

<s:set var="submitValue" value="error" />
<s:set var="currentForm" value="#session.CURRENT_FORM"/>
<s:if test='#currentForm.getPrimaryId().equals("")'>
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="create" />
	</s:if>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="search" />
	</s:if>
</s:if>
<s:else>
	<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
		<s:set var="submitValue" value="loadAdd" />
	</s:if>
	<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
		<s:set var="submitValue" value="loadSearchResult" />
	</s:if>
</s:else>

	<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<s:form name="InstanceLevelForm" action="InstanceLevelOperation" theme="simple">
	<s:hidden name="operation" value="error"/>
	<s:hidden name="userLoginName" value=""/>
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>"/>
			<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="contentBegins">
				<tr>
					<td>
					<table summary="Instance level details" cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td class="infoMessage" colspan="3">
								<s:if test="hasActionMessages()">
								      <s:actionmessage/>
								</s:if>			  
			  				</td>
						</tr>
						<tr>
							<td class="errorMessage" colspan="3">
							<s:if test="hasActionErrors()">
							      <s:actionerror/>
							</s:if>
							</td>
						</tr>
						<tr>
					<s:if test="#session.CURRENT_FORM != null">
						<s:if test='#currentForm.getPrimaryId().equals("")'>
							<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
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
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
								<tr>
									<td class="formMessage" colspan="3">Search for an existing Instance level filter by entering the <b>Filter Class Name</b>.</td>
								</tr>
								<tr>
									<td class="formMessage" colspan="3">Use * to perform wildcard searches</td>
								</tr>
								</s:if>
							</s:if>
							<s:else>
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
							</s:else>
						</tr>
						<tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE NEW FILTER CLAUSE DETAILS</td>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<td class="formTitle" height="20" colspan="3">ENTER THE FILTER CLAUSE SEARCH CRITERIA</td>
								</s:if>
							</s:if>
							<s:else>
									<td class="formTitle" height="20" colspan="3">FILTER CLAUSE DETAILS</td>
							</s:else>
						</tr>
							<s:if test='#currentForm.getPrimaryId().equals("")'>
								<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
									<s:set var="formElements" value="#currentForm.searchFormElements"/>
								</s:if>
								<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
									<s:set var="formElements" value="#currentForm.addFormElements"/>
								</s:if>
							</s:if>
							<s:else>
								<s:set var="formElements" value="#currentForm.displayFormElements"/>
								<s:set var="instanceLevelId" value="#currentForm.getPrimaryId()"/>
								<s:hidden name="instanceLevelForm.id" value="%{instanceLevelId}"/>
							</s:else>
							<s:iterator value="formElements" var="formElement">
								<tr>
									<s:if test='(#formElement.propertyRequired.equals("REQUIRED"))'>
										<td class="formRequiredNotice" width="5">*</td>
										<td class="formRequiredLabel2"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
									</s:if>
									<s:else>
										<td class="formRequiredNotice" width="5">&nbsp;</td>
										<td class="formLabel"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/></label></td>
									</s:else>
								
									<s:if test='#formElement.propertyType.equals("INPUT_BOX")'>
										<s:if test='#formElement.propertyReadonly.equals("READONLY")'>
											<s:hidden name="%{propertyName}" value="%{propertyValue}"/>
											<td class="formField"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyValue"/>&nbsp;</label></td>
										</s:if>
										<s:else>
											<td class="formField">
											<s:textfield size="60" maxlength="100"  name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>
											</td>
										</s:else>
									</s:if>
									<s:if test='#formElement.propertyType.equals("INPUT_COMBOBOX")'>
										<s:if test="formElement.propertyReadonly.equals(DisplayConstants.READONLY)">
											<s:hidden name="%{propertyName}" value="%{propertyValue}"/>
											<td class="formField"><label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyValue"/>&nbsp;</label></td>
										</s:if>
										<s:else>
											<s:if test='#formElement.propertyName.equals("className")'>
												<td class="formField">
													<s:select style="formFieldSized" name="%{propertyName}" list="#session.classNames" disabled="%{propertyDisabled}" onclick="loadFirstChildList();" onchange="loadFirstChildList();" onfocus="loadFirstChildList();"/>
												</td>
											</s:if>
											<s:if test='#formElement.propertyName.equals("filterChain")'>
												<input type="hidden" id="filterChain" name="filterChain" />
												<input type="hidden" id="targetClassName" name="targetClassName" />
												<td class="formField">
													<table id="childClassTable" name="childClassTable" cellpadding="0" cellspacing="0" border="0" width="100%" align="center" >
														<tr>
															<td>
															<s:select style="formFieldSized" name="filterChainElement1" id="filterChainElement1" list="%{propertyValue}" disabled="%{propertyDisabled}">
															</s:select>
															</td>
														</tr>
													</table>
													<s:submit type="button" value="Add" onclick="addRow()" />
													<input type="button" value="Remove" onclick="removeRow()" />
													<input type="button" value="Done" onclick="loadAssociatedAttribute()" />
												</td>
											</s:if>
											<s:if test='#formElement.propertyName.equals("targetClassAttributeName")'>
											111
												<input type="hidden" id="targetClassAttributeName" name="targetClassAttributeName" />
												<input type="hidden" id="targetClassAttributeType" name="targetClassAttributeType" />
												<td class="formField">
													<s:select id='%{propertyName}List' style="formFieldSized" name='%{propertyName}List"%>' list="%{propertyValue}" disabled="%{propertyDisabled}">
													</s:select>
												</td>
											</s:if>
										</s:else>
									</s:if>
									<s:if test='(#formElement.propertyType.equals("INPUT_DATE"))'>
										<td class="formField">
										<s:if test='#formElement.propertyReadonly.equals("READONLY")'>
											<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyValue"/>(MM/DD/YYYY)</label>
										</s:if>
										<s:else>
											<s:if test='#formElement.propertyDisabled'>
												<label for="<s:property value="#formElement.propertyName"/>"><s:property value="#formElement.propertyLabel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MM/DD/YYYY)</label>
											</s:if>
											<s:else>
											<s:textfield size="10" maxlength="10"  name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}"/>  (MM/DD/YYYY)
											</s:else>										
										</s:else>
										</td>
									</s:if>
									<s:if test='#formElement.propertyType.equals("INPUT_TEXTAREA")'>
										<td class="formField"><s:textarea style="formFieldSized" cols="32" rows="2" name="%{propertyName}" value="%{propertyValue}" disabled="%{propertyDisabled}" /></td>
									</s:if>
									<s:if test='#formElement.propertyType.equals("INPUT_RADIO")'>
										<td class="formField"><s:radio name="%{propertyName}" list="#{'YES':'Yes','NO':'No'}" value="%{propertyValue}" /></td>
									</s:if>
								</tr>
							</s:iterator>
						<tr>
							<td align="right" colspan="3"><!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<s:if test='#currentForm.getPrimaryId().equals("")'>
										<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
										<td><s:submit style="actionButton" onclick="setAndSubmit('create');" value="Add"/></td>
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('search');" value="Search"/></td>
										</s:if>
										<td><s:submit style="actionButton" onclick="setAndSubmit('loadAdd');" value="Reset"/></td>
										<td><s:submit style="actionButton" onclick="setAndSubmit('loadHome');" value="Back"/></td>
									</s:if>
									<s:else>
										<s:if test="#session.UPDATE_UPT_INSTANCE_LEVEL_OPERATION == null">
											<td><s:submit disabled="true" value="Update"/></td>
										</s:if>
										<s:else>
											<td><s:submit style="actionButton" onclick="setAndSubmit('update');" value="Update"/></td>
										
										</s:else>
										<s:if test="#session.DELETE_UPT_INSTANCE_LEVEL_OPERATION == null">
											<td><s:submit disabled="true" value="Delete"/></td>
										</s:if>
										<s:else>
											<td><s:submit class="actionButton" onclick="setAndSubmit('delete');" value="Delete"/></td>	
										</s:else>
										<s:if test='#session.CURRENT_ACTION.equals("ADD")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadAdd');" value="Back"/></td>
										</s:if>
										<s:if test='#session.CURRENT_ACTION.equals("SEARCH")'>
											<td><s:submit style="actionButton" onclick="setAndSubmit('loadSearchResult');" value="Back"/></td>
										</s:if>
									</s:else>
								</tr>
							</table>
							</td><!-- action buttons end -->
						</tr>
						</s:if>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:form>
	</table>

