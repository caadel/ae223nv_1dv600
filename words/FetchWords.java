package ae223nv_1dv600.words;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FetchWords {
	
	private ArrayList<String> wordList;

	public FetchWords() throws IOException {
		wordList = new ArrayList<String>();
		fetch();
	}
	
	private void fetch() throws IOException {
		URL url = new URL("http://www.desiquintans.com/downloads/nounlist/nounlist.txt");
		Scanner textFile = new Scanner(url.openStream()); 
		
		while (textFile.hasNext())
			wordList.add(textFile.next());
		
		textFile.close();
	}
	
	public String chooseRandomWord() {
		Random rn = new Random();
		int word = rn.nextInt(wordList.size());
		
		return wordList.get(word);
	}
	
	public String chooseRandomWordOfLength(int length) {
		var filteredList = new ArrayList<String>();
		
		for (String s : wordList) 
			if (s.length() == length)
				filteredList.add(s);
		
		Random rn = new Random();
		int word = rn.nextInt(filteredList.size());
		
		return filteredList.get(word);
	}
}
