/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.acegi.sdk;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

public interface ApplicationService {
    public int getValue();
    public void setValue(int _value);
    public void changeValue(int _value);
    
    public Collection search(Criteria c);
    public List search(Class targetClass, Object obj);
    public List search(Object o1, Object o2);
    public List search(Object o1, Object o2, Object o3);
}