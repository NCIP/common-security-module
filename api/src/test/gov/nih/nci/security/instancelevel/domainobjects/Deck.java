package test.gov.nih.nci.security.instancelevel.domainobjects;

import java.util.Collection;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table (name = "deck")
public class Deck  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	@Id
	private Integer id;
	@Column(name="name")
	private String name;
	@OneToMany
	@JoinColumn(name="DECK_ID",nullable=false)
	private Collection<Suit> suitCollection;
	/**
	* Retreives the value of id attribute
	* @return id
	**/

	public Integer getId(){
		return id;
	}

	/**
	* Sets the value of id attribue
	**/

	public void setId(Integer id){
		this.id = id;
	}
	
		/**
	* 	**/

	/**
	* Retreives the value of name attribute
	* @return name
	**/

	public String getName(){
		return name;
	}

	/**
	* Sets the value of name attribue
	**/

	public void setName(String name){
		this.name = name;
	}
	
	/**
	* An associated test.gov.nih.nci.security.instancelevel.domainobjectsSuit object's collection 
	**/
			

	/**
	* Retreives the value of suitCollection attribue
	* @return suitCollection
	**/

	public Collection<Suit> getSuitCollection(){
		return suitCollection;
	}

	/**
	* Sets the value of suitCollection attribue
	**/

	public void setSuitCollection(Collection<Suit> suitCollection){
		this.suitCollection = suitCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Deck) 
		{
			Deck c =(Deck)obj; 			 
			if(getId() != null && getId().equals(c.getId()))
				return true;
		}
		return false;
	}
		
	/**
	* Returns hash code for the primary key of the object
	**/
	public int hashCode()
	{
		if(getId() != null)
			return getId().hashCode();
		return 0;
	}
	
}