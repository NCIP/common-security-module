package gov.nih.nci.security.ldap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPSearchUtil {

	LDAPSearch search;

	public LDAPSearchUtil(LDAPSearch search) {
		this.search = search;
	}

	public List<LDAPUser> getUsers() throws LDAPImportException {

		validate();
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, search.getProviderURL());
		env.put(Context.SECURITY_AUTHENTICATION,
				search.getSecurityAthentication());
		if(search.getSecurityPrincipal() != null)
			env.put(Context.SECURITY_PRINCIPAL, search.getSecurityPrincipal());
		if(search.getSecurityCredentials() != null)
		env.put(Context.SECURITY_CREDENTIALS,
				search.getSecurityCredentials());

		// env.put(Context.INITIAL_CONTEXT_FACTORY,
		// "com.sun.jndi.ldap.LdapCtxFactory");
		// env.put(Context.PROVIDER_URL, "ldaps://ncids4a.nci.nih.gov:636");
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");

		// Search result = cn=KonkaPv,ou=6116: null:null:{mail=mail:
		// KonkaPv@mail.nih.gov,
		// sasdefaultloginsequence=sasDefaultLoginSequence: NDS, uid=uid:
		// KonkaPv, objectclass=objectClass: inetOrgPerson,
		// organizationalPerson, person, top, ndsLoginProperties, posixAccount,
		// givenname=givenName: Prasad, sn=sn: Konka, cn=cn: KonkaPv}
		// Search result name = cn=KonkaPv,ou=6116
		// Person Common Name = cn: KonkaPv
		// Person Given Name = givenName: Prasad
		// Person E-mail = mail: KonkaPv@mail.nih.gov
		// env.put(Context.SECURITY_PRINCIPAL, "uid=KonkaPv,ou=6116");
		// env.put(Context.SECURITY_CREDENTIALS, "");

		DirContext ctx = null;
		NamingEnumeration results = null;
		List<LDAPUser> users = null;
		try {
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			StringBuffer searchFilterStr = new StringBuffer();
			if(search.getFirstName() != null && search.getFirstName().trim().length() > 0)
				searchFilterStr.append("(givenname="+search.getFirstName()+")");
			else
				searchFilterStr.append("(givenname=*)");
			if(search.getLastName() != null && search.getLastName().trim().length() > 0)
				searchFilterStr.append("(sn="+search.getLastName()+")");
			else
				searchFilterStr.append("(sn=*)");
			if(search.getEmail() != null && search.getEmail().trim().length() > 0)
				searchFilterStr.append("(mail="+search.getEmail()+")");
			else
				searchFilterStr.append("(mail=*)");
			if(search.getLoginId() != null && search.getLoginId().trim().length() > 0)
				searchFilterStr.append("(uid="+search.getLoginId()+")");
			else
				searchFilterStr.append("(uid=*)");

			if(search.getSearchFilter() != null && search.getSearchFilter().trim().length() > 0)
				searchFilterStr.append(search.getSearchFilter());
			
			// results = ctx.search("ou=NCI,o=NIH", "(objectclass=user)",
			// controls);
			
			results = ctx.search("ou=NCI,o=NIH", "(&"+searchFilterStr.toString()+")",
						controls);
			
			if(results.hasMore())
				users = new ArrayList<LDAPUser>();
			int counter = 1;
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				
				users.add(buildUser(search, attributes));
				counter++;
			}
		} catch (Throwable e) {
			throw new LDAPImportException("Failed to search: "+e.getMessage());
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}
		return users;
	}

	private LDAPUser buildUser(LDAPSearch search, Attributes attributes) throws LDAPImportException {
		LDAPUser user = new LDAPUser();
		try {

			if (search.getMapping() != null
					&& search.getMapping().trim().length() > 0) {
				StringTokenizer tokenizer = new StringTokenizer(
						search.getMapping(), ",");
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					StringTokenizer attrMappingToken = new StringTokenizer(
							token, "=");
					String ldapAttr = attrMappingToken.nextToken();
					String userAttr = attrMappingToken.nextToken();
					if (userAttr.equalsIgnoreCase(LDAPUser.LOGINID))
						user.setUserLoginId((String) attributes.get(ldapAttr)
								.get());
					else if (userAttr.equalsIgnoreCase(LDAPUser.FIRSTNAME))
						user.setUserFirstName((String) attributes.get(ldapAttr)
								.get());
					else if (userAttr.equalsIgnoreCase(LDAPUser.LASTNAME))
						user.setUserLastName((String) attributes.get(ldapAttr)
								.get());
					if (userAttr.equalsIgnoreCase(LDAPUser.ORG))
						user.setUserOrganization((String) attributes.get(
								ldapAttr).get());
					if (userAttr.equalsIgnoreCase(LDAPUser.DEPT))
						user.setUserDepartment((String) attributes
								.get(ldapAttr).get());
					if (userAttr.equalsIgnoreCase(LDAPUser.TITLE))
						user.setUserTitle((String) attributes.get(ldapAttr)
								.get());
					if (userAttr.equalsIgnoreCase(LDAPUser.PHONE))
						user.setUserPhoneNumber((String) attributes.get(
								ldapAttr).get());
					if (userAttr.equalsIgnoreCase(LDAPUser.EMAIL))
						user.setUserEmailId((String) attributes.get(ldapAttr)
								.get());
				}

			} else {
				
				Attribute userIdAttr = attributes.get("uid");
				if(userIdAttr != null)
				{
					String userId = (String) userIdAttr.get();
					if (userId.indexOf(":") > 0)
						userId = userId.substring(userId.indexOf(":")+1);
					user.setUserLoginId(userId);
				}
				
				Attribute emailAttr = attributes.get("mail");
				if(emailAttr != null)
				{
					String email = (String) emailAttr.get();
					if (email.indexOf(":") > 0)
						email = email.substring(email.indexOf(":")+1);
					user.setUserEmailId(email);
				}
				Attribute givenNameAttr = attributes.get("givenname");
				if(givenNameAttr != null)
				{
					String givenName = (String) givenNameAttr.get();
					if (givenName.indexOf(":") > 0)
						givenName = givenName.substring(givenName.indexOf(":")+1);
					user.setUserFirstName(givenName);
				}

				Attribute lastNameAttr = attributes.get("sn");
				if(lastNameAttr != null)
				{
					String lastName = (String) lastNameAttr.get();
					if (lastName.indexOf(":") > 0)
						lastName = lastName.substring(lastName.indexOf(":")+1);
					user.setUserLastName(lastName);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
			throw new LDAPImportException("Failed to create LDAPUser based on mapping "+search.getMapping());
		}
		return user;
	}

	private List<String> validate() {
		List<String> errors = new ArrayList<String>();
		if (search == null) {
			errors.add("LDAP Search object is missing");
			return errors;
		}

		if (search.getSecurityAthentication() == null)
			errors.add("LDAP connection authentication type is missing");
		else if (!(search.getSecurityAthentication().equalsIgnoreCase(
				LDAPSearch.SECURITY_AUTHENTICATION_SIMPLE) || search
				.getSecurityAthentication().equalsIgnoreCase(
						LDAPSearch.SECURITY_AUTHENTICATION_NONE)))
			errors.add("LDAP connection authentication type is invalid. Valid types are simple, none");

		if (search.getProviderURL() == null
				|| search.getProviderURL().trim().length() == 0)
			errors.add("LDAP connection provider URL value is missing");

		if (search.getSecurityAthentication() == null  || search.getSecurityAthentication().trim().length() == 0)
			errors.add("LDAP connection security authentication type value is missing");
		
		if (search.getSecurityAthentication().equalsIgnoreCase(
				LDAPSearch.SECURITY_AUTHENTICATION_SIMPLE)
				&& (search.getSecurityPrincipal() == null || search
						.getSecurityPrincipal().trim().length() == 0))
			errors.add("LDAP connection security principal value is missing");
		if (search.getSecurityAthentication().equalsIgnoreCase(
				LDAPSearch.SECURITY_AUTHENTICATION_SIMPLE)
				&& (search.getSecurityCredentials() == null || search
						.getSecurityCredentials().trim().length() == 0))
			errors.add("LDAP connection security credential value is missing");

		if (search.getMapping() != null) {
			if (search.getMapping().indexOf(",") > 0) {
				StringTokenizer tokenizer = new StringTokenizer(
						search.getMapping(), ",");
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					if (token.indexOf("=") == -1) {
						errors.add("Invalid mapping. Valid mapping format is ldapattr1=csmattr1,ldapattr2=csmattr2");
						break;
					}
				}
			} else {
				errors.add("Invalid mapping. Valid mapping format is ldapattr1=csmattr1,ldapattr2=csmattr2");
			}
		}
		return errors;
	}
}
