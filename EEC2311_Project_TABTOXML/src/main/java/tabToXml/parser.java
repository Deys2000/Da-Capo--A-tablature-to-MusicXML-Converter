package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class parser {

	private ArrayList<String> extracted = new ArrayList<String>();
	private File inputFile;
	private int  measures;
	private ArrayList<String> baseNotes  = new ArrayList<>();
	
	parser(String file)
	{
		this.inputFile = new File(file);
	}
	
	public void createParsed(){
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){
				
				String line = sc.nextLine();
				if (line.contains("-") && line.contains("|")) // default tuning EADGBE
					extracted.add(line);	
			}		
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
	public void test() 
	{	
		this.measures = -1;
		String line = extracted.get(0); 
		for(int i = 0; i < line.length(); i++) 
		{
			if(line.charAt(i) ==  '|')
				this.measures++;
		}
	}
}
