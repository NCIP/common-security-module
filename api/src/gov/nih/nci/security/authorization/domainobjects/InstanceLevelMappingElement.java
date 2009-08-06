package gov.nih.nci.security.authorization.domainobjects;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class InstanceLevelMappingElement implements Comparable, Serializable {
	private Long mappingId;
	private byte activeFlag;
	private String objectPackageName;
	private String objectName;
	private String attributeName;
	private String tableName;
	
	private String viewNameForUser; 	
	private String viewNameForGroup;
	private String tableNameForUser;
	private String tableNameForGroup;
	
	private Application application;
	private Date updateDate;
	
	
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getObjectPackageName() {
		return objectPackageName;
	}
	public void setObjectPackageName(String objectPackageName) {
		this.objectPackageName = objectPackageName;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableNameForGroup() {
		return tableNameForGroup;
	}
	public void setTableNameForGroup(String tableNameForGroup) {
		this.tableNameForGroup = tableNameForGroup;
	}
	public String getTableNameForUser() {
		return tableNameForUser;
	}
	public void setTableNameForUser(String tableNameForUser) {
		this.tableNameForUser = tableNameForUser;
	}
	public String getViewNameForGroup() {
		return viewNameForGroup;
	}
	public void setViewNameForGroup(String viewNameForGroup) {
		this.viewNameForGroup = viewNameForGroup;
	}
	public String getViewNameForUser() {
		return viewNameForUser;
	}
	public void setViewNameForUser(String viewNameForUser) {
		this.viewNameForUser = viewNameForUser;
	}
	public byte getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getMappingId() {
		return mappingId;
	}
	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}
	
	public int compareTo(Object object) {
		if(object instanceof InstanceLevelMappingElement){
			InstanceLevelMappingElement a = (InstanceLevelMappingElement) object;	
			return this.getObjectName().compareToIgnoreCase(a.getObjectName()); 
		}
		return 0;
	}

	public String toString() {
        return new ToStringBuilder(this)
        	.append("mappingId", getMappingId())
        	.append("objectPackageName", getObjectPackageName())
        	.append("objectName", getObjectName())
        	.append("attributeName", getAttributeName())
            .toString();
	}
	
	public int hashCode() {
		 return new HashCodeBuilder()
		 	.append(this.objectName+this.attributeName)
		 	.toHashCode();
	 }	 
	
	 public boolean equals(Object other) {
    	 if(this == other)
 			return true;
 		 if((other == null) || (other.getClass() != this.getClass()))
 			return false;
 		InstanceLevelMappingElement castOther = (InstanceLevelMappingElement) other;
	     return new EqualsBuilder()
	     	.append(this.getMappingId(), castOther.getMappingId())
	        .isEquals();
     }
	
}
