package tabToXml;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import musicXML.*;


// NEEDS TO ADAPT TO CHANGES AS THEY SHOW UP, MORE OF A RESPONSIVE TYPE OF CLASS
// ORGANIZE THE SCHEMAS AND EVENTUALLY GET RID OF THE ONES WE DONT NEED
// IDEALLY THIS CLASS HAS 3 MAJOR METHODS FOR THE CREATION OF AN XML FOR EACH OF THE INSTRUMENTS ( subject to improvement )

public class xmlGen {

	Marshaller jaxbMarshaller;
	private ScorePartwise scorePartwise; //to store a guitar or bass XML 
	private drumTag.ScorePartwise drumScorePartwise;    // to store a drum XML

	//Default Guitar Attributes, should be moved over to GuitarParser by billy or elijah - syed
	private java.lang.String[][] attributeVals = {
			{"4"}, // divisions
			{"0"}, // fifths
			{"4","4"}, // beats and beat-type
			{"tab","5"}, // sign and line
			{"6"}, // staff lines
			{"E","A","D","G","B","E"}, //tuning-step
			{"2","2","3","3","3","4"} // tuning-octave
	};
   

	// GUITAR CONSTRUCTOR
	public xmlGen(GuitarParser gp, TextFileReader tfr) {
		guitarGenerator(gp.processor(),tfr);
		//attributeVals[4][0] = tfr.staffLines();
	}    
	// DRUM CONSTRUCTOR
	public xmlGen(DrumParser2 dp) {
		drumGenerator(dp);
		//attributeVals[4][0] = tfr.staffLines();
	}  

  
    			
	public java.lang.String getXMLContent() {
		StringWriter xml =  new StringWriter();
		try {    		
			if( drumScorePartwise == null)
				jaxbMarshaller.marshal(this.scorePartwise, xml); // return as a string???
			else
				jaxbMarshaller.marshal(this.drumScorePartwise, xml); // return as a string???
		}
		catch(JAXBException e) {e.printStackTrace(); }
		finally {
			return xml.toString();
		}
	}

	/**
	 * This method replaces the contents of a file with the XML of a tablature, its used for creating a file to put on a music player
	 * @param file
	 * @return
	 */
	@SuppressWarnings("finally")
	public File createFile(File file){
		try {
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(scorePartwise, file); // return as a string???
		}
		catch(JAXBException e) {e.printStackTrace();}
		finally {
			return file;
		}
	}


	/////////////////////////////
	//// XML GENERATORS BELOW ///
	/////////////////////////////

	/**
	 * Guitar XML File Generator
	 * @param gp
	 */
	public void guitarGenerator(java.lang.String[][] info, TextFileReader tfr){ 	
		// creating the outermost tag "score-partwise"

		this.scorePartwise = new ScorePartwise();
		scorePartwise.setMovementTitle("Guitar Music Piece"); // move to constuctor

		scorePartwise.setPartList( new PartList(new ScorePart("P1", "Classical Guitar"))); // constructor sets ID and part-name
		Part part = new Part("P1"); // constructer sets ID


		// creating measure list that will hold all the measures which will each contain all the notes
		ArrayList<musicXML.Measure> measures = new ArrayList<musicXML.Measure>();
		// initializing the first measure
		musicXML.Measure measure = new musicXML.Measure("1"); // constructor sets the measure number

		// creating the attributes section that goes into the first measure
		// set attributes for the measure
		int measureNum = 1;
		Attributes attributes;// = new Attributes();
		//        attributes.setDivisions(new BigDecimal(tfr.getAttributesPerMeasure().get(measureNum-1).getDivisions()));
		//
		//        Key key = new Key();
		//        key.setFifths(new BigInteger(""+tfr.getAttributesPerMeasure().get(measureNum-1).getFifths()));
		//        attributes.setKey(key);
		//
		//        attributes.setTime(new Time(""+tfr.getAttributesPerMeasure().get(measureNum-1).getBeats(), ""+tfr.getAttributesPerMeasure().get(measureNum-1).getBeattype())); // constructor takes beat and beat type
		//        attributes.setClef(new Clef(tfr.getAttributesPerMeasure().get(measureNum-1).getSign(), new BigInteger(tfr.getAttributesPerMeasure().get(measureNum-1).getLine()))); // constuctor sets sign and line
		//
		//        StaffDetails staffDetails = new StaffDetails(new BigInteger(java.lang.String.valueOf(tfr.getStaffLines()))); // constructor takes the number of lines
		//
		//        //creating all the staff tunings that will go into the staff details tag above 
		//        ArrayList<StaffTuning> staffTunings = new ArrayList<>();        
		//    	for(int j = 0; j < attributeVals[5].length; j++)
		//            staffTunings.add(new StaffTuning(new BigInteger(Integer.toString(j + 1)),attributeVals[5][j],new BigInteger(attributeVals[6][j])));       
		//    		// loop above adds all information into a stafftuning object before inserting into stafftuning list
		//    		// the constructor takes line, tuning step and tuning octave
		//    	
		//    	//insert the arraylist of staff tunings into the stffdetails object
		//        staffDetails.setStaffTuning(staffTunings);
		//        // insert staff details into attributes
		//        attributes.setStaffDetails(staffDetails);
		//        // At this point attributes contains all the requried information, it is added to the first measure
		//        measure.setAttributes(attributes);
		//        
		//        // setting REPEATS
		//    	System.out.println("<>"+ tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat());
		//
		//        if( tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat() != null ) {
		//        	musicXML.Barline barline1 = new Barline();
		//        	barline1.setLocation("left");
		//        	musicXML.BarStyle barstyle = new BarStyle();
		//        	barstyle.setValue("heavy-light");
		//        	barline1.setBarStyle(barstyle);
		//        	musicXML.Repeat repeat = new Repeat();
		//        	repeat.setDirection("forward");
		//        	barline1.setRepeat(repeat);
		//        	measure.setBarline1(barline1);
		//        	
		//        	musicXML.Direction direction = new Direction();
		//        	direction.setPlacement("above");
		//        	musicXML.DirectionType directiontype = new DirectionType();
		//        	musicXML.Words words = new Words();
		//        	words.setRelativeX(new BigDecimal("250"));
		//        	words.setRelativeY(new BigDecimal("20"));
		//        	words.setValue("Repeat "+tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat()+" times" );
		//        			System.out.println("<><>"+ words.getValue());
		//        	directiontype.setWords(words);
		//        	direction.setDirectionType(directiontype);
		//        	measure.setDirection(direction);
		//        	
		//        	musicXML.Barline barline2 = new Barline();
		//        	barline2.setLocation("right");
		//        	musicXML.BarStyle barstyle2 = new BarStyle();
		//        	barstyle2.setValue("light-heavy");
		//        	barline2.setBarStyle(barstyle2);
		//        	musicXML.Repeat repeat2 = new Repeat();
		//        	repeat2.setDirection("backward");
		//        	barline2.setRepeat(repeat2);
		//        	measure.setBarline2(barline2);         
		//        	
		//			System.out.println("<><>"+ measure.getBarline1());
		//			System.out.println("<><>"+ measure.getBarline2());
		//
		//        }
		// creating a list of notes to put into each measure object
		ArrayList<musicXML.Note> notes = new ArrayList<>();

		for(int i = 1; i < info.length; i++) // start at one since the first barline is not the end of measure
		{
			// if you happen to be at a "|", then you create a new measure object and store the previous one
			if(info[i][0].contains("|")) {        		
				measure = new musicXML.Measure();
				measure.setNumber(""+measureNum);

				// set attributes for the measure
				attributes = new Attributes();
				attributes.setDivisions(new BigDecimal(tfr.getAttributesPerMeasure().get(measureNum-1).getDivisions()));

				Key key = new Key();
				key.setFifths(new BigInteger(""+tfr.getAttributesPerMeasure().get(measureNum-1).getFifths()));
				attributes.setKey(key);

				attributes.setTime(new Time(""+tfr.getAttributesPerMeasure().get(measureNum-1).getBeats(), ""+tfr.getAttributesPerMeasure().get(measureNum-1).getBeattype())); // constructor takes beat and beat type
				attributes.setClef(new Clef(tfr.getAttributesPerMeasure().get(measureNum-1).getSign(), new BigInteger(tfr.getAttributesPerMeasure().get(measureNum-1).getLine()))); // constuctor sets sign and line

				StaffDetails staffDetails = new StaffDetails(new BigInteger(java.lang.String.valueOf(tfr.getStaffLines()))); // constructor takes the number of lines

				//creating all the staff tunings that will go into the staff details tag above 
				ArrayList<StaffTuning> staffTunings = new ArrayList<>();        
				for(int j = 0; j < attributeVals[5].length; j++)
					staffTunings.add(new StaffTuning(new BigInteger(Integer.toString(j + 1)),attributeVals[5][j],new BigInteger(attributeVals[6][j])));       
				// loop above adds all information into a stafftuning object before inserting into stafftuning list
				// the constructor takes line, tuning step and tuning octave

				//insert the arraylist of staff tunings into the stffdetails object
				staffDetails.setStaffTuning(staffTunings);
				// insert staff details into attributes
				attributes.setStaffDetails(staffDetails);
				// At this point attributes contains all the requried information, it is added to the first measure
				measure.setAttributes(attributes);

				// setting REPEATS
				System.out.println("<><><>"+ tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat());

				if( tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat() != null ) {
					musicXML.Barline barline1 = new Barline();
					barline1.setLocation("left");
					musicXML.BarStyle barstyle = new BarStyle();
					barstyle.setValue("heavy-light");
					barline1.setBarStyle(barstyle);
					musicXML.Repeat repeat = new Repeat();
					repeat.setDirection("forward");
					barline1.setRepeat(repeat);
					measure.setBarline1(barline1);

					musicXML.Direction direction = new Direction();
					direction.setPlacement("above");
					musicXML.DirectionType directiontype = new DirectionType();
					musicXML.Words words = new Words();
					words.setRelativeX(new BigDecimal("250.0"));
					words.setRelativeY(new BigDecimal("20.0"));
					words.setValue("Repeat "+tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat()+" times" );
					System.out.println("<><>"+ words.getValue());
					directiontype.setWords(words);
					direction.setDirectionType(directiontype);
					measure.setDirection(direction);

					musicXML.Barline barline2 = new Barline();
					barline2.setLocation("right");
					musicXML.BarStyle barstyle2 = new BarStyle();
					barstyle2.setValue("light-heavy");
					barline2.setBarStyle(barstyle2);
					musicXML.Repeat repeat2 = new Repeat();
					repeat2.setDirection("backward");
					barline2.setRepeat(repeat2);
					measure.setBarline2(barline2);         

					System.out.println("<><>"+ measure.getBarline1());
					System.out.println("<><>"+ measure.getBarline2());
					System.out.println("<><>"+ measure.getBarline1().getBarStyle().getValue());
					System.out.println("<><>"+ measure.getBarline2().getBarStyle().getValue());
					System.out.println("<><>"+ measure.getDirection().getPlacement());
					System.out.println("<><>"+ measure.getDirection().getDirectionType().getWords());

				}     

				measure.setNote(notes);
				measures.add(measure);

				measureNum++;

				notes = new ArrayList<musicXML.Note>();

			}
			//if you happen to NOT get a "|", then you are at a note, so create a note and store it in the notes list
			else {
				musicXML.Note note = new musicXML.Note();
				Chord c = new Chord();
				if( info[i][7].equals("true"))
					note.getDurationOrChordOrCue().add(c); // chord
				Pitch pitch = new Pitch(info[i][1],new BigInteger(info[i][3]));
				note.getDurationOrChordOrCue().add(pitch);
				note.getDurationOrChordOrCue().add(new BigDecimal(info[i][0])); // duration
				note.setVoice(info[i][2]);
				 // RESOLVES BUG #22, for dotted notes
	            if( info[i][4].contains("dotted") == true ) {
	            //if (info[i][4].substring(0,6).compareTo("dotted") == 0) { // NEW
	            	note.setType(new Type(info[i][4].substring(7))); // NEW
	            	note.setDot(new Dot()); // NEW
	            } // NEW
	            else { // NEW
	            	note.setType(new Type(info[i][4]));
	            } // NEW
				Notations notations = new Notations();
				Technical technical = new Technical();
				// ADDING HAMMER ONS AND PULL OFFS
				if(info[i][8] == null) {
					// do nothing
				}
				else if( info[i][8].substring(0,1).equals("h")) {
					HammerOn ho = new HammerOn();
					ho.setType(info[i][8].substring(1));
					technical.setHammerOn(ho);
					Slur slur = new Slur();
					slur.setType(info[i][8].substring(1));
					notations.setSlur(slur);
				}
				else if( info[i][8].substring(0,1).equals("p")) {
					PullOff po = new PullOff();
					po.setType(info[i][8].substring(1));
					technical.setPullOff(po);
					Slur slur = new Slur();
					slur.setType(info[i][8].substring(1));
					notations.setSlur(slur);
				}
				else if(info[i][8].substring(0,1).equals("P") && info[i][8].substring(5,6).equals("H")) {
					PullOff po = new PullOff();
					po.setType(info[i][8].substring(1,5));
					technical.setPullOff(po);

					HammerOn ho = new HammerOn();
					ho.setType(info[i][8].substring(6));
					technical.setHammerOn(ho);
				}   
				else if(info[i][8].substring(0,1).equals("H") && info[i][8].substring(5,6).equals("P")) {
					HammerOn ho = new HammerOn();
					ho.setType(info[i][8].substring(1,5));
					technical.setHammerOn(ho);

					PullOff po = new PullOff();
					po.setType(info[i][8].substring(6));
					technical.setPullOff(po);
				}

				else if(info[i][8].substring(0,1).equals("P") && info[i][8].substring(5,6).equals("P")) {
					PullOff po = new PullOff();
					po.setType(info[i][8].substring(1,5));
					technical.setPullOff(po);

					PullOff po1 = new PullOff();
					po1.setType(info[i][8].substring(6));
					technical.setPullOff(po1);
				}

				else if(info[i][8].substring(0,1).equals("H") && info[i][8].substring(5,6).equals("H")) {
					HammerOn ho = new HammerOn();
					ho.setType(info[i][8].substring(1,5));
					technical.setHammerOn(ho);

					HammerOn ho1 = new HammerOn();
					ho1.setType(info[i][8].substring(6));
					technical.setHammerOn(ho1);
				}
				else {

				}

				technical.setString( new musicXML.String(new BigInteger(info[i][5])));
				technical.setFret(new Fret(new BigInteger(info[i][6])));
				notations.setTechnical(technical);
				note.setNotations(notations);
				notes.add(note);
			}
		}       
		part.setMeasure(measures);
		// at this point all the measures have been created along with all the notes inside them

		scorePartwise.setPart(part);
		// add the part and our process of creating objects is complete
		// now we just need to marshall

		// ADDED TO CONSTRUCTOR
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			// output becomes tab indented
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//jaxbMarshaller.marshal(scorePartwise, System.out); //prints to console

		} catch (JAXBException e) {
			e.printStackTrace();
		}        
	}

	/**
	 * Drum XML File Generator
	 * @param dp
	 */
	public void drumGenerator(DrumParser2 dp){
		TextFileReader tfr = dp.getTFR();

		// creating the outermost tag "score-partwise"
		this.drumScorePartwise = new drumTag.ScorePartwise();

		drumTag.ScorePart scorepart = new drumTag.ScorePart();
		scorepart.setId("P1");
		drumTag.PartName partname = new drumTag.PartName();
		partname.setValue("Drumset");
		scorepart.setPartName(partname);
		//adding the list of instruments and their ID's
		for(DrumStringInfo stringinfo: dp.getDrumTabStrings()) {
			drumTag.ScoreInstrument si = new drumTag.ScoreInstrument();
			si.setId(stringinfo.getInstrumentId());
			si.setInstrumentName(stringinfo.getInstrumentName());
			//System.out.println(si.getInstrumentName());
			//System.out.println(si.getId());
			scorepart.addScoreInstrument(si);
		}
		drumScorePartwise.setPartList( new drumTag.PartList(scorepart) ); // constructor sets ID and part-name

		drumTag.Part part = new drumTag.Part("P1"); // constructer sets ID

		ArrayList<drumTag.Measure> measures = new ArrayList<drumTag.Measure>();
		// initializing the measure object
		drumTag.Measure measure;                

		for(int i = 0; i < dp.getDrumMeasures().size(); i++) {
			ArrayList<drumTag.Note> notesVoice1 = new ArrayList<drumTag.Note>();
			ArrayList<drumTag.Note> notesVoice2 = new ArrayList<drumTag.Note>();
			int measureNum = i + 1;
			measure = new drumTag.Measure(java.lang.String.valueOf(measureNum)); // constructor sets the measure number

			System.out.println(">>>>>>>>>>>>>>" + measureNum);
			// creating the attributes section that goes into the first measure
			drumTag.Attributes attributes = new drumTag.Attributes();
			attributes.setDivisions(new BigDecimal(4));
			drumTag.Key key = new drumTag.Key();
			key.setFifths(new BigInteger("0"));
			attributes.setKey(key);
			attributes.setTime(new drumTag.Time("4", "4")); // constructor takes beat and beat type
			attributes.setClef(new drumTag.Clef("percussion", new BigInteger("2"))); // constuctor sets sign and line

			// ADDING THE ATTRIBUTES TO THE MEASURE
			measure.setAttributes(attributes);

			// ADDING THE REPEATS
			if( tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat() != null ) { // minus one since the repeats array is on a 0 index basis
				drumTag.Barline barline1 = new drumTag.Barline();
				barline1.setLocation("left");
				drumTag.BarStyle barstyle = new drumTag.BarStyle();
				barstyle.setValue("heavy-light");
				barline1.setBarStyle(barstyle);
				drumTag.Repeat repeat = new drumTag.Repeat();
				repeat.setDirection("forward");
				barline1.setRepeat(repeat);
				measure.setBarline1(barline1);

				drumTag.Direction direction = new drumTag.Direction();
				direction.setPlacement("above");
				drumTag.DirectionType directiontype = new drumTag.DirectionType();
				drumTag.Words words = new drumTag.Words();
				words.setRelativeX(new BigDecimal("250.0"));
				words.setRelativeY(new BigDecimal("20.0"));
				words.setValue("Repeat "+tfr.getAttributesPerMeasure().get(measureNum-1).getRepeat()+" times" );
				System.out.println("<><>"+ words.getValue());
				directiontype.setWords(words);
				direction.setDirectionType(directiontype);
				measure.setDirection(direction);

				drumTag.Barline barline2 = new drumTag.Barline();
				barline2.setLocation("right");
				drumTag.BarStyle barstyle2 = new drumTag.BarStyle();
				barstyle2.setValue("light-heavy");
				barline2.setBarStyle(barstyle2);
				drumTag.Repeat repeat2 = new drumTag.Repeat();
				repeat2.setDirection("backward");
				barline2.setRepeat(repeat2);
				measure.setBarline2(barline2);         

				System.out.println("<><>"+ measure.getBarline1());
				System.out.println("<><>"+ measure.getBarline2());
				System.out.println("<><>"+ measure.getBarline1().getBarStyle().getValue());
				System.out.println("<><>"+ measure.getBarline2().getBarStyle().getValue());
				System.out.println("<><>"+ measure.getDirection().getPlacement());
				System.out.println("<><>"+ measure.getDirection().getDirectionType().getWords());

			} 


			drumTag.Backup b = null;
			drumTag.Note n;

			for(tabToXml.DrumNote note: dp.getDrumMeasures().get(i).getNotes()) {
				n = new drumTag.Note();
				// if the note is not a rest note give it the following values as well

				if(note.getUnpitchedOrRest().equals("unpitched")) {
					n.getDurationOrChordOrCue().add(new drumTag.Unpitched(new BigInteger(note.getDisplayOctave()),note.getDisplayStep()));
					if(note.getChord())
						n.getDurationOrChordOrCue().add(new drumTag.Chord());
					drumTag.Instrument instrument = new drumTag.Instrument();
					instrument.setId(note.getInstrumentID());
					n.setInstrument(instrument);
					n.setStem(new drumTag.Stem(note.getStem()));
					drumTag.Notehead notehead = new drumTag.Notehead();
					notehead.setValue(note.getNotehead());
					n.setNotehead(notehead);
					n.getDurationOrChordOrCue().add(new BigDecimal(note.getDuration()));        	   
					n.setVoice(java.lang.String.valueOf(note.getVoice()));
					n.setType(new drumTag.Type(note.getType()));
					// set values for all three of the beams	
					drumTag.Beam beam;
							beam = new drumTag.Beam();
							beam.setNumber(new BigInteger("1")); beam.setValue(note.getBeam1());
							n.addBeam(beam);
							beam = new drumTag.Beam();
							beam.setNumber(new BigInteger("2")); beam.setValue(note.getBeam2());
							n.addBeam(beam);
							beam = new drumTag.Beam();
							beam.setNumber(new BigInteger("3")); beam.setValue(note.getBeam3());
							n.addBeam(beam);

							if(n.getVoice().equals("1"))  
								notesVoice1.add(n);
							else
								notesVoice2.add(n);
				}
				// the else statement takes the rest notes
				else if( note.getUnpitchedOrRest().equals("backup")) {
					b = new drumTag.Backup();
					b.setDuration(new BigDecimal(note.getDuration()));
					//b.setLevel(new drumTag.Level());        		   
				}        	   
				else {     	   // the else statement takes the rest notes

					n.getDurationOrChordOrCue().add(new drumTag.Rest());
					n.getDurationOrChordOrCue().add(new BigDecimal(note.getDuration()));        	   
					n.setVoice(java.lang.String.valueOf(note.getVoice()));
					n.setType(new drumTag.Type(note.getType()));
					if(n.getVoice().equals("1"))  
						notesVoice1.add(n);
					else
						notesVoice2.add(n);
				}        	           	   
			}
			//adding the first batch of notes to the first measure
			measure.setVoice1Note(notesVoice1);
			measure.setVoice2Note(notesVoice2);
			if (b != null) measure.setBackup(b);
			measures.add(measure);
		}

		part.setMeasure(measures);
		drumScorePartwise.setPart(part);
		// add the 'part' object and our process of creating objects is complete
		// now we just need to marshall

		// ADDED TO CONSTRUCTOR
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(drumTag.ScorePartwise.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);            

			//jaxbMarshaller.marshal(drumScorePartwise, System.out); //prints to console

		} catch (JAXBException e) {
			e.printStackTrace();
		}


	}


} // END OF CLASS
