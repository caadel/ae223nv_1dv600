package ae223nv_1dv600;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
	private Scanner input;
	private int menuType;
	private String menuChoice;
	
	
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
			wordLengthMenu();
		else if (menuType == 4)
			endOfGameMenu();
		else if (menuType == 5)
			quitMenu();
	}
	
	public String getMenuChoice() {
		return menuChoice;
	}
	
	private void mainMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\n----------HANGMAN GAME----------");
		System.out.println("\nMenu:   \n1. Start game\n2. View scores\n3. Quit");
		System.out.print("\nSelect 1, 2 or 3: ");
		menuChoice = input.nextLine();
	}
	
	private void gameModeMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\nSelect a game mode:"
				+ "\n1. Guess on a random word"
				+ "\n2. Guess on a random word with a specific length (of your choosing)"
				+ "\n3. Guess on as many words as you can until you lose"
				+ "\n4. Guess on a random word, with a timer!");
		
		System.out.print("\nSelect 1, 2, 3 or 4: ");
		menuChoice = input.nextLine();
	}
	
	private void wordLengthMenu()  throws InterruptedException, IOException{
		Game.clear();
		System.out.println("\nSelect a word length between 2-17");
		System.out.print("\nSelection: ");
		menuChoice = input.nextLine();
	}
	
	private void endOfGameMenu() throws InterruptedException, IOException {
		System.out.println("\n1. Play again with same settings\n2. Go to main menu\n3. Quit");
		System.out.print("\nSelect 1, 2 or 3: ");
		menuChoice = input.nextLine();
	}
	
	public void showScores() throws InterruptedException, IOException {
		System.out.println("Scoring system not implemented!");
		Thread.sleep(1500);
		display();
	}
	
	private void quitMenu() throws InterruptedException, IOException {
		Game.clear();
		System.out.println("\nAre you sure you want to quit?"
				+ "\n1. Press 1 to confirm"
				+ "\n2. Press any other key to return to the previous menu");
		
		System.out.print("\nSelection: ");
		menuChoice = input.nextLine();
	}
	public void quit() throws InterruptedException {
		System.out.println("Shutting down...");
		Thread.sleep(200);
		input.close();
		System.exit(-1);
	}
	
}



