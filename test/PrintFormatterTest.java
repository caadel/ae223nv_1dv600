package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.PrintFormatter;

class PrintFormatterTest {
	
	PrintFormatter sut;
	@Test
	void shouldReturnColoredStringForCorrectInput() {
		sut = new PrintFormatter();
		
		assertEquals(sut.color("Test", "white"),"\033[0mTest\033[0m");
		assertEquals(sut.color("Test", "red"),"\033[31mTest\033[0m");
		assertEquals(sut.color("Test", "green"),"\033[32mTest\033[0m");
		assertEquals(sut.color("Test", "yellow"),"\033[33mTest\033[0m");
		assertEquals(sut.color("Test", "blue"),"\033[34mTest\033[0m");
		assertEquals(sut.color("Test", "purple"),"\033[35mTest\033[0m");
		assertEquals(sut.color("Test", "cyan"),"\033[36mTest\033[0m");
	}
	@Test
	void shouldThrowExceptionForWrongInput() {
		sut = new PrintFormatter();
		
		assertThrows(IllegalArgumentException.class, () ->  sut.color("Test", "black"));
	}
	@Test
	void shouldReturnFalseIfStringIsNotColored() {
		sut = new PrintFormatter();
		
		String testString = "Text";
		
		assertFalse(sut.isColored(testString));
	}
	@Test
	void shouldReturnTrueIfStringIsColored() {
		sut = new PrintFormatter();
		
		String testString = "Text";
		
		testString = sut.color(testString, "blue");
		
		assertTrue(sut.isColored(testString));
	}

}
