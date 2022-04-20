package data_source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class javaFolderParse implements Parsable{

    @Override
    public HashMap<String, String> parseJSON(String jsonString) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonString);
        HashMap<String, String> returnStringMap = new HashMap<>();

        for(int i = 0; i < jsonArray.length(); i++)
        {
            returnStringMap.put(jsonArray.getJSONObject(i).getString("name"), jsonArray.getJSONObject(i).getString("download_url"));
        }
        return returnStringMap;
    }
}
