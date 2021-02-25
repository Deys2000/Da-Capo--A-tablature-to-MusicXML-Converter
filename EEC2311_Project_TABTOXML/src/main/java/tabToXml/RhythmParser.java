package tabToXml;

import java.util.ArrayList;

public class RhythmParser {
    
    public ArrayList<String> durationArr = new ArrayList<String>();
    public ArrayList<String> typeArr = new ArrayList<String>();
    
    private int divisions = 0;
    private int padding = 1;

    public RhythmParser(int divisions) { // divisions should be 4 by default
        durationArr = new ArrayList<String>();
        typeArr = new ArrayList<String>();
        this.divisions = divisions;
    }
    
    /**
     * Generates duration and type arrays from parsed array
     * @param
     * @throws Exception
     */
    public void parseToRhythm(ArrayList<String> parsedTab) {
        
        int counter = 0;
        int noteLength = 0; // in 16th notes
        int lines = parsedTab.size();
        int currentLine = 0;
        //int prevChordNum = 0;
        //int curChordNum = 0;
        //String rhythmLine = "";
        
        boolean hasFret = false;
        boolean isCounting = false;
        
        while (counter < parsedTab.get(0).length() - 2) { // Changed from -1 to -2, the parsedTab is one space longer than expected ???
            
            currentLine = 0;
            hasFret = false;
            
            // Skip "|" and padding "-"
            if (parsedTab.get(0).charAt(counter) == '|') {
                
                // Assuming note lengths end at barlines
                if(noteLength != 0) {
                	//while (prevChordNum > 0) {
	                    durationArr.add("" + noteLength);
	                    typeArr.add(durationToType(noteLength, divisions));
	                    //prevChordNum--;
                	//}
                    noteLength = 0;
                    isCounting = false;
                }
                
                durationArr.add("|");
                typeArr.add("|");
                counter += padding; // skipping both '|' and padding '-'
            }
            
            // Should be only run before a note is encountered
            else if (isCounting == false) {
                
                // Check when the next note starts // NOTE ONLY WORKS FOR FRETS 0-9
                while(hasFret == false && currentLine < lines) {
                    
                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
                        isCounting = true;
                        noteLength++;
                        //prevChordNum++;
                        
                        hasFret = true;
                    }
                    
                    currentLine++;
                }
            }
            
            // Should be run all other times
            else if (isCounting == true) {
                
                while(hasFret == false && currentLine < lines) {
                    
                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
                    	//while (prevChordNum > 0) {
	                        durationArr.add("" + noteLength);
	                        typeArr.add(durationToType(noteLength, divisions));
	                        //prevChordNum--;
                    	//}
                        noteLength = 0;
                        //curChordNum++;
                        hasFret = true;
                    }
                    
                    currentLine++;
                }
                
                //prevChordNum = curChordNum;
                //curChordNum = 0;
                noteLength++;
            }
            
            counter++;
        }
        
        // Last note length and ending barline is added
        //while (prevChordNum > 0) {
	        durationArr.add("" + noteLength);
	        typeArr.add(durationToType(noteLength, divisions));
	        //prevChordNum--;
        //}
        durationArr.add("||");
        typeArr.add("||");
        
        // small temporary fix for error- Syed
        
//        if(durationArr.size() > 3 && typeArr.size() > 3) {
//        	durationArr.remove(durationArr.size()-2);
//        	durationArr.remove(durationArr.size()-2);
//        	typeArr.remove(typeArr.size()-2);
//        	typeArr.remove(typeArr.size()-2);
//        }
        
        System.out.print("Duration Array: ");
        System.out.println(durationArr);
        System.out.print("Type Array: ");
        System.out.println(typeArr);
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
