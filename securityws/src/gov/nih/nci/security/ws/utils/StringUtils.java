package gov.nih.nci.security.ws.utils;


public class StringUtils
{

	/**
	 * @param string 
	 * @param args
	 * @return 
	 */
	public static boolean isNullOrBlank(String string)
	{
		if (string == null || string.trim().equals(""))
			return true;
		return false;

	}

}
