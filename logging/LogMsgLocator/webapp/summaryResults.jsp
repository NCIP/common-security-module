<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="gov.grants.locator.Constants" %>

<!-- Display Search Criteria -->
<br>

    <!-- Check and display the existence of any messages specifically errors -->
    <logic:messagesPresent>
       <span class="error" >
         <bean:message key="errors.title" />
         <bean:message key="errors.header"/>
         <ul>
          <html:messages id="error">
          <li><bean:write name="error" filter="false" /></li>
         </html:messages>
          </ul><hr>
        </span>
   </logic:messagesPresent>

   <logic:messagesNotPresent>

      <bean:define id="summary" name='<%= Constants.SUMMARY_LIST %>'
         type="java.util.List" />
   </logic:messagesNotPresent>

  <br>
  <!-- Display the number of Summaries returned from the query here -->
  <table align="center" border="0" width="100%">
    <tr align="center" bgcolor="#0033CC">

      <td class="framesetHeader" ><bean:message key="summary.transCount" />
          <logic:present name="summary" >
            <bean:size name="summary" id="size"/>
              <%= " " + size.intValue() %>
          </logic:present>
          <logic:notPresent name="summary" >
              <%= " 0"  %>
          </logic:notPresent>
      </td>
    </tr>
  </table>


<table width="500" border="0" cellpadding="0" cellspacing="0" class="stestBlue">

    <logic:present name="summary">

      <br>

<% int i =0; %>
        <logic:iterate id="next" name="summary" type="gov.grants.locator.dao.Summary" >
         <%  String cellColor = i++ % 2 == 1 ? "#CCCCCC" : "#FFFFFF"; %>
         <tr align="center" bgcolor= <%= cellColor %> ><td>
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="2">


      
         <!-- Server Field -->
		     <td width="78" valign="top" class="stestBlue"><bean:message key='field.name'/></td>
            <td width="360" valign="top" class="stestBlack">
            
                <bean:write name="next" property="server" filter="false" />
           </td>
           <!-- Application Field -->
                <td width="70" valign="top" class="stestBlue"><bean:message key='field.application'/></td>
               <td width="309" valign="top" class="stestBlack">
                 <bean:write name="next" property="application" filter="false" />
               </td>
            <!-- Level Field --> 
                 <td width="37" valign="top" class="stestBlue"><bean:message key='field.level'/></td>
                 
                                  <bean:define id="logLevel" name="next" property="priority"/>
              	<%
              		String sytleClass="stestBlack";
              		
              		if ( logLevel.equals( "DEBUG" ) ){
              		    sytleClass="stestBlack";
              		} 
              		else if ( logLevel.equals( "INFO" ) ){
              		    sytleClass="stestGreen";
              		}
              		else if ( logLevel.equals( "WARN" ) ){
              			sytleClass="stestYellow";
              		}
              		else if ( logLevel.equals( "ERROR" ) ){
              			sytleClass="stestRed";
              		}
              		else if ( logLevel.equals( "FATAL" ) ){
              			sytleClass="stestRed";
              		}
              		
              	%>
                 <td width="193" valign="top" class='<%=sytleClass%>' >
                  

                
                <B> 
                 <bean:write name="next" property="priority" filter="false" />
                </B>  
             
                 </td>
			<!-- Time Field -->
			  <td width="36" valign="top" class="stestBlue"><bean:message key='field.time'/></td>
               <td width="130" valign="top" class="stestBlack">
                 <bean:write name="next" property="createdOn" filter="false" />
               </td>
               
               	<!-- Ndc Field -->
			  <td width="36" valign="top" class="stestBlue"><bean:message key='field.ndc'/></td>
               <td width="130" valign="top" class="stestBlack">
                 <bean:write name="next" property="ndc" filter="false" />
               </td>
          <tr>
               <td valign="top" class="stestBlue"><bean:message key='field.thread'/></td>
              <td colspan="9" valign="top" class="stestBlack">
                 <bean:write name="next" property="thread" filter="false" />
               </td>
             

         </tr>     
               
                    <!-- Message Field -->
      <tr>
               <td valign="top" class="stestBlue"><bean:message key='field.msg'/></td>
              <td colspan="9" valign="top" class="stestBlack">
                 <bean:write name="next" property="msgBody" filter="false" />
               </td>
             

         </tr>
            <!-- Throwable Field -->
      <tr>
               <td valign="top" class="stestBlue"><bean:message key='field.throwable'/></td>
              <td colspan="9" valign="top" class="stestBlack">
              	<bean:write name="next" property="throwable" filter="false" />
               </td>
             

         </tr></table></td></tr>
        </logic:iterate>

      </logic:present>

   </Table>

   <!--
        If there were no transactions to display then default
        to a standard no transactions found message
   -->
   <logic:notPresent name="summary">
        <br> <center> <b> <bean:message key="summary.notTransFound" /> </b> </center>
   </logic:notPresent>
