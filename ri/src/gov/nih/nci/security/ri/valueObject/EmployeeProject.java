package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EmployeeProject implements Serializable {

    /** identifier field */
    private gov.nih.nci.security.ri.valueObject.EmployeeProjectPK comp_id;

    /** nullable persistent field */
    private gov.nih.nci.security.ri.valueObject.Employee employee;

    /** nullable persistent field */
    private gov.nih.nci.security.ri.valueObject.Project project;

    /** full constructor */
    public EmployeeProject(gov.nih.nci.security.ri.valueObject.EmployeeProjectPK comp_id, gov.nih.nci.security.ri.valueObject.Employee employee, gov.nih.nci.security.ri.valueObject.Project project) {
        this.comp_id = comp_id;
        this.employee = employee;
        this.project = project;
    }

    /** default constructor */
    public EmployeeProject() {
    }

    /** minimal constructor */
    public EmployeeProject(gov.nih.nci.security.ri.valueObject.EmployeeProjectPK comp_id) {
        this.comp_id = comp_id;
    }

    public gov.nih.nci.security.ri.valueObject.EmployeeProjectPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gov.nih.nci.security.ri.valueObject.EmployeeProjectPK comp_id) {
        this.comp_id = comp_id;
    }

    public gov.nih.nci.security.ri.valueObject.Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(gov.nih.nci.security.ri.valueObject.Employee employee) {
        this.employee = employee;
    }

    public gov.nih.nci.security.ri.valueObject.Project getProject() {
        return this.project;
    }

    public void setProject(gov.nih.nci.security.ri.valueObject.Project project) {
        this.project = project;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof EmployeeProject) ) return false;
        EmployeeProject castOther = (EmployeeProject) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
