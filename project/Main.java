package project;
/*
 * This class starts the game and is used to change some states.
 * The amount of code here is almost half of what it once was, 
 * as the Menu class was basically fully written in this class. 
 * This code has implicit dependencies with the Menu class, which 
 * is not very good. During the next iteration I will try to remove 
 * the last of the implicit dependencies from this class.
 */
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		try {
			// Clearing console before starting the game
			Game.clear();
			GameMenu menu = new GameMenu();
			menu.display();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
