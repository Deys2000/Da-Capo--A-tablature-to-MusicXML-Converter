package tabToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
import com.gluonhq.charm.glisten.control.TextField;
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
	static TextFileReader textFile;

	StringBuilder parsedInfo;
	String instrument = "";
	String[][] information;
	GuitarParser gp;
	xmlGen xg;

	public static Stage currentStage; // acquired from Main when program starts

	public HomeController() {

	}

	@FXML
	public Button selectButton;
	@FXML
	public Label filePathLabel, statusLabel;
	@FXML
	public TextArea tabTextArea1, tabTextArea2, tabTextArea3;
	@FXML
	public CodeArea codeArea1;
	@FXML
	public CodeArea codeArea2;
	@FXML
	public CodeArea codeArea3;
	@FXML
	public HBox hBox;
	@FXML
	public HBox parentContainer;
	@FXML
	public JFXDrawer drawer1;
	@FXML
	public JFXButton saveButton, editButton, uploadButton, formartButton, convertButton , saveTimeSig;
	@FXML
	public javafx.scene.control.TextField titleText;
	@FXML
	public ChoiceBox choiceBox, beatChoice, typeChoice;
	@FXML
	public Spinner<Integer> from, to;
	@FXML
	public StackPane stackPane;

	public static JFXDialog dialog;

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
		System.out.println(location.getPath().substring(location.getPath().lastIndexOf('/') + 1));
		String file = location.getPath().substring(location.getPath().lastIndexOf('/') + 1);
		if (file.equals("Home5.fxml")) {
			var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, TabView.measures.size(), 1);
			var factory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, TabView.measures.size(), 1);
			factory.setWrapAround(true);
			factory2.setWrapAround(true);
			from.setValueFactory(factory);
			to.setValueFactory(factory2);
			to.decrement();
			beatChoice.getItems().addAll(2, 4, 6, 8, 9);
			typeChoice.getItems().addAll(2, 4, 8);
			titleText.setText(textFile.getMusicPieceTitle());
			saveTimeSig.setDisable(true);
			// from.setValueFactory(new SpinnerValueFactory<>());;
		}
		if (codeArea1 != null)
			TabView.Xmlsyntax(codeArea1, choiceBox, editButton, formartButton, convertButton);
		if (codeArea2 != null)
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
			// statusLabel.setTextFill(Color.RED);
			// statusLabel.setText("File Status: No file selected, pls select a .txt file");
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

	public void converter(ActionEvent event) throws Exception {
		// empty the XMLArea before getting new info
		// tabTextArea2.setText("");
		// saveButton.setDisable(true);
		if (TabView.majorError) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("FIX THE ERROR'S HIGHLIGTED IN RED!");
			alert.setContentText("bar's are either missaligned or tab type cannot be determained.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
			return;
		} else if (TabView.measures == null || TabView.measures.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Tab Detected");
			alert.setHeaderText("cant convert nothing... fool!");
			alert.setContentText("input a tab first.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

			return;
		}

		else if (TabView.noEndbar) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("A measure is missing a endBarLine!");
			alert.setContentText("please fix this to continue.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
			return;
		}
		// read the contents of the Tablature Editor Window
		String textAreaContents = codeArea1.getText();
		// Once the contents are collected, they should be sent to the TextFileReader
		// But the TextFileReader only takes file objects, so i need to make the
		// contents of the text area back into a file to process
		// making a file from the contants of the editor window
		try {
			// EVERYTHING ABOVE THIS LINE HAS BEEN TESTED, IT WORKS :)
			switch (textFile.getDetectedInstrument()) {
			case "Guitar":
				// tabTextArea3.setText("Instrument Detected: "+ tfr.getDetectedInstrument());
				GuitarParser gp = new GuitarParser(textFile);
				xg = new xmlGen(gp);
				sceneSwitcher(xg);
				// the following two lines should be outside the switch case, but bass and drums
				// dont work yet
				// if(tfr.checkAlignedVerticals() == false) {
				// tabTextArea2.setText("tablature misaligned, please check spacing or input a
				// different tab");
				// }
				break;
			case "Drum":
				/// tabTextArea3.setText("Instrument Detected: "+ tfr.getDetectedInstrument()
				// + "\n\nSystem is in prototype phase, unable to process Drums completely."
				// + "\nUse with caution."
				// + "\nYou may find that rests and beams are not processed correctly.");
				DrumParser dp = new DrumParser(textFile);
				xg = new xmlGen(dp);
				sceneSwitcher(xg);
				// the following two lines should be outside the switch case, but bass and drums
				// dont work yet
				// System.out.println("XMLCONTENT"+xg.getXMLContent());
				// if(tfr.checkAlignedVerticals() == false) {
				// tabTextArea2.setText("tablature misaligned, please check spacing or input a
				// different tab");
				// }
				break;
			case "Bass":
				// tabTextArea3.setText("Instrument Detected: "+ tfr.getDetectedInstrument()
				// + "\nSystem is in prototype phase, unable to process Bass.");
				GuitarParser bp = new GuitarParser(textFile);
				xg = new xmlGen(bp);
				sceneSwitcher(xg);
				break;
			default:
				// tabTextArea3.setText("Instrument Detected: "+ tfr.getDetectedInstrument());
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

	public void edit(MouseEvent event) throws IOException {

		if (TabView.majorError) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("FIX THE ERROR'S HIGHLIGTED IN RED!");
			alert.setContentText("bar's are either missaligned or tab type cannot be determained.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

			return;
		} else if (TabView.measures == null || TabView.measures.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Tab Detected");
			alert.setHeaderText("cant edit nothing... fool!");
			alert.setContentText("input a tab first.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

			return;
		} else if (TabView.noEndbar) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("A measure is missing a endBarLine!");
			alert.setContentText("please fix this to continue.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
			return;
		}
		GridPane grid = FXMLLoader.load(getClass().getResource("Home5.fxml"));
	

		dialog = new JFXDialog(stackPane, grid, JFXDialog.DialogTransition.LEFT);
		dialog.show();
	}

	public void updateMeasure(MouseEvent event) throws IOException {
		codeArea3.replaceText(" ");
		StringBuilder sb = new StringBuilder();
		System.out.println(from.getEditor().getText());
		System.out.println(from.getValue());

		saveTimeSig.setDisable(false);
		if (from.getValue() > to.getValue()) {
			to.getValueFactory().setValue(from.getValue());
		}
		if(from.getValue() != to.getValue()){
			beatChoice.setValue(" ");
			typeChoice.setValue(" ");
		}
		else{
			beatChoice.setValue(textFile.attributesPerMeasure.get(from.getValue()-1).getBeats());
			typeChoice.setValue(textFile.attributesPerMeasure.get(from.getValue()-1).getBeattype());
		}

		for (int i = from.getValue(); i <= to.getValue(); i++) {

			for (int j = 0; j < TabView.measures.get(i).size(); j++) {

				sb.append(TabView.measures.get(i).get(j));
				sb.append("\n");
			}
			sb.append("\n");
		}
		codeArea3.replaceText(sb.toString());
		codeArea3.position(0, 0);
	}

	public void compileTab(MouseEvent event) throws IOException {
		if (TabView.majorError) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("FIX THE ERROR'S HIGHLIGTED IN RED!");
			alert.setContentText("bar's are either missaligned or tab type cannot be determained.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

			return;
		} else if (TabView.measures == null || TabView.measures.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Tab Detected");
			alert.setHeaderText("cant edit nothing... fool!");
			alert.setContentText("input a tab first.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});

			return;
		} else if (TabView.noEndbar) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("MAJOR ERROR");
			alert.setHeaderText("A measure is missing a endBarLine!");
			alert.setContentText("please fix this to continue.");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
			return;
		} else {
			StringBuilder sb = new StringBuilder();

			for (int i = 1; i <= TabView.measures.size(); i++) {
				sb.append("\tMeasure: " + i);
				sb.append("\n");
				for (String s : TabView.measures.get(i))
					sb.append(s + "\n");
				sb.append("\n");
			}

			codeArea1.replaceText(" ");
			codeArea1.replaceText(sb.toString());
			codeArea1.position(0, 0);
			formartButton.setDisable(true);
			editButton.setDisable(false);
			convertButton.setDisable(false);

			File newFile = new File("TabEditorContents");
			FileWriter myWriter = null;
			myWriter = new FileWriter(newFile);
			myWriter.write(sb.toString() + "\n\n");
			myWriter.close();

			// pass the file to textfilereader so we can begin the process
			textFile = new TextFileReader(newFile);
		}
	}

	/**
	 * Method for saving a file
	 * 
	 * @throws IOException
	 */
	public void saveFile(Stage stage) throws IOException {
		// can put into users tmp file - good practice
		fc = new FileChooser();

		String contenttoSave;
		if (saveButton.getText().equals(" Save XML")) {
			fc.setTitle("Save MusicXML Conversion");
			contenttoSave = xg.getXMLContent();
			fc.getExtensionFilters().add(new ExtensionFilter("MusicXML File", "*.musicxml", "*.xml"));
		} else {
			fc.setTitle("Save Tab");
			contenttoSave = codeArea1.getText();
			fc.getExtensionFilters().add(new ExtensionFilter("text tab", "*.txt"));
		}

		File file = fc.showSaveDialog(stage);
		String operationResult = "Successfully written to file.";

		// clean this up later
		try {
			FileWriter myWriter = new FileWriter(file);
			// xmlGen gen10 = new xmlGen(gp.processor());
			// gen10.createFile(file);
			myWriter.write(contenttoSave);
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

	private void sceneSwitcher(xmlGen xg2) throws IOException {
		VirtualizedScrollPane<CodeArea> vSP = FXMLLoader.load(getClass().getResource("Home4.fxml"));
		System.out.println(vSP.getContent().toString());
		if (codeArea2 == null)
			codeArea2 = vSP.getContent();

		if (drawer1.getSidePane().isEmpty())
			drawer1.setSidePane(vSP);
		if (drawer1.isOpened()) {
			drawer1.close();
			saveButton.setText(" Save Tab");
			editButton.setVisible(true);
			uploadButton.setDisable(false);
			formartButton.setVisible(true);
			codeArea1.requestFocus();
			drawer1.toBack();
			Timeline timeline = new Timeline();
			KeyValue kv = new KeyValue(codeArea1.opacityProperty(), 1, Interpolator.EASE_IN);
			KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();
			codeArea1.setEditable(true);
		} else {
			codeArea2.replaceText(" ");
			codeArea2.replaceText(xg.getXMLContent());
			codeArea2.position(0, 0);
			drawer1.open();
			saveButton.setText(" Save XML");
			editButton.setVisible(false);
			uploadButton.setDisable(true);
			formartButton.setVisible(false);
			drawer1.toFront();
			codeArea1.setOpacity(0);
			codeArea1.setEditable(false);
			codeArea2.requestFocus();
			codeArea2.displaceCaret(0);
			codeArea2.scrollYToPixel(0);
		}
	}


	public void saveEdit(MouseEvent event) throws IOException
	{
			
			textFile.setRangeOfAttributes(from.getValue()-1, to.getValue()-1, beatChoice.getValue() == " " ? 4 : (Integer) beatChoice.getValue(),typeChoice.getValue() == " " ? 4 : (Integer) typeChoice.getValue(), "", "");
			textFile.setMusicPieceTitle(titleText.getText());
			dialog.close();
	}
	/**
	 * Method to read a .txt file and displaying in the field window
	 * 
	 * @throws Exception
	 */
	public void reader() throws Exception {

		StringBuilder sb = new StringBuilder(); 
		try {
			//File myObj = new File("filename.txt");
			Scanner myReader = new Scanner(selectedFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				sb.append(data);
				sb.append("\n");
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		codeArea1.replaceText(" ");
		codeArea1.replaceText(sb.toString());
		codeArea1.position(0, 0);
	}
}
