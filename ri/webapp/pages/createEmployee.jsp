<%@ include file= "/pages/imports.jsp" %>


<BR>

<P CLASS="header-s" ALIGN="center">Create Employee</P>


<html:form method="post" action="createEmployee">
  <TABLE WIDTH="60%" BORDER="0" ALIGN="Center" CELLPADDING="1" CELLSPACING="1">
    
   <TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	First Name
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="firstName"/> 
	</TD>
	<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	Last Name
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="lastName"/> 
	</TD>

	<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	Middle Name
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="middleName"/> 
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	Phone Number
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="phoneNumber"/> 
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	Salary
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="salary"/> 
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	SSN
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="ssn"/> 
	</TD>

	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:submit/>
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	Street Address
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="streetAddr"/> 
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	City
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="city"/> 
	</TD>
	
		<td>&nbsp;</td>
	</tr>
	<TR  CLASS="row">
   <td class="body-s" align="center" valign="middle">
   	State 
   </td>
	<TD CLASS="body-s" ALIGN="center" valign="middle">
		<html:text property="state"/> 
	</TD>
	
	</tr>
		
	<td>&nbsp;</td>
	</tr>
</TABLE>
</html:form> 
