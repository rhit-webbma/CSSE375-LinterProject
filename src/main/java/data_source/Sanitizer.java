package data_source;

public class Sanitizer {
	
	public static String sanitizeString(String s) {
			String[] nameSplit = s.split("/|;");
			return nameSplit[nameSplit.length-1];
	}

}
