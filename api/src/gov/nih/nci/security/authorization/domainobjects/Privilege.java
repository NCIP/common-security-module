package gov.nih.nci.security.authorization.domainobjects;







/**
 * This class is used to define the operations and different access levels which
 * can be associated with a user. e.g "Create", "Read", "Update", "Delete" etc..
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class Privilege {

	

	/**
	 * This is the name of the privilege.
	 */
	private String name;
	/**
	 * It gives the brief description about the privilege.
	 */
	private String desc;
	/**
	 * It is the unique id by for this privilege.
	 */
	private Long id;
	
	private java.util.Date updateDate;

	public Privilege(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * It gives the brief description about the privilege.
	 */
	public String getDesc(){
		return desc;
	}

	/**
	 * It is the unique id by for this privilege.
	 */
	public Long getId(){
		
		return id;
	}

	/**
	 * This is the name of the privilege.
	 */
	public String getName(){
		return name;
	}

	/**
	 * It gives the brief description about the privilege.
	 * @param newVal
	 * 
	 */
	public void setDesc(String newVal){
		desc = newVal;
	}

	/**
	 * It is the unique id by for this privilege.
	 * @param newVal
	 * 
	 */
	public void setId(Long newVal){
		id = newVal;
	}

	/**
	 * This is the name of the privilege.
	 * @param newVal
	 * 
	 */
	public void setName(String newVal){
		name = newVal;
	}
	
	/**
	 * The date when the privilege information was updated
	 */
	public java.util.Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * The date when the privilege information was updated
	 */
	public void setUpdateDate(java.util.Date newVal){
		this.updateDate= newVal;
	}
	
	/**
	 * @param obj
	 * 
	 */
	public boolean equals(Object obj){
		Privilege other = (Privilege)obj;
		if(this.id.toString().equals(other.getId().toString())){
			return true;
		}else{
		return false;
		}
	}

}