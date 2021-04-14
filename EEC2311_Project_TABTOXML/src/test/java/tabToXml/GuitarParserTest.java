package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class GuitarParserTest {

	/*// Commenting out old tests
//	// Duration Tests
	@Test
	void testDuration2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 
	@Test
	void testDuration3() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0---0---|");
		test.add("G|-----------------|");
		test.add("D|-----2-----------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "4", "4", "4", "4", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 
	@Test
	void testDuration4() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0-------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "8", "8", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}	
	 
	@Test
	void testDuration5() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|-----------------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "16", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	 
	@Test
	void testDuration6() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0---0---|");
		test.add("G|---------------1-|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "8", "4", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}	
	 
	@Test 
	void testDuration7() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "16", "16", "16", "16", "16", "16", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	 
	@Test 
	void testDuration8() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-------------0-1-|");
		test.add("B|---------0---0---|");
		test.add("G|---------0-----1-|");
		test.add("D|-----------------|");
		test.add("A|-0---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "8", "8", "4", "4", "2", "2", "2", "2", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		   
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	 
	@Test
	void testDuration9() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0-------|");
		test.add("G|----------------1|");
		test.add("D|-----------------|");
		test.add("A|----2------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "3", "5", "7", "1", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		   
		GuitarParser guitarParser = new GuitarParser(test);
		   
		ArrayList<String> actual = guitarParser.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
	 }
	
	// Type Tests
	@Test
	void testDottedHalfNote() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-3-----------|");
		test.add("B|-------------|");
		test.add("G|-------------|");
		test.add("D|-------------|");
		test.add("A|-------------|");
		test.add("E|-------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "dotted half", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void testType2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 
	@Test
	void testType3() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0---0---|");
		test.add("G|-----------------|");
		test.add("D|-----2-----------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "quarter", "quarter", "quarter", "quarter", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 
	@Test
	void testType4() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0-------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "half", "half", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}	
	 
	@Test
	void testType5() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|-----------------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	 
	@Test
	void testType6() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0---0---|");
		test.add("G|---------------1-|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "half", "quarter", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}	
	 
	@Test 
	void testType7() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	 
	@Test 
	void testType8() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-------------0-1-|");
		test.add("B|---------0---0---|");
		test.add("G|---------0-----1-|");
		test.add("D|-----------------|");
		test.add("A|-0---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "half", "half", "quarter", "quarter", "eighth", "eighth", "eighth", "eighth", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		   
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	@Test
	void testType9() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|---------0-------|");
		test.add("G|----------------1|");
		test.add("D|-----------------|");
		test.add("A|----2------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "dotted eighth", "quarter", "dotted quarter", "16th", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		   
		GuitarParser guitarParser = new GuitarParser(test);
		   
		ArrayList<String> actual = guitarParser.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
	 }
	
	// Notes Tests
	@Test
	void testNotes1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "E2", "B2", "E3", "G3", "B3", "E4", "B3", "G3", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected notes: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getNotes();
		
		System.out.println("actual notes: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 	 
	@Test 
	void testNotes2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	// Fret Number Tests
	@Test
	void testFretNumber1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "0", "2", "2", "1", "0", "0", "0", "1", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected fret numbers: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getFretNums();
		
		System.out.println("actual fert numbers: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 	 
	@Test 
	void testFretNumber2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "0", "0", "1", "2", "2", "0", "||"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected fret numbers: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getFretNums();
		
		System.out.println("actual fret numbers: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	// Fret String Tests
	@Test
	void testFretString1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "6", "5", "4", "3", "2", "1", "2", "3", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected fret strings: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getFretStrings();
		
		System.out.println("actual fert strings: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 	 
	@Test 
	void testFretString2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "1", "2", "3", "4", "5", "6", "||"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected fret strings: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getFretStrings();
		
		System.out.println("actual fret strings: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	// Chord Tests
	@Test
	void testChord1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "false", "false", "false", "false", "false", "false", "false", "false", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected chord values: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getChordArr();
		
		System.out.println("actual chord values: \t" + actual);
		
		assertEquals(expected,actual);
	}
	 	 
	@Test 
	void testChord2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-0---------------|");
		test.add("B|-0---------------|");
		test.add("G|-1---------------|");
		test.add("D|-2---------------|");
		test.add("A|-2---------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "false", "true", "true", "true", "true", "true", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected chord values: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getChordArr();
		
		System.out.println("actual chord values: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	
	//DoubleDigit Tests for rhythmParser
	@Test
	void testDoubleDigitDuration1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|------------0-----|");
		test.add("B|----------0---0---|");
		test.add("G|--------1-------1-|");
		test.add("D|------2-----------|");
		test.add("A|----2-------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void testDoubleDigitDuration2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-11---------0-----|");
		test.add("B|----------0---0---|");
		test.add("G|--------1-------1-|");
		test.add("D|------2-----------|");
		test.add("A|----2-------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void testDoubleDigitDuration3() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-11---------------|");
		test.add("B|--0---------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "16", "16", "16", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	//DoubleDigit Tests for rhythmParser
	@Test
	void testDoubleDigitType1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|------------0-----|");
		test.add("B|----------0---0---|");
		test.add("G|--------1-------1-|");
		test.add("D|------2-----------|");
		test.add("A|----2-------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void testDoubleDigitType2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-11---------0-----|");
		test.add("B|----------0---0---|");
		test.add("G|--------1-------1-|");
		test.add("D|------2-----------|");
		test.add("A|----2-------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void testDoubleDigitType3() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-11---------------|");
		test.add("B|--0---------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|-10---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "whole", "whole", "whole", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		GuitarParser guitarParser = new GuitarParser(test);
		
		ArrayList<String> actual = guitarParser.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}

	//Added March
	@Test
	void testHammeron1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-7h9--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------1h2------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "hstart", "hstop", "hstart", "hstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual hammeron: \t" + actual);

		assertEquals(expected,actual);
	}

	//Added March
	@Test
	void testPulloff1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-9p7--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------2p1------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "pstart", "pstop", "pstart", "pstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual pull: \t" + actual);

		assertEquals(expected,actual);
	}

	@Test
	void testHammeronAndPulloff() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-7h9p6------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------2p1h2----|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "hstart", "HstopPstart", "pstop", "pstart", "PstopHstart", "hstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected sequential HandP: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual sequential HandP: \t" + actual);

		assertEquals(expected,actual);
	}

	@Test
	void testBend1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-7b9--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------1b2------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "b2", "neutral", "b1", "neutral", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual bend: \t" + actual);

		assertEquals(expected,actual);
	}

	@Test
	void testRlease1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-9r7--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------2r1------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "b-2", "neutral", "b-1", "neutral", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected release: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual release: \t" + actual);

		assertEquals(expected,actual);
	}

	//-----------
	@Test
	void testAscedingSlide() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-7/9--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------1/2------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "astart", "astop", "astart", "astop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected ascending slide: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual ascending slide: \t" + actual);

		assertEquals(expected,actual);
	}

	@Test
	void testDescendingSlide() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-9\\7--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|--------4\\2-------|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "dstart", "dstop", "dstart", "dstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected descending slide*: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual descending slide*: \t" + actual);

		assertEquals(expected,actual);
	}


	@Test
	void testSequentialDandASlide() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-7/9\\2------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------1/2\\1----|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "astart", "AstopDstart", "dstop", "astart", "AstopDstart", "dstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected sequential slide*: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual sequential slide*: \t" + actual);

		assertEquals(expected,actual);
	}

	@Test
	void testLegatoSlide() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-3s9--------------|");
		test.add("B|------------------|");
		test.add("G|------------------|");
		test.add("D|------------------|");
		test.add("A|------------------|");
		test.add("E|---------10s12----|");

		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "sstart", "sstop", "sstart", "sstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected legato slide: \t" + expected);

		GuitarParser guitarParser = new GuitarParser(test);

		ArrayList<String> actual = guitarParser.getHandPArr();

		System.out.println("actual legato slide: \t" + actual);

		assertEquals(expected,actual);
	}
	*/
	
	// Grace Note tests
	@Test
	void testGrace1() throws Exception {
		
		String test = "|-----------0-----|-0---------------|\n"
					+ "|---------0---0---|-0---------------|\n"
					+ "|-------1-------1-|-1---------------|\n"
					+ "|-----2-----------|-2---------------|\n"
					+ "|---2-------------|-2---------------|\n"
					+ "|-0---------------|-0---------------|";
		
		TextFileReader tfr;
		GuitarParser gp;
		
		File newFile = new File("testFile");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(newFile);
			myWriter.write(test + "\n\n");
			myWriter.close();
			
			tfr = new TextFileReader(newFile);
			gp = new GuitarParser(tfr);
			
			ArrayList<String> expected = new ArrayList<>();
			String[] arr = {"|", "false", "false", "false", "false", "false", "false", "false", "false", 
							"|", "false", "false", "false", "false", "false", "false", "|"};

			for (int i = 0; i < arr.length; i++) {
				expected.add(arr[i]);
			}	

			System.out.println("expected grace note array: \t" + expected);

			ArrayList<String> actual = gp.getGraceArr();

			System.out.println("actual grace note array: \t" + actual);

			assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	@Test
	void testGrace2() throws Exception {
		
		String test = "|-----------0-----|-0---g---0-------|\n"
					+ "|-----------------|-----------------|\n"
					+ "|------p1-0---0h1-|-----------------|\n"
					+ "|-----------------|-----------------|\n"
					+ "|--g2-2-----------|-----------------|\n"
					+ "|-0---------------|-----------------|";
		
		TextFileReader tfr;
		GuitarParser gp;
		
		File newFile = new File("testFile");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(newFile);
			myWriter.write(test + "\n\n");
			myWriter.close();
			
			tfr = new TextFileReader(newFile);
			gp = new GuitarParser(tfr);
			
			ArrayList<String> expected = new ArrayList<>();
			String[] arr = {"|", "false", "false", "false", "false", "false", "false", "false", "false", 
							"|", "false", "false", "|"};

			for (int i = 0; i < arr.length; i++) {
				expected.add(arr[i]);
			}	

			System.out.println("expected grace note array: \t" + expected);

			ArrayList<String> actual = gp.getGraceArr();

			System.out.println("actual grace note array: \t" + actual);

			assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	@Test
	void testGrace3() throws Exception {
		
		String test = "|----------g0h4p0-|\n"
					+ "|-----------------|\n"
					+ "|------g1p0-------|\n"
					+ "|-----------------|\n"
					+ "|--g2h3-----------|\n"
					+ "|-0---------------|";
		
		TextFileReader tfr;
		GuitarParser gp;
		
		File newFile = new File("testFile");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(newFile);
			myWriter.write(test + "\n\n");
			myWriter.close();
			
			tfr = new TextFileReader(newFile);
			gp = new GuitarParser(tfr);
			
			ArrayList<String> expected = new ArrayList<>();
			String[] arr = {"|", "false", "true", "false", "true", "false", "true", "true", "false", "|"};

			for (int i = 0; i < arr.length; i++) {
				expected.add(arr[i]);
			}	

			System.out.println("expected grace note array: \t" + expected);

			ArrayList<String> actual = gp.getGraceArr();

			System.out.println("actual grace note array: \t" + actual);

			assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
}