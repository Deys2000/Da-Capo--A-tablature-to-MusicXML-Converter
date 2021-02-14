package tabToXml;

import java.util.ArrayList;

public class RhythmParser {

	public RhythmParser() {
		
	}
	
	/**
	 * Creates a rhythm array of the file in from parsed array
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> parseToRhythm(ArrayList<String> parsedTab) {
		
		int counter = 0;
		int noteLength = 0; // in 16th notes
		int lines = parsedTab.size();
		int currentLine = 0;
		//String rhythmLine = "";
		boolean hasFret = false;
		boolean isCounting = false;
		
		ArrayList<String> result = new ArrayList<String>();
		// System.out.println("tab length: " + parsedTab.get(0).length());
		
		while (counter < parsedTab.get(0).length() - 1) {
			
			currentLine = 0;
			hasFret = false;
			
			System.out.println("current counter: " + counter);
			
			// Skip "|" and padding "-"
			if (parsedTab.get(0).charAt(counter) == '|') {
				
				System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
				
				result.add("|");
				counter += 1; // skipping both '|' and padding '-'
				
				System.out.println("current counter: " + counter);
			}
			
			// Should be only run before a note is encountered
			else if (isCounting == false) {
				// System.out.println("First Branch");
				// Check when the next note starts // NOTE ONLY WORKS FOR FRETS 0-9
				while(hasFret == false && currentLine < lines) {
					
					System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
					
					if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
						hasFret = true;
						isCounting = true;
						noteLength++;
					}
					
					currentLine++;
				}
			}
			
			// Should be run all other times
			else if (isCounting == true) {
				// System.out.println("Second Branch");
				while(hasFret == false && currentLine < lines) {
					
					System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
					
					if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
						hasFret = true;
						result.add("" + noteLength);
						noteLength = 0;
					}
					
					currentLine++;
				}
				
				noteLength++;
			}
			
			counter++;
		}
		
		result.add("" + noteLength);
		result.add("||");
		//rhythmLine += "[" + noteLength + "]";
		//result.add(rhythmLine);
		
		return result;
	}
	
}
