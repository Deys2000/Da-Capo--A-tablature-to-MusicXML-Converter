package tabToXml;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class detectionTest {

	@Test
	public void test() {
		ArrayList<String> test = new ArrayList<>();
		test.add("|-----------0-----|-0---------------|-----------0-----|");
		test.add("|---------0---0---|-0---------------|---------0---0---|");
		test.add("|-------1-------1-|-1---------------|-------1-------1-|");
		test.add("|-----2-----------|-2---------------|-----2-----------|");
		test.add("|---2-------------|-2---------------|---2-------------|");
		test.add("|-0---------------|-0---------------|-0---------------|");
		
	//	String result = TextFileReader.detectInstrument(test);
	}
	
	

}
