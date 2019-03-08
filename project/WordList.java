package project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordList {
	
	private ArrayList<Word> wordList;

	public WordList() throws IOException {
		wordList = new ArrayList<Word>();
		fetch();
	}
	
	private void fetch() throws IOException {
		URL url = new URL("http://www.desiquintans.com/downloads/nounlist/nounlist.txt");
		Scanner textFile = new Scanner(url.openStream()); 
		
		while (textFile.hasNext())
			wordList.add(new Word(textFile.next()));
		
		textFile.close();
	}
	
	public Word chooseRandomWord() {
		Random rn = new Random();
		int word = rn.nextInt(wordList.size());
		
		return wordList.get(word);
	}
	
	public Word chooseRandomWordOfLength(int length) {
		// Because no words in the list are greater than 17 or smaller than 2, throw exception
		if (length < 2 || length > 17)
			throw new IllegalArgumentException("No word of length "+length+" is available!");
		
		ArrayList<Word> filteredList = new ArrayList<Word>();
		
		for (Word w : wordList) 
			if (w.length() == length)
				filteredList.add(w);
		
		Random rn = new Random();
		int word = rn.nextInt(filteredList.size());
		
		return filteredList.get(word);
	}
}
