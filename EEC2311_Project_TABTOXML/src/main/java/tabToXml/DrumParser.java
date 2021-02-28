package tabToXml;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrumParser {
	
	//contains the information on each string in the tab
	// contains: name, id, step, octave, stem, notehead
	ArrayList<StringInfo> tabStrings = new ArrayList<>();
	ArrayList<Measure> measures = new ArrayList<>();
	
	public DrumParser(ArrayList<String> exampleInput) throws Exception { //assume the class receives a parsed and clean tab
		
		//first clean the input so it contains only info i need in one long line of tablature
		//createParsedDrum(inputFile) perhaps
		//assume the following is what is received or created
		String[] exampleInput1 = 	{	" C:|X---------------|X---------------|X---------------|X---------------|",
										"HH:|----o---o---o---|----o---o---o---|----o---o---o---|----o---o-------|",
										" S:|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|----o--o-o------|",
		 								" BD:|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-o-----|"};
		String[] exampleInput2 =
		{"C|x---------------|--------x-------|",
		"HH|--x-x-x-x-x-x-x-|----------------|",
		"S|----o-------o---|oooo------------|",
		"MT|----------------|----oo----------|",
		"LT|----------------|------oo--------|",
		"BD|o-------o-------|o-------o-------|"};
		
		
		
		System.out.println("\nSIMPLIFIED TAB BELOW \n");
		for(int i = 0; i < exampleInput.size(); i++)
			System.out.println(exampleInput.get(i));
		
		//second check for bad tablature (to do later) examples: there is no tab, the tab is not spaced properly, etc, all lines should have equal length
		
		
		// gather the information on each string and complete the tabStrings Arraylist
		int numOfRows = exampleInput.size();
		for( int i = 0; i < numOfRows; i++) {
			String instrumentSymbol = exampleInput.get(i).substring(0,exampleInput.get(i).indexOf('|'));
			instrumentSymbol = instrumentSymbol.replaceAll("[^a-zA-Z0-9]", "");  // only keep alphanumeric values
			tabStrings.add(new StringInfo(instrumentSymbol.strip()));
			//remove the strings in front
			exampleInput.add(i, exampleInput.get(i).substring(exampleInput.get(i).indexOf('|')));
			exampleInput.remove(i+1);
		}
		//TESTER for method above
		System.out.println("\nINSTRUMENTS USED BELOW \n");
		for(int i = 0; i < tabStrings.size(); i++)
			System.out.println(tabStrings.get(i).getInstrumentName());
		
		//making a string array
		String[] tab = new String[exampleInput.get(0).length()];
		for(int i = 0; i < exampleInput.get(0).length(); i++) {
			String col = "";
			for(int j = 0; j < exampleInput.size(); j++ )
				col = col + exampleInput.get(j).charAt(i);
			tab[i] = col;
		}
		//TESTER for method above 
		System.out.println("\nTRANSPOSED ARRAY BELOW \n");
		for(int i = 0; i < tab.length; i++)
			System.out.println(tab[i]);
		
		//The drum tab has been transformed into an array like the following
		// Basically, its been transposed so each column in a string
//		"||||"
//		"X--o"
//		"----"
//		"----"
//		"---o"
//		"-oo-"
//		"----"
//		----
//		--o-
//		-o-o
//		--o-
//		---o
//		---o
//		-oo-
//		----
//		---o
//		----
//		||||
		
		

		//everything above this point works correctly, but is not yet prepared for bad input
		
		// MY LOGIC OF PARSING BELOW
		// loop should cycle through vertically gathering information
		//information that we need to gather
		// create note objects that are in an array of measure which is in a larger array of measures to form the whole piece. 
		// note unpitched or rest
		// if unpitched, then step,octave, instrument id and stem are determined by string - easy
		// if unpitched, then we must gather duration, voice and type ( type is basically beat*beat-type/duration)
		// if rest then we need duration, voice and type (type is the same as above)

		//go through every column (its a row in this transposed array btw)
		//create new measures where necessary
		Measure current = new Measure(1); //creates a useless measure for the first time, it does not get used anyway
		int measureNumber = 1;
		int beat = 4; int beattype = 4; int voice = 1; // some default declarations for now
		int p = 0;
		for(int i = 0; i < tab.length;  i++) {
			// if you are at the end of a measure, switch your measure object to a new one
			if(tab[i].contains("|")){
					current = new Measure(measureNumber);
					measures.add(current);
					measureNumber++;
					p = 0; //reset the beat multiple value counter
			}
			else {
				//p++;				
				// if you are at a beat multiple spot
				// and if no note exists here, make a rest note
				if( p%beat == 0 && tab[i].equals("----")) {
						//look ahead to get duration
						int duration = 1;
						for(int j = 1;j <= beat; j++) {
							if(tab[i+j].equals("----"))
								duration++;
							else
								break;
						}
						// create rest note, parameters are stringInfo  duration voice beat beattype 
						current.addNote(new Note(duration, beat, beattype));
				}
				// you are at a non-beat-multiple spot
				// note exists here, make an unpitched note
				else {
					//look ahead to get duration
					int duration = 1;
					for(int j = 1;j <= beat; j++)
						if(tab[i+j].equals("----") && (p+j)%beat != 0) // the columns ahead must be empty and not a beat multiple column
							duration++;
						else
							break;
					// loop to make notes for every element in the column
					boolean chord = false;
					for(int j = 0; j < tab[i].length(); j++ ) {
						//if a note exists at that spot make a note
						if(tab[i].charAt(j) != '-') {
							// create unpitched note, parameters are stringInfo ,duration, voice, beat,beattype and whether its a chord or not
							String notehead = ""+tab[i].charAt(j);
							current.addNote(new Note(tabStrings.get(j),duration,notehead, beat, beattype, chord));
							chord = true;
						}
						
					}
				}
				
			p++;
			}
			
		}
		// By the end of this, you should have all the note objects and measure objects
		
		//TESTER METHOD FOR THE PROCESS ABOVE		
		System.out.println("\nPARSED INFORMATION BELOW \n");
		for(int i = 0; i < measures.size()-1; i++ ) {
			System.out.println(measures.get(i));
		}
		//there seems to be incorrect information with regards to the duration (and type since it is dependent)
		// extra measure object is created but need not be used
		// Things are working for the most part :)
	
	}//END OF CONSTRUCTOR
	
	public ArrayList<StringInfo> getTabStrings() {
		return tabStrings;
	}

	public ArrayList<Measure> getMeasures() {
		return measures;
	}
	
}//END OF DRUMPARSER CLASS




class StringInfo{
		//gets the information of the instrument given its symbol on the tab line
		//name, id, step, octave, stem
		String name, id, step, octave, stem, notehead; // i have realized the notehad property is incorrect
		
		public StringInfo(String symbol) throws Exception {			
		
		switch(symbol) {
			
			case "BD":
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
			case "LT":
				name = "Low Tom";
				id = "P1-146";
				step = "D";
				octave = "5";
				stem = "up";		
				notehead = "x";
				break;
//			case "OHH":
//				name = "Open Hi-Hat";
//				id = "P1-I47";
//				step = "";
//				octave = "";
//				stem = "";
//				notehead = "";
//				break;
			case "MT":
				name = "Low-Mid Tom";
				id = "P1-I48";
				step = "E";
				octave = "5";
				stem = "up";			
				notehead = "x";
				break;
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
				throw new Exception("Unrecognizable Instrument");
				//throw some incorrect stuff exception later
//				name = "Closed Hi-Hat"; // High hat in doc
//				id = "P1-I43";
//				step = "G";
//				octave = "5";
//				stem = "up";		
//				notehead = "x";
				// the values above are just default to prevent crashing
		}
			
		}
		
		public String getInstrumentName() {return this.name;}
		public String getInstrumentId() {return id;}
		public String getDisplayStep() {return step;}
		public String getDisplayOctave() {return octave;}
		public String getStem() {return stem;}
		public String getNotehead() {return notehead;}
		
		

}

//|||||||||||||||||||||| MEASURE CLASS |||||||||||||||||||||||||||
class Measure{
	int measureNumber;
	ArrayList<Note> notes = new ArrayList<Note>();
	public Measure(int measureNumber) {
		this.measureNumber = measureNumber;
	}
	public void addNote(Note note) {
		this.notes.add(note);
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Measure: " + measureNumber + "\n" );
		for(Note n: notes)
			sb.append(n + "\n");
		return sb.toString();
	}
}

//||||||||||||||||||||||||| NOTE CLASS ||||||||||||||||||||||||||||
class Note {
	String unpitchedOrRest = null;
	String displayStep = null;
	String displayOctave = null;
	int duration = 0;
	String instrumentID = null;
	int voice = 0;
	String type = null;
	String stem = null;
	String notehead = null;
	boolean chord = false;
	
	//constructor for rest note
	public Note(int duration, int beat, int beattype) {
		this.unpitchedOrRest = "rest";
		this.duration = duration;
		this.voice = 2;  // hardcoded to 2 for now, but it should be determined
		this.type = getType(beat*beattype/duration);
	}
	
	//constructor for unpitched note
	public Note(StringInfo stringInfo , int duration, String notehead, int beat, int beattype, boolean chord ){
		this.unpitchedOrRest = "unpitched";
		this.displayOctave = stringInfo.getDisplayOctave();
		this.displayStep = stringInfo.getDisplayStep();
		this.instrumentID = stringInfo.getInstrumentId();
		this.duration = duration;
		if(stringInfo.getStem().equals("up")) { this.voice = 1;}
		else { this.voice = 2; }
		this.type = getType(beat*beattype/duration);
		this.stem = stringInfo.getStem();
		this.notehead = notehead; //stringInfo.getNotehead();
		this.chord = chord;
	}	
	private String getType(int val) {
		if( val <= 1)
			return "whole";
		else if(val <= 2 )
			return "half";
		else if( val <= 4)
			return "quarter";
		else if ( val <= 8)
			return "eighth";
		else if (val <= 16)
			return "16th";
		else if ( val <= 32)
			return "32nd";
		else if (val <= 64)
			return "64th";
		else 
			return "128th";
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Unpitched or Rest: " + this.unpitchedOrRest);
		sb.append(", Display-Step: " + this.displayStep);
		sb.append(", Display-Octave: " + this.displayOctave);
		sb.append(", Duration: " + this.duration);
		sb.append(", Instrument ID: " + this.instrumentID);
		sb.append(", Voice: " + this.voice);
		sb.append(", Type: " + this.type);
		sb.append(", Stem: " + this.stem);
		sb.append(", Notehead: " + this.notehead);
		sb.append(", Chord: " + this.chord);
		return sb.toString();
	}
	
	public String getUnpitchedOrRest() {		
		return unpitchedOrRest;
	}

	public String getDisplayStep() {
		return displayStep;
	}

	public String getDisplayOctave() {
		return displayOctave;
	}

	public int getDuration() {
		return duration;
	}

	public String getInstrumentID() {
		return instrumentID;
	}

	public int getVoice() {
		return voice;
	}

	public String getType() {
		return type;
	}

	public String getStem() {
		return stem;
	}

	public String getNotehead() {
		return notehead;
	}

	public boolean getChord() {
		return chord;
	}
	
	
}



