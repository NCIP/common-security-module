package gov.nih.nci.security.authorization.domainobjects;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserGroupRoleProtectionGroup implements Serializable {

    /** identifier field */
    private Long userGroupRoleProtectionGroupId;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.Group group;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.User user;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.ProtectionGroup protectionGroup;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.Role role;

    /** full constructor */
    public UserGroupRoleProtectionGroup(Long userGroupRoleProtectionGroupId, Date updateDate, gov.nih.nci.security.authorization.domainobjects.Group group, gov.nih.nci.security.authorization.domainobjects.User user, gov.nih.nci.security.authorization.domainobjects.ProtectionGroup protectionGroup, gov.nih.nci.security.authorization.domainobjects.Role role) {
        this.userGroupRoleProtectionGroupId = userGroupRoleProtectionGroupId;
        this.updateDate = updateDate;
        this.group = group;
        this.user = user;
        this.protectionGroup = protectionGroup;
        this.role = role;
    }

    /** default constructor */
    public UserGroupRoleProtectionGroup() {
    }

    public Long getUserGroupRoleProtectionGroupId() {
        return this.userGroupRoleProtectionGroupId;
    }

    public void setUserGroupRoleProtectionGroupId(Long userGroupRoleProtectionGroupId) {
        this.userGroupRoleProtectionGroupId = userGroupRoleProtectionGroupId;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public gov.nih.nci.security.authorization.domainobjects.Group getGroup() {
        return this.group;
    }

    public void setGroup(gov.nih.nci.security.authorization.domainobjects.Group group) {
        this.group = group;
    }

    public gov.nih.nci.security.authorization.domainobjects.User getUser() {
        return this.user;
    }

    public void setUser(gov.nih.nci.security.authorization.domainobjects.User user) {
        this.user = user;
    }

    public gov.nih.nci.security.authorization.domainobjects.ProtectionGroup getProtectionGroup() {
        return this.protectionGroup;
    }

    public void setProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup protectionGroup) {
        this.protectionGroup = protectionGroup;
    }

    public gov.nih.nci.security.authorization.domainobjects.Role getRole() {
        return this.role;
    }

    public void setRole(gov.nih.nci.security.authorization.domainobjects.Role role) {
        this.role = role;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userGroupRoleProtectionGroupId", getUserGroupRoleProtectionGroupId())
            .toString();
    }

}
