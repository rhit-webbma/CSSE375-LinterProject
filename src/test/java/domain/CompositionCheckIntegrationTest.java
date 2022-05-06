package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import data_source.MyClassNode;

class CompositionCheckIntegrationTest {
	
	CompositionCheck checker = new CompositionCheck();

	@Test
	void testCompositionBad() {
		MyClassNode curClass = new MyClassNode("BadComposition", "/user/package/SuperBadComposition", null, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		String expected = "\nComposition Over Inheritance: \n" + "	Class BadComposition inherits from user created class SuperBadComposition. "
				+ "Could composition be used instead? \n";
		
		assertEquals(expected, checker.runCheck(classes));
	}
	
	@Test
	void testCompositionGood() {
		MyClassNode curClass = new MyClassNode("goodComposition", "java/lang/object/good", null, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
	}

}
