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
			Game game = new Game();

			// Menus of different types
			Menu mainMenu = new Menu(1);
			Menu gameModeMenu = new Menu(2);
			Menu wordLengthMenu = new Menu(4);
			Menu endOfGameMenu = new Menu(5);
			Menu quitMenu = new Menu(6);

			// Used to skip menu selections when trying to replay
			boolean replayFromEndGameMenu = false;
			
			/* Each menu level is in a while-loop for easy navigation 
			 * up and down each level, and it makes it easier to 
			 * re-display the menu after an incorrect menu choice.
			 */

			while(true) { // DISPLAY MAIN MENU LOOP
				// Display main menu
				// If you want to replay from the end screen, skip this step
				if (replayFromEndGameMenu == false) 
					mainMenu.display();
				

				if (mainMenu.getMenuChoice().equals("1")) {
					// PLAY GAME
					game.playGame();
					
					while (true) { // END OF GAME MENU LOOP
						// When game.playGame() ends, execute this code
						// Display end of game menu options
						endOfGameMenu.display();
						
						if (endOfGameMenu.getMenuChoice().equals("1")) {
							replayFromEndGameMenu = true; // This instantly replays instead of showing main menu
							break; // Break out of end of game loop, go back to main menu loop
						} else if (endOfGameMenu.getMenuChoice().equals("2")) {
							replayFromEndGameMenu = false;
							break; // Break out of end of game loop, go back to main menu loop
						} else if (endOfGameMenu.getMenuChoice().equals("3")) 
							quitMenu.display();
						
					}

				} else if (mainMenu.getMenuChoice().equals("2")) {
					// CHANGE GAME MODE
					while (true) { // GAME MODE SELECT LOOP
						// Display game mode select menu						
						gameModeMenu.display();

						if (gameModeMenu.getMenuChoice().equals("1")) {
							game.setGameMode(1);
							game.setWordLength(1);
							break;
						} else if (gameModeMenu.getMenuChoice().equals("2")) {
							while (true) { // WORD LENGTH SELECT LOOP
								// Display word length select menu
								wordLengthMenu.display();

								if (!Menu.isInteger(wordLengthMenu.getMenuChoice())||Integer.parseInt(wordLengthMenu.getMenuChoice()) < 2||Integer.parseInt(wordLengthMenu.getMenuChoice()) > 17)
									continue; // Restart word length select loop
								else {
									// Play with a specific word length (game mode 2)
									game.setGameMode(Integer.parseInt(gameModeMenu.getMenuChoice()));
									game.setWordLength(Integer.parseInt(wordLengthMenu.getMenuChoice()));
									break; // Break out of word select loop, go back to game mode select loop
								}
							}
						} else 
							continue; // Restart game mode select loop
						break; // Used by word length select loop to return to main menu loop
					} 
				} else if (mainMenu.getMenuChoice().equals("3")) {
					// VIEW SCORES
					
				} else if (mainMenu.getMenuChoice().equals("4")) {
					// QUIT GAME
					quitMenu.display();
				} 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
