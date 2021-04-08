package tabToXml;

import java.util.ArrayList;

//NEEDS TO BE MODULAR SO THAT EVERYTHING IS NOT HAPPENING IN THE CONSTRUCTOR
//ALSO NEEDS A COUPLE OF TAGS THAT ARE MISSING
//NEED TO COMPLETE THE INSTRUMENT LIST AS WELL
//LOOK INTO MAKING THE NOTE AND MEASURE CLASS PRIVATE

public class DrumParser2 {
	ArrayList<DrumMeasure> measures;
	ArrayList<DrumStringInfo> tabStrings;
	ArrayList<String> instruments;
	TextFileReader tfr;
	// we will pass 3 pieces of information when creating an object of this class
	// they will all be contained within one object hopefully a MEASURE object of sorts 
	// 1 - a list of measures
	// 2 - a list of corresponding attributes for each measure
	// 3 - additional meta-information
	public DrumParser2(TextFileReader passedtfr ) throws Exception {
		tfr = passedtfr;
		// WE WILL ASSUME THE FOLLOWING IS THE FORMAT OF OUR INFORMATION (precondition is that all must be same length)
		// THIS CODE IS DESIGNED FOR NO PADDING IN EACH MEASURE //
		// 1 List of Measures
//		ArrayList<ArrayList<String>> exampleInputX = new ArrayList<ArrayList<String>>();
//		ArrayList<String> m1 = new ArrayList<>();
//		m1.add("|x---------------|");
//		m1.add("|--x-x-x-x-x-x-x-|");
//		m1.add("|----o-------o---|");
//		m1.add("|----------------|");
//		m1.add("|----------------|");
//		m1.add("|o-------o-------|");
//		ArrayList<String> m2 = new ArrayList<>();
//		m2.add("|--------x-------|");
//		m2.add("|----------------|");
//		m2.add("|oooo------------|");
//		m2.add("|----oo----------|");	
//		m2.add("|------oo--------|");	
//		m2.add("|o-------o-------|");	
//		exampleInput.add(m1);
//		exampleInput.add(m2);
		// Method that breaks down the parsed tab into format required for drum parsing
		ArrayList<String> parsedTab = tfr.getParsed();
		ArrayList<ArrayList<String>> exampleInput = this.getMeasuresParsed(parsedTab);
		
		
		// 2 - List of corresponding attributes
		ArrayList<DrumAttribute> drumAttributes = new ArrayList<>();
		// divisions, fifths, beats, beat-type, sign, line
		DrumAttribute da = new DrumAttribute(4,0,4,4,"percussion", 2);
		drumAttributes.add(da);
		drumAttributes.add(da);
		// 3 - additional meta info
		// The strings
//		instruments = new ArrayList<String>();
//		instruments.add("C");
//		instruments.add("S");
//		instruments.add("HH");
//		instruments.add("MT");
//		instruments.add("LT");
//		instruments.add("BD");
		this.instruments = tfr.getStringChars();
		System.out.println("TFR Determined > "+this.instruments);
		
		for(int i = 0; i < instruments.size(); i++) {
			// some overriding of instruments
			// if it contains a "C", then its a crash cymbal
			if(instruments.get(i).contains("C") || instruments.get(i).contains("c")) instruments.set(i,"C");
			// if it contains a "S", then its a snare drum
			if(instruments.get(i).contains("S") || instruments.get(i).contains("s")) instruments.set(i,"S");
			// if its a High Tom, make it a low tom since we don't support it yet
			if(instruments.get(i).equals("HT")) instruments.set(i,"LT");
			// if it has a B, make it a Bass Drum
			if(instruments.get(i).contains("B")) instruments.set(i,"BD");
		}
		
		
		
		/////////////////////////////
		// CONSTRUCTOR BEGINS HERE //
		/////////////////////////////
		
		// BASIC SEQUENCE OF THINGS TO DO
		
		// PREPROCESSING
		// decide which instruments are voice 1 and voice 2
		// make into 2 vertical static arrays called voice 1 and voice 2
		// EXTRACTING INFO
		// go through voice 1, and create the note objects
		// backup
		// go through voice 2, and create the note objects
		// repeat the process above for each measure
		
		// initialize lists
		this.tabStrings = new ArrayList<>();
		this.measures = new ArrayList<>();
		
		// setup the information for each string
		this.setDrumStringInfo(this.instruments);
				
		ArrayList<String> currentMeasure; // holds a single measure from the list of list of strings
		DrumAttribute currentAttribute; // hold a single attributes object
		String instrument; // holds the current string character

		DrumMeasure currentMeasureObject;
		//go through every measure
		for(int m = 0; m < exampleInput.size(); m++) {
			currentMeasure = exampleInput.get(m); // getting the measure
			currentAttribute = drumAttributes.get(0); // getting the attributes for the measure above CHANGE TO BE DYNAMIC
			int voice;
			
			//decide which instruments are voice 1 and voice 2
			ArrayList<String> voice1Lines = new ArrayList<>(); 
			ArrayList<String> voice2Lines = new ArrayList<>(); 
			ArrayList<DrumStringInfo> voice1StringsInfo = new ArrayList<>();
			ArrayList<DrumStringInfo> voice2StringsInfo = new ArrayList<>();
			// go through each measure preparing information
			for( int i = 0; i < currentMeasure.size(); i++) {
				instrument = instruments.get(i); // get instrument character
				voice = this.returnVoice(instrument); // figure out which voice it belongs to
				
				if(voice == 1) {
					voice1Lines.add(currentMeasure.get(i)); // if it belongs to voice 1, then put in voice one list
					voice1StringsInfo.add(this.tabStrings.get(i));
				}
				else {
					voice2Lines.add(currentMeasure.get(i)); // otherwise put into voice two list
					voice2StringsInfo.add(this.tabStrings.get(i));
				}
				System.out.println("Line "+ i +">" + currentMeasure.get(i));

			}			
			//make temporary vertical array for voice 1 and voice 2 both
			String[] verticalVoice1 = this.transpose(voice1Lines);
			String[] verticalVoice2 = this.transpose(voice2Lines);
			
			int measureNumber = (m+1);
			currentMeasureObject = new DrumMeasure(measureNumber); 
			// go through voice 1 and make notes for this measure
			this.setNotes(verticalVoice1, currentAttribute, voice1StringsInfo, currentMeasureObject);							
			//backup
			this.setBackup(currentAttribute.getBeats()*currentAttribute.getBeattype(), currentMeasureObject);
			// go through voice 2
			this.setNotes(verticalVoice2, currentAttribute, voice2StringsInfo, currentMeasureObject);		
			// append the measure into the list of global measures
			measures.add(currentMeasureObject);
			// make beams for the created notes
			this.setBeams(currentAttribute, currentMeasureObject);		
		}			
					
		
		
		//TESTER METHOD FOR THE PROCESS ABOVE		
		System.out.println("\nPARSED INFORMATION BELOW \n");
		for(int i = 0; i < measures.size(); i++ ) {
			System.out.println("Measure Printer in DP2:\n "+ measures.get(i)+"\n");
		}
		//there seems to be incorrect information with regards to the duration (and type since it is dependent)
		// extra measure object is created but need not be used
		// Things are working for the most part :)
		
		System.out.println(this.getDrumTabStrings().toString());
		
	} // END OF CONSTRUCTOR
	
	public ArrayList<DrumStringInfo> getDrumTabStrings() {
		return this.tabStrings;
	}
	
	private void setBeams(DrumAttribute da, DrumMeasure dm) {
		// removing unnecessary information
		ArrayList<DrumNote> voice1Notes = new ArrayList<DrumNote>();
		ArrayList<DrumNote> voice2Notes = new ArrayList<DrumNote>();
		for( DrumNote n : dm.getNotes()) {
			if( n.getChord() == false && n.getType() != null ) {
				if( n.getVoice().equals("1"))
					voice1Notes.add(n);
				else
					voice2Notes.add(n);
			}
		}
		// THOUGHT PROCESS 
		// go through the list of notes in the measure
			// first go through voice one notes
				//join the notes with beams based on where they are
				// use the duration property to get your location to terminate beams at beats
			// then go through voice two notes
				// do the same as mentioned above for voice one notes
		DrumNote current = null;
		DrumNote previous = null;
		int durationCounter = 0;
		int beattype = da.getBeattype();
		// set up 16th barlines
		for(int i = 0; i < voice1Notes.size(); i++) {
			current = voice1Notes.get(i);
			// if this is the first note, then start the barline if the next note has the same duration
			 // NOT SURE IF THIS ID CALCULATING THE RIGHT THING
			if( previous != null && (durationCounter % beattype) !=  0) {// || durationCounter > beattype) {
				if(current.getType().equals(previous.getType())) { // the note before is the same type
					if( previous.beam1 == null && previous.beam2 == null) {
						previous.setBeam1("begin");	previous.setBeam2("begin");
						current.setBeam1("end");current.setBeam2("end");
					}
					else{
						previous.setBeam1("continue");	previous.setBeam2("continue");
						current.setBeam1("end"); current.setBeam2("end");					
					}					
				}		
			}
			durationCounter += current.getDuration();
			previous = current;	
		}	
		
	}// END OF SET BEAMS METHOD
	
	private void setBackup(int duration, DrumMeasure currentMeasureObject) {
		// note that i have to create a fake note to trick the xmlGenerator (in actuality, its a backup object)
		currentMeasureObject.addNote(new DrumNote(duration)); 
	}
	
	//creates all the notes for a voice
	private void setNotes (String[] tab, DrumAttribute da, ArrayList<DrumStringInfo> dsi, DrumMeasure current) {
		int beat = da.getBeats(); int beattype = da.getBeattype(); // some default declarations for now
		int position = -1; // start at -1 to account for the first barline column
			
		for(int i = 0; i < tab.length;  i++) {
			String pruneBars = tab[i].replaceAll("\\|", "");
			String pruneDash  = tab[i].replaceAll("[^xXoOf]", "");
			System.out.println("PRUNED:" + pruneBars + " ~ " + pruneDash);
			if( pruneBars.length()==0) // skip the whole process because we are at the the vertical barlines				
				; // DO NOTHING
			
			// else if you are at a beat multiple spot and if no note exists here, make a rest note
			else if( position%beat == 0 && pruneDash.length()==0) {
				//look ahead to get duration
				int duration = 1;
				for(int j = 1;j <= beat; j++) {
					String pruneDash2 = tab[i+j].replaceAll("\\-", "");
					if(pruneDash2.length() == 0)		
						duration++;
					else								
						break;
				}
				// create rest note, parameters are stringInfo duration voice beat beattype 
				if(duration >= 4)current.addNote(new DrumNote(dsi.get(0),duration, beat, beattype));
			}
			// you are at a non-beat-multiple spot
			// note exists here, make an unpitched note
			else {
				//look ahead to get duration
				int duration = 1;
				for(int j = 1;j <= beat; j++) {
					String pruneDash2  = tab[i+j].replaceAll("\\-", "");
					if( (position+j)%beat != 0 && pruneDash2.length()==0) // the columns ahead must be empty and not a beat multiple column
						duration++;
					else
						break;
				}
				// loop to make notes for every element in the column
				boolean chord = false;
				for(int j = 0; j < tab[i].length(); j++ ) {
					//if a note exists at that spot make a note
					if(tab[i].charAt(j) != '-') {
						// create unpitched note, parameters are stringInfo ,duration, voice, beat,beattype and whether its a chord or not
						String notehead = ""+tab[i].charAt(j);
						// if this notehead is an f, then create a grace note

						if( notehead.equals("f")) {
							current.addNote( new DrumNote(dsi.get(j), notehead, beat, beattype, true)); // make a flam note
							notehead = dsi.get(j).getNotehead(); // replace the f with the desired note head for the instrument on the string
						}
						current.addNote(new DrumNote(dsi.get(j),duration,notehead, beat, beattype, chord));
						chord = true;
					}

				}
			}
			// move up counter in reading the tab lines
			position++;
		}
	}
	
	private void setDrumStringInfo( ArrayList<String> instruments) throws Exception {
		for(String s: instruments)
			tabStrings.add(new DrumStringInfo(s));
	}
	
	/**
	 * Transposes a given measure for pre-processing in the constructor
	 * Not meant for use elsewhere
	 * @param currentMeasure
	 * @return
	 */
	private String[] transpose (ArrayList<String> currentMeasure) {
		String[] vertical = new String[0];
		if(currentMeasure.size() < 1) return vertical; // error handling if the currentmeasure is empty
		
		vertical = new String[currentMeasure.get(0).length()];
		for(int i = 0; i < currentMeasure.get(0).length(); i++) {
			String col = "";
			for(int j = 0; j < currentMeasure.size(); j++ )
				col = col + currentMeasure.get(j).charAt(i);
			vertical[i] = col;
		}
		// if you would like to view the output yourself, uncomment below
		System.out.println("\nTRANSPOSED ARRAY BELOW \n");
		for(int i = 0; i < vertical.length; i++)
			System.out.println(vertical[i]);
		return vertical;
	}
	
	/**
	 * This method will return whether the passed instrument belongs to voice 1 or voice 2
	 * @param instrumentInitials
	 * @return
	 */
	private int returnVoice( String instrumentInitials) {
		switch(instrumentInitials) {
		case "C": return 1;
		case "HH": return 1;
		case "S": return 1;
		case "BD": return 2;
		case "LT": return 1;
		case "MT": return 1;
		default: return 1;
		}
	}

	public ArrayList<DrumMeasure> getDrumMeasures() {
		return this.measures;
	}
	
	public TextFileReader getTFR() {
		return tfr;
	}
	
	public ArrayList<ArrayList<String>> getMeasuresParsed(ArrayList<String> parsedTab){

		
		ArrayList<ArrayList<String>> tab = new ArrayList<ArrayList<String>>();
		ArrayList<String[]> splitTab = new ArrayList<String[]>();
		for(String s: parsedTab)
			splitTab.add(s.split("\\|"));
		// tab is split into lines and measures
		ArrayList<String> currentMeasure;
		for(int i = 0; i < splitTab.get(0).length; i++) {
			currentMeasure = new ArrayList<String>();
			for(int j = 0; j < splitTab.size(); j++) {					
				if( !splitTab.get(j)[i].equals(""))
					currentMeasure.add("|"+splitTab.get(j)[i]+"|");
			}
			if( currentMeasure.size() != 0) // dont add if empty string
				tab.add(currentMeasure);
			System.out.println("Get Measures Parsed: " + currentMeasure);
		}
		return tab;
	}

} // END OF DRUMPARSER2 CLASS

class DrumAttribute{
	
	public int getDivisions() {return divisions;}
	public int getFifths() {return fifths;}
	public int getBeats() {return beats;}
	public int getBeattype() {return beattype;}
	public String getSign() {return sign;}
	public int getLine() {return line;}

	// perhaps set default values
	int divisions = 4;
	int fifths = 0;
	int beats = 4;
	int beattype = 4;
	String sign = "percussion";
	int line = 2;
	
	public DrumAttribute( int d, int f, int b, int bt, String s, int l) {
		this.divisions = d;
		this.fifths = f;
		this.beats = b;
		this.beattype = bt;
		this.sign = s;
		this.line = l;
	}
}

class DrumStringInfo{
	//gets the information of the instrument given its symbol on the tab line
	//name, id, step, octave, stem
	String name, id, step, octave, stem, notehead, voice; // i have realized the notehad property is incorrect? perhaps not dunno
	
	public DrumStringInfo(String symbol) throws Exception {				
		
	switch(symbol) {
		
		case "BD":
			name = "Bass Drum 1";
			id = "P1-I36";
			step = "F";
			octave = "4";
			stem = "down";	
			notehead = "o";
			voice = "2";
			break;
			
		case "C":
			name = "Crash Cymbal";
			id = "P1-I50";
			step = "A";
			octave = "5";
			stem = "up";			
			notehead = "x";
			voice = "1";
			break;
			
		case "S":
			name = "Snare";
			id = "P1-I39";
			step = "C";
			octave = "5";
			stem = "up";		
			notehead = "o";
			voice = "1";
			break;
			
		case "HH":
			name = "Closed Hi-Hat"; // High hat in doc
			id = "P1-I43";
			step = "G";
			octave = "5";
			stem = "up";		
			notehead = "x";
			voice = "1";
			break;
		
		// Ask Billy for advice when uncommenting the instruments below
			
//		case "B2":
//			name = "Bass Drum 2";
//			id = "P1-I37";
//			step = "";
//			octave = "";
//			stem = "";		
//			notehead = "";
//			break;
//		case "SS":
//			name = "Side Stick";
//			id = "P1-I38";
//			step = "";
//			octave = "";
//			stem = "";		
//			notehead = "";
//			break;
//		case "LFT":
//			name = "Low Floor Tom";
//			id = "P1-I42";
//			step = "";
//			octave = "";
//			stem = "";		
//			notehead = "";
//			break;
//		case "HFT":
//			name = "High Floor Tom";
//			id = "P1-I44";
//			step = "";
//			octave = "";
//			stem = "";		
//			notehead = "";
//			break;
//		case "PHH":
//			name = "Pedal Hi-Hat";
//			id = "P1-I45";
//			step = "";
//			octave = "";
//			stem = "";		
//			notehead = "";
//			break;
		case "LT":
			name = "Low Tom";
			id = "P1-146";
			step = "D";
			octave = "5";
			stem = "up";		
			notehead = "x";
			voice = "1";
			break;
//		case "OHH":
//			name = "Open Hi-Hat";
//			id = "P1-I47";
//			step = "";
//			octave = "";
//			stem = "";
//			notehead = "";
//			break;
		case "MT":
			name = "Low-Mid Tom";
			id = "P1-I48";
			step = "E";
			octave = "5";
			stem = "up";			
			notehead = "x";
			voice = "1";
			break;
//		case "HMT":
//			name = "Hi-Mid Tom";
//			id = "P1-I49";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;
//		case "HT":
//			name = "High Tom";
//			id = "P1-I51";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;
//		case "RC":
//			name = "Ride Cymbal";
//			id = "P1-I52";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;
//		case "CC":
//			name = "Chinese Cymbal";
//			id = "P1-I53";
//			step = "";
//			octave = "";
//			stem = "";
//			notehead = "";
//			break;
//		case "RB":
//			name = "Ride Bell";
//			id = "P1-I54";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;			
//		case "T":
//			name = "Tambourine";
//			id = "P1-I54";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;			
//		case "SC":
//			name = "Splash Cymbal";
//			id = "P1-I56";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;		
//		case "CB":
//			name = "Cowbell";
//			id = "P1-I57";
//			step = "";
//			octave = "";
//			stem = "";
//			notehead = "";
//			break;			
//		case "C2":
//			name = "Crash Cymbal 2";
//			id = "P1-I58";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;
//		case "R2":
//			name = "Ride Cymbal 2";
//			id = "P1-I60";
//			step = "";
//			octave = "";
//			stem = "";
//			notehead = "";
//			break;
//		case "OHC":
//			name = "Open Hi Conga";
//			id = "P1-I64";
//			step = "";
//			octave = "";
//			stem = "";			
//			notehead = "";
//			break;
//		case "LC":
//			name = "Low Conga";
//			id = "P1-I64";
//			step = "";
//			octave = "";
//			stem = "";
//			notehead = "";
//			break;
					
		default:
			//Choice 1: throw an exception
			//throw new Exception("Unrecognizable Instrument");
			
			// Choice 2: Default to random instrument
			name = "Bass Drum 1";
			id = "P1-I36";
			step = "F";
			octave = "4";
			stem = "down";	
			notehead = "o";
			voice = "2";
	}
		
	}
	
	public String getInstrumentName() {return this.name;}
	public String getInstrumentId() {return id;}
	public String getDisplayStep() {return step;}
	public String getDisplayOctave() {return octave;}
	public String getStem() {return stem;}
	public String getNotehead() {return notehead;}		
	public String getVoice() {return voice;}

}

//|||||||||||||||||||||| MEASURE CLASS |||||||||||||||||||||||||||
class DrumMeasure{
	int measureNumber;
	ArrayList<DrumNote> notes = new ArrayList<DrumNote>();
	public DrumMeasure(int measureNumber) {
		this.measureNumber = measureNumber;
	}
	public void addNote(DrumNote note) {
		this.notes.add(note);
	}
	public ArrayList<DrumNote> getNotes(){
		return notes;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Measure: " + measureNumber + "\n" );
		for(DrumNote n: notes)
			sb.append(n + "\n");
		return sb.toString();
	}
}


class DrumNote {
	String unpitchedOrRestOrBackup = null; // or grace as well
	String displayStep = null;
	String displayOctave = null;
	int duration = 0;
	String instrumentID = null;
	String voice = "1";
	String type = null;
	String stem = null;
	String notehead = null;
	boolean chord = false;
	boolean grace = false;;
	// attributes for beams
	String beam1 = null;
	String beam2 = null;
	String beam3 = null;
		
	// constructor for the backup note ( backup is not really a note, but we treat it as one for simplicity )
	public DrumNote(int duration) {
		this.unpitchedOrRestOrBackup = "backup";
		this.duration = duration;
	}
	
	//constructor for rest note
	public DrumNote(DrumStringInfo stringInfo, int duration, int beat, int beattype) {
		this.unpitchedOrRestOrBackup = "rest";
		this.duration = duration;
		this.voice = stringInfo.getVoice();  // hardcoded to 2 for now, but it should be determined
		this.type = getType(beat*beattype/duration);
	}
	
	//constructor for unpitched note
	public DrumNote(DrumStringInfo stringInfo , int duration, String notehead, int beat, int beattype, boolean chord ){
		this.unpitchedOrRestOrBackup = "unpitched";
		this.displayOctave = stringInfo.getDisplayOctave();
		this.displayStep = stringInfo.getDisplayStep();
		this.instrumentID = stringInfo.getInstrumentId();
		this.duration = duration;
		this.voice = stringInfo.getVoice();
		this.type = getType(beat*beattype/duration);
		this.stem = stringInfo.getStem();
		this.notehead = notehead; //stringInfo.getNotehead();
		this.chord = chord;
	}	
	
	// constructor for a flam, aka a grace note
	public DrumNote(DrumStringInfo stringInfo, String notehead, int beat, int beattype, boolean grace ) {
		this.unpitchedOrRestOrBackup = "grace";
		this.displayOctave = stringInfo.getDisplayOctave();
		this.displayStep = stringInfo.getDisplayStep();
		this.instrumentID = stringInfo.getInstrumentId();
		this.voice = stringInfo.getVoice();
		this.type = getType(beat*beattype/duration);
		this.stem = stringInfo.getStem();
		this.notehead = stringInfo.getNotehead();
		this.grace = grace;
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
		sb.append("Unpitched or Rest: " + this.unpitchedOrRestOrBackup);
		sb.append(", Display-Step: " + this.displayStep);
		sb.append(", Display-Octave: " + this.displayOctave);
		sb.append(", Duration: " + this.duration);
		sb.append(", Instrument ID: " + this.instrumentID);
		sb.append(", Voice: " + this.voice);
		sb.append(", Type: " + this.type);
		sb.append(", Stem: " + this.stem);
		sb.append(", Notehead: " + this.notehead);
		sb.append(", Chord: " + this.chord);
		sb.append(", Grace: " + this.grace);
		sb.append(", Beam1: " + this.beam1);
		sb.append(", Beam2: " + this.beam2);
		sb.append(", Beam3: " + this.beam3);
		return sb.toString();
	}
	
	public String getUnpitchedOrRest() {return unpitchedOrRestOrBackup;}
	public String getDisplayStep() {return displayStep;}
	public String getDisplayOctave() {return displayOctave;}
	public int getDuration() {return duration;}
	public String getInstrumentID() {return instrumentID;}
	public String getVoice() {	return voice;}
	public String getType() {return type;}
	public String getStem() {return stem;}
	public String getNotehead() {return notehead;}
	public boolean getChord() {	return chord;}
	public boolean getGrace() {	return grace;}
	public String getBeam1() {	return beam1;}
	public String getBeam2() {	return beam2;}
	public String getBeam3() {	return beam3;}
	
	public void setBeam1(String s) {this.beam1 = s;}
	public void setBeam2(String s) {this.beam2 = s;}
	public void setBeam3(String s) {this.beam3 = s;}
	
}