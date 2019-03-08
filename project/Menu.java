package project;
 /*
  * This class takes care of all printing used during menu navigation
  */
import java.io.IOException;
import java.util.Scanner;

public class Menu {
	private Scanner input;
	private int menuType;
	private String menuChoice;
	private static PrintFormatter format = new PrintFormatter();
	
	
	public Menu(int menuType) {
		input = new Scanner(System.in);
		this.menuType = menuType;
	}
	
	public void display() throws InterruptedException, IOException {
		if (menuType == 1)
			mainMenu();
		else if (menuType == 2)
			gameModeMenu();
		else if (menuType == 3)
			showScores();
		else if (menuType == 4)
			wordLengthMenu();
		else if (menuType == 5)
			endOfGameMenu();
		else if (menuType == 6)
			quitMenu();
	}
	
	public String getMenuChoice() {return menuChoice;}
	
	private void mainMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\n----------HANGMAN GAME----------");
		
		String gameMode = Game.getCurrentGameMode()+", length: ";
		String wordLength;
		if (Game.getWordLength() == 1)
			wordLength = "random";
		else
			wordLength = Integer.toString(Game.getWordLength());
		System.out.println("\nMenu:   \n1. Play game ("+gameMode+wordLength+")\n2. Change game mode "+format.color("\n3. View scores", "red")+"\n4. Quit");
		System.out.print("\nSelect 1, 2 or 3: ");
		
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("3")) {
			System.out.print(format.color("Scoring system not implemented!", "red"));
			Thread.sleep(1500);
		} else if (menuChoice.equals("test")) {
			if (!Game.testModeIsActive())
				Game.setTestMode(true);
			else 
				Game.setTestMode(false);
		} else if (!menuChoice.equals("1")&&!menuChoice.equals("2")&&!menuChoice.equals("4"))
			incorrectMenuChoice(menuChoice);
	}
	
	private void gameModeMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\nSelect a game mode:"
				+ "\n1. Guess on a random word"
				+ "\n2. Guess on a random word with a specific length (of your choosing)"
				+ format.color("\n3. Guess on as many words as you can until you lose", "red")
				+ format.color("\n4. Guess on a random word, with a timer!", "red"));
		
		System.out.print("\nSelect 1, 2, 3 or 4: ");
		
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("1")) {
			System.out.println("Game mode 1 selected!");
			Thread.sleep(1500);
		} else if (menuChoice.equals("2")) {
			
		} else if (menuChoice.equals("3") || menuChoice.equals("4")){
			System.out.println(format.color("Game mode "+menuChoice+" not implemented!", "red"));
			Thread.sleep(1500);
		} else 
			incorrectMenuChoice(menuChoice);
	}
	
	private void wordLengthMenu()  throws InterruptedException, IOException{
		Game.clear();
		System.out.println("\nSelect a word length between 2-17");
		System.out.print("\nSelection: ");
		
		menuChoice = input.nextLine();
		
		if (!isInteger(menuChoice) || Integer.parseInt(menuChoice) < 2 || Integer.parseInt(menuChoice) > 17)
			incorrectMenuChoice(menuChoice);
		else {
			System.out.println("Game mode 1 (word length "+menuChoice+") selected!");
			Thread.sleep(1000);
		}
		
	}
	
	private void endOfGameMenu() throws InterruptedException, IOException {
		Game.clear();
		if (Game.hasWon())
			System.out.println("\n-----YOU WIN!-----\nThe correct word was "+Game.getWord().toString());
		else
			System.out.println("\n-----YOU LOSE!-----\nThe correct word was "+Game.getWord().toString());
		System.out.println("\n1. Play again with same settings\n2. Go to main menu\n3. Quit");
		System.out.print("\nSelect 1, 2 or 3: ");
		
		menuChoice = input.nextLine();
		
		if (!menuChoice.equals("1")&&!menuChoice.equals("2")&&!menuChoice.equals("3"))
			incorrectMenuChoice(menuChoice);
		
	}
	
	public void showScores() throws InterruptedException, IOException {
		System.out.println(format.color("Scoring system not implemented!", "red"));
		Thread.sleep(1500);
	}
	
	private void quitMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\nAre you sure you want to quit?"
				+ "\n1. Press 1 to confirm"
				+ "\n2. Press any other key to return to the previous menu");
		
		System.out.print("\nSelection: ");
		
		menuChoice = input.nextLine();
		
		if (menuChoice.equals("1"))
			quit();
	}
	public void quit() throws InterruptedException {
		System.out.println("Shutting down...");
		Thread.sleep(200);
		input.close();
		System.exit(-1);
	}
	public static void incorrectMenuChoice(String choice) throws InterruptedException {
		System.out.println(format.color("'"+choice+"' is an incorrect menu choice!","red"));
		Thread.sleep(1500);
	}
	public static boolean isInteger(String string) {
		try {
			Integer.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
