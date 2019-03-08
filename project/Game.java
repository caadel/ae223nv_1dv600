package project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
	private ArrayList<Character> incorrectChars;
	private static String gameMode; // Will be used in a later iteration
	private WordList wordList;
	private int guesses;
	private int maxGuesses;
	private static int wordLength;
	//private int score; // Will be used in a later iteration
	private Scanner guessInput; 
	private static Word word;
	private static boolean testModeIsActive;
	
	public Game() throws IOException {
		guessInput = new Scanner(System.in);
		wordList = new WordList();
		// Default game mode is standard (One word, random length)
		setGameMode(1);
		setWordLength(1);
		testModeIsActive = false;
	}
	
	public void playGame() throws IOException, InterruptedException {
		// Chose a random word
		if (testModeIsActive) {
			word = new Word("manner");
		} else {
			if (wordLength == 1)
				word = wordList.chooseRandomWord();
			else 
				word = wordList.chooseRandomWordOfLength(wordLength);
		}
		
		// RESET BEFORE PLAYING
		guesses = 0;
		maxGuesses = 10;
		clear();
		incorrectChars = new ArrayList<Character>();
		
		// The actual game
		guessingLoop();
	}
	
	public void setGameMode(int gameMode) {
		// This will be used in a later iteration to change the behaviour of the game
		switch (gameMode) {
		case 1:
			Game.gameMode = "One word";
			break;
		case 2:
			Game.gameMode = "One word";
			break;
		case 3:
			Game.gameMode = "Survival";
			break;
		case 4:
			Game.gameMode = "Timed";
			break;
		default:
			throw new IllegalArgumentException("Game mode "+gameMode+" does not exist!");
		}
	}
	public void setWordLength(int wordLength) {
		Game.wordLength = wordLength;
	}
	public static int getWordLength() {
		return wordLength;
	}
	
	private void guessingLoop() throws InterruptedException, IOException {
		// Game mode selection will influence this method, to be implemented in the next iteration

		// Playing
		while (hasNotLost()) {
			if (hasWon())
				break;
			else {
				String gameModePrint = "Current game mode: "+gameMode;
				if (wordLength > 1)
					gameModePrint = gameModePrint+", word length: "+wordLength;
				System.out.println(gameModePrint);
				
				System.out.println("\n"+word.getHiddenWord().toString());
				
				if (incorrectChars.size() > 0) {
					System.out.print("\nIncorrect guesses: ");
					for (char c : incorrectChars)
						System.out.print(c+" ");
				}
				
				System.out.println("\nIncorrect guesses left: "+(maxGuesses-guesses));
				System.out.print("\nGuess: ");
				String guess = guessInput.nextLine();
				if (guess.length() == 1)
					guess(guess.charAt(0));
				else {
					clear(); 	// Word guessing not implemented
								// Guessing more than one letter will not do anything, not even reduce guesses left
				}
			}
		}
	}
	
	// Character guessing
	private void guess(char c) throws InterruptedException, IOException {
		char guess = Character.toLowerCase(c);
		
		word.updateHiddenWordIfContains(guess); // Update display message
		
		// An incorrect guess gets added to a the list
		if (!word.contains(guess) && !incorrectChars.contains(guess)) {
			incorrectChars.add(guess);
			Collections.sort(incorrectChars);
			guesses++; 
		}
		
		clear();
	}
	
	// Clears the console window, ONLY works using Windows cmd (command prompt)
	public static void clear() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	// True if number of guesses is less than the max allowed guesses
	private boolean hasNotLost() {
		return guesses < maxGuesses;
	}
	// True if the word has been guessed
	public static boolean hasWon() {
		boolean hasWon = true;
		for (int i = 0; i < word.length(); i++) 
			if (word.getHiddenWord().charAt(i*2) != word.charAt(i))
				return false;
		return hasWon;
	}

	public static Word getWord() {return word;}
	public static String getCurrentGameMode() {return gameMode;}
	// Test mode = always play with the same word
	public static void setTestMode(boolean testModeIsActive) {Game.testModeIsActive = true;}
	public static boolean testModeIsActive() {return Game.testModeIsActive;}
	
}
