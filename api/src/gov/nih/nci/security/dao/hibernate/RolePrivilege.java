/*
 * Created on Dec 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import gov.nih.nci.security.authorization.domainobjects.*;

/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class RolePrivilege implements Serializable {
	
	/** identifier field */
    private Long rolePrivilegeId;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private Privilege privilege;

    /** persistent field */
    private Role role;

    /** full constructor */
    public RolePrivilege(Long rolePrivilegeId, Date updateDate,Privilege privilege,Role role) {
        this.rolePrivilegeId = rolePrivilegeId;
        this.updateDate = updateDate;
        this.privilege = privilege;
        this.role = role;
    }

    /** default constructor */
    public RolePrivilege() {
    }

    public Long getRolePrivilegeId() {
        return this.rolePrivilegeId;
    }

    public void setRolePrivilegeId(Long rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Privilege getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("rolePrivilegeId", getRolePrivilegeId())
            .toString();
    }


}
