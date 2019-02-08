package ae223nv_1dv600;

import java.util.Scanner;

/* 
 * Super basic code, just to illustrate that the file can be run
 */

public class HangmanMain {

	public static void main(String[] args) {
		System.out.println("--------------HANGMAN GAME-------------");
		System.out.println("\nMenu:   1.Start game\n\t2.View scores\n\t3.Quit app");
		System.out.print("Select 1, 2 or 3: ");
		Scanner inputScanner = new Scanner(System.in);
		String mainMenuChoice = inputScanner.nextLine();
		inputScanner.close();
		
		switch (mainMenuChoice) {
		case "1":
			System.out.println("Start game");
			break;

		case "2":
			System.out.println("View scores");
			break;
		case "3":
			System.out.println("Shutting down...");
			break;
		default:
			System.out.println("\""+mainMenuChoice+"\" is not a correct menu choice!");
			break;
		}
		
		

	}

}
