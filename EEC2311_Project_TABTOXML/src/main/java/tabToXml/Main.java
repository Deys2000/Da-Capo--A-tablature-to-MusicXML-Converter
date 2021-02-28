package tabToXml;


import java.io.File;

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
		launch(args);
		//sideTask(); // what will launch this? 

	}
	
	public static void sideTask() throws Exception {
		
		TextFileReader fileReader = new TextFileReader("tab.txt");
		GuitarParser gp = new GuitarParser();

		System.out.println(fileReader.printOrginal());
		
		gp.translateParsed("tab.txt");
		//RhythmParser rhythmParser = new RhythmParser(4);
        gp.parseToRhythm(fileReader.getParsed());
		
		System.out.println("Notes: " + gp.getNotes() + " size of array: " + gp.getNotes().size());
		System.out.println("Chord?: " + gp.getChordArr() + " size of array: " + gp.getChordArr().size() );
		System.out.println("Frets: " + gp.getFretNums() + " size of array: " + gp.getFretNums().size());
		System.out.println("Fret Strings: " + gp.getFretStrings() + " size of array: " + gp.getFretStrings().size());
        System.out.println("duration: \t" + gp.getDurationArr() + " Length of Array:" + gp.getDurationArr().size() );
	    System.out.println("type: \t" + gp.getTypeArr() + " Length of Array:" + gp.getTypeArr().size() );
		
	    System.out.println("Number of Lines in the Tab is: " + fileReader.numberOfLines());
	    for(String s: fileReader.getParsed())	
	    	System.out.println(s);

	    
		xmlGen gen10 = new xmlGen(gp.processor());
		gen10.createFile(new File("file.xml"));
		
		DrumParser dp = new DrumParser("exampleTabInput");
		
		System.exit(0);
	}  
}