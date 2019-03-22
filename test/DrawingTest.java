package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Drawing;

class DrawingTest {

	Drawing sut;

	@Test
	void shouldThrowExceptionForIncorrectIndexInput() {
		sut = new Drawing();
		assertThrows(IndexOutOfBoundsException.class, () ->  sut.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () ->  sut.get(20));
	}
	
	@Test
	void shouldReturnNullForAttemptedAccessBeforeDrawingIsSet() {
		sut = new Drawing();
		assertEquals(sut.get(4), null);
	}
	
	@Test
	void shouldReturnStringForAccessAfterDrawingIsSet() {
		sut = new Drawing();
		sut.setDrawingToHangman();
		assertTrue(sut.get(4) instanceof String);
	}

}
