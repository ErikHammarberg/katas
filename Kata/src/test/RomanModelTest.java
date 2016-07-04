package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import romanNumerals.RomanModel;

public class RomanModelTest {

	RomanModel romanModelSUT;
	@Before
	public void setUp() throws Exception {
		romanModelSUT = new RomanModel();
	}

	@After
	public void tearDown() throws Exception {
		romanModelSUT = null;
	}

	
	@Test
	public void testOne()  {
		String expectedOutcome = "I";
		String result = romanModelSUT.convertArabToRoman(1);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testFive() {
		String expectedOutcome = "V";
		String result = romanModelSUT.convertArabToRoman(5);
		assertEquals(expectedOutcome, result);
	}

	@Test
	public void testThree() {
		String expectedOutcome = "III";
		String result = romanModelSUT.convertArabToRoman(3);
		assertEquals(expectedOutcome, result);
	}
}
