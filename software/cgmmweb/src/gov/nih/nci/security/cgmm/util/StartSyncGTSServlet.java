package gov.nih.nci.security.cgmm.util;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import gov.nih.nci.cagrid.syncgts.core.SyncGTS;
import gov.nih.nci.security.cgmm.beans.CGMMInformation;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;

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
				String pathToSyncDescription = FileHelper.getFileAsURL(CGMMConstants.SYNCGTS_CONFIG_FILE).getPath();
				
				//Load Sync Description
				SyncDescription description = (SyncDescription) Utils.deserializeDocument(pathToSyncDescription,SyncDescription.class);

				// Sync with the Trust Fabric Once
				SyncGTS.getInstance().syncAndResyncInBackground(description, false);
			}
		}
		catch (Exception e) 
		{
			throw new ServletException ("Unable to Start Sync GTS Service.");
		}
		super.init(config);
	}
	
}
