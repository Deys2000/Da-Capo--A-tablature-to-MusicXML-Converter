package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class xmlGenTest {

	@Test
	void testGuiterGenerator() throws Exception {
		ArrayList<String> test = new ArrayList<>();
		test.add("E|-----------------|");
		test.add("B|-----------------|");
		test.add("G|-----------------|");
		test.add("D|-----------------|");
		test.add("A|-----------------|");
		test.add("E|-2---------------|");
		
		GuitarParser guitarParser = new GuitarParser(test);
		xmlGen xml = new xmlGen(guitarParser);
		xml.getXMLContent();
		System.out.println("In xmlGne Tester: \t" + xml);
		assertEquals(xml, "Expected");
	}

}
