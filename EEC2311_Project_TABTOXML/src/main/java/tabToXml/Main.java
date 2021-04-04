package tabToXml;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class Main extends Application {

	private HomeController homeController;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home3.fxml"));
		Parent root = loader.load();

		homeController = (HomeController) loader.getController();
		HomeController.currentStage = primaryStage;
		Scene scene = new Scene(root, 920,600);
		root.setStyle("-fx-hgap: 5;-fx-padding: 5; -fx-background-color: #4A525A;-fx-background-radius: 15;-fx-border-radius: 10;-fx-border-width: 5px;-fx-border-color:#55D6BE;");
		scene.setFill(Color.TRANSPARENT);
		//primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Da Capo : Text Tab to MusicXML Converter - version 1.0");
		// keep scene equal or bigger than default in fxml - 800x500
		primaryStage.getIcons().add(new Image("file:DaCapo.png"));
		primaryStage.getScene().getStylesheets().add(test.class.getResource("xml-highlighting.css").toExternalForm());
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		 Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		 //primaryStage.setX(primaryScreenBounds.getMinX());
		 //primaryStage.setY(primaryScreenBounds.getMinY());
		//primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight((primaryScreenBounds.getHeight())/1.5);
		primaryStage.show();
		// System.out.println(homeController.codeArea1);
	}

	public static void main(String[] args) throws Exception {

		System.out.print("Launching Application" + "\n");
		launch(args);
		// sideTask(); // what will launch this?

	}

	public static void sideTask() throws Exception {

		TextFileReader fileReader = new TextFileReader("DrumsTab2.txt");
		// //An if statement should select which one to call based on the instrument
		// detection
		// GuitarParser gp = new GuitarParser(fileReader.getParsed());
		// BassParser gp = new BassParser();
		//
		// System.out.println(fileReader.printOrginal());
		//
		// //gp.translateParsed("tab.txt");
		// //RhythmParser rhythmParser = new RhythmParser(4);
		// gp.parseToRhythm(fileReader.getParsed());
		// gp.translateParsed(fileReader.getParsed());
		//
		// System.out.println("Notes: " + gp.getNotes() + " size of array: " +
		// gp.getNotes().size());
		// System.out.println("Chord?: " + gp.getChordArr() + " size of array: " +
		// gp.getChordArr().size() );
		// System.out.println("Frets: " + gp.getFretNums() + " size of array: " +
		// gp.getFretNums().size());
		// System.out.println("Fret Strings: " + gp.getFretStrings() + " size of array:
		// " + gp.getFretStrings().size());
		// System.out.println("duration: \t" + gp.getDurationArr() + " Length of Array:"
		// + gp.getDurationArr().size() );
		// System.out.println("type: \t" + gp.getTypeArr() + " Length of Array:" +
		// gp.getTypeArr().size() );
		//
		// System.out.println("Number of Lines in the Tab is: " +
		// fileReader.numberOfLines());
		// for(String s: fileReader.getParsed())
		// System.out.println(s);
		//
		//
		// xmlGen gen10 = new xmlGen(gp);
		// //gen10.createFile(new File("file.xml"));
		// System.out.println(gen10.getXMLContent());

		System.exit(0);
	}
}