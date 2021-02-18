package tabToXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
/**
 * @author Crunchify.com
 * In Java How to Create XML File using DOM parser? Writing Out a DOM as an XML File.
 * Version: 1.1
 */
 
public class XMLGenerator {
 
    public static String runner(String[][] noteValsX) {
 
    	//the values are as follows
    	// division, fifths, beats, beat type, sign, line and stafflines, tuningsteps and tuning octaves 
    	String[][] attributeVals = {
    			{"2"}, // divisions
    			{"0"}, // fifths
    			{"4","4"}, // beats and beat-type
    			{"TAB","5"}, // sign and line
    			{"6"}, // staff lines
    			{"E","A","D","G","B","E"}, //tuning-step
    			{"2","2","3","3","3","4"} // tuning-octave
    	};
    	
    	
    	// noteVals array contain all the information for each note
    	//each row contains: duration, step, alter,octave,type,string,fret in each row
    	String[][] noteVals = {	{"1","E","1","5","2","eighth","6","3"},
    							{"1","E",null,"4","2","eighth","4","2"},
    							{"1","E","1","3","2","eighth","3","1"},
    							{"1",null,"1","2","2","eighth","2","0"}			};
    	
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentbuilder;
        String xmlString = "";
        
        try {
        	
            documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document musicTab = documentbuilder.newDocument();
 
                       
            //creation of all object nodes         
            getScorePartwise(musicTab, attributeVals, noteValsX);
            
            
            // AFTER ADDING ALL THE NODES //
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");            
            DOMSource source = new DOMSource(musicTab);
           
            
            StringWriter writer = new StringWriter();
            
            //transform document to string 
            transformer.transform(source, new StreamResult(writer));
     
            xmlString = writer.getBuffer().toString();   

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return xmlString;  
    }
 
    private static void getScorePartwise(Document doc, String[][] attributeVals, String[][] noteVals) {
    	 Element score_partwise_element = doc.createElement("score-partwise");
    	 //score_partwise_element.setAttribute("stuff",stuff);
    	 doc.appendChild(score_partwise_element);    
    	 score_partwise_element.appendChild(getPart(doc, attributeVals, noteVals));
    }
    private static Node getPart(Document doc, String[][] attributeVals, String[][] noteVals) {
        Element part_element = doc.createElement("part");
        part_element.appendChild(getMeasure(doc, attributeVals, noteVals));
        return part_element;
    }
 
    private static Node getMeasure(Document doc, String[][] attributeVals, String[][] noteVals) {
    	Element measure_element = doc.createElement("measure"); 	
    	
    	measure_element.appendChild(getAttributes(doc,attributeVals[0][0],attributeVals[1][0],attributeVals[2],attributeVals[3],attributeVals[4][0],attributeVals[5],attributeVals[6]));    	
    	
    	for(int i = 0; i < noteVals.length; i++)
    		measure_element.appendChild(getNote(doc,noteVals[i][0],noteVals[i][1],noteVals[i][2],noteVals[i][3],noteVals[i][4],noteVals[i][5],noteVals[i][6]));    	
    	return measure_element;
    }    
    
    
    /////////////////////////////// START OF ATTRIBUTES CREATION ////////////////////////
	// division, fifths, beats, beat type, sign, line and stafflines, tuningsteps and tuning octaves 
    private static Node getAttributes(Document doc, String divisionVal, String fifthsVal, String[] timeVals, String[] clefVals, String stafflinesVal, String[] tuningStepVals, String[] tuningOctaveVals) {
        Element note_element = doc.createElement("attributes");
        note_element.appendChild(getDivisions(doc, divisionVal));
        note_element.appendChild(getKey(doc, fifthsVal));
        note_element.appendChild(getTime(doc, timeVals)); // giving default for voice value 
        note_element.appendChild(getClef(doc, clefVals));
        note_element.appendChild(getStaffDetails(doc, stafflinesVal, tuningStepVals, tuningOctaveVals));
        return note_element;
    }
	private static Node getDivisions(Document doc, String divisionVal) {
    	Element division_element = doc.createElement("division");
    	if(divisionVal != null) division_element.appendChild(doc.createTextNode(divisionVal));
    	return division_element;
    }
    private static Node getKey(Document doc, String fifthsVal) {
    	Element key_element = doc.createElement("key");
    	key_element.appendChild(getFifths(doc,fifthsVal));
    	return key_element;
    }
    private static Node getFifths(Document doc, String fifthsVal) {
    	Element fifths_element = doc.createElement("fifths");
    	if(fifthsVal != null) fifths_element.appendChild(doc.createTextNode(fifthsVal));
    	return fifths_element;
	}
    private static Node getTime(Document doc, String[] timeVals) {
    	Element time_element = doc.createElement("key");
    	time_element.appendChild(getBeats(doc,timeVals[0]));
    	time_element.appendChild(getBeatType(doc,timeVals[1]));
    	return time_element;
    }
    private static Node getBeats(Document doc, String beatVal) {
    	Element beat_element = doc.createElement("fifths");
    	if(beatVal != null) beat_element.appendChild(doc.createTextNode(beatVal));
    	return beat_element;
	}
    private static Node getBeatType(Document doc, String beatTypeVal) {
    	Element beat_type_element = doc.createElement("fifths");
    	if(beatTypeVal != null) beat_type_element.appendChild(doc.createTextNode(beatTypeVal));
    	return beat_type_element;
	}
    private static Node getClef(Document doc, String[] clefVals) {
    	Element clef_element = doc.createElement("key");
    	clef_element.appendChild(getSign(doc,clefVals[0]));
    	clef_element.appendChild(getLine(doc,clefVals[1]));
    	return clef_element;
	}
	private static Node getLine(Document doc, String signVal) {
		Element line_element = doc.createElement("fifths");
    	if(signVal != null) line_element.appendChild(doc.createTextNode(signVal));
    	return line_element;
	}
	private static Node getSign(Document doc, String lineVal) {
		Element sign_element = doc.createElement("fifths");
    	if(lineVal != null) sign_element.appendChild(doc.createTextNode(lineVal));
    	return sign_element;
	}

	private static Node getStaffDetails(Document doc, String stafflinesVal, String[] tuningStepVals,
			String[] tuningOctaveVals) {
    	Element staff_details_element = doc.createElement("staff-details");
    	staff_details_element.appendChild(getStaffLines(doc,stafflinesVal));
    	for(int i = 0; i < Integer.parseInt(stafflinesVal); i++)
    		staff_details_element.appendChild(getStaffTuning(doc,tuningStepVals[i], tuningOctaveVals[i]));
    	return staff_details_element;
	}

	private static Node getStaffLines(Document doc, String stafflinesVal) {
		Element stafflines_element = doc.createElement("staff-lines");
    	if(stafflinesVal != null) stafflines_element.appendChild(doc.createTextNode(stafflinesVal));
    	return stafflines_element;
	}
	private static Node getStaffTuning(Document doc, String tuningStepVal, String tuningOctaveVal) {
		Element staffTuning_element = doc.createElement("staff-tuning");
		staffTuning_element.appendChild(getTuningStep(doc,tuningStepVal));
		staffTuning_element.appendChild(getTuningOctave(doc,tuningOctaveVal));
    	return staffTuning_element;
	}
	private static Node getTuningStep(Document doc, String tuningStepVal) {
		Element tuningStep_element = doc.createElement("tuning_step");
    	if(tuningStepVal != null) tuningStep_element.appendChild(doc.createTextNode(tuningStepVal));
    	return tuningStep_element;
	}
	private static Node getTuningOctave(Document doc, String tuningStepVal) {
		Element tuningOctave_element = doc.createElement("tuning-octave");
    	if(tuningStepVal != null) tuningOctave_element.appendChild(doc.createTextNode(tuningStepVal));
    	return tuningOctave_element;
	}


	/////////////////////////////// END OF ATTRIBUTES CREATION ////////////////////////
    
    /////////////////////////////// START OF NOTE CREATION ////////////////////////
    private static Node getNote(Document doc, String durationVal, String stepVal, String alterVal, String octaveVal, String typeVal, String fretVal, String stringVal) {
        Element note_element = doc.createElement("note");
        note_element.appendChild(getPitch(doc, stepVal, alterVal, octaveVal));
        note_element.appendChild(getDuration(doc, durationVal));
        note_element.appendChild(getVoice(doc, "1")); // giving default for voice value 
        note_element.appendChild(getType(doc, typeVal));
        note_element.appendChild(getNotations(doc, stringVal, fretVal));
        return note_element;
    }
    // DURATION
    private static Node getDuration(Document doc, String durationVal) {
    	Element duration_element = doc.createElement("duration");
    	if(durationVal != null) duration_element.appendChild(doc.createTextNode(durationVal));
    	return duration_element;
    }
    //VOICE
    private static Node getVoice(Document doc, String voiceVal) {
    	Element voice_element = doc.createElement("voice");
    	voice_element.appendChild(doc.createTextNode(voiceVal));
    	return voice_element;
    }
    //TYPE
    private static Node getType(Document doc, String typeVal) {
    	Element type_element = doc.createElement("type");
    	type_element.appendChild(doc.createTextNode(typeVal));
    	return type_element;
    }
    //NOTATIONS
    private static Node getNotations(Document doc, String stringVal, String fretVal) {
    	Element notations_element = doc.createElement("notations");
    	notations_element.appendChild(getTechnical(doc, stringVal, fretVal));
    	return notations_element;
    }
    	//TECHNICAL
    private static Node getTechnical(Document doc, String stringVal, String fretVal) {
    	Element technical_element = doc.createElement("technical");
    	technical_element.appendChild(getString(doc,stringVal));
    	technical_element.appendChild(getFret(doc,fretVal));
    	return technical_element;
    }
    		//STRING
    private static Node getString(Document doc, String stringVal) {
    	Element string_element = doc.createElement("string");
    	string_element.appendChild(doc.createTextNode(stringVal));
    	return string_element;
    }
    		//FRET
    private static Node getFret(Document doc, String fretVal) {
    	Element fret_element = doc.createElement("fret");
    	fret_element.appendChild(doc.createTextNode(fretVal));
    	return fret_element;
    }
    // PITCH
    private static Node getPitch(Document doc, String stepVal, String alterVal, String octaveVal) {
    	Element pitch = doc.createElement("pitch");
    	if(stepVal != null)   	pitch.appendChild(getStep(doc, stepVal));
    	if(alterVal != null)   	pitch.appendChild(getAlter(doc, alterVal));
    	if(octaveVal != null)  	pitch.appendChild(getOctave(doc, octaveVal));
    	return pitch;	
    }
    private static Node getStep(Document doc, String stepVal) {
    	Element step_element = doc.createElement("step");
    	step_element.appendChild(doc.createTextNode(stepVal));
    	return step_element;
    }
    private static Node getAlter(Document doc, String alterVal) {
    	Element alter_element = doc.createElement("alter");
    	alter_element.appendChild(doc.createTextNode(alterVal));
    	return alter_element;
    }
    private static Node getOctave(Document doc, String octaveVal) {
    	Element alter_element = doc.createElement("octave");
    	alter_element.appendChild(doc.createTextNode(octaveVal));
    	return alter_element;
    }
    ///////////////////////////////// END OF NOTE CREATION /////////////////////////
    
    
    
    // A PROCESSING METHOD FOR TEMPORARY PURPOSES
    public static String[][] processor(ArrayList<String> notes, ArrayList<String> fretNums,
			ArrayList<String> fretStrings, ArrayList<String> durationArr, ArrayList<String> typeArr) {
    	//cleaning Arrays
    	for(int i = notes.size()-1; i >=0; i--) {
    		if(notes.get(i).contains("|")) {
    			notes.remove(i);
    			fretNums.remove(i);
    			fretStrings.remove(i);
    			durationArr.remove(i);
    			typeArr.remove(i);
    		}
    	}
    	// inserting into 2d array
    	//each row contains: duration, step, alter,octave,type,string,fret in each row
    	String [][] infoArray = new String[notes.size()][7];
    	for(int i = 0; i < notes.size(); i++) {
    		infoArray[i][0] = durationArr.get(i);
    		infoArray[i][1] = notes.get(i).substring(0,1);
    		infoArray[i][2] = "1"; //default4now
    		infoArray[i][3] = notes.get(i).substring(1,2);
    		infoArray[i][4] = typeArr.get(i);
    		infoArray[i][5] = fretStrings.get(i);
    		infoArray[i][6] = fretNums.get(i);
    	}
		return infoArray;
	}
}

	