/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.gov.nih.nci.sdk.codegen;

import java.util.List;

import gov.nih.nci.sdk.common.ApplicationException;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface WritableApplicationService {

	public Object createObject(Object obj) throws ApplicationException;
	
	public Object updateObject(Object obj) throws ApplicationException;
	
	public void removeObject(Object obj) throws ApplicationException;
	
	public List getObjects(Object obj) throws ApplicationException;
}
