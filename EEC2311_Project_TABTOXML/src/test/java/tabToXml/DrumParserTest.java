package tabToXml;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DrumParserTest {

	//////////////////  TESTERS FOR DRUM FILE
	
	@Test
	// Tests whether the parser is able to recognize the number of lines correctly
	void test_NumberOfStrings() throws Exception {
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expectedLength = 4;
		int actualLength = dp.getDrumTabStrings().size();
		
		assertEquals(expectedLength,actualLength);      
	}
	
	@Test
	// Tests whether the parser is able to recognize the number of measures correctly
	void test_NumberOfMeasures() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expectedLength = 4;
		int actualLength = dp.getDrumMeasures().size();
		
		assertEquals(expectedLength,actualLength);		
	}
	
	@Test
	void test_TransposeMethod() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		String[] expectedTransposed = {	"||||",	"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||",
						"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||",
						"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||",
						"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||"};
		
		String[] actualTransposed = dp.transpose(tfr.getParsed());
		
		
		assertArrayEquals(expectedTransposed,actualTransposed);
		
	}
	
	@Test
	void test_TextFileReaderObject() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		assertEquals(tfr, dp.getTFR());
		
		
	}
	
	@Test
	/**
	 * Tests whether the measures are parsed correctly in the helper method for Drum Parser
	 * @throws Exception
	 */
	void test_getMeasuresParsed() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> m1 = new ArrayList<String>();
		m1.add("|X---------------|");
		m1.add("|----o---o---o---|");
		m1.add("|----o--o-o--o---|");
		m1.add("|o--o----o-oo--o-|");
		ArrayList<String> m2 = new ArrayList<String>();
		m2.add("|X---------------|");
		m2.add("|----o---o---o---|");
		m2.add("|----o--o-o--o---|");
		m2.add("|o--o----o-oo--o-|");
		ArrayList<String> m3 = new ArrayList<String>();
		m3.add("|X---------------|");
		m3.add("|----o---o---o---|");
		m3.add("|----o--o-o--o---|");
		m3.add("|o--o----o-oo--o-|");
		ArrayList<String> m4 = new ArrayList<String>();
		m4.add("|X---------------|");
		m4.add("|----o---o---o---|");
		m4.add("|----o--o-o--o---|");
		m4.add("|o--o----o-oo--o-|");
		expected.add(m1);
		expected.add(m2);
		expected.add(m3);
		expected.add(m4);
		
		
		ArrayList<ArrayList<String>> actual = dp.getMeasuresParsed(tfr.getParsed());
		
		
		assertEquals(expected,actual);
	}
	
	@Test
	/**
	 * This method checks if the correct voices are returned for each instrument
	 * @throws Exception
	 */
	void test_VoiceSelecterMethod() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int voice1 = 1;
		int voice2 = 2;
		
		assertEquals(voice1, dp.returnVoice("C"));
		assertEquals(voice1, dp.returnVoice("HH"));
		assertEquals(voice1, dp.returnVoice("S"));
		assertEquals(voice2, dp.returnVoice("BD"));
		assertEquals(voice1, dp.returnVoice("LT"));
		assertEquals(voice1, dp.returnVoice("MT"));
		assertEquals(voice1, dp.returnVoice("XX"));
		
		
	}
	
	@Test
	/**
	 * Tests whether the drumString information is stored to the correct Length
	 * @throws Exception
	 */
	void test_SetDrumStringInfo_Length() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expected = 4;	
				
		int actual = dp.getDrumTabStrings().size();
				
		assertEquals(expected,actual);
	}
	@Test
	/**
	 * Tests whether the drumString information is stored to the correct Length
	 * @throws Exception
	 */
	void test_getInstrumentInitials_Values_Valid() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("C");
		expected.add("HH");
		expected.add("S");
		expected.add("BD");		
				
		ArrayList<String> actual = dp.getInstrumentInitials();
				
		assertEquals(expected,actual);
	}
	@Test
	/**
	 * Tests whether the drumString information is stored to the correct Length
	 * @throws Exception
	 */
	void test_SetDrumStringInfo_Values_Invalid() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add(null);
		expected.add("HH");
		expected.add("S");
		expected.add("BD");		
				
		ArrayList<String> actual = dp.getInstrumentInitials();
		
		
		assertNotEquals(expected,actual);
	}
	
	////////////////// TESTERS FOR DRUMS+ FILE
	
	@Test
	// Tests whether the parser is able to recognize the number of lines correctly
	void test_NumberOfStrings_Plus() throws Exception {
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expectedLength = 4;
		int actualLength = dp.getDrumTabStrings().size();
		
		assertEquals(expectedLength,actualLength);      
	}
	
	@Test
	// Tests whether the parser is able to recognize the number of measures correctly
	void test_NumberOfMeasures_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expectedLength = 4;
		int actualLength = dp.getDrumMeasures().size();
		
		assertEquals(expectedLength,actualLength);		
	}
	
	@Test
	void test_TransposeMethod_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		String[] expectedTransposed = {	"||||",	"X-oo","--o-","--o-","--oo","-oo-","--o-","--o-",
						"--o-","-ooo","--o-","--oo","--oo","-oo-","--o-","--oo","--o-","||||",
						"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||",
						"X--o","X---","X---","X--o","Xfo-","X---","X---",
						"X-o-","xf-o","x-o-","x--o","x--o","xff-","x---","x--o","x---","||||",
						"X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||"};
		
		String[] actualTransposed = dp.transpose(tfr.getParsed());
		
		
		assertArrayEquals(expectedTransposed,actualTransposed);
		
	}
	
	@Test
	void test_TextFileReaderObject_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		assertEquals(tfr, dp.getTFR());
		
		
	}
	
	@Test
	/**
	 * Tests whether the measures are parsed correctly in the helper method for Drum Parser
	 * @throws Exception
	 */
	void test_getMeasuresParsed_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> m1 = new ArrayList<String>();
		m1.add("|X---------------|");
		m1.add("|----o---o---o---|");
		m1.add("|oooooooooooooooo|");
		m1.add("|o--o----o-oo--o-|");
		ArrayList<String> m2 = new ArrayList<String>();
		m2.add("|X---------------|");
		m2.add("|----o---o---o---|");
		m2.add("|----o--o-o--o---|");
		m2.add("|o--o----o-oo--o-|");
		ArrayList<String> m3 = new ArrayList<String>();
		m3.add("|XXXXXXXXxxxxxxxx|");
		m3.add("|----f---f---f---|");
		m3.add("|----o--o-o--f---|");
		m3.add("|o--o----o-oo--o-|");
		ArrayList<String> m4 = new ArrayList<String>();
		m4.add("|X---------------|");
		m4.add("|----o---o---o---|");
		m4.add("|----o--o-o--o---|");
		m4.add("|o--o----o-oo--o-|");
		expected.add(m1);
		expected.add(m2);
		expected.add(m3);
		expected.add(m4);
		
		
		ArrayList<ArrayList<String>> actual = dp.getMeasuresParsed(tfr.getParsed());
		
		
		assertEquals(expected,actual);
	}
	
	@Test
	/**
	 * Tests whether the drumString information is stored to the correct Length
	 * @throws Exception
	 */
	void test_SetDrumStringInfo_Length_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		int expected = 4;	
				
		int actual = dp.getDrumTabStrings().size();
				
		assertEquals(expected,actual);
	}
	@Test
	/**
	 * 
	 * @throws Exception
	 */
	void test_getInstrumentInitials_Values_Valid_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("C");
		expected.add("HH");
		expected.add("S");
		expected.add("BD");		
				
		ArrayList<String> actual = dp.getInstrumentInitials();
				
		assertEquals(expected,actual);
	}
	@Test
	/**
	 *
	 * @throws Exception
	 */
	void test_SetDrumStringInfo_Values_Invalid_Plus() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab+.txt");
		DrumParser dp = new DrumParser(tfr);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add(null);
		expected.add("HH");
		expected.add("S");
		expected.add("BD");		
				
		ArrayList<String> actual = dp.getInstrumentInitials();
		
		
		assertNotEquals(expected,actual);
	}
}
