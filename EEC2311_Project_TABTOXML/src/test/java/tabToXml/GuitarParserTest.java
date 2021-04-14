package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class GuitarParserTest {

	
//	// Duration Tests
	@Test
	void testDuration2() throws Exception {
		
		String test = "E|-----------0-----|\n"
					+ "B|---------0---0---|\n"
					+ "G|-------1-------1-|\n"
					+ "D|-----2-----------|\n"
					+ "A|---2-------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
				
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	 
	 
	@Test
	void testDuration3() throws Exception {
		
		String test = "E|-----------------|\n"
					+ "B|---------0---0---|\n"
					+ "G|-----------------|\n"
					+ "D|-----2-----------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "4", "4", "4", "4", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		
		System.out.println("expected duration: \t" + expected);
		
		
		ArrayList<String> actual = gp.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	 
	
	@Test
	void testDuration4() throws Exception {
		
		String test = "E|-----------------|\n"
					+ "B|---------0-------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "8", "8", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		
		
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
				}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}	
	 
	@Test
	void testDuration5() throws Exception {
		
		String test = "E|-----------------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "16", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
				
		ArrayList<String> actual = gp.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
		}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 
	 
	@Test
	void testDuration6() throws Exception {
		
		String test = "E|-----------------|\n"
					+ "B|---------0---0---|\n"
					+ "G|---------------1-|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "8", "4", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
				
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
		}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}	
	 
	 
	@Test 
	void testDuration7() throws Exception {
		String test = "E|-0---------------|\n"
					+ "B|-0---------------|\n"
					+ "G|-1---------------|\n"
					+ "D|-2---------------|\n"
					+ "A|-2---------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "16", "16", "16", "16", "16", "16", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
				
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
		}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 
	@Test 
	void testDuration8() throws Exception {
		String test = "E|-------------0-1-|\n"
					+ "B|---------0---0---|\n"
					+ "G|---------0-----1-|\n"
					+ "D|-----------------|\n"
					+ "A|-0---------------|\n"
					+ "E|-0---------------|";
		
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
		String[] arr = {"|", "8", "8", "4", "4", "2", "2", "2", "2", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		   		
		ArrayList<String> actual = gp.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	 
	@Test
	void testDuration9() throws Exception {
		String test = "E|-----------------|\n"
					+ "B|---------0-------|\n"
					+ "G|----------------1|\n"
					+ "D|-----------------|\n"
					+ "A|----2------------|\n"
					+ "E|-0---------------|";
	
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
		String[] arr = {"|", "3", "5", "7", "1", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected duration: \t" + expected);
		   
		   
		ArrayList<String> actual = gp.getDurationArr();
		   
		System.out.println("actual duration: \t" + actual);
		   
		assertEquals(expected,actual);
	}
	catch (Exception e) { 
		e.printStackTrace(); 
	}
	 }
	
//	// Type Tests
//	@Test
//	void testDottedHalfNote() throws Exception {
//		
//		String test = "E|-3---------------|\n"
//					+ "B|-----------------|\n"
//					+ "G|-----------------|\n"
//					+ "D|-----------------|\n"
//					+ "A|-----------------|\n"
//					+ "E|-----------------|";
//
//TextFileReader tfr;
//GuitarParser gp;
//
//File newFile = new File("testFile");
//FileWriter myWriter = null;
//
//try {
//	myWriter = new FileWriter(newFile);
//	myWriter.write(test + "\n\n");
//	myWriter.close();
//	
//	tfr = new TextFileReader(newFile);
//	gp = new GuitarParser(tfr);
//
//		ArrayList<String> expected = new ArrayList<>();
//		String[] arr = {"|", "dotted half", "|"};
//		
//		for (int i = 0; i < arr.length; i++) {
//			expected.add(arr[i]);
//		}	
//		
//		System.out.println("expected type: \t" + expected);
//		
//		
//		ArrayList<String> actual = gp.getTypeArr();
//		
//		System.out.println("actual type: \t" + actual);
//		
//		assertEquals(expected,actual);
//}
//catch (Exception e) { 
//	e.printStackTrace(); 
//}
//	}
	
	@Test
	void testType2() throws Exception {
		
		String test = "E|-----------0-----|\n"
					+ "B|---------0---0---|\n"
					+ "G|-------1-------1-|\n"
					+ "D|-----2-----------|\n"
					+ "A|---2-------------|\n"
					+ "E|-0---------------|";
	
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
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
				
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
	}
	catch (Exception e) { 
		e.printStackTrace(); 
	}
	}
	 
	@Test
	void testType3() throws Exception {
		
		String test = "E|-----------------|\n"
					+ "B|---------0---0---|\n"
					+ "G|-----------------|\n"
					+ "D|-----2-----------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";

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
		String[] arr = {"|", "quarter", "quarter", "quarter", "quarter", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 
	@Test
	void testType4() throws Exception {
		String test = "E|-----------------|\n"
					+ "B|---------0-------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|-0---------------|";

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
		String[] arr = {"|", "half", "half", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}	
	 
	@Test
	void testType5() throws Exception {
		
		String test = "E|-----------------|\n"
				+ "B|-----------------|\n"
				+ "G|-----------------|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 
	@Test
	void testType6() throws Exception {
		
		String test = "E|-----------------|\n"
				+ "B|---------0---0---|\n"
				+ "G|---------------1-|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "half", "quarter", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}	
	 
	@Test 
	void testType7() throws Exception {
		
		String test = "E|-0---------------|\n"
				+ "B|-0---------------|\n"
				+ "G|-1---------------|\n"
				+ "D|-2---------------|\n"
				+ "A|-2---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 
	@Test 
	void testType8() throws Exception {
		
		String test = "E|-------------0-1-|\n"
				+ "B|---------0---0---|\n"
				+ "G|---------0-----1-|\n"
				+ "D|-----------------|\n"
				+ "A|-0---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "half", "half", "quarter", "quarter", "eighth", "eighth", "eighth", "eighth", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		   
		
		ArrayList<String> actual = gp.getTypeArr();
		   
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
//	@Test
//	void testType9() throws Exception {
//		
//		String test = "E|-----------------|\n"
//					+ "B|---------0-------|\n"
//					+ "G|---------------1-|\n"
//					+ "D|-----------------|\n"
//					+ "A|---2-------------|\n"
//					+ "E|-0---------------|";
//
//TextFileReader tfr;
//GuitarParser gp;
//
//File newFile = new File("testFile");
//FileWriter myWriter = null;
//
//try {
//myWriter = new FileWriter(newFile);
//myWriter.write(test + "\n\n");
//myWriter.close();
//
//tfr = new TextFileReader(newFile);
//gp = new GuitarParser(tfr);
//
//		   
//		ArrayList<String> expected = new ArrayList<>();
//		String[] arr = {"|", "dotted eighth", "quarter", "dotted quarter", "16th", "|"};
//		   
//		for (int i = 0; i < arr.length; i++) {
//			expected.add(arr[i]);
//		}
//		   
//		System.out.println("expected type: \t" + expected);
//		   
//		   
//		ArrayList<String> actual = gp.getTypeArr();
//		   
//		System.out.println("actual type: \t" + actual);
//		   
//		assertEquals(expected,actual);
//}
//catch (Exception e) { 
//	e.printStackTrace(); 
//}
//	 }
	
	
	// Notes Tests
	@Test
	void testNotes1() throws Exception {
		String test = "E|-----------0-----|\n"
					+ "B|---------0---0---|\n"
					+ "G|-------1-------1-|\n"
					+ "D|-----2-----------|\n"
					+ "A|---2-------------|\n"
					+ "E|-0---------------|";

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
		String[] arr = {"|", "E2", "B2", "E3", "G3", "B3", "E4", "B3", "G3", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected notes: \t" + expected);
		
		
		ArrayList<String> actual = gp.getNotes();
		
		System.out.println("actual notes: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 	 
	@Test 
	void testNotes2() throws Exception {
		
		
		String test = "E|-0---------------|\n"
				+ "B|-0---------------|\n"
				+ "G|-1---------------|\n"
				+ "D|-2---------------|\n"
				+ "A|-2---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "whole", "whole", "whole", "whole", "whole", "whole", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	// Fret Number Tests
	@Test
	void testFretNumber1() throws Exception {
		
		String test = "E|-----------0-----|\n"
				+ "B|---------0---0---|\n"
				+ "G|-------1-------1-|\n"
				+ "D|-----2-----------|\n"
				+ "A|---2-------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "0", "2", "2", "1", "0", "0", "0", "1", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected fret numbers: \t" + expected);
		
		
		ArrayList<String> actual = gp.getFretNums();
		
		System.out.println("actual fert numbers: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 	 
	@Test 
	void testFretNumber2() throws Exception {
		
		String test = "E|-0---------------|\n"
				+ "B|-0---------------|\n"
				+ "G|-1---------------|\n"
				+ "D|-2---------------|\n"
				+ "A|-2---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "0", "0", "1", "2", "2", "0", "||"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected fret numbers: \t" + expected);
		
		
		ArrayList<String> actual = gp.getFretNums();
		
		System.out.println("actual fret numbers: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	// Fret String Tests
	@Test
	void testFretString1() throws Exception {
		String test = "E|-----------0-----|\n"
				+ "B|---------0---0---|\n"
				+ "G|-------1-------1-|\n"
				+ "D|-----2-----------|\n"
				+ "A|---2-------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "6", "5", "4", "3", "2", "1", "2", "3", "||"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected fret strings: \t" + expected);
		
		
		ArrayList<String> actual = gp.getFretStrings();
		
		System.out.println("actual fert strings: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 	 
	@Test 
	void testFretString2() throws Exception {
		String test = "E|-0---------------|\n"
				+ "B|-0---------------|\n"
				+ "G|-1---------------|\n"
				+ "D|-2---------------|\n"
				+ "A|-2---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "1", "2", "3", "4", "5", "6", "||"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected fret strings: \t" + expected);
		
		ArrayList<String> actual = gp.getFretStrings();
		
		System.out.println("actual fret strings: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	// Chord Tests
	@Test
	void testChord1() throws Exception {
		String test = "E|-----------0-----|\n"
				+ "B|---------0---0---|\n"
				+ "G|-------1-------1-|\n"
				+ "D|-----2-----------|\n"
				+ "A|---2-------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "false", "false", "false", "false", "false", "false", "false", "false", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected chord values: \t" + expected);
		
		
		ArrayList<String> actual = gp.getChordArr();
		
		System.out.println("actual chord values: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	 	 
	@Test 
	void testChord2() throws Exception {
		String test = "E|-0---------------|\n"
				+ "B|-0---------------|\n"
				+ "G|-1---------------|\n"
				+ "D|-2---------------|\n"
				+ "A|-2---------------|\n"
				+ "E|-0---------------|";

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
		String[] arr = {"|", "false", "true", "true", "true", "true", "true", "|"};
		   
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}
		   
		System.out.println("expected chord values: \t" + expected);
		
		
		ArrayList<String> actual = gp.getChordArr();
		
		System.out.println("actual chord values: \t" + actual);
		   
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	
	//DoubleDigit Tests for rhythmParser
	@Test
	void testDoubleDigitDuration1() throws Exception {
		
		String test = "E|------------0-----|\n"
				+ "B|----------0---0---|\n"
				+ "G|--------1-------1-|\n"
				+ "D|------2-----------|\n"
				+ "A|----2-------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	@Test
	void testDoubleDigitDuration2() throws Exception {
		
		
		String test = "E|-11---------0-----|\n"
				+ "B|----------0---0---|\n"
				+ "G|--------1-------1-|\n"
				+ "D|------2-----------|\n"
				+ "A|----2-------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2", "2", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	@Test
	void testDoubleDigitDuration3() throws Exception {
		
		String test = "E|-11---------------|\n"
				+ "B|--0---------------|\n"
				+ "G|------------------|\n"
				+ "D|------------------|\n"
				+ "A|------------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "16", "16", "16", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected duration: \t" + expected);
		
		
		ArrayList<String> actual = gp.getDurationArr();
		
		System.out.println("actual duration: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	//DoubleDigit Tests for rhythmParser
	@Test
	void testDoubleDigitType1() throws Exception {
		String test = "E|------------0-----|\n"
				+ "B|----------0---0---|\n"
				+ "G|--------1-------1-|\n"
				+ "D|------2-----------|\n"
				+ "A|----2-------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	@Test
	void testDoubleDigitType2() throws Exception {
		
		String test = "E|-11---------0-----|\n"
				+ "B|----------0---0---|\n"
				+ "G|--------1-------1-|\n"
				+ "D|------2-----------|\n"
				+ "A|----2-------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	@Test
	void testDoubleDigitType3() throws Exception {
		
		String test = "E|-11---------------|\n"
				+ "B|--0---------------|\n"
				+ "G|------------------|\n"
				+ "D|------------------|\n"
				+ "A|------------------|\n"
				+ "E|-10---------------|";

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
		String[] arr = {"|", "whole", "whole", "whole", "|"};
		
		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	
		
		System.out.println("expected type: \t" + expected);
		
		
		ArrayList<String> actual = gp.getTypeArr();
		
		System.out.println("actual type: \t" + actual);
		
		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	//Added March
	@Test
	void testHammeron1() throws Exception {
		
		String test = "E|-7h9-------------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|--------1h2------|";

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
		String[] arr = {"|", "hstart", "hstop", "hstart", "hstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual hammeron: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	//Added March
	@Test
	void testPulloff1() throws Exception {
		
		
		
		String test = "E|-9p7-------------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|--------2p1------|";

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
		String[] arr = {"|", "pstart", "pstop", "pstart", "pstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);

		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual pull: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	@Test
	void testHammeronAndPulloff() throws Exception {
		
		
		String test = "E|-7h9p6-----------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|--------2p1h2----|";

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
		String[] arr = {"|", "hstart", "HstopPstart", "pstop", "pstart", "PstopHstart", "hstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected sequential HandP: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual sequential HandP: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}

	}

	@Test
	void testBend1() throws Exception {
		
		String test = "E|-7b9-------------|\n"
				+ "B|-----------------|\n"
				+ "G|-----------------|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|--------1b2------|";

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
		String[] arr = {"|", "b2", "neutral", "b1", "neutral", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected type: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual bend: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	@Test
	void testRlease1() throws Exception {
		
		String test = "E|-9r7-------------|\n"
				+ "B|-----------------|\n"
				+ "G|-----------------|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|--------2r1------|";

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
		String[] arr = {"|", "b-2", "neutral", "b-1", "neutral", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected release: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual release: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	//-----------
	@Test
	void testAscedingSlide() throws Exception {
		String test = "E|-7/9-------------|\n"
				+ "B|-----------------|\n"
				+ "G|-----------------|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|--------1/2------|";

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
		String[] arr = {"|", "astart", "astop", "astart", "astop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected ascending slide: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual ascending slide: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	@Test
	void testDescendingSlide() throws Exception {
		String test = "E|-9\\7-------------|\n"
				+ "B|-----------------|\n"
				+ "G|-----------------|\n"
				+ "D|-----------------|\n"
				+ "A|-----------------|\n"
				+ "E|--------4\\2------|";

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
		String[] arr = {"|", "dstart", "dstop", "dstart", "dstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected descending slide*: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual descending slide*: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}


	@Test
	void testSequentialDandASlide() throws Exception {
		
		String test = "E|-7/9\\2-----------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|--------1/2\\1----|";

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
		String[] arr = {"|", "astart", "AstopDstart", "dstop", "astart", "AstopDstart", "dstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected sequential slide*: \t" + expected);

		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual sequential slide*: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}

	@Test
	void testLegatoSlide() throws Exception {
		String test = "E|-3s9-------------|\n"
					+ "B|-----------------|\n"
					+ "G|-----------------|\n"
					+ "D|-----------------|\n"
					+ "A|-----------------|\n"
					+ "E|--------10s12----|";

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
		String[] arr = {"|", "sstart", "sstop", "sstart", "sstop", "|"};

		for (int i = 0; i < arr.length; i++) {
			expected.add(arr[i]);
		}	

		System.out.println("expected legato slide: \t" + expected);


		ArrayList<String> actual = gp.getHandPArr();

		System.out.println("actual legato slide: \t" + actual);

		assertEquals(expected,actual);
}
catch (Exception e) { 
	e.printStackTrace(); 
}
	}
	
	
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