package tabToXml;

import java.util.ArrayList;

// In progress - Syed & Miguel

public class DrumParser {
	
	
	public DrumParser() {
		
	}
	//first clean the input so it contains only info i need in one long line of tablature
	
	
	//second check for bad tablature
	// examples: there is no tab, the tab is not spaced properly, etc
	
	
	//third make a character array
	
	// loop should cycle through vertically gathering information
	//information that we need to gather
		// create note objects that are in an array of measure which is in a larger array of measures to form the whole piece. 
		// note unpitched or rest
		// if unpitched, then step,octave, instrument id and stem are dtermined by string - easy
		// if unpitched, then we must gather duration, voice and type ( type is basically beat*beatype/duration)
		// if rest then we need duration, voice and type (type is the same as above)
		

	public String[] getDrumInstrument(String symbol) {
		//gets the information of the instrument given its symbol on the tab line
		
		//name, id, step, octave, stem
		String name, id, step, octave, stem, notehead;
		
		switch(symbol) {
			
			case "B":
				name = "Bass Drum 1";
				id = "P1-I36";
				step = "";
				octave = "";
				stem = "";	
				notehead = "";
				break;
			
//			case "B2":
//				name = "Bass Drum 2";
//				id = "P1-I37";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
//			case "SS":
//				name = "Side Stick";
//				id = "P1-I38";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
			case "S":
				name = "Snare";
				id = "P1-I39";
				step = "";
				octave = "";
				stem = "";		
				notehead = "";

				break;
//			case "LFT":
//				name = "Low Floor Tom";
//				id = "P1-I42";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
			case "HH":
				name = "Closed Hi-Hat";
				id = "P1-I43";
				step = "";
				octave = "";
				stem = "";		
				notehead = "";

				break;
//			case "HFT":
//				name = "High Floor Tom";
//				id = "P1-I44";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
//			case "PHH":
//				name = "Pedal Hi-Hat";
//				id = "P1-I45";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
//			case "LT":
//				name = "Low Tom";
//				id = "P1-146";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//
//				break;
//			case "OHH":
//				name = "Open Hi-Hat";
//				id = "P1-I47";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//
//				break;
//			case "LMT":
//				name = "Low-Mid Tom";
//				id = "P1-I48";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//			case "HMT":
//				name = "Hi-Mid Tom";
//				id = "P1-I49";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
			case "C":
				name = "Crash Cymbal";
				id = "P1-I50";
				step = "";
				octave = "";
				stem = "";			
				notehead = "";

				break;
//			case "HT":
//				name = "High Tom";
//				id = "P1-I51";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//			case "RC":
//				name = "Ride Cymbal";
//				id = "P1-I52";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//			case "CC":
//				name = "Chinese Cymbal";
//				id = "P1-I53";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//
//				break;
//			case "RB":
//				name = "Ride Bell";
//				id = "P1-I54";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//				
//			case "T":
//				name = "Tambourine";
//				id = "P1-I54";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//				
//			case "SC":
//				name = "Splash Cymbal";
//				id = "P1-I56";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//				
//			case "CB":
//				name = "Cowbell";
//				id = "P1-I57";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//
//				break;
//				
//			case "C2":
//				name = "Crash Cymbal 2";
//				id = "P1-I58";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//			case "R2":
//				name = "Ride Cymbal 2";
//				id = "P1-I60";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//
//				break;
//			case "OHC":
//				name = "Open Hi Conga";
//				id = "P1-I64";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//
//				break;
//			case "LC":
//				name = "Low Conga";
//				id = "P1-I64";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//
//				break;
						
			default:
				//throw some incorrect stuff exception
				name = "default";
				id = "default";
				step = "default";
				octave = "default";
				stem = "default";	
				notehead = "default";
			
		}
		
		String[] info = {name, id,step,octave,stem,notehead};
		return info;

	}
}

/*
 * gathers information about each strings on the drums
 */
//class StringInfo{
//	
//	public StringInfo() {
//		
//	}
//	public ArrayList<String> getStringInfo(){
//		
//	}
//}

// incomplete
class Note{
	String unpitchedOrRest = "";
	String displayStep = "";
	String displayOctave = "";
	int duration = 0;
	String instrumentID = "";
	int voice = 0;
	String type = "";
	String stem = "";
	
	Note(String unpitchedOrRest, String line ){
		
	}
}


