package tabToXml;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TabView {

    private static final Pattern XML_TAG = Pattern.compile("(?<MeasureLine>([a-gA-G]?)(\\|)(-\\S+)(\\|))");

    private static final Pattern GUITAR_TAB = Pattern.compile("([hp^BbgGrR\\/\\\sS\\[\\]0-9])");

    private static final int BASE_NOTE = 2;
    private static final int MEASURE_START = 3;
    private static final int MEASURE_INFO = 4;
    private static final int MEASURE_END = 5;
    // private static final int GROUP_CLOSE_BRACKET = 5;
    // private static final int GROUP_ATTRIBUTE_NAME = 1;
    // private static final int GROUP_EQUAL_SYMBOL = 2;
    // private static final int GROUP_ATTRIBUTE_VALUE = 3;

    public static void Xmlsyntax(CodeArea codeArea) {

        // codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText));
        });
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {

        Matcher matcher = XML_TAG.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            if (matcher.group("MeasureLine") != null) {

                String attributesText = matcher.group(MEASURE_INFO);
                spansBuilder.add(Collections.emptyList(), matcher.start(BASE_NOTE) - lastKwEnd);
                spansBuilder.add(Collections.singleton("detected"),
                        matcher.end(MEASURE_START) -  matcher.start(BASE_NOTE) );
                 if (!attributesText.isEmpty()) {
                //     lastKwEnd = 0;
                spansBuilder.add(Collections.singleton("mesureContent"),  matcher.start(MEASURE_END) - matcher.end(MEASURE_START));
                //     Matcher amatcher = ATTRIBUTES.matcher(attributesText);
                //     while (amatcher.find()) {
                //         if (amatcher.group("info") != null) {
                //             spansBuilder.add(Collections.singleton("attribute"), amatcher.end() - amatcher.start());
                //         }
                //         lastKwEnd = amatcher.end();
                //     }
                //     if (attributesText.length() > lastKwEnd)
                //         spansBuilder.add(Collections.emptyList(), attributesText.length() - lastKwEnd);
                 }
                spansBuilder.add(Collections.singleton("detected"),1);
                lastKwEnd = matcher.end(MEASURE_END);                
            }

            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}