package ae223nv_1dv600;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			// Clearing console before starting the game, creating a game
			Game.clear();
			Game game = new Game();
			
			// Menus of different types
			Menu mainMenu = new Menu(1);
			Menu gameModeMenu = new Menu(2);
			Menu wordLengthMenu = new Menu(3);
			Menu endOfGameMenu = new Menu(4);
			Menu quitMenu = new Menu(5);
			
			// Menu selection storage, used for instant replaying of the same mode
			String mainMenuChoice = "";
			String gameModeMenuChoice = "";
			String wordLengthMenuChoice = "";
			String endOfGameMenuChoice = "";
			String quitMenuChoice = "";
			
			// Used to skip menu selections when trying to replay
			boolean replayFromEndGameMenu = false;
			
			/* Each menu level is in a while-loop for easy navigation 
			 * up and down each level, and it makes it easier to 
			 * re-display the menu after an incorrect menu choice.
			 */
			
			while(true) { // DISPLAY MAIN MENU LOOP
				// Display main menu
				// If you want to replay from the end screen, skip this step
				if (replayFromEndGameMenu == false) {
					mainMenu.display();
					mainMenuChoice = mainMenu.getMenuChoice();
				}
				

				if (mainMenuChoice.compareTo("1") == 0) {
					while (true) { // GAME MODE SELECT LOOP
						// Display game mode select menu
						// If you want to replay from the end screen, skip this step
						if (replayFromEndGameMenu == false) {
							gameModeMenu.display();
							gameModeMenuChoice = gameModeMenu.getMenuChoice();
						}
						if (gameModeMenuChoice.compareTo("1") == 0) {
							game.playGame(1, 1);
							// start game mode 1
						} else if (gameModeMenuChoice.compareTo("2") == 0) {
							while (true) { // WORD LENGTH SELECT LOOP
								// Display word length select menu
								// If you want to replay from the end screen, skip this step
								if (replayFromEndGameMenu == false) {
									wordLengthMenu.display();
									wordLengthMenuChoice = wordLengthMenu.getMenuChoice();
								}
								if (!isInteger(wordLengthMenuChoice)) {
									game.incorrectMenuChoice(wordLengthMenuChoice);
									continue;
								} else if (Integer.parseInt(wordLengthMenuChoice) < 1||Integer.parseInt(wordLengthMenuChoice) > 17) {
									System.out.println("Word length must be between 2 and 17!");
									Thread.sleep(1500);
									continue; // Restart word length select loop
								} else  // Play with a specific word length
									game.playGame(Integer.parseInt(gameModeMenuChoice), Integer.parseInt(wordLengthMenuChoice));
								break; // Break out of word select loop, go back to game mode select loop
							}
						} else if (gameModeMenuChoice.compareTo("3") == 0) {
							System.out.println("Game mode 3 not implemented!");
							Thread.sleep(1500);
							continue;
						} else if (gameModeMenuChoice.compareTo("4") == 0) {
							System.out.println("Game mode 4 not implemented!");
							Thread.sleep(1500);
							continue;
						} else {
							game.incorrectMenuChoice(gameModeMenuChoice); 
							continue; // Restart game mode select loop
						}
						while (true) { // END OF GAME MENU LOOP
							// When game.playGame() ends, execute this code
							if (game.hasWon()) {
								Game.clear();
								System.out.println("\n-----YOU WIN!-----\nThe correct word was "+game.getWord());
							} else {
								Game.clear();
								System.out.println("\n-----YOU LOSE!-----\nThe correct word was "+game.getWord());
							}
							// Display end of game menu options
							endOfGameMenu.display();
							endOfGameMenuChoice = endOfGameMenu.getMenuChoice();
							if (endOfGameMenuChoice.compareTo("1") == 0) {
								replayFromEndGameMenu = true; // This makes us pass through menus
								break; // Break out of end of game loop, goto end of word select loop
							} else if (endOfGameMenuChoice.compareTo("2") == 0) {
								replayFromEndGameMenu = false;
								break; // Break out of end of game loop, goto end of word select loop
							} else if (endOfGameMenuChoice.compareTo("3") == 0) {
								quitMenu.display();
								quitMenuChoice = quitMenu.getMenuChoice();
								if (quitMenuChoice.compareTo("1") == 0)
									mainMenu.quit();
								else {
									quitMenuChoice = ""; // Reset to prevent an infinite loop 
									continue; // Restart the end of game loop
								}
							} else {
								game.incorrectMenuChoice(endOfGameMenuChoice);
								continue; // Restart the end of game loop
							}
						}
						break; // Break out of game mode loop, go back to main menu loop
					} 
				} else if (mainMenuChoice.compareTo("2") == 0) {
					System.out.println("Scoring system not implemented!");
					Thread.sleep(2000);
				} else if (mainMenuChoice.compareTo("3") == 0) {
					quitMenu.display();
					quitMenuChoice = quitMenu.getMenuChoice();
					if (quitMenuChoice.compareTo("1") == 0)
						mainMenu.quit();
					else 
						continue;
				} else 
					game.incorrectMenuChoice(mainMenuChoice);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/* Used when selecting a random word of a specific length.
	* parseInt()/valueOf() only works on strings which are actually numbers, like "5" "68" or "1"
	* This returns false if the string is something like "sixteen" "x" or "five"
	*/
	public static boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

}
