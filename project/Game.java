package project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
	private ArrayList<Character> incorrectChars;
	private ArrayList<Word> incorrectWords;
	private String gameMode; // Will be used in a later iteration
	private WordList wordList;
	private int guesses;
	private int maxGuesses;
	private int wordLength;
	private int score; 
	private Scanner guessInput; 
	private Word word;
	private Drawing drawing;
	private Timer timer = new Timer();
	private boolean testModeIsActive;
	private boolean endlessReplay;
	
	public Game() throws IOException {
		guessInput = new Scanner(System.in);
		wordList = new WordList();
		drawing = new Drawing();		
		maxGuesses = 10;
		// Default game mode is standard (One word, random length)
		setGameMode(1);
		setWordLength(1);
		testModeIsActive = false;
		endlessReplay = false;
	}
	
	public void playGame() throws IOException, InterruptedException {
		// Chose a random word
		if (testModeIsActive)
			word = new Word("manner");
		else {
			if (wordLength == 1)
				word = wordList.chooseRandomWord();
			else 
				word = wordList.chooseRandomWordOfLength(wordLength);
		}
		
		// RESET BEFORE PLAYING
		guesses = 0;
		if (!endlessReplay)
			score = 0;
		clear();
		incorrectChars = new ArrayList<Character>();
		incorrectWords = new ArrayList<Word>();
		drawing.setDrawingToHangman();
		
		// The actual game
		guessingLoop();
	}
	
	public void setGameMode(int gameMode) {
		// This will be used in a later iteration to change the behaviour of the game
		switch (gameMode) {
		case 1:
			this.gameMode = "One word";
			break;
		case 2:
			this.gameMode = "One word";
			break;
		case 3:
			this.gameMode = "Endless";
			break;
		case 4:
			this.gameMode = "Timed";
			break;
		default:
			throw new IllegalArgumentException("Game mode "+gameMode+" does not exist!");
		}
	}
	public String getCurrentGameMode() {return gameMode;}
	public void setWordLength(int wordLength) {this.wordLength = wordLength;}
	public int getWordLength() {return wordLength;}
	public Word getWord() {return word;}
	// Test mode = always play with the same word
	public void setTestModeState(boolean setTestModeState) {testModeIsActive = setTestModeState;}
	public boolean getTestModeState() {return testModeIsActive;}
	public int getScore() {return score;}
	
	private void guessingLoop() throws InterruptedException, IOException {

		// For the timed game mode, the task is never started in the other game games
		TimerTask task = new TimerTask() {public void run() {score++;}};
		if (gameMode.equals("Timed")) 
			timer.scheduleAtFixedRate(task, 1000, 1000);

		// Playing
		while (!hasEnded()) {
			if (hasWon()) {
				if (gameMode.equals("Endless")) {
					score += (maxGuesses-guesses) * 10 ; // No incorrect guesses = 100p, 4 incorrect guesses = 60p, etc
					endlessReplay = true;
					System.out.println("\nCorrect!\n+"+((maxGuesses-guesses)*10)+" points!");
					Thread.sleep(1500);
					playGame();
				} else 
					break;
			} else {
				String gameModePrint = "\n Current game mode: "+gameMode;
				
				if (wordLength > 1)
					gameModePrint = gameModePrint+", word length: "+wordLength;
				else if (gameMode.equals("Timed"))
					gameModePrint = gameModePrint+", time: "+score+"s";
				else if (gameMode.equals("Endless"))
					gameModePrint = gameModePrint+", score: "+score;
				System.out.println(gameModePrint);
				
				
				// Hangman drawing
				System.out.println(drawing.get(guesses));
				
				
				System.out.println("\n "+word.getHiddenWord().toString());
				
				// Printing incorrect guesses
				System.out.print("\n Incorrect guesses: ");
				for (char c : incorrectChars) { // letters
					System.out.print(c);
					if(c != incorrectChars.get(incorrectChars.size()-1))
						System.out.print(", ");
				}
				
				if(!incorrectChars.isEmpty()) // separator between chars and words
						System.out.print(", ");
				
				for (Word w : incorrectWords) { // words
					System.out.print(w.toString());
					if(!w.equals(incorrectWords.get(incorrectWords.size()-1)))
						System.out.print(", ");
				}
				
				System.out.print("\n Guess: ");
				String guess = guessInput.nextLine();
				if (guess.length() == 1)
					guess(guess.charAt(0));
				else {
					guess(new Word(guess));
					clear(); 
				}
			}
		}
		
		// Stops the timer task after the game is either won or lost
		task.cancel();
		// Resets the Endless mode replay loop
		endlessReplay = false;
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
	// Word guessing
	private void guess(Word w) throws InterruptedException, IOException {
		Word guess = w.toLowerCase();
		
		// An incorrect guess gets added to a the list
		if (!word.equals(guess) && !incorrectWords.contains(guess)) {
			incorrectWords.add(guess);
			Collections.sort(incorrectWords);
			guesses++; 
		} else 
			word.updateHiddenWordIfContains(guess);
		
		clear();
	}
	
	// Clears the console window, ONLY works using Windows cmd (command prompt)
	public static void clear() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	// True if number of guesses is less than the max allowed guesses
	private boolean hasEnded() {
		return guesses == maxGuesses;
	}
	// True if the word has been guessed
	public boolean hasWon() {
		boolean hasWon = true;
		for (int i = 0; i < word.length(); i++) 
			if (word.getHiddenWord().charAt(i*2) != word.charAt(i))
				return false;
		return hasWon;
	}
	
}
