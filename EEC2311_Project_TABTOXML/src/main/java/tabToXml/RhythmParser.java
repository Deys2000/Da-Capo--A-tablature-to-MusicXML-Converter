package tabToXml;

import java.util.ArrayList;

public class RhythmParser {
    
    public ArrayList<String> durationArr = new ArrayList<String>();
    public ArrayList<String> typeArr = new ArrayList<String>();
    
    private int divisions = 4;
    private int padding = 1;
     
    public RhythmParser(int divisions) { // divisions should be 4 by default
        durationArr = new ArrayList<String>();
        typeArr = new ArrayList<String>();
        this.divisions = divisions;
    }
    
    /**
     * Generates duration and type arrays from parsed array
     * @param parsedTab - a formatted tab (arrayList of Strings)
     */
    public void parseToRhythm(ArrayList<String> parsedTab) {
    	
//    	// For Debugging
//    	for(int i = 0; i < parsedTab.size(); i++) {
//    		System.out.println(parsedTab.get(i));
//    	}
        
        int counter = 0; // iterates horizontally
        int noteLength = 0; // in 16th notes
        int lines = parsedTab.size(); // number of lines in staff
        int currentLine = 0;
        int prevChordNum = 0; // number of notes in previous chord
        int curChordNum = 0; // number of notes in current chord

        boolean isCounting = false; // if we're currently counting noteLength
        
        while (counter < parsedTab.get(0).length() - 2) { // Changed from -1 to -2, the parsedTab is one space longer than expected ???
            
            currentLine = 0;
            
            // Skip "|" and padding "-"
            if (parsedTab.get(0).charAt(counter) == '|') {
                
                // Assuming note lengths end at barlines
                if(noteLength != 0) {
                	// Add all tracked notes to arrays
                	while (prevChordNum > 0) {
	                    durationArr.add("" + noteLength);
	                    typeArr.add(durationToType(noteLength, divisions));
	                    prevChordNum--;
                	}
                	
                	// since we have reached end of measure, reset and stop counting noteLength
                    noteLength = 0;
                    isCounting = false;
                }
                
                durationArr.add("|");
                typeArr.add("|");
                counter += padding; // skipping both '|' and padding '-'
            }
            
            // Should be run before encountering the first note/chord in a measure
            else if (isCounting == false) {
                
                // Check when the next note starts 
                while(currentLine < lines) {
                    
                	// Add all notes to a chord
                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
                    	
                    	// should only be done once
                    	if (isCounting == false) {
                    		isCounting = true;
                    		noteLength++;
                    	}
                    	
                        prevChordNum++;
                    }
                    
                    currentLine++;
                }
            }
            
            // Should be run after encountering the first note/chord in a measure
            else if (isCounting == true) {
                
                while(currentLine < lines) {
                    
                	// Adds all previous chord notes to arrays, then adds current notes to current chord
                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
                    	while (prevChordNum > 0) {
	                        durationArr.add("" + noteLength);
	                        typeArr.add(durationToType(noteLength, divisions));
	                        prevChordNum--;
                    	}
                    	
                    	// reset note Length
                        noteLength = 0;
                        curChordNum++;
                    }
                    
                    currentLine++;
                }
                
                // if there are notes in the current chord, copy them to previous chord and empty current chord
                if(curChordNum != 0) {
                	prevChordNum = curChordNum;
                	curChordNum = 0;
                }
                
                noteLength++;
            }
            
            counter++;
        }
        
        // Last chord/note length and ending barline is added
        while (prevChordNum > 0) {
	        durationArr.add("" + noteLength);
	        typeArr.add(durationToType(noteLength, divisions));
	        prevChordNum--;
        }
        durationArr.add("||");
        typeArr.add("||");
        
//        // For debugging
//        System.out.print("Duration Array: ");
//        System.out.println(durationArr);
//        System.out.print("Type Array: ");
//        System.out.println(typeArr);

    }
    
    private String durationToType(int duration, int divisions) {
        
        double durOverDiv = (double) duration / divisions;
        String result = "";
        
        if (durOverDiv == 4.0) {
            result = "whole";
        }
        else if (durOverDiv == 2.0) {
            result = "half";
        }
        else if (durOverDiv == 1.0) {
            result = "quarter";
        }
        else if (durOverDiv == 0.5) {
            result = "eighth";
        }
        else if (durOverDiv == 0.25) {
            result = "sixteenth";
        }
        else {
        	result = "unidentified";
        }
        
        return result;
        
    }    
    public ArrayList<String> getTypeArr(){
    	return this.typeArr;
    }
    public ArrayList<String> getDurationArr(){
    	return this.durationArr;
    }
}
