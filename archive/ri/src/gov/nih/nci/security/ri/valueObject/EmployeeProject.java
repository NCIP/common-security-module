/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EmployeeProject implements Serializable {

    /** identifier field */
    private Long employeeProjectId;

    /** persistent field */
    private gov.nih.nci.security.ri.valueObject.Employee employee;

    /** persistent field */
    private gov.nih.nci.security.ri.valueObject.Project project;

    /** full constructor */
    public EmployeeProject(Long employeeProjectId, gov.nih.nci.security.ri.valueObject.Employee employee, gov.nih.nci.security.ri.valueObject.Project project) {
        this.employeeProjectId = employeeProjectId;
        this.employee = employee;
        this.project = project;
    }

    /** default constructor */
    public EmployeeProject() {
    }

    public Long getEmployeeProjectId() {
        return this.employeeProjectId;
    }

    public void setEmployeeProjectId(Long employeeProjectId) {
        this.employeeProjectId = employeeProjectId;
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
            .append("employeeProjectId", getEmployeeProjectId())
            .toString();
    }

}
