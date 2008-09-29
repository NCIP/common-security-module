package gov.nih.nci.security.cgmm.beans;

import java.io.Serializable;

import org.globus.gsi.GlobusCredential;

/**
 * This class represents the CGMM User. It is a transient object used to provide or receieve User details in CGMM.
 * The User details can include CSM as well as Grid user details.
 * 
 * @author Vijay Parmar
 *
 */
public class CGMMUser implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The loginName for Grid
	 */
	private String loginIDGrid;
	/**
	 * The loginName for CSM
	 */
	private String loginIDCSM;
	/**
	 * The password used to login into the CSM.
	 * 
	 */
	private String passwordCSM;
	/**
	 * The password used to login into the Grid.
	 * 
	 */
	private String passwordGrid;
	/**
	 * The first name of the user
	 */
	private String firstName;
	/**
	 * The last name of the user
	 */
	private String lastName;
	/**
	 * The name of the organization that this user belongs to.
	 */
	private String organization;
	/**
	 * The name of the department that this user belongs to.
	 */
	private String phoneNumber;
	
	/**
	 * Email id for this user.
	 */
	private String emailId;
	
	private byte migratedFlag;
	
	private GlobusCredential globusCredential;
	
	private String address1;
	private String address2;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	
	

	public CGMMUser(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * Email id for this user.
	 */
	public String getEmailId(){
		return emailId;
	}

	/**
	 * The first name of the user
	 */
	public String getFirstName(){
		return firstName;
	}

	/**
	 * The last name of the user
	 */
	public String getLastName(){
		return lastName;
	}

	/**
	 * The name of the organization that this user belongs to.
	 */
	public String getOrganization(){
		return organization;
	}

	/**
	 * The password used to login into the application
	 */
	public String getPasswordCSM(){
		return passwordCSM;
	}
	/**
	 * The password used to login into the application
	 */
	public String getPasswordGrid(){
		return passwordGrid;
	}
	/**
	 * This is the work phone of the user.
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}

	/**
	 * Email id for this user.
	 * @param newVal
	 * 
	 */
	public void setEmailId(final String newVal){
		emailId = newVal;
	}
	
	/**
	 * The first name of the user
	 * @param newVal
	 * 
	 */
	public void setFirstName(final String newVal){
		firstName = newVal;
	}

	/**
	 * The last name of the user
	 * @param newVal
	 * 
	 */
	public void setLastName(final String newVal){
		lastName = newVal;
	}

	/**
	 * The name of the organization that this user belongs to.
	 * @param newVal
	 * 
	 */
	public void setOrganization(final String newVal){
		organization = newVal;
	}

	/**
	 * The password used to login into the application
	 * @param newVal
	 * 
	 */
	public void setPasswordCSM(final String newVal){
		passwordCSM = newVal;
	}
	/**
	 * The password used to login into the application
	 * @param newVal
	 * 
	 */
	public void setPasswordGrid(final String newVal){
		passwordGrid = newVal;
	}

	/**
	 * This is the work phone of the user.
	 * @param newVal
	 * 
	 */
	public void setPhoneNumber(final String newVal){
		phoneNumber = newVal;
	}
	
	public byte getMigratedFlag() {
		return migratedFlag;
	}

	public void setMigratedFlag(final byte migratedFlag) {
		this.migratedFlag = migratedFlag;
	}

	

	public String getLoginIDCSM() {
		return loginIDCSM;
	}

	public void setLoginIDCSM(String loginIDCSM) {
		this.loginIDCSM = loginIDCSM;
	}

	public String getLoginIDGrid() {
		return loginIDGrid;
	}

	public void setLoginIDGrid(String loginIDGrid) {
		this.loginIDGrid = loginIDGrid;
	}

	public GlobusCredential getGlobusCredential() {
		return globusCredential;
	}

	public void setGlobusCredential(GlobusCredential globusCredential) {
		this.globusCredential = globusCredential;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		
		CGMMUser test = (CGMMUser)obj;

		return (loginIDGrid == test.loginIDGrid || (loginIDGrid != null && loginIDGrid.equals(test.loginIDGrid))) && 
			(loginIDCSM == test.loginIDCSM || (loginIDCSM != null && loginIDCSM.equals(test.loginIDCSM)));
	}
	
	public int hashCode(){
		
		int intNumber = 57 * 5;
		intNumber = intNumber + ( (null==loginIDCSM?0:loginIDCSM.hashCode()) + (null==loginIDGrid?0:loginIDGrid.hashCode()) );
		return intNumber;
	}

	
}
