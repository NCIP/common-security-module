<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ include file= "/pages/imports.jsp" %>

<html>
<head>

<link rel="stylesheet" type="text/css" href="styleSheet.css" />
<script src="script.js" type="text/javascript"></script>
</head>
<body>


<table cellpadding="0" cellspacing="0" border="0" height="120" width="175" height="100%">





<tr>
<td class="mainMenu">



<!-- notepad begins -->


<table background="<html:rewrite href="pages/images/notepad.gif"/>" class="contentPage" width="175" border="0" cellspacing=0 cellpadding=0>
<tr><td class="contentBegins" align="center">Your Name:<br>Brian Husted</td></tr>
<tr><td class="contentBegins" align="center">Your Role:<br>Employee</td></tr></table>

<!-- 
<table>
<tr><td><img src="pages/images/notepad.gif"></td></tr></table>-->


<!-- notepad ends   -->


<table>

<tr><td><img src="pages/images/dilbert.gif"></td></tr>

<tr>
<td align="center" height="20" class="mainMenuItemOver" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="document.location.href='home.html'">
                        <a class="mainMenuLink" href="home.html">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HOME&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                      </td>
</tr>



<!--<tr><td>&nbsp;</td></tr>


<!--<tr><td><img src="pages/images/wally.gif"></td></tr>-->
<tr><td height="3"></td></tr>

	 <tr>
	    <td align="center" height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="document.location.href='createemployee.html'">
	     <a class="mainMenuLink" href="createemployee.html">CREATE EMPLOYEE</a>
	     </td>
	  </tr>


<tr><td height="3"></td></tr>

	  <tr>
	     <td height="20" align="center" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="document.location.href='searchemployee.html'">
	      <a class="mainMenuLink" href="searchemployee.html">SEARCH EMPLOYEE</a>
	      </td>
	   </tr>

<tr><td height="3"></td></tr>


<tr>
	    <td align="center" height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()" onclick="document.location.href='createproject.html'">
	     <a class="mainMenuLink" href="createproject.html">CREATE PROJECT</a>
	     </td>
	  </tr>

<tr><td height="3"></td></tr>

	  <tr>
	     <td  align="center" height="20" class="mainMenuItem" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()" onclick="document.location.href='searchproject.html'">
	      <a class="mainMenuLink" href="searchproject.html">SEARCH PROJECT</a>
	      </td>
	   </tr>


</table>
</td>
</tr>
</table>


</body>
</html>