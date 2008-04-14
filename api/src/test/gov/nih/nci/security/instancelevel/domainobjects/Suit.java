package test.gov.nih.nci.security.instancelevel.domainobjects;

import java.util.Collection;
import java.io.Serializable;
	/**
	* 	**/
public class Suit  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* 	**/
	private Integer id;
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
	private String name;
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
			
	private Collection<Card> cardCollection;
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
			
	private Deck deck;
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