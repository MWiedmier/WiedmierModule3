/**
 * @author Mandy Wiedmier - mwiedmier2
 * CIS175 - Spring 2024
 * Jan 23, 2024
 */

import java.util.List;
import java.util.Scanner;

import controller.FishHelper;
import model.Fish;

/**
 * 
 */
public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static FishHelper fih = new FishHelper();

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the species: ");
		String species = in.nextLine();
		System.out.print("Enter how many owned: ");
		int numFish = in.nextInt();
		Fish toAdd = new Fish(species, numFish);
		fih.insertItem(toAdd);
	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the species to delete: ");
		String species = in.nextLine();
		System.out.print("Enter the number of the species owned to delete: ");
		int numFish = in.nextInt();
		Fish toDelete = new Fish(species, numFish);
		fih.deleteItem(toDelete);
	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Species");
		System.out.println("2 : Search by numFish");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Fish> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the species name: ");
			String speciesName = in.nextLine();
			foundItems = fih.searchForFishBySpecies(speciesName);
			
		} else {
			System.out.print("Enter the number of species owned: ");
			int ownedNum = in.nextInt();
			foundItems = fih.searchForFishByNumberOfFish(ownedNum);

		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (Fish l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Fish toEdit = fih.searchForFishById(idToEdit);
			System.out.println("Retrieved " + toEdit.getNumOfFish() + " from " + toEdit.getSpecies());
			System.out.println("1 : Update Species");
			System.out.println("2 : Update Number of Fish");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Species: ");
				String newSpecies = in.nextLine();
				toEdit.setSpecies(newSpecies);
			} else if (update == 2) {
				System.out.print("New Number of Fish: ");
				int newNumberFish = in.nextInt();
				toEdit.setNumOfFish(newNumberFish);
			}

			fih.updateFish(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome fish list! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				fih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		List<Fish> allItems = fih.showAllItems();
		for(Fish singleItem : allItems) {
			System.out.println(singleItem.returnItemDetails());
		}
	}
}
