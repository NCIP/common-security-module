package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EmployeeProjectPK implements Serializable {

    /** identifier field */
    private Long employeeId;

    /** identifier field */
    private Long projectId;

    /** full constructor */
    public EmployeeProjectPK(Long employeeId, Long projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    /** default constructor */
    public EmployeeProjectPK() {
    }

    public Long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("employeeId", getEmployeeId())
            .append("projectId", getProjectId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof EmployeeProjectPK) ) return false;
        EmployeeProjectPK castOther = (EmployeeProjectPK) other;
        return new EqualsBuilder()
            .append(this.getEmployeeId(), castOther.getEmployeeId())
            .append(this.getProjectId(), castOther.getProjectId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getEmployeeId())
            .append(getProjectId())
            .toHashCode();
    }

}
