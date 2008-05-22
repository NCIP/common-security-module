package gov.nih.nci.security.acegi.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * CSM Implementation of UserDetailsService uses 
 * 
 * 
 * @author parmarv
 *
 */
public class CSMUserDetailsService implements UserDetailsService {
	
	private String csmApplicationContext;
	
	
	protected AuthorizationManager authorizationManager;
	protected AuthenticationManager authenticationManager;
	
	public AuthorizationManager authorizationManagerInstance(){
		if(this.authorizationManager!=null){
			return this.authorizationManager;
		}else{
			try {
				this.authorizationManager = SecurityServiceProvider
						.getAuthorizationManager(this.csmApplicationContext);
			} catch (CSConfigurationException e) {
				e.printStackTrace();
				throw new DataRetrievalFailureException(e.getMessage());
			} catch (CSException e) {
				e.printStackTrace();
				throw new DataAccessResourceFailureException(e.getMessage());
			}
			return this.authorizationManager;
		}
	}
	
	public AuthenticationManager authenticationManagerInstance(){
		if(this.authenticationManager!=null){
			return this.authenticationManager;
		}else{
			try {
				this.authenticationManager = SecurityServiceProvider
						.getAuthenticationManager(this.csmApplicationContext);
			} catch (CSConfigurationException e) {
				e.printStackTrace();
				throw new DataRetrievalFailureException(e.getMessage());
			} catch (CSException e) {
				e.printStackTrace();
				throw new DataAccessResourceFailureException(e.getMessage());
			}
			return this.authenticationManager;
		}
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		
		//	Make sure AuthenticationManager Instance is available.
		authenticationManagerInstance();
		
		gov.nih.nci.security.authorization.domainobjects.User csmUser = 
			authorizationManagerInstance().getUser(username);
		
		if (csmUser == null)
			throw new UsernameNotFoundException("Unable to find user by the given user name");
		// Get All GrantedAuthorities from CSM.
		GrantedAuthority[] grantedAuthorities = getAuthorityCollection(csmUser.getUserId().toString()); 
				
		UserDetails userDetails = new CSMUserDetails(grantedAuthorities, csmUser.getPassword(),csmUser
						.getLoginName(), "Additional Data");

		return userDetails;
	}

	private GrantedAuthority[] getAuthorityCollection(String csmUserId) {

		Set pePrivContextSet = null;
		try {
			pePrivContextSet = authorizationManagerInstance().getProtectionElementPrivilegeContextForUser(csmUserId);
		} catch (CSObjectNotFoundException e) {
			throw new DataRetrievalFailureException("Could not retrieve Granted Authorities for user.");
			
		}
		
		Collection<GrantedAuthority> authorityCollection = new ArrayList<GrantedAuthority>();
		
		Iterator iter = pePrivContextSet.iterator();
		while(iter.hasNext()){
			ProtectionElementPrivilegeContext pepc = (ProtectionElementPrivilegeContext)iter.next();
			String peName = pepc.getProtectionElement().getProtectionElementName();
			Set privSet = pepc.getPrivileges();
			Iterator ite = privSet.iterator();
			while(ite.hasNext()){
				Privilege priv = (Privilege)ite.next();
				authorityCollection.add(new GrantedAuthorityImpl(peName+"_"+priv.getName().toUpperCase()));
			}
			
		}
		
		GrantedAuthority[] grantedAuthorities = new GrantedAuthorityImpl[authorityCollection.size()];
		Iterator it = authorityCollection.iterator();
		for(int i=0;i<authorityCollection.size();i++){
			grantedAuthorities[i]=(GrantedAuthority) it.next();
		}
		 
		return grantedAuthorities;
		
		
	}

	
	

	public AuthorizationManager getAuthorizationManager() {
		return authorizationManager;
	}

	public void setAuthorizationManager(AuthorizationManager authorizationManager) {
		this.authorizationManager = authorizationManager;
	}

	public String getCsmApplicationContext() {
		return csmApplicationContext;
	}

	public void setCsmApplicationContext(String csmApplicationContext) {
		this.csmApplicationContext = csmApplicationContext;
	}

}