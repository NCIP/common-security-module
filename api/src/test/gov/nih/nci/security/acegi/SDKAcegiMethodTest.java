package test.gov.nih.nci.security.acegi;
 
import java.util.Collection;

import junit.framework.TestCase;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.hibernate.Criteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.gov.nih.nci.security.acegi.sdk.ApplicationService;

/**
 * 
 * Requires: via UPT.
 * 	-an application provisioned for a user 'manager' with password 'manager'.
 *  - User has access to 2 PE's. 
 *  	Namely  'test.gov.nih.nci.security.acegi.sdk.domain.Gene' and 
 *  	'test.gov.nih.nci.security.acegi.sdk.domain.Taxon'
 *  - The privileges for each PE must include READ. 
 *  - For testing try removing or adding other privileges - WRITE for example.
 * Requires acegitest.csm.new.hibernate.cfg.xml in classpath
 * Requires csm-acegi-security.xml in classpath.
 * 
 * @author parmarv
 *
 */
public class SDKAcegiMethodTest extends TestCase {

    // Read the Spring configuration file. 
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("csm-acegi-security.xml");

    private static void createSecureContext(final ApplicationContext ctx, final String username, final String password) {
        AuthenticationProvider provider = (AuthenticationProvider) ctx.getBean("authenticationProvider");
        Authentication auth = provider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // Clear the security context after each test.
    /**
     * 
     */
    public void teardown() {
        SecurityContextHolder.setContext(new SecurityContextImpl());
    }
    

    public void testManagerAccess1() {
        createSecureContext(ctx, "manager", "manager");
        Criteria c= null;
        
        Collection col = ((ApplicationService) ctx.getBean("applicationService")).search(c);
        if(AcegiTestConstants.DESIRED_OBJECTS_SET*3 - col.size() == 0){
        	System.out.println("Result is not filtered. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }else{
        	System.out.println("Results filtered!. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }
    }
    public void testManagerAccess2() {
        createSecureContext(ctx, "manager", "manager");
        Object o2= new Object();
        Object o = new Object();
        
        Collection col = ((ApplicationService) ctx.getBean("applicationService")).search(o.getClass(),o2);
        if(AcegiTestConstants.DESIRED_OBJECTS_SET*3 - col.size() == 0){
        	System.out.println("Result is not filtered. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }else{
        	System.out.println("Results filtered!. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }
        
        
    }
    
    public void testManagerAccess3() {
        createSecureContext(ctx, "manager", "manager");
        Object o2= new Object();
        Object o3= new Object();
        Object o = new Object();
        
        Collection col = ((ApplicationService) ctx.getBean("applicationService")).search(o.getClass(),o2,o3);
        if(AcegiTestConstants.DESIRED_OBJECTS_SET*3 - col.size() == 0){
        	System.out.println("Result is not filtered. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }else{
        	System.out.println("Results filtered!. Expected "+AcegiTestConstants.DESIRED_OBJECTS_SET*3+", got: "+col.size());
        }
    }
    

}