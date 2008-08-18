package gov.nih.nci.security.migrate.util;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import gov.nih.nci.cagrid.syncgts.core.SyncGTS;
import gov.nih.nci.security.migrate.beans.CGMMInformation;
import gov.nih.nci.security.migrate.constants.CGMMConstants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;






public class StartSyncGTSServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		try
		{
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			CGMMInformation cgmmInformation = CGMMProperties.getCGMMInformation();

			if ("yes".equalsIgnoreCase(cgmmInformation.getStartAutoSyncGTS()))
			{
				FileHelper fileHelper = (FileHelper)ObjectFactory.getObject(CGMMConstants.FILE_HELPER);
			
				String pathToSyncDescription = fileHelper.getFileAsURL("sync-description.xml").getPath();
				
				//Load Sync Description
				SyncDescription description = (SyncDescription) Utils.deserializeDocument(pathToSyncDescription,SyncDescription.class);

				// Sync with the Trust Fabric Once
				SyncGTS.getInstance().syncAndResyncInBackground(description, false);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException ("Unable to Start Sync GTS Service.");
		}
		super.init(config);
	}
	
}
