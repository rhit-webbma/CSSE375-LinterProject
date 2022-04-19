package data_source;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public interface Parsable {

    public HashMap<String, String> parseJSON(String jsonString) throws JSONException;
}
