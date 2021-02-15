package tabToXml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class HomeController {

	FileChooser fc;
	File selectedFile, oldFile;
	String txtFileContents, fileType;
	
	public HomeController() {}
	
	@FXML
	private Button selectButton, convertButton;
	@FXML
	private Button viewText1, viewparsed1, viewXml1;
	@FXML
	private Button viewText2, viewparsed2, viewXml2;
	@FXML
	private Label filePathLabel, statusLabel;
	@FXML
	private TextArea tabTextArea1, tabTextArea2;
	
	/**
	 * Method for action event of file chooser button being clicked
	 * @param event
	 * @throws IOException
	 */
	public void fileChooser (ActionEvent event) throws IOException{
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
		}
		else if(fileType.equals(".txt")) {
			statusLabel.setTextFill(Color.GREEN);
			statusLabel.setText("File Status: A \".txt\" file, you may convert this");
			reader();
		}else {
			statusLabel.setTextFill(Color.RED);
			statusLabel.setText("File Status: NOT a \".txt\" file, pls select a .txt file");
			tabTextArea1.setText("");
		}		
	}
	
	/**
	 * Method for action event of the convert button being clicked
	 * @param event
	 */
	public void converter(ActionEvent event) {
				
	}
	
	/**
	 * Method to read a .txt file and displaying in the field window
	 */
	public void reader() {
		TextFileReader gt = new TextFileReader(selectedFile.getAbsolutePath());
		tabTextArea1.setText(gt.printOrginal());	
		
		for (int i = 0; i < gt.printParsed().size(); i++) {
			tabTextArea2.setText(gt.printParsed().get(i).toString());
		}
		
	}
	
}
