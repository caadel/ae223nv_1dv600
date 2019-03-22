package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import project.Word;
import project.WordList;

class WordListTest {
	WordList sut;
	
	@Test
	void shouldContainDownloadedWords() throws IOException {
		sut = new WordList();
		
		assertTrue(sut.size() > 0);
	}
	@Test
	void shouldReturnWord() throws IOException {
		sut = new WordList();
		
		assertTrue(sut.chooseRandomWord() != null);
	}
	@Test
	void shouldReturnFromWordList() throws IOException {
		sut = new WordList();
		
		assertTrue(sut.getWords().contains(sut.chooseRandomWord()));
	}
	@Test
	void shouldReturnWordOfCorrectSizeIfCalledOnFilledList() throws IOException {
		sut = new WordList();
		
		assertEquals(sut.chooseRandomWordOfLength(2).length(), 2);
		assertEquals(sut.chooseRandomWordOfLength(8).length(), 8);
		assertEquals(sut.chooseRandomWordOfLength(17).length(), 17);
	}
	@Test
	void shouldThrowExceptionIfCalledWithIncorrectWordSize() throws IOException {
		sut = new WordList();
		
		assertThrows(IllegalArgumentException.class, () -> sut.chooseRandomWordOfLength(0));
		assertThrows(IllegalArgumentException.class, () -> sut.chooseRandomWordOfLength(20));
	}
	@Test //Failing test
	void shouldReturnTrueIfWordListContainsWord() throws IOException {
		sut = new WordList();
		Word wordToSearchFor = new Word("ox");
		
		assertTrue(sut.contains(wordToSearchFor));
	}
}
