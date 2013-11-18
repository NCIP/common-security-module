<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="java.util.*"%>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.GroupForm.operation.value=target;
  		document.GroupForm.submit();
 	}
 	
function skipNavigation()
{
	document.getElementById("groupAssoc").focus();
	window.location.hash="groupAssoc";
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


	<table cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		
		<s:form name="GroupForm" action="GroupDBOperation" theme="simple">
	<s:hidden name="operation" value="read" />
	<s:set var="groupForm" value="#session.CURRENT_FORM"/>
	<s:set var="groupId" value="#groupForm.getGroupId()"/>
	<s:hidden name="groupForm.groupId" value="%{groupId}"/>
	
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/GroupDBOperation'/>"/>
		
		<tr>
			<td>
			<h2><a id="groupAssoc"></a>Group, Protection Group and Roles</h2>
			</td>
		</tr>
		<tr>
			<td valign="top" width="100%">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				width="100%" class="contentBegins">
				<s:if test='!groupForm.groupName.equals("")'>
				<tr>
					<td>
						<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
							<tr>
								<td class="formTitle" height="20" colspan="2">SELECTED GROUP</td>
							</tr>
							<tr class="dataRowDark">
								<td class="formRequiredLabel" width="40%" scope="row"><label for="groupName">Group Name</label></td>
								<td class="formField" width="60%"><s:property value="#groupForm.groupName"/></td>
							</tr>
						</table>
					</td>
				</tr>
				</s:if>
				<tr>
					<td>
					<table cellpadding="0" cellspacing="0" border="0"
						width="100%">
						<tr>
							<td class="errorMessage" colspan="3">
							<s:if test="hasActionErrors()">
							      <s:actionerror/>
							</s:if>
							</td>
						</tr>
						<tr><td><br></td></tr>
						<tr>
							<td class="formMessage" colspan="3">Select the <b>Protection Group</b> association which to be removed for
							 the selected <b>Group</b> or whose <b>Roles</b> Association needs to be updated.</td>
						</tr>
						<tr>
							<td class="dataTablePrimaryLabel" height="20">SEARCH RESULTS</td>
						</tr>
						<!-- paging begins -->
						<s:if test="#session.AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET != null">
							<s:set var="oddRow" value="true"/>
							<!-- paging ends -->
							<tr>
								<td>
								<table summary="Assign Group, Protection Group, Role" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="10%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="45%">Associated Protection Group Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="45%">Associated Role Name</th>
									</tr>
									<s:iterator value="#session.AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET" var="protectionGroupRoleContext">
									<s:set var="protectionGroup" value="#protectionGroupRoleContext.getProtectionGroup()"/>
										<s:if test='oddRow.equals("true")'>
											<s:set var="oddRow" value="false"/>
											<tr class="dataRowLight">
										</s:if>
										<s:else>
											<s:set var="oddRow" value="true"/>
											<tr class="dataRowDark">
										</s:else>
												<td class="dataCellNumerical" width="10%">
												<s:radio
													name="groupForm.protectionGroupAssociatedId"  list="#{#protectionGroup.getProtectionGroupId().toString():#protectionGroup.getProtectionGroupId().toString()}"/>
												</td>
												<td class="dataCellText" width="45%"><s:property value="#protectionGroupRoleContext.protectionGroup.protectionGroupName"/></td>
												<td class="dataCellText" width="45%">
												<s:iterator value="#protectionGroupRoleContext.getRoles()" var="role">
													<s:property value="#role.getName()"/>,&nbsp;
												</s:iterator>
												</td>
											</tr>
									</s:iterator>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>

										<td><s:submit style="actionButton"
											onclick="setAndSubmit('removeProtectionGroupAssociation');" value="Remove PG & Roles"/></td>											
										<td><s:submit style="actionButton"
											onclick="setAndSubmit('loadRoleAssociation');" value="Associated Roles"/></td>
										<td><s:submit style="actionButton"
											onclick="setAndSubmit('read');" value="Back"/></td>											
									</tr>
								</table>
								<!-- action buttons end --></td>
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
