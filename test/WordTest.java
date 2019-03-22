package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Word;

class WordTest {
	Word sut;
	
	@Test
	void addCharactersToWordShouldUpdateWordLength() {
		sut = new Word();
		
		sut.add("Text");
		int expected = 4;
		int actual = sut.length();
		assertEquals(expected, actual);
		
		sut.add('X');
		expected++;
		actual = sut.length();
		assertEquals(expected, actual);
	}
	@Test
	void addCharactersToWordShouldUpdateStringContent() {
		sut = new Word();
		
		assertTrue(sut.toString() == "");
		
		sut.add("Text");
		assertTrue(sut.toString().equals("Text"));
		
		sut.add('X');
		assertTrue(sut.toString().equals("TextX"));
	}
	@Test
	void emptyWordShouldReturnZeroLength() {
		sut = new Word();
		assertTrue(sut.length() == 0);
	}
	@Test
	void nonEmptyWordShouldNotReturnZeroLength() {
		sut = new Word();
		sut.setWord("Test");
		assertFalse(sut.length() == 0);
	}
	@Test
	void hiddenWordShouldBeTwiceTheLengthOfWord() {
		sut = new Word("Test");
		assertTrue(sut.getHiddenWord().length() == sut.length()*2);
	}
	@Test
	void shouldReturnLowerCaseWord() {
		sut = new Word("Test");
		assertTrue(sut.toLowerCase().toString().equals("test"));
	}
	@Test
	void shouldReturnTrueIfWordEqualsOtherWord() {
		sut = new Word("Test");
		Word sut2 = new Word("Test");
		assertTrue(sut.equals(sut2));
	}
	@Test
	void shouldReturnFalseIfWordIsNotEqualToOtherWord() {
		sut = new Word("Test");
		Word sut2 = new Word("k");
		assertFalse(sut.equals(sut2));
	}
	@Test
	void shouldReturnFalseIfWordIsNotEqualToOtherObject() {
		sut = new Word("Test");
		String sut2 = "Test";
		assertFalse(sut.equals(sut2));
	}
	@Test
	void hiddenWordShouldCorrectlyConsistOfSpacesAndUnderscores() {
		// Checks that the hidden word is correct ("Test" = "_ _ _ _ ")
		sut = new Word("Test");

		for (int i = 0; i < sut.toString().length(); i++) {
			if (i%2 == 0)
				assertTrue(sut.getHiddenWord().charAt(i) == '_');
			else 
				assertTrue(sut.getHiddenWord().charAt(i) == ' ');
		}
	}
	@Test
	void compareWordToWordWithLargerUnicodeShouldReturnFirstWordIsSmaller() {
		sut = new Word("Test");
		assertTrue(sut.compareTo(new Word("Tes")) > 0);
	}
	@Test
	void compareWordToWordWithSmallerUnicodeShouldReturnFirstWordIsLarger() {
		sut = new Word("Test");
		assertTrue(sut.compareTo(new Word("Testt")) < 0);
	}
	@Test
	void compareWordToWordWithSameUnicodeShouldReturnEqual() {
		sut = new Word("Test");
		assertTrue(sut.compareTo(new Word("Test")) == 0);
	}
	@Test
	void shouldReturnTrueIfWordContainsCharacter() {
		sut = new Word("Test");
		assertTrue(sut.contains('T'));
	}
	@Test
	void shouldReturnFalseIfWordDoesNotContainCharacter() {
		sut = new Word("Test");
		assertFalse(sut.contains('F'));
	}
	@Test
	void shouldShowCharacterInHiddenWordIfWordContainsCharacter() {
		sut = new Word("Test");
		sut.updateHiddenWordIfContains('e');
		
		assertTrue(sut.getHiddenWord().toString().equals("_ e _ _ "));
		
		sut = new Word("Test-case");
		sut.updateHiddenWordIfContains('e');
		
		assertTrue(sut.getHiddenWord().toString().equals("_ e _ _ - _ _ _ e "));
	}
	@Test
	void shouldRevealHiddenWordIfCrrectWordIsGuessed() {
		sut = new Word("Test");
		sut.updateHiddenWordIfContains(new Word("Test"));
		
		System.out.println(sut.getHiddenWord());
		assertTrue(sut.getHiddenWord().toString().equals("T e s t "));
		
 }

}