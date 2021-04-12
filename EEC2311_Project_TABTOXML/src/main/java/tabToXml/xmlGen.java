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
// IDEALLY THIS CLASS HAS 3 MAJOR METHODS FOR THE CREATION OF AN XML FOR EACH OF THE INSTRUMENTS ( subject to improvement )

public class xmlGen {

	Marshaller jaxbMarshaller; // what compiles the set of nested objects into an xml file
	
	private musicXML.ScorePartwise scorePartwise; //to store a guitar or bass XML 
	private drumTag.ScorePartwise drumScorePartwise;    // to store a drum XML


	// GUITAR CONSTRUCTOR
	public xmlGen(GuitarParser gp) {
		guitarGenerator(gp);
	}    
	// DRUM CONSTRUCTOR
	public xmlGen(DrumParser dp) {
		drumGenerator(dp);
	}  

//	/**
//	 * This method returns the xml content as a string and returns it, it is useful for printing to console or a text area
//	 * @return String containing xml information
//	 */
//    public void guitarGenerator(java.lang.String[][] info)
//    {
//
//    	// creating the outermost tag "score-partwise"
//        this.scorePartwise = new ScorePartwise();
//        scorePartwise.setMovementTitle("Guitar Music Piece"); // move to constuctor
//        
//        scorePartwise.setPartList( new PartList(new ScorePart("P1", "Classical Guitar"))); // constructor sets ID and part-name
//        Part part = new Part("P1"); // constructer sets ID
//        
//
//        // creating measure list that will hold all the measures which will each contain all the notes
//        ArrayList<musicXML.Measure> measures = new ArrayList<musicXML.Measure>();
//        // initializing the first measure
//        musicXML.Measure measure = new musicXML.Measure("1"); // constructor sets the measure number
//           
//        // creating the attributes section that goes into the first measure
//        Attributes attributes = new Attributes();
//        attributes.setDivisions(new BigDecimal(2));
//
//        Key key = new Key();
//        key.setFifths(new BigInteger("0"));
//        attributes.setKey(key);
//
//        attributes.setTime(new Time("4", "4")); // constructor takes beat and beat type
//        attributes.setClef(new Clef("TAB", new BigInteger("5"))); // constuctor sets sign and line
//
//        StaffDetails staffDetails = new StaffDetails(new BigInteger("6")); // constructor takes the number of lines
//
//        //creating all the staff tunings that will go into the staff details tag above 
//        ArrayList<StaffTuning> staffTunings = new ArrayList<>();        
//    	for(int i = 0; i < attributeVals[5].length; i++)
//            staffTunings.add(new StaffTuning(new BigInteger(Integer.toString(i + 1)),attributeVals[5][i],new BigInteger(attributeVals[6][i])));       
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
//        // creating a list of notes to put into each measure object
//        ArrayList<musicXML.Note> notes = new ArrayList<>();
//        int measureNum = 1;
//        for(int i = 1; i < info.length; i++)
//        {
//        	// if you happen to be at a "|", then you create a new measure object and store the previous one
//        	if(info[i][0] == null ) {
//        		measure.setNote(notes);
//        		notes = new ArrayList<musicXML.Note>();
//        		measures.add(measure);
//        		measure = new musicXML.Measure();
//        		measureNum++;
//        		measure.setNumber(""+measureNum);
//        	}
//        	//if you happen to NOT get a "|", then you are at a note, so create a note and store it in the notes list
//        	else {
//        		musicXML.Note note = new musicXML.Note();
//        		Chord c = new Chord();
//        		if( info[i][7].equals("true"))
//        			note.getDurationOrChordOrCue().add(c); // chord
//            Pitch pitch = new Pitch(info[i][1],new BigInteger(info[i][3]));
//            note.getDurationOrChordOrCue().add(pitch);
//            note.getDurationOrChordOrCue().add(new BigDecimal(info[i][0])); // duration
//            note.setVoice(info[i][2]);
//            note.setType(new Type(info[i][4]));
//            Notations notations = new Notations();
//            Technical technical = new Technical();
//            // ADDING HAMMER ONS AND PULL OFFS
//            if(info[i][8] == null) {
//            	// do nothing
//            }
//            else if( info[i][8].substring(0,1).equals("h")) {
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(1));
//            	technical.setHammerOn(ho);
//            	Slur slur = new Slur();
//            	slur.setType(info[i][8].substring(1));
//            	notations.setSlur(slur);
//            }
//            else if( info[i][8].substring(0,1).equals("p")) {
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1));
//            	technical.setPullOff(po);
//            	Slur slur = new Slur();
//            	slur.setType(info[i][8].substring(1));
//            	notations.setSlur(slur);
//            }
//            else if(info[i][8].substring(0,1).equals("P") && info[i][8].substring(5,6).equals("H")) {
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1,5));
//            	technical.setPullOff(po);
//            	
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(6));
//            	technical.setHammerOn(ho);
//            }   
//            else if(info[i][8].substring(0,1).equals("H") && info[i][8].substring(5,6).equals("P")) {
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(1,5));
//            	technical.setHammerOn(ho);
//            	
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(6));
//            	technical.setPullOff(po);
//            }
//            
//            else if(info[i][8].substring(0,1).equals("P") && info[i][8].substring(5,6).equals("P")) {
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1,5));
//            	technical.setPullOff(po);
//            	
//            	PullOff po1 = new PullOff();
//            	po1.setType(info[i][8].substring(6));
//            	technical.setPullOff(po1);
//            }
//            
//            else if(info[i][8].substring(0,1).equals("H") && info[i][8].substring(5,6).equals("H")) {
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(1,5));
//            	technical.setHammerOn(ho);
//            	
//            	HammerOn ho1 = new HammerOn();
//            	ho1.setType(info[i][8].substring(6));
//            	technical.setHammerOn(ho1);
//            }
//            else if( info[i][8].substring(0,1).equals("N")) {
//            	Harmonic nh = new Harmonic();
//            	Natural nat = new Natural();
//            	nh.setNatural(nat);
//            	technical.setHarmonic(nh);
//            }
//            //-------
//            else if( info[i][8].substring(0,1).equals("a")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1));
//            	notations.setSlide(sl);
////            	no.setHammerOn(ho);
////            	Slur slur = new Slur();
////            	slur.setType(info[i][8].substring(1));
////            	notations.setSlur(slur);
//            }
//            else if( info[i][8].substring(0,1).equals("d")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1));
//            	notations.setSlide(sl);
////            	PullOff po = new PullOff();
////            	po.setType(info[i][8].substring(1));
////            	technical.setPullOff(po);
////            	Slur slur = new Slur();
////            	slur.setType(info[i][8].substring(1));
////            	notations.setSlur(slur);
//            }
//            else if(info[i][8].substring(0,1).equals("A") && info[i][8].substring(5,6).equals("D")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1,5));
//            	notations.setSlide(sl);
//            	
//            	Slide sl2 = new Slide();
//            	sl2.setType(info[i][8].substring(6));
//            	notations.setSlide(sl2);
////            	PullOff po = new PullOff();
////            	po.setType(info[i][8].substring(1,5));
////            	technical.setPullOff(po);
////            	
////            	HammerOn ho = new HammerOn();
////            	ho.setType(info[i][8].substring(6));
////            	technical.setHammerOn(ho);
//            }   
//            else if(info[i][8].substring(0,1).equals("D") && info[i][8].substring(5,6).equals("A")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1,5));
//            	notations.setSlide(sl);
//            	
//            	Slide sl2 = new Slide();
//            	sl2.setType(info[i][8].substring(6));
//            	notations.setSlide(sl2);
////            	HammerOn ho = new HammerOn();
////            	ho.setType(info[i][8].substring(1,5));
////            	technical.setHammerOn(ho);
////            	
////            	PullOff po = new PullOff();
////            	po.setType(info[i][8].substring(6));
////            	technical.setPullOff(po);
//            }
//            
//            else if(info[i][8].substring(0,1).equals("A") && info[i][8].substring(5,6).equals("A")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1,5));
//            	notations.setSlide(sl);
//            	
//            	Slide sl2 = new Slide();
//            	sl2.setType(info[i][8].substring(6));
//            	notations.setSlide(sl2);            	
////            	PullOff po = new PullOff();
////            	po.setType(info[i][8].substring(1,5));
////            	technical.setPullOff(po);
////            	
////            	PullOff po1 = new PullOff();
////            	po1.setType(info[i][8].substring(6));
////            	technical.setPullOff(po1);
//            }
//            
//            else if(info[i][8].substring(0,1).equals("D") && info[i][8].substring(5,6).equals("D")) {
//            	Slide sl = new Slide();
//            	sl.setType(info[i][8].substring(1,5));
//            	notations.setSlide(sl);
//            	
//            	Slide sl2 = new Slide();
//            	sl2.setType(info[i][8].substring(6));
//            	notations.setSlide(sl2);
////            	HammerOn ho = new HammerOn();
////            	ho.setType(info[i][8].substring(1,5));
////            	technical.setHammerOn(ho);
////            	
////            	HammerOn ho1 = new HammerOn();
////            	ho1.setType(info[i][8].substring(6));
////            	technical.setHammerOn(ho1);
//            }
//            //-------------
//           
//           	else {
//           		
//           	}
//           	
//            technical.setString( new musicXML.String(new BigInteger(info[i][5])));
//            technical.setFret(new Fret(new BigInteger(info[i][6])));
//            notations.setTechnical(technical);
//            note.setNotations(notations);
//            notes.add(note);
//        	}
//        }
//        measure.setNote(notes);        
//        part.setMeasure(measures);
//        // at this point all the measures have been created along with all the notes inside them
//        
//        scorePartwise.setPart(part);
//        // add the part and our process of creating objects is complete
//        // now we just need to marshall
//        
//        // ADDED TO CONSTRUCTOR
//        try {
//
//            JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
//            jaxbMarshaller = jaxbContext.createMarshaller();
//
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            
//
//            //jaxbMarshaller.marshal(scorePartwise, System.out); //prints to console
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        
//        
//    }
//
//    /**
//     * This method replaces the contents of a file with the XML of a tablature, its used for creating a file to put on a music player
//     * @param file
//     * @return
//     */
//    @SuppressWarnings("finally")
//	public File createFile(File file)
//    {
//    	try {
//    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//    		jaxbMarshaller.marshal(scorePartwise, file); // return as a string???
//    	}
//    	catch(JAXBException e) {
//    		e.printStackTrace();
//    	}finally {
//    		return file;
//    	}
//    }
//    
//
//    
//    @SuppressWarnings("finally")
//>>>>>>> refs/remotes/origin/Elijah_feature
	
	/**
	* This method gets the XML information as a string, its useful for printing on console or to GUI
   	* @return
   	*/
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
	public void guitarGenerator(GuitarParser gp){ 	
		
		// FOR CONSOLE VIEWING PURPOSES
		System.out.println("Length: "+ gp.getNotes().size()			+ "> Notes: \t" 	+ gp.getNotes() );
		System.out.println("Length: "+ gp.getChordArr().size() 		+ "> Chord?: \t" 	+ gp.getChordArr()  );
		System.out.println("Length: "+ gp.getFretNums().size()		+ "> Frets Nums: \t"+ gp.getFretNums()  );
		System.out.println("Length: "+ gp.getFretStrings().size()	+ "> Fret Strings:\t"+ gp.getFretStrings());
		System.out.println("Length: "+ gp.getDurationArr().size()	+ "> Duration: \t" 	+ gp.getDurationArr());
		System.out.println("Length: "+ gp.getTypeArr().size() 		+ "> Type: \t" 		+ gp.getTypeArr() );
		System.out.println("Length: "+ gp.getAlters().size()		+ "> Alter: \t" 	+ gp.getAlters() );
		System.out.println("Length: "+ gp.getHandPArr().size() 		+ "> H and P: \t" 	+ gp.getHandPArr());
		System.out.println("Length: "+ gp.getGraceArr().size()		+ "> Grace: \t" 	+ gp.getGraceArr() );
		
		
		// creating the outermost tag "score-partwise"
		TextFileReader tfr = gp.tfr;

		this.scorePartwise = new ScorePartwise();
		if(gp.tfr.getMusicPieceTitle() == null)
			if(gp.tfr.getDetectedInstrument().equals("Guitar"))	scorePartwise.setMovementTitle("Guitar Music Piece");
			else												scorePartwise.setMovementTitle("Bass Music Piece");
		else
			scorePartwise.setMovementTitle(gp.tfr.getMusicPieceTitle()); 
		
		scorePartwise.setPartList( new PartList(new ScorePart("P1", "Classical Guitar/Bass"))); // constructor sets ID and part-name
		Part part = new Part("P1"); // constructer sets ID


		// creating measure list that will hold all the measures which will each contain all the notes
		ArrayList<musicXML.Measure> measures = new ArrayList<musicXML.Measure>();
		// initializing the first measure
		int measureNum = 0;
		musicXML.Measure measure; // = new musicXML.Measure("1"); // constructor sets the measure number

		// creating the attributes section that goes into the first measure
		// set attributes for the measure
		Attributes attributes;
		// creating a list of notes to put into each measure object
		ArrayList<musicXML.Note> notes = new ArrayList<>();

		for(int i = 1; i < gp.getNotes().size(); i++) // start at one since the first barline is not the end of measure
		{
			// if you happen to be at a "|", then you create a new measure object and store the previous one
			if(gp.getDurationArr().get(i).contains("|")) {        		
				measure = new musicXML.Measure();
				measure.setNumber(""+(measureNum+1));

				// set attributes for the measure
				attributes = new Attributes();
				attributes.setDivisions(new BigDecimal(tfr.getAttributesPerMeasure().get(measureNum).getDivisions()));

				Key key = new Key();
				key.setFifths(new BigInteger(""+tfr.getAttributesPerMeasure().get(measureNum).getFifths()));
				attributes.setKey(key);

				attributes.setTime(new Time(""+tfr.getAttributesPerMeasure().get(measureNum).getBeats(), ""+tfr.getAttributesPerMeasure().get(measureNum).getBeattype())); // constructor takes beat and beat type
				attributes.setClef(new Clef(tfr.getAttributesPerMeasure().get(measureNum).getSign(), new BigInteger(tfr.getAttributesPerMeasure().get(measureNum).getLine()))); // constuctor sets sign and line

				StaffDetails staffDetails = new StaffDetails(new BigInteger(java.lang.String.valueOf(tfr.getStaffLines()))); // constructor takes the number of lines

				//creating all the staff tunings that will go into the staff details tag above 
				
				ArrayList<StaffTuning> staffTunings = new ArrayList<>();        
				for(int j = 0; j < gp.tuning.size(); j++) {
					staffTunings.add(new StaffTuning(new BigInteger(Integer.toString(j + 1)),gp.tuning.get(j).substring(0,1),new BigInteger(gp.tuning.get(j).substring(1))));       
				}
	
				// the constructor takes line, tuning step and tuning octave

				//insert the arraylist of staff tunings into the stffdetails object
				staffDetails.setStaffTuning(staffTunings);
				// insert staff details into attributes
				attributes.setStaffDetails(staffDetails);
				// At this point attributes contains all the requried information, it is added to the first measure
				measure.setAttributes(attributes);
				
				System.out.println(measureNum+">>>"+ tfr.getAttributesPerMeasure().get(measureNum).getRepeatTimes());
				
				// setting REPEATS
				if( tfr.getAttributesPerMeasure().get(measureNum).getLeftBar() != null ) {
					musicXML.Barline barline1 = new Barline();
					barline1.setLocation("left");
					musicXML.BarStyle barstyle = new BarStyle();
					barstyle.setValue("heavy-light");
					barline1.setBarStyle(barstyle);
					musicXML.Repeat repeat = new Repeat();
					repeat.setDirection("forward");
					barline1.setRepeat(repeat);
					measure.setBarline1(barline1);
				}
				if( tfr.getAttributesPerMeasure().get(measureNum).getRepeatTimes() != null ) {
					musicXML.Direction direction = new Direction();
					direction.setPlacement("above");
					musicXML.DirectionType directiontype = new DirectionType();
					musicXML.Words words = new Words();
					words.setRelativeX(new BigDecimal("250.0"));
					words.setRelativeY(new BigDecimal("20.0"));
					words.setValue("Repeat "+tfr.getAttributesPerMeasure().get(measureNum).getRepeatTimes()+" times" );
					System.out.println("<><>"+ words.getValue());
					directiontype.setWords(words);
					direction.setDirectionType(directiontype);
					measure.setDirection(direction);
				}
				if( tfr.getAttributesPerMeasure().get(measureNum).getRightBar() != null ) {
					musicXML.Barline barline2 = new Barline();
					barline2.setLocation("right");
					musicXML.BarStyle barstyle2 = new BarStyle();
					barstyle2.setValue("light-heavy");
					barline2.setBarStyle(barstyle2);
					musicXML.Repeat repeat2 = new Repeat();
					repeat2.setDirection("backward");
					barline2.setRepeat(repeat2);
					measure.setBarline2(barline2);         

				}     

				measureNum++;
				measure.setNote(notes);
				measures.add(measure);
				notes = new ArrayList<musicXML.Note>();

			}
			//if you happen to NOT get a "|", then you are at a note, so create a note and store it in the notes list
			else {
				musicXML.Note note = new musicXML.Note();
				Chord c = new Chord();
				if( gp.getChordArr().get(i).equals("true"))
					note.getDurationOrChordOrCue().add(c); // chord
				Pitch pitch = new Pitch(gp.getNotes().get(i).substring(0,1),new BigInteger(gp.getNotes().get(i).substring(1)));
				if(gp.getAlters().get(i) != null)
					pitch.setAlter(new BigDecimal(gp.getAlters().get(i)));
				note.getDurationOrChordOrCue().add(pitch);
				note.getDurationOrChordOrCue().add(new BigDecimal(gp.getDurationArr().get(i))); // duration

				note.setVoice("1"); // ---------------------------HARDCODED: we dont consider a second voice for drums
				// RESOLVES BUG #22, for dotted notes
				if( gp.getTypeArr().get(i).contains("dotted") == true ) {
					//if (info[i][4].substring(0,6).compareTo("dotted") == 0) { // NEW
					note.setType(new Type(gp.getTypeArr().get(i).substring(7))); // NEW
					note.setDot(new Dot()); // NEW
				} // NEW
				else { // NEW
					note.setType(new Type(gp.getTypeArr().get(i)));
				} // NEW
				Notations notations = new Notations();
				Technical technical = new Technical();
//				// ADDING HAMMER ONS AND PULL OFFS
//				if(gp.getHandPArr().get(i) == null) {
//					// do nothing
//				}
//				else if( gp.getHandPArr().get(i).substring(0,1).equals("h")) {
//					HammerOn ho = new HammerOn();
//					ho.setType(gp.getHandPArr().get(i).substring(1));
//					technical.setHammerOn(ho);
//					Slur slur = new Slur();
//					slur.setType(gp.getHandPArr().get(i).substring(1));
//					notations.setSlur(slur);
//				}
//				else if( gp.getHandPArr().get(i).substring(0,1).equals("p")) {
//					PullOff po = new PullOff();
//					po.setType(gp.getHandPArr().get(i).substring(1));
//					technical.setPullOff(po);
//					Slur slur = new Slur();
//					slur.setType(gp.getHandPArr().get(i).substring(1));
//					notations.setSlur(slur);
//				}
//				else if(gp.getHandPArr().get(i).substring(0,1).equals("P") && gp.getHandPArr().get(i).substring(5,6).equals("H")) {
//					PullOff po = new PullOff();
//					po.setType(gp.getHandPArr().get(i).substring(1,5));
//					technical.setPullOff(po);
//
//					HammerOn ho = new HammerOn();
//					ho.setType(gp.getHandPArr().get(i).substring(6));
//					technical.setHammerOn(ho);
//				}   
//				else if(gp.getHandPArr().get(i).substring(0,1).equals("H") && gp.getHandPArr().get(i).substring(5,6).equals("P")) {
//					HammerOn ho = new HammerOn();
//					ho.setType(gp.getHandPArr().get(i).substring(1,5));
//					technical.setHammerOn(ho);
//
//					PullOff po = new PullOff();
//					po.setType(gp.getHandPArr().get(i).substring(6));
//					technical.setPullOff(po);
//				}
//
//				else if(gp.getHandPArr().get(i).substring(0,1).equals("P") && gp.getHandPArr().get(i).substring(5,6).equals("P")) {
//					PullOff po = new PullOff();
//					po.setType(gp.getHandPArr().get(i).substring(1,5));
//					technical.setPullOff(po);
//
//					PullOff po1 = new PullOff();
//					po1.setType(gp.getHandPArr().get(i).substring(6));
//					technical.setPullOff(po1);
//				}
//
//				else if(gp.getHandPArr().get(i).substring(0,1).equals("H") && gp.getHandPArr().get(i).substring(5,6).equals("H")) {
//					HammerOn ho = new HammerOn();
//					ho.setType(gp.getHandPArr().get(i).substring(1,5));
//					technical.setHammerOn(ho);
//
//					HammerOn ho1 = new HammerOn();
//					ho1.setType(gp.getHandPArr().get(i).substring(6));
//					technical.setHammerOn(ho1);
//				}
				if(gp.getHandPArr().get(i) == null) {
	            	// do nothing
	            }
	            else if( gp.getHandPArr().get(i).substring(0,1).equals("h")) {
	            	HammerOn ho = new HammerOn();
	            	ho.setType(gp.getHandPArr().get(i).substring(1));
	            	technical.setHammerOn(ho);
	            	Slur slur = new Slur();
	            	slur.setType(gp.getHandPArr().get(i).substring(1));
	            	notations.setSlur(slur);
	            }
	            else if( gp.getHandPArr().get(i).substring(0,1).equals("p")) {
	            	PullOff po = new PullOff();
	            	po.setType(gp.getHandPArr().get(i).substring(1));
	            	technical.setPullOff(po);
	            	Slur slur = new Slur();
	            	slur.setType(gp.getHandPArr().get(i).substring(1));
	            	notations.setSlur(slur);
	            }
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("P") && gp.getHandPArr().get(i).substring(5,6).equals("H")) {
	            	PullOff po = new PullOff();
	            	po.setType(gp.getHandPArr().get(i).substring(1,5));
	            	technical.setPullOff(po);
	            	
	            	HammerOn ho = new HammerOn();
	            	ho.setType(gp.getHandPArr().get(i).substring(6));
	            	technical.setHammerOn(ho);
	            }   
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("H") && gp.getHandPArr().get(i).substring(5,6).equals("P")) {
	            	HammerOn ho = new HammerOn();
	            	ho.setType(gp.getHandPArr().get(i).substring(1,5));
	            	technical.setHammerOn(ho);
	            	
	            	PullOff po = new PullOff();
	            	po.setType(gp.getHandPArr().get(i).substring(6));
	            	technical.setPullOff(po);
	            }	           
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("P") && gp.getHandPArr().get(i).substring(5,6).equals("P")) {
	            	PullOff po = new PullOff();
	            	po.setType(gp.getHandPArr().get(i).substring(1,5));
	            	technical.setPullOff(po);
	            	
	            	PullOff po1 = new PullOff();
	            	po1.setType(gp.getHandPArr().get(i).substring(6));
	            	technical.setPullOff(po1);
	            }	            
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("H") && gp.getHandPArr().get(i).substring(5,6).equals("H")) {
	            	HammerOn ho = new HammerOn();
	            	ho.setType(gp.getHandPArr().get(i).substring(1,5));
	            	technical.setHammerOn(ho);
	            	
	            	HammerOn ho1 = new HammerOn();
	            	ho1.setType(gp.getHandPArr().get(i).substring(6));
	            	technical.setHammerOn(ho1);
	            }
	            //-------
	            else if( gp.getHandPArr().get(i).substring(0,1).equals("N")) {
	            	Harmonic nh = new Harmonic();
	            	Natural nat = new Natural();
	            	nh.setNatural(nat);
	            	technical.setHarmonic(nh);
	            }
	            else if( gp.getHandPArr().get(i).substring(0,1).equals("a")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1));
	            	notations.setSlide(sl);
//	            	no.setHammerOn(ho);
//	            	Slur slur = new Slur();
//	            	slur.setType(gp.getHandPArr().get(i).substring(1));
//	            	notations.setSlur(slur);
	            }
	            else if( gp.getHandPArr().get(i).substring(0,1).equals("d")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1));
	            	notations.setSlide(sl);
//	            	PullOff po = new PullOff();
//	            	po.setType(gp.getHandPArr().get(i).substring(1));
//	            	technical.setPullOff(po);
//	            	Slur slur = new Slur();
//	            	slur.setType(gp.getHandPArr().get(i).substring(1));
//	            	notations.setSlur(slur);
	            }
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("A") && gp.getHandPArr().get(i).substring(5,6).equals("D")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1,5));
	            	notations.setSlide(sl);
	            	
	            	Slide sl2 = new Slide();
	            	sl2.setType(gp.getHandPArr().get(i).substring(6));
	            	notations.setSlide(sl2);
//	            	PullOff po = new PullOff();
//	            	po.setType(gp.getHandPArr().get(i).substring(1,5));
//	            	technical.setPullOff(po);
//	            	
//	            	HammerOn ho = new HammerOn();
//	            	ho.setType(gp.getHandPArr().get(i).substring(6));
//	            	technical.setHammerOn(ho);
	            }   
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("D") && gp.getHandPArr().get(i).substring(5,6).equals("A")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1,5));
	            	notations.setSlide(sl);
	            	
	            	Slide sl2 = new Slide();
	            	sl2.setType(gp.getHandPArr().get(i).substring(6));
	            	notations.setSlide(sl2);
//	            	HammerOn ho = new HammerOn();
//	            	ho.setType(gp.getHandPArr().get(i).substring(1,5));
//	            	technical.setHammerOn(ho);
//	            	
//	            	PullOff po = new PullOff();
//	            	po.setType(gp.getHandPArr().get(i).substring(6));
//	            	technical.setPullOff(po);
	            }
	            
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("A") && gp.getHandPArr().get(i).substring(5,6).equals("A")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1,5));
	            	notations.setSlide(sl);
	            	
	            	Slide sl2 = new Slide();
	            	sl2.setType(gp.getHandPArr().get(i).substring(6));
	            	notations.setSlide(sl2);            	
//	            	PullOff po = new PullOff();
//	            	po.setType(gp.getHandPArr().get(i).substring(1,5));
//	            	technical.setPullOff(po);
//	            	
//	            	PullOff po1 = new PullOff();
//	            	po1.setType(gp.getHandPArr().get(i).substring(6));
//	            	technical.setPullOff(po1);
	            }
	            
	            else if(gp.getHandPArr().get(i).substring(0,1).equals("D") && gp.getHandPArr().get(i).substring(5,6).equals("D")) {
	            	Slide sl = new Slide();
	            	sl.setType(gp.getHandPArr().get(i).substring(1,5));
	            	notations.setSlide(sl);
	            	
	            	Slide sl2 = new Slide();
	            	sl2.setType(gp.getHandPArr().get(i).substring(6));
	            	notations.setSlide(sl2);
//	            	HammerOn ho = new HammerOn();
//	            	ho.setType(gp.getHandPArr().get(i).substring(1,5));
//	            	technical.setHammerOn(ho);
//	            	
//	            	HammerOn ho1 = new HammerOn();
//	            	ho1.setType(gp.getHandPArr().get(i).substring(6));
//	            	technical.setHammerOn(ho1);
	            }
				else {

				}

				technical.setString( new musicXML.String(new BigInteger(gp.getFretStrings().get(i))));
				technical.setFret(new Fret(new BigInteger(gp.getFretNums().get(i))));
				notations.setTechnical(technical);
				note.setNotations(notations);
				notes.add(note);
			}
		}       
		part.setMeasure(measures);
		// at this point all the measures have been created along with all the notes inside them

		scorePartwise.setPart(part); //add the part and our process of creating objects is complete

		// now we just need to marshall
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			// output becomes tab indented
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//jaxbMarshaller.marshal(scorePartwise, System.out); //prints to console

		} catch (JAXBException e) {
			e.printStackTrace();
		}        
	} // END OF GUITAR GENERATOR
	
	

	
	
	///////////////////////////////////////////
	///     DRUMS BELOW THIS POINT      ///////
	///////////////////////////////////////////
	
	
	
	
	
	/**
	 * Drum XML File Generator
	 * @param dp
	 */
	public void drumGenerator(DrumParser dp){
		TextFileReader tfr = dp.getTFR();

		// creating the outermost tag "score-partwise"
		drumScorePartwise = new drumTag.ScorePartwise();
		if(dp.tfr.getMusicPieceTitle() == null)
			drumScorePartwise.setMovementTitle("Drum Music Piece"); 
		else
			drumScorePartwise.setMovementTitle(dp.tfr.getMusicPieceTitle()); 

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
			
			
			
			// creating the attributes section
			drumTag.Attributes attributes = new drumTag.Attributes();
			attributes.setDivisions(new BigDecimal(dp.getDrumAttributesPerMeasure().get(measureNum-1).getDivisions()));
			drumTag.Key key = new drumTag.Key();
			key.setFifths(new BigInteger(Integer.toString(dp.getDrumAttributesPerMeasure().get(measureNum-1).getFifths())));
			attributes.setKey(key);
			attributes.setTime(new drumTag.Time(Integer.toString(dp.getDrumAttributesPerMeasure().get(measureNum-1).getBeats()),
												Integer.toString(dp.getDrumAttributesPerMeasure().get(measureNum-1).getBeattype() ))); // constructor takes beat and beat type
			attributes.setClef(new drumTag.Clef(dp.getDrumAttributesPerMeasure().get(measureNum-1).getSign(),
											new BigInteger(dp.getDrumAttributesPerMeasure().get(measureNum-1).getLine()))); // constuctor sets sign and line
			
			// ADDING THE ATTRIBUTES TO THE MEASURE
			measure.setAttributes(attributes);

			// ADDING THE REPEATS
			if( tfr.getAttributesPerMeasure().get(measureNum-1).getLeftBar() != null ) {
				drumTag.Barline barline1 = new drumTag.Barline();
				barline1.setLocation("left");
				drumTag.BarStyle barstyle = new drumTag.BarStyle();
				barstyle.setValue("heavy-light");
				barline1.setBarStyle(barstyle);
				drumTag.Repeat repeat = new drumTag.Repeat();
				repeat.setDirection("forward");
				barline1.setRepeat(repeat);
				measure.setBarline1(barline1);
			}
			if( tfr.getAttributesPerMeasure().get(measureNum-1).getRepeatTimes() != null ) {

				drumTag.Direction direction = new drumTag.Direction();
				direction.setPlacement("above");
				drumTag.DirectionType directiontype = new drumTag.DirectionType();
				drumTag.Words words = new drumTag.Words();
				words.setRelativeX(new BigDecimal("250.0"));
				words.setRelativeY(new BigDecimal("20.0"));
				words.setValue("Repeat "+tfr.getAttributesPerMeasure().get(measureNum-1).getRepeatTimes()+" times" );
				System.out.println("<><>"+ words.getValue());
				directiontype.setWords(words);
				direction.setDirectionType(directiontype);
				measure.setDirection(direction);
			}
			if( tfr.getAttributesPerMeasure().get(measureNum-1).getRightBar() != null ) {

				drumTag.Barline barline2 = new drumTag.Barline();
				barline2.setLocation("right");
				drumTag.BarStyle barstyle2 = new drumTag.BarStyle();
				barstyle2.setValue("light-heavy");
				barline2.setBarStyle(barstyle2);
				drumTag.Repeat repeat2 = new drumTag.Repeat();
				repeat2.setDirection("backward");
				barline2.setRepeat(repeat2);
				measure.setBarline2(barline2);         
			} 

			
			drumTag.Backup b = null;
			drumTag.Note n;
			boolean previous_note_is_grace_note = false;

			for(tabToXml.DrumNote note: dp.getDrumMeasures().get(i).getNotes()) {
				n = new drumTag.Note();
				
				// CASE 1 - GRACE (FLAM) NOTE
				if(note.getGrace() == true) {				
					makeGraceNote(note, n);									
					addToRespectiveVoice(notesVoice1, notesVoice2, n);
					previous_note_is_grace_note = true; // make the accompanying slur for next note
				}						
				// CASE 2 - UNPITCHED NOTE				
				else if(note.getUnpitchedOrRest().equals("unpitched")) {					
					makeUnpitchedNote(note,n);
					if(previous_note_is_grace_note == true) {
						drumTag.Notations notations = new drumTag.Notations();
						drumTag.Slur slur = new drumTag.Slur();
						slur.setNumber(new BigInteger("1"));
						slur.setPlacement("above");
						slur.setType("stop");
						notations.setSlur(slur);
						n.setNotations(notations);
					}
					previous_note_is_grace_note = false; // we have made the accompanying note
					addToRespectiveVoice(notesVoice1, notesVoice2, n);

				}
				// CASE 3 - BACKUPS
				else if( note.getUnpitchedOrRest().equals("backup")) {
					makeBackup(note, b);
				}        
				// CASE 4 - REST NOTES
				else {    
					makeRestNote(note,n);
					addToRespectiveVoice(notesVoice1, notesVoice2, n);

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


	} // END OF DRUM GENERATOR

	public void makeGraceNote(tabToXml.DrumNote note, drumTag.Note n){
		n.setGrace(new drumTag.Grace()); // GRACE
		
		n.getDurationOrChordOrCue().add(new drumTag.Unpitched(new BigInteger(note.getDisplayOctave()),note.getDisplayStep()));
		drumTag.Instrument instrument = new drumTag.Instrument();
		instrument.setId(note.getInstrumentID());
		n.setInstrument(instrument);
		n.setStem(new drumTag.Stem(note.getStem()));
		n.setVoice(java.lang.String.valueOf(note.getVoice()));
		
		drumTag.Notations notations = new drumTag.Notations();
		drumTag.Slur slur = new drumTag.Slur();
		slur.setNumber(new BigInteger("1"));
		slur.setPlacement("above");
		slur.setType("start");
		notations.setSlur(slur);
		n.setNotations(notations);
	}
	
	public void makeUnpitchedNote(tabToXml.DrumNote note, drumTag.Note n) {
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
		if(note.getBeam1() != null) {	
			beam = new drumTag.Beam();
			beam.setNumber(new BigInteger("1")); beam.setValue(note.getBeam1());
			n.addBeam(beam);
		}
		if(note.getBeam2() != null) {	
		beam = new drumTag.Beam();
		beam.setNumber(new BigInteger("2")); beam.setValue(note.getBeam2());
		n.addBeam(beam);
		}
		if(note.getBeam3() != null) {	
		beam = new drumTag.Beam();
		beam.setNumber(new BigInteger("3")); beam.setValue(note.getBeam3());
		n.addBeam(beam);
		}
	}
	public void makeBackup(tabToXml.DrumNote note, drumTag.Backup b) {
		b = new drumTag.Backup();
		b.setDuration(new BigDecimal(note.getDuration()));
	}
	public void makeRestNote(tabToXml.DrumNote note, drumTag.Note n) {
		n.getDurationOrChordOrCue().add(new drumTag.Rest());
		n.getDurationOrChordOrCue().add(new BigDecimal(note.getDuration()));        	   
		n.setVoice(java.lang.String.valueOf(note.getVoice()));
		n.setType(new drumTag.Type(note.getType()));
	}
	public void addToRespectiveVoice(ArrayList<drumTag.Note >notesVoice1,ArrayList<drumTag.Note> notesVoice2, drumTag.Note n) {
		if(n.getVoice().equals("1")) 	notesVoice1.add(n);
		else							notesVoice2.add(n);			
	}

	
	
	 
	public musicXML.ScorePartwise getGuitarScorePartwise(){
		return this.scorePartwise;
	}
	public drumTag.ScorePartwise getDrumScorePartwise(){
		return this.drumScorePartwise;
	}
	
	
	
	
	
	
} // END OF CLASS
