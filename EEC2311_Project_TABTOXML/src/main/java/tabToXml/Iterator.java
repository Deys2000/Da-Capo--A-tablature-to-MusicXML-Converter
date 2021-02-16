package tabToXml;

import java.util.ArrayList;

public class Iterator {
    
    // CAN ALL BE DONE IN CONSTRUCTOR (from preprocessor)
    public String instrument = "guitar";
    public String[] tuning = {"E4", "B3", "G3", "D3", "A2", "E2"};
    
    public ArrayList<String> rhythmArr = new ArrayList<String>();
    public ArrayList<String> notesArr = new ArrayList<String>();
    
    public Iterator() {
        
    }
    
    
    /**
     * Creates a note and rhythm array from parsed array
     * @param
     * @return
     * @throws Exception
     */
    public void parseToNoteRhythm(ArrayList<String> parsedTab) {
        
        int counter = 0;
        int noteLength = 0; // in 16th notes
        int lines = parsedTab.size();
        int currentLine = 0;
        //String rhythmLine = "";
        boolean hasFret = false;
        boolean isCounting = false;
        
        // System.out.println("tab length: " + parsedTab.get(0).length());
        
        while (counter < parsedTab.get(0).length() - 1) {
            
            currentLine = 0;
            hasFret = false;
            
            // System.out.println("current counter: " + counter);
            
            // Skip "|" and padding "-"
            if (parsedTab.get(0).charAt(counter) == '|') {
                
                // Assuming note lengths end at barlines
                if(noteLength != 0) {
                    rhythmArr.add("" + noteLength);
                    noteLength = 0;
                    isCounting = false;
                }
                
                // System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
                
                // System.out.println("adding | to result");
                rhythmArr.add("|");
                notesArr.add("|");
                counter += 1; // skipping both '|' and padding '-'
                
                // System.out.println("current counter: " + counter);
            }
            
            // Should be only run before a note is encountered
            else if (isCounting == false) {
                // System.out.println("First Branch");
                // Check when the next note starts // NOTE ONLY WORKS FOR FRETS 0-9
                while(hasFret == false && currentLine < lines) {
                    
                    //System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
                    
                    char curFret = parsedTab.get(currentLine).charAt(counter);
                    
                    if(Character.isDigit(curFret)) {
                        
                        // System.out.println(tuning[currentLine] + " + " + curFret + " = " + translate(tuning[currentLine], Character.getNumericValue(curFret)));
                        notesArr.add(translate(tuning[currentLine], Character.getNumericValue(curFret)));
                        
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
                    
                    //System.out.println("char: " + parsedTab.get(currentLine).charAt(counter));
                    
                    char curFret = parsedTab.get(currentLine).charAt(counter);
                    
                    if(Character.isDigit(curFret)) {
                        
                        // System.out.println(tuning[currentLine] + " + " + curFret + " = " + translate(tuning[currentLine], Character.getNumericValue(curFret)));
                        notesArr.add(translate(tuning[currentLine], Character.getNumericValue(curFret)));
                        
                        hasFret = true;
                        //System.out.println("adding " + noteLength + " to result");
                        rhythmArr.add("" + noteLength);
                        noteLength = 0;
                    }
                    
                    currentLine++;
                }
                
                noteLength++;
            }
            
            counter++;
        }
        
        // Last note length and ending barline is added
        //System.out.println("adding " + noteLength + " to result");
        rhythmArr.add("" + noteLength);
        //System.out.println("adding || to result");
        rhythmArr.add("||");
        notesArr.add("||");
        //rhythmLine += "[" + noteLength + "]";
        //result.add(rhythmLine);
    }
    
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

}
