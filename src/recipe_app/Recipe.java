package recipe_app;

import java.util.*;

public class Recipe {
	String name;
	String category;
	HashMap<Integer, String> directions; // Step #, Step details
	HashMap<String, String> ingredients; // Ingredient, Amount of ingredient
	String[] tags;
	
	// Constructor
	public Recipe(String name_in, String category_in, String[] tags_in) {
		this.name = name_in;
		this.category = category_in;
		this.tags = tags_in;
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
	
	/*
	 * Getters and Setters
	 */
	public String get_name() {
		return this.name;
	}
	public String get_category() {
		return this.category;
	}
	public String[] get_tags() {
		return this.tags;
	}
}
