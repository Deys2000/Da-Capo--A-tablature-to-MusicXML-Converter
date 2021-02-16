package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IteratorTest {
    
    @Test
    void testTranslate1() throws Exception {
        
        String expected = "E2";
        Iterator testIterator = new Iterator();
        String actual = testIterator.translate("E2", 0);
        
        System.out.println("expected note: " + expected);
        System.out.println("actual note: " + actual);
        
        assertEquals(expected, actual);
    }
    
    
    @Test
    void testIterator1() throws Exception {
        ArrayList<String> test = new ArrayList<>();
        test.add("|-----------0-----|-0---------------|-----------0-----|");
        test.add("|---------0---0---|-0---------------|---------0---0---|");
        test.add("|-------1-------1-|-1---------------|-------1-------1-|");
        test.add("|-----2-----------|-2---------------|-----2-----------|");
        test.add("|---2-------------|-2---------------|---2-------------|");
        test.add("|-0---------------|-0---------------|-0---------------|");
        
        // Rhythm Setup
        ArrayList<String> expectedRhythm = new ArrayList<>();

        String[] rhythmResult = {"|", "2", "2", "2", "2", "2", "2", "2", "2",
                        "|", "16",
                        "|", "2", "2", "2", "2", "2", "2", "2", "2", "||"};
        
        for (int i = 0; i < rhythmResult.length; i++) {
            expectedRhythm.add(rhythmResult[i]);
        }
        
        // Note Setup
        ArrayList<String> expectedNotes= new ArrayList<>();
        
        String[] notesResult = {"|", "E2", "B2", "E3", "G#3", "B3", "E4", "B3", "G#3",
                "|", "E4",
                "|", "E2", "B2", "E3", "G#3", "B3", "E4", "B3", "G#3", "||"};

        for (int i = 0; i < notesResult.length; i++) {
            expectedNotes.add(notesResult[i]);
        }
        
        // Iterator Testing
        Iterator testIterator = new Iterator();
        
        testIterator.parseToNoteRhythm(test);
        
        // Rhythm Result
        ArrayList<String> actualRhythm = testIterator.rhythmArr;
        System.out.println("expected rhythm: \t" + expectedRhythm);
        System.out.println("rhythm translation: \t" + actualRhythm);
        assertEquals(expectedRhythm, actualRhythm);
        
        // Notes Result
        ArrayList<String> actualNotes = testIterator.notesArr;
        System.out.println("expected notes: \t" + expectedNotes);
        System.out.println("notes translation: \t" + actualNotes);
        assertEquals(expectedNotes, actualNotes);
    }

}


