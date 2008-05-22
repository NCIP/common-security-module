package gov.nih.nci.security.util;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;

import java.util.Comparator;

public class ProtectionElementPrivilegesContextComparator  implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		ProtectionElementPrivilegeContext p1 = (ProtectionElementPrivilegeContext)o1;
		ProtectionElementPrivilegeContext p2 = (ProtectionElementPrivilegeContext)o2;
		return p1.getProtectionElement().getProtectionElementName().compareToIgnoreCase(p2.getProtectionElement().getProtectionElementName());
	}
}