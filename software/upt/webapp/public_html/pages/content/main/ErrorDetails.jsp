<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ page import="gov.nih.nci.security.upt.constants.*"%>
<%@ page import="gov.nih.nci.security.upt.forms.*"%>

<br>
  <table width="700" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr valign="middle">

      <TD>
      <h1 align="center"></h1>
      </td>
    </tr>

    <tr>
      <td>
        <table width="701" border="0" cellspacing="0" cellpadding="0">
        <tr>
           <td>&nbsp;</td>
       </tr>
        <tr>
           <td>&nbsp;</td>
       </tr>
       <tr>
          <td align="center">
 
            <P>
              <s:property value="exception.message"/>
            </P>
            <P>
              <s:property value="exception.stackTrace"/>
            </P>
<h3>Error Message</h3>
<s:actionerror/>
<p>
    <s:property value="%{exception.message}"/>
</p>
<hr/>
<h3>Technical Details</h3>
<p>
    <s:property value="%{exceptionStack}"/>
</p>
</td>
       </tr>
  		<tr>
  		
  		<!-- news2 separate links depending on admin or super admin -->
							
			<s:if test="#session.ADMIN_USER != null">
				<td align="center"><s:url action="AdminHome" var="AdminHome"/><s:a href="%{AdminHome}">Click here to go back to Home Page</s:a></td>
			</s:if>
			<s:else>
				<td align="center"><s:url action="Home" var="Home"/><s:a href="%{Home}">Click here to go back to Home Page</s:a></td>
			</s:else>
			
								
							<!-- end home links -->		   
       </tr>

  </table>
