package tabToXml;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Converter: Text to MusicXML");
		// keep scene equal or bigger than default in fxml - 800x500
	    primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")), 800, 500)); 
		primaryStage.show();
		HomeController.currentStage = primaryStage;
	}
	
	public static void main(String[] args) throws Exception {

		System.out.print("Launching Application" + "\n");
		//launch(args);
		 sideTask(); // what will launch this? 
	}
	
	public static void sideTask() throws Exception {
		
		TextFileReader fileReader = new TextFileReader("tab.txt");
		TabParser tmp = new TabParser();

		
		System.out.println(fileReader.printOrginal());
		
		tmp.translateParsed("tab.txt");
		System.out.println("Notes: " + tmp.notes() + " size of array: " + tmp.notes().size());
		System.out.println("Frets: " + tmp.fretNums() + " size of array: " + tmp.fretNums().size());
		System.out.println("Fret Strings: " + tmp.fretStrings() + " size of array: " + tmp.fretStrings().size());


		RhythmParser rhythmParser = new RhythmParser(4);
        rhythmParser.parseToRhythm(fileReader.getParsed());
		
        System.out.println("duration: \t" + rhythmParser.getDurationArr() + " Length of Array:" + rhythmParser.getDurationArr().size() );
	    System.out.println("type: \t" + rhythmParser.getTypeArr() + " Length of Array:" + rhythmParser.getTypeArr().size() );
		
	    System.out.println("Number of Lines in the Tab is: " + fileReader.numberOfLines());
	    for(String s: fileReader.getParsed())	
	    	System.out.println(s);
	    
		//new stuff
	    String[][] information = XMLGenerator.processor(tmp.notes(), tmp.fretNums(), tmp.fretStrings(), rhythmParser.getDurationArr(), rhythmParser.getTypeArr());
		System.out.println(XMLGenerator.runner(information));
		System.exit(0);
	}  
}
