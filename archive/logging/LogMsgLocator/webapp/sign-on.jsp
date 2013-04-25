<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.apache.struts.action.*" %>

<html:form method="post" action="/sign-on">


<HTML><HEAD><TITLE>Sign In </TITLE></HEAD>
<BODY>
<TABLE width="100%" border="0" spacing="5">
  <TBODY>
  <TR>
    <TD colSpan=2>
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
      border=0>
        <TBODY>

        <tr>
            <td align=center colspan="2" class="formLabelwhite" bgColor=#0033cc > <strong><font color="#FFFFFF" size="2" face="Arial, Helvetica, sans-serif">Log 
              Message Locator SignOn</font></strong> </td>
        </tr>
          </TBODY></TABLE></TD></TR>
  <TR>
   <TD vAlign=top align=middle width="100%" class="errors">
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
<!--<html:errors property="org.apache.struts.action.GLOBAL_ERROR"/>-->
 </TD></TR>
  <TR>
    <TD  colSpan=2><TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
        <TBODY>
        <TR>
          <TD colSpan=2>
          
            
            <TABLE cellSpacing=2 cellPadding=2 width=450 align=center
              border=0>
                <TBODY>
              <TR>
                <TD colSpan=2>
                  <!-- Check and display the existence of any messages specifically errors -->

		               
                </TD></TR>
              <TR>
                <TD>
                  <DIV align=right> 
                        <P class=normalBold><strong><font size="2" face="Arial, Helvetica, sans-serif"><bean:message key="signin.name"/></font></strong></P>
                      </DIV></TD>
                <TD><html:text property="username" maxlength="20" size="18"/></TD></TR>
                
                <TR>
		                <TD>
		                  <DIV align=right> 
                        <P class=normalBold><strong><font color="#000000" size="2" face="Arial, Helvetica, sans-serif"><bean:message key="signin.password"/></font></strong></P>
                      </DIV></TD>
		                <TD><html:password property="password" maxlength="20" size="18"/></TD></TR>
              
                  
                  <TR>
		                  <TD colSpan=2>
		                    <CENTER>
		                    	<html:submit><bean:message key="label.login"/></html:submit>
		                      <html:cancel><bean:message key="label.cancel"/></html:cancel>
		                    </CENTER></TD></TR></TBODY></TABLE>
                  
                
                
                
                </TD></TR>
  <TR>
            <TD align=middle vAlign=center bgcolor="#0033CC" class=copyright>&nbsp;</TD>
          </TR></TBODY></TABLE></BODY></HTML>
         
       </html:form>