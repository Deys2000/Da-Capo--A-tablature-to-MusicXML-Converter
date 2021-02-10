package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class parser {

	ArrayList<String> parsed  = new ArrayList<>();
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
			ArrayList<String> extracted  = new ArrayList<>();
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){
				
				String line = sc.nextLine();
				if (line.contains("-") && line.contains("|")) {// default tuning EADGBE
						extracted.add(line);
				}
			}
			for(int i = 0; i < extracted.size(); i++) 
			{
				if(i >= 6) {
				StringBuilder sb = new StringBuilder();
				sb.append(parsed.get(i % 6));
				sb.append(extracted.get(i).substring(2));
				parsed.set(i%6, sb.toString());
				}
				else 
				{
					parsed.add(extracted.get(i));
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
	public ArrayList<String> getParsed() {
		return parsed;
	}
	public void test() 
	{	
		this.measures = -1;
		String line = parsed.get(0);
		for(int i = 0; i < line.length(); i++) 
		{
			if(line.charAt(i) ==  '|')
				this.measures++;
		}
		
//		for(String s : extracted) 
//		{
//			if(s.charAt(0) == '|') 
//			{
//				
//			}
//		}
	}
}
