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
		String rhythmLine = "";
		boolean hasFret = false;
		boolean isCounting = false;
		
		ArrayList<String> result = new ArrayList<String>();
		
		// TODO
		// 1. only care about hasFret flag and isCounting flag
		// 2. every tick of counter, check for hasFret flag
		// 3. if hasFret flag is true and isCounting is false -> set isCounting to true, noteLength++
		// 4. else if hasFret is false and isCounting is true -> noteLength++
		// 5. else if hasFret is true and isConuting is true -> append current noteLength to rhythmLine, set new noteLength to be 1
		
		while (counter < parsedTab.get(0).length() - 1) {
			
			currentLine = 0;
			hasFret = false;
			
			// Skip "|" and padding "-"
			if (parsedTab.get(0).charAt(counter) == '|') {
				counter += 2; // skipping both '|' and padding '-'
			}
			
			if (isCounting == false) {
				// Check when the next note starts // NOTE ONLY WORKS FOR FRETS 0-9
				while(hasFret == false && currentLine < lines) {
					if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
						hasFret = true;
						isCounting = true;
						noteLength++;
					}
					
					currentLine++;
				}
			}
			else if (isCounting == true) {
				while(hasFret == false && currentLine < lines) {
					if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
						hasFret = true;
						rhythmLine += "[" + noteLength + "]";
						noteLength = 1;
					}
					
					currentLine++;
				}
			}
			
			counter++;
		}
		
		result.add(rhythmLine);
		
		return result;
	}
	
}
