package tabToXml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DrumParserTest {
	
//	public static void main(String[] args) throws Exception {		
//		ArrayList<String> drumTab = new ArrayList<String>();
//		drumTab.add(" C:|X---------------|X---------------|X---------------|X---------------|");
//		drumTab.add("HH:|----o---o---o---|----o---o---o---|----o---o---o---|----o---o---o---|");
//		drumTab.add(" S:|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|");
//		drumTab.add("BD:|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|");
//		
//		DrumParser dp = new DrumParser(drumTab);
//		System.out.println("DONE");
//
//	}

	@Test
	void test1() throws Exception {
		ArrayList<String> drumTab = new ArrayList<String>();
		drumTab.add(" C:|X---------------|X---------------|X---------------|X---------------|");
		drumTab.add("HH:|----o---o---o---|----o---o---o---|----o---o---o---|----o---o---o---|");
		drumTab.add(" S:|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|");
		drumTab.add("BD:|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|");
		
		DrumParser dp = new DrumParser(drumTab);
		System.out.println("DONE");
		
		int expectedLength = 4;
		int actualLength = dp.getTabStrings().size();
		
		assertEquals(expectedLength,actualLength);
      
	}
	
	@Test
	void test2() throws Exception{
		
		ArrayList<String> drumTab = new ArrayList<String>();
		drumTab.add(" C:|X---------------|X---------------|X---------------|X---------------|");
		drumTab.add("HH:|----o---o---o---|----o---o---o---|----o---o---o---|----o---o---o---|");
		drumTab.add(" S:|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|----o--o-o--o---|");
		drumTab.add("BD:|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|o--o----o-oo--o-|");
		
		DrumParser dp = new DrumParser(drumTab);
		System.out.println("DONE");
		
		int expectedLength = 5;
		int actualLength = dp.getMeasures().size();
		
		assertEquals(expectedLength,actualLength);
		
	}
	
	void test3() throws Exception{
		ArrayList<String> drumTab = new ArrayList<String>();
		drumTab.add(" C:|X---------------|");
		drumTab.add("HH:|----o---o---o---|");
		drumTab.add(" S:|----o--o-o--o---|");
		drumTab.add("BD:|o--o----o-oo--o-|");
		
		String[] transposed = {"||||","X--o","----","----","---o","-oo-","----","----",
						"--o-","-o-o","--o-","---o","---o","-oo-","----","---o","----","||||"};

		
		DrumParser dp = new DrumParser(drumTab);
		
		
		assertEquals(dp.getTransposedTab(),transposed);
		
	}

}
