/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao.hibernate;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import gov.nih.nci.security.authorization.domainobjects.*;

/** 
 *        @hibernate.class
 *         table="user_group"
 *     
*/
public class UserGroup implements Serializable {

    /** identifier field */
    private Long userGroupId;

    /** persistent field */
    private Group group;

    /** persistent field */
    private User user;

    /** full constructor */
    public UserGroup(Long userGroupId, Group group, User user) {
        this.userGroupId = userGroupId;
        this.group = group;
        this.user = user;
    }

    /** default constructor */
    public UserGroup() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="USER_GROUP_ID"
     *         
     */
    public Long getUserGroupId() {
        return this.userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="GROUP_ID"         
     *         
     */
    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="USER_ID"         
     *         
     */
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userGroupId", getUserGroupId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserGroup) ) return false;
        UserGroup castOther = (UserGroup) other;
        return new EqualsBuilder()
            .append(this.getUserGroupId(), castOther.getUserGroupId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserGroupId())
            .toHashCode();
    }

}

