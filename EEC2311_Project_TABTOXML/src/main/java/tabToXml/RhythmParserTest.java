package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RhythmParserTest {
	@Test
	void testTranslate1() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("|-----------0-----|-0---------------|");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("[6][16]");
		ArrayList<String> actual = RhythmParser.parseToRhythm(test);
		assertEquals(expected,actual);
		
		System.out.println("translation: " + actual);
	}
	
	@Test
	void testTranslate2() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("|---------0---0---|-0---------------|");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("[4][4][16]");
		ArrayList<String> actual = RhythmParser.parseToRhythm(test);
		assertEquals(expected,actual);
		
		System.out.println("translation: " + actual);
	}
	
	@Test
	void testTranslate3() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("|-------1-------1-|-1---------------|");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("[8][2][16]");
		ArrayList<String> actual = RhythmParser.parseToRhythm(test);
		assertEquals(expected,actual);
		
		System.out.println("translation: " + actual);
	}
	
	@Test
	void testTranslate4() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("|-----------0-----|-0---------------|");
		test.add("|---------0---0---|-0---------------|");
		test.add("|-------1-------1-|-1---------------|");
		test.add("|-----2-----------|-2---------------|");
		test.add("|---2-------------|-2---------------|");
		test.add("|-0---------------|-0---------------|");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("[2][2][2][2][2][2][2][2][16]");
		ArrayList<String> actual = RhythmParser.parseToRhythm(test);
		assertEquals(expected,actual);
		
		System.out.println("translation: " + actual);
	}

}
