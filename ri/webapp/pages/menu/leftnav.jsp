<%@ include file="/pages/imports.jsp"%>


<table cellpadding="0" cellspacing="0" border="0" width="175"
	height="100%">


<!-- notepad row -->
	<tr>
		<td valign="top" class="leftMenu" width="175" height="150">
			<table background="<html:rewrite href="pages/images/notepad.gif"/>"
				width="175" height="150" border="0" cellspacing=0 cellpadding=0>
				<tr>
					<td align="center"><br>Your Name:<br>
								Brian Husted
								<br><br>			
				
					Your Role:<br>
						Employee</td>
				</tr>
			</table>
		</td>
	</tr>	
		<!-- notepad ends   -->
	<!-- menu row -->		
	<tr>
		<td valign="top" class="leftMenu" width="100%" height="100%">
		<table>
			<tr>
				<td><img src="pages/images/dilbert.gif"></td>
			</tr>
			<tr>
				<td align="center" height="20" class="mainMenuItemOver"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()"
					onclick="document.location.href='home.html'"><html:link
					action="viewHome">
					<p class="mainMenuLink">HOME</p>
				</html:link></td>
			</tr>
			<tr>
				<td height="3"></td>
			</tr>
			<tr>
				<td align="center" height="20" class="mainMenuItem"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"
					onclick="document.location.href='createemployee.html'"><html:link
					action="viewCreateEmployee">
					<p class="mainMenuLink">CREATE EMPLOYEE</p>
				</html:link></td>
			</tr>
			<tr>
				<td height="3"></td>
			</tr>

			<tr>
				<td height="20" align="center" class="mainMenuItem"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"
					onclick="document.location.href='searchemployee.html'"><html:link
					action="viewSearchEmployee"><p class="mainMenuLink">SEARCH EMPLOYEE</p></html:link>
				</td>
			</tr>

			<tr>
				<td height="3"></td>
			</tr>


			<tr>
				<td align="center" height="20" class="mainMenuItem"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItem'),hideCursor()"
					onclick="document.location.href='createproject.html'"><html:link
					action="viewCreateProject"><p class="mainMenuLink">CREATE PROJECT</p></html:link>
				</td>
			</tr>

			<tr>
				<td height="3"></td>
			</tr>

			<tr>
				<td align="center" height="20" class="mainMenuItem"
					onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()"
					onmouseout="changeMenuStyle(this,'mainMenuItemOver'),hideCursor()"
					onclick="document.location.href='searchproject.html'"><a
					class="mainMenuLink" href="searchproject.html">SEARCH PROJECT</a></td>
			</tr>


		</table>
		</td>
	</tr>
	<!-- menu row ends -->
	
</table>
