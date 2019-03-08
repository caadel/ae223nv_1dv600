package test;

import java.util.ArrayList;
import java.util.Random;
import project.Word;

public class WordListMock {
	
	private ArrayList<Word> wordList;

	public WordListMock() {
		wordList = new ArrayList<Word>();
	}
	
	public void fillWithNouns() {
		/*
		 *  In the real WordList, this methods downloads a list of several
		 *  thousand words, instead of only 16. This can't really be tested,
		 *  so this mock was created instead.
		 */
		wordList.add(new Word("ox"));
		wordList.add(new Word("act"));
		wordList.add(new Word("bell"));
		wordList.add(new Word("canal"));
		wordList.add(new Word("saloon"));
		wordList.add(new Word("grammar"));
		wordList.add(new Word("cloister"));
		wordList.add(new Word("digestive"));
		wordList.add(new Word("chopsticks"));
		wordList.add(new Word("abnormality"));
		wordList.add(new Word("supernatural"));
		wordList.add(new Word("semiconductor"));
		wordList.add(new Word("recommendation"));
		wordList.add(new Word("experimentation"));
		wordList.add(new Word("characterization"));
		wordList.add(new Word("revascularization"));
	}
	
	public Word chooseRandomWord() {
		if (wordList.size() == 0)
			throw new NullPointerException("WordList is empty!");
		/*
		 * This method should choose a random word, which can't be tested either.
		 * The method instead always picks the same word.
		 */
		return wordList.get(0);
	}
	
	public Word chooseRandomWordOfLength(int length) {
		if (wordList.size() == 0)
			throw new NullPointerException("WordList is empty!");
		/*
		 * This method did not need to be changed from the implementation, 
		 * because only one word per length was created above. This means that
		 * the "random" selection can only pick one single word.
		 */
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
	
	public boolean contains(Word w) {
		/*for (Word w2 : wordList)
			if (w.toString().equals(w2.toString()))
				return true;*/
		
		return false;
	}
}
