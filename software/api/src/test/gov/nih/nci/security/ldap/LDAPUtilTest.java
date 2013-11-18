package test.gov.nih.nci.security.ldap;

import static org.junit.Assert.*;

import java.util.List;

import gov.nih.nci.security.ldap.LDAPSearch;
import gov.nih.nci.security.ldap.LDAPSearchUtil;
import gov.nih.nci.security.ldap.LDAPUser;

import org.junit.Test;

public class LDAPUtilTest {

	@Test
	public void testSearch() {
		//attributes: {mail=mail: clevelal@mail.nih.gov, ifolderservername=iFolderServerName: , objectclass=objectClass: inetOrgPerson, organizationalPerson, person, top, ndsLoginProperties, iFolderUser, nadUser, givenname=givenName: Linda, sn=sn: Cleveland, initials=initials: S, cn=cn: ClevelaL}

		//(&(sn=Geisel)(mail=*))
		LDAPSearch search = new LDAPSearch();
		search.setProviderURL("ldaps://ncids4a.nci.nih.gov:636");
		search.setSecurityAthentication("none");
		//search.setSearchFilter("(&(mail=clevelal@mail.nih.gov)(sn=Cleveland))");
		//search.setEmail("clevelal@mail.nih.gov");
		//search.setLastName("Cleveland");
		search.setFirstName("Linda");		
		//search.setLastName("Konka");
		LDAPSearchUtil util = new LDAPSearchUtil(search);
		List<LDAPUser> users = util.getUsers();
		if(users == null || users.size() == 0)
			fail("Failed to query users");
		
		System.out.println("size: "+users.size());
	}
}
