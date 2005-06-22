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
public class Item implements Serializable{
  private Long id;
  private String name;
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
