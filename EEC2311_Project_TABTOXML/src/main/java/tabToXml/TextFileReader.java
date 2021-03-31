package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * This Class creates an object that has two String Lists, the original text  and the parsedTab text (it does more now - syed)
 * @author Group15
 *
 */
public class TextFileReader {
	
	private File inputFile;
	public static int numOfLines;
	boolean isDrum = false;
	private static String instrument;
	static String lineStorage;
	
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
		
		// Step x: Create the Parsed Tab now that we know there are no fatal errors 
		this.createparsedTab();
		// Step x: Create an attributes list from the parsed tab created above
		this.createAttributesList();
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
			int counter = 0; int loopcheck = -1;
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){			
				//for line counting
				String next = sc.nextLine();				
				//getting array for dash counting for time signature for guitar/bass (skips first line for padding)
				//not done
				char [] info  = next.toCharArray();
				for(int i = 2; i < info.length; i++) {
					counter ++;
					if(loopcheck < 1 && info[i] == '|') {
						loopcheck++;
						int trueCount = counter - 1;
						System.out.println(trueCount);
					}					
				}
				//end of dash counting

				//drum check
				if(next.contains("X") || next.contains("x") || next.contains("o") || next.contains("O"))
					isDrum = true;

				if (next.contains("-") && next.contains("|")) {
					numOfLines++;
					System.out.println(numOfLines);
				}
				else if( 0 < numOfLines )  // modified this line to cater to prevent crashing with spacing at the start
					break;
			}					
		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		finally {sc.close();}
		//test
		this.UnderscoreCheck();
		this.checkAlignedVerticals();
		//end of test

		// DETECT INSTRUMENT
		instrument = "Unable to Identify";
		int lines = numOfLines/2;
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
							index++;
						}else if (key == true){
							holder = parsedTab.get(index);
							startFrom = line.indexOf('|') + 1;
							holder = holder + line.substring(startFrom, line.length());
							parsedTab.set(index, holder);
							index++;
						}
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
	}
	
	public void createAttributesList() {
		// TODO 
		
	}

	
	
	// somehow try to make 
	public static String getStaffLines(){
		Integer count = numOfLines/2;
		java.lang.String lines = count.toString();
		return lines;
	}
			
	public static String getSign(){
		String sign = "tab";
		if(instrument == "Guitar") {
			//G for treble
			sign = "G";
		}
		else if (instrument == "Bass") {
			//F for bass
			sign = "F";
		}
		else {
			//percussion for drums
			sign = "percussion";
		}
		System.out.println(sign);
		return sign;
	}
	
	public static String getLine(){
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
		System.out.println(line);
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
			// TODO Auto-generated catch block
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
//							
//							System.out.println("initial array being compared to: ");
//							System.out.println(Arrays.toString(indexHolder));
//							
//							System.out.println("array for line "+loopCount+": ");
//							System.out.println(Arrays.toString(indexHolder2));
//							
//							System.out.println("isVertical has the value: "+isVertical);
						}
						else {
							isVertical = false;
//							
//							System.out.println("initial array being compared to: ");
//							System.out.println(Arrays.toString(indexHolder));
//							
//							System.out.println("array for line "+loopCount+": ");
//							System.out.println(Arrays.toString(indexHolder2));
//							
//							System.out.println("isVertical has the value: "+isVertical);
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
		System.out.println(this.getParsedString());
		return parsedTab;
	}
	//
	public String getDetectedInstrument() {
		return this.instrument;
	}
} // END OF TEXT FILE READER CLASS

// CLASS MADE TO PASS ATTRIBUTES OF THE MEASURE OVER TO THE PARSERS
class TFRAttribute{
	// initializations with some default values
	int divisions = 4;
	int fifths = 0;
	int beats = 4;
	int beattype = 4;
	String sign = "TAB";
	int line = 2;
	String staffLines = "6";
	ArrayList<String> tuningSteps;
	ArrayList<String> tuningOctaves;
	
	// Constructor
	public TFRAttribute( int d, int f, int b, int bt, String s, int l) {
		this.divisions = d;
		this.fifths = f;
		this.beats = b;
		this.beattype = bt;
		this.sign = s;
		this.line = l;
	}	
//	// Guitar Attribute Constructor
//	public TFRAttribute( int d, int f, int b, int bt, String s, int l, String sl, ArrayList<String> ts, ArrayList<String> to) {
//		this.divisions = d;
//		this.fifths = f;
//		this.beats = b;
//		this.beattype = bt;
//		this.sign = s;
//		this.line = l;
//		this.staffLines = sl;
//		this.tuningSteps = ts;
//		this.tuningOctaves = to;
//	}	
	
	// GETTERS
	public int getDivisions() {return divisions;}
	public int getFifths() {return fifths;}
	public int getBeats() {return beats;}
	public int getBeattype() {return beattype;}
	public String getSign() {return sign;}
	public int getLine() {return line;}
	public String getStaffLines() { return staffLines; };
	public ArrayList<String> getTuningOctaves() { return tuningOctaves; };
	public ArrayList<String> getTuningSteps() { return tuningSteps; };
		
	// SETTERS
	
	//*add some setters maybe
	
}