package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TextFileReaderTest {

	@Test
	void testTextFileReaderString() {
		
		String expectedfile = "GuitarTab.txt";
		
		TextFileReader tfr = new TextFileReader(expectedfile);	
		
		String actualfile = tfr.getFile().getName();
		
		assertEquals(expectedfile,actualfile);
	}

	@Test
	void testTextFileReaderFile() {
		String expectedfile = "DrumsTab.txt";
		
		TextFileReader tfr = new TextFileReader(expectedfile);	
		
		String actualfile = tfr.getFile().getName();
		
		assertEquals(expectedfile,actualfile);
	}

	@Test
	void testCreateparsedTab() {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("E|-----------0-----|-0---------------||");
		expected.add("D|---------0---0---|-0---------------||");
		expected.add("G|-------1-------1-|-1---------------||");
		expected.add("B|-----2-----------|-2---------------||");
		expected.add("A|---2-------------|-2---------------||");
		expected.add("E|-0---------------|-0---------------||");
				
		TextFileReader tfr = new TextFileReader("GuitarTab.txt");
		
		ArrayList<String> actual = tfr.getParsed();
		
		assertEquals(actual, expected);			
	}

	@Test
	void testDetectInstrument() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testNumberOfLines() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testStaffLines() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetParsedString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetParsed() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPrintOrginal() {
		fail("Not yet implemented"); // TODO
	}

}
