package gov.nih.nci.security.authorization.domainobjects;

import java.util.Date;





/**
 * This class defines the security role object in context of an application. The
 * roles are collection of privileges.
 * @version 1.0
 * created 03-Dec-2004 1:17:51 AM
 */
public class Role {

	/**
	 * This is the name of the role. This can be any user friendly name to address
	 * business needs.
	 */
	private String name;
	/**
	 * This attribute describes the role in detail.
	 */
	private String desc;
	/**
	 * This is the unique id by which this role can be identied  for an application.
	 */
	private Long id;
	/**
	 * Indicates if the role is active or not.
	 */
	private byte active_flag;
	/**
	 * A collection of Privilege objects. Indicates which privileges belong to this
	 * role.
	 */
	private java.util.Set privileges;
	/**
	 * The application to which the role belongs
	 */
	private Application application;
	/**
	 * The date when the role information was last updated
	 */
	private java.util.Date updateDate;

	public Role(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * Indicates if the role is active or not.
	 */
	public byte getActive_flag(){
		return active_flag;
	}

	/**
	 * The application to which the role belongs
	 */
	public Application getApplication(){
		return application;
	}

	/**
	 * This attribute describes the role in detail.
	 */
	public String getDesc(){
		return desc;
	}

	/**
	 * This is the unique id by which this role can be identied  for an application.
	 */
	public Long getId(){
		return id;
	}

	/**
	 * This is the name of the role. This can be any user friendly name to address
	 * business needs.
	 */
	public String getName(){
		return name;
	}

	/**
	 * A collection of Privilege objects. Indicates which privileges belong to this
	 * role.
	 */
	public java.util.Set getPrivileges(){
		return privileges;
	}

	/**
	 * The date when the role information was last updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * Indicates if the role is active or not.
	 * @param newVal
	 * 
	 */
	public void setActive_flag(byte newVal){
		active_flag = newVal;
	}

	/**
	 * The application to which the role belongs
	 * @param newVal
	 * 
	 */
	public void setApplication(Application newVal){
		application = newVal;
	}

	/**
	 * This attribute describes the role in detail.
	 * @param newVal
	 * 
	 */
	public void setDesc(String newVal){
		desc = newVal;
	}

	/**
	 * This is the unique id by which this role can be identied  for an application.
	 * @param newVal
	 * 
	 */
	public void setId(Long newVal){
		id = newVal;
	}

	/**
	 * This is the name of the role. This can be any user friendly name to address
	 * business needs.
	 * @param newVal
	 * 
	 */
	public void setName(String newVal){
		name = newVal;
	}

	/**
	 * A collection of Privilege objects. Indicates which privileges belong to this
	 * role.
	 * @param newVal
	 * 
	 */
	public void setPrivileges(java.util.Set newVal){
		privileges = newVal;
	}

	/**
	 * The date when the role information was last updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}
	
	public boolean equals(Object obj){
		Role other = (Role)obj;
		if(this.id.toString().equals(other.getId().toString())){
			return true;
		}else{
		return false;
		}
	}

}