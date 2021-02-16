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
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();
	
	//Parsed text 
	private List<List<String>> parsedTab = new ArrayList<List<String>>();
	
	//Read in the file
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.createOriginal();
		//this.createParsed();
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
				}		
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}		
	}
	
//	/**
//	 * Creates a parsed array of the file in parsedTab variable
//	 */
//	private void createParsed(){
//		Scanner sc = null;
//		try {
//			sc = new Scanner(inputFile);
//			List<String> list = new ArrayList<>();
//			String previousLine = "";
//			
//			if (sc.hasNextLine()) {
//				previousLine = sc.nextLine();
//				list.add(previousLine);
//				parsedTab.add(list);
//				list = new ArrayList<>();	
//			}
//			
//			while(sc.hasNextLine()){
//				
//				String line = sc.nextLine();
//
//				if ((previousLine.contains("-") && previousLine.contains("|")) && (line.contains("-") && line.contains("|"))) {
//					list.add(line);
//					parsedTab.add(list);
//					list = new ArrayList<>();						
//				}		
//				previousLine = line;			
//			}		
//		}
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		finally {
//			sc.close();
//		}
//	}
	
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