package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class RhythmParserTest {
//    /*
//    @Test
//    void testTranslate1() throws Exception {
//        ArrayList<String> test = new ArrayList<>();
//        test.add("|-----------0-----|-0---------------|");
//        ArrayList<String> expected = new ArrayList<>();
//        expected.add("(6)(16)");
//        ArrayList<String> actual = RhythmParser.parseToRhythm(test);
//        assertEquals(expected,actual);
//        
//        System.out.println("translation: " + actual);
//    }
//    
//    @Test
//    void testTranslate2() throws Exception {
//        ArrayList<String> test = new ArrayList<>();
//        test.add("|---------0---0---|-0---------------|");
//        ArrayList<String> expected = new ArrayList<>();
//        expected.add("(4)(4)(16)");
//        ArrayList<String> actual = RhythmParser.parseToRhythm(test);
//        assertEquals(expected,actual);
//        
//        System.out.println("translation: " + actual);
//    }
//    
//    @Test
//    void testTranslate3() throws Exception {
//        ArrayList<String> test = new ArrayList<>();
//        test.add("|-------1-------1-|-1---------------|");
//        ArrayList<String> expected = new ArrayList<>();
//        expected.add("(8)(2)(16)");
//        ArrayList<String> actual = RhythmParser.parseToRhythm(test);
//        assertEquals(expected,actual);
//        
//        System.out.println("translation: " + actual);
//    }
//    */
//    
   @Test
   void testDuration1() throws Exception {
       ArrayList<String> test = new ArrayList<>();
       test.add("|-------5-7-----7-|-8-----8-2-----2-|-0---------0-----|-----------------|");
       test.add("|-----5-----5-----|---5-------3-----|---1---1-----1---|-0-1-1-----------|");
       test.add("|---5---------5---|-----5-------2---|-----2---------2-|-0-2-2---2-------|");
       test.add("|-7-------6-------|-5-------4-------|-3---------------|-----------------|");
       test.add("|-----------------|-----------------|-----------------|-2-0-0---0--/8-7-|");
       test.add("|-----------------|-----------------|-----------------|-----------------|");
       
       ArrayList<String> expected = new ArrayList<>();
       String[] arr = {"|", "2", "2", "2", "2", "2", "2", "2", "2",
                       "|", "2", "2", "2", "2", "2", "2", "2", "2",
                       "|", "2", "2", "2", "4", "2", "2", "2",
                       "|", "2", "2", "4", "4", "2", "2", "||"};
       
       for (int i = 0; i < arr.length; i++) {
           expected.add(arr[i]);
       }
       
       System.out.println("expected duration: \t" + expected);
       
       RhythmParser rhythmParser = new RhythmParser(4);
       rhythmParser.parseToRhythm(test);
       ArrayList<String> actual = rhythmParser.durationArr;
       
       System.out.println("actual duration: \t" + actual);
       
       assertEquals(expected,actual);
   }
   
   @Test
   void testType1() throws Exception {
       ArrayList<String> test = new ArrayList<>();
       test.add("|-------5-7-----7-|-8-----8-2-----2-|-0---------0-----|-----------------|");
       test.add("|-----5-----5-----|---5-------3-----|---1---1-----1---|-0-1-1-----------|");
       test.add("|---5---------5---|-----5-------2---|-----2---------2-|-0-2-2---2-------|");
       test.add("|-7-------6-------|-5-------4-------|-3---------------|-----------------|");
       test.add("|-----------------|-----------------|-----------------|-2-0-0---0--/8-7-|");
       test.add("|-----------------|-----------------|-----------------|-----------------|");
       
       ArrayList<String> expected = new ArrayList<>();
       String[] arr = {"|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth",
                       "|", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth", "eighth",
                       "|", "eighth", "eighth", "eighth", "quarter", "eighth", "eighth", "eighth",
                       "|", "eighth", "eighth", "quarter", "quarter", "eighth", "eighth", "||"};
       
       for (int i = 0; i < arr.length; i++) {
           expected.add(arr[i]);
       }
       
       System.out.println("expected type: \t\t" + expected);
       
       RhythmParser rhythmParser = new RhythmParser(4);
       rhythmParser.parseToRhythm(test);
       ArrayList<String> actual = rhythmParser.typeArr;
       
       System.out.println("actual type: \t\t" + actual);
       
       assertEquals(expected,actual);
   }

}

