package tabToXml;

import java.util.ArrayList;
import java.util.List;

public class GuitarParser {
	
	// all the arrays to contain information gathered by the notes
	public static ArrayList<String> notes = new ArrayList<>();
	public static ArrayList<String> fretString = new ArrayList<>();
	public static ArrayList<String> fretNum = new ArrayList<>();
    public ArrayList<String> durationArr = new ArrayList<String>();
    public ArrayList<String> typeArr = new ArrayList<String>();
    public static ArrayList<String> chords = new ArrayList<String>();
    public static ArrayList<String> handp = new ArrayList<String>();
  
    
    
    // current tuning, from top line to bottom line
    public String[] tuning = {"E4", "B3", "G3", "D3", "A2", "E2"};
    
    private int divisions = 4; //current default is 4
    private int padding = 1;
    
    
    public GuitarParser(ArrayList<String> tfrparsed) throws Exception {
    	translateParsed(tfrparsed);
    	parseToRhythm(tfrparsed);
    }
    
//    public static String removeCharAt(String str, int pos) {
//    	return str.substring(0, pos) + str.substring(pos + 1);
//    }
    
//    public int columnLength(ArrayList<String> parsedTab) {
//    	String shortString = parsedTab.get(0);
//    	
//    	for (String elem : parsedTab) {
//    		if (elem.length() < shortString.length()) {
//    			shortString = elem;
//    		}
//    	}
//    	
//    	return shortString.length();
//    }
    
	/*This method takes the input file and parses that into a 2d array. 
	 *The result of that 2d array is used to obtain the fret and chord/note
	 *using the translate() method above. */    
	public void translateParsed(ArrayList<String> parsedTab) throws Exception {

		int row = parsedTab.size();	
		int next = parsedTab.get(0).length();
//		next = parsedTab.get(0).length(); //Adjust the lenght of the current line of the tablature
		char fret2 = '\0';
		String tmp = "";
	
		int cal = 0;
		int j = 0;
			// go through each column
			for (j = 0; j < next; j++) {			
				String fretStringValue = "";
				String fretNumVar = "";   // reset the fret string checker on the iteration of each new column
				String chord = ""; 		// clear value of note before every column
				
				// go through each row
				for(int i = 0; i < row; i++) {

					next = parsedTab.get(i).length(); //Adjust the lenght of the current line of the tablature
					String fretStringVar = ""; //This is the number  of the current string for a Guitar the string are numbered from 1-6
					
					fret2 = parsedTab.get(i).charAt(j);					
					int fret = Character.getNumericValue(fret2);
					
					if ((fret2 >= '0' && fret2 <= '9') && (j > 0 && j < next)) {
						if (parsedTab.get(i).charAt(j+1) >= '0' && parsedTab.get(i).charAt(j+1) <= '9') {
							continue;
						}
					}
					
					if (fret2 >= '0' && fret2 <= '9') {
						fretStringValue = ""; //Used for concatenating two single numbers to make a double digit number
						tmp = "";
						
						// make regular note 
						if (chord.isEmpty() && fretNumVar.isEmpty() && fretStringVar.isEmpty()) {
							
							//fret is the number
							if (parsedTab.get(i).charAt(j-1) >= '0' && parsedTab.get(i).charAt(j-1) <= '9') {
								fretStringValue =  String.valueOf(parsedTab.get(i).charAt(j-1)) + String.valueOf(parsedTab.get(i).charAt(j));
								fret = Integer.parseInt(fretStringValue);
//								parsedTab.set(i, removeCharAt(parsedTab.get(i), j-1));							
							}else {
								fretStringValue = fret + "";
							}
						
							if (fret <= 24) {
								chord = translate(tuning[i], fret);
								fretNumVar = fretStringValue;
								if (parsedTab.get(i).charAt(j+1) == 'p' || parsedTab.get(i).charAt(j+1) == 'P') {
									fretNumVar = fretNumVar + "p";
								}
								if (parsedTab.get(i).charAt(j-2) == 'p' || parsedTab.get(i).charAt(j-2) == 'P' || parsedTab.get(i).charAt(j-1) == 'p' || parsedTab.get(i).charAt(j-1) == 'P') {
									fretNumVar = "p" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == 'h' || parsedTab.get(i).charAt(j+1) == 'H') {
									fretNumVar = fretNumVar + "h";
								}
								if (parsedTab.get(i).charAt(j-2) == 'h' || parsedTab.get(i).charAt(j-2) == 'H' || parsedTab.get(i).charAt(j-1) == 'h' || parsedTab.get(i).charAt(j-1) == 'H') {
									fretNumVar = "h" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == '/') {
									fretNumVar = fretNumVar + "/";
								}
								if (parsedTab.get(i).charAt(j-2) == '/' || parsedTab.get(i).charAt(j-1) == '/') {
									fretNumVar = "/" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == '\\' || parsedTab.get(i).charAt(j+1) == '\\') {
									fretNumVar = fretNumVar + "\\";
								}
								if (parsedTab.get(i).charAt(j-2) == '\\' || parsedTab.get(i).charAt(j-1) == '\\') {
									fretNumVar = "\\" + fretNumVar;
								}
								//----
								if ((parsedTab.get(i).charAt(j-2) == '[' || parsedTab.get(i).charAt(j-1) == '[') && parsedTab.get(i).charAt(j+1) == ']') {
									fretNumVar = "[" + fretNumVar + "]";
								}
								if ((parsedTab.get(i).charAt(j-2) == '{' || parsedTab.get(i).charAt(j-1) == '{') && parsedTab.get(i).charAt(j+1) == '}') {
									fretNumVar = "[" + fretNumVar + "]";
								}
								if ((parsedTab.get(i).charAt(j-2) == '(' || parsedTab.get(i).charAt(j-1) == '(') && parsedTab.get(i).charAt(j+1) == ')') {
									fretNumVar = "[" + fretNumVar + "]";
								}							
								//-------
//								else {fretNumVar = fretStringValue;}	
								cal = i + 1;
								fretStringVar = cal + "";
							}
							
						}else { // make a chord note
//							notes.remove(notes.size() - 1);
//							fretNum.remove(fretNum.size() - 1);
							//fretString.remove(fretString.size()-1);
							
//							notes.add(chord);
//							fretNum.add(fretNumVar);
							//fretString.add(fretStringVar);
							
							if (parsedTab.get(i).charAt(j-1) >= '0' && parsedTab.get(i).charAt(j-1) <= '9') {
								fretStringValue =  String.valueOf(parsedTab.get(i).charAt(j-1)) + String.valueOf(parsedTab.get(i).charAt(j));
								fret = Integer.parseInt(fretStringValue);
//								parsedTab.set(i, removeCharAt(parsedTab.get(i), j-1));
							}else {
								fretStringValue = fret + "";
							}
							
							
							
							if (fret <= 24) {
								chord = "+" + translate(tuning[i], fret);
								fretNumVar = fretStringValue;
								if (parsedTab.get(i).charAt(j+1) == 'p' || parsedTab.get(i).charAt(j+1) == 'P') {
									fretNumVar = fretNumVar + "p";
								}
								if (parsedTab.get(i).charAt(j-2) == 'p' || parsedTab.get(i).charAt(j-2) == 'P' || parsedTab.get(i).charAt(j-1) == 'p' || parsedTab.get(i).charAt(j-1) == 'P') {
									fretNumVar = "p" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == 'h' || parsedTab.get(i).charAt(j+1) == 'H') {
									fretNumVar = fretNumVar + "h";
								}
						        if (parsedTab.get(i).charAt(j-2) == 'h' || parsedTab.get(i).charAt(j-2) == 'H' || parsedTab.get(i).charAt(j-1) == 'h' || parsedTab.get(i).charAt(j-1) == 'H') {
									fretNumVar = "h" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == '/') {
									fretNumVar = fretNumVar + "/";
								}
								if (parsedTab.get(i).charAt(j-2) == '/' || parsedTab.get(i).charAt(j-1) == '/') {
									fretNumVar = "/" + fretNumVar;
								}
								if (parsedTab.get(i).charAt(j+1) == '\\' || parsedTab.get(i).charAt(j+1) == '\\') {
									fretNumVar = fretNumVar + "\\";
								}
								if (parsedTab.get(i).charAt(j-2) == '\\' || parsedTab.get(i).charAt(j-1) == '\\') {
									fretNumVar = "\\" + fretNumVar;
								}
								//----
								if ((parsedTab.get(i).charAt(j-2) == '[' || parsedTab.get(i).charAt(j-1) == '[') && parsedTab.get(i).charAt(j+1) == ']') {
									fretNumVar = "[" + fretNumVar + "]";
								}
								if ((parsedTab.get(i).charAt(j-2) == '{' || parsedTab.get(i).charAt(j-1) == '{') && parsedTab.get(i).charAt(j+1) == '}') {
									fretNumVar = "[" + fretNumVar + "]";
								}
								if ((parsedTab.get(i).charAt(j-2) == '(' || parsedTab.get(i).charAt(j-1) == '(') && parsedTab.get(i).charAt(j+1) == ')') {
									fretNumVar = "[" + fretNumVar + "]";
								}
								
								//-------
//						        else {fretNumVar = fretStringValue;}							
								fretStringVar = cal + "";
							}								
						} 
						
//						if (!fretNumVar.isEmpty() && !chord.isEmpty() && !fretStringVar.isEmpty()) {
//							if (parsedTab.get(i).charAt(j+1) == 'p' || parsedTab.get(i).charAt(j+1) == 'P') {
//								fretNumVar = fretStringValue + "p";
//							}else if (parsedTab.get(i).charAt(j-1) == 'p' || parsedTab.get(i).charAt(j-1) == 'P') {
//								fretNumVar = "p" + fretStringValue;
//							}else if (parsedTab.get(i).charAt(j+1) == 'h' || parsedTab.get(i).charAt(j+1) == 'H') {
//								fretNumVar = fretStringValue + "h";
//							}else if (parsedTab.get(i).charAt(j-1) == 'h' || parsedTab.get(i).charAt(j-1) == 'H') {
//								fretNumVar = "h" + fretStringValue;
//							}
//						}
										
								
						addToLists(chord, fretNumVar, String.valueOf(i+1));
	
					}
					else if (fret2 == '|') {
						if (tmp.isEmpty()) {
							tmp = fret2 + "";
							addToLists(tmp, tmp, tmp);

						}
					}

				}
			}
		
			removeFromLists(notes.size() - 1, fretNum.size() - 1, fretString.size()-1);

			addToLists("||", "||", "||");

		//cleaning up the + in the chords and making a chords list
		for( int i = 0; i < notes.size(); i++) {
			if(notes.get(i).charAt(0) == '+'){
    			notes.set(i, notes.get(i).substring(1));
				chords.add("true");
    		}
    		else if( notes.get(i).charAt(0) == '|') {
    			chords.add("|");
    		}
    		else {
    			chords.add("false");
    		}
    	}
		
		for( int i = 0; i < fretNum.size(); i++) {
			
				if ((fretNum.get(i).charAt(0) >= '0' && fretNum.get(i).charAt(0) <= '9') ^ (fretNum.get(i).charAt(fretNum.get(i).length()-1) >= '0' && fretNum.get(i).charAt(fretNum.get(i).length()-1) <= '9')) {
					if(fretNum.get(i).charAt(0) == 'p'){
						fretNum.set(i, fretNum.get(i).substring(1));
						handp.add("pstop");
		    		}
					else if(fretNum.get(i).charAt(0) == 'h'){
						fretNum.set(i, fretNum.get(i).substring(1));
						handp.add("hstop"); //h10
		    		}
					else if(fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'p'){
						fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
						handp.add("pstart"); //11p
		    		}
					else if(fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'h'){
						fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
						handp.add("hstart"); //11h
		    		}
					else if(fretNum.get(i).charAt(fretNum.get(i).length()-1) == '/'){
						fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
						handp.add("astart"); //11h
		    		}
					else if(fretNum.get(i).charAt(fretNum.get(i).length()-1) == '\\'){
						fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
						handp.add("dstart"); //11h
		    		}
					else if(fretNum.get(i).charAt(0) == '\\'){
						fretNum.set(i, fretNum.get(i).substring(1));
						handp.add("dstop"); //h10
		    		}
					else if(fretNum.get(i).charAt(0) == '/'){
						fretNum.set(i, fretNum.get(i).substring(1));
						handp.add("astop"); //h10
		    		}
				}
//				else if (!((fretNum.get(i).charAt(0) >= '0' && fretNum.get(i).charAt(0) <= '9') ^ (fretNum.get(i).charAt(fretNum.get(i).length()-1) >= '0' && fretNum.get(i).charAt(fretNum.get(i).length()-1) <= '9'))) {
				else if (fretNum.get(i).charAt(0) == 'p' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'h') {
						fretNum.set(i, fretNum.get(i).substring(1));
						fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
						handp.add("PstopHstart");
						
				}
				else if (fretNum.get(i).charAt(0) == 'h' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'p') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("HstopPstart");
				}
				else if (fretNum.get(i).charAt(0) == 'h' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'h') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("HstopHstart");
				}
				else if (fretNum.get(i).charAt(0) == 'p' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == 'p') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("PstopPstart");
				}		
				//-------
				else if (fretNum.get(i).charAt(0) == '/' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == '\\') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("AstopDstart");
					
				}
				else if (fretNum.get(i).charAt(0) == '\\' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == '/') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("DstopAstart");
				}
				else if (fretNum.get(i).charAt(0) == '/' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == '/') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("AstopAstart");
				}
				else if (fretNum.get(i).charAt(0) == '\\' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == '\\') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("DstopDstart");
				}
				//----------
				
				//-----------
				else if (fretNum.get(i).charAt(0) == '[' && fretNum.get(i).charAt(fretNum.get(i).length()-1) == ']') {
					fretNum.set(i, fretNum.get(i).substring(1));
					fretNum.set(i, fretNum.get(i).substring(0, fretNum.get(i).length()-1));
					handp.add("Nharmonics");	
				}
				//-----------
				
	    		else if( fretNum.get(i).charAt(0) == '|') {
	    			handp.add("|");
	    		}
	    		else {
	    			handp.add("neutral");
	    		}
				
    	}
			
		// chords - add the double bars later
	}
	
	public static void addToLists(String note, String ft, String fs) {
		notes.add(note);
		fretNum.add(ft);
		fretString.add(fs);
		
	}
	
	public static void removeFromLists(int note, int ft, int fs) {
		notes.remove(note);
		fretNum.remove(ft);
		fretString.remove(fs);
		
	}
	
	//CARRIED OVER FROM RHYTHMPARSER CLASS
		/**
	     * Generates duration and type arrays from parsed array
	     * @param parsedTab - a formatted tab (arrayList of Strings)
	     */
	    public void parseToRhythm(ArrayList<String> parsedTab) {
	    	
//	    	// For Debugging
//	    	for(int i = 0; i < parsedTab.size(); i++) {
//	    		System.out.println(parsedTab.get(i));
//	    	}
	        
	        int counter = 0; // iterates horizontally
	        int noteLength = 0; // in 16th notes
	        int lines = parsedTab.size(); // number of lines in staff
	        int currentLine = 0;
	        int prevChordNum = 0; // number of notes in previous chord
	        int curChordNum = 0; // number of notes in current chord
	        
	        boolean isDoubleDigit = false;
	        
	        while (counter < parsedTab.get(0).length() - 1) { 
	            
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
	                }
	                
	                durationArr.add("|");
	                typeArr.add("|");
	                counter += padding; // skipping both '|' and padding '-', if padding exists
	            }
	            
	            // Should be run before encountering the first note/chord in a measure
	            else if (prevChordNum == 0) {
	            	
	            	// Assume Frets are Single digit
	                while(currentLine < lines && !isDoubleDigit) {
	                	                  
	                	// Add all notes to a chord
	                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
	                    	
	                    	// check if fret is doubledigit
	                    	if(Character.isDigit(parsedTab.get(currentLine).charAt(counter + 1))) {
	                    		isDoubleDigit = true;
	                    	}
	                    	
	                    	// starts counting noteLength, should only be done once
	                    	if (prevChordNum == 0) {
	                    		noteLength++;
	                    	}
	                    	
	                        prevChordNum++;
	                    }
	                    
	                    currentLine++;
	                }
	                
	                // If DoubleDigit frets, recount using one's place
	                if (isDoubleDigit) {
	                	int totalFretNum = 0;
	                	currentLine = 0;
	                	
	                	while (currentLine < lines) {
	                		if(Character.isDigit(parsedTab.get(currentLine).charAt(counter + 1))) {
	                			totalFretNum++;
	                		}
	                		
	                		currentLine++;
	                	}
	                	
	                	prevChordNum = totalFretNum;
	                }
	            }
	            
	            // Should be run after encountering the first note/chord in a measure
	            else{
	                
	            	// Assume Frets are Single Digit
	                while(currentLine < lines && !isDoubleDigit) {
	                    
	                	// Adds all previous chord notes to arrays, then adds current notes to current chord
	                    if(Character.isDigit(parsedTab.get(currentLine).charAt(counter))) {
	                    	
	                    	// check if fret is doubledigit
	                    	if(Character.isDigit(parsedTab.get(currentLine).charAt(counter+1))) {
	                    		isDoubleDigit = true;
	                    	}
	                    	
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
	                
	                // If DoubleDigit frets, recount using one's place
	                if (isDoubleDigit) {
	                	int totalFretNum = 0;
	                	currentLine = 0;
	                	
	                	while (currentLine < lines) {
	                		if(Character.isDigit(parsedTab.get(currentLine).charAt(counter + 1))) {
	                			totalFretNum++;
	                		}
	                		
	                		currentLine++;
	                	}
	                	
	                	prevChordNum = totalFretNum;
	                }
	                
	                // if there are notes in the current chord, copy them to previous chord and empty current chord
	                if(curChordNum != 0) {
	                	prevChordNum = curChordNum;
	                	curChordNum = 0;
	                }
	                
	                noteLength++; 
	            }
	            
	            // If Fret is double-digit, increment counter and reset isDoubleDigit
	            if (isDoubleDigit) {
	            	counter++;
	            	isDoubleDigit = false;
	            }
	            
	            counter++;
	        }
	        
	        // Last chord/note length and ending barline is added
	        while (prevChordNum > 0) {
		        durationArr.add("" + noteLength);
		        typeArr.add(durationToType(noteLength, divisions));
		        prevChordNum--;
	        }
	        
	        /**Elijah added this might have been fixed before but was still getting and irregular size for durationArr and typeArr**/
	        durationArr.remove(typeArr.size()-1);
	        typeArr.remove(typeArr.size()-1);
	        
	        durationArr.add("||");
	        typeArr.add("||");
	        
//	        // For debugging
//	        System.out.print("Duration Array: ");
//	        System.out.println(durationArr);
//	        System.out.print("Type Array: ");
//	        System.out.println(typeArr);

	}
    
    /**
     * CARRIED OVER FROM RHYTHM PARSER CLASS
     * returns the word representation of the duration amount
     * @param duration
     * @param divisions
     * @return
     */
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
            result = "16th";
        }
        else {
        	result = "16th"; //default
        }
        
        return result;
        
    }    

	
	/**
	 * Converts a Note
	 * C > C#/Db > D > D#/Eb > E > F > F#/Gb > G > G#/Ab > A > A#/Bb > B > Loops back to C
	 * @param Note
	 * @return
	 * @throws Exception 
	 */
	public static String translate(String string, int fret) throws Exception{
		if(fret > 24)
			throw new Exception("The fret must be between 1 and 24 (inclusive)");

		String[] table = {"C","C","D","D","E","F","F","G","G","A","A","B"};			
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
	
	// CARRIED OVER FROM XMLGENERATOR CLASS
    // A PROCESSING METHOD TO MAKE INFORMATION EASY TO ACCESS FOR JAXB
	// CHANGE LATER SO IT MAKES OBJECTS RATHER THAN STRINGS
    public String[][] processor() {
    	ArrayList<String> notes = this.notes;
    	ArrayList<String> fretNums = this.fretNum;
		ArrayList<String> fretStrings = this.fretString;
		ArrayList<String> durationArr = this.durationArr;
		ArrayList<String> typeArr = this.typeArr;
		ArrayList<String> chordArr = this.chords;
		ArrayList<String> handpArr = this.handp;
		
    	// inserting into 2d array
    	//each row contains: duration, step, alter,octave,type,string,fret and CHORD in each row
    	String [][] infoArray = new String[notes.size()][9]; // added one to account for NEW chord property
    	
    	for(int i = 0; i < notes.size(); i++) {
    		if(notes.get(i).contains("|")){
    			infoArray[i][0] = null;
        		infoArray[i][1] = null; 
        		infoArray[i][2] = null;
        		infoArray[i][3] = null;
        		infoArray[i][4] = null;
        		infoArray[i][5] = null;
        		infoArray[i][6] = null; 
        		infoArray[i][7] = null;
        		infoArray[i][8] = null;
    		}
    		else {    		
    			infoArray[i][0] = durationArr.get(i);   //duration
    			infoArray[i][1] = notes.get(i).substring(0,1); // step 
    			infoArray[i][2] = "1"; //default4now // alter
    			infoArray[i][3] = notes.get(i).substring(1); // octave
    			infoArray[i][4] = typeArr.get(i); //type
    			infoArray[i][5] = fretStrings.get(i); //string
    			infoArray[i][6] = fretNums.get(i); // fret 
    			infoArray[i][7] = chordArr.get(i); // "false" or "true" depending on if its a chord or not
    			infoArray[i][8] = handpArr.get(i); // contains whether a note is hammer on and pull off
    		}
    	}
    	
		return infoArray;
	}
	
    
    // METHODS TO SEND EACH OF THE ARRAYS OF INFORMATION TO XMLGEN 
    
	public ArrayList<String> getNotes(){
		return notes;
	}	
	public ArrayList<String> getFretStrings(){
		return fretString;
	}
	
	public ArrayList<String> getFretNums(){
		return fretNum;
	}
    public ArrayList<String> getTypeArr(){
    	return this.typeArr;
    }
    public ArrayList<String> getDurationArr(){
    	return this.durationArr;
    }
    public ArrayList<String> getChordArr(){
    	return this.chords;
    }
    public ArrayList<String> getHandPArr(){
    	return handp;
    }
    
}
