package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This Class creates an object that has two String Lists, the original text  and the parsedTab text
 * @author Group15
 *
 */
public class TextFileReader {
	
	private File inputFile;
	int count;
	boolean isDrum;
	
	//Original text 
	private ArrayList<String> originalTab = new ArrayList<String>();
	
	//parsedTab text 
	private List<String> parsedTab = new ArrayList<>();
	
	//Read in the file
	public TextFileReader(String inputFile){
		this.inputFile = new File(inputFile);
		this.createOriginal();
		this.createparsedTab();
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
	 * Creates a parsedTab array of the file in parsedTabTab variable
	 */
	public void createparsedTab(){
		Scanner sc = null;
		try {
			ArrayList<String> extracted  = new ArrayList<>();
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){
				
				String line = sc.nextLine();
				if (line.contains("-") && line.contains("|")) {// default tuning EADGBE
						extracted.add(line);
				}
			}
			for(int i = 0; i < extracted.size(); i++) 
			{
				// check if tab has base note here
				// if not set base note to default
				// default tuning EADGBE
				if(i >= 6) {
				StringBuilder sb = new StringBuilder();
				sb.append(parsedTab.get(i % 6));
				sb.append(extracted.get(i).substring(2));
				parsedTab.set(i%6, sb.toString());
				}
				else 
				{
					parsedTab.add(extracted.get(i));
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
		
		if(count == 4 && isDrum == false ) {
			instrument = "bass";
		}
		else if (count == 6 && isDrum == false) {
			instrument = "guitar";
		}
		else{
			instrument = "drums";
		}
		
		//for now we just return guitar
		return "Guitar";
	}
	
//	/**
//	 * Creates a parsedTab array of the file in parsedTabTab variable
//	 */
//	private void createparsedTab(){
//		Scanner sc = null;
//		try {
//			sc = new Scanner(inputFile);
//			List<String> list = new ArrayList<>();
//			String previousLine = "";
//			
//			if (sc.hasNextLine()) {
//				previousLine = sc.nextLine();
//				list.add(previousLine);
//				parsedTabTab.add(list);
//				list = new ArrayList<>();	
//			}
//			
//			while(sc.hasNextLine()){
//				
//				String line = sc.nextLine();
//
//				if ((previousLine.contains("-") && previousLine.contains("|")) && (line.contains("-") && line.contains("|"))) {
//					list.add(line);
//					parsedTabTab.add(list);
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
	
	public String getParsedString() {
		StringBuilder sb = new StringBuilder();
		for(String s : parsedTab)
			sb.append(s.toString()+"\n");
		return sb.toString();
	}
	
	/**
	 * Prints the parsedTab text file
	 * @return
	 */
	public List<String> printParsed() {	
		return parsedTab;
	}
	
	public ArrayList<String> printParsed2() {	
		ArrayList<String> s = new ArrayList<String>();
		for(String pt : parsedTab)
			s.add(pt.toString());
		return s;
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