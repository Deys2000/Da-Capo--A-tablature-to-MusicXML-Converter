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
	
		System.out.println("Launching Application");
		launch(args);
		//new stuff
		// the following lines are just test examples you can use to test my parser.java class.
		parser p = new parser("tab.txt");
		parser q = new parser("exampleemajor.txt");
		p.createParsed();
		p.test();
		q.createParsed();
		q.test();
		for(String s : p.getParsed())
			System.out.println(s);
		System.out.println("*******************");
		for(String s : q.getParsed())
			System.out.println(s);
	}

  
}