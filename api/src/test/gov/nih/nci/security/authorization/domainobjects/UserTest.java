// UserTest.java

package test.gov.nih.nci.security.authorization.domainobjects;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 1.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */


import gov.nih.nci.security.authorization.domainobjects.User;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * UserTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.User </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     User extends java.lang.Object <br>
 *     User implements java.security.Principal </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2005-03-18 19:23:10 $
 * @version $Revision: 1.2 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.User
 * @see some.other.package
 */

public class UserTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public UserTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //user = new User();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    user = null;
  }

  /**
   * Test the constructor: User()
   */
  public void testUser() {

  }

  /**
   * Test method: int hashCode()
   */
  public void testHashCode() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: boolean equals(Object)
   */
  public void testEquals() {
    //Must test for the following parameters!
    Object oValues [] = { null };

  }

  /**
   * Test method: String getName()
   */
  public void testGetName() {

  }

  /**
   * Test method: String toString()
   */
  public void testToString() {

  }

  /**
   * Test method: void setTitle(String)
   */
  public void testSetTitle() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: String getTitle()
   */
  public void testGetTitle() {

  }

  /**
   * Test method: java.util.Set getGroups()
   */
  public void testGetGroups() {

  }

  /**
   * Test method: java.util.Set getProtectionElements()
   */
  public void testGetProtectionElements() {

  }

  /**
   * Test method: java.util.Date getUpdateDate()
   */
  public void testGetUpdateDate() {

  }

  /**
   * Test method: void setGroups(Set)
   */
  public void testSetGroups() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setProtectionElements(Set)
   */
  public void testSetProtectionElements() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setUpdateDate(Date)
   */
  public void testSetUpdateDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: java.util.Set getProtectionGroupRoleContexts()
   */
  public void testGetProtectionGroupRoleContexts() {

  }

  /**
   * Test method: void setProtectionGroupRoleContexts(Set)
   */
  public void testSetProtectionGroupRoleContexts() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: String getDepartment()
   */
  public void testGetDepartment() {

  }

  /**
   * Test method: String getEmailId()
   */
  public void testGetEmailId() {

  }

  /**
   * Test method: java.util.Date getEndDate()
   */
  public void testGetEndDate() {

  }

  /**
   * Test method: String getFirstName()
   */
  public void testGetFirstName() {

  }

  /**
   * Test method: String getLastName()
   */
  public void testGetLastName() {

  }

  /**
   * Test method: String getLoginName()
   */
  public void testGetLoginName() {

  }

  /**
   * Test method: String getOrganization()
   */
  public void testGetOrganization() {

  }

  /**
   * Test method: String getPassword()
   */
  public void testGetPassword() {

  }

  /**
   * Test method: String getPhoneNumber()
   */
  public void testGetPhoneNumber() {

  }

  /**
   * Test method: java.util.Date getStartDate()
   */
  public void testGetStartDate() {

  }

  /**
   * Test method: java.lang.Long getUserId()
   */
  public void testGetUserId() {

  }

  /**
   * Test method: void setDepartment(String)
   */
  public void testSetDepartment() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setEmailId(String)
   */
  public void testSetEmailId() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setEndDate(Date)
   */
  public void testSetEndDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: void setFirstName(String)
   */
  public void testSetFirstName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setLastName(String)
   */
  public void testSetLastName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setLoginName(String)
   */
  public void testSetLoginName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setOrganization(String)
   */
  public void testSetOrganization() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setPassword(String)
   */
  public void testSetPassword() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setPhoneNumber(String)
   */
  public void testSetPhoneNumber() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setStartDate(Date)
   */
  public void testSetStartDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: void setUserId(Long)
   */
  public void testSetUserId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(UserTest.class) );
  }
  private User user;
}
