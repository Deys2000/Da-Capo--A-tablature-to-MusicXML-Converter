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
	    primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")),800, 500)); 
		primaryStage.show();
	}
	
	public static void main(String[] args) throws Exception {
	

		System.out.print("Launching Application" + "\n");
		launch(args);
		TextFileReader fileReader = new TextFileReader("test.txt");
		TabInterface tmp = new TabInterface();
		
		for (int i = 0; i < fileReader.printParsed().size(); i++) {
			System.out.print(fileReader.printParsed().get(i) + "\n");
		}
		
		tmp.translateParsed("test.txt");
		System.out.print("\nNotes: " + tmp.notes() + " size of array: " + tmp.notes().size());
		System.out.print("\nFrets: " + tmp.fretNums() + " size of array: " + tmp.fretNums().size());
		System.out.print("\nFret Strings: " + tmp.fretStrings() + " size of array: " + tmp.fretStrings().size());

		
		//new stuff
		XMLGenerator.runner();
		System.exit(0);
	}

  
}
