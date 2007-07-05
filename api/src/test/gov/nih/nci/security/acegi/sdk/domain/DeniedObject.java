package test.gov.nih.nci.security.acegi.sdk.domain;

public class DeniedObject {
	private String junk;

	public DeniedObject(String junk1){
		this.junk=junk1;
	}
	
	public String getJunk() {
		return junk;
	}

	public void setJunk(String junk) {
		this.junk = junk;
	}
}
