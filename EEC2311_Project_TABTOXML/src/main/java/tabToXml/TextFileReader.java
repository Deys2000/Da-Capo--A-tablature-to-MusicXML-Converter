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
		this.createParsed();		
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
	
	/**
	 * Creates a parsed array of the file in parsedTab variable
	 */
	private void createParsed(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			
			
			List<String> listE2 = new ArrayList<>();
			List<String> listB2 =new ArrayList<>();
			List<String> listG3 = new ArrayList<>();
			List<String> listD3 = new ArrayList<>();
			List<String> listA3 = new ArrayList<>();
			List<String> listD4 = new ArrayList<>();
			
			String lineE2 = "E|";
			String lineB2 = "B|";
			String lineG3 = "G|";
			String lineD3 = "D|";
			String lineA3 = "A|";
			String lineD4 = "D|";
			
			String previousLine = "";
			while(sc.hasNextLine()){
					
					String line = sc.nextLine();
					if (line.contains("-") && line.contains("|") && line.matches(".*[a-zA-Z].*")) {
						//list.add(line);
						
						if (line.contains("E") && !(previousLine.contains("A"))) {
							lineE2 = lineE2 + line.substring(2, line.length());
						}
						else if (!(previousLine.isEmpty()) && previousLine.contains("A")) {
							lineD4 = lineD4 + line.substring(2, line.length());
							
						}
						else if (line.contains("B")) {
							lineB2 = lineB2 + line.substring(2, line.length());
						}
						else if (line.contains("G")) {
							lineG3 = lineG3 + line.substring(2, line.length());
						}
						else if (line.contains("D")) {
							lineD3 = lineD3 + line.substring(2, line.length());
						}
						else if (line.contains("A")) {
							lineA3 = lineA3 + line.substring(2, line.length());
						}
					} 			
	
				previousLine = line;			
			}
			listE2.add(lineE2);
			listD4.add(lineD4);
			listB2.add(lineB2);
			listG3.add(lineG3);
			listD3.add(lineD3);
			listA3.add(lineA3);
			
			parsedTab.add(listE2);
			parsedTab.add(listB2);
			parsedTab.add(listG3);
			parsedTab.add(listD3);
			parsedTab.add(listA3);
			parsedTab.add(listD4);
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
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