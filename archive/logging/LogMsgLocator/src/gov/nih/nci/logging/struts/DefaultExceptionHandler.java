/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.struts;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import org.apache.struts.action.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.http.*;
import org.apache.log4j.Category;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.action.ExceptionHandler;

/**
 * Class handles exceptions that are unhandled by normal processing.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */

public class DefaultExceptionHandler extends ExceptionHandler
{

	// private static final Category log = Category.getInstance(
	// DefaultExceptionHandler.class );

	public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance, HttpServletRequest request, HttpServletResponse response) throws ServletException
	{

		logError(ex);
		return super.execute(ex, ae, mapping, formInstance, request, response);

	}

	public static void logError(Exception ex)
	{
		String msg = "Exception was NULL";
		ex.printStackTrace();
		if (ex != null)
		{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(bo, true);
			ex.printStackTrace(pw);
			msg = "Unhandled Exception occured in Log Message Locator\n" + "Message: " + ex.getMessage() + "\n" + "StackTrace: " + bo.toString();
		}

		FileWriter writer = null;
		try
		{
			File f = new File("logMsgLocator" + System.currentTimeMillis() + ".log");
			writer = new FileWriter(f);
			writer.write(msg);
			writer.flush();

		}
		catch (Exception e)
		{
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (Exception e1)
			{
			}
		}

		// log.error( msg );

	}

}