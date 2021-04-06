package tabToXml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import drumTag.Instrument;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TabView {

    private static final Pattern XML_TAG = Pattern
            .compile("(?<MeasureLine>^((CC|HH|SD|HT|MT|BD)|([a-gA-G])?)(\\|)(\\S+)(\\|$))", Pattern.MULTILINE);

    private static final Pattern GUITAR_TAB = Pattern
            .compile("(?<Guitar>(-)|(\\|\\|?)|([hp^BbgGrR\\/sS\\[\\]\\*0-9])|([^\\|hp^BbgGrR\\/sS\\[\\]\\*0-9]))");
    private static final Pattern DRUM_TAB = Pattern.compile("(?<Drum>(-)|(\\|\\|?)|([oOgdxX#])|([^\\|oOgdxX#]))");

    private static final int BASE_NOTE = 2;
    private static final int DRUM_TAGS = 3;
    private static final int GUITAR_TAGS = 4;
    private static final int MEASURE_START = 5;
    private static final int MEASURE_INFO = 6;
    private static final int MEASURE_END = 7;

    private static final int MEASURE_DASH = 2;
    private static final int MEASURE_BAR = 3;
    private static final int VALID_TAB = 4;
    private static final int INVALID_TAB = 5;
    // private static final int GROUP_ATTRIBUTE_NAME = 1;
    // private static final int GROUP_EQUAL_SYMBOL = 2;
    // private static final int GROUP_ATTRIBUTE_VALUE = 3;
    private static boolean drum = false;
    private static boolean guitar = false;
    private static HashMap<Integer, Integer> measureLineEnd;
    private static Integer maxLine;
    private static int lineCount;
    private static HashMap<Integer, Integer> measureLinePOS;

    public static boolean majorError = false;

    public static void Xmlsyntax(CodeArea codeArea, ChoiceBox choiceBox) {

        // codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText, choiceBox));
        });
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text, ChoiceBox choiceBox) {

        majorError = false;
        Matcher matcher = XML_TAG.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        ArrayList<ArrayList<Integer>> barlinePOS = new ArrayList<>();
        int drumTagCount = 0;
        int otherCount = 0;
        lineCount = 0;;
        measureLineEnd = new HashMap<>();
        while (matcher.find()) {
            ArrayList<Integer> barPos = new ArrayList<>();

            String attributesText = matcher.group(MEASURE_INFO);
            if (!attributesText.isEmpty()) {
                Matcher iMatcher;
                if (drum == true)
                    iMatcher = DRUM_TAB.matcher(attributesText);
                else
                    iMatcher = GUITAR_TAB.matcher(attributesText);
                while (iMatcher.find()) {
                    if (iMatcher.end(MEASURE_BAR) != -1)
                        barPos.add(iMatcher.end(MEASURE_BAR));
                }
                barlinePOS.add(barPos);
                lineCount++;
            }
            int tempEnd = matcher.end(MEASURE_END) - matcher.start(MEASURE_START);
            if (measureLineEnd.get(tempEnd) == null)
                measureLineEnd.put(tempEnd, 1);
            else
                measureLineEnd.put(tempEnd, measureLineEnd.get(tempEnd) + 1);

            if (matcher.group(DRUM_TAGS) != null)
                drumTagCount++;
            else
                otherCount++;

        }
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> e : measureLineEnd.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                maxLine = e.getKey();
            }
        }
        int measures = 0;
        ArrayList<Integer> maxSize = new ArrayList<>();
        for (ArrayList a : barlinePOS) {
            maxSize.add(a.size());
        }
        if (!maxSize.isEmpty())
            measures = mostFrequent(maxSize) + 1;
        measureLinePOS = new HashMap<>();
        for (ArrayList a : barlinePOS) {
            int i = 0;
            while (i < measures - 1 && a.size() == (measures - 1)) {
                if (measureLinePOS.get(a.get(i)) == null) {
                    measureLinePOS.put((Integer) a.get(i), 1);
                } else {
                    measureLinePOS.put((Integer) a.get(i), (measureLinePOS.get(a.get(i)) + 1));
                }
                i++;
            }
        }

        System.out.println("drumTagCount: " + drumTagCount);
        System.out.println("OtherCount: " + otherCount);

        if (drumTagCount > otherCount) {
            drum = true;
            guitar = false;
            choiceBox.getSelectionModel().select(2);
        } else if (otherCount > drumTagCount) {
            drum = false;
            guitar = true;
            choiceBox.getSelectionModel().select(1);
        } else {
            drum = false;
            guitar = false;
            majorError = true;
            choiceBox.setValue("Unknown");
        }

        matcher.reset();

        while (matcher.find()) {
            if (matcher.group("MeasureLine") != null) {

                String attributesText = matcher.group(MEASURE_INFO);
                spansBuilder.add(Collections.emptyList(), matcher.start(BASE_NOTE) - lastKwEnd);
                if (drum == true && matcher.group(GUITAR_TAGS) != null) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_START) - matcher.start(BASE_NOTE));
                } else if (guitar == true && matcher.group(DRUM_TAGS) != null) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_START) - matcher.start(BASE_NOTE));
                } else if (drum == true && (matcher.group(GUITAR_TAGS) == null && matcher.group(DRUM_TAGS) == null)) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_START) - matcher.start(BASE_NOTE));
                } else {
                    spansBuilder.add(Collections.singleton("detected"),
                            matcher.end(MEASURE_START) - matcher.start(BASE_NOTE));
                }
                if (!attributesText.isEmpty()) {
                    Matcher iMatcher;
                    if (drum == true)
                        iMatcher = DRUM_TAB.matcher(attributesText);
                    else
                        iMatcher = GUITAR_TAB.matcher(attributesText);
                    lastKwEnd = 0;

                    while (iMatcher.find()) {

                        spansBuilder.add(Collections.singleton("minorError"),
                                iMatcher.end(INVALID_TAB) - iMatcher.start(INVALID_TAB));
                        spansBuilder.add(Collections.singleton("dash"),
                                iMatcher.end(MEASURE_DASH) - iMatcher.start(MEASURE_DASH));
                        if (measureLinePOS.get(iMatcher.end(MEASURE_BAR)) != null && measureLinePOS.get(iMatcher.end(MEASURE_BAR)) >= lineCount-1 )
                            spansBuilder.add(Collections.singleton("barLine"),
                                    iMatcher.end(MEASURE_BAR) - iMatcher.start(MEASURE_BAR));
                        else{                            
                            if(iMatcher.end(MEASURE_BAR) - iMatcher.start(MEASURE_BAR) > 0)
                                majorError = true;
                            spansBuilder.add(Collections.singleton("majorError"),
                                    iMatcher.end(MEASURE_BAR) - iMatcher.start(MEASURE_BAR));
                        }
                        spansBuilder.add(Collections.singleton("guitar"),
                                iMatcher.end(VALID_TAB) - iMatcher.start(VALID_TAB));
                        // spansBuilder.add(Collections.emptyList(), iMatcher.end() - lastKwEnd);
                        lastKwEnd = iMatcher.end();
                    }

                }
                // spansBuilder.add(Collections.singleton("detected"), 1);
                lastKwEnd = matcher.end(MEASURE_INFO);
                // spansBuilder.add(Collections.singleton("majorError"),
                // matcher.start(MEASURE_END)
                // - lastKwEnd);
                if (drum == true && matcher.group(GUITAR_TAGS) != null) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_END) - matcher.start(MEASURE_END));
                } else if (guitar == true && matcher.group(DRUM_TAGS) != null) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_END) - matcher.start(MEASURE_END));
                } else if (drum == true && (matcher.group(GUITAR_TAGS) == null && matcher.group(DRUM_TAGS) == null)) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_END) - matcher.start(MEASURE_END));
                } else if (matcher.end(MEASURE_END) - matcher.start(MEASURE_START) != maxLine) {
                    majorError = true;
                    spansBuilder.add(Collections.singleton("majorError"),
                            matcher.end(MEASURE_END) - matcher.start(MEASURE_END));
                } else {
                    spansBuilder.add(Collections.singleton("detected"),
                            matcher.end(MEASURE_END) - matcher.start(MEASURE_END));
                }
            }

            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    static int mostFrequent(ArrayList<Integer> list) {

        // Insert all elements in hash
        Map<Integer, Integer> hp = new HashMap<Integer, Integer>();

        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            if (hp.containsKey(key)) {
                int freq = hp.get(key);
                freq++;
                hp.put(key, freq);
            } else {
                hp.put(key, 1);
            }
        }

        // find max frequency.
        int max_count = 0, res = -1;

        for (Entry<Integer, Integer> val : hp.entrySet()) {
            if (max_count < val.getValue()) {
                res = val.getKey();
                max_count = val.getValue();
            }
        }

        return res;
    }

}
