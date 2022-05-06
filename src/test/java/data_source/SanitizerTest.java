package data_source;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SanitizerTest {

	@Test
	void testSanitize() {
		String expected = "thing";
		String longName = "java/lang/object/thing;/";
		assertEquals(expected, Sanitizer.sanitizeString(longName));
	}

}
