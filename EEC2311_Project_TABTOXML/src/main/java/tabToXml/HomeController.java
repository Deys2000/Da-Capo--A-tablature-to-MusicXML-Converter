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
	
	public static Stage currentStage; // acquired from Main when program starts
	
	public HomeController() {}
	
	@FXML
	private Button selectButton, convertButton;
	@FXML
	private Button viewText1, viewparsed1, viewXml1;
	@FXML
	private Button viewText2, viewparsed2, viewXml2;
	@FXML
	private Label filePathLabel, statusLabel, instrumentLabel;
	@FXML
	private TextArea tabTextArea1, tabTextArea2;
	
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
			convertButton.setDisable(true);
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
	 * Method for action event of the convert button being clicked
	 * @param event
	 */
	public void converter(ActionEvent event) {
		saveFile(currentStage);
	}
	
	/**
	 * Method for saving a file
	 */
	public void saveFile(Stage stage) {
		fc = new FileChooser();
		fc.setTitle("Save Translation");
		File file = fc.showSaveDialog(stage);
		String operationResult = "Successfully written to file.";
				
		try {			
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(XMLGenerator.runner(information));
			myWriter.close();			
			
		} catch (IOException e) {
			operationResult = "Failed to write to file.";
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
		
		
		parsedInfo = new StringBuilder();
		//adding the parsed tab
		parsedInfo.append(guitarTab.printOrginal());		
	
		TabParser tmp = new TabParser();
		tmp.translateParsed(selectedFile.getAbsolutePath());
		
		parsedInfo.append("\nNotes: " + tmp.notes() + " Length of array: " + tmp.notes().size());
		parsedInfo.append("\nFrets: " + tmp.fretNums() + " Length of array: " + tmp.fretNums().size());
		parsedInfo.append("\nFret Strings: " + tmp.fretStrings() + " Length of array: " + tmp.fretStrings().size());

		RhythmParser rhythmParser = new RhythmParser(4);
        rhythmParser.parseToRhythm(guitarTab.getParsed());
		
        parsedInfo.append("\nDuration:" + rhythmParser.getDurationArr() + " Length of Array:" + rhythmParser.getDurationArr().size() );
	    parsedInfo.append("\nType:" + rhythmParser.getTypeArr() + " Length of Array:" + rhythmParser.getTypeArr().size() );
		
		//tabTextArea2.setText(parsedInfo.toString());
	    
		//new stuff
	    information = XMLGenerator.processor(tmp.notes(), tmp.fretNums(), tmp.fretStrings(), rhythmParser.getDurationArr(), rhythmParser.getTypeArr());
		tabTextArea2.setText(XMLGenerator.runner(information));
		//System.exit(0);

		instrument = guitarTab.detectInstrument();
		instrumentLabel.setText("Instrument Detection: " + instrument);
	}
	
}
