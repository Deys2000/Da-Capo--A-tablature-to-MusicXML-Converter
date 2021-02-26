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
	int count = 0;
	boolean isDrum;
	int nextstep = 0;
	String[] lines;
	public ArrayList<String> lines2 = new ArrayList<String>();
	
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
//				String line = sc.nextLine();
//				originalTab.add(line);
//				//counts number of lines for instrumental detection
//				count++;
					String next = sc.nextLine();
					if (next.contains("-") && next.contains("|")) {
						count ++;
						
					}else if (!(next.contains("-") && next.contains("|"))) {
						break;
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
	 * Creates a parsed array of the file in parsedTab variable
	 */
	private void createParsed(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			
			int index = 0;
			int startFrom;
			Boolean key = false;
			String holder;
			while (sc.hasNextLine()) {
				
				String line = sc.nextLine();
			
				if (line.contains("-") && line.contains("|")) {
			
					if (index <= count) {
						
						if (key == false) {
							
							lines2.add(line);
							index++;
						}else if (key == true){
							holder = lines2.get(index);
							startFrom = line.indexOf('|') + 1;
							holder = holder + line.substring(startFrom, line.length());
							lines2.set(index, holder);
							index++;
						}
						
					}
					
				}
				
				if (index == count) {
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
	
	public String getParsedString() {
		StringBuilder sb = new StringBuilder();
		for(List<String> s : parsedTab)
			sb.append(s.toString()+"\n");
		return sb.toString();
	}
	
	/**
	 * Prints the parsed text file
	 * @return
	 */
	public List<List<String>> printParsed() {	
		return parsedTab;
	}
	
	public ArrayList<String> printParsed2() {	
		ArrayList<String> s = new ArrayList<String>();
		for(List<String> pt : parsedTab)
			s.add(pt.toString());
		return s;
	}
	
	public int numberOfLines() {
		return count;
	}
	
	public ArrayList<String> printer(){
		return lines2;
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