package gov.nih.nci.security.authorization;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Category;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;





/**
 * @version 1.0
 * created 03-Dec-2004 1:17:48 AM
 */
public class AuthorizationManagerFactory {

	private static final Category log = Category.getInstance(AuthorizationManagerFactory.class);
	
	public AuthorizationManagerFactory(){

	}

	/**
	 * This methods instantiate an implementation of the {@link AuthorizationManager} and returns it to the calling method.
	 * It reads the config file using the Application Context/Name provided as parameter. If an entry is found,
	 * it retrieves the name of the class and instantiate an object of the same and returns it to the calling method.
	 * However if the entry is not found, then the default {@link UserProvisioningManagerImpl} Class is instantiated and 
	 * returned to the calling method
	 *
	 * The path for the application config file should be configured in the system properties file as shown below
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * e.g. gov.nih.nci.security.configFile=/foo/bar/ApplicationSecurityConfig.xml
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * Where <code>gov.nih.nci.security.configFile</code> is the property name and <code>/foo/bar/ApplicationSecurityConfig.xml</code> is the fully
	 * qualified file path. This configuration file contains which implementation of Authorization Manager is to be used
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * NOTE: that the application name/context should be same as those configured in the configuration/property files	 
	 * @return An instance of the class implementating the AuthorizationManager interface. This could the client custom
	 * implementation or the default provided Authorization Manager
	 * @throws CSException If there are any errors in obtaining the correct instance of the {@link AuthorizationManager}
	 */	

	public static AuthorizationManager getAuthorizationManager(String applicationContextName) throws CSException{

		Document configDoc = getConfigDocument();
		
		AuthorizationManager authorizationManager = null;
		String applicationManagerClassName = getAuthorizationManagerClass(applicationContextName);
		if (null == applicationManagerClassName || applicationManagerClassName.equals(""))
		{
			if (log.isDebugEnabled())
				log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Success|Initializing Common Authorization Manager|");
			authorizationManager = (AuthorizationManager)SecurityServiceProvider.getUserProvisioningManager(applicationContextName);
			authorizationManager.initialize(applicationContextName);
		}
		else
		{
			try
			{
				authorizationManager = (AuthorizationManager)(Class.forName(applicationManagerClassName)).newInstance();
				authorizationManager.initialize(applicationContextName);
				if (log.isDebugEnabled())
					log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Success|Initializing Custom Authorization Manager "+applicationManagerClassName+"|" );
			}
			catch (Exception exception)
			{
				if (log.isDebugEnabled())
					log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Failure| Error initializing Custom Authorization Manager "+applicationManagerClassName+"|" + exception.getMessage() );
				exception.printStackTrace();
				throw new CSException("Cannot initialize AuthorizationManager for the given application context", exception);
			}
			
		}
		return authorizationManager;

		
	}
	
	private static Document getConfigDocument() throws CSException{
		Document configDoc = null;
		try {
			String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
            SAXBuilder builder = new SAXBuilder();
            configDoc = builder.build(new File(configFilePath));
        } catch(Exception e) {
			if (log.isDebugEnabled())
				log.debug("Authorization|||getConfigDocument|Failure| Error reading the Config File |" + e.getMessage() );
			}
        return configDoc;
	}

	private static String getAuthorizationManagerClass(String applicationContextName) throws CSException{
		String authorizationProviderClassName = null;
		Document configDocument;
		try {
			configDocument = getConfigDocument();
		} catch (CSException cse) {
			if (log.isDebugEnabled())
				log.debug("Authorization|||getAuthorizationManagerClass|Failure| Error reading the Config File |" + cse.getMessage() );
			throw new CSException("Error reading the Application Security Config File", cse);			
		}
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = securityConfig.getChildren("application");
		 Iterator appIterator  = applications.iterator();
		 while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element contextName = application.getChild("context-name");
		 	String contextNameValue = contextName.getText().trim();
			if(contextNameValue.equalsIgnoreCase(applicationContextName)){
				Element authorization = application.getChild("authorization");
				Element authorizationProviderClass = authorization.getChild("authorization-provider-class");
				authorizationProviderClassName = authorizationProviderClass.getText().trim();
			}
		 }
			if (log.isDebugEnabled())
				log.debug("Authorization|||getAuthorizationManagerClass|Success| Read the authorization Class Name " );
		 return authorizationProviderClassName;
	}

}
