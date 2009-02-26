package gov.nih.nci.security.cgmm.loginmodules;
import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimplePrincipal;

public class NullPasswordStackingLoginModule 
  implements javax.security.auth.spi.LoginModule
{
    private Subject subject;
    private CallbackHandler callbackHandler;

    private Map sharedState;
    private Map options;

    private String username = null;

    // Saves the state of phase 1, login().
    private boolean loginOk = false;  

    private SimplePrincipal usernameprincipal;
    private Object password;

    public void initialize( Subject subject, 
			    CallbackHandler callbackHandler, 
			    Map sharedState, 
			    Map options)
    {
    	
	this.subject = subject;
	this.callbackHandler = callbackHandler;
	this.sharedState = sharedState;
	this.options = options;
    }

    public boolean login() throws LoginException
    {
    	 NameCallback nameCallback = new NameCallback("Username");
    	    PasswordCallback passwordCallback = new PasswordCallback("Password", false);

    	    Callback[] callbacks = new Callback[]{nameCallback, passwordCallback};
    	    try {
				callbackHandler.handle(callbacks);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedCallbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	    username = nameCallback.getName();
    	    char[] password = passwordCallback.getPassword();
    	    passwordCallback.clearPassword();

//    	  For JBoss - useFirstPass option
    		sharedState.put("javax.security.auth.login.name", username);
            sharedState.put("javax.security.auth.login.password", password);
            
    	loginOk = true;
    	return true;

    }

    public boolean commit() throws LoginException
    {
    	 if ( ! loginOk )
    			return false;

    		    usernameprincipal = new SimplePrincipal( username );
    		    password = new String(password.toString());

    		    subject.getPrincipals().add( usernameprincipal );
    		    subject.getPublicCredentials().add( password );
    		    subject.getPrivateCredentials().add(password);
    		    //this.username = null;
    		    return true;
 
    }

    public boolean abort() throws LoginException
    {
     return true;
    }

    public boolean logout() throws LoginException
    {
     return true;
    }
}