package tabToXml;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TabParserTest {
	
	@Test
	void testTranslate1() throws Exception {
		String expected = "D4";
		String actual = TabParser.translate("A3",5);
		assertEquals(expected,actual);
	}
	
	@Test
	void testTranslate2() throws Exception {
		String expected = "D3";
		String actual = TabParser.translate("A3",5);
		assertNotEquals(expected,actual);
	}
	
	@Test
	void testTranslate3() {
		Assertions.assertThrows(Exception.class, () -> TabParser.translate("A3", 100));
	}

}