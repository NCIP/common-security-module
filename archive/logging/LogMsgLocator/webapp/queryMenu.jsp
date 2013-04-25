<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/server-list.tld" prefix="custom" %>

<html:form action="/executeQuery" focus="logLevel" >
     <script language="Javascript">
        function defaultFields() {
         document.queryForm.application.value = "";
         document.queryForm.server.value = "";
         document.queryForm.startDate.value = '<%= new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date()) %>';
         document.queryForm.startTime.value = "";
         document.queryForm.endDate.value = "";
         document.queryForm.endTime.value = "";
         document.queryForm.logLevel.value = "";
         document.queryForm.msgBody.value = "";
         document.queryForm.threadName.value = "";
         document.queryForm.maxRecordCount.value = "50";
         document.queryForm.isXmlView.checked  = false;
         document.queryForm.logLevel.focus();
         return true;
       }

     </script>
    <center>
       <table align="center"  width="100%" border="0" cellpadding="2">

        <tr>
          <td colspan="2" class="framesetHeader">
            Search Criteria:
          </td>
        </tr>
        <tr>
           <td>
             <html:image
				border="0"
    			src="images/TL_Submit.jpg"
          		value="submit"/>
           </td>
        </tr>
	<tr><td class="formLabelyellow">User: <%= (String) request.getSession().getAttribute("USERNAME") %></td>
	
         <tr>
          <td colspan="2" class="formLabelwhite">
              LogLevel:
          </td>
        </tr>
        <tr>
          <td colspan="2" class="displayValue">
              <html:select property="logLevel">
              	<html:option value="">All Levels</html:option>
              	<html:option value="DEBUG">DEBUG</html:option>
              	<html:option value="INFO">INFO</html:option>
              	<html:option value="WARN">WARN</html:option>
              	<html:option value="ERROR">ERROR</html:option>
              	<html:option value="FATAL">FATAL</html:option>
             </html:select>
       </td>
        </tr>
          <tr>
          <td colspan="2" class="formLabelwhite">
              Application:
          </td>
        </tr>
        <tr>
          <td colspan="2" class="displayValue">
             <html:select property="application">
              	<html:option value="">All Applications</html:option>
              	<html:option value="APPLY">APPLY</html:option>
              	<html:option value="WS">WS</html:option>
              	<html:option value="WEB">WEB</html:option>
            </html:select>
          </td>
        </tr>
        <tr>

          <td colspan="2" class="formLabelwhite">
              Server:
          </td>
        </tr>
         <td colspan="2" class="displayValue">
          <html:select property="server">
              	<custom:options />              	
         </html:select>
          </td>
           <tr>
          <td colspan="2" class="formLabelwhite">
            Message Contains:
          </td>
        </tr>

        <tr >
          <td valign="middle" colspan="2">
            <html:text property="msgBody" maxlength="200" size="18"/>
          </td>
          <tr >
        <tr>
          <td colspan="2" class="formLabelwhite">
            NDC Contains:
          </td>
        </tr>

         <tr>
          <td valign="middle" colspan="2">
            <html:text property="ndc" maxlength="200" size="18"/>
          </td>
         </tr>
        </tr>
          <tr>
          <td colspan="2" class="formLabelwhite">
            Thread Contains:
          </td>
        </tr>

        <tr >
          <td valign="middle" colspan="2">
            <html:text property="threadName" maxlength="200" size="18" />
          </td>

        </tr>

        <tr>
	      <td class="formLabelwhite" width="50%">
            Start Date:
            <br>(MM/DD/YYYY)
          </td>
          <td class="formLabelwhite" width="50%">
            Start Time:
            <br>(HH:MM AM|PM)
          </td>
        </tr>

         <tr>
        	<td>
        	  <logic:notEmpty name="queryForm" property="startDate">
        	   <html:text property="startDate" size="10" />
        	  </logic:notEmpty>
        	  <logic:empty name="queryForm" property="startDate">
        	   <html:text property="startDate" size="10"  maxlength="10"
        	   		value='<%= new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date()) %>' />
        	  </logic:empty>
           	</td>

           	<td>
        		<html:text property="startTime" size="10" maxlength="8" />
          	</td>
       </tr>

       <tr>
	      <td class="formLabelwhite" width="50%">
            End Date:
            <br>(MM/DD/YYYY)
          </td>
          <td class="formLabelwhite" width="50%">
            End Time:
            <br>(HH:MM AM|PM)
          </td>
        </tr>
       <tr>
	     <td>
		   <html:text property="endDate" size="10"  maxlength="10" />
	     </td>
	     <td>
		   <html:text property="endTime" size="10"  maxlength="8"/>
	     </td>
      </tr>
      <tr>
        <td colspan="2" class="formLabelwhite">
          Maximum number of Results:
        </td>
      </tr>
     <tr>
        <td colspan="2">
        <logic:notEmpty name="queryForm" property="maxRecordCount">
         <html:text property="maxRecordCount" size="18"  maxlength="4" />
        </logic:notEmpty>
        <logic:empty name="queryForm" property="maxRecordCount">
          <html:text property="maxRecordCount" value="50"  maxlength="4" size="18" />
        </logic:empty>
        </td>
      </tr>

      <tr>
        <td class="formLabelwhite">
          View as Log4J XML (Coming Soon)
          &nbsp;
        </td>
        <td>

          <!-- <html:checkbox property="isXmlView" /> -->
        </td>
      </tr>
      <tr>
          <td>
          <html:image
				border="0"
				onclick="javaScript:defaultFields()"
    			src="images/TL_Reset.jpg"/>
          </td>
          <td>
            <html:image
				border="0"
    			src="images/TL_Submit.jpg"
          		value="submit"/>
          </td>
      </tr>
    </table>
   </center>
</html:form>