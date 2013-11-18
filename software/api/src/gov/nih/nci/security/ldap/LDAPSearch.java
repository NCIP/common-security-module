package gov.nih.nci.security.ldap;

import javax.naming.Context;

public class LDAPSearch {
	public static final String SECURITY_AUTHENTICATION_SIMPLE = "simple";
	public static final String SECURITY_AUTHENTICATION_NONE = "none";
	
	private String securityAthentication;
	private String securityPrincipal;
	private String  securityCredentials;
	private String providerURL;
	private String searchFilter;
	private String firstName;
	private String lastName;
	private String loginId;
	private String email;
	private String mapping;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getSearchFilter() {
		return searchFilter;
	}
	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}
	public String getSecurityAthentication() {
		return securityAthentication;
	}
	public void setSecurityAthentication(String securityAthentication) {
		this.securityAthentication = securityAthentication;
	}
	public String getSecurityPrincipal() {
		return securityPrincipal;
	}
	public void setSecurityPrincipal(String securityPrincipal) {
		this.securityPrincipal = securityPrincipal;
	}
	public String getSecurityCredentials() {
		return securityCredentials;
	}
	public void setSecurityCredentials(String securityCredentials) {
		this.securityCredentials = securityCredentials;
	}
	public String getProviderURL() {
		return providerURL;
	}
	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}
}
