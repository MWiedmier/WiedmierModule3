/**
 * @author Mandy Wiedmier - mwiedmier2
 * CIS175 - Spring 2024
 * Jan 23, 2024
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Fish;

/**
 * 
 */
public class FishHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Module3Wiedmier");
	
	public void insertItem(Fish fi) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(fi);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Fish> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<Fish> allItems = em.createQuery("SELECT i FROM Fish i").getResultList();
		return allItems;
	}
	
	public void deleteItem(Fish toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Fish> typedQuery = em.createQuery("SELECT fi FROM Fish fi WHERE fi.species = :selectedSpecies and fi.numOfFish = :selectNumOfFish", Fish.class);
		//Substitute parameter with	actual data from the toDelete item
		typedQuery.setParameter("selectedSpecies",	toDelete.getSpecies());
		typedQuery.setParameter("selectNumOfFish",	toDelete.getNumOfFish());
		//we only want one result
		typedQuery.setMaxResults(1);
		//get the result and save it into a new list item
		Fish result = typedQuery.getSingleResult();
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public Fish searchForFishById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Fish found = em.find(Fish.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateFish(Fish toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param speciesName
	 * @return
	 */
	public List<Fish> searchForFishBySpecies(String speciesName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Fish> typedQuery = em.createQuery("SELECT fi FROM Fish fi WHERE fi.species = :selectedSpecies", Fish.class);
		typedQuery.setParameter("selectedSpecies", speciesName);
		List<Fish> foundFish = typedQuery.getResultList();
		em.close();
		return foundFish;
	}

	/**
	 * @param ownedNum
	 * @return
	 */
	public List<Fish> searchForFishByNumberOfFish(int ownedNum) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Fish> typedQuery = em.createQuery("SELECT fi FROM Fish fi WHERE fi.numOfFish = :selectedNumber", Fish.class);
		typedQuery.setParameter("selectedNumber", ownedNum);
		List<Fish> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
