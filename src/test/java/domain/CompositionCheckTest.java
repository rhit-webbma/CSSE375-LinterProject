package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import data_source.MyClassNode;

class CompositionCheckTest {

	@Test
	void testCompositionGood() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		node.name = "java/util/ArrayList";
		node.superName = "java/lang/Object";
		CompositionCheck check = new CompositionCheck();
		assertEquals("", check.checkClassComposition(node));
	}
	
	@Test
	void testCompositionBad() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		node.name = "datasource/test/ClassName";
		node.superName = "domain/test/SuperName";
		CompositionCheck check = new CompositionCheck();
		assertEquals("	Class ClassName inherits from user created class SuperName. Could composition be used instead? \n", check.checkClassComposition(node));
	}

}
