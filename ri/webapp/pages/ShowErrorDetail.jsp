<%@ include file= "/pages/imports.jsp" %>

<br>
  <table width="700" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr valign="middle">

      <TD>
      <h1 align="center"><img src="pages/images/dilbert.gif"></h1>
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
               <bean:write name='<%= Constants.ERROR_DETAIL %>' />
            </P>
          </td>
       </tr>
  		<tr>
           <td>&nbsp;</td>
       </tr>

  </table>
