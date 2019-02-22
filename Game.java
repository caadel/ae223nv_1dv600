package ae223nv_1dv600;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ae223nv_1dv600.words.FetchWords;

public class Game {
	private ArrayList<Character> incorrectChars;
	private boolean isTimed; // Will be used in a later iteration
	private boolean oneWordGame; // Will be used in a later iteration
	private char[] wordWithGuessedChars;
	private FetchWords wordList;
	private int guesses;
	private int maxGuesses;
	private int score; // Will be used in a later iteration
	private Scanner guessInput; 
	private String word;
	private String menuChoiceReturnValue;
	
	public Game() throws IOException {
		guessInput = new Scanner(System.in);
		wordList = new FetchWords();
	}
	
	public void playGame(int gameMode, int wordLength) throws IOException, InterruptedException {
		setGameMode(gameMode);
		// Chose a random word
		if (wordLength == 1)
			word = wordList.chooseRandomWord();
		else 
			word = wordList.chooseRandomWordOfLength(wordLength);
		
		// RESET BEFORE PLAYING
		guesses = 0;
		clear();
		incorrectChars = new ArrayList<Character>();
		
		// printout during guessing
		wordWithGuessedChars = new char[word.length()];
		for (int i = 0; i < word.length(); i++) 
			wordWithGuessedChars[i] = '_';
		
		printWord(); // Show word length before you can start guessing
		
		// The actual game
		guessingLoop();
	}
	
	private void setGameMode(int gameMode) {
		// This will be used in a later iteration
		switch (gameMode) {
		case 1:
			this.isTimed = false;
			this.oneWordGame = true;
			break;
		case 2:
			this.isTimed = false;
			this.oneWordGame = false;
			break;
		case 3:
			this.isTimed = true;
			this.oneWordGame = true;
			break;
		case 4:
			this.isTimed = true;
			this.oneWordGame = false;
			break;
		default:
			throw new IllegalArgumentException("Game mode "+gameMode+" does not exist!");
		}
		this.maxGuesses = 10;
	}
	
	private void guessingLoop() throws InterruptedException, IOException {
		// Game mode selection will influence this method, to be implemented in the next iteration

		// Playing
		while (hasNotLost()) {
			if (hasWon())
				break;
			else {
				if (incorrectChars.size() > 0) {
					System.out.print("\n\nIncorrect characters: ");
					for (char c : incorrectChars)
						System.out.print(c+" ");
				}
				System.out.println("\nGuesses left: "+(maxGuesses-guesses));
				System.out.print("\nGuess: ");
				String guess = guessInput.nextLine();
				if (guess.length() == 1)
					guess(guess.charAt(0));
				else {
					clear(); 	// Word guessing not implemented
					printWord();// Guessing more than one letter will not do anything, not even reduce guesses left
				}
			}
		}
	}
	
	// Character guessing
	private void guess(char c) throws InterruptedException, IOException {
		boolean correctGuess = false;
		// Looping to check for character match in "word"-string
		for (int i = 0; i < word.length(); i++) {
			if (c == word.charAt(i)) {
				correctGuess = true;
				wordWithGuessedChars[i] = c; // Update display message
			}
		}
		// Incorrect guess
		if (!correctGuess && !incorrectChars.contains(c)) {
			incorrectChars.add(c);
			Collections.sort(incorrectChars);
			guesses++; 
		}
		
		clear();
		if (!hasWon()) 
			printWord();
	}
	
	// Clears the console window, does not work in IDE consoles
	public static void clear() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	// True if number of guesses is less than the max allowed guesses
	private boolean hasNotLost() {
		return guesses < maxGuesses;
	}
	// True if the word has been guessed
	public boolean hasWon() {
		boolean hasWon = true;
		for (int i = 0; i < word.length(); i++) 
			if (wordWithGuessedChars[i] != word.charAt(i))
				return false;
		return hasWon;
	}
	
	// Print the word that is guessed on
	private void printWord() {
		for (int i = 0; i < this.word.length(); i++) {
			if (word.charAt(i) == '-')
				wordWithGuessedChars[i] = '-';
			System.out.print(wordWithGuessedChars[i]+" ");
		}
	}
	
	public String getWord() {
		return word;
	}
	
	public String getMenuChoice() {
		return menuChoiceReturnValue;
	}
	
	public void incorrectMenuChoice(String choice) throws InterruptedException {
		System.out.println("\""+choice+"\" is an incorrect menu choice!");
		Thread.sleep(1500);
	}
	
}
