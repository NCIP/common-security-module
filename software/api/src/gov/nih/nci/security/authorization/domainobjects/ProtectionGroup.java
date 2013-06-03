/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

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
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;





/**
 * This class represents a Protection Group. It is nothing but a collection of
 * protection elements. A user or a user-group can assume various roles on a
 * protection group
 * @version 1.0
 * created 03-Dec-2004 1:17:50 AM
 */
public class ProtectionGroup implements Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -592825051392330293L;
	/**
	 * It is the unique id by which it is identified in an application. 
	 */
	private Long protectionGroupId;
	/**
	 * It is the name of the protection element 
	 */
	private String protectionGroupName;
	/**
	 * A collection of protection elements that belongs to this protection group. 
	 */
	private java.util.Set protectionElements;
	/**
	 * The brief description of the protection group.
	 */
	private String protectionGroupDescription;
	/**
	 * The date when the protection group was last updated
	 */
	private java.util.Date updateDate;
	/**
	 * The protection group which is parent of the current protection group
	 */
	private ProtectionGroup parentProtectionGroup;
	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 */
	private byte largeElementCountFlag;
	/**
	 * The application to which the following protection group belongs
	 */
	private Application application;

	public ProtectionGroup(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The application to which the following protection group belongs
	 */
	public Application getApplication(){
		return application;
	}

	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 */
	public byte getLargeElementCountFlag(){
		return largeElementCountFlag;
	}

	/**
	 * The protection group which is parent of the current protection group
	 */
	public ProtectionGroup getParentProtectionGroup(){
		return parentProtectionGroup;
	}

	/**
	 * A collection of protection elements that belongs to this protection group. 
	 */
	public java.util.Set getProtectionElements(){
		return protectionElements;
	}

	/**
	 * The brief description of the protection group.
	 */
	public String getProtectionGroupDescription(){
		return protectionGroupDescription;
	}

	/**
	 * It is the unique id by which it is identified in an application. 
	 */
	public Long getProtectionGroupId(){
		return protectionGroupId;
	}

	/**
	 * It is the name of the protection element 
	 */
	public String getProtectionGroupName(){
		return protectionGroupName;
	}

	/**
	 * The date when the protection group was last updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * The application to which the following protection group belongs
	 * @param newVal
	 * 
	 */
	public void setApplication(Application newVal){
		application = newVal;
	}

	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 * @param newVal
	 * 
	 */
	public void setLargeElementCountFlag(byte newVal){
		largeElementCountFlag = newVal;
	}

	/**
	 * The protection group which is parent of the current protection group
	 * @param newVal
	 * 
	 */
	public void setParentProtectionGroup(ProtectionGroup newVal){
		parentProtectionGroup = newVal;
	}

	/**
	 * A collection of protection elements that belongs to this protection group.
	 * @param newVal
	 * 
	 */
	public void setProtectionElements(java.util.Set newVal){
		protectionElements = newVal;
	}

	/**
	 * The brief description of the protection group.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupDescription(String newVal){
		protectionGroupDescription = newVal;
	}

	/**
	 * It is the unique id by which it is identified in an application.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupId(Long newVal){
		protectionGroupId = newVal;
	}

	/**
	 * It is the name of the protection element
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupName(String newVal){
		protectionGroupName = newVal;
	}

	/**
	 * The date when the protection group was last updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

	/**
	 * @param members    members
	 * 
	 */
	public void addGroups(GroupRoleContext[] members){

	}

	/**
	 * @param members    members
	 * 
	 */
	public void addUsers(UserRoleContext[] members){

	}

	/**
	 * @param elements    elements
	 * 
	 */
	public void addProtectionElements(ProtectionElement[] elements){

	}
	
	public int compareTo(Object object) {

		if(object instanceof ProtectionGroup){
			ProtectionGroup obj = (ProtectionGroup) object;	
			return this.getProtectionGroupName().compareToIgnoreCase(obj.getProtectionGroupName()); 
		}
		return 0;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
        	.append("protectionGroupId", protectionGroupId)
            .append("protectionGroupName", protectionGroupName)
            .append("protectionGroupDescription", protectionGroupDescription)            
            .append("updateDate", updateDate.toString())            
            .toString();
	}
	
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if ((other == null) || (other.getClass() != this.getClass()))
			return false;
		ProtectionGroup castOther = (ProtectionGroup) other;
		return new EqualsBuilder()
			.append(this.getProtectionGroupId(),castOther.getProtectionGroupId())
			.append(this.getProtectionGroupName(),castOther.getProtectionGroupName())
			.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.protectionGroupId)
				.append(this.protectionGroupName)
				.toHashCode();
	}
	

}
