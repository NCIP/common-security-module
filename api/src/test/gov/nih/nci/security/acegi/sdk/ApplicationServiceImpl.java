/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.acegi.sdk;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import test.gov.nih.nci.security.acegi.AcegiTestConstants;
import test.gov.nih.nci.security.acegi.sdk.domain.DeniedObject;
import test.gov.nih.nci.security.acegi.sdk.domain.Gene;
import test.gov.nih.nci.security.acegi.sdk.domain.Taxon;

public class ApplicationServiceImpl implements ApplicationService {

    private int value = 0;

    public ApplicationServiceImpl() {
        super();
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int _value) {
        this.value = _value;
    }

    public void changeValue(int _value) {
        this.value += _value;
    }

	public Collection search(Criteria c) {

		return getResultCollection();
	}
	
	public List search(Class targetClass, Object obj) {
		return (List) getResultCollection();
	}
	
	public List search(Object o1, Object o2) {
		return (List) getResultCollection();
	}
	
	public List search(Object o1, Object o2, Object o3) {
		return (List) getResultCollection();
	}
	
	private Collection getResultCollection(){
		Collection c = new ArrayList();
		for(int i=0;i<AcegiTestConstants.DESIRED_OBJECTS_SET;i++){
			c.add(new Gene("Gene"+i));
			c.add(new Taxon("T"+i));
			c.add(new DeniedObject("N"+i));
		}
		
		
		return c;
	}

	

	

}