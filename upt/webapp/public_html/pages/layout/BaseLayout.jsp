<%@ taglib uri="/tags/tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">


<html:html>

<head>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <title><tiles:getAsString name="title"/></title>
   <base href="<%=("http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/")%>" />
   <link rel="stylesheet" href="styles/styleSheet.css" type="text/css" />
	<script language="JavaScript" src="scripts/script.js"></script>

</head>






<body>
<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">

	<!-- nci hdr begins -->
  <tr>
    <td><tiles:get name="orgheader.jsp"/>
    </td>
  </tr>
  <!-- nci hdr ends -->

  <tr>
    <td height="100%" align="center" valign="top">
      <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%" width="771">
				<!-- application hdr begins -->
				<tr>
					<td height="50"><tiles:get name="appheader.jsp"/>
					</td>
				</tr>
				<!-- application hdr ends -->
        <tr>
          <td valign="top">
            <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
              <tr>
                <td height="20" class="mainMenu">

                  <!-- main menu begins -->
                <tiles:get name="mainmenu.jsp"/>
                  <!-- main menu ends -->

                </td>
              </tr>

<!--_____ main content begins _____-->
              <tr>
                <td valign="top">
                  <!-- target of anchor to skip menus --><a name="content" /></a>
                  <tiles:get name="UPTHome.jsp"/>
                </td>
              </tr>
<!--_____ main content ends _____-->

              <tr>
                <td height="20" width="100%" class="footerMenu">

                  <!-- application ftr begins -->
                  <tiles:get name="appfooter.jsp"/>
                  <!-- application ftr ends -->

                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>

      <!-- footer begins -->
      <tiles:get name="orgfooter.jsp"/>
      <!-- footer ends -->

    </td>
  </tr>
</table>
</body>
</html>
