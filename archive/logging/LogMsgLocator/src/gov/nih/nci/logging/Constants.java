/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * Constants Interface for storing Database column names and struts session
 * variable names.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */
public interface Constants
{

	public static final String OID = "OID";
	public static final String APPLICATION = "APPLICATION";
	public static final String SERVER = "SERVER";
	public static final String PRIORITY = "PRIORITY";
	public static final String CATEGORY = "CATEGORY";
	public static final String THREAD = "THREAD";
	public static final String USER = "USER";
	public static final String SESSIONID = "SESSIONID";
	public static final String MSG = "MSG";
	public static final String THROWABLE = "THROWABLE";
	public static final String NDC = "NDC";
	public static final String LOGTAB = "LOGTAB";
	public static final String CREATED_ON = "CREATED_ON";

	public static final String SUMMARY_LIST = "SUMMARY_LIST";
	public static final String DETAIL_LIST = "DETAIL_LIST";
	public static final String ACTION_SUCCESS = "success";
	public static final String ACTION_FAILURE = "failure";
	public static final String INVALID_LOGIN = "error.user.notfound";

}