package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.validator.ValidatorForm;


/** @author Hibernate CodeGenerator */
public class Project extends ValidatorForm implements Serializable {

    /** identifier field */
    private Long projectId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Set employeeProjects;

    /** full constructor */
    public Project(Long projectId, String name, Set employeeProjects) {
        this.projectId = projectId;
        this.name = name;
        this.employeeProjects = employeeProjects;
    }

    /** default constructor */
    public Project() {
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getEmployeeProjects() {
        return this.employeeProjects;
    }

    public void setEmployeeProjects(Set employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("projectId", getProjectId())
            .toString();
    }

}
