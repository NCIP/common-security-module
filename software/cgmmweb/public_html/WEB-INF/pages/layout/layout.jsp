<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<html:html locale="en">
<head>
<title><tiles:getAsString name="title" /></title>
<META name="author" content="Vijay Parmar">
<META name="keywords"
	content="CSM GAARDS Migration Module, NCICBIIT">
<META name="description"
	content="CSM GAARDS Migration Module enables existing CSM-authentication based web applications to migrate their users to login via GAARDS based Grid credentials.">

<base
	href='<%=(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/")%>' />
<link rel="stylesheet" href="style/styleSheet.css" type="text/css" />
<link rel="stylesheet" href="style/translocator.css" type="text/css" />
<script language="JavaScript" src="scripts/script.js"
	type="text/javascript"></script>
</head>
<body>
<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
  <!-- nci hdr begins -->
  <tiles:get name="nci_header" />
  <!-- nci hdr ends -->	
  <tr>
    <td height="100%" valign="top" align="center">
      <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
		<!-- application hdr begins -->
		<tiles:get name="app_header" />
		<!-- application hdr ends -->
        <tr>
          <td width="190" valign="top" class="subMenu">
          </td>
          <td valign="top" width="100%">
            <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" align="center">
              <tr>
                <td height="20" width="100%" class="mainMenu" >                
                  <!-- main menu begins -->
                  <tiles:get name="menu" />
                  <!-- main menu ends -->
                </td>
              </tr>
			<!--_____ main content begins _____-->
			              <tiles:get name="content"/>
			<!--_____ main content ends _____-->
            
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
 
</table>
</body>
</html:html>
