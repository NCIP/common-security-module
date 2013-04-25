/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.instancelevel.domainobjects;


import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;




@Entity
@Table (name = "card")
@FilterDef(name="cardNameFilter", parameters=@ParamDef( name="nameParam", type="string" ))
@Filter(name="cardNameFilter", condition=":nameParam = name")

public class Card  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	@Id
	private Integer id;
	
	@Column(name="IMAGE") 
	private String image;
	
	@Column(name="NAME")
	private String Name;
	
	@ManyToOne
	@JoinColumn(name="suit_id", insertable=false,updatable=false)
	private Suit suit;
	
	
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
	* Retreives the value of image attribute
	* @return image
	**/

	public String getImage(){
		return image;
	}

	/**
	* Sets the value of image attribue
	**/

	public void setImage(String image){
		this.image = image;
	}
	
	
	/**
	* Retreives the value of Name attribute
	* @return Name
	**/

	public String getName(){
		return Name;
	}

	/**
	* Sets the value of Name attribue
	**/

	public void setName(String Name){
		this.Name = Name;
	}


	/**
	* Retreives the value of suit attribue
	* @return suit
	**/
	
	public Suit getSuit(){
		return suit;
	}
	/**
	* Sets the value of suit attribue
	**/

	public void setSuit(Suit suit){
		this.suit = suit;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Card) 
		{
			Card c =(Card)obj; 			 
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