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
  		document.searchResultForm.operation.value=target;
 	}
// -->
</script>

<html:form styleId="searchResultForm"
	action="<%="/ApplicationDBOperation"%>">
	<html:hidden property="operation" value="error" />
	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="100%" height="100%">
		<tr>
			<td>
			<h2>Application</h2>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0" border="0"
				class="contentBegins">
				<tr>
					<td>
					<table summary="" cellpadding="0" cellspacing="0" border="0"
						width="600">
						<tr>
							<td class="dataTablePrimaryLabel" height="20">SEARCH RESULTS</td>
						</tr>
						<!-- paging begins -->
						<logic:present name="<%=DisplayConstants.SEARCH_RESULT%>">
							<bean:define name="<%=DisplayConstants.SEARCH_RESULT%>"
								property="searchResultObjects" id="searchResultObjects" />
							<bean:define id="oddRow" value="true" />
							<tr>
								<td align="right" class="dataPagingSection" height="20">
								<table cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td class="dataPagingText" align="right"><a
											class="dataPagingLink"
											href="javascript: setAndSubmit('next')">&lt;&lt; Previous</a></td>
										<td class="dataPagingText" align="center">| 1 - 5 of 10 |</td>
										<td class="dataPagingText" align="left"><a
											class="dataPagingLink"
											href="javascript: setAndSubmit('next')">Next &gt;&gt;</a></td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- paging ends -->

							<tr>
								<td>
								<table summary="Enter summary of data here" cellpadding="3"
									cellspacing="0" border="0" class="dataTable" width="100%">
									<tr>
										<th class="dataTableHeader" scope="col" align="center"
											width="9%">Select</th>
										<th class="dataTableHeader" scope="col" align="center"
											width="33%">Application Name</th>
										<th class="dataTableHeader" scope="col" align="center"
											colspan="3" width="58%">Application Description</th>
									</tr>
									<logic:iterate name="searchResultObjects"
										id="searchResultObject" type="Application">
										<%if (oddRow.equals("true")) { oddRow = "false";%>
											<tr class="dataRowLight">
												<td class="dataCellNumerical" width="9%"><html:radio
													style="formFieldSized" property="applicationId"
													value="<%=searchResultObject.getApplicationId().toString()%>" /></td>
												<td class="dataCellText" width="33%"><bean:write
													name="searchResultObject" property="applicationName" /></td>
												<td class="dataCellText" colspan="3" width="58%"><bean:write
													name="searchResultObject" property="applicationDescription" /></td>
											</tr>
										<%}else{ oddRow = "true";%>
											<tr class="dataRowDark">
												<td class="dataCellNumerical" width="9%"><html:radio
													style="formFieldSized" property="applicationId"
													value="<%=searchResultObject.getApplicationId().toString()%>" /></td>
												<td class="dataCellText" width="33%"><bean:write
													name="searchResultObject" property="applicationName" /></td>
												<td class="dataCellText" colspan="3" width="58%"><bean:write
													name="searchResultObject" property="applicationDescription" /></td>
											</tr>
										<%}%>
									</logic:iterate>
								</table>
								</td>
							</tr>
							<tr>
								<td align="right" class="actionSection"><!-- action buttons begins -->
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('loadSearch');">Back</html:submit></td>
										<td><html:submit style="actionButton"
											onclick="setAndSubmit('read');">View Details</html:submit></td>
									</tr>
								</table>
								<!-- action buttons end --></td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>










