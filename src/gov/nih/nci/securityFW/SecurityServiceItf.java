/**
 *	The caBIG Software License, Version 1.0
 *
 *	Copyright 2004 SAIC. This software was developed in conjunction with the National Cancer
 *	Institute, and so to the extent government employees are co-authors, any rights in such works
 *	shall be subject to Title 17 of the United States Code, section 105.
 *
 *	Redistribution and use in source and binary forms, with or without modification, are permitted
 *	provided that the following conditions are met:
 *
 *	1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *	and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *	copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *	other materials provided with the distribution.
 *
 *	2.  The end-user documentation included with the redistribution, if any, must include the
 *	following acknowledgment:
 *
 *	"This product includes software developed by the SAIC and the National Cancer
 *	Institute."
 *
 *	If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *	software itself, wherever such third-party acknowledgments normally appear.
 *
 *	3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *	promote products derived from this software.
 *
 *	4. This license does not authorize the incorporation of this software into any third party proprietary
 *	programs.  This license does not authorize the recipient to use any trademarks owned by either
 *	NCI or SAIC-Frederick.
 *
 *
 *	5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *	WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *	MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *	DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *	THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *	EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *	PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *	OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *	NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package gov.nih.nci.securityFW;

/**
 * An interface defines the generic properties of a security service object.
 * @author       Q. Pan
 * @version      1.0
 */
public interface SecurityServiceItf
{

   /**
    * Returns the result of the user authentication.
    * @param loginName  the login name of the user
    * @param passwd     the password of the user
    * @return  true for successful authentication<br>
    *          false for failed authentication
    */
   public boolean authenticateUser(String loginName, String passwd);


   /**
    * Returns an array of Role objects associated with the loginName/passwd.
    * @param loginName  the login name of the user
    * @param passwd the password of the user
    * @return an array of Role objects associated with the login name
    */
   public Role [] getRole(String loginName, String passwd);

   /**
    * Returns the given user or role's access permission to a protection element.
    * @param loginName login name used to check if this is the owner of the protection element
    * @param roles, an array of Role that the user has
    * @param applicationName application name
    * @param objectId object Id
    * @param attribute attribute
    * @return true if the user is the owner of the protection element or has the permission
    *         false if the user is not the owner of the protection element or has no asscee permission
    */
   public boolean authorizeUser(String loginName, Role [] roles, String applicationName, String objectId, String attribute);

   /**
    * Returns the roles' access permission to a protection element.
    * @param roles, an array of Role that the user has
    * @param applicationName application name
    * @param objectId object Id
    * @param attribute attribute
    * @return true if has the permission
    *         false if has no asscee permission
    */
   public boolean authorizeUser(Role [] roles, String applicationName, String objectId, String attribute);
}
