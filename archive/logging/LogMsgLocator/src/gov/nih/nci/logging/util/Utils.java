/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.util;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.util.*;
import java.text.*;

/**
 * Utility functions for basic operations.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class Utils
{

	public static final String DISPLAY_DATE_FORMAT = "MM/dd/yyyy , h:mm a";

	public static final SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT);

	/**
	 * Returns true if the object has a value. An empty string is treated is a
	 * non-value.
	 * 
	 * @param o
	 * @return
	 */
	public static boolean hasValue(Object o)
	{
		if (o == null)
		{
			return false;
		}
		;

		if (o instanceof Date)
		{
			// it is not null so return true
			return true;
		}

		if (o instanceof String)
		{
			if (((String) o).trim().length() > 0)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Parses a query date defaulting time if it was not set
	 * 
	 * @param date
	 * @param time
	 * @param isStartDate
	 * @return
	 */
	public static String formatDateForQuery(String date, String time, boolean isStartDate)
	{
		String convertedTime = "";
		try
		{
			if (!Utils.hasValue(time))
			{
				if (isStartDate)
				{
					time = "00:00 AM";
				}
				else
				{
					time = "11:59 PM";
				}
			}
			date += " , " + time;

			convertedTime = String.valueOf(sdf.parse(date).getTime());
		}
		catch (Exception ex)
		{
		}
		return convertedTime;

	}

	public static String clean(String str)
	{
		if (str == null || str.length() <= 0)
		{
			return "";
		}
		if (str.indexOf("'") < 0 && str.indexOf(",") < 0)
		{
			return str;
		}
		str = replace(str, "'", "''");
		str = replace(str, ",", " ");

		return str;

	}

	protected static String replace(String source, String find, String replacement)
	{
		int i = 0;
		int j;
		int k = find.length();
		int m = replacement.length();

		while (i < source.length())
		{
			j = source.indexOf(find, i);

			if (j == -1)
			{
				break;
			}

			source = replace(source, j, j + k, replacement);

			i = j + m;
		}

		return source;
	}

	protected static String replace(String source, int start, int end, String replacement)
	{
		if (start == 0)
		{
			source = replacement + source.substring(end);
		}
		else if (end == source.length())
		{
			source = source.substring(0, start) + replacement;
		}
		else
		{
			source = source.substring(0, start) + replacement + source.substring(end);
		}

		return source;
	}

}