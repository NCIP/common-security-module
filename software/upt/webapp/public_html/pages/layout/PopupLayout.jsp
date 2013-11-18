<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ page import="gov.nih.nci.security.upt.constants.*"%>

<html>

<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   	<link rel="stylesheet" href="styles/styleSheet.css" type="text/css" />
	<script language="JavaScript" src="scripts/script.js"></script>
	<!-- Page Title begins -->
	<title><tiles:getAsString name="Title"/></title>
	<!-- Page Title ends -->
</head>
<body>

<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">

  <tr>
    <td height="100%" align="center" valign="top">

      <table cellpadding="0" cellspacing="0" border="0" height="100%" width="771">
      
			  <!-- main content begins -->
              <tr>
                <td valign="top">
                  <!-- target of anchor to skip menus --><a name="content" /></a>
                  <tiles:insertAttribute name="Content"/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
