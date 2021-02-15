package tabToXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
 
    public static void runner() {
 
    	//the values are as follows
    	// division, fifths, beats, beat type, sign, line and stafflines, tuningsteps and tuning octaves 
    	String[][] attributeVals = {};
    	
    	
    	// noteVals array contain all the information for each note
    	//each row contains: duration, step, alter,octave,type,string,fret in each row
    	String[][] noteVals = {	{"1","E","1","5","2","eighth","6","3"},
    							{"1","E",null,"4","2","eighth","4","2"},
    							{"1","E","1","3","2","eighth","3","1"},
    							{"1",null,"1","2","2","eighth","2","0"}			};
    	
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentbuilder;
        
        try {
        	
            documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document musicTab = documentbuilder.newDocument();
 
                       
            //creation of all object nodes
            getScorePartwise(musicTab, attributeVals, noteVals);
            
            
            // AFTER ADDING ALL THE NODES //
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");            
            DOMSource source = new DOMSource(musicTab);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);
            System.out.println("XML complete");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
//    	for(int i = 0; i < attributeVals.length; i++)
//    		measure_element.appendChild(getAttribute(doc,attributeVals[i][0],attributeVals[i][1],attributeVals[i][2],attributeVals[i][3]));    	
    	for(int i = 0; i < noteVals.length; i++)
    		measure_element.appendChild(getNote(doc,noteVals[i][0],noteVals[i][1],noteVals[i][2],noteVals[i][3]));    	
    	return measure_element;
    }    
    
    
    
    
    /////////////////////////////// START OF NOTE CREATION ////////////////////////
    private static Node getNote(Document doc, String durationVal, String stepVal, String alterVal, String octaveVal) {
        Element note_element = doc.createElement("note");
        note_element.appendChild(getDuration(doc, durationVal));
        note_element.appendChild(getPitch(doc, stepVal, alterVal, octaveVal));
        return note_element;
    }
    
    // DURATION
    private static Node getDuration(Document doc, String durationVal) {
    	Element duration_element = doc.createElement("duration");
    	if(durationVal != null) duration_element.appendChild(doc.createTextNode(durationVal));
    	return duration_element;
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
}