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
			<TD  height="100%" colspan="3" ><tiles:insert attribute="header"/></TD>
		</TR>
		<TR>
			
    <TD width="175" height="100%" valign="top" class="menu" ><p>&nbsp;</p><tiles:insert attribute="menu"/> 
    </TD>
			<TD width="80%" valign="top" align="left">
				<tiles:insert attribute="body"/>
			</TD>
		</TR>

	</TABLE>
	</BODY>
</HTML>