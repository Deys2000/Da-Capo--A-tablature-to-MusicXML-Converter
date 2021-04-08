package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This Class creates an object that has two String Lists, the original text  and the parsedTab text (it does more now - syed)
 * @author Group15
 *
 */
public class TextFileReader {
	
	private File inputFile; // the file of the 
	int numOfLines; // hold the number of rows in the tablature
	boolean isDrum = false; // responsible for detecting if tab is a drums tab
	private String instrument;
	// String lineStorage; <- not sure why this is here - syed
	boolean isVertical;
	
	ArrayList<String> stringChars; // contains the instruments or starting octaves
	ArrayList<TFRAttribute> attributesPerMeasure;
	
	//Parsed text 
	public ArrayList<String> parsedTab = new ArrayList<String>();
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();
 
	// STRING CONSTRUCTOR
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.coreMethods();
	}
	// FILE CONSTRUCTOR
	public TextFileReader(File inputFile){
		this.inputFile = inputFile;
		this.coreMethods();
	}
	
	//--------------------------------------------------------------------------------------------//
	
	// EVERYTHING IN THIS CLASS RUNS FROM HERE, ITS BASICALLY THE PROXY CONSTRUCTOR
	// TWO MAJOR THINGS HAPPEN HERE
	// 1 - Error Detection
	// 2 - Process Information to pass to Parsers (ie. parsedTab Variable and attributes );
	// the methods should be arranged in an order so that error checking happens before any of the more sensitive methods are run
	private void coreMethods() {
		// Step x: Count the number of lines and figure out what the instrument is
		this.countLinesAndDetectInstrument();
		
		// methods to check for errors
		
		//test
		this.UnderscoreCheck();
		//this.checkAlignedVerticals(); // this fails for repeats with an index out of bounds exception , need to investigate
		//end of test
		
		
		
		// NO ERRORS WERE DETECTED
		
		// Step x: Create the Parsed Tab now that we know there are no fatal errors 
		this.createparsedTab();
		// Step x: Create an attributes list from the parsed tab created above
		this.createAttributesList(this.parsedTab);
		
		System.out.println("Initial Characters" + stringChars.toString()); // delete this later
	}
	
	//-------------------------------------------------------------------------------------------//
	
		
	///////////////////////////////////////////
	/// PROCESSING/EXTRACTION METHODS BELOW ///
	///////////////////////////////////////////
	
	/**
	 * counts the number of lines for Instrument Detection
	 * also responsible for checking if the input provided is bad
	 */
	private void countLinesAndDetectInstrument(){
		// COUNT LINES
		Scanner sc = null;
		try {
			boolean loopcheck = false;
			sc = new Scanner(inputFile);
			String next;
			while(sc.hasNextLine()){			
				//for line counting

				next = sc.nextLine();		
				if( next.contains("|") && next.contains("-")) { // only added if contain these two characters
					numOfLines++; System.out.println("Number of Lines Counted: " + numOfLines);
					if(next.contains("X") || next.contains("x") || next.contains("o") || next.contains("O"))
						isDrum = true;
					loopcheck = true;
				}
				else if(loopcheck == true) {
					// exit, the lines have been counted
					break;
				}
			}					
		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		finally {sc.close();}


		// DETECT INSTRUMENT
		instrument = "Unable to Identify";
		int lines = numOfLines;
		if(lines == 4 && isDrum == false ) {
			instrument = "Bass";
		}
		else if (lines == 6 && isDrum == false) {
			instrument = "Guitar";
		}
		else if( isDrum == true){
			instrument = "Drum";

		}
	} // END OF METHOD

	/**
	 * Creates a parsedTab array of the file in parsedTabTab variable
	 */
	public void createparsedTab(){
		Scanner sc = null;
		try {
			ArrayList<String> extracted  = new ArrayList<>();
			sc = new Scanner(inputFile);

			int index = 0;
			int startFrom;
			boolean key = false;
			String holder;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.contains("-") && line.contains("|")) {			
					if (index <= numOfLines) {						
						if (key == false) {							
							parsedTab.add(line);
						}else if (key == true){
							holder = parsedTab.get(index);
							startFrom = line.indexOf('|') + 1;
							holder = holder + line.substring(startFrom, line.length());
							parsedTab.set(index, holder);
						}
						index++; 
					}

				}
				if (index == numOfLines) {
					key = true;
					index = 0;
				}			
			}			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
		
		// separating the chars and rest of measure
		System.out.println("COMPLETE TAB\n" + parsedTab.toString());
		stringChars = new ArrayList<String>();
		for( int i = 0; i < parsedTab.size(); i++) {
			String currentLine = parsedTab.get(i);
			if( -1 != currentLine.indexOf('|')){
				String firstSection = currentLine.substring(0,currentLine.indexOf('|')); 
				//if( firstSection.contains("\\-") && firstSection.length() != 0 ) {  // perhaps we don't need the conditional
				stringChars.add(firstSection.replaceAll("[^a-zA-Z0-9]", ""));  // only keep alphanumeric values
				parsedTab.set(i,currentLine.substring(currentLine.indexOf('|')));
				//}
			}
			else {
				stringChars.add(null); //Some default value
			}
			System.out.println("Current Line: " + parsedTab.get(i));
		}
	}
	
	public void createAttributesList(ArrayList<String> tab) {
		// TODO
		attributesPerMeasure = new ArrayList<TFRAttribute>();
		// For now, i will assume that all measures are beats = 4, beatype = 4, division = 2 and fifths = 0
		int divisions = 4;
		int beats = 4;
		int beattype = 4;
		int fifths = 0;
		int stafflines = this.numOfLines;
		ArrayList<String> repeats = new ArrayList<>();
		String sign = this.getSign();
		String line = this.getLine();

		if(numOfLines > 1) { // only works if you have atleast 2 lines in your tablature
			int repeat = 0;
			String line1 = parsedTab.get(0);
			String line2 = parsedTab.get(1);
			
			int accomodate = 0; // this variable accomodates for the decreasing size of the 
			for(int i = 1; i < line1.length(); i++) { // START from 1 so that we dont check the first barline and a -1 index out of bounds error
				if(line2.charAt(i) == '|' && line1.charAt(i) == '|') {
					if( i-1 >= 0 && Character.isDigit( line1.charAt(i-1))) { // character behind is a number
						repeats.add(Character.toString(line1.charAt(i-1)));
						for(int j = 0; j < parsedTab.size(); j++) // remove the vertical column with the number so it does not get interpreted as a note later
							parsedTab.set(j, parsedTab.get(j).substring(0,i-accomodate-1)+parsedTab.get(j).substring(i-accomodate));
						accomodate++;
					}
					else if( i-1 >= 0 && line1.charAt(i-1) != '|') // character behind should not be another barline
						repeats.add(null);
				}
			} // end of loop			
		}// end of conditional
		
		for(int i = 0; i < repeats.size(); i++) { // start at one to ignore the first bar that repeats has as that is not a measure			
			attributesPerMeasure.add(new TFRAttribute(i+1,divisions,fifths, beats, beattype, sign, line, repeats.get(i)));			
		}
		System.out.println("ATTRIBUTES COLLECTED\n" + attributesPerMeasure);
		
		
	}// end of create attributes list method
 
	public int getStaffLines(){
		return numOfLines;  
	}
			
	public String getSign(){
		String sign = "TAB";
		if(instrument == "Guitar") {
			//G for treble
			// sign = "G";
			sign = "TAB";
		}
		else if (instrument == "Bass") {
			//F for bass
			//sign = "F";
			sign = "TAB";
		}
		else {
			//percussion for drums
			sign = "percussion";
		}
		System.out.println("SIGN FOR TAB: " + sign);
		return sign;
	}
	
	public String getLine(){
		java.lang.String line = "";
		if(instrument == "Guitar") {
			line = "2"; //treble lies on 3rd string for guitar
		}
		else if (instrument == "Bass") {
			line = "4"; //bass clef lies on 4th string for bass
		}
		else {
			line = "2";			//2 for percussion tabs
		}
		System.out.println("LINE PLACEMENT FOR SIGN: " + line);
		return line;
	}
			

	
	/**
	 * Prints the original text file, used only for the GUI when you open a file
	 * @return
	 */
	public String printOrginal() {
		StringBuilder sb = new StringBuilder();	
		try {
			Scanner sc = new Scanner(inputFile);
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine()+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();	
	}
	

	////////////////////////////////////
	/// ERROR CHECKING METHODS BELOW ///
	////////////////////////////////////
	
	public boolean checkAlignedVerticals() {
		//checks that the vertical lines are aligned
		//not fully working yet
		boolean isVertical = true;
		int loopCount = 1;
		int [] indexHolder = {};
		int [] indexHolder2 = {};
		int verticalsInLine = 0;

		Scanner quickScan = null;
		try {
			quickScan = new Scanner(inputFile);
			while((loopCount <= (numOfLines/2)) && (isVertical == true)) {
				System.out.println("LOOP START");
				
				//making array out of the given line
				String next = quickScan.nextLine();
				char [] charCheck = next.toCharArray();
				System.out.println("charCheck #"+loopCount+": ");
				System.out.println(charCheck);
				
				//first loop determines locations of verticals
				if(loopCount == 1) {
					
					for(int i = 0;i<(charCheck.length);i++) {
						if (charCheck[i] == '|') {
							verticalsInLine++;
						}
					}
					
					//make an array of the same size of the number of verticals in the first line
					indexHolder = new int [verticalsInLine];
					
					//set array to have the location of each vertical as an element
					int forCount = 0;
					for(int i = 0;i<(charCheck.length);i++) {
						if(charCheck[i] == '|') {
							indexHolder[forCount] = i;
							forCount++;
						}	
					}
				}
				// end of first loop only stuff
				
				//now creating temporary arrays out of the new lines
				else if(loopCount > 1) {
					int forCount2 = 0;
					indexHolder2 = new int[indexHolder.length];
					
					for(int i = 0;i<(charCheck.length);i++) {
						if(charCheck[i] == '|') {
							indexHolder2[forCount2] = i;
							forCount2++;
						}
					}
					
					//comparing the position of vertical bars in the arrays
					for(int i = 0;i<indexHolder.length;i++) {
						if(indexHolder[i] == indexHolder2[i]) {
							isVertical = true;
						}
						else {
							isVertical = false;
							break;
						}
					}

				}

				System.out.println("LOOP END");
				System.out.println("/////////////////////////////////////////////////////////////////////");
				loopCount++;
			}
	
		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		finally {quickScan.close();}
		System.out.println("isVertical has the FINAL value: "+isVertical);
		return isVertical;
	}
	
	public void tripleVerticalCheck() {
		//checks that there aren't more than 3 staff lines in a row
		
	}
	
	public void SpaceCheck() {
		//checks that there's no spaces between tabs
		
	}
	
	public void firstVertical() {
		//checks that the tab starts with a vertical line
		
	}
	
	public void ExtraDashCheck() {
		//checks that there's extra dashes in certain rows of a tab
		
	}
	
	public boolean UnderscoreCheck() {
		//checks that there's no underscores instead of dashes
		Scanner underscoreScan = null;
		boolean underscore = false;
		try {
			
			underscoreScan = new Scanner(inputFile);
			while (underscoreScan.hasNextLine()) {
				String line = underscoreScan.nextLine();
				if (line.contains("_")) {
					underscore = true;
				}
						
			}			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			underscoreScan.close();
		}
		System.out.println("underscore check yields: "+underscore);
		return underscore;
		
	}
	
	public void subInstrumentCheck() {
		//checks that the initials for the drums or strings are correct (theres no base strings if the instrument is a guitar...etc)
		
	}
	
	public void SymbolCheck() {
		//checks that the symbols for a given instrument are in english
		
	}
	
	//////////////////////////////////////
	/// GETTER AND SETTER METHODS BELOW///
	//////////////////////////////////////
	
	/**
	 * just to get the path of the file
	 * @return
	 */
	public File getFile() {
		return this.inputFile;
	}
	/**
	 * This is for printing purposes, it makes everything into one string
	 * @return
	 */
	public String getParsedString() {
		StringBuilder sb = new StringBuilder();
		for(String s : parsedTab)
			sb.append(s.toString()+"\n");
		return sb.toString();
	}
	
	/**
	 * This is the MAIN output of this file that goes into the three parsers
	 * @return
	 */
	public ArrayList<String> getParsed(){
		System.out.println("Get parsed method\n"  + this.getParsedString());
		return parsedTab;
	}
	//
	public String getDetectedInstrument() {
		return this.instrument;
	}
	public ArrayList<String> getStringChars(){
		return this.stringChars;
	}
	public ArrayList<TFRAttribute> getAttributesPerMeasure(){
		return this.attributesPerMeasure;
	}
	
	// NEW METHOD
	public void setRangeOfAttributes( int from, int to, int beat, int beattype, String sign, String line) {
		
		// only make changes if valid input is given, valid meaning "from" is before or equal to "to"
		for(int i = from; i <= to; i++) { // inclusive from and two
			// we will use -1 throughout the loop since the array is on a index base of 0 and not 1
			// so measure 1 is actually at position 0
			this.attributesPerMeasure.get(i).setBeats(beat);
			this.attributesPerMeasure.get(i).setBeattype(beattype);
			this.attributesPerMeasure.get(i).setSign(sign);
			this.attributesPerMeasure.get(i).setLine(line);			
		}		
	}
	
	
	
}
//_______________________________________END OF TEXT FILE READER CLASS______________________________________________//
//------------------------------------------------------------------------------------------------------------------//


// CLASS MADE TO PASS AN OBJECT OF ATTRIBUTES OF EACH MEASURE OVER TO THE PARSERS 
class TFRAttribute{
	
	// initializations with some default values
	int measure;
	int divisions = 4;
	int fifths = 0;
	int beats = 4;
	int beattype = 4;
	String sign = "TAB";
	String line = "2";
	String staffLines = "6";
	ArrayList<String> tuningSteps;
	ArrayList<String> tuningOctaves;
	
	String repeat;
	
	
	// Constructor
	public TFRAttribute(int m, int d, int f, int b, int bt, String s, String l, String r) {
		this.measure = m;
		this.divisions = d;
		this.fifths = f;
		this.beats = b;
		this.beattype = bt;
		this.sign = s;
		this.line = l;
		this.repeat = r;
	}	
	
	public String toString() {
		return ("Measure: "		+ measure + 
				" Divisions: "	+ divisions +
				" Fifths: "		+ fifths +
				" Beats: "		+ beats +
				" BeatType: "	+ beattype +
				" Sign: "		+ sign +
				" Line: "		+ line +
				" Repeat: "		+ repeat+ "\n");
	}
	
	// GETTERS
	public int getDivisions() {return divisions;}
	public int getFifths() {return fifths;}
	public int getBeats() {return beats;}
	public int getBeattype() {return beattype;}
	public String getSign() {return sign;}
	public String getLine() {return line;}
	public String getStaffLines() { return staffLines; };
	public ArrayList<String> getTuningOctaves() { return tuningOctaves; };
	public ArrayList<String> getTuningSteps() { return tuningSteps; };

	public String getRepeat() { return repeat; };
		
	// SETTERS
	
	//*add some setters maybe
	public void setDivisions(int divisions) {this.divisions= divisions;}
	public void setFifths(int fifths) {this.fifths = fifths;}
	public void setBeats(int beats) {this.beats =  beats;}
	public void setBeattype(int beattype) {this.beattype = beattype;}
	public void setSign(String sign) {this.sign = sign;}
	public void setLine(String sign) {this.line = line;}

	
}