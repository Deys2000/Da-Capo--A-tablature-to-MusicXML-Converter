package tabToXml;
import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import musicXML.*;
import musicXML.String;


public class xmlGen {
	
	private File xmlfile;
	Marshaller jaxbMarshaller;
    private ScorePartwise scorePartwise;
    java.lang.String[][] attributeVals = {
        {"4"}, // divisions
        {"0"}, // fifths
        {"4","4"}, // beats and beat-type
        {"TAB","5"}, // sign and line
        {"6"}, // staff lines
        {"E","A","D","G","B","E"}, //tuning-step
        {"2","2","3","3","3","4"} // tuning-octave
    };
    
    
    /**
     * A constructor prepared for the future of this Class, it takes the instrument and makes xml stuff out of it based on instrument
     * this one is for guitar obviously
     * @param instrument
     * @param parserObject
     */
    public xmlGen(GuitarParser gp) {
    	guitarGenerator(gp.processor());
    }    

    /**
     * previously a constructor, this has now become a method the constructor call if the instrument is a guitar
     * @param gp
     */
    public void guitarGenerator(java.lang.String[][] info)
    {
    	    	
        this.scorePartwise = new ScorePartwise();
        //scorePartwise.setMovementTitle("test");
        Part part = new Part();
        part.setId("P1");
        PartList partlist = new PartList();
        ScorePart scorePart = new ScorePart();
        scorePart.setId("P1");
        PartName pn = new PartName();
        pn.setValue("Classical Guitar"); 
        scorePart.setPartName(pn);
        partlist.getPartGroupOrScorePart().add(scorePart);
        scorePartwise.setPartList(partlist);

        ArrayList<musicXML.Measure> measures = new ArrayList<musicXML.Measure>();
        musicXML.Measure measure = new musicXML.Measure();
        measure.setNumber("1");

        
        Attributes attributes = new Attributes();
        attributes.setDivisions(new BigDecimal(2));
        Key key = new Key();
        key.setFifths(new BigInteger("0"));
        attributes.setKey(key);
        Time time = new Time();
        time.setBeats("4");
        time.setBeatType("4");
        attributes.setTime(time);
        Clef clef = new Clef();
        clef.setSign("TAB");
        clef.setLine(new BigInteger("5"));
        attributes.setClef(clef);
        measure.setAttributes(attributes);
        
        StaffDetails StaffDetails = new StaffDetails();
        StaffDetails.setStaffLines(new BigInteger("6"));
        ArrayList<StaffTuning> staffTunings = new ArrayList<>();
    	for(int i = 0; i < attributeVals[5].length; i++) {
            StaffTuning staffTuning = new StaffTuning();
            staffTuning.setLine(new BigInteger(Integer.toString(i + 1)));
            staffTuning.setTuningStep(attributeVals[5][i]);
            staffTuning.setTuningOctave(new BigInteger(attributeVals[6][i]));            

            staffTunings.add(staffTuning);
    	}

        StaffDetails.setStaffTuning(staffTunings);
        attributes.setStaffDetails(StaffDetails);

        ArrayList<musicXML.Note> notes = new ArrayList<>();
        int measureNum = 1;
        for(int i = 1; i < info.length; i++)
        {
        	if(info[i][0] == null ) {
        		measure.setNote(notes);
        		notes = new ArrayList<musicXML.Note>();
        		measures.add(measure);
        		measure = new musicXML.Measure();
        		measureNum++;
        		measure.setNumber(""+measureNum);
        	}
        	else {
        		musicXML.Note note = new musicXML.Note();
        		Chord c = new Chord();
        		if( info[i][7].equals("true"))
        			note.getDurationOrChordOrCue().add(c); // chord
        		Pitch pitch = new Pitch();
        		pitch.setStep(info[i][1]);
        		//System.out.println(info[i][3]);
        		pitch.setOctave(new BigInteger(info[i][3]));
        		note.getDurationOrChordOrCue().add(pitch); // pitch
        		note.getDurationOrChordOrCue().add(new BigDecimal(info[i][0])); // duration
        		note.setVoice(info[i][2]);
        		note.setType(new Type(info[i][4]));
        		Notations notations = new Notations();
        		Technical technical = new Technical();
        		//System.out.println(info[i][5]);
        		technical.setString( new String(new BigInteger(info[i][5])));
        		technical.setFret(new Fret(new BigInteger(info[i][6])));
        		notations.setTechnical(technical);
        		note.setNotations(notations);
        		notes.add(note);
        	}
        }
        
       
        measure.setNote(notes);
        part.setMeasure(measures);
        scorePartwise.setPart(part);
        
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

    public File createFile(File file)
    {
    	try {
        jaxbMarshaller.marshal(scorePartwise, file); // return as a string???
    	}
    	catch(JAXBException e) {
    		e.printStackTrace();
    	}finally {
    		return file;
    	}
    }
    
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
}
