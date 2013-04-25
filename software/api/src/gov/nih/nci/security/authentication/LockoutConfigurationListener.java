/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.authentication;

import org.apache.commons.configuration.event.ConfigurationErrorEvent;
import org.apache.commons.configuration.event.ConfigurationErrorListener;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;

public class LockoutConfigurationListener implements ConfigurationListener,ConfigurationErrorListener {

	@Override
	public void configurationChanged(ConfigurationEvent event) {

        if (!event.isBeforeUpdate())
        {
            // only display events after the modification was done
            System.out.println("Received event!");
            System.out.println("Type = " + event.getType());
            LockoutManager lockoutManager = LockoutManager.getInstance();
            if (event.getPropertyName() != null && event.getPropertyName().equalsIgnoreCase("PASSWORD_LOCKOUT_TIME"))
            {
            	System.out.println("getPropertyName!" + event.getPropertyName());
            	if (event.getPropertyValue() != null)
            	{
            		String[] st = (String[]) event.getPropertyValue();
            		System.out.println("st!" + st[0]);
            		lockoutManager.setLockoutTime((Long.parseLong(st[0]))) ;
            	}
            }
            if (event.getPropertyName() != null && event.getPropertyName().equalsIgnoreCase("ALLOWED_LOGIN_TIME"))
            {
            	System.out.println("getPropertyName!" + event.getPropertyName());
            	if (event.getPropertyValue() != null)
            	{
            		String[] st = (String[]) event.getPropertyValue();
            		System.out.println("st!" + st[0]);
            		lockoutManager.setAllowedLoginTime(Long.parseLong(st[0]));
            	}	
            }
            if (event.getPropertyName() != null && event.getPropertyName().equalsIgnoreCase("ALLOWED_ATTEMPTS"))
            {
            	System.out.println("getPropertyName!" + event.getPropertyName());
            	if (event.getPropertyValue() != null)
            	{
            		String[] st = (String[]) event.getPropertyValue();
            		System.out.println("st!" + st[0]);
            		lockoutManager.setAllowedAttempts( Integer.parseInt(st[0]));
            	}
            } 
            System.out.println("ALLOWED ATTEMPTS SET TO " + lockoutManager.getAllowedAttempts());
            LockoutManager lockoutManager2 = LockoutManager.getInstance();
            System.out.println("AGAIN ALLOWED ATTEMPTS SET TO " + lockoutManager2.getAllowedAttempts());
            
        
        }
	}
	
    public void configurationError(ConfigurationErrorEvent event)
    {
        System.out.println("An internal error occurred!");
        configurationChanged(event);
        event.getCause().printStackTrace();
    }

}
