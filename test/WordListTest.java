package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import project.Word;

class WordListTest {
	WordListMock sut;
	
	@Test
	void shouldThrowExceptionOnEmptyList() {
		sut = new WordListMock();
		
		assertThrows(NullPointerException.class, () -> sut.chooseRandomWord());
		assertThrows(NullPointerException.class, () -> sut.chooseRandomWordOfLength(2));
	}
	@Test
	void shouldReturnWordOnFilledList() {
		sut = new WordListMock();
		sut.fillWithNouns();
		
		assertTrue(sut.chooseRandomWord().compareTo(new Word("ox")) == 0);
	}
	@Test
	void shouldReturnWordOfCorrectSizeIfCalledOnFilledList() {
		sut = new WordListMock();
		sut.fillWithNouns();
		
		assertEquals(sut.chooseRandomWordOfLength(2).length(), 2);
		assertEquals(sut.chooseRandomWordOfLength(8).length(), 8);
		assertEquals(sut.chooseRandomWordOfLength(17).length(), 17);
	}
	@Test
	void shouldThrowExceptionIfCalledWithIncorrectWordSize() {
		sut = new WordListMock();
		sut.fillWithNouns();
		
		assertThrows(IllegalArgumentException.class, () -> sut.chooseRandomWordOfLength(0));
		assertThrows(IllegalArgumentException.class, () -> sut.chooseRandomWordOfLength(20));
	}
	@Test //Failing test
	void shouldReturnTrueIfWordListContainsWord() {
		sut = new WordListMock();
		sut.fillWithNouns();
		Word wordToSearchFor = new Word("ox");
		
		assertTrue(sut.contains(wordToSearchFor));
	}
}
