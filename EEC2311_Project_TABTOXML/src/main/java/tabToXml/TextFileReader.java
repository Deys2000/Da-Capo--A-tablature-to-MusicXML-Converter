package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This Class creates an object that has two String Lists, the original text  and the parsed text
 * @author Group15
 *
 */
public class TextFileReader {
	
	private File inputFile;
	int numOfLines = 0;
	boolean isDrum;
	
	//Parsed text 
	public ArrayList<String> parsedTab = new ArrayList<String>();
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();
	
	//Read in the file
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.countLines();
		this.createParsed();
		this.detectInstrument();
	}
	
	/**
	 * counts the number of lines for Instrument Detection
	 * also responsible for checking if the input provided is bad
	 */
	private void countLines(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){	
				String next = sc.nextLine();
				if (next.contains("-") && next.contains("|"))
					numOfLines ++;						
				else if( 0 < numOfLines )  // modified this line to cater to prevent crashing with spacing at the start
					break;
			}					
		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		finally {sc.close();}
	}
	
	/**
	 * Creates a parsed array of the file in parsedTab variable
	 */
	private void createParsed(){
		Scanner sc = null;
		try {
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
	/**
	 * determines the instrument based on the number of lines
	 * still in progress
	 */
	public String detectInstrument(){	
		String instrument = "Unable to Identify";
		if(numOfLines == 4 && isDrum == false ) {
			instrument = "bass";
		}
		else if (numOfLines == 6 && isDrum == false) {
			instrument = "guitar";
		}
		else{
			instrument = "drums";
		}
		//for now we just return guitar
		return instrument;
	}
	
	public int numberOfLines() {
		return numOfLines;
	}
	
	public ArrayList<String> getParsed(){
		return parsedTab;
	}
	
	/**
	 * Prints the original text file
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
}