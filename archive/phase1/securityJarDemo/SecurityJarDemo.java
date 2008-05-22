import gov.nih.nci.securityFW.*;

/**
 * Title: 	     SecurityJarDemo.java
 * Description:  A demo program for the Security APIs
 * Copyright:    Copyright (c) 2004
 * Company:	     NCICB/SAIC
 * @author
 * @version 1.0
 */

public class SecurityJarDemo {

  public static void main (String args[])
  {
     System.out.println("Running the main of SecurityJarDemo");

     SecurityServiceObj theSecurityServiceObj = new SecurityServiceObj();

     System.out.println("Test Authentication with user 'anverm'");
     if (theSecurityServiceObj.authenticateUser("wuh","wuh0518@"))

        System.out.println("authentication successful");
     else System.out.println("authentication failed");

     System.out.println("Test Authorization with user 'wuh'");
     Role [] roleAry = theSecurityServiceObj.getRole("wuh","wuh0518@");

     if (theSecurityServiceObj.authorizeUser("wuh",roleAry, "caMOD", "caMOD.Availability.136", "getId"))
       System.out.println("access to allowed");
     else System.out.println("access disallowed");
  }
}
