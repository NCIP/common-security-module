<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/WEB-INF/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="gov.nih.nci.security.authorization.domainobjects.*"%>

    <script> 
    <!--
    
   		//Anzen Comment(Added By Vijay) - Popup window for User Search and close the popup Windw (Code Start)
   		
	    function closepopup()
		{
			newwin = window.open("about:blank", "UserSearchWin");
			if(false == newwin.closed)
			{
				newwin.close();
			}
		}
    
    	function opennewwin(appContext)
    	{
    		
    		newwin = window.open("about:blank", "UserSearchWin", "left=100,top=190,scrollbars=1,width=790,height=400");
    		newwin.document.open();
    		newwin.document.writeln('<form name="UserForm" method="post" action="/'+appContext+'/SearchUserDBOperation.action" id="UserForm">');
    		newwin.document.writeln('<input type="hidden" name="operation" value="error">');
    		newwin.document.writeln('<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>">');
    		newwin.document.writeln('</form>');
    		newwin.document.close();
   			newwin.document.UserForm.operation.value='loadSearch';
    		newwin.document.UserForm.submit();
    	}
    	
		//Anzen Comment(Added By Vijay) - Code End
    	
    	function setAndSubmit(target)
    	{
    			if (target == "read")
    			{
	    			document.applicationForm.operation.value=target;
	    			document.applicationForm.submit();
    			}
    			else
    			{		
	    			var len = document.applicationForm.associatedIds.length;
	    			for (i=0 ; i < len ; i++)
	    			{
	    				document.applicationForm.associatedIds[i].selected = true;
	    			}
	    			
	    			document.applicationForm.operation.value=target;
	    			document.applicationForm.submit();
				}
	    }

	function selSwitch(btn)
	{
		   var i= btnType = 0;
		   var isavailableIds = doIt = false;
		
		   if (btn.value == "Assign" || btn.value == "Deassign") 
		      btnType = 1;
		   else if (btn.value == "Assign All" || btn.value == "Deassign All") 
		      btnType = 2;
		   else
		      btnType = 3;
		
	      isavailableIds = (btn.value.indexOf('Assign') != -1) ? true : false;     
	      with ( ((isavailableIds)? document.dummyForm.availableIds: document.applicationForm.associatedIds) )
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
	                     document.applicationForm.applicationForm.associatedIds.options[document.applicationForm.associatedIds.length] = new Option( text, value );
	                  else
	                     document.dummyForm.availableIds.options[document.dummyForm.availableIds.length] = new Option( text, value );
	               } 
	               options[i] = null;
	               i--;
	            } 
	         } // end for loop
	         if (options[0] != null)
	            options[0].selected = true;
	      } // end with isavailableIds
		}   
		
function skipNavigation()
{
	document.getElementById("appAssoc").focus();
	window.location.hash="appAssoc";
	document.getElementById("ncilink").tabIndex = -1;
	document.getElementById("nihlink").tabIndex = -1;
	document.getElementById("skipmenu").tabIndex = -1;
	
	if(document.getElementById("homeLink"))
		document.getElementById("homeLink").tabIndex = -1;
	if(document.getElementById("adminhomeLink"))
		document.getElementById("adminhomeLink").tabIndex = -1;
		
	document.getElementById("saHome").tabIndex = -1;
	document.getElementById("saApp").tabIndex = -1;
	document.getElementById("saUser").tabIndex = -1;
	document.getElementById("saPriv").tabIndex = -1;
	document.getElementById("saLogout").tabIndex = -1;
}
		// -->
    </script>
<body onUnload="closepopup();">
<table cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top" width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" class="contentBegins">
			<tr>
				<td colspan="3">
					<h2><a id="appAssoc"></a>Application And Admin Association</h2>
				</td>
			</tr>
			<s:set var="applicationForm" value="#session.CURRENT_FORM"/>
			<s:if test='#applicationForm.applicationName != ""'>
			<tr>
				<td>
					<table cellpadding="3" cellspacing="0" border="0" width="90%" align="center">
						<tr>
							<td class="formTitle" height="20" colspan="2">SELECTED APPLICATION</td>
						</tr>
						<tr class="dataRowDark">
							<td class="formRequiredLabel" width="40%" scope="row"><label for="applicationName">Application Name</label></td>
							<td class="formField" width="60%"><s:property value="#applicationForm.applicationName"/></td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td valign="top" align="center" width="80%"><!-- sidebar begins -->
				<table cellpadding="3" cellspacing="10" border="0" height="100%" width="100%">
					<tr>
						<td class="infoMessage">
							<s:actionerror cssClass="error"/>
							<s:actionmessage cssClass="message"/>
							<s:fielderror  cssClass="error"/>
		  				</td>
					</tr>
					<tr>
						<td  align="center" class="formMessage" colspan="3">Assign or Deassign multiple <b>Admins</b> 
						for the selected <b>Application</b>. To remove the complete association Deassign all the <b>Admins</b>.</td>
					</tr>
					
					<tr>
					<td>
					<%
						Collection aSet1 = (Collection)request.getAttribute("AVAILABLE_SET");
						Map avSetList = new HashMap();
						if(aSet1 != null)
						{
							Iterator iter = aSet1.iterator();
							while(iter.hasNext())
							{
								User user = (User) iter.next();
								avSetList.put(user.getUserId(), user.getLoginName());
							}
						}

						Map asSetList = new HashMap();
						Collection aSet2 = (Collection)request.getAttribute("ASSIGNED_SET");
						if(aSet2 != null)
						{
							Iterator iter = aSet2.iterator();
							while(iter.hasNext())
							{
								User user = (User) iter.next();
								asSetList.put(user.getUserId(), user.getLoginName());
							}
						}
					%>
					
					<form name="dummyForm">
						<select name="availableIds"  style="width:0;" size="0">
							<s:iterator value="#request.AVAILABLE_SET" var="user">
							</s:iterator>
	                    			</select>
					</form>
					</td>
					<!-- big table starts -->
					<td width="100%">
					<table width="100%">
					<!-- ROW 1 begins -->
					<tr>

					<td width="0%" valign="top">
					
					</td>
	
					<!-- transition to ROW 2 -->
					</tr>
					<tr>							
					
					<td align="center" width="100%">
					<table width="220">
					<tr>
					<td align="center">


		
					</table>	
					</td>
					
					<!-- transition to ROW 3 -->
					</tr>
					<tr>		
					
					<td width="100%" valign="top">
					<s:form name="applicationForm" action="ApplicationDBOperation" theme="simple">
					<s:hidden name="operation" value="read"/>
					<s:set var="applicationId" value="#applicationForm.getApplicationId()"/>
					<s:hidden name="applicationForm.applicationId" value="%{applicationId}"/>
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='/ApplicationDBOperation'/>"/>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
						<tr>

							<td class="sidebarTitle" height="20">ASSIGNED ADMINISTRATORS</td>
						</tr>
						<tr>
						<td class="formField" align="center">
						<select name="associatedIds" multiple style="width:100%;" size="6">
						<s:iterator value="%{#request.ASSIGNED_SET}" var="user">
							<option value='<s:property value="#user.userId"/>'><s:property value="#user.loginName"/></option>
						</s:iterator>
						
	                    			</select>
	                    			</td>
						</tr>
					</table>
					</td>
					
					
					</tr>
					<!-- finish up changes -->
					<!-- add bottom row -->
					
					<tr>
				<td align="right" class="actionSection"><!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr>
					
						<td align="center">
						<script>

							var tempURL = window.location+"";			


							var url_array= tempURL.split("/");
							var contextTemp = url_array[3]+"";
							var temp = contextTemp.toLowerCase();

							document.write('<input type="button" value="Assign Admin" style="width:92px;" onclick="closepopup();opennewwin(contextTemp);">');

						
						</script>
						
						</td>
						
						
						<td align="center">
						<input type="button" value="Deassign" style="width:75px;" onclick="selSwitch(this);"></td>
						<td><s:submit class="actionButton" onclick="setAndSubmit('setAssociation');" value="Update Association"/></td>
						<td><s:submit style="actionButton" onclick="setAndSubmit('read');" value="Back"/></td>
					</tr>
				</table>
				</td>				
			</tr>
					
					<!--close up big table-->
					</table>
					</td>
					
					
					
					</tr>
				</table>
			</tr>
			
			</s:form>
		</table>
		</td>
	</tr>
</table>
</body>

