package gov.nih.nci.security.authorization.domainobjects;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserProtectionElement implements Serializable {

    /** identifier field */
    private Long userProtectionElementId;

    /** persistent field */
    private Date updateDate;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.ProtectionElement protectionElement;

    /** persistent field */
    private gov.nih.nci.security.authorization.domainobjects.User user;

    /** full constructor */
    public UserProtectionElement(Long userProtectionElementId, Date updateDate, gov.nih.nci.security.authorization.domainobjects.ProtectionElement protectionElement, gov.nih.nci.security.authorization.domainobjects.User user) {
        this.userProtectionElementId = userProtectionElementId;
        this.updateDate = updateDate;
        this.protectionElement = protectionElement;
        this.user = user;
    }

    /** default constructor */
    public UserProtectionElement() {
    }

    public Long getUserProtectionElementId() {
        return this.userProtectionElementId;
    }

    public void setUserProtectionElementId(Long userProtectionElementId) {
        this.userProtectionElementId = userProtectionElementId;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public gov.nih.nci.security.authorization.domainobjects.ProtectionElement getProtectionElement() {
        return this.protectionElement;
    }

    public void setProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement protectionElement) {
        this.protectionElement = protectionElement;
    }

    public gov.nih.nci.security.authorization.domainobjects.User getUser() {
        return this.user;
    }

    public void setUser(gov.nih.nci.security.authorization.domainobjects.User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userProtectionElementId", getUserProtectionElementId())
            .toString();
    }

}
