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
	int count;
	boolean isDrum;
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();
	
	//Parsed text 
	private List<List<String>> parsedTab = new ArrayList<List<String>>();
	
	//Read in the file
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.createOriginal();
		this.createParsed();
		this.detectInstrument();
	}
	
	/**
	 * copies the file exactly the way it is into a dynamic string array
	 */
	private void createOriginal(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			
			while(sc.hasNextLine()){	
				String line = sc.nextLine();
				originalTab.add(line);
				//counts number of lines for instrumental detection
				count++;
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
	 * Creates a parsed array of the file in parsedTab variable
	 */
	private void createParsed(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			List<String> list = new ArrayList<>();
			
			String previousLine = "";
			while(sc.hasNextLine()){
				
				String line = sc.nextLine();
				if (!(previousLine.isEmpty()) && (line.contains("-") && line.contains("|")))
					list.add(line);
					
				if ((previousLine.contains("-") && previousLine.contains("|")) && !(line.contains("-") && line.contains("|"))) {					
					parsedTab.add(list);
					list = new ArrayList<>();						
				}
				
				previousLine = line;
				//differentiates between drum or other tab
				if(line.contains("o")) {
					isDrum = true;
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
		String instrument ="";
		
		if(count == 4 && isDrum == false ) {
			instrument = "bass";
		}
		else if (count == 6 && isDrum == false) {
			instrument = "guitar";
		}
		else {
			instrument = "drums";
		}
		
		System.out.println(instrument);
//		System.out.println(count);
		
		return instrument;
	}
	/**
	 * Prints the parsed text file
	 * @return
	 */
	public List<List<String>> printParsed() {		
		return parsedTab;
	}
	
	/**
	 * Prints the original text file
	 * @return
	 */
	public String printOrginal() {
		StringBuilder sb = new StringBuilder();	
		
		for(String s : originalTab) {
			sb.append(s + "\n");
		}
		return sb.toString();	
	}
}