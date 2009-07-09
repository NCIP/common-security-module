package gov.nih.nci.security.cgmm.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import gov.nih.nci.cagrid.syncgts.core.SyncGTS;
import gov.nih.nci.security.cgmm.beans.CGMMInformation;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.helper.impl.CGMMMessages;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;






public class StartSyncGTSServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		try
		{
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			@SuppressWarnings("unused")
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			CGMMInformation cgmmInformation = cgmmProperties.getCGMMInformation();

			if (CGMMConstants.TRUE.equalsIgnoreCase(cgmmInformation.getStartAutoSyncGTS()))
			{
				String pathToSyncDescription = "sync-description.xml";
				Properties props = System.getProperties();
				if(props.get(CGMMConstants.CGMM_SYNCGTS_FILE)!=null){
					pathToSyncDescription = (String)props.get(CGMMConstants.CGMM_SYNCGTS_FILE);
				}
				
				File f = null;
				URL url = null;
				if(!StringUtils.isBlankOrNull(pathToSyncDescription)){
					try {
						f = new File(pathToSyncDescription);
						if(f!=null){
							
							if(!f.exists()){
								throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_SYNCGTS_FILE);
							}
							
							url = f.toURL();
						}
			
					} catch (MalformedURLException e) {
						throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_SYNCGTS_FILE);
					}
				}
				if(url==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_SYNCGTS_FILE);
				
				
				//Load Sync Description
				SyncDescription description = (SyncDescription) Utils.deserializeDocument(pathToSyncDescription,SyncDescription.class);

				// Sync with the Trust Fabric Once
				SyncGTS.getInstance().syncAndResyncInBackground(description, false);
			}
		}
		catch (Exception e) 
		{
			throw new ServletException ("Unable to Start Sync GTS Service.",e);
		}
		super.init(config);
	}
	
}
