package tabToXml;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// In progress - Syed & Miguel

public class DrumParser {
	
	//contains the information on each string in the tab
	// contains: name, id, step, octave, stem, notehead
	ArrayList<StringInfo> tabStrings = new ArrayList<>();
	ArrayList<Measure> measures = new ArrayList<>();
	
	public DrumParser(String exampleInputX) { //assume the class receives a parsed and clean tab
		
		//first clean the input so it contains only info i need in one long line of tablature
		//createParsedDrum(inputFile) perhaps
		//assume the following is what is recieved or created
		String[] exampleInput = 	{	" C:|X---------------|X---------------|X---------------|X---------------|",
										"HH:|----o---o---o---|----o---o---o---|----o---o---o---|----o---o---o---|",
										" S:|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|",
		 								" B:|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|"};

		
		//second check for bad tablature (to do later)
		//examples: there is no tab, the tab is not spaced properly, etc, all lines should have equal length
		
		
		// gather the information on each string and complete the tabStrings arraylist
		int numOfRows = exampleInput.length;
		for( int i = 0; i < numOfRows; i++) {
			String instrumentSymbol = exampleInput[i].substring(0,exampleInput[i].indexOf(':'));
			tabStrings.add(new StringInfo(instrumentSymbol));
			//remove the strings in front
			exampleInput[i] = exampleInput[i].substring(exampleInput[i].indexOf(':')+1);
		}
		
		//making a character array
		char[][] tab = new char[exampleInput.length][exampleInput[0].length()];
		for(int i = 0; i < tab.length; i++) {
			for(int j = 0; j < tab[i].length; j++ ) {
				tab[i][j] = exampleInput[i].charAt(j);
			}
		}
		// uncomment below to check if it works
		// printCharArray(tab);
		
		//everything above this point works correctly, but is not yet prepared for bad input
		
		// loop should cycle through vertically gathering information
		//information that we need to gather
			// create note objects that are in an array of measure which is in a larger array of measures to form the whole piece. 
			// note unpitched or rest
			// if unpitched, then step,octave, instrument id and stem are determined by string - easy
			// if unpitched, then we must gather duration, voice and type ( type is basically beat*beat-type/duration)
			// if rest then we need duration, voice and type (type is the same as above)

		//process of getting duration 
			//check if you are at a 4th multiple spot
			//stop at the note that you want to get duration for
			//...in progress
		
		
		//go through every column
		for(int i = 0; i < tab[0].length;  i++) {
			
			//go through each row
			for(int j = 0; j < tab.length; j++) {
				//if()
			}		
			
		}
		
		
	}
	
	//just a temporary method for printing out stuff, can delete later
		public void printCharArray(char[][] tab) {
			for(int i = 0; i < tab.length; i++) {
				for(int j = 0; j < tab[i].length; j++ ) {
					System.out.print(tab[i][j]);
				}
				System.out.println();
			}
		}
}

class StringInfo{
		//gets the information of the instrument given its symbol on the tab line
		//name, id, step, octave, stem
		String name, id, step, octave, stem, notehead;
		
		public StringInfo(String symbol) {			
		
		switch(symbol) {
			
			case "B":
				name = "Bass Drum 1";
				id = "P1-I36";
				step = "F";
				octave = "4";
				stem = "down";	
				notehead = "o";
				break;
				
			case "C":
				name = "Crash Cymbal";
				id = "P1-I50";
				step = "A";
				octave = "5";
				stem = "up";			
				notehead = "x";
				break;
				
			case "S":
				name = "Snare";
				id = "P1-I39";
				step = "C";
				octave = "5";
				stem = "up";		
				notehead = "o";
				break;
				
			case "HH":
				name = "Closed Hi-Hat"; // High hat in doc
				id = "P1-I43";
				step = "G";
				octave = "5";
				stem = "up";		
				notehead = "x";
				break;
			
			// Ask Billy for advice when uncommenting the instruments below
				
//			case "B2":
//				name = "Bass Drum 2";
//				id = "P1-I37";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "SS":
//				name = "Side Stick";
//				id = "P1-I38";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "LFT":
//				name = "Low Floor Tom";
//				id = "P1-I42";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "HFT":
//				name = "High Floor Tom";
//				id = "P1-I44";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "PHH":
//				name = "Pedal Hi-Hat";
//				id = "P1-I45";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "LT":
//				name = "Low Tom";
//				id = "P1-146";
//				step = "";
//				octave = "";
//				stem = "";		
//				notehead = "";
//				break;
//			case "OHH":
//				name = "Open Hi-Hat";
//				id = "P1-I47";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;
//			case "LMT":
//				name = "Low-Mid Tom";
//				id = "P1-I48";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "HMT":
//				name = "Hi-Mid Tom";
//				id = "P1-I49";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "HT":
//				name = "High Tom";
//				id = "P1-I51";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "RC":
//				name = "Ride Cymbal";
//				id = "P1-I52";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "CC":
//				name = "Chinese Cymbal";
//				id = "P1-I53";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;
//			case "RB":
//				name = "Ride Bell";
//				id = "P1-I54";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;			
//			case "T":
//				name = "Tambourine";
//				id = "P1-I54";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;			
//			case "SC":
//				name = "Splash Cymbal";
//				id = "P1-I56";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;		
//			case "CB":
//				name = "Cowbell";
//				id = "P1-I57";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;			
//			case "C2":
//				name = "Crash Cymbal 2";
//				id = "P1-I58";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "R2":
//				name = "Ride Cymbal 2";
//				id = "P1-I60";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;
//			case "OHC":
//				name = "Open Hi Conga";
//				id = "P1-I64";
//				step = "";
//				octave = "";
//				stem = "";			
//				notehead = "";
//				break;
//			case "LC":
//				name = "Low Conga";
//				id = "P1-I64";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;
						
			default:
				//throw some incorrect stuff exception later
				name = "default";
				id = "default";
				step = "default";
				octave = "default";
				stem = "default";	
				notehead = "default";
		}
			
		}
		
		public String getInstrumentName() {return this.name;}
		public String getInstrumentId() {return id;}
		public String getDisplayStep() {return step;}
		public String getDisplayOctave() {return octave;}
		public String getStem() {return stem;}
		public String getNotehead() {return notehead;}

}
	
class Measure{
	ArrayList<Note> notes = new ArrayList<Note>();
	public void addNote(Note note) {
		this.notes.add(note);
	}
}

// incomplete
class Note {
	String unpitchedOrRest = "";
	String displayStep = "";
	String displayOctave = "";
	int duration = 0;
	String instrumentID = "";
	int voice = 0;
	String type = "";
	String stem = "";
	String notehead = "";
	
	public Note(String unpitchedOrRest, StringInfo stringInfo , int duration, int voice, String type, int beat, int beattype ){
		if(unpitchedOrRest.equals("unpitched")) {
			this.displayOctave = stringInfo.getDisplayOctave();
			this.displayStep = stringInfo.getDisplayStep();
			this.instrumentID = stringInfo.getInstrumentId();
			this.stem = stringInfo.getStem();
			this.notehead = stringInfo.getNotehead();
			
		}else if( unpitchedOrRest.equals("rest")){
			this.duration = duration;
			this.voice = voice;
			this.type = getType(beat*beattype/duration);
			
		}else {
			//theres an issue, u should not be here
		}
	}
	public String getType(int val) {
		switch(val) {
		case 2:
			return "half";
		case 4:
			return "quarter";
		case 8:
			return "eighth";
		case 16:
			return "16th";
		default:
			return "default";
		}
	}
}


