package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextFileReaderTest {

	@Test
	public void testReader1() {
		TextFileReader fileReader = new TextFileReader("tab.txt");
		
		assertNotNull(fileReader);
	}
	
	@Test
	public void testReader2() {
		TextFileReader fileReader = new TextFileReader("tab.txt");
		
		assertEquals(fileReader.detectInstrument(), "Guitar");
	}
	
	@Test
	public void testReader3() {
		TextFileReader fileReader = new TextFileReader("tab.txt");
		
		assertEquals(fileReader.numberOfLines(), 6);
	}
	
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
