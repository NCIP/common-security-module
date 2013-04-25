/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.application.persistence;

import java.util.List;



/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface DAOInterface {
            
	public Object createObject(Object val);
	
	public Object updateObject(Object val);
	
	public void removeObject(Object val);
	
	public void addChildren(Object parent, List children,String propertyName);
	
	public void removeChildren(Object parent, List children, String propertyName);
	
	public List getObjects(Object val);
}
