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

	@Test
	public void testReader1() {
		TextFileReader fileReader = new TextFileReader("tab.txt");
		
		assertNotNull(fileReader);
	}
	
//	@Test
//	public void testReader2() {
//		TextFileReader fileReader = new TextFileReader("tab.txt");
//		
//		assertEquals(fileReader.detectInstrument(), "Guitar");
//	}
	
//	@Test
//	public void testReader3() {
//		TextFileReader fileReader = new TextFileReader("tab.txt");
//		
//		assertEquals(fileReader.numberOfLines(), 6);
//	}
	
	@Test
	public void testReader4() {
		TextFileReader fileReader = new TextFileReader("tab.txt");
		
		assertAll("", 
				() -> assertEquals(fileReader.getParsed().get(0).charAt(0), 'E'),
				() -> assertEquals(fileReader.getParsed().get(1).charAt(0), 'D'),
				() -> assertEquals(fileReader.getParsed().get(2).charAt(0), 'G'),
				() -> assertEquals(fileReader.getParsed().get(3).charAt(0), 'B'),
				() -> assertEquals(fileReader.getParsed().get(4).charAt(0), 'A')
				);
	}
}
