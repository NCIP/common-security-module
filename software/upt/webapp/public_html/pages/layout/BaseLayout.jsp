<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<!-- NCI hdr begins -->
  <tr>
    <td><tiles:insertAttribute name="OrgHeader"/>
    </td>
  </tr>
  <!-- NCI hdr ends -->

  <tr>
    <td height="100%" align="center" valign="top">
      <table cellpadding="0" cellspacing="0" border="0" height="100%" width="771">
				<!-- application hdr begins -->
				<tr>
					<td valign="top" height="10"><tiles:insertAttribute name="AppHeader"/>
					</td>
				</tr>
				<!-- application hdr endsss -->
			       <tr>
					<s:if test="#session['ADMIN_USER'] != null">
						<tiles:insertAttribute name="AppAdminMenu"/>
					</s:if>
					<s:else>
						<tiles:insertAttribute name="AppMenu"/>
					</s:else>
			      </tr>
	                      
    	            
			  <!-- main content begins -->
              <tr>
                <td valign="top">
                  <!-- target of anchor to skip menus --><a name="content" /></a>
                  <tiles:insertAttribute name="Content"/>
                </td>
              </tr>
			  <!-- main content ends -->
              <tr>
                <td height="20" width="100%" class="footerMenu">
                  <!-- application ftr begins -->
                  <tiles:insertAttribute name="AppFooter"/>
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
      <!-- NCI footer begins -->
      <tiles:insertAttribute name="OrgFooter"/>
      <!-- NCI footer ends -->
    </td>
  </tr>
</table>
</body>
</html>
