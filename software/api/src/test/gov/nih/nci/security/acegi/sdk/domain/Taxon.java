package test.gov.nih.nci.security.acegi.sdk.domain;

public class Taxon {
	
	private String attribute;
	
	public Taxon(String str){
		this.attribute=str;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String someAttribute) {
		this.attribute = someAttribute;
	}

}
