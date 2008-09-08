package gov.nih.nci.security.cgmm.beans;

import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;

import java.io.Serializable;

public class DorianInformation implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String dorianServiceURL = null;
	
	private int proxyLifetimeHours = 12;
	
	private int proxyLifetimeMinutes = 0;
	
	private int proxyLifetimeSeconds = 0;
	
	private int delegationPathLength = 0;

	public String getDorianServiceURL()
	{
		return dorianServiceURL;
	}

	public void setDorianServiceURL(String dorianServiceURL)
	{
		this.dorianServiceURL = dorianServiceURL;
	}

	public int getProxyLifetimeHours()
	{
		return proxyLifetimeHours;
	}

	public void setProxyLifetimeHours(int proxyLifetimeHours)
	{
		this.proxyLifetimeHours = proxyLifetimeHours;
	}

	public int getProxyLifetimeMinutes()
	{
		return proxyLifetimeMinutes;
	}

	public void setProxyLifetimeMinutes(int proxyLifetimeMinutes)
	{
		this.proxyLifetimeMinutes = proxyLifetimeMinutes;
	}

	public int getProxyLifetimeSeconds()
	{
		return proxyLifetimeSeconds;
	}

	public void setProxyLifetimeSeconds(int proxyLifetimeSeconds)
	{
		this.proxyLifetimeSeconds = proxyLifetimeSeconds;
	}

	public int getDelegationPathLength()
	{
		return delegationPathLength;
	}

	public void setDelegationPathLength(int delegationPathLength)
	{
		this.delegationPathLength = delegationPathLength;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public ProxyLifetime getProxyLifeTime()
	{
		ProxyLifetime proxyLifetime = new ProxyLifetime();
		proxyLifetime.setHours(this.getProxyLifetimeHours());
		proxyLifetime.setMinutes(this.getProxyLifetimeMinutes());
		proxyLifetime.setSeconds(this.getProxyLifetimeSeconds());
		return proxyLifetime;
	}
}
