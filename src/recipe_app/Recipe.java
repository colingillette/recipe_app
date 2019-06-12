package recipe_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class Recipe {
	String name;
	String category;
	HashMap<Integer, String> directions; // Step #, Step details
	HashMap<String, String> ingredients; // Ingredient, Amount of ingredient
	String[] tags;
	
	// Constructor
	public Recipe() {
		this.directions = new HashMap<Integer, String>();
		this.ingredients = new HashMap<String, String>();
	}
	
	// Add Direction
	public boolean add_direction(int step_in, String details_in) {
		if (!this.directions.containsKey(step_in)) {
			this.directions.put(step_in, details_in);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Add Ingredient
	public boolean add_ingredient(String ingredient_in, String amount_in) {
		if (!this.ingredients.containsKey(ingredient_in)) {
			this.ingredients.put(ingredient_in, amount_in);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Static method to initiate recipe creation
	public static boolean createRecipe() {
		Scanner scanner = new Scanner(System.in);
		Recipe recipe = new Recipe();
		
		// Get general info
		System.out.println();
		System.out.println("Welcome to the Recipe Creator!");
		System.out.println("What is the name of your recipe?");
		recipe.set_name(scanner.next());
		System.out.println("Which of the following categories is it?");
		recipe.set_category();
		System.out.println("Please list tags for this item. Seperate each tag by pressing the enter key. Enter the number '0' when you are done.");
		recipe.set_tags();
		
		// Get Ingredients
		int finished_ingredients = 1;
		while (finished_ingredients == 1) {
			String ing;
			String amt;
			System.out.println("Please enter an ingredient or enter 0 to stop adding ingredients.");
			ing = scanner.next();
			if (ing == "0") {
				finished_ingredients = 0;
			}
			else {
				System.out.println("Please enter the amount of that ingredient needed in this recipe.");
				amt = scanner.next();
				if (recipe.add_ingredient(ing, amt)) {
					System.out.println("Ingredient successfully added!");
				}
				else {
					System.out.println("Something went wrong when storing that ingredient, please try again!");
				}
			}
		}
		
		// Get Directions
		int finished_directions = 1;
		int step = 1;
		while (finished_directions == 1) {
			String in;
			
			System.out.println("Please enter the #" + step + " step in the process, or enter '0' to finish the process.");
			in = scanner.next();
			if (in == "0") {
				finished_directions = 0;
			}
			else {
				recipe.add_direction(step, in);
			}
			
			step++;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://locahlhost/recipe_app?" + "user=cag35&password=cag35"); 
		} catch (Exception e) {
			System.out.println("Something went wrong when trying to add that recipe to the database. Please try again.");
		}
	}
	
	/*
	 * Getters and Setters
	 */
	public String get_name() {
		return this.name;
	}
	public void set_name(String new_name) {
		this.name = new_name;
	}
	public String get_category() {
		return this.category;
	}
	public void set_category(String cat_in) {
		this.category = cat_in;
	}
	public String[] get_tags() {
		return this.tags;
	}
}
