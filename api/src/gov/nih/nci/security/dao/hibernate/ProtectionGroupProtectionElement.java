/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import gov.nih.nci.security.authorization.domainobjects.*;

/** 
 *        @hibernate.class
 *         table="protection_group_protection_element"
 *     
*/
public class ProtectionGroupProtectionElement implements Serializable {

    /** identifier field */
    private Long protectionGroupProtectionElementId;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private ProtectionElement protectionElement;

    /** persistent field */
    private ProtectionGroup protectionGroup;

    /** full constructor */
    public ProtectionGroupProtectionElement(Long protectionGroupProtectionElementId, Date updateDate, ProtectionElement protectionElement, ProtectionGroup protectionGroup) {
        this.protectionGroupProtectionElementId = protectionGroupProtectionElementId;
        this.updateDate = updateDate;
        this.protectionElement = protectionElement;
        this.protectionGroup = protectionGroup;
    }

    /** default constructor */
    public ProtectionGroupProtectionElement() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="PROTECTION_GROUP_PROTECTION_ELEMENT_ID"
     *         
     */
    public Long getProtectionGroupProtectionElementId() {
        return this.protectionGroupProtectionElementId;
    }

    public void setProtectionGroupProtectionElementId(Long protectionGroupProtectionElementId) {
        this.protectionGroupProtectionElementId = protectionGroupProtectionElementId;
    }

    /** 
     *            @hibernate.property
     *             column="UPDATE_DATE"
     *             length="10"
     *             not-null="true"
     *         
     */
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="PROTECTION_ELEMENT_ID"         
     *         
     */
    public ProtectionElement getProtectionElement() {
        return this.protectionElement;
    }

    public void setProtectionElement(ProtectionElement protectionElement) {
        this.protectionElement = protectionElement;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="PROTECTION_GROUP_ID"         
     *         
     */
    public ProtectionGroup getProtectionGroup() {
        return this.protectionGroup;
    }

    public void setProtectionGroup(ProtectionGroup protectionGroup) {
        this.protectionGroup = protectionGroup;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("protectionGroupProtectionElementId", getProtectionGroupProtectionElementId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ProtectionGroupProtectionElement) ) return false;
        ProtectionGroupProtectionElement castOther = (ProtectionGroupProtectionElement) other;
        return new EqualsBuilder()
            .append(this.getProtectionGroupProtectionElementId(), castOther.getProtectionGroupProtectionElementId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getProtectionGroupProtectionElementId())
            .toHashCode();
    }

}
