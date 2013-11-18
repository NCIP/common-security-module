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
<%@ page import="gov.nih.nci.security.constants.Constants"%>
<script>
<!--
   	function setAndSubmit(target)
   	{
  		document.UserForm.operation.value=target;
  		document.UserForm.submit();
 	}
 	
function skipNavigation()
{
	document.getElementById("userPGAssoc").focus();
	window.location.hash="userPGAssoc";
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
		
		<s:form name="UserForm"
	action="UserDBOperation" theme="simple">
	<s:set var="userForm" value="#session.CURRENT_FORM"/>
	<s:hidden name="operation" value="read" />
	<s:set var="userId" value="#userForm.getUserId()"/>
	<s:hidden name="userForm.userId" value="%{userId}"/>

	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/UserDBOperation'/>"/>
		
		<tr>
			<td>
			<h2><a id="userPGAssoc"></a>Associated Protection Groups and Roles</h2>
			</td>
		</tr>
		<tr>
			<td valign="top" width="100%">
			<table cellpadding="0" cellspacing="0" border="0"
				width="100%" class="contentBegins">
				<s:if test='#userForm.userLoginName != ""'>
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
							<td class="formMessage" colspan="3">
							To modify the <b>Role(s)</b> associated with a protection group, select the desired <b>Protection Group</b> 
							then click the Associated Roles button. To remove a <b>Protection Group</b>/<b>Role</b> association from this <b>User</b>, select
							the desired <b>Protection Group</b> and click the Remove PG & Roles button.</td>
						</tr>
						<tr>
							<td class="dataTablePrimaryLabel" height="20">SEARCH RESULTS</td>
						</tr>
						<!-- paging begins -->
						<s:set var="oddRow" value="true"/>

						<s:if test="#session.AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET != null">
							<!-- paging ends -->
							<tr>
								<td>
								<table summary="Assign Protection Group, User, Role" cellpadding="3"
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
													name="userForm.protectionGroupAssociatedId"  list="#{#protectionGroup.getProtectionGroupId().toString():#protectionGroup.getProtectionGroupId().toString()}"/>
												</td>
												<td class="dataCellText" width="45%"> 
												<s:property value="#protectionGroup.protectionGroupName"/>
												</td>
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
										<s:if test="#session.UPDATE_UPT_USER_OPERATION != null">
											<td><s:submit style="actionButton"
												onclick="setAndSubmit('removeProtectionGroupAssociation');" value="Remove PG & Roles"/></td>											
										</s:if>
										<s:if test="#session.UPDATE_UPT_USER_OPERATION == null">
\											<td><s:submit style="actionButton" disabled="true" value="Remove PG & Roles"/></td>
										</s:if>
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
