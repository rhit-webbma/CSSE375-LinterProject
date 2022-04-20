package data_source;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class javaClassParse implements Parsable{

    @Override
    public HashMap<String, String> parseJSON(String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);

        HashMap<String, String> returnStringMap = new HashMap<>();

        returnStringMap.put(obj.getString("name"), obj.getString("download_url"));
        return returnStringMap;
    }
}
