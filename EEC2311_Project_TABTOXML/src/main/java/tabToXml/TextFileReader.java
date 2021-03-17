package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * This Class creates an object that has two String Lists, the original text  and the parsedTab text
 * @author Group15
 *
 */
public class TextFileReader {
	
	private File inputFile;
	public static int numOfLines;
	boolean isDrum = false;
	static String instrument;
	static String lineStorage;
	boolean isVertical;
	
	//Parsed text 
	public ArrayList<String> parsedTab = new ArrayList<String>();
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();

	//Read in the file
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.countLines();
		this.createparsedTab();
		this.detectInstrument();
	}
	public TextFileReader(File inputFile){
		this.inputFile = inputFile;
		this.countLines();
		this.createparsedTab();
		this.detectInstrument();
	}
	
	/**
	 * counts the number of lines for Instrument Detection
	 * also responsible for checking if the input provided is bad
	 */
	private void countLines(){
		Scanner sc = null;
		try {
			int counter = 0;
			int loopcheck = -1;
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
				if(next.contains("X") || next.contains("x") || next.contains("o") || next.contains("O"))  {

					isDrum = true;
				}
				//end of drum tab check
				
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
	}
	
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
//=======
//			}
//			for(int i = 0; i < extracted.size(); i++) 
//			{
//				// check if tab has base note here
//				// if not set base note to default
//				// default tuning EADGBE
//				if(i >= 6) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(parsedTab.get(i % 6));
//				sb.append(extracted.get(i).substring(2));
//				parsedTab.set(i%6, sb.toString());
//				}
//				else 
//				{
//					parsedTab.add(extracted.get(i));
//				}
//			}
//>>>>>>> refs/heads/Ayub_Features
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
	
	//checks number of dashes in first line
//	public void checkDashes() {
//		Scanner sc = null;
//		try {
//			
//			sc = new Scanner(inputFile);
//			lineStorage = sc.nextLine();
//			
//			
//			char [] info  = lineStorage.toCharArray();
//			for(int i = 0; i < info.length; i++) {
//				System.out.println(info[i]);
//			}		
//		}
//		
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		finally {
//			sc.close();
//		}
//		System.out.println(lineStorage);
//	}
	
	

	/**
	 * determines the instrument based on the number of lines
	 * still in progress
	 */
	public String detectInstrument(){	
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
		return instrument;
	}
	
	public int numberOfLines() {
		return numOfLines;
	}
	

	public static java.lang.String staffLines(){
		Integer count = numOfLines/2;
		java.lang.String lines = count.toString();
		return lines;
		}
		
	
	public static java.lang.String sign(){
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
	
	public static java.lang.String line(){
		java.lang.String line = "";
		if(instrument == "Guitar") {
			//treble lies on 3rd string for guitar
			line = "2";
		}
		else if (instrument == "Bass") {
			//bass clef lies on 4th string for bass
			line = "4";
		}
		else {
			//2 for percussion tabs
			line = "2";
		}
		System.out.println(line);
		return line;
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
		return parsedTab;
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
	
	/**
	 * just to get the file
	 * @return
	 */
	public File getFile() {
		return this.inputFile;
	}
	
}