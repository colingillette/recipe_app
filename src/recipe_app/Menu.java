package recipe_app;

import java.util.*;

public class Menu {
	
	// Properties
	String step;
	int instruction;
	
	// Constructor
	public Menu() {
		step = "start";
	}
	
	// Runs on application initialization. Returns command for what to do next
	public int start() {
		String input;
		Scanner scanner = new Scanner(System.in);
		int selection = 99;
		
		// Run this menu until a valid choice is made
		do {
			System.out.println("Welcome to the Recipe App!");
			System.out.println("Please type the number associated with the choice you would like to make.");
			System.out.println();
			System.out.println("(1) Create Recipe");
			System.out.println("(2) Search Recipes by Name");
			System.out.println("(3) Search Recipes by Category");
			System.out.println("(4) Search Recipes by Ingredient");
			System.out.println("(5) Search Recipes by Tag");
			System.out.println("(0) Quit Program");
			
			input = scanner.next();
			
			try {
				selection = Integer.parseInt(input);
				if (selection < 0 || selection > 5) {
					System.out.println("The number you entered is not one of the choices. Please try again.");
					selection = 99;
				}
			} 
			catch (Exception e) {
				System.out.println();
				System.out.println("Something was wrong with your input. Please type only the number of your selection.");
			}
		} while (selection == 99); // end do while loop
		
		scanner.close();
		return selection;
	}
	
	// getters and setters
	public String get_step() {
		return this.step;
	}
	public int get_instruction() {
		return this.instruction;
	}
	public void set_step(String step_in) {
		this.step = step_in;
	}
	public void set_instruction(int instruction_in) {
		this.instruction = instruction_in;
	}
}
