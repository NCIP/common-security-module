/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.authentication.principal;

import java.security.Principal;

/**
 * Abstract Base class that implements the {@link Principal} interface. It provides basic common implementation for
 * all the principals in the CSM APIs.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 */
public abstract class BasePrincipal implements Principal
{

	private final String name;

	/**
	 * Constructor which accepts a {@link String} value and stores it withing the {@link Principal}
	 * @param name the string value which is to be stored withing the Principal
	 */
	public BasePrincipal(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("Null name");
		}
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	public String getName()
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "CSMPrincipal: " + name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj.getClass().isInstance(this)))
			return false;
		BasePrincipal another = (BasePrincipal) obj;
		return name.equals(another.getName());
	}

	/**
	 * @return the hashcode value of the stored {@link String} <code>name</code>
	 */
	public int hasCode()
	{
		return name.hashCode();
	}
}
