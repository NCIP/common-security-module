/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.instancelevel.domainobjects;

import java.util.Collection;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import javax.persistence.Id;
	

@Entity
@Table (name = "suit")
public class Suit  implements Serializable
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
	@JoinColumn(name="suit_id",nullable=false)
	private Collection<Card> cardCollection;
	@ManyToOne
	@JoinColumn(name="deck_id", insertable=false,updatable=false, nullable=false)
	private Deck deck;
	
	
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
	* An associated test.gov.nih.nci.security.instancelevel.domainobjects.Card object's collection 
	**/
			
	
	/**
	* Retreives the value of cardCollection attribue
	* @return cardCollection
	**/

	public Collection<Card> getCardCollection(){
		return cardCollection;
	}

	/**
	* Sets the value of cardCollection attribue
	**/

	public void setCardCollection(Collection<Card> cardCollection){
		this.cardCollection = cardCollection;
	}
		
	/**
	* An associated test.gov.nih.nci.security.instancelevel.domainobjects.Deck object
	**/
			
	
	/**
	* Retreives the value of deck attribue
	* @return deck
	**/
	
	public Deck getDeck(){
		return deck;
	}
	/**
	* Sets the value of deck attribue
	**/

	public void setDeck(Deck deck){
		this.deck = deck;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Suit) 
		{
			Suit c =(Suit)obj; 			 
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