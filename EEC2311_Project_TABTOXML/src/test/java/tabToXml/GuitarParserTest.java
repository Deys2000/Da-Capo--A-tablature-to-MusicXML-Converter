package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GuitarParserTest {

	// Duration Tests
	@Test
	void testDuration1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------00----|");
		test.add("B|---------00--00--|");
		test.add("G|-------11------11|");
		test.add("D|-----22----------|");
		test.add("A|---22------------|");
		test.add("E|-00--------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "1", "1", "1", "1", "1", "1", "1", "1",
				   				"1", "1", "1", "1", "1", "1", "1", "1", "||"};
		   
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
	void testDuration2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------0-----|");
		test.add("B|---------0---0---|");
		test.add("G|-------1-------1-|");
		test.add("D|-----2-----------|");
		test.add("A|---2-------------|");
		test.add("E|-0---------------|");
		
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "||"};
		
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
		String[] arr = {"|", "4", "4", "4", "4", "||"};
		
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
		String[] arr = {"|", "8", "8", "||"};
		
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
		String[] arr = {"|", "16", "||"};
		   
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
		test.add("G|---------------11|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "8", "4", "2", "1", "1", "||"};
		
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
		String[] arr = {"|", "16", "16", "16", "16", "16", "16", "||"};
		   
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
		test.add("G|---------0-----11|");
		test.add("D|-----------------|");
		test.add("A|-0--------------1|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "8", "8", "4", "4", "2", "2", "1", "1", "1", "1", "||"};
		   
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
		String[] arr = {"|", "3", "5", "7", "1", "||"};
		   
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
	void testType1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------00----|");
		test.add("B|---------00--00--|");
		test.add("G|-------11------11|");
		test.add("D|-----22----------|");
		test.add("A|---22------------|");
		test.add("E|-00--------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "16th", "16th", "16th", "16th", "16th", "16th", "16th", "16th",
				   				"16th", "16th", "16th", "16th", "16th", "16th", "16th", "16th", "||"};
		   
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
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "||"};
		
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
		String[] arr = {"|", "quarter", "quarter", "quarter", "quarter", "||"};
		
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
		String[] arr = {"|", "half", "half", "||"};
		
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
		String[] arr = {"|", "whole", "||"};
		   
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
		test.add("G|---------------11|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "half", "quarter", "eighth", "16th", "16th", "||"};
		
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
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "||"};
		   
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
		test.add("G|---------0-----11|");
		test.add("D|-----------------|");
		test.add("A|-0--------------1|");
		test.add("E|-0---------------|");
		   
		ArrayList<String> expected = new ArrayList<>();
		String[] arr = {"|", "half", "half", "quarter", "quarter", "eighth", "eighth", "16th", "16th", "16th", "16th", "||"};
		   
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
		String[] arr = {"|", "16th", "16th", "16th", "16th", "||"};
		   
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
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "||"};
		   
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
	
	// comment for merging
}