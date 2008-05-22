package test.gov.nih.nci.security.acegi.sdk.domain;

public class Gene {
	
	private String someAttribute;
	public Gene(String str){
		this.someAttribute=str;
	}
	public String getSomeAttribute() {
		return someAttribute;
	}
	public void setSomeAttribute(String someAttribute) {
		this.someAttribute = someAttribute;
	}

}
