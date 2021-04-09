package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TextFileReaderTest {

	@Test
	void test_TextFileReaderString() {
		String expectedfile = "src/test/java/tabToXml/GuitarTab.txt";
		
		TextFileReader tfr = new TextFileReader(expectedfile);	
		
		String actualfile = tfr.getFile().getName();
		expectedfile = expectedfile.substring(expectedfile.lastIndexOf('/')+1);
		
		assertEquals(expectedfile,actualfile);
	}

	@Test
	void test_TextFileReaderFile() {
		String expectedfile = "src/test/java/tabToXml/DrumsTab.txt";
			
		TextFileReader tfr = new TextFileReader(expectedfile);	
		
		String actualfile = tfr.getFile().getName();
		expectedfile = expectedfile.substring(expectedfile.lastIndexOf('/')+1);
		
		assertEquals(expectedfile,actualfile);
	}

	@Test
	void test_CreateparsedTab() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("|-----------0-----|-0---------------||");
		expected.add("|---------0---0---|-0---------------||");
		expected.add("|-------1-------1-|-1---------------||");
		expected.add("|-----2-----------|-2---------------||");
		expected.add("|---2-------------|-2---------------||");
		expected.add("|-0---------------|-0---------------||");
				
		TextFileReader tfr = new TextFileReader("src/test/java/tabToXml/GuitarTab.txt");
		
		ArrayList<String> actual = tfr.getParsed();
		
		assertEquals(actual, expected);			
	}

//	@Test
//	void testDetectInstrument() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	void testNumberOfLines() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	void testStaffLines() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	void testGetParsedString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	void testGetParsed() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	void testPrintOrginal() {
//		fail("Not yet implemented"); // TODO
//	}
	
	@Test
	public void testReader2() {
		TextFileReader fileReader = new TextFileReader("src/test/java/tabToXml/DrumsTab.txt");
		String expected = "Drum";
		String actual = fileReader.getDetectedInstrument();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testReader3() {
		TextFileReader fileReader = new TextFileReader("src/test/java/tabToXml/GuitarTab.txt");
		String expected = "Guitar";
		String actual = fileReader.getDetectedInstrument();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_TuningLetterDetection() {
		TextFileReader fileReader = new TextFileReader("src/test/java/tabToXml/GuitarTab.txt");
		
		assertAll("", 
				() -> assertEquals(fileReader.getStringChars().get(0).charAt(0), 'E'),
				() -> assertEquals(fileReader.getStringChars().get(1).charAt(0), 'D'),
				() -> assertEquals(fileReader.getStringChars().get(2).charAt(0), 'G'),
				() -> assertEquals(fileReader.getStringChars().get(3).charAt(0), 'B'),
				() -> assertEquals(fileReader.getStringChars().get(4).charAt(0), 'A'),
				() -> assertEquals(fileReader.getStringChars().get(5).charAt(0), 'E')

				);
	}
}
