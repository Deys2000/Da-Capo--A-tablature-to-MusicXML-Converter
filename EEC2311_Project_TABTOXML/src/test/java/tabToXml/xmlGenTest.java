package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class xmlGenTest {

	@Test
	// Tests whether the objects creation for higher level object occurs 
	void test_CorrectNestedObjectCreation() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/tabToXml/DrumsTab.txt");
		DrumParser dp = new DrumParser(tfr);
		xmlGen xg = new xmlGen(dp);
		
		assertTrue(xg.getDrumScorePartwise()!= null);
		assertTrue(xg.getDrumScorePartwise().getPartList() !=null);
		assertTrue(xg.getDrumScorePartwise().getPartList().getPartGroupOrScorePart() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getId() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(0) !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(1) !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(0).getVoice1Note() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(1).getVoice1Note() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(0).getAttributes() !=null);
		assertTrue(xg.getDrumScorePartwise().getPart().getMeasure().get(1).getAttributes() !=null);			
	}
	
//	@Test
//	void test_CreateUnpitchedNote() throws Exception{
//		
//	}
//	@Test
//	void test_CreateBackup() throws Exception{
//		
//	}
//	@Test
//	void test_CreateRest() throws Exception{
//		
//	}
//	@Test
//	void test_CreateGraceNote() throws Exception{
//		
//	}
	@Test
	void testGuiterGenerator() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|-----------------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-2---------------|");

		GuitarParser guitarParser = new GuitarParser(test);
		xmlGen xml = new xmlGen(guitarParser);
		xml.getXMLContent();
		System.out.println("In xmlGne Tester: \t" + xml);
		assertEquals(xml, "Expected");
	}
	
	
	
}
