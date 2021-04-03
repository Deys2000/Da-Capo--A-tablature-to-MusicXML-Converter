package tabToXml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

import com.gluonhq.charm.glisten.control.Icon;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;

import org.fxmisc.flowless.VirtualizedScrollPane;

public class HomeController implements Initializable {

	// CLEAN UP, THERE IS A LOT OF EXTRA VARIABLES AND LINES HANGING AROUND
	// ALSO LOOK INTO DEVELOPING GUI SO WE CAN SATISFY THE NEW REQUIREMENTS

	FileChooser fc;
	File selectedFile, oldFile;
	String txtFileContents, fileType;

	StringBuilder parsedInfo;
	String instrument = "";
	String[][] information;
	GuitarParser gp;
	xmlGen xg;

	public static Stage currentStage; // acquired from Main when program starts

	public HomeController() {

	}

	@FXML
	private Button selectButton, convertButton;
	@FXML
	private Label filePathLabel, statusLabel;
	@FXML
	private TextArea tabTextArea1, tabTextArea2, tabTextArea3;
	@FXML
	public CodeArea codeArea1;
	@FXML
	public CodeArea codeArea2;
	@FXML
	private HBox hBox;
	@FXML
	private HBox parentContainer;
	@FXML
	private JFXDrawer drawer1;
	@FXML
	private JFXButton saveButton,editButton,uploadButton; 
	@FXML
	private ChoiceBox choiceBox;
	@FXML
	private StackPane stackPane;

	private double xOffset = 0;
	private double yOffset = 0;

	/**
	 * Method for action event of file chooser button being clicked
	 * 
	 * @param event
	 * @throws Exception
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(codeArea1 != null)
			TabView.Xmlsyntax(codeArea1,choiceBox);
		if(codeArea2 != null)
			XMLView.Xmlsyntax(codeArea2);
		if (choiceBox != null)
			choiceBox.getItems().addAll("Guitar", "Bass", "Drum");
	}

	public void fileChooser(MouseEvent event) throws Exception {
		fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("text tab", "*.txt"));
		selectedFile = fc.showOpenDialog(currentStage);
		if (selectedFile == null)
			// selectedFile = oldFile;
			oldFile = selectedFile;

		if (selectedFile != null) {
			// filePathLabel.setText("File Path: "+ selectedFile.getAbsolutePath());
		}
		System.out.println(selectedFile);

		if (selectedFile != null) {
			String filePath = selectedFile.getAbsolutePath();
			int index = filePath.lastIndexOf('.');
			fileType = filePath.substring(index);
		}

		if (selectedFile == null) {
			//statusLabel.setTextFill(Color.RED);
			//statusLabel.setText("File Status: No file selected, pls select a .txt file");
			// convertButton.setDisable(true);
		} else if (fileType.equals(".txt")) {
			// statusLabel.setTextFill(Color.GREEN);
			// statusLabel.setText("File Status: A \".txt\" file, you may convert this");
			reader();
			// convertButton.setDisable(false);
		} else {
			// statusLabel.setTextFill(Color.RED);
			// statusLabel.setText("File Status: NOT a \".txt\" file, pls select a .txt
			// file");
			// tabTextArea1.setText("");
			// tabTextArea2.setText("");
			// instrumentLabel.setText("Instrument Detection: Unable to Identify");
			// convertButton.setDisable(true);
		}
	}

	/**
	 * Method for convert button being clicked It takes the stuff in the tab editor
	 * area, turns it into a text file, passes that to textfile reader Textfile
	 * reader will determine the instrument and clean the measures (perhaps we ask
	 * the user for default info like beat & beat type) depending on the instrument,
	 * we send the file to a guitar, drum or bass parser
	 * 
	 * @param event
	 * @throws Exception
	 */
	// for the quit button
	public void quit(MouseEvent event) throws Exception {
		currentStage.close();
	}


	// for moveing of window assign to onMousePressed event
	public void dragger(MouseEvent event) throws Exception {
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();
	}

	// for moveing of window assign to onMouseDragged event
	public void dragger2(MouseEvent event) throws Exception {
		currentStage.setX(event.getScreenX() - xOffset);
		currentStage.setY(event.getScreenY() - yOffset);

	}

	// for drag and drop feature
	public void dragOver(DragEvent event) {

		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
		event.consume();

	}

	// for drag and drop feature
	public void dragDropped(DragEvent event) throws Exception {

		List<File> files = event.getDragboard().getFiles();
		System.out.println("Got " + files.size() + " files");
		String fileExtension = files.get(0).getName().substring(files.get(0).getName().lastIndexOf(".") + 1,
				files.get(0).getName().length());
		System.out.println(fileExtension);
		if (fileExtension.equals("txt") || fileExtension.equals("TXT")) {
			selectedFile = files.get(0);
			reader();
		}
		event.consume();
	}

	@FXML
	public void loadSecond(ActionEvent event) throws Exception {
		VirtualizedScrollPane<CodeArea> vSP = FXMLLoader.load(getClass().getResource("Home4.fxml"));
		System.out.println(drawer1.getSidePane());
		if (drawer1.getSidePane().isEmpty())
			drawer1.setSidePane(vSP);
		if (drawer1.isOpened()) {
			drawer1.close();
			saveButton.setText("Save Tab");
			codeArea1.requestFocus();
		} else {
			converter(event);
			drawer1.open();
			saveButton.setText("Save XML");
			codeArea2.requestFocus();
		}
	}

	public void converter(ActionEvent event) throws Exception {
		// empty the XMLArea before getting new info
		// tabTextArea2.setText("");
		VirtualizedScrollPane<CodeArea> vSP = FXMLLoader.load(getClass().getResource("Home4.fxml"));
		System.out.println(vSP.getContent().toString());
		codeArea2 = vSP.getContent();
		codeArea2.replaceText(0, 0, "");

		// saveButton.setDisable(true);

		// read the contents of the Tablature Editor Window
		String textAreaContents = codeArea1.getText();
		// Once the contents are collected, they should be sent to the TextFileReader
		// But the TextFileReader only takes file objects, so i need to make the
		// contents of the text area back into a file to process

		// making a file from the contants of the editor window
		File newFile = new File("TabEditorContents");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(newFile);
			myWriter.write(textAreaContents + "\n\n");
			myWriter.close();

			// pass the file to textfilereader so we can begin the process
			TextFileReader tfr = new TextFileReader(newFile);
			System.out.println(tfr.detectInstrument());
			// EVERYTHING ABOVE THIS LINE HAS BEEN TESTED, IT WORKS :)
			switch (tfr.detectInstrument()) {
			case "Guitar":
				// tabTextArea3.setText("Instrument Detected: " + tfr.detectInstrument());
				GuitarParser gp = new GuitarParser(tfr.getParsed());
				xg = new xmlGen(gp, tfr);
				// the following two lines should be outside the switch case, but bass and drums
				// dont work yet
				// tabTextArea2.setText(xg.getXMLContent());
				if (drawer1.getSidePane().isEmpty())
					drawer1.setSidePane(vSP);

				if (drawer1.isOpened()) {
					drawer1.close();
					saveButton.setText(" Save Tab");
					editButton.setVisible(true);
					uploadButton.setVisible(true);
					codeArea1.requestFocus();
					drawer1.toBack();
					Timeline timeline = new Timeline();
					KeyValue kv = new KeyValue(codeArea1.opacityProperty(), 1, Interpolator.EASE_IN);
					KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
					timeline.getKeyFrames().add(kf);
					timeline.play();
					codeArea1.setEditable(true);
				} else {
					codeArea2.replaceText(xg.getXMLContent());
					codeArea2.position(0, 0);
					drawer1.open();
					saveButton.setText(" Save XML");
					editButton.setVisible(false);
					uploadButton.setVisible(false);
					drawer1.toFront();
					codeArea1.setOpacity(0);
					codeArea1.setEditable(false);
					codeArea2.requestFocus();
					codeArea2.displaceCaret(0);
					codeArea2.scrollYToPixel(0);
				}
				// saveButton.setDisable(false);
				System.out.println("Notes: " + gp.getNotes() + " size of array: " + gp.getNotes().size());
				System.out.println("Chord?: " + gp.getChordArr() + " size of array: " + gp.getChordArr().size());
				System.out.println("Frets: " + gp.getFretNums() + " size of array: " + gp.getFretNums().size());
				System.out.println(
						"Fret Strings: " + gp.getFretStrings() + " size of array: " + gp.getFretStrings().size());
				System.out.println(
						"duration: \t" + gp.getDurationArr() + " Length of Array:" + gp.getDurationArr().size());
				System.out.println("type: \t" + gp.getTypeArr() + " Length of Array:" + gp.getTypeArr().size());
				break;
			case "Drum":
				tabTextArea3.setText("Instrument Detected: " + tfr.detectInstrument()
						+ "\n\nSystem is in prototype phase, unable to process Drums completely."
						+ "\nUse with caution." + "\nYou may find that rests and beams are not processed correctly.");
				DrumParser dp = new DrumParser(tfr.getParsed());
				xg = new xmlGen(dp, tfr);
				// the following two lines should be outside the switch case, but bass and drums
				// dont work yet
				// tabTextArea2.setText(xg.getXMLContent());
				//saveButton.setDisable(false);
				break;
			case "Bass":
				tabTextArea3.setText("Instrument Detected: " + tfr.detectInstrument()
						+ "\nSystem is in prototype phase, unable to process Bass.");
				BassParser bp = new BassParser(tfr.getParsed());
				xg = new xmlGen(bp, tfr);
				// tabTextArea2.setText(xg.getXMLContent());
				//saveButton.setDisable(false);
				break;
			default:
				tabTextArea3.setText("Instrument Detected: " + tfr.detectInstrument());
				throw new Exception();
			// give some error message saying instrument was not detected or something

			}
			// xmlGen xg = null;
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Message");
			alert.setHeaderText("ERROR: INVALID INPUT");
			alert.setContentText("System is Unable to recognize the Tablature. "
					+ "\nPlease look at Section 5.2[Input Limitations] of the User Manual for more information");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

		}

	}

	/**
	 * I DONT KNOW WHY THERE ARE TWO METHODS TO SAVE ONE FILE KEEP BOTH UNTIL WE
	 * FIGURE OUT WHY
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void save(MouseEvent event) throws IOException {
		saveFile(currentStage);
	}

	/**
	 * Method for saving a file
	 * 
	 * @throws IOException
	 */
	public void saveFile(Stage stage) throws IOException {
		// can put into users tmp file - good practice
		fc = new FileChooser();
		if(saveButton.getText().equals(" Save XML"))
		{
			fc.setTitle("Save MusicXML Conversion");
			fc.getExtensionFilters().add(new ExtensionFilter("MusicXML File", "*.musicxml", "*.xml"));
		}
		else
		{
			fc.setTitle("Save Tab");
			fc.getExtensionFilters().add(new ExtensionFilter("text tab", "*.txt"));
		}
		
		File file = fc.showSaveDialog(stage);
		String operationResult = "Successfully written to file.";

		// clean this up later
		try {
			FileWriter myWriter = new FileWriter(file);
			// xmlGen gen10 = new xmlGen(gp.processor());
			// gen10.createFile(file);
			myWriter.write(xg.getXMLContent());
			myWriter.close();

		} catch (Exception e) {
			operationResult = "Save Cancelled";
		} finally {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("User Message");
			alert.setHeaderText("Operation Status");
			alert.setContentText(operationResult);
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
	}

	/**
	 * Method to read a .txt file and displaying in the field window
	 * 
	 * @throws Exception
	 */
	public void reader() throws Exception {
		TextFileReader guitarTab = new TextFileReader(selectedFile.getAbsolutePath());
		// set area to be the text from the file
		// tabTextArea1.setText(guitarTab.printOrginal());
		codeArea1.replaceText(guitarTab.printOrginal());
		// parsedInfo = new StringBuilder();
		// //adding the parsed tab
		// parsedInfo.append(guitarTab.printOrginal());
		//
		// gp = new GuitarParser();
		// gp.translateParsed(selectedFile.getAbsolutePath());
		//
		// parsedInfo.append("\nNotes: " + gp.getNotes() + " Length of array: " +
		// gp.getNotes().size());
		// parsedInfo.append("\nFrets: " + gp.getFretNums() + " Length of array: " +
		// gp.getFretNums().size());
		// parsedInfo.append("\nFret Strings: " + gp.getFretStrings() + " Length of
		// array: " + gp.getFretStrings().size());
		//
		// //RhythmParser rhythmParser = new RhythmParser(4);
		// gp.parseToRhythm(guitarTab.getParsed());
		//
		// parsedInfo.append("\nDuration:" + gp.getDurationArr() + " Length of Array:" +
		// gp.getDurationArr().size() );
		// parsedInfo.append("\nType:" + gp.getTypeArr() + " Length of Array:" +
		// gp.getTypeArr().size() );
		//
		// //tabTextArea2.setText(parsedInfo.toString());
		//
		// //new stuff
		// //information = gp.processor();
		// tabTextArea2.setText(xg.getXMLContent());
		// //System.exit(0);
		//
		// instrument = guitarTab.detectInstrument();
		// instrumentLabel.setText("Instrument Detection: " + instrument);
	}
}
