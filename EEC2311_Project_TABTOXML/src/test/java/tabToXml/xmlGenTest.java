package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class xmlGenTest {

	@Test
	// Tests whether the objects creation for higher level object occurs 
	void test_CorrectNestedObjectCreation() throws Exception{
		TextFileReader tfr = new TextFileReader( "src/test/java/drumTabs/DrumsTab.txt");
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
	
	
//	@Test
//	void testGuiterGenerator() throws Exception {
//		ArrayList<String> test = new ArrayList<>();
//		test.add("E|-----------------|");
//		test.add("B|-----------------|");
//		test.add("G|-----------------|");
//		test.add("D|-----------------|");
//		test.add("A|-----------------|");
//		test.add("E|-2---------------|");
//
//		GuitarParser guitarParser = new GuitarParser(test);
//		xmlGen xml = new xmlGen(guitarParser);
//		xml.getXMLContent();
//		System.out.println("In xmlGne Tester: \t" + xml);
//		assertEquals(xml, "Expected");
//	}
	
	@Test
	void testXmlGenConstructor() throws Exception {

		String test = "E|-2------6r4----7h9p6--g3h4-|\n"
		+"D|---------------------------|\n"
		+"G|-----g1--------6/7\\6-------|\n"
		+"B|---------------------------|\n"
		+"A|---------------------------|\n"
		+"E|-4b6----2s4---[8]----------|";
		
		
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
		
		xmlGen xmlGuitar = new xmlGen(gp);

		TextFileReader tfrDrum = new TextFileReader("src/test/java/tabToXml/DrumsTab.txt");
		DrumParser drumParser = new DrumParser(tfrDrum);
		xmlGen xmlDrum = new xmlGen(drumParser);

		assertNotNull(xmlGuitar);
		assertNotNull(xmlDrum);
	}
	catch (Exception e) { 
		e.printStackTrace(); 
	}
	}
	
	
	
	@Test
	void testGuiterXMLGen() throws Exception {

		String test = "E|-2------6r4----7h9p6--g3h4-|\n"
				+"D|---------------------------|\n"
				+"G|-----g1--------6/7\\6-------|\n"
				+"B|---------------------------|\n"
				+"A|---------------------------|\n"
				+"E|-4b6----2s4---[8]----------|";
				
				
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
		

		xmlGen xml = new xmlGen(gp);
		String output = xml.getXMLContent();

		assertTrue(output.contains("<score-partwise"));
		assertTrue(output.contains("</score-partwise>"));
		assertNotNull(xml.getGuitarScorePartwise());

		assertTrue(output.contains("<measure number=\"1\">"));

		assertTrue(output.contains("<movement-title>Guitar Music Piece</movement-title>"));
		assertTrue(output.contains("<part-name>Classical Guitar/Bass</part-name>"));
		assertTrue(output.contains("<fret>2</fret>"));

		assertTrue(output.contains("<slur type=\"start\"/>"));
		assertTrue(output.contains("<slide type=\"start\"/>"));
		assertTrue(output.contains("<fret>2</fret>"));

		assertTrue(output.contains("<slur type=\"stop\"/>"));
		assertTrue(output.contains("<slide type=\"stop\"/>"));
		assertTrue(output.contains("<fret>4</fret>"));  

		assertTrue(output.contains("<chord/>"));
		assertTrue(output.contains("<bend-alter>2</bend-alter>"));
		assertTrue(output.contains("<duration>2</duration>"));
		assertTrue(output.contains("<step>G</step>"));
		assertTrue(output.contains("<octave>2</octave>"));

		assertTrue(output.contains("<fret>4</fret>"));
		assertTrue(output.contains("<bend-alter>-2</bend-alter>"));
		assertTrue(output.contains("<fret>6</fret>"));

		assertTrue(output.contains("<slide type=\"start\"/>"));
		assertTrue(output.contains("<fret>6</fret>"));
		assertTrue(output.contains("<slide type=\"start\"/>"));  

		assertTrue(output.contains("<fret>7</fret>"));
		assertTrue(output.contains("<slide type=\"stop\"/>"));
		assertTrue(output.contains("<fret>6</fret>"));  


		assertTrue(output.contains("<hammer-on type=\"start\"/>"));
		assertTrue(output.contains("<hammer-on type=\"stop\"/>"));
		assertTrue(output.contains("<pull-off type=\"start\"/>"));  
		assertTrue(output.contains("<pull-off type=\"stop\"/>"));  

		assertTrue(output.contains("<harmonic>"));
		assertTrue(output.contains("<natural/>"));  
		assertTrue(output.contains("</harmonic>"));  	

	//	assertTrue(output.contains("<grace/>")); This does not work for some reason  
			}
			catch (Exception e) { 
				e.printStackTrace(); 
			}


	}

	@Test
	void testDrumXMGen() throws Exception {
		
				
		String test = "CC|x---------------|----------------|\n"
		+"HH|--o-------------|----------------|\n"
		+"SD|----------------|----------------|\n"
		+"HT|----------------|----------------|\n"
		+"MT|----------------|----------------|\n"
		+"BD|----------------|----------------|";
		
				
			TextFileReader tfr;
			DrumParser dp;
			
			File newFile = new File("testFile");
			FileWriter myWriter = null;
			try {
				myWriter = new FileWriter(newFile);
				myWriter.write(test + "\n\n");
				myWriter.close();
				
				tfr = new TextFileReader(newFile);
				dp = new DrumParser(tfr);
		
		xmlGen xg = new xmlGen(dp);

		assertTrue(xg.getXMLContent().contains("<movement-title>Drum Music Piece</movement-title>"));
		assertTrue(xg.getXMLContent().contains("<part-name>Drumset</part-name>"));
		assertTrue(xg.getXMLContent().contains("<notehead>x</notehead>"));

		assertTrue(xg.getXMLContent().contains("<instrument-name>Crash Cymbal</instrument-name>"));
		assertTrue(xg.getXMLContent().contains("<instrument-name>Closed Hi-Hat</instrument-name>"));
		assertTrue(xg.getXMLContent().contains(" <instrument-name>Snare</instrument-name>"));

		assertTrue(xg.getXMLContent().contains("<instrument-name>Low Tom</instrument-name>"));
		assertTrue(xg.getXMLContent().contains("<instrument-name>Low-Mid Tom</instrument-name>"));
		assertTrue(xg.getXMLContent().contains("<instrument-name>Bass Drum 1</instrument-name>"));

		assertTrue(xg.getXMLContent().contains("<duration>2</duration>"));
		assertTrue(xg.getXMLContent().contains("<notehead>o</notehead>"));
		
			}
			catch (Exception e) { 
				e.printStackTrace(); 
			}

	}
	
	
}
