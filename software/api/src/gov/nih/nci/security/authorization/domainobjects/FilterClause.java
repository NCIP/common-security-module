package gov.nih.nci.security.authorization.domainobjects;

import java.io.Serializable;
import java.util.Date;


public class FilterClause implements Comparable, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9026282337212120933L;
	
	private Long id;
	private String className;
	private String filterChain;
	private String targetClassName;
	private String targetClassAttributeName;
	private String targetClassAttributeType;
	private String targetClassAlias;
	private String targetClassAttributeAlias;
	//private String generatedSQL;
	private String generatedSQLForUser;
	private String generatedSQLForGroup;
	private Application application;
	private Date updateDate;
	
	public Application getApplication()
	{
		return application;
	}
	
	public void setApplication(Application application)
	{
		this.application = application;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public void setClassName(String className)
	{
		this.className = className;
	}
	
	public String getFilterChain()
	{
		return filterChain;
	}
	
	public void setFilterChain(String filterChain)
	{
		this.filterChain = filterChain;
	}
	
	/*public String getGeneratedSQL()
	{
		return generatedSQL;
	}
	
	public void setGeneratedSQL(String generatedSQL)
	{
		this.generatedSQL = generatedSQL;
	}*/
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getTargetClassAlias()
	{
		return targetClassAlias;
	}
	
	public void setTargetClassAlias(String targetClassAlias)
	{
		this.targetClassAlias = targetClassAlias;
	}
	
	public String getTargetClassAttributeName()
	{
		return targetClassAttributeName;
	}

	
	public void setTargetClassAttributeName(String targetClassAttributeName)
	{
		this.targetClassAttributeName = targetClassAttributeName;
	}

	public String getTargetClassAttributeType()
	{
		return targetClassAttributeType;
	}

	
	public void setTargetClassAttributeType(String targetClassAttributeType)
	{
		this.targetClassAttributeType = targetClassAttributeType;
	}

	
	public String getTargetClassName()
	{
		return targetClassName;
	}

	
	public void setTargetClassName(String targetClassName)
	{
		this.targetClassName = targetClassName;
	}

	
	public String getTargetClassAttributeAlias()
	{
		return targetClassAttributeAlias;
	}

	
	public void setTargetClassAttributeAlias(String targetClassAttributeAlias)
	{
		this.targetClassAttributeAlias = targetClassAttributeAlias;
	}

	
	
	/**
	 * @return Returns the updateDate.
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}

	
	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public int compareTo(Object object)
	{
		if(object instanceof FilterClause){
			FilterClause obj = (FilterClause) object;	
			return this.getClassName().compareToIgnoreCase(obj.getClassName()); 
		}
		return 0;
	}

	public String getGeneratedSQLForGroup() {
		return generatedSQLForGroup;
	}

	public void setGeneratedSQLForGroup(String generatedSQLForGroup) {
		this.generatedSQLForGroup = generatedSQLForGroup;
	}

	public String getGeneratedSQLForUser() {
		return generatedSQLForUser;
	}

	public void setGeneratedSQLForUser(String generatedSQLForUser) {
		this.generatedSQLForUser = generatedSQLForUser;
	}



}
