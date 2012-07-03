package gov.nih.nci.security.authorization.domainobjects;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
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


import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;





/**
 * The user class is a representation of an application user.
 * @version 1.0
 * created 03-Dec-2004 1:17:51 AM
 */
public class User implements Principal, Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362437494465133742L;
	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 */
	private java.util.Set protectionGroupRoleContexts;
	/**
	 * This attribute tells which groups does this user belong to.
	 */
	private java.util.Set groups;
	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 */
	private java.util.Set protectionElements;
	/**
	 * This a unique id to identify a user within an application.
	 */
	private Long userId;
	/**
	 * This string is used for login into the application.
	 */
	private String loginName;
	/**
	 * This string is used to store the pre migration id for CSM.
	 */
	private String preMigrationLoginName;	
	/**
	 * The first name of the user
	 */
	private String firstName;
	/**
	 * The last name of the user
	 */
	private String lastName;
	/**
	 * The name of the organization that this user belongs to.
	 */
	private String organization;
	/**
	 * The name of the department that this user belongs to.
	 */
	private String department;
	/**
	 * The name of the title for this user.
	 */
	private String title;
	/**
	 * This is the work phone of the user.
	 */
	private String phoneNumber;
	/**
	 * The password used to login into the application
	 */
	private String password;
	/**
	 * Email id for this user.
	 */
	private String emailId;
	/**
	 * It is the start date for this user.
	 */
	private java.util.Date startDate;
	/**
	 * It is the end date for this user.
	 */
	private java.util.Date endDate;
	/**
	 * It is the date when the user information was last updated
	 */
	private java.util.Date updateDate;
	/**
	 * Indicate whether the User id is migrated to Grid Id.
	 */
	private byte migratedFlag;	
	
	private byte firstTimeLogin;
	
	private byte passwordExpired;
	
	private java.util.Date passwordUpdateDate;		

	public User(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The name of the department that this user belongs to.
	 */
	public String getDepartment(){
		return department;
	}

	/**
	 * Email id for this user.
	 */
	public String getEmailId(){
		return emailId;
	}

	/**
	 * It is the end date for this user.
	 */
	public Date getEndDate(){
		return endDate;
	}

	/**
	 * The first name of the user
	 */
	public String getFirstName(){
		return firstName;
	}

	/**
	 * This attribute tells which groups does this user belong to.
	 */
	public java.util.Set getGroups(){
		return groups;
	}

	/**
	 * The last name of the user
	 */
	public String getLastName(){
		return lastName;
	}

	/**
	 * This string is used for login into the application.
	 */
	public String getLoginName(){
		return loginName;
	}

	/**
	 * The name of the organization that this user belongs to.
	 */
	public String getOrganization(){
		return organization;
	}

	/**
	 * The password used to login into the application
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * This is the work phone of the user.
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}

	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 */
	public java.util.Set getProtectionElements(){
		return protectionElements;
	}

	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 */
	public java.util.Set getProtectionGroupRoleContexts(){
		return protectionGroupRoleContexts;
	}

	/**
	 * It is the start date for this user.
	 */
	public Date getStartDate(){
		return startDate;
	}

	/**
	 * The name of the title for this user.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * It is the date when the user information was last updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * This a unique id to identify a user within an application.
	 */
	public Long getUserId(){
		return userId;
	}

	/**
	 * The name of the department that this user belongs to.
	 * @param newVal
	 * 
	 */
	public void setDepartment(String newVal){
		department = newVal;
	}

	/**
	 * Email id for this user.
	 * @param newVal
	 * 
	 */
	public void setEmailId(String newVal){
		emailId = newVal;
	}

	/**
	 * It is the end date for this user.
	 * @param newVal
	 * 
	 */
	public void setEndDate(Date newVal){
		endDate = newVal;
	}

	/**
	 * The first name of the user
	 * @param newVal
	 * 
	 */
	public void setFirstName(String newVal){
		firstName = newVal;
	}

	/**
	 * This attribute tells which groups does this user belong to.
	 * @param newVal
	 * 
	 */
	public void setGroups(java.util.Set newVal){
		groups = newVal;
	}

	/**
	 * The last name of the user
	 * @param newVal
	 * 
	 */
	public void setLastName(String newVal){
		lastName = newVal;
	}

	/**
	 * This string is used for login into the application.
	 * @param newVal
	 * 
	 */
	public void setLoginName(String newVal){
		loginName = newVal;
	}

	/**
	 * The name of the organization that this user belongs to.
	 * @param newVal
	 * 
	 */
	public void setOrganization(String newVal){
		organization = newVal;
	}

	/**
	 * The password used to login into the application
	 * @param newVal
	 * 
	 */
	public void setPassword(String newVal){
		password = newVal;
	}

	/**
	 * This is the work phone of the user.
	 * @param newVal
	 * 
	 */
	public void setPhoneNumber(String newVal){
		phoneNumber = newVal;
	}

	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 * @param newVal
	 * 
	 */
	public void setProtectionElements(java.util.Set newVal){
		protectionElements = newVal;
	}

	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupRoleContexts(java.util.Set newVal){
		protectionGroupRoleContexts = newVal;
	}

	/**
	 * It is the start date for this user.
	 * @param newVal
	 * 
	 */
	public void setStartDate(Date newVal){
		startDate = newVal;
	}

	/**
	 * The name of the title for this user.
	 * @param newVal
	 * 
	 */
	public void setTitle(String newVal){
		title = newVal;
	}

	/**
	 * It is the date when the user information was last updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

	/**
	 * This a unique id to identify a user within an application.
	 * @param newVal
	 * 
	 */
	public void setUserId(Long newVal){
		userId = newVal;
	}
	
	public byte getMigratedFlag() {
		return migratedFlag;
	}

	public void setMigratedFlag(byte migratedFlag) {
		this.migratedFlag = migratedFlag;
	}

	public byte getFirstTimeLogin() {
		return firstTimeLogin;
	}

	public void setFirstTimeLogin(byte firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}
	
	public byte getPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(byte passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public java.util.Date getPasswordUpdateDate() {
		return passwordUpdateDate;
	}

	public void setPasswordUpdateDate(java.util.Date passwordUpdateDate) {
		this.passwordUpdateDate = passwordUpdateDate;
	}


	public String toString() {
        return new ToStringBuilder(this)
        	.append("userId", userId)
            .append("loginName", loginName)
            .append("firstName", firstName)            
            .append("lastName", lastName)
            .append("emailId", emailId)
            .append("department", department)
            .append("organization", organization)
            .append("phoneNumber", phoneNumber)
            .append("startDate", startDate)
            .append("updateDate", updateDate.toString())            
            .toString();
	}

	public String getName(){
		return this.loginName;
	}

	public int compareTo(Object object) {

		if(object instanceof User){
			User obj = (User) object;	
			return this.getLoginName().compareToIgnoreCase(obj.getLoginName()); 
		}
		return 0;
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if ((other == null) || (other.getClass() != this.getClass()))
			return false;
		User castOther = (User) other;
		return new EqualsBuilder()
			.append(this.userId,castOther.getUserId())
			.append(this.getLoginName(),castOther.getLoginName())
			.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.userId)
				.append(this.loginName)
				.toHashCode();
	}

	public String getPreMigrationLoginName() {
		return preMigrationLoginName;
	}

	public void setPreMigrationLoginName(String preMigrationLoginName) {
		this.preMigrationLoginName = preMigrationLoginName;
	}

	
	
	
}
