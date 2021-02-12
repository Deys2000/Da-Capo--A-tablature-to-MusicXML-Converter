package tabToXml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	
	public static void main(String[] args) {
	
		System.out.print("Launching Application" + "\n");
		//launch(args);
		TextFileReader fileReader = new TextFileReader("test.txt");
		TabInterface tmp = new TabInterface();
		
		for (int i = 0; i < fileReader.printParsed().size(); i++) {
			System.out.print(fileReader.printParsed().get(i) + "\n");
		}
		
		System.out.print(tmp.translateParsed("test.txt"));
		//new stuff
		
	}

  
}
