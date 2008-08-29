package gov.nih.nci.security.authorization.attributeLevel;

import java.util.List;

public class ClassAttributeMap{
	
	private String className;
	private List<String> attributes;
	public ClassAttributeMap(String className){
		this.className=className;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}