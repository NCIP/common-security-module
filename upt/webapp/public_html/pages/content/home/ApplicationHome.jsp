<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
    <script>
    <!--
    	function setAndSubmit(target)
    	{
    		document.tableHomeForm.operation.value=target;
    		document.tableHomeForm.submit();
    	}
    // -->
    </script>

<html:form styleId="tableHomeForm" action = "/ApplicationDBOperation">
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<html:hidden property="operation" value="error"/>
	<tr>
		<td valign="top">
		<table cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">

				<h2>Application</h2>

				<h3>Application Home</h3>

				<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi ornare accumsan massa. Vivamus scelerisque, mauris vitae lacinia tincidunt, metus tellus ultrices arcu, at vestibulum lectus urna sit amet augue. Aliquam diam eros, tempor in, ultricies nec, rutrum nec, wisi. Curabitur sit amet magna eu ipsum hendrerit aliquam. Proin id purus nec nisl porttitor ornare. Integer sagittis. Cras vestibulum rhoncus dui. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce ut urna non libero iaculis volutpat. Mauris pellentesque vulputate lectus. Morbi felis justo, dapibus quis, vulputate eget, mollis non, lorem. Donec sed lacus in velit tempus imperdiet. Pellentesque eu odio sit amet dui sollicitudin posuere. Donec molestie, magna eget volutpat facilisis, wisi metus semper wisi, eu consequat wisi massa quis lorem. Nulla eu ipsum. Mauris porta molestie massa. In aliquam. Sed et odio. Sed sed massa quis neque consectetuer facilisis. Proin facilisis enim quis augue.</p>
				</td>
			</tr>
			<tr>
				<td valign="top" width="40%"><!-- sidebar begins -->
				<table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
					<tr>
						<td>
		  				<html:messages id="message" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
		  				<li><bean:write name="message"/></li>
		  				</html:messages>				
		  				</td>
					</tr>					
					<tr>
					<td valign="top">
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">APPLICATION LINKS</td>
						</tr>
						<tr>
							<td class="sidebarContent"><a href="javascript: setAndSubmit('loadAdd')">Create a New Application</a><br>
							Click to add a new application.</td>
						</tr>
						<tr>
							<td class="sidebarContent"><a href="javascript: setAndSubmit('loadSearch')">Select an Existing Application</a><br>
							Enter search criteria to find the application you wish to operate on.</td>
						</tr>
					</table>
					</td>
					</tr>
				</table>
				<td width="60%"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</html:form>


