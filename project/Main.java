package project;

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
