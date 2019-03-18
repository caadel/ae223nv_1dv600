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
	private String pathToTimedScores = new File("").getAbsolutePath()+"/project/data/scorest.txt";
	
	public FileHandler() {
		
	}

	public void saveScores(String name, int score) throws IOException  {
		File textFile;
		if (Game.getCurrentGameMode().equals("Timed")) {
			textFile = new File(pathToTimedScores); 
		} else 
			textFile = new File(pathToEndlessScores); 
		
		if (textFile.exists()) {
			ArrayList<Score> list = getEndlessScores();
			list.add(new Score(name,score));
			Collections.sort(list);
			
			// Overwrites the already existing file with the sorted scores
			FileWriter writer = new FileWriter(textFile, false);
			for (Score s : list)
				writer.write(s.getName()+"\n"+s.getScore()+"\n");
			writer.close();
		} else {
			// Creates a new file
			PrintWriter printer = new PrintWriter(textFile);
			printer.print(name+"\n"+score+"\n");
			printer.close();
		}
	}
	
	public ArrayList<Score> getEndlessScores() throws FileNotFoundException {
		return getScores(pathToEndlessScores);
	}
	public ArrayList<Score> getTimedScores() throws FileNotFoundException {
		return getScores(pathToTimedScores);
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
	// Returns true if either endless or timed scores are cleared
	public boolean deleteScores() {
		File timedScores = new File(pathToTimedScores); 
		File endlessScores = new File(pathToEndlessScores); 
		
		return (timedScores.delete() || endlessScores.delete());
	}
	
}
