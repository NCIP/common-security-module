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
				<td align="center" height="20" class="mainMenuItemOver"><html:link
					action="viewHome" styleClass="mainMenuLink">HOME</html:link></td>
			</tr>
			<tr>
				<td height="3"></td>
			</tr>
			<tr>
				<td align="center" height="20" class="mainMenuItem"><html:link
					action="viewCreateEmployee" styleClass="mainMenuLink">CREATE EMPLOYEE
				</html:link></td>
			</tr>
			<tr>
				<td height="3"></td>
			</tr>

			<tr>
				<td height="20" align="center" class="mainMenuItem"><html:link
					action="viewSearchEmployee" styleClass="mainMenuLink">SEARCH EMPLOYEE</html:link>
				</td>
			</tr>

			<tr>
				<td height="3"></td>
			</tr>


			<tr>
				<td align="center" height="20" class="mainMenuItem"><html:link
					action="viewCreateProject" styleClass="mainMenuLink">CREATE PROJECT</html:link>
				</td>
			</tr>

			<tr>
				<td height="3"></td>
			</tr>

			<tr>
				<td align="center" height="20" class="mainMenuItem">SEARCH PROJECT
				</td>
			</tr>


		</table>
		</td>
	</tr>
	<!-- menu row ends -->
	
</table>
