package tabToXml;

import java.util.ArrayList;
import java.util.List;

public class TabInterface {

	/**
	 * Converts a Note
	 * C > C#/Db > D > D#/Eb > E > F > F#/Gb > G > G#/Ab > A > A#/Bb > B > Loops back to C
	 * @param Note
	 * @return
	 */
	public String translate(String string, int fret) {
		String[] table = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};			
		//find location in table
		int location = 0;
		for(int i = 0; i < table.length; i++) {
			if(string.substring(0,1).equals(table[i])) {
				location = i;
				break;
			}
		}
		
		int startingOctave = Integer.parseInt(string.substring(1));
		int octave = startingOctave;
		while(fret > 0) {
			location++;
			fret--;
			if(location == table.length) {
				location = 0;
				octave++;
			}
		}		
		String newNote = table[location]+octave;
		return newNote;
	}
	
	public ArrayList<String> translateParsed(String inputfile) {
		
		TextFileReader tabReader = new TextFileReader(inputfile);
		ArrayList<String> note = new ArrayList<>();
		ArrayList<Character> fretChar = new ArrayList<>();
		ArrayList<Integer> fretNum = new ArrayList<>();
		
		int row = tabReader.printParsed().size();
		int col = tabReader.printParsed().get(0).size();
		int next = tabReader.printParsed().get(0).get(0).length();
		//String line = "";
		char fret2 = '\0';
		

			for (int j = 0; j < next; j++) {
				for(int i = 0; i < row; i++) {
					
					fret2 = tabReader.printParsed().get(i).get(0).charAt(j);
					int fret = Character.getNumericValue(fret2);
					if (fret2 >= '0' && fret2 <= '9') {
						if (i == 0) {
							note.add(translate("E4", fret));
							fretNum.add(fret);
						}else if (i == 1) {
							note.add(translate("B3", fret));
							fretNum.add(fret);
						}else if (i == 2) {
							note.add(translate("G3", fret));
							fretNum.add(fret);
						}else if (i == 3) {
							note.add(translate("D3", fret));
							fretNum.add(fret);
						}else if (i == 4) {
							note.add(translate("A2", fret));
							fretNum.add(fret);
						}else if (i == 5) {
							note.add(translate("E2", fret));
							fretNum.add(fret);
						}
						
					}
					
					
				}	
			}
//		System.out.print("Fret Num: \n" + fretNum + "Fret size: \n" + fretNum.size() + "\n");
		return note;
	
	}
}
