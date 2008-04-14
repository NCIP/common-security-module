package gov.nih.nci.security.authorization.attributeLevel;

import java.util.List;

public class GroupClassAttributeMap{
	
	private String className;
	private List attributes;
	public GroupClassAttributeMap(String className){
		this.className=className;
	}
	public List getAttributes() {
		return attributes;
	}
	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}