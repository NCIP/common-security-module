package gov.nih.nci.security.migrate.webapp.viewobjects;


public class ServerObject
{
	
	private String name;

	
	public ServerObject(String name)
	{
		this.name = name;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}

	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

}
