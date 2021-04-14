package tabToXml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@ExtendWith(ApplicationExtension.class)
public class HomeControllerTest {
	
	
	 private HomeController homeController;
	@Start
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home3.fxml"));
		Parent root = loader.load();
		homeController = (HomeController) loader.getController();
		HomeController.currentStage = primaryStage;
		Scene scene = new Scene(root, 920,600);
		root.setStyle("-fx-hgap: 5;-fx-padding: 5; -fx-background-color: #4A525A;-fx-background-radius: 15;-fx-border-radius: 10;-fx-border-width: 5px;-fx-border-color:#55D6BE;");
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Da Capo : Text Tab to MusicXML Converter - version 1.0");
		primaryStage.getIcons().add(new Image("file:DaCapo.jpg"));
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setHeight((primaryScreenBounds.getHeight())/1.5);
		File file = new File("bigTab.txt");
		homeController.selectedFile = file;
		homeController.reader();
		primaryStage.show();
		
	}

	@Test
	public void containsTextArea(FxRobot robot) {
		
		 FxAssert.verifyThat(homeController.saveButton, LabeledMatchers.hasText(" Save Tab"));
		
	}
	@Test
	public void CheckIfPastedTabisValid(FxRobot robot) throws InterruptedException {
		 FxAssert.verifyThat(homeController.saveButton, LabeledMatchers.hasText(" Save Tab"));
		robot.clickOn("#codeArea1");
		assertEquals(false,TabView.majorError);
	}
	@Test
	public void editBtnDisabled(FxRobot robot) {
	
		robot.clickOn("#editButton");
		assertEquals(true,homeController.convertButton.isDisabled());
	}
	@Test
	public void convertBtnDisabled(FxRobot robot) {
		robot.clickOn("#convertButton");
		assertEquals(true,homeController.convertButton.isDisabled());
	}
	@Test
	public void formartBtn_enabled(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		assertEquals(false,homeController.convertButton.isDisabled());
	}
	@Test
	public void editBtn_DisabledAfter_userInput(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		assertEquals(false,homeController.editButton.isDisabled());
	}
	@Test
	public void convertBtn_DisabledAfter_userInput(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		assertEquals(false,homeController.convertButton.isDisabled());
	}
	@Test
	public void xmlSceneisLoaded(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		robot.clickOn("#convertButton");
		assertEquals(true,homeController.codeArea2.isVisible());
	}
	@Test
	public void Savebtn_Text_is_saveXML_after_sceneswap(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		robot.clickOn("#convertButton");
		FxAssert.verifyThat("#saveButton", LabeledMatchers.hasText(" Save XML"));
	}
	@Test
	public void Savebtn_Text_is_saveTAB_after_sceneswap(FxRobot robot) {
		robot.clickOn("#codeArea1");
		robot.clickOn("#formartButton");
		robot.clickOn("#convertButton");
		robot.clickOn("#codeArea1");
		robot.clickOn("#convertButton");
		FxAssert.verifyThat("#saveButton", LabeledMatchers.hasText(" Save Tab"));
	}
	@Test
	public void clicking_save_changes_closes_dialog(FxRobot robot) throws InterruptedException {
		FxAssert.verifyThat(homeController.saveButton, LabeledMatchers.hasText(" Save Tab"));
		robot.clickOn("#formartButton");
		robot.clickOn("#editButton");
		WaitForAsyncUtils.waitForFxEvents();
		robot.clickOn("#viewBtn");
		robot.clickOn("#saveTimeSig");
		assertEquals(true,homeController.dialog.isOverlayClose());
	}

}
