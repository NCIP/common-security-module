package gov.nih.nci.security.acegi.authentication;

import org.acegisecurity.GrantedAuthority;

/**
 * CSM's Implementation of GrantedAuthority
 * 
 * 
 * @author parmarv
 *
 */
public class CSMGrantedAuthority implements GrantedAuthority {
	private String authority = null;

	public CSMGrantedAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}
}