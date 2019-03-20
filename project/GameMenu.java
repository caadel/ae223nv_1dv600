package project;
/*
  * This class takes care of all printing used during menu navigation
  */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMenu {
	private boolean scoreIsSaved = false;
	private boolean wordIsBanned = false;
	private Scanner input;
	private String menuChoice;
	private PrintFormatter format = new PrintFormatter();
	private Drawing hangmanDrawing = new Drawing();
	private FileHandler fh = new FileHandler();
	private Game game;
	
	public GameMenu() throws IOException {
		input = new Scanner(System.in);
		hangmanDrawing.setDrawingToHangman();
		game = new Game();
	}
	
	public void display() throws InterruptedException, IOException {
		mainMenu();
	}
	
	private void mainMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\n============ HANGMAN GAME ============");
		
		String gameMode = game.getCurrentGameMode();
		if (game.getCurrentGameMode().equals("One word")) {
			if (game.getWordLength() == 1)
				gameMode = gameMode+", length: random";
			else
				gameMode = gameMode+", length: "+Integer.toString(game.getWordLength());
		}
		
		System.out.print("\n 1. Play game ("+gameMode+")\n 2. Change game mode\n 3. View scores\n 4. View banned words\n 5. Quit\n\n Select 1, 2, 3, 4 or 5\n\n> ");
		
		menuChoice = input.nextLine();
		if (menuChoice.equals("1")) {
			game.playGame();
			scoreIsSaved = false;
			wordIsBanned = false;
			endOfGameMenu();
		} else if (menuChoice.equals("2")) {
			gameModeMenu();
		} else if (menuChoice.equals("3")) {
			showScores();
		} else if (menuChoice.equals("4")) {
			showBannedWords();
		} else if (menuChoice.equals("5")) {
			quitMenu();
		} else if (menuChoice.equals("test")) {
			if (!game.getTestModeState())
				game.setTestModeState(true);
			else 
				game.setTestModeState(false);
		} else
			incorrectMenuChoice(menuChoice);
			
		mainMenu();
	}
	
	private void gameModeMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.print("\nSelect a game mode:"
				+ "\n1. Guess on a random word"
				+ "\n2. Guess on a random word with a specific length (of your choosing)"
				+ "\n3. Guess on as many words as you can until you lose"
				+ "\n4. Guess on a random word, with a timer!"
				+ "\n\n> ");
				
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("1") || menuChoice.equals("3") || menuChoice.equals("4")) {
			game.setGameMode(Integer.parseInt(menuChoice));
			game.setWordLength(1);
			System.out.println("Game mode " + menuChoice + " selected!");
			Thread.sleep(1000);
		} else if (menuChoice.equals("2")) {
			wordLengthMenu();
		} else {
			incorrectMenuChoice(menuChoice);
			gameModeMenu();
		}
		mainMenu();
	}
	
	private void wordLengthMenu()  throws InterruptedException, IOException{
		Game.clear();
		System.out.println("\nSelect a word length between 2-17");
		System.out.print("\n> ");
		
		menuChoice = input.nextLine();
		
		if (!isInteger(menuChoice) || Integer.parseInt(menuChoice) < 2 || Integer.parseInt(menuChoice) > 17) {
			incorrectMenuChoice(menuChoice);
			wordLengthMenu();
		} else {
			game.setWordLength(Integer.parseInt(menuChoice));
			System.out.println("Word length "+menuChoice+" selected!");
			Thread.sleep(1000);
		}
		
	}
	
	private void endOfGameMenu() throws InterruptedException, IOException {
		Game.clear();
		
		// scorePrint = "Time" or "Final score"
		String scorePrint = "";
		String save = "Save score";
		String ban = "Ban word";
		if (game.getCurrentGameMode().equals("Timed"))
			scorePrint = " Time: "+game.getScore()+" seconds";
		else if (game.getCurrentGameMode().equals("Endless"))
			scorePrint = " Final score: "+game.getScore()+" points";
		
		if (game.hasWon()) {
			System.out.println("\n ====== YOU WIN! ======");
			System.out.println(hangmanDrawing.get(11));		
		} else {
			System.out.println("\n ====== YOU LOSE! ======");
			System.out.println(hangmanDrawing.get(10));
			if (!game.getCurrentGameMode().equals("Endless"))
				save = format.color("Save score", "red");
		}
		
		if (scoreIsSaved || game.getCurrentGameMode().equals("One word"))
			save = format.color("Save score", "red");
		if (wordIsBanned)
			ban = format.color(ban, "red");
		
		System.out.println(" The correct word was "+game.getWord().toString());
		System.out.println(scorePrint);
		System.out.println("\n 1. Play again with same settings\n 2. Go to main menu\n 3. "+save+"\n 4. "+ban+"\n 5. Quit");
		System.out.print("\n> ");
		
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("1")) {
			game.playGame();
		} else if (menuChoice.equals("2")) {
			mainMenu();
		} else if (menuChoice.equals("3")) {
			
			if (format.isColored(save)) {
				System.out.println(format.color("No score to save!","red"));
				Thread.sleep(1000);
			} else {
				Game.clear();
				System.out.print(" Enter a name \n> ");
				String name = input.nextLine();
				
				System.out.println("Score saved!");
				Thread.sleep(750);
				
				if (game.getCurrentGameMode().equals("Timed"))
					fh.saveTimedScore(name, game.getScore());
				else
					fh.saveEndlessScore(name, game.getScore());
				scoreIsSaved = true;
			}
		} else if (menuChoice.equals("4")) {
			fh.saveBannedWord(game.getWord());
			wordIsBanned = true;
			System.out.println("Word banned!");
			Thread.sleep(750);
		} else if (menuChoice.equals("5"))
			quitMenu();
		else
			incorrectMenuChoice(menuChoice);
		
		
		endOfGameMenu();
	}
	
	private void showScores() throws InterruptedException, IOException {
		Game.clear();
		ArrayList<Score> scoresE = fh.getEndlessScores();
		ArrayList<Score> scoresT = fh.getTimedScores();
		// READ FILE
		System.out.println(" TIMED");
		if (scoresT.size() > 0) {
			int rank = 1;
			for (Score s : scoresT) {
				System.out.println("  "+rank+": "+s.getName()+" - "+s.getScore()+"s");
				rank++;
			}
		} else 
			System.out.println(" No scores saved!\n");
		
		System.out.println("\n ENDLESS");
		if (scoresE.size() > 0) {
			int rank = 1;
			for (Score s : scoresE) {
				System.out.println("  "+rank+": "+s.getName()+" - "+s.getScore()+"p");
				rank++;
			}
		} else 
			System.out.println(" No scores saved!\n");
		
		System.out.print("\n Type 'delete' to clear all previous scores\n Type anything else to return to the main menu\n\n> ");
		menuChoice = input.nextLine();
		if (menuChoice.equals("delete")) {
			if (fh.deleteScores())
				System.out.println("All scores cleared!");
			else 
				System.out.println(format.color("No scores to clear!", "red"));
			Thread.sleep(1000);
			showScores();
		}
	}
	private void showBannedWords() throws InterruptedException, IOException {
		Game.clear();
		ArrayList<Word> bans = fh.getBannedWords();
		// READ FILE
		System.out.println(" Banned words:");
		if (bans.size() > 0) {
			int rank = 1;
			for (Word w : bans) {
				System.out.println("  "+rank+". "+w.toString());
				rank++;
			}
		} else 
			System.out.println(" No words have been banned!\n");
		
		System.out.print("\n Type 'delete' to clear all banned words\n Type anything else to return to the main menu\n\n> ");
		menuChoice = input.nextLine();
		if (menuChoice.equals("delete")) {
			if (fh.deleteBannedWords())
				System.out.println("All bans cleared!");
			else 
				System.out.println(format.color("No words have been banned!", "red"));
			Thread.sleep(1000);
			showBannedWords();
		}
	}
	
	private void quitMenu() throws InterruptedException, IOException {
		Game.clear();
		
		
		System.out.print("\nAre you sure you want to quit?"
				+ "\n1. Press 1 to confirm"
				+ "\n2. Press any other key to return to the previous menu"
				+ "\n\n> ");
		
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("1"))
			quit();
	}
	public void quit() throws InterruptedException {
		System.out.println("Shutting down...");
		input.close();
		System.exit(-1);
	}
	private void incorrectMenuChoice(String choice) throws InterruptedException {
		System.out.println(format.color("'"+choice+"' is an incorrect menu choice!","red"));
		Thread.sleep(1500);
	}
	private boolean isInteger(String string) {
		try {
			Integer.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
