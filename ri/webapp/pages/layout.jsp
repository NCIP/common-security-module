<%@ include file= "/pages/imports.jsp" %>

<HTML>
	<HEAD>
		<TITLE><tiles:getAsString name="title"/></TITLE>

        <link rel="stylesheet" href="pages/styleSheet.css"/>
        <script src="pages/script.js" type="text/javascript"></script>
	</HEAD>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	
	
	
	<TABLE height="100%" width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
		<TR>
			<TD valign="top" colspan="2">
			<tiles:get name="header"/></TD>
		</TR>
		
		
		<TR>			
    <TD valign="top" width="175" align="left" height="100%"><tiles:get name="menu"/></TD>
    
		   
		
			<TD valign="top" align="left">
			
			 <logic:messagesPresent> 
				<UL>
				 <html:messages id="error"> 
				 <font color='red' face="Arial">
				 The following errors occurred:
				 <LI><bean:write name="error" filter="false"/></LI>
				 </font>
				 </html:messages> 
				</UL> 
				<BR>
				<BR>
				</logic:messagesPresent>
				<logic:messagesPresent message="true"> 
				<UL> 
				 <html:messages id="msg" message="true"> 
				  <font color='green' face="Arial">
				  <P ALIGN="CENTER"><bean:write name="msg" filter="false"/></P>
				  </font>
				 </html:messages>
				</UL>
				<BR>
				<BR>
			</logic:messagesPresent> 
			
				<tiles:get name="body"/>
			</TD>
		</TR>

	</TABLE>
	</BODY>
</HTML>