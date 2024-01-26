/**
 * @author Mandy Wiedmier - mwiedmier2
 * CIS175 - Spring 2024
 * Jan 23, 2024
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="fish")
public class Fish {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="SPECIES")
	private String species;
	@Column(name="numoffish")
	private int numOfFish;
	
	//Constructors
	/**
	 * 
	 */
	public Fish() {
		super();
	}
	
	/**
	 * @param species
	 * @param numOfFish
	 */
	public Fish(String species, int numOfFish) {
		super();
		this.species = species;
		this.numOfFish = numOfFish;
	}

	//Getters and Setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the species
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * @return the numOfFish
	 */
	public int getNumOfFish() {
		return numOfFish;
	}

	/**
	 * @param numOfFish the numOfFish to set
	 */
	public void setNumOfFish(int numOfFish) {
		this.numOfFish = numOfFish;
	}

	//Helper Methods
	public String returnItemDetails() {
		return "Fish [id=" + id + ", species=" + species + ", numOfFish=" + numOfFish + "]";
	}
}
