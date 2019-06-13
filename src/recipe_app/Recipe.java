package recipe_app;

import java.sql.*;
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
	}
	
	private int receiveCategoryChoice(String input, Scanner scanner) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Please ensure you have entered correct input. It should just be the number of your selection.");
			return receiveCategoryChoice(scanner.next(), scanner);
		}
	}
	
	private String interpretCategoryChoice(int choice, Scanner scanner) throws SQLException, ClassNotFoundException {
		if (choice == 0) {
			System.out.println("Please enter the name of your new category!");
			String newCategory = scanner.next();
			if (newCategory.length() > 45) {
				newCategory = newCategory.substring(0, 44);
			}
			writeNewCategory(newCategory);
			return newCategory;
		}
		else {
			return selectCategory(choice);
		}
	}
	
	private void writeNewCategory(String cat) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection("jdbc:mysql://locahlhost/recipe_app?" + "user=cag35&password=cag35");
		PreparedStatement statement = connect.prepareStatement("insert into categories (category) values ('?');");
		statement.setString(1, cat);
		Resources.close(connect, statement);
	}
	
	private String selectCategory(int id) throws ClassNotFoundException, SQLException {
		String result;
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection("jdbc:mysql://locahlhost/recipe_app?" + "user=cag35&password=cag35");
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery("select category from categories where category_id = " + Integer.toString(id));
		result = resultSet.getString("category");
		
		Resources.close(connect, statement, resultSet);
		
		return result;
	}
	
	/*
	 * Getters and Setters
	 */
	public String get_name() {
		return this.name;
	}
	
	public void set_name(String new_name) {
		if (new_name.length() > 50) {
			new_name = new_name.substring(0, 49);
		}
		
		this.name = new_name;
	}
	
	public String get_category() {
		return this.category;
	}
	
	public void set_category() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			String categoryQuery = "";
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://locahlhost/recipe_app?" + "user=cag35&password=cag35");
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(categoryQuery);
			Resources.writeResultSet(resultSet, "categories");
			System.out.println("Enter '0' to create a new category, or enter the number of the choice you would like to make.");
			
			int choice = receiveCategoryChoice(scanner.next(), scanner);
			this.category = interpretCategoryChoice(choice, scanner);
			
			if (!Resources.close(connect, statement, resultSet)) {
				System.out.println("Something broke when trying to close Database Resources");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong when trying to display the category list.");
		} finally {
			scanner.close();
		}
	}
	
	public String[] get_tags() {
		return this.tags;
	}
}
