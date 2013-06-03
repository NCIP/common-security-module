<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L--%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String uptLoginHome = path+"/LHome.do";
String uptLoginLogin= path+"/Login.do";
String uptLoginMenuSelection= path+"/MenuSelection.do";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   	<link rel="stylesheet" href="styles/styleSheet.css" type="text/css" />

	<script language="JavaScript" src="scripts/script.js"></script>
	<!-- Page Title begins -->
	<title>UPT Login Application</title>
	<!-- Page Title ends -->
</head>
<body>

<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">

	<!-- NCI hdr begins -->
  <tr>

    <td>



<table width="100%" border="0" cellspacing="0" cellpadding="0" class="hdrBG">
        <tr>
          <td width="283" height="37" align="left"><a href="http://www.cancer.gov"><img alt="National Cancer Institute" src="images/logotype.gif" width="283" height="37" border="0"></a></td>
          <td>&nbsp;</td>
          <td width="295" height="37" align="right"><a href="http://www.cancer.gov"><img alt="U.S. National Institutes of Health | www.cancer.gov" src="images/tagline.gif" width="295" height="37" border="0"></a></td>
        </tr>
      </table>

    </td>
  </tr>
  <!-- NCI hdr ends -->
  <tr>
    <td height="100%" align="center" valign="top">
      <table cellpadding="0" cellspacing="0" border="0" height="100%" width="771">
				<!-- application hdr begins -->
				<tr>

					<td height="50">







<table width="100%" height="50" border="0" cellspacing="0" cellpadding="0" class="subhdrBG">
							<tr>
							
							<!-- new separate links depending on admin or super admin -->
							
							 
								
							
							<td height="50" width="400" align="left"><a href="<%=uptLoginHome%>"><img src="images/appLogo.gif" border="0" hspace="10" alt="UPT Home"></a></td>
									
								
							<!-- end home links -->	
								
								
							</tr>

</table>

					</td>
				</tr>
				<!-- application hdr ends -->
        <tr>
          
              
		            
	           
		                








<script>
  <!--
    	function set(tableId)
    	{
    		document.MenuForm.tableId.value=tableId;
    		document.MenuForm.submit();
    	}
    	
    	  
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
    	
    	
   // -->
</script>

<form name="MenuForm" method="post" action="<%=uptLoginMenuSelection %>" id="menuForm">
	
	<input type="hidden" name="tableId" value="error">

	<td class="mainMenu" height="20">
	<table cellpadding="0" cellspacing="0" border="0"
		height="16">
		
	</table>
	</td>
</form>

		                 
    	            
                
              </tr>
	                      
    	            
			  <!-- main content begins -->
              <tr>
                <td valign="top">

                  <!-- target of anchor to skip menus --><a name="content" /></a>
                  




<table cellpadding="0" cellspacing="0" border="0"
	class="contentPage" width="100%" height="100%">
	<tr>
		<td valign="top"><!-- target of anchor to skip menus --><a
			name="content" /></a>
		<table cellpadding="0" cellspacing="0" border="0"
			class="contentPage" width="100%" height="100%">

			<!-- banner begins -->
			<tr>
				<td class="bannerHome"><img src="images/bannerHome.gif" height="140" alt="NCICB CSM User Provisioning Tool"></td>

			</tr>
			<!-- banner begins -->

			<tr>
				<td height="100%"><!-- target of anchor to skip menus --><a
					name="content" /></a>

				<table cellpadding="0" cellspacing="0" border="0"
					height="100%">
					<tr>
						<td width="70%"><!-- welcome begins -->
						<table summary="Welcome Content" cellpadding="0" cellspacing="0" border="0"
							height="100%">

							<tr>
								<td class="welcomeTitle" height="20">WELCOME TO THE USER
								PROVISIONING TOOL</td>
							</tr>
							<tr>
								<td class="welcomeContent" valign="top">Welcome to the User
								Provisioning Tool (UPT). This user interface tool is designed so
								that developers can easily configure an application's
								authorization data in the Common Security Module (CSM) database.
								With the click of a few buttons you may control which users can
								access protected elements or operations of your application.
								This tool combined with the CSM allows for fine-grain security
								control, and will eventually provide other features such as
								single sign-on. The UPT is divided into six major sections:
								Users, Groups, Protection Groups, Protection Elements,
								Roles and Privileges. From these sections you may perform 
								basic functions such as modify,	delete, or create, 
								and you may also manage associations between
								the objects. For example you may assign Privileges to a Role.
								Please begin by logging in, then select one of the menu options
								or follow our Recommended Workflow.</td>
							</tr>
						</table>
						<!-- welcome ends --></td>

						<td valign="top" width="30%"><!-- sidebar begins -->
						<table cellpadding="0" cellspacing="0" border="0"
							height="100%">
							<form name="LoginForm" method="post" action="<%=uptLoginLogin%>"  autocomplete="off">
								<!-- login begins -->
								<tr>
									<td valign="top">
									<table  cellpadding="2" cellspacing="0" border="0"
										width="100%" class="sidebarSectionLogin">
										<tr>
											<td class="sidebarTitle" height="20">LOGIN Application</td>

										</tr>
										<tr>
											<td class="sidebarContent">
											<table cellpadding="2" cellspacing="0" border="0">
												<tr>
													<td colspan="2"></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label for="loginId">LOGIN
													ID</label></td>

													<td class="formFieldLogin"><input type="text" id="loginId" name="loginId" size="14" value="" style="formField"></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="password">PASSWORD</label></td>
													<td class="formFieldLogin"><input type="password" id="password" name="password" size="14" value="" style="formField"></td>
												</tr>
												<tr>
													<td class="sidebarLogin" align="right"><label
														for="applicationContextName">APPLICATION NAME</label></td>

													<td class="formFieldLogin"><input type="text" id="applicationContextName" name="applicationContextName" size="14" value="" style="formField"></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input type="submit" value="Login" style="actionButton"></td>
												</tr>
											</table>
											</td>
										</tr>

									</table>
									</td>
								</tr>
								<!-- login ends -->
							</form>
<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["LoginForm"].elements["loginId"];

  if (focusControl.type != "hidden") {
     focusControl.focus();
  }
  // -->
</script>

							<!-- what's new begins -->
							<tr>

								<td valign="top">
								<table summary="What is new" cellpadding="0" cellspacing="0" border="0"
									width="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">WHAT'S NEW IN 4.2</td>
									</tr>
									<tr>
										<td class="sidebarContent">
											<li>NCI CBIIT 2011 technology stack upgrade
											<li>Instance Level Security performance enhancements
											<li>AuthorizationManager enhancements
											<li>Several API enhancements and bug fixes
										</td>

									</tr>
								</table>
								</td>
							</tr>
							<!-- what's new ends -->

							<!-- did you know? begins -->
							<tr>
								<td valign="top">

								<table summary="Do you know?" cellpadding="0" cellspacing="0" border="0"
									width="100%" height="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarTitle" height="20">DID YOU KNOW?</td>
									</tr>
									<tr>
										<td class="sidebarContent" valign="top">
										<li>You can organize your Protection Elements with the new Protection Element Type field. 
										<li>A user's Login ID can no longer be edited once it has been created.
										
										</td>

									</tr>
								</table>
								</td>
							</tr>
							<!-- did you know? ends -->

							<!-- spacer cell begins (keep for dynamic expanding) -->
							<tr>
								<td valign="top" height="100%">

								<table cellpadding="0" cellspacing="0" border="0"
									width="100%" height="100%" class="sidebarSectionLogin">
									<tr>
										<td class="sidebarContent" valign="top">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<!-- spacer cell ends -->

						</table>
						<!-- sidebar ends --></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>

</table>

                </td>
              </tr>
			  <!-- main content ends -->
              <tr>
                <td height="20" width="100%" class="footerMenu">
                  <!-- application ftr begins -->
                  



  <table class="footerMenu" cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>

      <td align="center" height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()">
        &nbsp;&nbsp;<a class="footerMenuLink" href="FooterContactUs.do">CONTACT US</a>&nbsp;&nbsp;
      </td>
      <td ><img src="images/ftrMenuSeparator.gif" width="1" height="16" alt="" /></td>
      <td align="center" height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()">
        &nbsp;&nbsp;<a class="footerMenuLink" href="FooterPrivacy.do">PRIVACY NOTICE</a>&nbsp;&nbsp;
      </td>
      <td><img src="images/ftrMenuSeparator.gif" width="1" height="16" alt="" /></td>

      <td align="center" height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()">
        &nbsp;&nbsp;<a class="footerMenuLink" href="FooterDisclaimer.do">DISCLAIMER</a>&nbsp;&nbsp;
      </td>
      <td><img src="images/ftrMenuSeparator.gif" width="1" height="16" alt="" /></td>
      <td align="center" height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()">
        &nbsp;&nbsp;<a class="footerMenuLink" href="FooterAccessibility.do">ACCESSIBILITY</a>&nbsp;&nbsp;
      </td>
      <td><img src="images/ftrMenuSeparator.gif" width="1" height="16" alt="" /></td>

      <td align="center" height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()">
        &nbsp;&nbsp;<a class="footerMenuLink" href="FooterApplicationSupport.do">APPLICATION SUPPORT</a>&nbsp;&nbsp;
      </td>
    </tr>
  </table>

                  <!-- application ftr ends -->
                </td>
              </tr>

            </table>
          </td>
        </tr>
      </table>
    <td></td>
  </tr>
  <tr>
    <td>
      <!-- NCI footer begins -->

      




<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ftrTable">
        <tr>
          <td valign="top">
            <div align="center">
              <a href="http://www.cancer.gov/"><img src="images/footer_nci.gif" width="63" height="31" alt="National Cancer Institute" border="0"></a>
              <a href="http://www.dhhs.gov/"><img src="images/footer_hhs.gif" width="39" height="31" alt="Department of Health and Human Services" border="0"></a>
              <a href="http://www.nih.gov/"><img src="images/footer_nih.gif" width="46" height="31" alt="National Institutes of Health" border="0"></a>
              <a href="http://www.firstgov.gov/"><img src="images/footer_firstgov.gif" width="91" height="31" alt="FirstGov.gov" border="0"></a>
            </div>

          </td>
        </tr>
      </table>

      <!-- NCI footer ends -->
    </td>
  </tr>
</table>
</body>
</html>
