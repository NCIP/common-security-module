/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.prototype.domainobjects;

import java.io.Serializable;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Customer implements Serializable{
   private Long id;
   private String name;
   private java.util.Set orders;
   private Account account;
   
   
   
/**
 * @return Returns the account.
 */
public Account getAccount() {
	return account;
}
/**
 * @param account The account to set.
 */
public void setAccount(Account account) {
	this.account = account;
}
/**
 * @return Returns the orders.
 */
public java.util.Set getOrders() {
	return orders;
}
/**
 * @param orders The orders to set.
 */
public void setOrders(java.util.Set orders) {
	this.orders = orders;
}
/**
 * @return Returns the id.
 */
public Long getId() {
	return id;
}
/**
 * @param id The id to set.
 */
public void setId(Long id) {
	this.id = id;
}
/**
 * @return Returns the name.
 */
public String getName() {
	return name;
}
/**
 * @param name The name to set.
 */
public void setName(String name) {
	this.name = name;
}
}
