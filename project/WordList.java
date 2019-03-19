package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordList {
	
	private ArrayList<Word> wordList;
	private ArrayList<Word> bannedWords;
	private FileHandler fh;

	public WordList() throws IOException {
		wordList = new ArrayList<Word>();
		//bannedWords = fh.getBannedWords();
		fh = new FileHandler();
		fetch();
	}
	
	public ArrayList<Word> getWords() {
		return wordList;
	}
	
	private void fetch() throws IOException {
		URL url = new URL("http://www.desiquintans.com/downloads/nounlist/nounlist.txt");
		Scanner textFile = new Scanner(url.openStream()); 
		
		while (textFile.hasNext())
			wordList.add(new Word(textFile.next()));
		
		textFile.close();
	}
	
	public Word chooseRandomWord() throws FileNotFoundException {
		bannedWords = fh.getBannedWords();
		Random rn = new Random();
		int word = rn.nextInt(wordList.size());
		
		// The chosen word has been banned, choose a new one
		if (bannedWords.contains(wordList.get(word)))
			chooseRandomWord();
		
		return wordList.get(word);
	}
	
	public Word chooseRandomWordOfLength(int length) throws FileNotFoundException {
		// Because no words in the list are greater than 17 or smaller than 2, throw exception
		if (length < 2 || length > 17)
			throw new IllegalArgumentException("No word of length "+length+" is available!");
		
		bannedWords = fh.getBannedWords();
		ArrayList<Word> filteredList = new ArrayList<Word>();
		
		for (Word w : wordList) 
			if (w.length() == length && !bannedWords.contains(w))
				filteredList.add(w);
		
		if (filteredList.size() != 0) { // If no words are available, pick one of the banned ones instead
			for (Word w : bannedWords) 
				if (w.length() == length)
					filteredList.add(w);
		}
		
		Random rn = new Random();
		int word = rn.nextInt(filteredList.size());
		
		return filteredList.get(word);
	}
}
