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
    private ScorePartwise scorePartwise;
    
    // This section below needs work on automatic value entry
    
    private java.lang.String[][] attributeVals = {
        {"4"}, // divisions
        {"0"}, // fifths
        {"4","4"}, // beats and beat-type
        {"TAB","5"}, // sign and line
        {"6"}, // staff lines
        {"E","A","D","G","B","E"}, //tuning-step - deafault, the instrument detection is done in the constructor
        {"2","2","3","3","3","4"} // tuning-octave
    };

    
    public xmlGen(DrumParser dp, TextFileReader tfr) {
    	drumGenerator(dp);
        attributeVals[4][0] = tfr.staffLines();
    }
    
    public xmlGen(BassParser bp, TextFileReader tfr) {
    	guitarGenerator(bp.processor());
        attributeVals[4][0] = tfr.staffLines();

    }
    
    /**
     * this one is for guitar obviously
     * @param instrument
     * @param parserObject
     */
    public xmlGen(GuitarParser gp, TextFileReader tfr) {
    	guitarGenerator(gp.processor());
       	//attributeVals[4][0] = tfr.staffLines();
    }    

    /**
     * previously a constructor, this has now become a method the constructor call if the instrument is a guitar
     * @param gp
     */
    public void guitarGenerator(java.lang.String[][] info)
    {

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
        Attributes attributes = new Attributes();
        attributes.setDivisions(new BigDecimal(2));

        Key key = new Key();
        key.setFifths(new BigInteger("0"));
        attributes.setKey(key);

        attributes.setTime(new Time("4", "4")); // constructor takes beat and beat type
        attributes.setClef(new Clef("TAB", new BigInteger("5"))); // constuctor sets sign and line

        StaffDetails staffDetails = new StaffDetails(new BigInteger("6")); // constructor takes the number of lines

        //creating all the staff tunings that will go into the staff details tag above 
        ArrayList<StaffTuning> staffTunings = new ArrayList<>();        
    	for(int i = 0; i < attributeVals[5].length; i++)
            staffTunings.add(new StaffTuning(new BigInteger(Integer.toString(i + 1)),attributeVals[5][i],new BigInteger(attributeVals[6][i])));       
    		// loop above adds all information into a stafftuning object before inserting into stafftuning list
    		// the constructor takes line, tuning step and tuning octave
    	
    	//insert the arraylist of staff tunings into the stffdetails object
        staffDetails.setStaffTuning(staffTunings);
        // insert staff details into attributes
        attributes.setStaffDetails(staffDetails);
        // At this point attributes contains all the requried information, it is added to the first measure
        measure.setAttributes(attributes);

        // creating a list of notes to put into each measure object
        ArrayList<musicXML.Note> notes = new ArrayList<>();
        int measureNum = 1;
        for(int i = 1; i < info.length; i++)
        {
        	// if you happen to be at a "|", then you create a new measure object and store the previous one
        	if(info[i][0] == null ) {
        		measure.setNote(notes);
        		notes = new ArrayList<musicXML.Note>();
        		measures.add(measure);
        		measure = new musicXML.Measure();
        		measureNum++;
        		measure.setNumber(""+measureNum);
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
            note.setType(new Type(info[i][4]));
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
            else if( info[i][8].substring(0,1).equals("N")) {
            	Harmonic nh = new Harmonic();
            	Natural nat = new Natural();
            	nh.setNatural(nat);
            	technical.setHarmonic(nh);
            }
            //-------
            else if( info[i][8].substring(0,1).equals("a")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1));
            	notations.setSlide(sl);
//            	no.setHammerOn(ho);
//            	Slur slur = new Slur();
//            	slur.setType(info[i][8].substring(1));
//            	notations.setSlur(slur);
            }
            else if( info[i][8].substring(0,1).equals("d")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1));
            	notations.setSlide(sl);
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1));
//            	technical.setPullOff(po);
//            	Slur slur = new Slur();
//            	slur.setType(info[i][8].substring(1));
//            	notations.setSlur(slur);
            }
            else if(info[i][8].substring(0,1).equals("A") && info[i][8].substring(5,6).equals("D")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1,5));
            	notations.setSlide(sl);
            	
            	Slide sl2 = new Slide();
            	sl2.setType(info[i][8].substring(6));
            	notations.setSlide(sl2);
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1,5));
//            	technical.setPullOff(po);
//            	
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(6));
//            	technical.setHammerOn(ho);
            }   
            else if(info[i][8].substring(0,1).equals("D") && info[i][8].substring(5,6).equals("A")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1,5));
            	notations.setSlide(sl);
            	
            	Slide sl2 = new Slide();
            	sl2.setType(info[i][8].substring(6));
            	notations.setSlide(sl2);
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(1,5));
//            	technical.setHammerOn(ho);
//            	
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(6));
//            	technical.setPullOff(po);
            }
            
            else if(info[i][8].substring(0,1).equals("A") && info[i][8].substring(5,6).equals("A")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1,5));
            	notations.setSlide(sl);
            	
            	Slide sl2 = new Slide();
            	sl2.setType(info[i][8].substring(6));
            	notations.setSlide(sl2);            	
//            	PullOff po = new PullOff();
//            	po.setType(info[i][8].substring(1,5));
//            	technical.setPullOff(po);
//            	
//            	PullOff po1 = new PullOff();
//            	po1.setType(info[i][8].substring(6));
//            	technical.setPullOff(po1);
            }
            
            else if(info[i][8].substring(0,1).equals("D") && info[i][8].substring(5,6).equals("D")) {
            	Slide sl = new Slide();
            	sl.setType(info[i][8].substring(1,5));
            	notations.setSlide(sl);
            	
            	Slide sl2 = new Slide();
            	sl2.setType(info[i][8].substring(6));
            	notations.setSlide(sl2);
//            	HammerOn ho = new HammerOn();
//            	ho.setType(info[i][8].substring(1,5));
//            	technical.setHammerOn(ho);
//            	
//            	HammerOn ho1 = new HammerOn();
//            	ho1.setType(info[i][8].substring(6));
//            	technical.setHammerOn(ho1);
            }
            //-------------
           
           	else {
           		
           	}
           	
            technical.setString( new musicXML.String(new BigInteger(info[i][5])));
            technical.setFret(new Fret(new BigInteger(info[i][6])));
            notations.setTechnical(technical);
            note.setNotations(notations);
            notes.add(note);
        	}
        }
        measure.setNote(notes);        
        part.setMeasure(measures);
        // at this point all the measures have been created along with all the notes inside them
        
        scorePartwise.setPart(part);
        // add the part and our process of creating objects is complete
        // now we just need to marshall
        
        // ADDED TO CONSTRUCTOR
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
            jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            

            //jaxbMarshaller.marshal(scorePartwise, System.out); //prints to console

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        
    }

    /**
     * This method replaces the contents of a file with the XML of a tablature, its used for creating a file to put on a music player
     * @param file
     * @return
     */
    @SuppressWarnings("finally")
	public File createFile(File file)
    {
    	try {
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		jaxbMarshaller.marshal(scorePartwise, file); // return as a string???
    	}
    	catch(JAXBException e) {
    		e.printStackTrace();
    	}finally {
    		return file;
    	}
    }
    

    /**
     * This method gets the XML information as a string, its useful for printing on console or to GUI
     * @return
     */
    @SuppressWarnings("finally")
	public java.lang.String getXMLContent() {
    	StringWriter xml =  new StringWriter();
    	try {    		
            jaxbMarshaller.marshal(scorePartwise, xml); // return as a string???
        }
        catch(JAXBException e) {
        	e.printStackTrace();
        }finally {
        	return xml.toString();
        }
    }
    
    /**
     * WORKING ON GETTING DRUMS TO WORK
     * @param gp
     */
    public void drumGenerator(DrumParser dp)
    {

    	// creating the outermost tag "score-partwise"
        this.scorePartwise = new ScorePartwise();
       // scorePartwise.setMovementTitle("test"); // move to constuctor
        //PartList partlist = new PartList();
        
        ScorePart scorepart = new ScorePart();//"P1","Drumset");
        scorepart.setId("P1");
        PartName partname = new PartName();
        partname.setValue("Drumset");
        scorepart.setPartName(partname);
        //adding the list of insturments and their ID's
        for(StringInfo stringinfo: dp.getTabStrings()) {
        	ScoreInstrument si = new ScoreInstrument();
        	si.setId(stringinfo.getInstrumentId());
        	si.setInstrumentName(stringinfo.getInstrumentName());
    		System.out.println(si.getInstrumentName());
    		System.out.println(si.getId());
        	try {
        	scorepart.addScoreInstrument(si);
        	}
        	catch(NullPointerException e) {
        		System.out.println(">>>>>>>>>>>>>>>>ISSUE");
        		System.out.println(scorepart.getPartName().getValue());
        		System.out.println(stringinfo.getInstrumentName());

        	}
        	finally {
        		
        	}
        }
        scorePartwise.setPartList( new PartList(scorepart) ); // constructor sets ID and part-name
        
        
        
        Part part = new Part("P1"); // constructer sets ID
        

        // creating measure list that will hold all the measures which will each contain all the notes
        ArrayList<musicXML.Measure> measures = new ArrayList<musicXML.Measure>();
        // initializing the first measure
        musicXML.Measure measure = new musicXML.Measure("1"); // constructor sets the measure number
           
        // creating the attributes section that goes into the first measure
        Attributes attributes = new Attributes();
        attributes.setDivisions(new BigDecimal(4));
        Key key = new Key();
        key.setFifths(new BigInteger("0"));
        attributes.setKey(key);
        attributes.setTime(new Time("4", "4")); // constructor takes beat and beat type
        attributes.setClef(new Clef("percussion", new BigInteger("2"))); // constuctor sets sign and line

        // ADDING THE NOTES IN THE FIRST MEASURE
       measure.setAttributes(attributes);
       // adding all the notes of the first measure
	   ArrayList<musicXML.Note> notes = new ArrayList<musicXML.Note>();
	   musicXML.Note n;
       for(tabToXml.Note note: dp.getMeasures().get(0).getNotes()) {
    	   n = new musicXML.Note();
    	   // if the note is not a rest note give it the following values as well
    	   if( note.getUnpitchedOrRest().equals("unpitched")) {        	   
        	   n.getDurationOrChordOrCue().add(new Unpitched(new BigInteger(note.getDisplayOctave()),note.getDisplayStep()));        	   
        	   //if(note.getChord())
        		//   n.getDurationOrChordOrCue().add(new Chord());
        	   Instrument instrument = new Instrument();
        	   instrument.setId(note.getInstrumentID());
        	   n.setInstrument(instrument);        	   
        	   n.setStem(new Stem(note.getStem()));
        	   Notehead notehead = new Notehead();
        	   notehead.setValue(note.getNotehead());
        	   n.setNotehead(notehead);
    	   }   
    	   // the else statement takes the rest notes
    	   else {
    		   n.getDurationOrChordOrCue().add(new Rest());
    	   }    		   
    	   // duration, voice and type are for all notes regardless
    	   n.getDurationOrChordOrCue().add(new BigDecimal(note.getDuration()));
    	   n.setVoice(java.lang.String.valueOf(note.getVoice()));
    	   n.setType(new Type(note.getType()));    	       	   
    	   notes.add(n);
       }
       //adding the first batch of notes to the first measure
       measure.setNote(notes);  
       measures.add(measure);
    	 
       // Making all the measures after the first one
       int measureNum = 1;
       for(int i = 1; i < dp.getMeasures().size(); i++) {
    	   notes = new ArrayList<musicXML.Note>();
    	   measureNum++;
           measure = new musicXML.Measure(java.lang.String.valueOf(measureNum));

    	   for(tabToXml.Note note: dp.getMeasures().get(i).getNotes()) {
        	   n = new musicXML.Note();
        	   // if the note is not a rest note give it the following values as well
        	   if(note.getUnpitchedOrRest().equals("unpitched")) {
        		   n.getDurationOrChordOrCue().add(new Unpitched(new BigInteger(note.getDisplayOctave()),note.getDisplayStep()));
        		   //if(note.getChord())
        			//   n.getDurationOrChordOrCue().add(new Chord());
        		   Instrument instrument = new Instrument();
            	   instrument.setId(note.getInstrumentID());
            	   n.setInstrument(instrument);
            	   n.setStem(new Stem(note.getStem()));
            	   Notehead notehead = new Notehead();
            	   notehead.setValue(note.getNotehead());
            	   n.setNotehead(notehead);
        	   }else {     	   // the else statement takes the rest notes

        		   n.getDurationOrChordOrCue().add(new Rest());
        	   }        	   
        	   n.getDurationOrChordOrCue().add(new BigDecimal(note.getDuration()));        	   
        	   n.setVoice(java.lang.String.valueOf(note.getVoice()));
        	   n.setType(new Type(note.getType()));
        	   notes.add(n);
           }
           //adding the first batch of notes to the first measure
           measure.setNote(notes); 
           measures.add(measure);
       	}
       
       	// At this point, we should have all the measures containing all the notes;
       //we also have an extra empty measure created at the end so we remove it
       measures.remove(measures.size()-1);
       part.setMeasure(measures);
        scorePartwise.setPart(part);
        // add the part and our process of creating objects is complete
        // now we just need to marshall
        
        // ADDED TO CONSTRUCTOR
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
            jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            

            jaxbMarshaller.marshal(scorePartwise, System.out); //prints to console

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        
    }
}
