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
		
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(1);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testFive() {
		String expectedOutcome = "V";
		
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(5);
		assertEquals(expectedOutcome, result);
	}

	@Test
	public void testThree() {
		String expectedOutcome = "III";
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(3);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testFour() {
		String expectedOutcome = "IV";
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(4);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testNine() {
		String expectedOutcome = "IX";
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(9);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testHastings() {
		String expectedOutcome = "MLXVI";System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(1066);
		assertEquals(expectedOutcome, result);
	}
	
	@Test
	public void testBerlinUnited() {
		String expectedOutcome = "MCMLXXXIX";
		System.out.println("Expected outcome: " + expectedOutcome);
		String result = romanModelSUT.convertArabToRoman(1989);
		assertEquals(expectedOutcome, result);
	}
}
