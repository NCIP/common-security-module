/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.instancelevel.domainobjects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table (name = "user")
@FilterDef(name="activatedFilter", parameters=@ParamDef( name="activatedParam", type="boolean" ))
@Filter(name="activatedFilter", condition=":activatedParam = activated")

public class TestUser
{
	@Id
    private int id;
	@Column(name="USERNAME") 
    private String username;
	@Column(name="ACTIVATED") 
    private boolean activated;
    
    
    public boolean isActivated()
    {
        return activated;
    }
    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
}
