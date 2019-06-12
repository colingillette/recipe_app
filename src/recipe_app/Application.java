package recipe_app;

public class Application {
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.set_instruction(menu.start());
		menu.direct(menu.get_step(), menu.get_instruction());
	}
}
