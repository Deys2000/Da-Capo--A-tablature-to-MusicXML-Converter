package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

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
	
	
	
	
}
