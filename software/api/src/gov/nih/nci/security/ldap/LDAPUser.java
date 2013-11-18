package gov.nih.nci.security.ldap;

public class LDAPUser {
	private String userLoginId;
	private String userFirstName;
	private String userLastName;
	private String userOrganization;
	private String userDepartment;
	private String userTitle;
	private String userPhoneNumber;
	private String userEmailId;

	public static String LOGINID = "UserId";
	public static String FIRSTNAME = "FirstName";
	public static String LASTNAME = "LastName";
	public static String ORG = "Org";
	public static String DEPT = "Dept";
	public static String TITLE = "Title";
	public static String PHONE = "Phone";
	public static String EMAIL = "Email";
	
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserOrganization() {
		return userOrganization;
	}
	public void setUserOrganization(String userOrganization) {
		this.userOrganization = userOrganization;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public String getUserTitle() {
		return userTitle;
	}
	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
}
