/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.acegi.authentication;


import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * CSM Implementation of UserDetails
 * 
 * @author parmarv
 *
 */
public class CSMUserDetails implements UserDetails {
	private GrantedAuthority[] authorities = null;

	private String password = null;

	private String username = null;

	private String additionalData = null;

	public CSMUserDetails(GrantedAuthority[] authorities, String password,
			String username, String additionalData) {
		super();
		this.authorities = authorities;
		this.password = password;
		this.username = username;
		this.additionalData = additionalData;
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}