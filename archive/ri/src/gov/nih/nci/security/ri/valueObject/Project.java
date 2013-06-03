/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

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

    /** nullable persistent field */
    private String description;

    /** persistent field */
    private Set employeeProjects;

    /** full constructor */
    public Project(Long projectId, String name, String description, Set employeeProjects) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.employeeProjects = employeeProjects;
    }

    /** default constructor */
    public Project() {
    }

    /** minimal constructor */
    public Project(Long projectId, String name, Set employeeProjects) {
        this.projectId = projectId;
        this.name = name;
        this.employeeProjects = employeeProjects;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    /**
	 * @param obj
	 *  
	 */
	public boolean equals(Object obj) {
		Project other = (Project) obj;
		if (this.getProjectId().longValue() == other.getProjectId().longValue()) {
			return true;
		} else {
			return false;
		}
	}

}
