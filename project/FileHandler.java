package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileHandler {

	private String pathToEndlessScores = new File("").getAbsolutePath()+"/project/data/scoresE.txt";
	private String pathToTimedScores = new File("").getAbsolutePath()+"/project/data/scoresT.txt";
	private String pathToBannedWords = new File("").getAbsolutePath()+"/project/data/bans.txt";
	
	public FileHandler() {
		
	}

	public void saveEndlessScore(String name, int score) throws IOException  {
		saveScores(new Score(name, score), pathToEndlessScores);
	}
	public void saveTimedScore(String name, int time) throws IOException  {
		saveScores(new Score(name, time), pathToTimedScores);
	}
	
	private void saveScores(Score score, String path) throws IOException {
		File textFile = new File(path);
		
		if (textFile.exists()) {
			ArrayList<Score> list = getScores(path);
			list.add(score);
			
			// Overwrites the already existing file with the sorted scores
			FileWriter writer = new FileWriter(textFile, false);
			for (Score s : list)
				writer.write(s.getName()+"\n"+s.getScore()+"\n");
			writer.close();
		} else {
			// Creates a new file
			PrintWriter printer = new PrintWriter(textFile);
			printer.print(score.getName()+"\n"+score.getScore()+"\n");
			printer.close();
		}
	}
	
	public ArrayList<Score> getEndlessScores() throws FileNotFoundException {
		ArrayList<Score> list = getScores(pathToEndlessScores);
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}
	public ArrayList<Score> getTimedScores() throws FileNotFoundException {
		ArrayList<Score> list = getScores(pathToTimedScores);
		Collections.sort(list);
		return list;
	}
	// Returns an empty ArrayList if no scores have been saved
	private ArrayList<Score> getScores(String path) throws FileNotFoundException {
		ArrayList<Score> list = new ArrayList<Score>();
		File file = new File(path); 
		
		if (file.exists()) {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String name = scanner.next();
				String score = scanner.next();
				list.add(new Score(name,Integer.parseInt(score)));
			}	
			scanner.close();
			Collections.sort(list);
			return list;
		} else
			return new ArrayList<Score>();
	}
	// Returns true if either endless or timed scores are deleted
	public boolean deleteScores() {
		File timedScores = new File(pathToTimedScores); 
		File endlessScores = new File(pathToEndlessScores); 
		
		boolean dts = timedScores.delete();
		boolean des = endlessScores.delete();
		
		return (dts || des);
	}
	public void saveBannedWord(Word w) throws IOException {
		File textFile = new File(pathToBannedWords);
		
		if (textFile.exists()) {
			ArrayList<Word> list = getBannedWords();
			if (!list.contains(w)) {
				// Appends the new word at the end of the file
				FileWriter writer = new FileWriter(textFile, true);
				writer.write(w.toString()+"\n");
				writer.close();
			}
		} else {
			// Creates a new file
			PrintWriter printer = new PrintWriter(textFile);
			printer.print(w.toString()+"\n");
			printer.close();
		}
	}
	public ArrayList<Word> getBannedWords() throws FileNotFoundException {
		ArrayList<Word> list = new ArrayList<Word>();
		File file = new File(pathToBannedWords); 
		
		if (file.exists()) {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String next = scanner.next();
				list.add(new Word(next));
			}	
			scanner.close();
			Collections.sort(list);
			return list;
		} else
			return new ArrayList<Word>();
	}
	// Returns true if banned words are deleted
	public boolean deleteBannedWords() {
		File bans = new File(pathToBannedWords); 
		return bans.delete();
	}
	
}
