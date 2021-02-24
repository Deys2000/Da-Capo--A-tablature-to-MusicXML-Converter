package tabToXml;

import java.util.ArrayList;
import java.util.List;

public class TabParser {
	
	public ArrayList<String> note = new ArrayList<>();
	public ArrayList<String> fretString = new ArrayList<>();
	public ArrayList<String> fretNum = new ArrayList<>();
	

	/**
	 * Converts a Note
	 * C > C#/Db > D > D#/Eb > E > F > F#/Gb > G > G#/Ab > A > A#/Bb > B > Loops back to C
	 * @param Note
	 * @return
	 * @throws Exception 
	 */
	public static String translate(String string, int fret) throws Exception{
		if(fret > 12)
			throw new Exception("The fret must be between 1 and 12 (inclusive)");

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
	public void translateParsed(String inputfile) throws Exception {
		
		TextFileReader tabReader = new TextFileReader(inputfile);
		
		int row = tabReader.printParsed().size();
//		int col = tabReader.printParsed().get(0).size();
		int next = tabReader.printParsed().get(0).get(0).length();
		char fret2 = '\0';
		String tmp = "";
		int cal = 0;

			for (int j = 0; j < next; j++) {
				String chord = "";
				String fretNumVar = "";
				String fretStringVar = "";
				
				
				for(int i = 0; i < row; i++) {
					
					fret2 = tabReader.printParsed().get(i).get(0).charAt(j);
					int fret = Character.getNumericValue(fret2);
					if (fret2 >= '0' && fret2 <= '9') {
						if (i == 0) {
							tmp = "";
							if (chord.isEmpty() && fretNumVar.isEmpty() && fretStringVar.isEmpty()) {
								chord = translate("E4", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
								
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("E4", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
								
//								chord = translate("E4", fret) + "+" + chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}else if (i == 1) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("B3", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
//								chord = translate("B3", fret) + "+" + chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("B3", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}else if (i == 2) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("G3", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
//								chord = translate("G3", fret)  + "+" + chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("G3", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
								
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}else if (i == 3) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("D3", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
//								chord = translate("D3", fret)  + "+" +  chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("D3", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}else if (i == 4) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("A2", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
//								chord =  translate("A2", fret) + "+" +  chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("A2", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
								
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}else if (i == 5) {
							tmp = "";
							if (chord.isEmpty()) {
								chord = translate("E2", fret);
								fretNumVar = fret + "";
								cal = i + 1;
								fretStringVar = cal + "";
							}else {
								note.remove(note.size() - 1);
								fretNum.remove(fretNum.size() - 1);
								fretString.remove(fretString.size()-1);
//								chord = translate("E2", fret) + "+" +  chord;
//								fretNumVar = fretNumVar + "+" + fret;
//								fretStringVar = fretStringVar + "+" + cal;
								
								note.add(chord);
								fretNum.add(fretNumVar);
								fretString.add(fretStringVar);
								
								chord = "+" + translate("E2", fret);
								fretNumVar = fret + "";
								fretStringVar = cal + "";
								
							
							}
							note.add(chord);
							fretNum.add(fretNumVar);
							fretString.add(fretStringVar);
						}
						
					}else if (fret2 == '|') {
						if (tmp.isEmpty()) {
							tmp = fret2 + "";
							note.add(tmp);
							fretNum.add(tmp);
							fretString.add(tmp);
						}
					}
					
					
				}	
			}
		
			note.remove(note.size() - 1);
			fretNum.remove(fretNum.size() - 1);
			fretString.remove(fretString.size()-1);
			note.add("||");
			fretNum.add("||");
			fretString.add("||");
//		System.out.print("Fret String: " + fretString + " Fret size: " + fretString.size() + "\n");
//		System.out.print("Fret Num: " + fretNum + " Fret size: " + fretNum.size() + "\n");
//		System.out.print("\nNote size: " + note.size());
		
	
	}
	
	public ArrayList<String> notes(){
		return note;
	}
	
	public ArrayList<String> fretStrings(){
		return fretString;
	}
	
	public ArrayList<String> fretNums(){
		return fretNum;
	}
	
}
