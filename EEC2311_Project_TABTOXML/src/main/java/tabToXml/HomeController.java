package tabToXml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomeController {

	FileChooser fc;
	File selectedFile, oldFile;
	String txtFileContents, fileType;

	StringBuilder parsedInfo;
	String instrument = "";
	String[][] information;
	GuitarParser gp;
	xmlGen xg;
	
	public static Stage currentStage; // acquired from Main when program starts
	
	public HomeController() {}
	
	@FXML
	private Button selectButton, convertButton, saveButton;
	@FXML
	private Button viewText1, viewparsed1, viewXml1;
	@FXML
	private Button viewText2, viewparsed2, viewXml2;
	@FXML
	private Label filePathLabel, statusLabel, instrumentLabel;
	@FXML
	private TextArea tabTextArea1, tabTextArea2, tabTextArea3;
	
	/**
	 * Method for action event of file chooser button being clicked
	 * @param event
	 * @throws Exception 
	 */
	public void fileChooser (ActionEvent event) throws Exception{
		fc = new FileChooser();
		selectedFile = fc.showOpenDialog(null);
		if(selectedFile == null)
			selectedFile = oldFile;
		oldFile = selectedFile;
		
		if (selectedFile != null) {
			filePathLabel.setText("File Path: "+ selectedFile.getAbsolutePath());
		}
		System.out.println(selectedFile);
		
		if(selectedFile != null) {
			String filePath = selectedFile.getAbsolutePath();
			int index = filePath.lastIndexOf('.');
			fileType = filePath.substring(index);
		}
		
		if (selectedFile == null) {
			statusLabel.setTextFill(Color.RED);
			statusLabel.setText("File Status: No file selected, pls select a .txt file");
			//convertButton.setDisable(true);
		}
		else if(fileType.equals(".txt")) {
			statusLabel.setTextFill(Color.GREEN);
			statusLabel.setText("File Status: A \".txt\" file, you may convert this");
			reader();
			convertButton.setDisable(false);
		}else {
			statusLabel.setTextFill(Color.RED);
			statusLabel.setText("File Status: NOT a \".txt\" file, pls select a .txt file");
			tabTextArea1.setText("");
			tabTextArea2.setText("");
			instrumentLabel.setText("Instrument Detection: Unable to Identify");
			convertButton.setDisable(true);
		}		
	}
	
	/**
	 * Method for convert button being clicked
	 * It takes the stuff in the tab editor area, turns it into a text file, passes that to textfile reader
	 * Textfile reader will determine the instrument and clean the measures
	 * (perhaps we ask the user for default info like beat & beat type)
	 * depending on the instrument, we send the file to a guitar, drum or bass parser
	 * @param event
	 * @throws Exception 
	 */
	public void converter(ActionEvent event) throws Exception {
				
		//read the contents of the Tablature Editor Window
		String textAreaContents = tabTextArea1.getText();
		//Once the contents are collected, they should be sent to the TextFileReader
		//But the TextFileReader only takes file objects, so i need to make the contents of the text area back into a file to process

		// making a file from the contants of the editor window
		File newFile = new File("TabEditorContents");
		FileWriter myWriter = null;
		try { 
			myWriter = new FileWriter(newFile);
			myWriter.write(textAreaContents); 
			myWriter.close();
		} 
		catch (IOException e) { e.printStackTrace(); }
		
		// pass the file to textfilereader so we can begin the process
		TextFileReader tfr = new TextFileReader(newFile);
		tabTextArea3.setText("Instrument Detected: "+ tfr.detectInstrument());
		
		// EVERYTHING ABOVE THIS LINE HAS BEEN TESTED, IT WORKS :)
		switch(tfr.detectInstrument()) {
		case "Guitar":
			GuitarParser gp = new GuitarParser(tfr.getParsed());
			xg = new xmlGen(gp);
			break;
		case "Drum":
			//DrumParser dp = new DrumParser(tfr.getParsed());
			//xmlGen xg = new xmlGen(dp);
			break;
		case "Bass":
			//BassParser bp = new BassParser(tfr.getParsed());
			//xmlGen xg = new xmlGen(bp);
			break;
		default:
			//give some error message saying instrument was not detected or something
			
		}	
		//xmlGen xg = null;
		tabTextArea2.setText(xg.getXMLContent());		
		saveButton.setDisable(false);
	}
	
	
	
	/**
	 * I DONT KNOW WHY THERE ARE TWO METHODS TO SAVE ONE FILE
	 * KEEP BOTH UNTIL WE FIGURE OUT WHY
	 * @param event
	 * @throws IOException
	 */
	public void save(ActionEvent event) throws IOException {
		saveFile(currentStage);
	}
	/**
	 * Method for saving a file
	 * @throws IOException 
	 */
	public void saveFile(Stage stage) throws IOException {
		//can put into users tmp file - good practice
		
		fc = new FileChooser();
		fc.setTitle("Save MusicXML Conversion");
		File file = fc.showSaveDialog(stage);
		//String operationResult = "Successfully written to file.";
		
		// clean this up later
		try {			
			FileWriter myWriter = new FileWriter(file);
		//xmlGen gen10 = new xmlGen(gp.processor());
		//gen10.createFile(file);
			myWriter.write(xg.getXMLContent());
			myWriter.close();			
			
		} finally {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Message");
			alert.setHeaderText("Operation Status");
			alert.setContentText("Successfully written to file");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
		}		
	}
	
	/**
	 * Method to read a .txt file and displaying in the field window
	 * @throws Exception 
	 */
	public void reader() throws Exception {
		TextFileReader guitarTab = new TextFileReader(selectedFile.getAbsolutePath());		
		//set area to be the text from the file
		tabTextArea1.setText(guitarTab.printOrginal());	
//		
//		
//		parsedInfo = new StringBuilder();
//		//adding the parsed tab
//		parsedInfo.append(guitarTab.printOrginal());		
//	
//		gp = new GuitarParser();
//		gp.translateParsed(selectedFile.getAbsolutePath());
//		
//		parsedInfo.append("\nNotes: " + gp.getNotes() + " Length of array: " + gp.getNotes().size());
//		parsedInfo.append("\nFrets: " + gp.getFretNums() + " Length of array: " + gp.getFretNums().size());
//		parsedInfo.append("\nFret Strings: " + gp.getFretStrings() + " Length of array: " + gp.getFretStrings().size());
//
//		//RhythmParser rhythmParser = new RhythmParser(4);
//        gp.parseToRhythm(guitarTab.getParsed());
//		
//        parsedInfo.append("\nDuration:" + gp.getDurationArr() + " Length of Array:" + gp.getDurationArr().size() );
//	    parsedInfo.append("\nType:" + gp.getTypeArr() + " Length of Array:" + gp.getTypeArr().size() );
//		
//		//tabTextArea2.setText(parsedInfo.toString());
//	    
//		//new stuff
//	    //information = gp.processor();
//		tabTextArea2.setText(xg.getXMLContent());
//		//System.exit(0);
//
//		instrument = guitarTab.detectInstrument();
//		instrumentLabel.setText("Instrument Detection: " + instrument);
	}
	
}
