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
	
	/*This method takes the input file and parses that into a 2d array. 
	 *The result of that 2d array is used to obtain the fret and chord/note
	 *using the translate() method above. */
	public ArrayList<String> translateParsed(String inputfile) {
		
		TextFileReader tabReader = new TextFileReader(inputfile);
		ArrayList<String> note = new ArrayList<>();
		//ArrayList<Character> fretChar = new ArrayList<>();
		ArrayList<Integer> fretNum = new ArrayList<>();
		
		int row = tabReader.printParsed().size();
//		int col = tabReader.printParsed().get(0).size();
		int next = tabReader.printParsed().get(0).get(0).length();
		char fret2 = '\0';
		String tmp = "";
		

			for (int j = 0; j < next; j++) {
				String chord = "";
				for(int i = 0; i < row; i++) {
					
					fret2 = tabReader.printParsed().get(i).get(0).charAt(j);
					int fret = Character.getNumericValue(fret2);
					if (fret2 >= '0' && fret2 <= '9') {
						if (i == 0) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("E4", fret);
							}else {
								note.remove(note.size() - 1);
								chord = translate("E4", fret) + "+" + chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}else if (i == 1) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("B3", fret);
							}else {
								note.remove(note.size() - 1);
								chord = translate("B3", fret) + "+" + chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}else if (i == 2) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("G3", fret);
							}else {
								note.remove(note.size() - 1);
								chord = translate("G3", fret)  + "+" + chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}else if (i == 3) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("D3", fret);
							}else {
								note.remove(note.size() - 1);
								chord = translate("D3", fret)  + "+" +  chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}else if (i == 4) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("A2", fret);
							}else {
								note.remove(note.size() - 1);
								chord =  translate("A2", fret) + "+" +  chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}else if (i == 5) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("E2", fret);
							}else {
								note.remove(note.size() - 1);
								chord = translate("E2", fret) + "+" +  chord;
							}
							note.add(chord);
							fretNum.add(fret);
						}
						
					}else if (fret2 == '|') {
						if (tmp.isEmpty()) {
							tmp = fret2 + "";
							note.add(tmp);
						}
					}
					
					
				}	
			}
		System.out.print("Fret Num: " + fretNum + " Fret size: " + fretNum.size() + "\n");
		return note;
	
	}
}
